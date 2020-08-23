package fr.rqndomhax.challengers.commands;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.game.GameState;
import org.bukkit.command.CommandSender;

public class Next {

    private final Setup setup;
    private final CommandSender sender;
    private final String[] args;

    public Next(Setup setup, CommandSender sender, String[] args) {
        this.setup = setup;
        this.sender = sender;
        this.args = args;
    }


    public boolean onCommand() {


        for(GameState gameStates : GameState.values()) {

            if(setup.getGm().getGame().getGameState() != gameStates) continue;

            gameStates.next();

        }

        return true;
    }


}
