package com.ozerian.app.service;

import com.ozerian.app.entity.Profile;

import java.util.List;

public interface ProfileService {

    Profile addProfile(Profile profile);

    void deleteProfile(Long id);

    List<Profile> getAllProfiles();

}
