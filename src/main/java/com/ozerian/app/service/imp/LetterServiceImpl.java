package com.ozerian.app.service.imp;

import com.ozerian.app.model.entity.Letter;
import com.ozerian.app.repository.LetterRepository;
import com.ozerian.app.service.LetterService;
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

    @Override
    public Letter addLetter(Letter letter) {
        letterRepository.save(letter);
        return letter;
    }

    @Override
    public void deleteLetter(Long id) {
        letterRepository.delete(id);
    }

    @Override
    public List<Letter> getAllLetters() {
        return (List<Letter>) letterRepository.findAll();
    }

    @Autowired
    public void setLetterRepository(LetterRepository letterRepository) {
        this.letterRepository = letterRepository;
    }
}
