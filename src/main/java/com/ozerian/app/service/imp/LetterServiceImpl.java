package com.ozerian.app.service.imp;

import com.ozerian.app.model.entity.Letter;
import com.ozerian.app.repository.LetterRepository;
import com.ozerian.app.service.LetterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implemented class of appropriate Service class interface
 * for possibility to add, delete, findAll Letters.
 */
@Service
public class LetterServiceImpl implements LetterService {

    private LetterRepository letterRepository;

    private final static Logger LOGGER = Logger.getLogger(LetterServiceImpl.class);

    @Override
    public Letter saveLetter(Letter letter) {
        letterRepository.save(letter);
        LOGGER.debug("saveLetter() service method was called and execute success");
        return letter;
    }

    @Override
    public void deleteLetter(Long id) {
        letterRepository.delete(id);
        LOGGER.debug("deleteLetter() service method was called and execute success");
    }

    @Override
    public List<Letter> getAllLetters() {
        List<Letter> letters = (List<Letter>) letterRepository.findAll();
        LOGGER.debug("getAllLetter() service method was called and execute success");
        return letters;
    }

    @Autowired
    public void setLetterRepository(LetterRepository letterRepository) {
        this.letterRepository = letterRepository;
    }
}
