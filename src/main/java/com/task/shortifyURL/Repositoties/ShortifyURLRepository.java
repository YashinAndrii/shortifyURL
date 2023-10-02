package com.task.shortifyURL.Repositoties;

import com.task.shortifyURL.Entity.ShortyURL;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortifyURLRepository extends MongoRepository<ShortyURL, String> {
    Optional<ShortyURL> findByShortURL(String shortURL);
    Optional<ShortyURL> findByOriginalURL(String originalURL);
}
