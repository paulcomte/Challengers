/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.listeners;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final Setup setup;

    public PlayerListener(Setup setup) {
        this.setup = setup;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(setup.getCore(), () -> setup.getTeamScoreboard().newTeamScoreboard(e.getPlayer()), 20);
    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent e) {

        if(e.getPlayer().hasPermission(setup.getCore().getConfig().getString("Permissions.StaffChatFormat"))) {
            e.setFormat(ChatColor.DARK_RED + "[STAFF] " + e.getPlayer().getName() + " » " + ChatColor.RED + e.getMessage());
            return;
        }

        PlayerData playerData = setup.getGm().getPlayerData(e.getPlayer().getUniqueId());

        if (playerData == null) {
            e.setFormat(ChatColor.GRAY + e.getPlayer().getName() + ChatColor.GOLD + " » " + ChatColor.WHITE + e.getMessage());
            return;
        }

        if(playerData.getTeamData() == null) {
            e.setFormat(ChatColor.GRAY + e.getPlayer().getName() + ChatColor.GOLD + " » " + ChatColor.WHITE + e.getMessage());
            return;
        }

        e.setFormat(ChatColor.GRAY + e.getPlayer().getName() + playerData.getTeamData().getTeam().getChatColor() + " [" + playerData.getTeamData().getTeam().getName().toUpperCase() + "]" + ChatColor.GOLD + " » " + playerData.getTeamData().getTeam().getChatColor() + e.getMessage());

        // %1$s == player name
        // %2$s == message
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        setup.getTeamScoreboard().removeTeamScoreboard(e.getPlayer());
    }

}
