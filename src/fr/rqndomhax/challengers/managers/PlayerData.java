package fr.rqndomhax.challengers.managers;

import fr.rqndomhax.challengers.managers.team.TeamList;

import java.util.UUID;

public class PlayerData {

    private String name;
    private UUID uuid;
    private TeamList team;
    private int playerpoints = 0;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
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

    public int getPlayerpoints() {
        return playerpoints;
    }

    public void setPlayerpoints(int playerpoints) {
        this.playerpoints = playerpoints;
    }
}
