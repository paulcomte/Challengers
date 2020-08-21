package fr.rqndomhax.challengers.managers.team;

import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.Bukkit;


public class TeamManager {

    private final Team team = new Team();

    // Method to add the player to a team.
    public void addToTeam(PlayerData playerData, TeamList team){
        if (hasTeam(playerData)) removeFromTeam(playerData);

        playerData.setTeamData(getTeam(team));

        playerData.getTeamData().getMembers().add(playerData);

        playerData.getTeamData().addTeamSize(1);

        if(Bukkit.getPlayer(playerData.getUuid()) != null) {
            Bukkit.getPlayer(playerData.getUuid()).setDisplayName(team.getChatColor() + "[" + team.getName().toUpperCase() + "] " + playerData.getName());
        }

    }

    // Checking if time size is exceeded
    public boolean isAbleToJoin(TeamList team) {
        return getTeam(team).getTeamMaxSize() > getTeam(team).getTeamSize();
    }

    // Removing the player from a team
    public void removeFromTeam(PlayerData playerData){

        playerData.getTeamData().getMembers().remove(playerData);

        playerData.getTeamData().removeTeamSize(1);

        playerData.setTeamData(null);

        if(Bukkit.getPlayer(playerData.getUuid()) != null)
            Bukkit.getPlayer(playerData.getUuid()).setDisplayName(playerData.getName());

    }

    // Check if player is in team
    public boolean hasTeam(PlayerData playerData) {
        return playerData.getTeamData() != null;
    }

    // Get team from teamlist
    public TeamData getTeam(TeamList team) {

        for(TeamData teams : this.team.getTeams()) {

            if(team != teams.getTeam()) continue;

            return teams;

        }

        return null;
    }

    // Add player points and player's team
    public void autoAddPoints(PlayerData playerData, int i) {
        playerData.setPlayerPoints(playerData.getPlayerPoints() + i);
        playerData.getTeamData().setTeamPoints(playerData.getTeamData().getTeamPoints() + i);
    }

    // Remove player points and player's team
    public void autoRemovePoints(PlayerData playerData, int i) {
        playerData.setPlayerPoints(playerData.getPlayerPoints() - i);
        playerData.getTeamData().setTeamPoints(playerData.getTeamData().getTeamPoints() - i);
    }

    // Remove player points and player's team
    public void autoSetPoints(PlayerData playerData, int i) {
        playerData.getTeamData().setTeamPoints((playerData.getTeamData().getTeamPoints() - playerData.getPlayerPoints())+ i);
        playerData.setPlayerPoints(i);
    }

    // Add team points
    public void addTeamPoints(TeamList team, int i) {
        getTeam(team).setTeamPoints(getTeam(team).getTeamPoints() + i);
    }

    // Remove team points
    public void removeTeampoints(TeamList team, int i) {
        getTeam(team).setTeamPoints(getTeam(team).getTeamPoints() - i);
    }

    public Team getTeam() {
        return team;
    }
}
