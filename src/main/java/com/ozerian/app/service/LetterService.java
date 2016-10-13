package com.ozerian.app.service;

import com.ozerian.app.model.entity.Letter;

import java.util.List;

public interface LetterService {

    Letter saveLetter(Letter letter);

    List<Letter> getAllLetters();
}
