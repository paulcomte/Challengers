/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.managers.team;

import fr.rqndomhax.challengers.managers.Activites;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TeamData {

    private final TeamList team;
    private Set<PlayerData> members = new HashSet<>();
    private int teamPoints;
    private final int teamMaxSize;
    HashMap<Activites, Location> locations;
    private int teamSize = 0;

    public TeamData(TeamList team, int teamMaxSize, HashMap<Activites, Location> locations) {
        this.team = team;
        this.teamMaxSize = teamMaxSize;
        this.locations = locations;
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

    public HashMap<Activites, Location> getLocations() {
        return locations;
    }
}
