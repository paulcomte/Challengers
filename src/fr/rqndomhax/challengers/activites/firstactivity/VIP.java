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

import java.util.*;

public class VIP {

    private final HashMap<TeamData, PlayerData> vips = new HashMap<>();
    private final HashSet<PlayerData> votes = new HashSet<>();
    private final HashMap<PlayerData, Integer> vipVotes = new HashMap<>();
    private final Set<TeamList> votedTeams = new HashSet<>();
    private boolean isVIPCooldownFinished = false;
    private final Setup setup;

    public VIP(Setup setup) {
        this.setup = setup;
    }

    public void addVipVotes(PlayerData playerData, int i) {

        for (TeamData teamDatas : setup.getTm().getTeam().getTeams()) {

            if (teamDatas != playerData.getTeamData()) continue;

            vipVotes.put(playerData, vipVotes.getOrDefault(playerData, 0) + i);

        }
    }

    public void removeVipVotes(PlayerData playerData, int i) {

        for (TeamData teamDatas : setup.getTm().getTeam().getTeams()) {

            if (teamDatas != playerData.getTeamData()) continue;

            vipVotes.put(playerData, vipVotes.getOrDefault(playerData, 0) - i);

        }

    }

    public void setVipVotes(PlayerData playerData, int i) {

        for (TeamData teamDatas : setup.getTm().getTeam().getTeams()) {

            if (teamDatas != playerData.getTeamData()) continue;

            vipVotes.put(playerData, i);

        }

    }

    public void setVip(PlayerData playerData) {
        vips.put(playerData.getTeamData(), playerData);
    }

    public void setTeamVips() {
        HashMap<PlayerData, Integer> datas = new HashMap<>();

        for (TeamData teamData : setup.getTm().getTeam().getTeams()) {

            for (Map.Entry<PlayerData, Integer> map : vipVotes.entrySet()) {

                if (map.getKey().getTeamData() != teamData) continue;

                datas.put(map.getKey(), map.getValue());

            }

            Map.Entry<PlayerData, Integer> entry = Collections.max(datas.entrySet(), Comparator.comparingInt(Map.Entry::getValue));

            List<PlayerData> pDatas = getPlayerDatas(datas, entry);

            if (pDatas.isEmpty()) {
                vips.put(entry.getKey().getTeamData(), entry.getKey());
            }

            pDatas.add(entry.getKey());
            Random r = new Random();
            int b = r.nextInt(pDatas.size());
            vips.put(pDatas.get(b).getTeamData(), pDatas.get(b));
        }

        datas.clear();

    }

    public HashMap<TeamData, PlayerData> getVips() {
        return vips;
    }

    public HashSet<PlayerData> getVotes() {
        return votes;
    }

    public void setVIPCooldownFinished(boolean VIPCooldownFinished) {
        isVIPCooldownFinished = VIPCooldownFinished;
    }

    public HashMap<PlayerData, Integer> getVipVotes() {
        return vipVotes;
    }

    private List<PlayerData> getPlayerDatas(HashMap<PlayerData, Integer> datas, Map.Entry<PlayerData, Integer> entry) {

        ArrayList<PlayerData> pData = new ArrayList<>();

        for (Map.Entry entry1 : datas.entrySet()) {

                PlayerData playerData = (PlayerData) entry1.getKey();
                Integer integer = (Integer) entry1.getValue();

                if (playerData.equals(entry.getKey())) continue;

                if (entry.getValue() == integer.intValue()) {
                    PlayerData playerData1 = entry.getKey();
                    pData.add(playerData1);
                }

            }
            return pData;
        }

    public void announceVIPFinished() {

        setTeamVips();

        setup.getMm().VIPFinished();

        for(TeamData teams : setup.getTm().getTeam().getTeams()) {
            setup.getMm().VIPChose(getVips().get(teams), teams.getTeam());
        }

    }

    public boolean isVIPCooldownFinished() {
        return isVIPCooldownFinished;
    }

    public Set<TeamList> getVotedTeams() {
        return votedTeams;
    }

    public Set<TeamList> getMissingTeams() {

        Set<TeamList> missingVoteds = new HashSet<>();

        for(TeamList teams : TeamList.values()) {
            if (!votedTeams.contains(teams)) missingVoteds.add(teams);
        }

        return missingVoteds;

    }
}
