package com.pictime.codinggame.basicai.game;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("${lightningbot.url}")
    private String url;

    @Value("${lightningbot.pseudo}")
    private String pseudo;

    @Value("${lightningbot.token}")
    private String token;

    @Value("${lightningbot.mode}")
    private String mode;

    public String getUrl() {
        return url;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getToken() {
        return token;
    }

    public String getMode() {
        return mode;
    }
}
