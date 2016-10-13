package com.ozerian.app.service.imp;

import com.ozerian.app.model.entity.Profile;
import com.ozerian.app.repository.ProfileRepository;
import com.ozerian.app.service.ProfileService;
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

    @Override
    public Profile saveProfile(Profile profile) {
        profileRepository.save(profile);
        return profile;
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.delete(id);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return (List<Profile>) profileRepository.findAll();
    }

    @Autowired
    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
}
