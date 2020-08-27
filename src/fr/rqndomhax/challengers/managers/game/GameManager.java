/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.managers.game;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import fr.rqndomhax.challengers.managers.game.activitymanagers.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class GameManager {

    private final Game game = new Game();
    private final Setup setup;

    public GameManager(Setup setup) {
        this.setup = setup;
    }

    public Game getGame() {
        return game;
    }

    public PlayerData getPlayerData(UUID user) {

        for (PlayerData players : game.getPlayers()) {

            if (players.getUuid() != user) continue;

            return players;

        }

        return null;
    }

    public PlayerData getPlayerData(String name) {

        for (PlayerData players : game.getPlayers()) {

            if (!players.getName().equalsIgnoreCase(name)) continue;

            return players;

        }

        return null;
    }

    public void toLobby(boolean hasBeenForceStopped) {
        String message = null;

        String[] coords = setup.getCore().getConfig().getString("Locations.Lobby.coords").split(",");

        Location location = new Location(Bukkit.getWorld(setup.getCore().getConfig().getString("Locations.Lobby.WorldName")),
                Double.parseDouble(coords[0]),
                Double.parseDouble(coords[1]),
                Double.parseDouble(coords[2]));

        if (hasBeenForceStopped)
            message = setup.getCore().getConfig().getString("Messages.ActivityStopped");
        else
            message = setup.getCore().getConfig().getString("Messages.ActivityFinished"); //TODO in config

        for (PlayerData playerDatas : game.getPlayers()) {

            if (Bukkit.getPlayer(playerDatas.getUuid()) == null) continue;

            Bukkit.getPlayer(playerDatas.getUuid()).teleport(location);
            if (Bukkit.getPlayer(playerDatas.getUuid()).getGameMode() != GameMode.ADVENTURE)
                Bukkit.getPlayer(playerDatas.getUuid()).setGameMode(GameMode.ADVENTURE);

            Bukkit.getPlayer(playerDatas.getUuid()).sendMessage(message);

        }

    }

    public void next(CommandSender sender) {

        switch (game.getGameState()) {

            case FIRSTAC:
                new FirstACM(setup, sender).onCommand();
                break;

            case SECONDAC:
                new SecondACM(setup, sender).onCommand();
                break;

            case THIRDAC:
                new ThirdACM(setup, sender).onCommand();

            case FOURTHAC:
                new FourthACM(setup, sender).onCommand();

            case FIFTHAC:
                new FifthACM(setup, sender).onCommand();

            default:
                break;

        }
    }
}