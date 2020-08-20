package fr.rqndomhax.challengers.activites.firstactivity;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

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
        PlayerData voteP = null;

        for(PlayerData playerDatas : setup.getGm().getPlayerData(p.getUniqueId()).getTeamData().getMembers()) {

            if(playerDatas.getName().equalsIgnoreCase(vote)) {
                voteP = playerDatas;
            }

        }

        setup.getFm().addVipVote(, 1);
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

        e.setCancelled(true);

        String selected = e.getCurrentItem().getItemMeta().getDisplayName().substring(12);
        PlayerData userSelected = null;

        PlayerData playerData = setup.getGm().getPlayerData(p.getUniqueId());

        for(PlayerData playerDatas : playerData.getTeamData().getMembers()) {

            /*if(Bukkit.getOfflinePlayer(users).getName().equalsIgnoreCase(p.getName())) {
                p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.CannotVoteForYourself")));
                return;
            }
             */

            if(playerDatas.getName().equalsIgnoreCase(selected)) {

                if(setup.getbG().getBodyguards().contains(setup.getGm().getPlayerData(selected))) {
                    p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.BodyGuard.AlreadySelectedAsBG").replace("%player%", playerDatas.getName())));
                    return;
                }

                userSelected = playerDatas;
            }

        }

        if(userSelected == null) {
            p.sendMessage("WARNING : UUID IS NULL, PLEASE CONTACT THE DEVELOPER §e_Paul#6918 §fIF THAT HAPPENS AGAIN !");
            return;
        }

        setup.getbG().getBodyguards().put(playerData.getTeamData(), userSelected);

        p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.BodyGuard.Selected").replace("%player%", userSelected.getName())));

        if(setup.getbG().getBodyguards().size() == 2) {
            setup.getbG().getVotes().add(playerData);
            p.closeInventory();
        }

    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }


}
