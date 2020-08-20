package fr.rqndomhax.challengers.activites.firstactivity;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import fr.rqndomhax.challengers.managers.team.TeamList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.*;

public class FirstM {

    private final Setup setup;

    public FirstM(Setup setup) {
        this.setup = setup;
    }

    // New ArrayList of players to check if player has already chosen
    private final Set<UUID> pVoting = new HashSet<>();
    private final Set<UUID> VIP = new HashSet<>();

    // New HashMap of voted players
    private final HashMap<UUID, Integer> pBlue = new HashMap<>();
    private final HashMap<UUID, Integer> pRed = new HashMap<>();
    private final HashMap<UUID, Integer> pYellow = new HashMap<>();
    private final HashMap<UUID, Integer> pGreen = new HashMap<>();

    // New HashSet of bodyguards
    private final ArrayList<UUID> bBlue = new ArrayList<>();
    private final ArrayList<UUID> bRed = new ArrayList<>();
    private final ArrayList<UUID> bYellow = new ArrayList<>();
    private final ArrayList<UUID> bGreen = new ArrayList<>();

    // New HashSet of vip who chose bodyguards
    private final Set<UUID> bSelected = new HashSet<>();

    public void addVipVote(UUID user, int i) {

        switch(setup.getTm().getPlayerTeam(user).getTeam()) {
            case CYAN:
                pBlue.put(user, pBlue.getOrDefault(user, 0) + i);
                break;
            case RED:
                pRed.put(user, pRed.getOrDefault(user, 0) + i);
                break;
            case GREEN:
                pGreen.put(user, pGreen.getOrDefault(user, 0) + i);
                break;
            case YELLOW:
                pYellow.put(user, pYellow.getOrDefault(user, 0) + i);
                break;
            default: break;
        }
    }

    public void removeVipVote(UUID user, int i) {

        switch(setup.getTm().getPlayerTeam(user).getTeam()) {
            case CYAN:
                pBlue.put(user, pBlue.getOrDefault(user, 0) - i);
                break;
            case RED:
                pRed.put(user, pRed.getOrDefault(user, 0) - i);
                break;
            case GREEN:
                pGreen.put(user, pGreen.getOrDefault(user, 0) - i);
                break;
            case YELLOW:
                pYellow.put(user, pYellow.getOrDefault(user, 0) - i);
                break;
            default: break;
        }

    }

    public void setVipVote(UUID uuid, int i) {

        switch(setup.getTm().getPlayerTeam(uuid).getTeam()) {
            case CYAN:
                pBlue.put(uuid, i);
                break;
            case RED:
                pRed.put(uuid, i);
                break;
            case GREEN:
                pGreen.put(uuid, i);
                break;
            case YELLOW:
                pYellow.put(uuid, i);
                break;
            default: break;
        }

    }

    public HashMap<UUID, Integer> getVipVotes(UUID user) {
        switch (setup.getTm().getPlayerTeam(user).getTeam()) {
            case CYAN:
                return pBlue;

            case RED:
                return pRed;

            case YELLOW:
                return pYellow;

            case GREEN:
                return pGreen;

            default:
                return null;
        }
    }

    public int getVipPlayerVotes(UUID uuid) {
        return getVipVotes(uuid).get(uuid);
    }

    public void setVip(UUID uuid) {
        VIP.add(uuid);
    }

    public UUID getTeamVIP(TeamList team) {
        HashMap<UUID, Integer> votes = new HashMap<>();

        for (UUID users : VIP) {

            if(setup.getGm().getPlayerData(users).getTeam() != team) continue;

            votes.put(users, getVipVotes(users).getOrDefault(users, 0));
        }

        Map.Entry<UUID, Integer> entry = Collections.max(votes.entrySet(), Comparator.comparingInt(Map.Entry::getValue));

        List<UUID> uuids = new ArrayList<>();

        for (Map.Entry entry1 : votes.entrySet()) {
            UUID uuid = (UUID) entry1.getKey();
            Integer integer = (Integer) entry1.getValue();

            if (uuid.equals(entry.getKey())) continue;

            if (entry.getValue() == integer.intValue()) {
                UUID firstUUID = entry.getKey();
                uuids.add(firstUUID);
            }

        }

        if(uuids.isEmpty()) {
            return entry.getKey();
        }

        uuids.add(entry.getKey());
        Random r = new Random();
        int b = r.nextInt(uuids.size());
        return uuids.get(b);
    }

    public Set<UUID> getPVoting() {
        return pVoting;
    }

    public Set<UUID> getbSelected() {
        return bSelected;
    }

    public ArrayList<UUID> getbRed() {
        return bRed;
    }

    public ArrayList<UUID> getbBlue() {
        return bBlue;
    }

    public ArrayList<UUID> getbGreen() {
        return bGreen;
    }

    public ArrayList<UUID> getbYellow() {
        return bYellow;
    }
    
    public void teleport() {

        String[] redCoords = setup.getCore().getConfig().getString("Location.FirstAC.redLoc.coords").split(",");
        String[] blueCoords = setup.getCore().getConfig().getString("Location.FirstAC.blueLoc.coords").split(",");
        String[] greenCords = setup.getCore().getConfig().getString("Location.FirstAC.greenLoc.coords").split(",");
        String[] yellowCords = setup.getCore().getConfig().getString("Location.FirstAC.yellowLoc.coords").split(",");

        Location redLoc = new Location(Bukkit.getWorld(setup.getCore().getConfig().getString("Location.FirstAC.redLoc.WorldName")),
                Double.parseDouble(redCoords[0]),
                Double.parseDouble(redCoords[1]),
                Double.parseDouble(redCoords[2]));

        Location blueLoc = new Location(Bukkit.getWorld(setup.getCore().getConfig().getString("Location.FirstAC.blueLoc.WorldName")),
                Double.parseDouble(blueCoords[0]),
                Double.parseDouble(blueCoords[1]),
                Double.parseDouble(blueCoords[2]));

        Location greenLoc = new Location(Bukkit.getWorld(setup.getCore().getConfig().getString("Location.FirstAC.greenLoc.WorldName")),
                Double.parseDouble(greenCords[0]),
                Double.parseDouble(greenCords[1]),
                Double.parseDouble(greenCords[2]));

        Location yellowLoc = new Location(Bukkit.getWorld(setup.getCore().getConfig().getString("Location.FirstAC.yellowLoc.WorldName")),
                Double.parseDouble(yellowCords[0]),
                Double.parseDouble(yellowCords[1]),
                Double.parseDouble(yellowCords[2]));

        for(PlayerData playerData : setup.getGm().getGame().getPlayers()) {

            if(!Bukkit.getOfflinePlayer(playerData.getUuid()).isOnline()) continue;

            if(setup.getGm().getVipFromTeam(playerData.getTeam()) != playerData.getUuid() && !setup.getGm().getBodyGuardFromTeam(playerData.getTeam()).contains(playerData.getUuid())) continue;

            switch(playerData.getTeam()) {

                case RED:
                    Bukkit.getPlayer(playerData.getUuid()).teleport(redLoc);
                    break;
                case CYAN:
                    Bukkit.getPlayer(playerData.getUuid()).teleport(blueLoc);
                    break;
                case GREEN:
                    Bukkit.getPlayer(playerData.getUuid()).teleport(greenLoc);
                    break;
                case YELLOW:
                    Bukkit.getPlayer(playerData.getUuid()).teleport(yellowLoc);
                    break;
            }

            Bukkit.getPlayer(playerData.getUuid()).sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Teleported")));

        }
        
        
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }
}
