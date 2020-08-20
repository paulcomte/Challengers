package fr.rqndomhax.challengers.managers.team;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.Bukkit;

import java.util.UUID;


public class TeamManager {

    private final Team team = new Team();
    private final Setup setup;

    public TeamManager(Setup setup) {
        this.setup = setup;
    }

    // Auto set a player to a team
    public void autoSetTeam(UUID user, TeamList team) {
        addToTeam(user, team);
    }

    // Method to add the player to a team.
    public void addToTeam(UUID user, TeamList team){
        if (hasTeam(user)) removeFromTeam(user);
        setup.getGm().getPlayerData(user).setTeam(team);
        getTeam(team).addTeamSize(1);
        if(Bukkit.getOfflinePlayer(user).isOnline())
            Bukkit.getPlayer(user).setDisplayName(team.getChatColor() + Bukkit.getPlayer(user).getName());
    }

    // Checking if time size is exceeded
    public boolean isAbleToJoin(TeamList team) {
        return getTeam(team).getTeamMaxSize() > getTeam(team).getTeamSize();
    }

    // Removing the player from a team
    public void removeFromTeam(UUID user){
        PlayerData player = setup.getGm().getPlayerData(user);

        if(Bukkit.getOfflinePlayer(user).isOnline())
            Bukkit.getPlayer(user).setDisplayName(player.getName());

        getPlayerTeam(user).removeTeamSize(1);

        player.setTeam(null);

    }

    // Getting the player's team
    public TeamData getPlayerTeam(UUID user){
        for(TeamData teams : team.getTeams()) {

            if(setup.getGm().getPlayerData(user).getTeam() != teams.getTeam()) continue;

            return teams;

        }
        return null;
    }

    // Check if player is in team
    public boolean hasTeam(UUID user) {
        return setup.getGm().getPlayerData(user).getTeam() != null;
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
    public void autoAddPoints(UUID user, int i) {
        setup.getGm().getPlayerData(user).setPlayerPoints(setup.getGm().getPlayerData(user).getPlayerPoints() + i);
        getPlayerTeam(user).setTeamPoints(getPlayerTeam(user).getTeamPoints() + i);
    }

    // Remove player points and player's team
    public void autoRemovePoints(UUID user, int i) {
        setup.getGm().getPlayerData(user).setPlayerPoints(setup.getGm().getPlayerData(user).getPlayerPoints() - i);
        getPlayerTeam(user).setTeamPoints(getPlayerTeam(user).getTeamPoints() - i);
    }

    // Remove player points and player's team
    public void autoSetPoints(UUID user, int i) {
        setup.getGm().getPlayerData(user).setPlayerPoints(i);
        getPlayerTeam(user).setTeamPoints((getPlayerTeam(user).getTeamPoints() - setup.getGm().getPlayerData(user).getPlayerPoints()) + i);
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
