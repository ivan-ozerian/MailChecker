package com.ozerian.app.gui.controller;

import com.ozerian.app.gui.view.ProfilesWindow;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ProfilesWindowControllerTest {

    private ProfilesWindowController controller;
    private ProfilesWindow window;

    @Before
    public void setUp(){
        controller = new ProfilesWindowController();
        window = mock(ProfilesWindow.class);
        controller.setWindow(window);
    }

    @Test
    public void testMockCreation() {
        assertNotNull(window);
    }

    @Test
    public void prepareAndOpenFrame() throws Exception {
        controller.prepareAndOpenFrame();
        verify(window, times(1)).init();
    }

}