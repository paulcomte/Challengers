/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.inventories;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.team.TeamList;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ILocationConvoy {

    private final Setup setup;
    private final TeamList teamList;
    private final Player owner;
    private final Location location;

    public ILocationConvoy(Player owner, Setup setup, TeamList teamList, Location location) {
        this.setup = setup;
        this.teamList = teamList;
        this.owner = owner;
        this.location = location;
    }

    public void onClick() {
        setup.getCore().getConfig().set("Location.FirstAC." + teamList.getName() + ".WorldName", location.getWorld());
        setup.getCore().getConfig().set("Location.FirstAC." + teamList.getName() + ".coords", location.getX() + ", " + location.getY() + ", " + location.getZ());
        owner.sendMessage("Vous avez définis le point de spawn de l'équipe "
                + teamList.getChatColor() + teamList.getName() +
                " en x=" + location.getX() + " y=" + location.getY() + " z=" + location.getZ()
                + " pour l'activité 1.");
    }

}
