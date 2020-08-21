package fr.rqndomhax.challengers.scoreboard;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.team.TeamData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeamScoreboard {

    private final Map<UUID, FastBoard> boards = new HashMap<>();
    private final Setup setup;

    public TeamScoreboard(Setup setup) {
        this.setup = setup;
    }


    public void newTeamScoreboard(Player p) {
        FastBoard fb = new FastBoard(p);
        fb.updateTitle(ChatColor.RED + "Challengers");

        boards.put(p.getUniqueId(), fb);
    }

    public void removeTeamScoreboard(Player p) {
        FastBoard fb = boards.remove(p.getUniqueId());
    }

    private void updateBoard(FastBoard board) {

        // Set title according to the gamestate

        switch(setup.getGm().getGame().getGameState()) {

            case WAITING:
                board.updateTitle(ChatColor.RED + "Challengers " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Attente");
                break;
            case FIRSTAC:
                board.updateTitle(ChatColor.RED + "Epreuve 1 " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Escorte");
                break;
            case SECONDAC:
                board.updateTitle(ChatColor.RED + "Epreuve 2 " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Labyrinthe");
                break;
            case THIRDAC:
                board.updateTitle(ChatColor.RED + "Epreuve 3 " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Construction");
                break;
            case FOURTHAC:
                board.updateTitle(ChatColor.RED + "Epreuve 4 " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Parcours");
                break;
            case FIFTHAC:
                board.updateTitle(ChatColor.RED + "Epreuve 5 " + ChatColor.GOLD + "- " + ChatColor.GREEN + "5: Arène monstrueuse");
                break;
            default:
                board.updateTitle(ChatColor.RED + "Challengers " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Erreur");
                break;
        }

        if(setup.getGm().getPlayerData(board.getPlayer().getUniqueId()) == null || setup.getGm().getPlayerData(board.getPlayer().getUniqueId()).getTeamData() == null) {

            getTeamsScoreboard(null, board, 1);

            return;
        }

        TeamData teamData = setup.getGm().getPlayerData(board.getPlayer().getUniqueId()).getTeamData();

        board.updateLines(
                "",
                "Equipe : " + teamData.getTeam().getName(),
                "Point d'équipe : " + teamData.getTeamPoints(),
                "Point personnel : " + setup.getGm().getPlayerData(board.getPlayer().getUniqueId()).getPlayerPoints(),
                "",
                "",
                "",
                ""
        );

        getTeamsScoreboard(teamData, board, 6);

    }


    public void runBoard() {
        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(setup.getCore(), () -> {
            for (FastBoard board : boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);
    }

    private void getTeamsScoreboard(TeamData teamData, FastBoard board, int fromSlot) {

        for(TeamData teamDatas : setup.getTm().getTeam().getTeams()) {

            if(teamDatas == teamData) continue;

            board.updateLine(fromSlot,teamDatas.getTeam().getChatColor() +teamDatas.getTeam().getName().toUpperCase() +" » " + teamDatas.getTeamPoints());
            fromSlot++;
        }
    }

}
