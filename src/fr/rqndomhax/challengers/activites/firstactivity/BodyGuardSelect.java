package fr.rqndomhax.challengers.activites.firstactivity;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Set;
import java.util.UUID;

public class BodyGuardSelect implements CommandExecutor {

    private final Setup setup;

    public BodyGuardSelect(Setup setup) {
        this.setup = setup;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(setup.getCore().getConfig().getString("Messages.NotInstanced"));
            return false;
        }

        Player p = (Player) sender;


        if (setup.getGm().getGame().isBodyGuardCooldownFinished()) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.BodyGuard.FinishedBG")));
        }

        if (!(setup.getTm().hasTeam(p.getUniqueId()))) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.Teams.NeedToBeInTeam")));
            return false;
        }

        if (setup.getFm().getbSelected().contains(p.getUniqueId())) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.BodyGuard.AlreadySelected")));
            return false;
        }

        Inventory pInv = Bukkit.createInventory(null, 9, "Séléction de garde du corps");

        // Add players head
        Set<UUID> playersteam = setup.getTm().getTeam(setup.getGm().getPlayerData(p.getUniqueId()).getTeam()).getMembers();

        if (playersteam.isEmpty()) {
            p.sendMessage("Erreur interne, l'équipe est vide ! Veuillez contacter le développeur: §a_Paul#6918");
            return false;
        }

        for (UUID players : playersteam) {
            pInv.addItem(new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3)
                    .setSkullOwner(players)
                    .setName(ChatColor.GOLD + "Choisir » " + Bukkit.getOfflinePlayer(players).getName())
                    .toItemStack());
        }

        p.openInventory(pInv);

        return false;
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }
}
