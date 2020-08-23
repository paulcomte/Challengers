package fr.rqndomhax.challengers.listeners;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.inventories.ILocationMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class LocationManager implements Listener {

    private final Setup setup;

    public LocationManager(Setup setup) {
        this.setup = setup;
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        ItemStack item = e.getItem();

        if(item.getType() != Material.ARROW) return;

        if(!item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Gestionnaire des emplacements")) return;

        new ILocationMain(p, setup, e.getClickedBlock().getLocation());

    }

}
