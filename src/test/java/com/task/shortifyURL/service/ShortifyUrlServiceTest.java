package com.task.shortifyURL.service;

import com.task.shortifyURL.Entity.ShortyURL;
import com.task.shortifyURL.Repositoties.ShortifyURLRepository;
import com.task.shortifyURL.Service.ShortifyUrlService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ShortifyUrlServiceTest {
    @InjectMocks
    private ShortifyUrlService shortifyUrlService;

    @Mock
    private ShortifyURLRepository shortifyURLRepository;

    @Test
    public void testIsValidURL() {
        assertTrue(shortifyUrlService.isValidURL("https://example.com"));
        assertFalse(shortifyUrlService.isValidURL("invalid-url"));
    }

    @Test
    public void testGetOriginalURL() {
        String shortURL = "http://short/abc123";
        ShortyURL shortyURL = new ShortyURL();
        shortyURL.setOriginalURL("https://example.com");
        shortyURL.setShortURL(shortURL);

        Mockito.when(shortifyURLRepository.findByShortURL(shortURL)).thenReturn(Optional.of(shortyURL));

        Optional<String> originalURL = shortifyUrlService.getOriginalURL(shortURL);
        assertTrue(originalURL.isPresent());
        assertEquals("https://example.com", originalURL.get());
    }

    @Test
    public void testShortenURL() {
        ShortyURL shortyURL = new ShortyURL();
        String md5Hash = DigestUtils.md5Hex("https://example.com");
        String shortVersion = "http://short/" + md5Hash.substring(0, 8);
        shortyURL.setOriginalURL("https://example.com");
        shortyURL.setShortURL("http://short/abc123");

        Mockito.when(shortifyURLRepository.save(Mockito.any())).thenReturn(shortyURL);

        String shortenedURL = shortifyUrlService.shortenURL("https://example.com");
        assertEquals(shortVersion, shortenedURL);
    }

    @Test
    public void testGetShortURL() {
        String originalURL = "https://example.com";
        ShortyURL shortyURL = new ShortyURL();
        shortyURL.setOriginalURL(originalURL);
        shortyURL.setShortURL("http://short/abc123");

        Mockito.when(shortifyURLRepository.findByOriginalURL(originalURL)).thenReturn(Optional.of(shortyURL));

        Optional<String> shortURL = shortifyUrlService.getShortURL(originalURL);
        assertTrue(shortURL.isPresent());
        assertEquals("http://short/abc123", shortURL.get());
    }

    @Test
    public void testGetOriginalURLNotFound() {
        String shortURL = "http://short/notfound";
        Mockito.when(shortifyURLRepository.findByShortURL(shortURL)).thenReturn(Optional.empty());
        Optional<String> originalURL = shortifyUrlService.getOriginalURL(shortURL);
        assertFalse(originalURL.isPresent());
    }

    @Test
    public void testGetShortURLNotFound() {
        String originalURL = "https://example.com/notfound";
        Mockito.when(shortifyURLRepository.findByOriginalURL(originalURL)).thenReturn(Optional.empty());
        Optional<String> shortURL = shortifyUrlService.getShortURL(originalURL);
        assertFalse(shortURL.isPresent());
    }

    @Test
    public void testIsExist() {
        String originalURL = "https://example.com";
        ShortyURL shortyURL = new ShortyURL();
        shortyURL.setOriginalURL(originalURL);
        Mockito.when(shortifyURLRepository.findByOriginalURL(originalURL)).thenReturn(Optional.of(shortyURL));
        assertTrue(shortifyUrlService.isExist(originalURL));
    }

    @Test
    public void testShortenURLMD5Hash() {
        String originalURL = "https://example.com";
        String expectedHash = "c984d06a";
        ShortyURL shortyURL = new ShortyURL();
        shortyURL.setOriginalURL(originalURL);
        shortyURL.setShortURL("http://short/" + expectedHash);
        Mockito.when(shortifyURLRepository.save(Mockito.any())).thenReturn(shortyURL);
        String shortenedURL = shortifyUrlService.shortenURL(originalURL);
        assertTrue(shortenedURL.endsWith(expectedHash));
    }
}
