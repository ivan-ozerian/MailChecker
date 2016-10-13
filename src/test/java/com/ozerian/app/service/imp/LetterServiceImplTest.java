package com.ozerian.app.service.imp;

import com.ozerian.app.model.entity.Letter;
import com.ozerian.app.repository.LetterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class LetterServiceImplTest {

    private LetterServiceImpl letterService;
    private LetterRepository letterRepository;
    private Letter letter;
    private List<Letter> letters;

    @Before
    public void setUp() {
        letterService = new LetterServiceImpl();
        letterRepository = mock(LetterRepository.class);
        letterService.setLetterRepository(letterRepository);
        letter = new Letter();
        letters = new ArrayList<>();
    }

    @Test
    public void testMockCreation() {
        assertNotNull(letterRepository);
    }

    @Test
    public void saveLetter() throws Exception {
        letterService.saveLetter(letter);
        verify(letterRepository, times(1)).save(letter);
    }

    @Test
    public void getAllLetters() throws Exception {
        when(letterService.getAllLetters()).thenReturn(letters);
        assertEquals(new ArrayList<Letter>(), letterService.getAllLetters());
        verify(letterRepository, times(1)).findAll();
    }
}