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
import fr.rqndomhax.challengers.managers.team.TeamList;
import fr.rqndomhax.challengers.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public class ILocationTeam extends RInventory {

    private final Setup setup;
    private final Player owner;
    private final Activites activites;
    private final Location location;

    public ILocationTeam(Player owner, Setup setup, Activites activites, Location location) {
        super(owner, "Choisissez une équipe", 9);
        this.setup = setup;
        this.owner = owner;
        this.activites = activites;
        this.location = location;

        for(TeamList teamList : TeamList.values()) {

            this.setItem(teamList.getSlot(), new ItemBuilder(Material.BANNER)
                    .setBannerColor(teamList.getDyeColor())
                    .setName(teamList.getChatColor() + teamList.getName())
                    .toItemStack(), onClick(teamList));
        }

    }

    private Consumer<InventoryClickEvent> onClick(TeamList teamList) {

        return e -> {

            activites.getCustomInventory().accept(setup, owner, location, teamList);
        };

    }

}
