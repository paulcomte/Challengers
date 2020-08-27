/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.commands;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.game.GameState;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Stop {

    private final Setup setup;
    private final CommandSender sender;

    public Stop(Setup setup, CommandSender sender) {
        this.setup = setup;
        this.sender = sender;
    }

    public boolean onCommand() {

        if(setup.getGm().getGame().getGameState() == GameState.WAITING) {
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NotRunning")));
            return false;
        }

        if(setup.getTaskM().getRunnable() != null) setup.getTaskM().getRunnable().cancel();

        setup.getGm().getGame().setGameState(GameState.WAITING);
        setup.getGm().toLobby(true);
        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.StoppedActivity")));
        return true;
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
