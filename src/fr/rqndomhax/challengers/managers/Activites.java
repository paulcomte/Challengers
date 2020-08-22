package fr.rqndomhax.challengers.managers;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Activites {

    CONVOY(Material.RAILS, ChatColor.GOLD + "Escorte"),
    MAZE(Material.QUARTZ_BLOCK, ChatColor.DARK_PURPLE + "Labyrinthe"),
    PARKOUR(Material.NETHER_BRICK_STAIRS, ChatColor.RED + "Parcours"),
    BUILD(Material.DIRT, ChatColor.DARK_RED + "Construction"),
    ARENAMOB(Material.MONSTER_EGG, ChatColor.GREEN + "Arène infernale");

    private final Material material;
    private final String name;

    Activites(Material material, String name) {
        this.material = material;
        this.name = name;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }
}
