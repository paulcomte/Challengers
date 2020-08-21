package fr.rqndomhax.challengers.managers.game;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;

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

        for(PlayerData players : game.getPlayers()) {

            if(players.getUuid() != user) continue;

            return players;

        }

        return null;
    }

    public PlayerData getPlayerData(String name) {

        for(PlayerData players : game.getPlayers()) {

            if(!players.getName().equalsIgnoreCase(name)) continue;

            return players;

        }

        return null;
    }

    public void toLobby(boolean hasBeenForceStopped) {
        String message = null;

        String[] coords = setup.getCore().getConfig().getString("Lobby.coords").split(",");

        Location location = new Location(Bukkit.getWorld(setup.getCore().getConfig().getString("Lobby.WorldName")),
                Double.parseDouble(coords[0]),
                Double.parseDouble(coords[1]),
                Double.parseDouble(coords[2]));

        if(hasBeenForceStopped)
             message = setup.getCore().getConfig().getString("Messages.ActivityStopped");

        for(PlayerData users : game.getPlayers()) {

            if(!Bukkit.getOfflinePlayer(users.getUuid()).isOnline()) continue;

            Bukkit.getPlayer(users.getUuid()).teleport(location);
            if(Bukkit.getPlayer(users.getUuid()).getGameMode() != GameMode.ADVENTURE)
                Bukkit.getPlayer(users.getUuid()).setGameMode(GameMode.ADVENTURE);

            if(hasBeenForceStopped)
                Bukkit.getPlayer(users.getUuid()).sendMessage(message);

        }

    }
}