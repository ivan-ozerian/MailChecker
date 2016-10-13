package com.ozerian.app.service.imp;

import com.ozerian.app.model.entity.Profile;
import com.ozerian.app.repository.ProfileRepository;
import com.ozerian.app.service.ProfileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Implemented class of appropriate Service class interface
 * for possibility to add, delete, findAll Profiles.
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    private final static Logger LOGGER = Logger.getLogger(ProfileServiceImpl.class);

    @Override
    public Profile saveProfile(Profile profile) {
        profileRepository.save(profile);
        LOGGER.debug("saveProfile() service method was called and execute success");
        return profile;
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.delete(id);
        LOGGER.debug("deleteProfile() service method was called and execute success");
    }

    @Override
    public List<Profile> getAllProfiles() {
        List<Profile> profiles = (List<Profile>) profileRepository.findAll();
        LOGGER.debug("getAllProfiles() service method was called and execute success");
        return profiles;
    }

    @Autowired
    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
}
