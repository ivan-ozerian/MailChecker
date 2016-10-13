package com.ozerian.app.service;

import com.ozerian.app.model.entity.Profile;

import java.util.List;

public interface ProfileService {

    Profile saveProfile(Profile profile);

    List<Profile> getAllProfiles();

}
