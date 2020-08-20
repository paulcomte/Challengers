package fr.rqndomhax.challengers.managers.game;

import fr.rqndomhax.challengers.managers.tasks.*;
import org.bukkit.scheduler.BukkitRunnable;

public enum GameState {
    WAITING(),
    FIRSTAC(1, "Messages.FirstAC.Start", FirstT.class),
    SECONDAC(2, "Messages.SecondAC.Start", SecondT.class),
    THIRDAC(3, "Messages.ThirdAC.Start", ThirdT.class),
    FOURTHAC(4, "Messages.FourthAC.Start", FourthT.class),
    FIFTHAC(5, "Messages.FifthAC.Start", FifthT.class);

    private int gameInt;
    private String path;
    private Class<? extends BukkitRunnable> runnable;

    GameState() {
    }

    GameState(int gameInt, String path, Class<? extends BukkitRunnable> runnable) {
        this.gameInt = gameInt;
        this.path = path;
        this.runnable = runnable;
    }

    public int getGameInt() {
        return gameInt;
    }

    public String getPath() {
        return path;
    }

    public Class<? extends BukkitRunnable> getCustomClass() {
        return runnable;
    }
}
