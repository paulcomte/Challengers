/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.inventories;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.inventoryapi.RInventory;
import fr.rqndomhax.challengers.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public class ILocationMaze extends RInventory  {

    private final Setup setup;
    private final Player owner;
    private final Location location;

    public ILocationMaze(Player owner, Setup setup, Location location) {
        super(owner, "Séléctionnez la changement", 9);
        this.owner = owner;
        this.setup = setup;
        this.location = location;

        this.addItem(new ItemBuilder(Material.PURPUR_DOUBLE_SLAB).setName(ChatColor.AQUA + "Emplacement de la ligne d'arrivée").toItemStack(), onClick(1));
        this.addItem(new ItemBuilder(Material.GOLD_PLATE).setName(ChatColor.YELLOW + "Premier emplacement de téléportation").toItemStack(), onClick(2));
        this.addItem(new ItemBuilder(Material.GOLD_PLATE).setName(ChatColor.GOLD + "Second emplacement de téléportation").toItemStack(), onClick(2));

    }

    private Consumer<InventoryClickEvent> onClick(int i) {

        return e -> {
          new ILocationMazeManager(setup, owner, location, i).onClick();
        };

    }

}
