package com.pictime.codinggame.basicai.game;

import com.pictime.codinggame.basicai.client.Lightningbot;
import com.pictime.codinggame.basicai.game.ai.MyAI;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Game {

    @Autowired
    Config config;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public ResponseEntity<String> start() throws Exception{
        GameClient c = new GameClient(config);

        MyAI ai = new MyAI(c);
        ai.start();

        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
