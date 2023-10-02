package com.task.shortifyURL.Service;
import com.task.shortifyURL.Entity.ShortyURL;
import com.task.shortifyURL.exceptions.CustomException;
import org.apache.commons.codec.digest.DigestUtils;
import com.task.shortifyURL.Repositoties.ShortifyURLRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URL;
import java.util.Optional;
import org.slf4j.Logger;

@Service
public class ShortifyUrlService {
    private final ShortifyURLRepository shortURLRepository;

    private static final Logger logger = LoggerFactory.getLogger(ShortifyUrlService.class);

    @Autowired
    public ShortifyUrlService(ShortifyURLRepository shortURLRepository) {
        this.shortURLRepository = shortURLRepository;
    }

    public String shortenURL(String originalURL) {
        try{
            String md5Hash = DigestUtils.md5Hex(originalURL);
            String shortVersion = "http://short/" + md5Hash.substring(0, 8);

            ShortyURL shortyURL = new ShortyURL();
            shortyURL.setOriginalURL(originalURL);
            shortyURL.setShortURL(shortVersion);

            logger.info("Shortening URL: {}", originalURL);

            shortURLRepository.save(shortyURL);

            return shortVersion;
        }catch (Exception e) {
            logger.error("An error occurred while shortening URL: {}", originalURL, e);
            throw new CustomException("Failed to shorten URL");
        }

    }

    public boolean isValidURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<String> getOriginalURL(String shortURL) {
        Optional<ShortyURL> shortyURL = shortURLRepository.findByShortURL(shortURL);
        return shortyURL.map(ShortyURL::getOriginalURL);
    }

    public Optional<String> getShortURL(String originalURL) {
        Optional<ShortyURL> shortyURL = shortURLRepository.findByOriginalURL(originalURL);
        return shortyURL.map(ShortyURL::getShortURL);
    }

    public boolean isExist(String originalURL) {
        Optional<ShortyURL> shortyURL = shortURLRepository.findByOriginalURL(originalURL);
        return shortyURL.isPresent();
    }
}
