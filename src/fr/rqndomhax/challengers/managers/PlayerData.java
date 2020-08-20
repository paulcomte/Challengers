package fr.rqndomhax.challengers.managers;

import fr.rqndomhax.challengers.managers.team.TeamData;
import org.bukkit.Bukkit;

import java.util.UUID;

public class PlayerData {

    private String name;
    private UUID uuid;
    private TeamData teamData = null;
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

    public TeamData getTeamData() {
        return teamData;
    }

    public void setTeamData(TeamData teamData) {
        this.teamData = teamData;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }
}
