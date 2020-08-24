/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.managers;

import fr.rqndomhax.challengers.core.Core;
import fr.rqndomhax.challengers.managers.team.TeamList;
import org.bukkit.Bukkit;

public class MessageManagers {

    private final Core core;

    public MessageManagers(Core core) {
        this.core = core;
    }

    public void VIPFinished() {
        Bukkit.broadcastMessage(this.core.getConfig().getString("Messages.FirstAC.VIP.FinishedVIP"));
    }

    public void VIPChose(PlayerData playerData, TeamList tl) {
        Bukkit.broadcastMessage(this.core.getConfig().getString("Messages.FirstAC.VIP.AnnounceWhoVIP").replace("%teamcolor%", tl.getChatColor() + "").replace("%team%", tl.getName()).replace("%player%", playerData.getName()));
    }

    public void BDChose(PlayerData playerData, TeamList tl, String number) {
        Bukkit.broadcastMessage(this.core.getConfig().getString("Messages.FirstAC.BodyGuard.AnnounceWho.BG").replace("%number%", number).replace("%teamcolor%", tl.getChatColor() + "").replace("%team%", tl.getName()).replace("%player%", playerData.getName()));
    }


}
