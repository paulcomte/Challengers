package fr.rqndomhax.challengers.managers;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.inventories.CustomConsumer;
import fr.rqndomhax.challengers.inventories.ILocationConvoy;
import fr.rqndomhax.challengers.inventories.ILocationMaze;
import fr.rqndomhax.challengers.managers.team.TeamList;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public enum Activites {

    CONVOY(Material.RAILS, ChatColor.GOLD + "Escorte", (setup, player, location, teamList) -> {
        new ILocationConvoy(player, setup, teamList, location);
    }),
    MAZE(Material.QUARTZ_BLOCK, ChatColor.DARK_PURPLE + "Labyrinthe", (setup, player, location, teamList) -> {
        new ILocationMaze(player, setup, location);
    }),
    PARKOUR(Material.NETHER_BRICK_STAIRS, ChatColor.RED + "Parcours", (setup, player, location, teamList) -> {

    }),
    BUILD(Material.DIRT, ChatColor.DARK_RED + "Construction", (setup, player, location, teamList) -> {

    }),
    ARENAMOB(Material.MONSTER_EGG, ChatColor.GREEN + "Arène infernale", (setup, player, location, teamList) -> {

    });

    private final Material material;
    private final String name;
    private final CustomConsumer<Setup, Player, Location, TeamList> customConsumer;

    Activites(Material material, String name, CustomConsumer<Setup, Player, Location, TeamList> customConsumer) {
        this.material = material;
        this.name = name;
        this.customConsumer = customConsumer;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public CustomConsumer<Setup, Player, Location, TeamList> getCustomConsumer() {
        return customConsumer;
    }
}
