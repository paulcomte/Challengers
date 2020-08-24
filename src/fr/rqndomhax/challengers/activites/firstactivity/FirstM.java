/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.activites.firstactivity;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.Activites;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;

public class FirstM {

    private final Setup setup;

    public FirstM(Setup setup) {
        this.setup = setup;
    }

    public HashSet<PlayerData> init() {

        HashSet<PlayerData> players = new HashSet<>();

        for(PlayerData playerDatas : setup.getGm().getGame().getPlayers()) {

            if(setup.getVip().getVips().get(playerDatas.getTeamData()) != playerDatas && !setup.getbG().getBodyguards().contains(playerDatas)) continue;

            players.add(playerDatas);

        }

        return players;
    }
    
    public void teleport(HashSet<PlayerData> players) {

        for(PlayerData playerDatas : players) {

            if(Bukkit.getPlayer(playerDatas.getUuid()) == null) continue;

            Bukkit.getPlayer(playerDatas.getUuid()).teleport(playerDatas.getTeamData().getLocations().get(Activites.CONVOY));

            Bukkit.getPlayer(playerDatas.getUuid()).sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Teleported")));

            Bukkit.getPlayer(playerDatas.getUuid()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 9999, 5));
            Bukkit.getPlayer(playerDatas.getUuid()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 9999, 5));
        }
    }

    public void start(HashSet<PlayerData> players) {

        for(PlayerData playerDatas : players) {

            if(Bukkit.getPlayer(playerDatas.getUuid()) == null) continue;

            Bukkit.getPlayer(playerDatas.getUuid()).getActivePotionEffects().clear();
        }

    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }
}
