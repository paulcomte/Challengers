package fr.rqndomhax.challengers.scoreboard;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.game.GameState;
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
    private int i;

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
                board.updateTitle(ChatColor.YELLOW + "Challengers " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Attente");
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

        teamBoard(board);

    }

    private void teamBoard(FastBoard board) {

        getTeamsScoreboard(board);

    }

    private void getCurrentScore(FastBoard board) {


        i = i >= 30 ? 0 : i;
    }


    public void runBoard() {
        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(setup.getCore(), () -> {
            for (FastBoard board : boards.values()) {

                updateBoard(board);

                if(setup.getGm().getGame().getGameState() == GameState.WAITING) {
                    updateBoard(board);
                }

                if(i >= 15)
                    teamBoard(board);
                else
                    getCurrentScore(board);

                i++;
            }
        }, 0, 20);
    }

    private void getTeamsScoreboard(FastBoard board) {

        for(TeamData teamDatas : setup.getTm().getTeam().getTeams()) {
            board.updateLines(teamDatas.getTeam().getChatColor() + teamDatas.getTeam().getName().toUpperCase() +" » " + teamDatas.getTeamPoints());
        }
    }

}
