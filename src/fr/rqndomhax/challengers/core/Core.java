package fr.rqndomhax.challengers.core;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    @Override
    public void onEnable() {

        new Setup(this).setup();

        super.onEnable();
    }


    @Override
    public void onDisable() {

        super.onDisable();
    }


}
