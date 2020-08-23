package fr.rqndomhax.challengers.commands;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocationCommand {

    private final Setup setup;
    private final CommandSender sender;
    private final String[] args;

    public LocationCommand(Setup setup, CommandSender sender, String[] args) {
        this.setup = setup;
        this.sender = sender;
        this.args = args;
    }

    public boolean onCommand() {

        if(!(sender instanceof Player)) {
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NotAPlayer")));
            return false;
        }

        Player p = (Player) sender;

        if(!(p.hasPermission(setup.getCore().getConfig().getString("Permissions.ActivityManager")))) {
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NotAllowed")));
            return false;
        }

        if(p.getInventory().getSize() >= 36) {
            sender.sendMessage("Votre inventaire est complet !");
            return false;
        }

        p.getInventory().addItem(new ItemBuilder(Material.ARROW).setName(ChatColor.GOLD + "Gestionnaire des emplacements").toItemStack());
        p.sendMessage("Vous avez reçu un item vous permettant de gérer l'emplacement de la configuration");
        p.sendMessage("Cliquez droit, sur un block pour que l'inventaire de la gestion des emplacements s'ouvre");
        return true;

    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
