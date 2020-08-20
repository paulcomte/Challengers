package fr.rqndomhax.challengers.managers.team;

import fr.rqndomhax.challengers.managers.PlayerData;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TeamData {

    private final TeamList team;
    private Set<PlayerData> members = new HashSet<>();
    private int teamPoints;
    private final int teamMaxSize;
    private int teamSize = 0;

    public TeamData(TeamList team, int teamMaxSize) {
        this.team = team;
        this.teamMaxSize = teamMaxSize;
    }

    public UUID getVip() {
        return vip;
    }

    public void setVip(UUID vip) {
        this.vip = vip;
    }

    public TeamList getTeam() {
        return team;
    }

    public Set<PlayerData> getMembers() {
        return members;
    }

    public void setMembers(Set<PlayerData> members) {
        this.members = members;
    }

    public int getTeamPoints() {
        return teamPoints;
    }

    public void setTeamPoints(int teamPoints) {
        this.teamPoints = teamPoints;
    }

    public int getTeamMaxSize() {
        return teamMaxSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void removeTeamSize(int i) {
        teamSize = teamSize - i;
    }

    public void addTeamSize(int i) {
        teamSize = teamSize - i;
    }

    public Set<UUID> getBodyGuards() {
        return bodyGuards;
    }
}
