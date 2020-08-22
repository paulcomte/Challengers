package fr.rqndomhax.challengers.commands;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.game.GameManager;
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

        if(GameManager.INSTANCE.getGame().getGameState() == GameState.WAITING) {
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NotRunning")));
            return false;
        }

        for(GameState games : GameState.values()) {

            if(GameManager.INSTANCE.getGame().getGameState() != games) continue;
            
            setup.getTaskM().getRunnable().cancel();
            GameManager.INSTANCE.getGame().setGameState(GameState.WAITING);
            GameManager.INSTANCE.toLobby(true);
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.StoppedActivity")));
            return true;
        }

        return false;
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
