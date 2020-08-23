package fr.rqndomhax.challengers.managers.team;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public enum TeamList {
    CYAN(ChatColor.AQUA, "Bleue", DyeColor.CYAN, 1, "blue"),
    RED(ChatColor.RED, "Rouge", DyeColor.RED, 3, "red"),
    GREEN(ChatColor.GREEN, "Verte", DyeColor.GREEN, 5, "green"),
    YELLOW(ChatColor.YELLOW, "Jaune", DyeColor.YELLOW, 7, "yellow");

    private final ChatColor chatColor;
    private final DyeColor dyeColor;
    private final String name;
    private final String path;
    private final int slot;

    TeamList(ChatColor chatColor, String name, DyeColor dyeColor, int slot, String path) {
        this.chatColor = chatColor;
        this.dyeColor = dyeColor;
        this.name = name;
        this.slot = slot;
        this.path = path;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public String getName() {
        return name;
    }

    public DyeColor getDyeColor() {
        return dyeColor;
    }

    public int getSlot() {
        return slot;
    }

    public String getPath() {
        return path;
    }
}
