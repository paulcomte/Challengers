/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.activites.managers;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.team.TeamList;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SpawnManager {

    private final Setup setup;
    private final Player player;
    private final Location location;
    private final TeamList teamList;
    private final int number;

    public SpawnManager(Setup setup, Player player, Location location, TeamList teamList, int number) {
        this.setup = setup;
        this.player = player;
        this.location = location;
        this.teamList = teamList;
        this.number = number;
    }

    public void onEvent() {



    }

}
