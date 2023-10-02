package com.task.shortifyURL.Controllers;

import com.task.shortifyURL.Service.ShortifyUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ShortifyUrlController {
    private static final Logger logger = LoggerFactory.getLogger(ShortifyUrlService.class);
    private final ShortifyUrlService shortenedURLService;

    @Autowired
    public ShortifyUrlController(ShortifyUrlService shortenedURLService) {
        this.shortenedURLService = shortenedURLService;
    }

    @GetMapping("/shortify")
    String shortify() {
        return "shortPage";
    }

    @PostMapping("/shortify")
    public String shortenURL(@RequestParam("originalURL") String originalURL, Model model) {
        try {
            Optional<String> shortenedURL = shortenedURLService.getShortURL(originalURL);

            if (shortenedURL.isPresent()) {
                model.addAttribute("shortenedURL", shortenedURL.get());
            } else if (shortenedURLService.isValidURL(originalURL)) {
                String newShortenedURL = shortenedURLService.shortenURL(originalURL);
                model.addAttribute("shortenedURL", newShortenedURL);
            }
        } catch (Exception e) {
            logger.error("An error occurred while shortening URL: {}", originalURL, e);
            model.addAttribute("error", "An error occurred");
        }
        return "shortPage";

    }

    @GetMapping("/orig")
    String original() {
        return "origPage";
    }

    @PostMapping("/orig")
    String getOriginal(@RequestParam("shortURL") String shortURL, Model model) {
        Optional<String> originalURL = shortenedURLService.getOriginalURL(shortURL);

        originalURL.ifPresent(s -> model.addAttribute("originalURL", s));

        return "origPage";
    }
}
