package com.ozerian.app.service.imp;

import com.ozerian.app.entity.Profile;
import com.ozerian.app.repository.ProfileRepository;
import com.ozerian.app.service.ProfileService;

import java.util.List;

public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Override
    public Profile addProfile(Profile profile) {
        return null;
    }

    @Override
    public void deleteProfile(Long id) {

    }

    @Override
    public List<Profile> getAllProfiles() {
        return null;
    }
}
