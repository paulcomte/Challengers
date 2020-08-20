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

public class VipSelect implements CommandExecutor {

    private final Setup setup;

    public VipSelect(Setup setup) {
        this.setup = setup;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("Seul un joueur peut choisir un VIP");
            return false;
        }

        Player p = (Player)sender;


        if(setup.getGm().getGame().isVIPCooldownFinished()) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.VIP.AlreadyFinished")));
        }

        if(!(setup.getTm().hasTeam(p.getUniqueId()))) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.Team.NeedToBeInTeam")));
            return false;
        }

        if (setup.getFm().getPVoting().contains(p.getUniqueId())) {
            p.sendMessage(setup.getCore().getConfig().getString("Messages.FirstAC.VIP.AlreadyVoted"));
            return false;
        }

        Inventory pInv = Bukkit.createInventory(null, 9, "Séléction d'un VIP");

        // Add players head
        Set<UUID> playerTeam = setup.getTm().getTeam(setup.getGm().getPlayerData(p.getUniqueId()).getTeam()).getMembers();

        if(playerTeam.isEmpty()) {
            p.sendMessage("Erreur interne, l'équipe est vide ! Veuillez contacter le développeur: §a_Paul#6918");
            return false;
        }

        for(UUID players : playerTeam) {
            pInv.addItem(new ItemBuilder(Material.SKULL_ITEM, 1, (short)3)
                    .setSkullOwner(players)
                    .setName(ChatColor.GOLD + "Voter pour » " + Bukkit.getOfflinePlayer(players).getName())
                    .setLore(ChatColor.AQUA + "Nombre de votes » " + ChatColor.DARK_AQUA + setup.getFm().getVipVotes(players).getOrDefault(players, 0))
                    .toItemStack());
        }

        p.openInventory(pInv);

        return false;
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
