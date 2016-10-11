package com.ozerian.app.repository;

import com.ozerian.app.entity.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
