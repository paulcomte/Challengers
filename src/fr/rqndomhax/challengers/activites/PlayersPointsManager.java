package fr.rqndomhax.challengers.activites;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import org.bukkit.Bukkit;
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

    private PlayerData onPoints() {

        if(args.length != 3) {
            ActivityCommands.showHelp(sender, 3);
            return null;
        }

        String targetName = args[1];

        PlayerData playerData = Bukkit.getPlayer(targetName) == null ? setup.getGm().getPlayerData(targetName) : setup.getGm().getPlayerData(Bukkit.getPlayer(targetName).getUniqueId());

        if (playerData == null) {
            sender.sendMessage("WARNING: LE JOUEUR SPECIFIE N'EXISTE PAS DANS LA LISTE DES JOUEURS");
            sender.sendMessage("WARNING: VEUILLEZ VERIFIER SON PSEUDO");
            sender.sendMessage("WARNING: SI VOUS PENSEZ QU'IL Y A UN SOUCIS VEUILLEZ CONTACTER LE DEVELOPPEUR §e_Paul#6918");
            return null;
        }

        if(Integer.parseInt(args[2]) < 0 || Integer.parseInt(args[2]) > 2147483645) {
            sender.sendMessage("WARNING: VEUILLEZ SPECIFIER UN NOMBRE CORRECT");
            sender.sendMessage("WARNING: LE NOMBRE DOIT ETRE COMPRIS ENTRE 0 & 2 147 483 644");
            sender.sendMessage("WARNING: SI VOUS PENSEZ QU'IL Y A UN SOUCIS VEUILLEZ CONTACTER LE DEVELOPPEUR §e_Paul#6918");
            return null;
        }

        if(playerData.getTeamData() == null) {
            sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.Teams.PlayerNeedToBeInTeam").replace("%player%", playerData.getName())));
            sender.sendMessage("WARNING: SI VOUS PENSEZ QU'IL Y A UN SOUCIS VEUILLEZ CONTACTER LE DEVELOPPEUR §e_Paul#6918");
            return null;
        }

        return playerData;
    }

    public boolean onSetPoints() {

        if(onPoints() == null)
            return false;

        int points = Integer.parseInt(args[2]);

        PlayerData playerData = onPoints();

        sender.sendMessage(this.a(autoReplace(setup.getCore().getConfig().getString("Messages.OldPoints"), playerData)));

        setup.getTm().autoSetPoints(playerData, points);

        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.GettingPoints")));

        sender.sendMessage(this.a(autoReplace(setup.getCore().getConfig().getString("Messages.NewPoints"), playerData)));

        sender.sendMessage(this.a(autoReplace2(setup.getCore().getConfig().getString("Messages.SetPlayerPoints"), playerData)));

        tryMessage(setup.getCore().getConfig().getString("Messages.SetPointsToMessage"), playerData);

        return true;
    }

    public boolean onRemovePoints() {

        if(onPoints() == null)
            return false;

        int points = Integer.parseInt(args[2]);

        PlayerData playerData = onPoints();


        sender.sendMessage(this.a(autoReplace(setup.getCore().getConfig().getString("Messages.OldPoints"), playerData)));

        setup.getTm().autoRemovePoints(playerData, points);

        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.GettingPoints")));

        sender.sendMessage(this.a(autoReplace(setup.getCore().getConfig().getString("Messages.NewPoints"), playerData)));

        sender.sendMessage(this.a(autoReplace2(setup.getCore().getConfig().getString("Messages.RemovedPlayerPoints"), playerData)));

        tryMessage(setup.getCore().getConfig().getString("Messages.RemovedPointsToMessage"), playerData);

        return true;

    }

    public boolean onAddPoints() {

        if(onPoints() == null)
            return false;

        int points = Integer.parseInt(args[2]);

        PlayerData playerData = onPoints();

        sender.sendMessage(this.a(autoReplace(setup.getCore().getConfig().getString("Messages.OldPoints"), playerData)));

        setup.getTm().autoAddPoints(playerData, points);

        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.GettingPoints")));

        sender.sendMessage(this.a(autoReplace(setup.getCore().getConfig().getString("Messages.NewPoints"), playerData)));

        sender.sendMessage(this.a(autoReplace2(setup.getCore().getConfig().getString("Messages.AddedPlayerPoints"), playerData)));

        tryMessage(setup.getCore().getConfig().getString("Messages.AddedPointsToMessage"), playerData);

        return true;
    }

    private String autoReplace(String message, PlayerData playerData) {

        return message
                .replace("%team%", playerData.getTeamData().getTeam().getName())
                .replace("%teamcolor%", playerData.getTeamData().getTeam().getChatColor() + "")
                .replace("%player%", playerData.getName())
                .replace("%playerpoints%", String.valueOf(playerData.getPlayerPoints()))
                .replace("%teampoints%", String.valueOf(playerData.getTeamData().getTeamPoints()));

    }

    private String autoReplace2(String message, PlayerData playerData) {

        return message
                .replace("%player%", playerData.getName())
                .replace("%teamcolor%", playerData.getTeamData().getTeam().getChatColor() + "")
                .replace("%points%", String.valueOf(playerData.getPlayerPoints()));

    }

    private void tryMessage(String message, PlayerData playerData) {
        try{
            Bukkit.getPlayer(playerData.getUuid()).sendMessage(this.a(message.replace("%playerpoints%", String.valueOf(playerData.getPlayerPoints()))));
        } catch (Exception ignored){}
    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
