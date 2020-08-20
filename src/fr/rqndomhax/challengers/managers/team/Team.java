package fr.rqndomhax.challengers.managers.team;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Team {

    private final Set<TeamData> teams = new HashSet<>();
    private final HashMap<UUID, TeamList> playerTeams = new HashMap<>();
    private final HashMap<TeamList, Integer> teamPoints = new HashMap<>();
    private final HashMap<UUID, Integer> playerPoints = new HashMap<>();
    private final HashMap<TeamList, Integer> teamSize = new HashMap<>();

    public Set<TeamData> getTeams() {
        return teams;
    }

    public HashMap<UUID, TeamList> getPlayerTeams() {
        return playerTeams;
    }

    public HashMap<TeamList, Integer> getTeamSize() {
        return teamSize;
    }
}

