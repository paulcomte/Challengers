package fr.rqndomhax.challengers.activites.firstactivity;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import fr.rqndomhax.challengers.managers.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class FirstM {

    private final Setup setup;

    public FirstM(Setup setup) {
        this.setup = setup;
    }
    
    public void teleport() {

        String[] redCoords = setup.getCore().getConfig().getString("Locations.FirstAC.redLoc.coords").replaceAll(" ", "").split(",");
        String[] blueCoords = setup.getCore().getConfig().getString("Locations.FirstAC.blueLoc.coords").replaceAll(" ", "").split(",");
        String[] greenCords = setup.getCore().getConfig().getString("Locations.FirstAC.greenLoc.coords").replaceAll(" ", "").split(",");
        String[] yellowCords = setup.getCore().getConfig().getString("Locations.FirstAC.yellowLoc.coords").replaceAll(" ", "")  .split(",");

        Location redLoc = new Location(Bukkit.getWorld(setup.getCore().getConfig().getString("Locations.FirstAC.redLoc.WorldName")),
                Integer.parseInt(redCoords[0]),
                Integer.parseInt(redCoords[1]),
                Integer.parseInt(redCoords[2]));

        Location blueLoc = new Location(Bukkit.getWorld(setup.getCore().getConfig().getString("Locations.FirstAC.blueLoc.WorldName")),
                Integer.parseInt(blueCoords[0]),
                Integer.parseInt(blueCoords[1]),
                Integer.parseInt(blueCoords[2]));

        Location greenLoc = new Location(Bukkit.getWorld(setup.getCore().getConfig().getString("Locations.FirstAC.greenLoc.WorldName")),
                Integer.parseInt(greenCords[0]),
                Integer.parseInt(greenCords[1]),
                Integer.parseInt(greenCords[2]));

        Location yellowLoc = new Location(Bukkit.getWorld(setup.getCore().getConfig().getString("Locations.FirstAC.yellowLoc.WorldName")),
                Integer.parseInt(yellowCords[0]),
                Integer.parseInt(yellowCords[1]),
                Integer.parseInt(yellowCords[2]));


        for(PlayerData playerDatas : GameManager.INSTANCE.getGame().getPlayers()) {

            if(Bukkit.getPlayer(playerDatas.getUuid()) == null) continue;

            if(setup.getVip().getVips().get(playerDatas.getTeamData()) != playerDatas && !setup.getbG().getBodyguards().contains(playerDatas)) continue;

            switch(playerDatas.getTeamData().getTeam()) {

                case RED:
                    Bukkit.getPlayer(playerDatas.getUuid()).teleport(redLoc);
                    break;
                case CYAN:
                    Bukkit.getPlayer(playerDatas.getUuid()).teleport(blueLoc);
                    break;
                case GREEN:
                    Bukkit.getPlayer(playerDatas.getUuid()).teleport(greenLoc);
                    break;
                case YELLOW:
                    Bukkit.getPlayer(playerDatas.getUuid()).teleport(yellowLoc);
                    break;
            }

            Bukkit.getPlayer(playerDatas.getUuid()).sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Teleported")));

        }
        
        
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }
}
