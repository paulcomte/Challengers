package fr.rqndomhax.challengers.activites.firstactivity;

import fr.rqndomhax.challengers.core.Setup;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class FirstAL implements Listener {

    private final Setup setup;

    public FirstAL(Setup setup) {
        this.setup = setup;
    }

    @EventHandler
    private void onInvClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if(!(e.getInventory().getName().equalsIgnoreCase("Séléction d'un VIP"))) return;

        if(e.getCurrentItem() == null) return;

        if(e.getCurrentItem().getType() == null) return;

        if(!(e.getCurrentItem().getType().equals(Material.SKULL_ITEM))) return;

        if(!(e.getCurrentItem().hasItemMeta())) return;

        String vote = e.getCurrentItem().getItemMeta().getDisplayName().substring(15);
        UUID voteP = null;

        for(UUID users : setup.getTm().getTeam(setup.getTm().getPlayerTeam(p.getUniqueId()).getTeam()).getMembers()) {

            if(Bukkit.getOfflinePlayer(users).getName().equalsIgnoreCase(vote)) {
                voteP = users;
            }

        }

        setup.getFm().addVipVote(voteP, 1);
        setup.getFm().getPVoting().add(p.getUniqueId());

        p.closeInventory();
        p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.VIP.VoteVip").replace("%player%", Bukkit.getOfflinePlayer(voteP).getName())));

    }

    @EventHandler
    private void onBodyGuardClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if(!(e.getInventory().getName().equalsIgnoreCase("Séléction de garde du corps"))) return;

        if(e.getCurrentItem() == null) return;

        if(e.getCurrentItem().getType() == null) return;

        if(!(e.getCurrentItem().getType().equals(Material.SKULL_ITEM))) return;

        if(!(e.getCurrentItem().hasItemMeta())) return;

        String selected = e.getCurrentItem().getItemMeta().getDisplayName().substring(15);
        UUID userSelected = null;

        for(UUID users : setup.getTm().getTeam(setup.getTm().getPlayerTeam(p.getUniqueId()).getTeam()).getMembers()) {

            if(Bukkit.getOfflinePlayer(users).getName().equalsIgnoreCase(selected)) {
                userSelected = users;
            }

        }

        setup.getFm().getbSelected().add(userSelected);

        p.closeInventory();
        p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.BodyGuard.Selected").replace("%player", Bukkit.getOfflinePlayer(userSelected).getName())));

    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }


}
