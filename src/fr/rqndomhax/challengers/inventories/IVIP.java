package fr.rqndomhax.challengers.inventories;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.inventoryapi.RInventory;
import fr.rqndomhax.challengers.managers.PlayerData;
import fr.rqndomhax.challengers.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Set;
import java.util.function.Consumer;

public class IVIP extends RInventory {

    private final Player owner;
    private final Setup setup;

    public IVIP(Player owner, Setup setup, Set<PlayerData> teamPlayers) {
        super(owner,  "Séléction d'un VIP", 9);

        this.owner = owner;
        this.setup = setup;

        for(PlayerData playerDatas : teamPlayers) {

            if(setup.getGm().getPlayerData(owner.getUniqueId()) == playerDatas) continue;

            this.addItem(new ItemBuilder(Material.SKULL_ITEM, 1, (short)3)
                    .setSkullOwner(playerDatas.getUuid())
                    .setName(ChatColor.GOLD + "Voter pour » " + playerDatas.getName())
                    .setLore(ChatColor.AQUA + "Nombre de votes » " + ChatColor.DARK_AQUA + setup.getVip().getVipVotes().getOrDefault(playerDatas, 0))
                    .toItemStack(), onClick(playerDatas));
        }

    }

    private Consumer<InventoryClickEvent> onClick(PlayerData playerData) {

        return e -> {

            e.setCancelled(true);

            setup.getVip().addVipVotes(playerData, 1);
            setup.getVip().getVotes().add(setup.getGm().getPlayerData(owner.getUniqueId()));

            owner.closeInventory();
            owner.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.VIP.VoteVip").replace("%player%", playerData.getName())));

        };

    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
