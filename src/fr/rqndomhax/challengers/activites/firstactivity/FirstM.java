package fr.rqndomhax.challengers.activites.firstactivity;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.Activites;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class FirstM {

    private final Setup setup;

    public FirstM(Setup setup) {
        this.setup = setup;
    }
    
    public void teleport() {

        for(PlayerData playerDatas : setup.getGm().getGame().getPlayers()) {

            if(Bukkit.getPlayer(playerDatas.getUuid()) == null) continue;

            if(setup.getVip().getVips().get(playerDatas.getTeamData()) != playerDatas && !setup.getbG().getBodyguards().contains(playerDatas)) continue;

            Bukkit.getPlayer(playerDatas.getUuid()).teleport(playerDatas.getTeamData().getLocations().get(Activites.CONVOY));

            Bukkit.getPlayer(playerDatas.getUuid()).sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Teleported")));

        }
        
        
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }
}
