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

public class IBodyGuard extends RInventory {

    private final Player owner;
    private final Setup setup;

    public IBodyGuard(Player owner, Setup setup, Set<PlayerData> teamPlayers) {
        super(owner, "Séléction de garde du corps", 9);
        this.owner = owner;
        this.setup = setup;

        for (PlayerData playerDatas : teamPlayers) {

            //if(setup.getGm().getPlayerData(owner.getUniqueId()) == playerDatas) continue;

            this.addItem(new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3)
                    .setSkullOwner(playerDatas.getUuid())
                    .setName(ChatColor.GOLD + "Choisir » " + playerDatas.getName())
                    .toItemStack(), onClick(playerDatas));
        }
    }

    private Consumer<InventoryClickEvent> onClick(PlayerData playerData) {

        return e -> {

            e.setCancelled(true);

            if(setup.getbG().getBodyguards().contains(playerData)) {
                owner.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.BodyGuard.AlreadySelectedAsBG").replace("%player%", playerData.getName())));
                return;
            }

            setup.getbG().getBodyguards().add(playerData);

            owner.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.BodyGuard.Selected").replace("%player%", playerData.getName())));

            if(setup.getbG().getBodyguards().size() == 2) {
                setup.getbG().getVotes().add(playerData);
                owner.closeInventory();
            }

        };

    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
