package fr.rqndomhax.challengers.activites;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayersManager {

    private final CommandSender sender;
    private final Setup setup;
    private final String[] args;

    public PlayersManager(Setup setup, CommandSender sender, String[] args) {
        this.sender = sender;
        this.setup = setup;
        this.args = args;
    }


    public boolean onAdd() {

        if(args.length != 3) {
            ActivityCommands.showHelp(sender, 3);
            return false;
        }

        String targetName = args[2];

        if(Bukkit.getPlayer(targetName) == null) {
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NotValid")));
            return false;
        }

        Player target = Bukkit.getPlayer(targetName);

        if(setup.getGm().getPlayerData(target.getUniqueId()) != null) {
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.AlreadyPlaying").replace("%player%", target.getName())));
            return false;
        }

        setup.getGm().getGame().getPlayers().add(new PlayerData(target.getUniqueId()));
        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NewPlayer").replace("%player%", target.getName())));
        target.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NewPlayerToMessages")));

        return true;
    }

    public boolean onRemove() {

        if(args.length != 3) {
            ActivityCommands.showHelp(sender, 3);
            return false;
        }

        String targetName = args[2];

        if(Bukkit.getPlayer(targetName) == null) {
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.NotValid")));
            sender.sendMessage("WARNING : SI LE JOUEUR S'EST DECONNECTE ET QUE VOUS SOUHAITEZ LE SUPPRIMER, VEUILLEZ EFFECTUER LA COMMANDE SUIVANTE");
            sender.sendMessage("WARNING : /ac warningRemove player");
            return false;
        }

        Player target = Bukkit.getPlayer(targetName);

        if(setup.getGm().getPlayerData(target.getUniqueId()) == null) {
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.AlreadyNotPlaying").replace("%player%", target.getName())));
            return false;
        }

        setup.getGm().getGame().getPlayers().remove(setup.getGm().getPlayerData(target.getUniqueId()));
        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.RemovedPlayer").replace("%player%", target.getName())));
        target.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.RemovedPlayerToMessage")));

        return true;
    }

    public boolean onWarningRemove() {
        if (args.length != 3) {
            ActivityCommands.showHelp(sender, 3);
            return false;
        }

        String targetName = args[2];

        PlayerData playerData = setup.getGm().getPlayerData(targetName);

        if (playerData == null) {
            sender.sendMessage("WARNING: LE JOUEUR SPECIFIE N'EXISTE PAS DANS LA LISTE DES JOUEURS");
            sender.sendMessage("WARNING: VEUILLEZ VERIFIER SON PSEUDO");
            sender.sendMessage("WARNING: SI VOUS PENSEZ QU'IL Y A UN SOUCIS VEUILLEZ CONTACTER §e_Paul#6918");
            return false;
        }

        setup.getGm().getGame().getPlayers().remove(playerData);
        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.RemovedPlayer").replace("%player%", playerData.getName())));
        return true;
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
