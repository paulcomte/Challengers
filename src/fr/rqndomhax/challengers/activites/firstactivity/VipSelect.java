package fr.rqndomhax.challengers.activites.firstactivity;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.inventories.IVIP;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

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

        if(setup.getVip().isVIPCooldownFinished()) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.VIP.AlreadyFinished")));
        }

        PlayerData playerData = setup.getGm().getPlayerData(p.getUniqueId());


        if(playerData == null) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NotPlaying")));
            return false;
        }

        if(!(setup.getTm().hasTeam(playerData))) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.Teams.NeedToBeInTeam")));
            return false;
        }

        if (setup.getVip().getVotes().contains(playerData)) {
            p.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.VIP.AlreadyVoted")));
            return false;
        }

        // Add players head
        Set<PlayerData> playerTeam = playerData.getTeamData().getMembers();

        if(playerTeam.isEmpty()) {
            p.sendMessage("Erreur interne, l'équipe est vide ! Veuillez contacter le développeur: §a_Paul#6918");
            return false;
        }

        new IVIP(p, setup, playerTeam).open();

        return true;
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
