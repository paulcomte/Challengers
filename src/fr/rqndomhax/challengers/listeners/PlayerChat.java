package fr.rqndomhax.challengers.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent e) {
        System.out.println(e.getFormat());;
    }

}
