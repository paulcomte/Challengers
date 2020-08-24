/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.activites.firstactivity;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.inventories.IBodyGuard;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class BodyGuardSelect implements CommandExecutor {

    private final Setup setup;

    public BodyGuardSelect(Setup setup) {
        this.setup = setup;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(setup.getCore().getConfig().getString("Messages.NotInstanced"));
            return false;
        }

        Player p = (Player) sender;

        if (setup.getbG().isBodyGuardCooldownFinished()) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.BodyGuard.FinishedBG")));
        }

        PlayerData playerData = setup.getGm().getPlayerData(p.getUniqueId());

        if(playerData == null) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NotPlaying")));
            return false;
        }

        if (!(setup.getTm().hasTeam(playerData))) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.Teams.NeedToBeInTeam")));
            return false;
        }

        if(!setup.getVip().isVIPCooldownFinished()) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.BodyGuard.NotSelectingBodyguards")));
            return false;
        }

        if(setup.getVip().getVips().getOrDefault(playerData.getTeamData(), null) != playerData) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.BodyGuard.NotTheVIP")));
            return false;
        }

        if (setup.getbG().getVotes().contains(playerData)) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.BodyGuard.AlreadySelected")));
            return false;
        }

        // Add players head
        Set<PlayerData> teamPlayers = playerData.getTeamData().getMembers();

        if (teamPlayers.isEmpty()) {
            p.sendMessage("Erreur interne, l'équipe est vide ! Veuillez contacter le développeur: §a_Paul#6918");
            return false;
        }

        new IBodyGuard(p, setup, teamPlayers).open();

        return true;
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }
}
