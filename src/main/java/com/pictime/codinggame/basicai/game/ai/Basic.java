package com.pictime.codinggame.basicai.game.ai;

import com.pictime.codinggame.basicai.client.dto.DirectionsResponse;
import com.pictime.codinggame.basicai.client.dto.InfoResponse;
import com.pictime.codinggame.basicai.client.dto.MoveResponse;
import com.pictime.codinggame.basicai.game.GameClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

abstract class Basic {
    private static final Logger logger = LoggerFactory.getLogger(Basic.class);

    private GameClient gameClient;
    public Basic(GameClient client){
        super();
        gameClient = client;
    }

    private void getInfo() throws Exception {
        InfoResponse infoResponse = gameClient.info();

        long before = System.currentTimeMillis();
        receiveInformation(infoResponse);
        long after = System.currentTimeMillis();

        long wait = infoResponse.getWait() - (before - after);
        logger.info("Les données d'initialisation sont traitées. Il reste " + wait + " millisecondes avant le début du tour 1");

        TimeUnit.MILLISECONDS.sleep(wait);
    }

    private long getDirections(int turn) throws Exception{
        if(turn > 1) {
            DirectionsResponse directionsResponse = gameClient.direction(turn);
            receiveDirections(directionsResponse, turn);
            return directionsResponse.getWait();
        }
        return 2000;
    }

    private long defineNextMove(int turn, long waitForNextTurn) throws Exception{
        int direction = this.choseDirection(turn, waitForNextTurn);
        MoveResponse moveResponse = gameClient.move(direction,turn);
        return moveResponse.getWait();
    }

    public void start() throws Exception{
        try {
            gameClient.connect();

            this.getInfo();

            int turn = 1;
            long waitForNextTurn = 2000;
            while (true){
                waitForNextTurn = this.getDirections(turn);

                waitForNextTurn = this.defineNextMove(turn, waitForNextTurn);

                TimeUnit.MILLISECONDS.sleep(waitForNextTurn);
                turn += 1;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    abstract void receiveInformation(InfoResponse infoResponse) throws Exception;
    abstract void receiveDirections(DirectionsResponse directionsResponse, int turn) throws Exception;
    abstract int choseDirection(int turn, long waitForNextTurn) throws Exception;

}
