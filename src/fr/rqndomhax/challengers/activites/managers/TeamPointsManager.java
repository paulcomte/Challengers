package fr.rqndomhax.challengers.activites.managers;

import fr.rqndomhax.challengers.commands.ActivityCommands;
import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.PlayerData;
import fr.rqndomhax.challengers.managers.team.TeamData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class TeamPointsManager {

    private final Setup setup;
    private final CommandSender sender;
    private final String[] args;

    public TeamPointsManager(Setup setup, CommandSender sender, String[] args) {
        this.setup = setup;
        this.sender = sender;
        this.args = args;
    }

    private TeamData onPoints() {

        if(args.length != 3) {
            ActivityCommands.showHelp(sender, 3);
            return null;
        }

        String teamTarget = args[1];

        TeamData teamData = null;

        for(TeamData teamDatas : setup.getTm().getTeam().getTeams()) {

            if(!teamDatas.getTeam().getName().equalsIgnoreCase(teamTarget)) continue;

            teamData = teamDatas;
        }

        if(teamData == null) {
            sender.sendMessage("§4WARNING: L'EQUIPE SPECIFIE N'EXISTE PAS");
            sender.sendMessage("§4WARNING: VEUILLEZ VERIFIER SON NOM");
            sender.sendMessage("§4WARNING: LISTE DES NOMS: §bBLEUE §cROUGE §aVERTE §eJAUNE");
            sender.sendMessage("§4WARNING: SI VOUS PENSEZ QU'IL Y A UN SOUCIS VEUILLEZ CONTACTER LE DEVELOPPEUR §e_Paul#6918");
            return null;
        }

        if(Integer.parseInt(args[2]) < 0 || Integer.parseInt(args[2]) > 2147483645) {
            sender.sendMessage("§4WARNING: VEUILLEZ SPECIFIER UN NOMBRE CORRECT");
            sender.sendMessage("§4WARNING: LE NOMBRE DOIT ETRE COMPRIS ENTRE 0 & 2 147 483 644");
            sender.sendMessage("§4WARNING: SI VOUS PENSEZ QU'IL Y A UN SOUCIS VEUILLEZ CONTACTER LE DEVELOPPEUR §e_Paul#6918");
            return null;
        }

        return teamData;
    }

    public boolean onAddPoints() {

        if(onPoints() == null)
            return false;

        int points = Integer.parseInt(args[2]);

        TeamData teamData = onPoints();

        sender.sendMessage(this.a(autoReplace(setup.getCore().getConfig().getString("Messages.OldPoints"), teamData)));

        teamData.setTeamPoints(teamData.getTeamPoints() + points);

        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.GettingPoints")));

        sender.sendMessage(this.a(autoReplace(setup.getCore().getConfig().getString("Messages.NewPoints"), teamData)));

        sender.sendMessage(this.a(autoReplace2(setup.getCore().getConfig().getString("Messages.AddedTeamPoints"), teamData)));

        tryMessage(setup.getCore().getConfig().getString("Messages.AddedTeamPointsToMessage"), teamData, points);

        return true;
    }

    public boolean onRemovePoints() {

        if(onPoints() == null)
            return false;

        int points = Integer.parseInt(args[2]);

        TeamData teamData = onPoints();

        sender.sendMessage(this.a(autoReplace(setup.getCore().getConfig().getString("Messages.OldPoints"), teamData)));

        teamData.setTeamPoints(teamData.getTeamPoints() - points);

        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.GettingPoints")));

        sender.sendMessage(this.a(autoReplace(setup.getCore().getConfig().getString("Messages.NewPoints"), teamData)));

        sender.sendMessage(this.a(autoReplace2(setup.getCore().getConfig().getString("Messages.RemovedTeamPoints"), teamData)));

        tryMessage(setup.getCore().getConfig().getString("Messages.RemovedTeamPointsToMessage"), teamData, points);

        return true;
    }

    public boolean onSetPoints() {

        if(onPoints() == null)
            return false;

        int points = Integer.parseInt(args[2]);

        TeamData teamData = onPoints();

        sender.sendMessage(this.a(autoReplace(setup.getCore().getConfig().getString("Messages.OldPoints"), teamData)));

        teamData.setTeamPoints(points);

        sender.sendMessage(this.a(setup.getCore().getConfig().getString("Messages.GettingPoints")));

        sender.sendMessage(this.a(autoReplace(setup.getCore().getConfig().getString("Messages.NewPoints"), teamData)));

        sender.sendMessage(this.a(autoReplace2(setup.getCore().getConfig().getString("Messages.SetTeamPoints"), teamData)));

        tryMessage(setup.getCore().getConfig().getString("Messages.SetTeamPointsToMessage"), teamData, points);

        return true;
    }

    private String autoReplace(String message, TeamData teamData) {

        return message
                .replace("%team%", teamData.getTeam().getName())
                .replace("%teamcolor%", teamData.getTeam().getChatColor() + "")
                .replace("%teampoints%", String.valueOf(teamData.getTeamPoints()));

    }

    private String autoReplace2(String message, TeamData teamData) {

        return message
                .replace("%team%", teamData.getTeam().getName())
                .replace("%teamcolor%", teamData.getTeam().getChatColor() + "")
                .replace("%points%", String.valueOf(teamData.getTeamPoints()));

    }

    private void tryMessage(String message, TeamData teamData, int points) {

        for(PlayerData playerData : teamData.getMembers())

        try{
            Bukkit.getPlayer(playerData.getUuid()).sendMessage(this.a(message.replace("%teampoints%", String.valueOf(points))));
        } catch (Exception ignored){}

    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
