package fr.rqndomhax.challengers.listeners;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import fr.rqndomhax.challengers.managers.game.GameManager;
import fr.rqndomhax.challengers.managers.team.TeamData;
import fr.rqndomhax.challengers.managers.team.TeamList;
import fr.rqndomhax.challengers.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

public class TeamListener implements Listener {

    private final Setup setup;

    public TeamListener(Setup setup) {
        this.setup = setup;
    }

    @EventHandler
    public void onBannerInteract(PlayerInteractEvent e) {

        // Return if not correct item
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getItem() == null) return;
        if (e.getItem().getType() == null) return;
        if (e.getItem().getType() != Material.BANNER) return;
        if (!e.getItem().getItemMeta().getDisplayName().substring(2).equalsIgnoreCase("Séléction d'équipe")) return;

        if(GameManager.INSTANCE.getPlayerData(e.getPlayer().getUniqueId()) == null) {
            e.getPlayer().sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NotPlaying")));
            return;
        }

        e.setCancelled(true);

        // New inventory
        Inventory teamSelect = Bukkit.createInventory(null, 9, "• Menu d'équipe •");

        // Set items in inventory
        for(TeamData teamDatas : setup.getTm().getTeam().getTeams()) {

            if(teamDatas.getMembers().contains(GameManager.INSTANCE.getPlayerData(e.getPlayer().getUniqueId()))) {
                teamSelect.setItem(teamDatas.getTeam().getSlot(), new ItemBuilder(Material.BANNER).setBannerColor(teamDatas.getTeam().getDyeColor())
                        .setName(teamDatas.getTeam().getChatColor() + "Equipe " + teamDatas.getTeam().getName().toLowerCase())
                        .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1).hideEnchants()
                        .setLore(setLore(teamDatas.getTeam()))
                        .toItemStack());
            } else {
                teamSelect.setItem(teamDatas.getTeam().getSlot(), new ItemBuilder(Material.BANNER).setBannerColor(teamDatas.getTeam().getDyeColor())
                        .setName(teamDatas.getTeam().getChatColor() + "Equipe " + teamDatas.getTeam().getName().toLowerCase())
                        .setLore(setLore(teamDatas.getTeam()))
                        .toItemStack());
            }

        }

        // Open the teamSelect inventory
        e.getPlayer().openInventory(teamSelect);
    }

    @EventHandler
    public void onTeamInventoryInteract(InventoryClickEvent e) {

        // Return if not correct inventory
        if(e.getCurrentItem() == null) return;
        if(e.getCurrentItem().getType() != Material.BANNER) return;
        if(!(e.getCurrentItem().hasItemMeta())) return;
        if(!(e.getClickedInventory().getName().equalsIgnoreCase("• Menu d'équipe •"))) return;

        ItemMeta clickedM = e.getCurrentItem().getItemMeta();

        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);

        for(TeamList teams : TeamList.values()) {
            if(e.getCurrentItem().getItemMeta().getDisplayName().substring(2).equalsIgnoreCase("Equipe " + teams.getName()))
                autoAction(p, clickedM, teams);
        }
    }

    private void autoAction(Player p, ItemMeta iM, TeamList team) {
        if (!(iM.getDisplayName().substring(2).equalsIgnoreCase("Equipe " + team.getName()))) return;

        if (iM.hasEnchants()) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.Teams.AlreadyInTeam")
                    .replace("%teamcolor%", team.getChatColor() + "")
                    .replace("%team%", team.getName())));
            return;
        }

        if (!(setup.getTm().isAbleToJoin(team))) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.Teams.TeamIsFull")
                    .replace("%teamcolor%", team.getChatColor() + "")
                    .replace("%team%", team.getName())));
            return;
        }

        p.closeInventory();
        setup.getTm().addToTeam(GameManager.INSTANCE.getPlayerData(p.getUniqueId()), team);
        p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.Teams.JoinedTheTeam")
                .replace("%teamcolor%", team.getChatColor() + "")
                .replace("%team%", team.getName())));
    }

    private String setLore(TeamList team) {

        StringBuilder sb = new StringBuilder();

        for(PlayerData playerDatas : setup.getTm().getTeam(team).getMembers()) {

            if(playerDatas.getName() == null) continue;

            sb.append("\n");
            sb.append(team.getChatColor()).append("• ").append(playerDatas.getName());

        }

        return sb.toString();
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
