package com.ozerian.app.service;

import com.ozerian.app.entity.Letter;

import java.util.List;

public interface LetterService {

    Letter addLetter(Letter letter);

    void deleteLetter(Long id);

    List<Letter> getAllLetters();
}
