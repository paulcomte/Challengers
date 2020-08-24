/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.inventories;

import fr.rqndomhax.challengers.core.Setup;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ILocationMazeManager {

    private final Setup setup;
    private final Player player;
    private final Location location;
    private final int i;

    public ILocationMazeManager(Setup setup, Player player, Location location, int i) {
        this.setup = setup;
        this.player = player;
        this.location = location;
        this.i = i;
    }

    public void onClick() {

        switch(i) {

            case 1:
                setup.getCore().getConfig().set("Locations.SecondAC.FinishLine.coords", location.getX() + ", " + location.getY() + ", " + location.getZ());
                player.sendMessage("Vous avez définis l'emplacement de la ligne d'arrivée en x=" + location.getX() + " y=" + location + " z=" + location.getZ());
                break;

            case 2:
                setup.getCore().getConfig().set("Locations.SecondAC.FirstTP.coords", location.getX() + ", " + location.getY() + ", " + location.getZ());
                player.sendMessage("Vous avez définis l'emplacement de la première téléportation en x=" + location.getX() + " y=" + location + " z=" + location.getZ());
                break;

            case 3:
                setup.getCore().getConfig().set("Locations.SecondAC.SecondTP.coords", location.getX() + ", " + location.getY() + ", " + location.getZ());
                player.sendMessage("Vous avez définis l'emplacement de la seconde téléportation en x=" + location.getX() + " y=" + location + " z=" + location.getZ());
                break;

        }

    }

}
