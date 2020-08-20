package fr.rqndomhax.challengers.activites;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class PlayersPointsManager {

    private final Setup setup;
    private final CommandSender sender;
    private final String[] args;

    public PlayersPointsManager(Setup setup, CommandSender sender, String[] args) {
        this.setup = setup;
        this.sender = sender;
        this.args = args;
    }

    public boolean onSetPoints() {

        if(args.length != 3) {
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

        if(0 < Integer.parseInt(args[2]) || Integer.parseInt(args[2]) > 2147483645) {
            sender.sendMessage("WARNING: VEUILLEZ SPECIFIER UN NOMBRE CORRECT");
            sender.sendMessage("WARNING: LE NOMBRE DOIT ETRE COMPRIS ENTRE 0 & 2 147 483 644");
            return false;
        }

        int points = Integer.parseInt(args[2]);

        setup.getTm().autoAddPoints(playerData.getUuid(), points);

        return false;
    }

    public boolean onRemovePoints() {

        if(args.length != 3) {
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

        if(0 < Integer.parseInt(args[2]) || Integer.parseInt(args[2]) > 2147483645) {
            sender.sendMessage("WARNING: VEUILLEZ SPECIFIER UN NOMBRE CORRECT");
            sender.sendMessage("WARNING: LE NOMBRE DOIT ETRE COMPRIS ENTRE 0 & 2 147 483 644");
            return false;
        }

        int points = Integer.parseInt(args[2]);

        setup.getTm().autoRemovePoints(playerData.getUuid(), points);

        return false;

    }

    public boolean onAddPoints() {

        if(args.length != 3) {
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

        if(0 < Integer.parseInt(args[2]) || Integer.parseInt(args[2]) > 2147483645) {
            sender.sendMessage("WARNING: VEUILLEZ SPECIFIER UN NOMBRE CORRECT");
            sender.sendMessage("WARNING: LE NOMBRE DOIT ETRE COMPRIS ENTRE 0 & 2 147 483 644");
            return false;
        }

        int points = Integer.parseInt(args[2]);

        setup.getTm().autoAddPoints(playerData.getUuid(), points);
        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.SuccessAddPlayerPoints")));


        return false;
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
