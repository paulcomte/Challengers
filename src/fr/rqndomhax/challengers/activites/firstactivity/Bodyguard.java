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

public class Bodyguard {

    private final Setup setup;

    public Bodyguard(Setup setup) {
        this.setup = setup;
    }

    private final HashSet<PlayerData> bodyguards = new HashSet<>();
    private final HashSet<PlayerData> votes = new HashSet<>();
    private boolean isBodyGuardCooldownFinished = false;

    public HashSet<PlayerData> getBodyguards() {
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

    public HashSet<PlayerData> getVotes() {
        return votes;
    }

    public void announceBGFinished() {
        for(TeamData teams : setup.getTm().getTeam().getTeams()) {

            setup.getMm().BDChose(getTeamBodyGuards(teams.getTeam()).get(0), teams.getTeam(), "premier");
            setup.getMm().BDChose(getTeamBodyGuards(teams.getTeam()).get(1), teams.getTeam(), "second");

        }
    }
}
