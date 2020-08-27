/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.commands;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Start {

    private final Setup setup;
    private final CommandSender sender;
    private final String[] args;

    public Start(Setup setup, CommandSender sender, String[] args) {
        this.setup = setup;
        this.sender = sender;
        this.args = args;
    }

    public boolean onCommand() {

        if(args.length != 2) {
            ActivityCommands.showHelp(sender, 1);
            return false;
        }

        int number = Integer.parseInt(args[1]);

        if(number > 5 || number < 1) {
            ActivityCommands.showHelp(sender, 1);
            return false;
        }

        for (GameState gs : GameState.values()) {

            if (number != gs.getGameInt()) continue;

            if (setup.getGm().getGame().getGameState() != GameState.WAITING) {
                sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.AlreadyRunning")));
                return false;
            }

            /*try {
                setup.getTaskM().start(gs.getCustomClass());
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
             */

            Bukkit.broadcastMessage(this.a(setup.getCore().getConfig().getString(gs.getPath())));
            setup.getGm().getGame().setGameState(gs);
            return true;

        }

        ActivityCommands.showHelp(sender, 1);
        return false;
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }
}
