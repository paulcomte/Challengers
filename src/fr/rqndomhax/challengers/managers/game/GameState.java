package fr.rqndomhax.challengers.managers.game;

import fr.rqndomhax.challengers.managers.tasks.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public enum GameState {
    WAITING(),
    FIRSTAC(1, "Messages.FirstAC.Start", FirstT.class, 0),
    SECONDAC(2, "Messages.SecondAC.Start", SecondT.class, 0),
    THIRDAC(3, "Messages.ThirdAC.Start", ThirdT.class, 0),
    FOURTHAC(4, "Messages.FourthAC.Start", FourthT.class, 0),
    FIFTHAC(5, "Messages.FifthAC.Start", FifthT.class, 0);

    private int gameInt;
    private int gameState;
    private String path;
    private Class<? extends BukkitRunnable> runnable;


    GameState() {
    }

    GameState(int gameInt, String path, Class<? extends BukkitRunnable> runnable, int gameState) {
        this.gameInt = gameInt;
        this.path = path;
        this.runnable = runnable;
        this.gameState = gameState;
    }

    public int getGameInt() {
        return gameInt;
    }

    public String getPath() {
        return path;
    }

    public Class<? extends BukkitRunnable> getEnum() {
        return runnable;
    }

    private GameState getBy(int gameInt) {
        return Arrays.stream(values()).filter(g -> g.getGameInt() == gameInt).findAny().orElse(WAITING);
    }

    public GameState next() {
        return getBy(GameManager.INSTANCE.getGame().getGameState().getGameInt() + 1);
    }

    public int getGameState() {
        return gameState;
    }

}
