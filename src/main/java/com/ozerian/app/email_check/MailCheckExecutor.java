package com.ozerian.app.email_check;

import com.ozerian.app.model.entity.Letter;
import com.ozerian.app.model.entity.Profile;
import com.ozerian.app.service.LetterService;
import com.ozerian.app.service.ProfileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.Multipart;
import javax.mail.BodyPart;
import javax.mail.Part;
import javax.mail.NoSuchProviderException;
import javax.mail.MessagingException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Class for implementation of email scheduled checking through @Scheduled annotation
 * which is available in Spring Framework. There is a logic for checking of new "unseen"
 * email messages, checking on letter's attachments and saveLetter messages into a database and
 * attachments in folder according profile's "path for attachments".
 */
@Component
public class MailCheckExecutor {

    private LetterService letterService;
    private ProfileService profileService;

    private static final Logger LOGGER = Logger.getLogger(MailCheckExecutor.class);

    public MailCheckExecutor() {
    }

    /**
     * Method for checking profile's email with scheduled execution (every 60 second).
     */
    @Scheduled(fixedRate = 60000)
    public void checkScheduleExecute() {
        LOGGER.debug("Scheduled method for each profile check is running");
        List<Profile> profiles = profileService.getAllProfiles();
        profiles.forEach(this::connectAndCheckMail);
        LOGGER.debug("Checking was success");
    }

    /**
     * This method handles profiles data, get email address, port, password
     * and try to connect to email account for checking on new messages.
     * This method also contain logic for check what kind of message content is
     * in the message (text, file (attachments) and so on).
     *
     * @param profile Profile for mail check.
     */
    public void connectAndCheckMail(Profile profile) {

        try {
            String protocolName = defineProtocol(profile.getPort());
            String mailServiceDomain = defineDomain(profile.getEmail());

            // delete all numeric values from protocol name. For example,
            // if we have pop3 protocol, domain name will be pop.mail.com without "3".
            String host = protocolName.replaceAll("[0-9.]", "") + "." + mailServiceDomain;

            Properties props = setProfileProperties(profile, host);

            Session session = Session.getDefaultInstance(props, null);

            // Addition "s" suffix for protocol name for ssl data transfer support.
            Store store = session.getStore(protocolName + "s");
            store.connect(host, profile.getEmail(), profile.getPassword());
            LOGGER.debug("Connection to mail profile is success from connectAndCheckMail()");

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);

            // search for all "unseen" messages
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
            Message messages[] = inbox.search(unseenFlagTerm);

            checkContentAndSaveLetter(profile, messages);

            // close inbox and store.
            inbox.close(true);
            store.close();
            LOGGER.debug("Inbox and store have been closed. Method connectAndCheckMail() was correct");

        } catch (NoSuchProviderException e) {
            LOGGER.error("Try to use an unsupported provider", e);
            e.printStackTrace();
        } catch (MessagingException e) {
            LOGGER.error("Error during message checking", e);
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error("Error during work with messages", e);
            e.printStackTrace();
        }
    }

    /**
     * This private method receive an array of new messages from method above
     * (connectAndCheckMail()) and iterate through each letter with cheking on it's
     * contain.
     *
     * @param profile  Profile of an email account.
     * @param messages Message[] array with new received messages.
     * @throws MessagingException
     * @throws IOException
     */
    private void checkContentAndSaveLetter(Profile profile, Message[] messages) throws MessagingException, IOException {

        for (Message message : messages) {
            Address[] fromAddress = message.getFrom();
            String contentType = message.getContentType();
            String messageContent = "";

            messageContent = checkMessageContent(message, contentType, messageContent, profile);

            saveNewLetter(message, fromAddress[0], messageContent, profile);
        }
    }

    /**
     * This method set appropriate properties for each existing profile.
     *
     * @param profile Profile for checking.
     * @param host    Host of email server service.
     * @return Properties object with set properties.
     */
    private Properties setProfileProperties(Profile profile, String host) {
        Properties props = new Properties();
        props.setProperty("mail.host", host);
        props.setProperty("mail.socketFactory.port", profile.getPort());
        props.setProperty("mail.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.auth", "true");
        props.setProperty("mail.port", profile.getPort());
        props.setProperty("mail.ssl.trust", "*");
        LOGGER.info("Profile settings has been set");
        return props;
    }

    /**
     * This private method called from checkContentAndSaveLetter() method and
     * check what kind of content is in the message (text, html, file and so on)
     *
     * @param message        Message for checking from an array of received messages.
     * @param contentType    String defined content type.
     * @param messageContent String for save of message content.
     * @param profile        Profile for checking.
     * @return String message text content.
     * @throws IOException
     * @throws MessagingException
     */
    private String checkMessageContent(Message message, String contentType, String messageContent, Profile profile)
            throws IOException, MessagingException {
        LOGGER.debug("checkMessageContent() is running");
        if (contentType.contains("multipart")) {
            Multipart multipart = (Multipart) message.getContent();

            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);

                if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                    downloadAttachment(profile, bodyPart);
                } else {
                    messageContent = bodyPart.getContent().toString();
                }
            }
        } else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
            messageContent = message.getContent().toString();
        }
        LOGGER.debug("Message was handled correct");
        return messageContent;
    }

    /**
     * This method is for downloading of attachment file from the letter in the folder
     * which was specified in Profile settings.
     *
     * @param profile  Profile profile for receiving of path to folder for storing of attachments.
     * @param bodyPart BodyPart part of the message with attachment.
     * @throws MessagingException
     * @throws IOException
     */
    private void downloadAttachment(Profile profile, BodyPart bodyPart) throws MessagingException, IOException {
        LOGGER.debug("New file from attachment is creating");
        File file = new File(profile.getAttachmentStorePath() + "/" + bodyPart.getFileName());

        try (InputStream inputStream = bodyPart.getInputStream();
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            byte[] buf = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buf)) != -1) {
                fileOutputStream.write(buf, 0, bytesRead);
            }
        }
        LOGGER.debug("Attachment download has been finished");
    }

    /**
     * This message is for saving of new letter to database through LetterService.
     * Creation of new Letter object, setting of all it's properties (subject, from,
     * date, content and so on).
     *
     * @param message        Message for receiving all necessary properties.
     * @param fromAddress    Address of the sender.
     * @param messageContent String message content for saving to database.
     * @param profile        Profile profile fro setting appropriate profile.
     * @throws MessagingException
     */
    private void saveNewLetter(Message message, Address fromAddress, String messageContent, Profile profile) throws MessagingException {
        Letter letter = new Letter();
        letter.setSender(fromAddress.toString());
        letter.setSubject(message.getSubject());
        letter.setLetterContent(messageContent);
        letter.setLetterDate(message.getReceivedDate());
        letter.setPath(profile.getAttachmentStorePath());
        letter.setProfile(profile);

        letterService.saveLetter(letter);
        LOGGER.info("New letter has been saved!");
    }

    /**
     * Method for definition of mail protocol according profile's prpperties.
     *
     * @param port String port for connection to mail service.
     * @return String protocol name (provider).
     * @throws NoSuchProviderException
     */
    private String defineProtocol(String port) throws NoSuchProviderException {
        if ("995".equals(port)) {
            return "pop3";
        } else if ("993".equals(port)) {
            return "imap";
        } else {
            LOGGER.error("Not supported protocol. Occurred in defineProtocol() method");
            throw new NoSuchProviderException();
        }
    }

    /**
     * Method for definition of domain service name through division
     * of the email string on a part after "@" symbol.
     *
     * @param mailAddress Profile's email address.
     * @return name of domain service.
     */
    private String defineDomain(String mailAddress) {
        return mailAddress.substring(mailAddress.indexOf("@") + 1);
    }

    @Autowired
    public void setLetterService(LetterService letterService) {
        this.letterService = letterService;
    }

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
