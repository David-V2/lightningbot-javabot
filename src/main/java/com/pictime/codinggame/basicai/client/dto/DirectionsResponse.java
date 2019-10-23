package com.pictime.codinggame.basicai.client.dto;

import java.util.List;

/**
 * Cette réponse du serveur (à récupérer chaque tour)
 * contient les directions prises par les joueurs
 * au tour précédent
 */
public class DirectionsResponse extends AbstractResponse {
    private List<Directions> directions;


    public List<Directions> getDirections() {
        return directions;
    }

    public void setDirections(List<Directions> directions) {
        this.directions = directions;
    }

}
