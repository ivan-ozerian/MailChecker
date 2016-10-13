package com.ozerian.app.repository;

import com.ozerian.app.entity.Profile;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring Data JPA Repository for manipulation with Letter's entity.
 */
public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
