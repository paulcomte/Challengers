/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.commands;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.game.GameState;
import org.bukkit.command.CommandSender;

public class Next {

    private final Setup setup;
    private final CommandSender sender;

    public Next(Setup setup, CommandSender sender) {
        this.setup = setup;
        this.sender = sender;
    }


    public boolean onCommand() {

        if(setup.getGm().getGame().getGameState() == GameState.WAITING) {
            sender.sendMessage("Aucune activité n'a débuté, veuillez en démarrer une à l'aide de /ac start <activity>");
            return false;
        }

        for(GameState gameStates : GameState.values()) {

            if(setup.getGm().getGame().getGameState() != gameStates) continue;

            setup.getGm().next(sender);

        }

        return true;
    }


}
