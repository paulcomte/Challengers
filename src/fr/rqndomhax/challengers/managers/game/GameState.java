package fr.rqndomhax.challengers.managers.game;

import fr.rqndomhax.challengers.managers.tasks.*;
import org.bukkit.scheduler.BukkitRunnable;

public enum GameState {
    WAITING(),
    FIRSTAC(1, "Messages.FirstAC.Start", FirstT.class, 0),
    SECONDAC(2, "Messages.SecondAC.Start", SecondT.class, 0),
    THIRDAC(3, "Messages.ThirdAC.Start", ThirdT.class, 0),
    FOURTHAC(4, "Messages.FourthAC.Start", FourthT.class, 0),
    FIFTHAC(5, "Messages.FifthAC.Start", FifthT.class, 0);

    private int gameInt;
    private int currentState;
    private String path;
    private Class<? extends BukkitRunnable> runnable;


    GameState() {
    }

    GameState(int gameInt, String path, Class<? extends BukkitRunnable> runnable, int gameState) {
        this.path = path;
        this.runnable = runnable;
        this.currentState = gameState;
    }

    public String getPath() {
        return path;
    }

    public Class<? extends BukkitRunnable> getEnum() {
        return runnable;
    }

    public int getCurrentState() {
        return currentState;
    }

    public GameState withGameState(GameState gameState, int gameState1) {
        this.currentState = gameState1;
        return gameState;
    }

    public int getGameInt() {
        return gameInt;
    }
}
