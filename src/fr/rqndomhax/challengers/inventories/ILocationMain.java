/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.inventories;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.inventoryapi.RInventory;
import fr.rqndomhax.challengers.managers.Activites;
import fr.rqndomhax.challengers.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public class ILocationMain extends RInventory {

    private final Player owner;
    private final Setup setup;
    private final Location location;

    public ILocationMain(Player owner, Setup setup, Location location) {
        super(owner, "Choisissez une activité", 9);
        this.setup = setup;
        this.owner = owner;
        this.location = location;

        for(Activites activites :  Activites.values()) {
                this.addItem(new ItemBuilder(activites.getMaterial()).setName(activites.getName()).toItemStack(), onClick(activites));
        }


    }

    public Consumer<InventoryClickEvent> onClick(Activites activites) {

        return e -> {

            if(activites.equals(Activites.MAZE)) {

                activites.getCustomConsumer().accept(setup, owner, location, null);

            }

            new ILocationTeam(owner, setup, activites, location).open();

        };

    }

}
