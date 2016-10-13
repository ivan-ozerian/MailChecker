package com.ozerian.app.gui.controller;

import com.ozerian.app.gui.view.ProfilesWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controller class for launching of main program's window.
 */
@Controller
public class ProfilesWindowController {

    private ProfilesWindow window;

    /**
     * This method prepare and launch main frame window.
     */
    public void prepareAndOpenFrame() {
        window.init();
    }

    @Autowired
    public void setWindow(ProfilesWindow window) {
        this.window = window;
    }
}
