package com.ozerian.app.service.imp;

import com.ozerian.app.entity.Letter;
import com.ozerian.app.repository.LetterRepository;
import com.ozerian.app.service.LetterService;

import java.util.List;

public class LetterServiceImpl implements LetterService {

    private LetterRepository letterRepository;

    @Override
    public Letter addLetter(Letter letter) {
        return null;
    }

    @Override
    public void deleteLetter(Long id) {

    }

    @Override
    public List<Letter> getAllLetters() {
        return null;
    }
}
