/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.managers.game;

import fr.rqndomhax.challengers.managers.PlayerData;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private GameState gameState;
    private final Set<PlayerData> players = new HashSet<>();

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Set<PlayerData> getPlayers() {
        return players;
    }
}
