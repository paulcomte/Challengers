/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.activites.firstactivity;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import fr.rqndomhax.challengers.managers.team.TeamData;
import fr.rqndomhax.challengers.managers.team.TeamList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bodyguard {

    private final Setup setup;

    public Bodyguard(Setup setup) {
        this.setup = setup;
    }

    private final Set<PlayerData> bodyguards = new HashSet<>();
    private final Set<TeamList> votedTeams = new HashSet<>();
    private final Set<PlayerData> votes = new HashSet<>();
    private boolean isBodyGuardCooldownFinished = false;

    public Set<PlayerData> getBodyguards() {
        return bodyguards;
    }

    public boolean isBodyGuardCooldownFinished() {
        return isBodyGuardCooldownFinished;
    }

    public void setBodyGuardCooldownFinished(boolean bodyGuardCooldownFinished) {
        isBodyGuardCooldownFinished = bodyGuardCooldownFinished;
    }

    public List<PlayerData> getTeamBodyGuards(TeamList teamList) {

        List<PlayerData> list = new ArrayList<>();

        for(PlayerData bg : bodyguards) {

            if(bg.getTeamData().getTeam() != teamList) continue;

            list.add(bg);

        }

        return list;
    }

    public Set<PlayerData> getVotes() {
        return votes;
    }

    public void announceBGFinished() {
        for(TeamData teams : setup.getTm().getTeam().getTeams()) {

            setup.getMm().BDChose(getTeamBodyGuards(teams.getTeam()).get(0), teams.getTeam(), "premier");
            setup.getMm().BDChose(getTeamBodyGuards(teams.getTeam()).get(1), teams.getTeam(), "second");

        }
    }

    public Set<TeamList> getVotedTeams() {
        return votedTeams;
    }

    public Set<TeamList> getMissingTeams() {

        Set<TeamList> missingTeams = new HashSet<>();

        for(TeamList teams : TeamList.values()) {

            if(!votedTeams.contains(teams)) missingTeams.add(teams);

        }

        return missingTeams;
    }
}
