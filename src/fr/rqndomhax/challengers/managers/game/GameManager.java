package fr.rqndomhax.challengers.managers.game;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import fr.rqndomhax.challengers.managers.team.TeamList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.UUID;

public class GameManager {

    private final Game game = new Game();
    private final Setup setup;

    private UUID blueV;
    private UUID redV;
    private UUID yellowV;
    private UUID greenV;

    private ArrayList<UUID> blueBG = new ArrayList<>();
    private ArrayList<UUID> redBG = new ArrayList<>();
    private ArrayList<UUID> yellowBG = new ArrayList<>();
    private ArrayList<UUID> greenBG = new ArrayList<>();

    public GameManager(Setup setup) {
        this.setup = setup;
    }

    public Game getGame() {
        return game;
    }

    public PlayerData getPlayerData(UUID user) {

        for(PlayerData players : game.getPlayers()) {

            if(players.getUuid() != user) continue;

            return players;

        }

        return null;
    }

    public PlayerData getPlayerData(String name) {

        for(PlayerData players : game.getPlayers()) {

            if(!players.getName().equalsIgnoreCase(name)) continue;

            return players;

        }

        return null;
    }

    public void announceVIPFinished() {

        setup.getMm().VIPFinished();

        /*blueV = fm.getTeamVIP(TeamList.CYAN);
        redV = fm.getTeamVIP(TeamList.RED);
        yellowV = fm.getTeamVIP(TeamList.YELLOW);
        greenV = fm.getTeamVIP(TeamList.GREEN);

        mm.VIPChose(blueV, TeamList.CYAN);
        mm.VIPChose(redV, TeamList.RED);
        mm.VIPChose(yellowV, TeamList.YELLOW);
        mm.VIPChose(greenV, TeamList.GREEN);
         */

        redV = setup.getFm().getTeamVIP(TeamList.RED);
        setup.getMm().VIPChose(redV, TeamList.RED);

    }

    public void announceBGFinished() {

        redBG = setup.getFm().getbRed();
        blueBG = setup.getFm().getbBlue();
        greenBG = setup.getFm().getbGreen();
        yellowBG = setup.getFm().getbYellow();

        setup.getMm().BDChose(redBG.get(0), TeamList.RED, "premier");
        setup.getMm().BDChose(redBG.get(1), TeamList.RED, "second");

        setup.getMm().BDChose(blueBG.get(0), TeamList.CYAN, "premier");
        setup.getMm().BDChose(blueBG.get(1), TeamList.CYAN, "second");

        setup.getMm().BDChose(greenBG.get(0), TeamList.GREEN, "premier");
        setup.getMm().BDChose(greenBG.get(1), TeamList.CYAN, "second");

        setup.getMm().BDChose(yellowBG.get(0), TeamList.YELLOW, "premier");
        setup.getMm().BDChose(yellowBG.get(1), TeamList.YELLOW, "second");
    }

    public void toLobby(boolean hasBeenForceStopped) {
        String message = null;

        String[] coords = setup.getCore().getConfig().getString("Lobby.coords").split(",");

        Location location = new Location(Bukkit.getWorld(setup.getCore().getConfig().getString("Lobby.WorldName")),
                Double.parseDouble(coords[0]),
                Double.parseDouble(coords[1]),
                Double.parseDouble(coords[2]));

        if(hasBeenForceStopped)
             message = setup.getCore().getConfig().getString("Messages.ActivityStopped");

        for(PlayerData users : game.getPlayers()) {

            if(!Bukkit.getOfflinePlayer(users.getUuid()).isOnline()) continue;

            Bukkit.getPlayer(users.getUuid()).teleport(location);
            if(Bukkit.getPlayer(users.getUuid()).getGameMode() != GameMode.ADVENTURE)
                Bukkit.getPlayer(users.getUuid()).setGameMode(GameMode.ADVENTURE);

            if(hasBeenForceStopped)
                Bukkit.getPlayer(users.getUuid()).sendMessage(message);

        }

    }

    public UUID getVipFromTeam(TeamList t) {
        switch(t) {

            case RED:
                return redV;
            case CYAN:
                return blueV;
            case GREEN:
                return greenV;
            case YELLOW:
                return yellowV;

        }
        return null;
    }

    public ArrayList<UUID> getBodyGuardFromTeam(TeamList t) {
        switch(t) {

            case RED:
                return redBG;
            case CYAN:
                return blueBG;
            case GREEN:
                return greenBG;
            case YELLOW:
                return yellowBG;

        }
        return null;
    }

    public UUID getBlueV() {
        return blueV;
    }

    public UUID getRedV() {
        return redV;
    }

    public UUID getGreenV() {
        return greenV;
    }

    public UUID getYellowV() {
        return yellowV;
    }

    public ArrayList<UUID> getBlueBG() {
        return blueBG;
    }

    public ArrayList<UUID> getRedBG() {
        return redBG;
    }

    public ArrayList<UUID> getYellowBG() {
        return yellowBG;
    }

    public ArrayList<UUID> getGreenBG() {
        return greenBG;
    }
}