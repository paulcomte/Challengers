/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.managers.tasks;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;

public class FirstT extends BukkitRunnable {

    private final Setup setup;
    private final int timer;
    HashSet<PlayerData> players = new HashSet<>();
    int t = 0;

    public FirstT(Setup setup) {
        this.setup = setup;
        timer = setup.getCore().getConfig().getInt("Messages.FirstAC.Timer");
    }


    @Override
    public void run() {

        // TELEPORT
        if(t < 5) {
            Bukkit.broadcastMessage("Téléportation des joueurs dans " + (t-5) + " secondes");
        }

        if(t==5) {
            Bukkit.broadcastMessage("Téléportation des joueurs...");
            players = setup.getFm().init();
            setup.getFm().teleport(players);
            Bukkit.broadcastMessage("Début de l'épreuve dans 5 secondes");
        }

        // START
        if (t > 5 && t < 10) {
            Bukkit.broadcastMessage("Début dans " + (t-10) + " secondes");
        }

        if(t==10) {
            setup.getFm().start(players);
            Bukkit.broadcastMessage("L'épreuve a débuté ! Bonne chance :D");
        }

        // EXP BAR
        if(timer >= (10-t)) {

            for (PlayerData players : players) {

                if (Bukkit.getPlayer(players.getUuid()) == null) continue;

                Bukkit.getPlayer(players.getUuid()).setExp(timer - (10 - t));

            }

        }

        // TITLE
        switch(timer - (10-t)) {

            case 60: case 30: case 10: case 5: case 4: case 3: case 2: case 1:

                for (PlayerData players : players) {

                    if (Bukkit.getPlayer(players.getUuid()) == null) continue;

                    Bukkit.getPlayer(players.getUuid()).sendTitle(ChatColor.GOLD + String.valueOf(timer - (10-t)) + " secondes restantes", null, 1, 20, 5);

                }
                break;
        }

        t++;
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
