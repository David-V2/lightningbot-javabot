package com.pictime.codinggame.basicai.game;

import com.pictime.codinggame.basicai.client.Lightningbot;
import com.pictime.codinggame.basicai.client.dto.*;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class GameClient {

    private static final Logger logger = LoggerFactory.getLogger(GameClient.class);

    private boolean isInTestMode;
    private Lightningbot client;

    private String pseudo;
    private String token;

    private Lightningbot getLightningbotClient(Config config){

        String address = config.getUrl();

        Lightningbot client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(Lightningbot.class, address);

        return client;
    }

    public GameClient(Config config){
        this.isInTestMode = config.getMode().equals("test");
        this.client = getLightningbotClient(config);

        if(this.isInTestMode){
            logger.info("La partie est créée en mode test");
            this.pseudo = config.getPseudo() + UUID.randomUUID().toString().substring(0,5);
            //this.token = Le token sera renvoyé dans la réponse de /connect
        }else{
            logger.info("La partie est créée en mode identifié");
            this.token = config.getToken();
            //this.pseudo = Le pseudo sera renvoyé dans la réponse de /connect
        }
    }

    public void connect() throws Exception {

        AbstractResponse resp;
        if(this.isInTestMode){
            ConnectTestResponse connectTestResponse = this.client.connectTest(this.pseudo);
            this.token = connectTestResponse.getToken();
            resp = connectTestResponse;
        }else{
            ConnectResponse connectResponse = this.client.connect(token);
            this.pseudo = connectResponse.getPseudo();
            resp = connectResponse;
        }

        Long wait = resp.getWait();

        if(!resp.isSuccess()) GameErrorHandler.throwError(resp.getError());

        logger.info(this.pseudo + " est connecté avec le token " + this.token);
        logger.info("La partie commence dans " + wait + " millisecondes");

        TimeUnit.MILLISECONDS.sleep(wait);
    }


    public InfoResponse info() throws Exception {
        InfoResponse infoResponse = client.info(this.token);

        if (!infoResponse.isSuccess()) GameErrorHandler.throwError(infoResponse.getError());

        logger.info("Données d'initialisation reçues pour la partie \"" + infoResponse.getName()+"\"");
        logger.info("Le premier tour commence dans " + infoResponse.getWait() + " millisecondes");

        return infoResponse;
    }

    public DirectionsResponse direction(int turn) throws Exception {
        DirectionsResponse resp = client.direction(this.token,turn);
        if(resp.isSuccess()) {
            logger.info("Récupération des directions pour le tour " + turn);
        } else {
            GameErrorHandler.throwError(resp.getError());
        }
        return resp;
    }

    public MoveResponse move(int direction, int turn) throws Exception {
        MoveResponse resp = client.move(this.token,direction,turn);

        if(resp.isSuccess()) {
            logger.info("Définition du prochain mouvement : OK");
        } else {
            GameErrorHandler.throwError(resp.getError());
        }
        logger.info("Attente de " + resp.getWait() + " millisecondes");

        return resp;
    }
}
