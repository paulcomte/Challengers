package fr.rqndomhax.challengers.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent e) {
        // %1$s == player name
        // %2$s == message
        e.setFormat("%1$s §e» §f%2$s");
    }

}
