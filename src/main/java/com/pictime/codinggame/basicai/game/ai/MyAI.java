package com.pictime.codinggame.basicai.game.ai;

import com.pictime.codinggame.basicai.client.dto.DirectionsResponse;
import com.pictime.codinggame.basicai.client.dto.InfoResponse;
import com.pictime.codinggame.basicai.game.GameClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAI extends  Basic {
    private static final Logger logger = LoggerFactory.getLogger(MyAI.class);

    public MyAI(GameClient client) {
        super(client);
    }

    /**
     * Cette fonction est appelée une seule fois, en début de partie
     * Elle permet de récupérer les informations utiles sur la partie
     * @param infoResponse
     */
    void receiveInformation(InfoResponse infoResponse) {
        logger.info("receiveInformation : " + infoResponse.getName());

        //TODO: exploiter les informations pour initialiser la stratégie
    }

    /**
     * Cette fonction est appelée une fois par tour et permet
     * de récupérer les directions que les joueurs ont
     * pris au tour précédent
     * @param directionsResponse
     * @param turn
     */
    void receiveDirections(DirectionsResponse directionsResponse, int turn) {
        logger.info("receiveDirections (" + turn + ") : " + directionsResponse.getDirections().size());

        //TODO: exploiter les informations pour mettre à jour la stratégie
    }

    /**
     * Cette fonction est appelée une fois par tour et sert
     * à informer le serveur de la direction à prendre
     * pour ce tour
     * @param turn
     * @param waitForNextTurn
     * @return 0,1,2 ou 3 pour aller à droite, en bas, à gauche ou en haut
     */
    int choseDirection(int turn, long waitForNextTurn) {
        int randomDirection = (int) (Math.random() * 3);
        logger.info("choseDirection (turn " + turn + ", direction " + randomDirection + ")");

        //TODO: renvoyer la direction choisie

        return randomDirection;
    }
}
