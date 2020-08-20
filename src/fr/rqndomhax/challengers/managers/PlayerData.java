package fr.rqndomhax.challengers.managers;

import fr.rqndomhax.challengers.managers.team.TeamList;
import org.bukkit.Bukkit;

import java.util.UUID;

public class PlayerData {

    private String name;
    private UUID uuid;
    private TeamList team = null;
    private int playerPoints = 0;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        this.name = Bukkit.getOfflinePlayer(uuid).getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public TeamList getTeam() {
        return team;
    }

    public void setTeam(TeamList team) {
        this.team = team;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }
}
