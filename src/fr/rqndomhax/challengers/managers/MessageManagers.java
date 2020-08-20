package fr.rqndomhax.challengers.managers;

import fr.rqndomhax.challengers.core.Core;
import fr.rqndomhax.challengers.managers.team.TeamList;
import org.bukkit.Bukkit;

import java.util.UUID;

public class MessageManagers {

    private final Core core;

    public MessageManagers(Core core) {
        this.core = core;
    }

    public void VIPFinished() {
        Bukkit.broadcastMessage(this.core.getConfig().getString("Messages.FirstAC.VIP.FinishedVIP"));
    }

    public void VIPChose(UUID user, TeamList tl) {
        Bukkit.broadcastMessage(this.core.getConfig().getString("Messages.FirstAC.VIP.AnnounceWhoVIP").replace("%teamcolor%", tl.getChatColor() + "").replace("%team%", tl.getName()).replace("%player%", Bukkit.getOfflinePlayer(user).getName()));
    }

    public void BDChose(UUID user, TeamList tl, String number) {
        Bukkit.broadcastMessage(this.core.getConfig().getString("Messages.FirstAC.BodyGuard.AnnounceWho.BG").replace("%number%", number).replace("%teamcolor%", tl.getChatColor() + "").replace("%team%", tl.getName()).replace("%player%", Bukkit.getOfflinePlayer(user).getName()));
    }


}
