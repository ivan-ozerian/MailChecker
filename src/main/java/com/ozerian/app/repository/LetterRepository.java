package com.ozerian.app.repository;

import com.ozerian.app.model.entity.Letter;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring Data JPA Repository for manipulation with Letter's model.
 */
public interface LetterRepository extends CrudRepository<Letter, Long> {

}
