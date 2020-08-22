package fr.rqndomhax.challengers.commands;

import fr.rqndomhax.challengers.core.Setup;
import org.bukkit.command.CommandSender;

public class Pause {

    private final Setup setup;
    private final CommandSender sender;

    public Pause(Setup setup, CommandSender sender) {
        this.setup = setup;
        this.sender = sender;
    }


    public boolean onCommand() {

        return false;
    }

}
