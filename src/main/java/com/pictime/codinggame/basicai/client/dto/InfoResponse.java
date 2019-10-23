package com.pictime.codinggame.basicai.client.dto;

import java.util.List;

/**
 * Cette réponse du serveur (à récupérer en début de partie)
 * contient les informations suivantes :
 * - Le nom de la partie
 * - La dimension de la zone de jeu
 * - Les positions de départ des joueurs
 */
public class InfoResponse extends AbstractResponse {
    protected String name;
    protected int dimensions;
    protected List<Positions> positions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public List<Positions> getPositions() {
        return positions;
    }

    public void setPositions(List<Positions> positions) {
        this.positions = positions;
    }
}
