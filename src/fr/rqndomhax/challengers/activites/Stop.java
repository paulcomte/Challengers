package fr.rqndomhax.challengers.activites;

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

        for(GameState games : GameState.values()) {

            if(setup.getGm().getGame().getGameState() != games) continue;
            
            setup.getTaskM().getRunnable().cancel();
            setup.getGm().getGame().setGameState(GameState.WAITING);
            setup.getGm().toLobby(true);
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.StoppedActivity")));
            return true;
        }

        return false;
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
