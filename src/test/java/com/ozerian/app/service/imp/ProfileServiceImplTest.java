package com.ozerian.app.service.imp;

import com.ozerian.app.model.entity.Profile;
import com.ozerian.app.repository.ProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ProfileServiceImplTest {

    private ProfileServiceImpl profileService;
    private ProfileRepository profileRepository;
    private Profile profile;
    private List<Profile> profiles;

    @Before
    public void setUp() {
        profileService = new ProfileServiceImpl();
        profileRepository = mock(ProfileRepository.class);
        profileService.setProfileRepository(profileRepository);
        profile = new Profile("123@gmail.com", "993", "D://test");
        profiles = new ArrayList<>();
    }

    @Test
    public void testMockCreation() {
        assertNotNull(profileRepository);
    }

    @Test
    public void saveProfile() throws Exception {
        profileService.saveProfile(profile);
        verify(profileRepository, times(1)).save(profile);
    }

    @Test
    public void getAllProfiles() throws Exception {
        when(profileService.getAllProfiles()).thenReturn(profiles);
        assertEquals(new ArrayList<Profile>(), profileService.getAllProfiles());
        verify(profileRepository, times(1)).findAll();
    }

}