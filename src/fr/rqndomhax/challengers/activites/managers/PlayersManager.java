/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.activites.managers;

import fr.rqndomhax.challengers.commands.ActivityCommands;
import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayersManager {

    private final CommandSender sender;
    private final Setup setup;
    private final String[] args;

    public PlayersManager(Setup setup, CommandSender sender, String[] args) {
        this.sender = sender;
        this.setup = setup;
        this.args = args;
    }


    public boolean onAdd() {

        if(args.length != 2) {
            ActivityCommands.showHelp(sender, 3);
            return false;
        }

        String targetName = args[1];

        if(Bukkit.getPlayer(targetName) == null) {
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NotValid")));
            return false;
        }

        Player target = Bukkit.getPlayer(targetName);

        if(setup.getGm().getPlayerData(target.getUniqueId()) != null) {
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.AlreadyPlaying").replace("%player%", target.getName())));
            return false;
        }

        setup.getGm().getGame().getPlayers().add(new PlayerData(target.getUniqueId()));
        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NewPlayer").replace("%player%", target.getName())));
        target.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NewPlayerToMessage")));

        return true;
    }

    public boolean onRemove() {

        if(args.length != 2) {
            ActivityCommands.showHelp(sender, 3);
            return false;
        }

        String targetName = args[1];

        PlayerData playerData = Bukkit.getPlayer(targetName) == null ? setup.getGm().getPlayerData(targetName) : setup.getGm().getPlayerData(Bukkit.getPlayer(targetName).getUniqueId());

        if(playerData == null) {
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.AlreadyNotPlaying")));
            sender.sendMessage("§4WARNING : SI VOUS PENSEZ QUE C'EST UNE ERREURE VEUILLEZ CONTACTER LE DEVELOPPEUR §e_Paul#6918");
            return false;
        }

        targetName = playerData.getName();

        UUID uuid = playerData.getUuid();

        setup.getGm().getGame().getPlayers().remove(playerData);
        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.RemovedPlayer")
                .replace("%player%", targetName)));

        if(Bukkit.getPlayer(uuid) != null) {
            Bukkit.getPlayer(uuid).sendMessage(this.a(setup.getCore().getConfig().getString("Messages.RemovedPlayerToMessage")));
        }
        return true;

    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
