package com.pictime.codinggame.basicai.game;

import com.pictime.codinggame.basicai.client.exception.*;

public class GameErrorHandler {
    public static void throwError(int error) throws Exception{
        switch (error){
            case 0:
                throw new InvalidRequestPathException();
            case 1:
                throw new InvalidParameterException();
            case 2:
                throw new RequestPhaseOverException();
            case 3:
                throw new RequestPhaseNotInProgressException();
            case 100:
                throw new GameFullException();
            case 101:
                throw new TokenAlreadyUsedException();
            case 200:
                throw new YouAreTheWinnerException();
            case 201:
                throw new YourBotIsDeadException();
        }
    }
}
