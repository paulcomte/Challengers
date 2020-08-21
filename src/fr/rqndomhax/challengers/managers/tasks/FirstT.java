package fr.rqndomhax.challengers.managers.tasks;

import fr.rqndomhax.challengers.core.Setup;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class FirstT extends BukkitRunnable {

    private final Setup setup;
    int i = 0;

    public FirstT(Setup setup) {
        this.setup = setup;
    }


    @Override
    public void run() {

        // First task selecting a vip
        if(!setup.getVip().isVIPCooldownFinished()) {
            switch (i) {

                case 30:
                    Bukkit.getServer().broadcastMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Timer").replace("%timer%", "1 minute et 30 secondes")));
                    break;

                case 60:
                    Bukkit.getServer().broadcastMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Timer").replace("%timer%", "1 minute")));
                    break;

                case 90:
                    Bukkit.getServer().broadcastMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Timer").replace("%timer%", "30 secondes")));
                    break;

                case 10: case 9: case 8: case 7: case 6: case 5: case 4: case 3: case 2:
                    Bukkit.getServer().broadcastMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Timer").replace("%timer%", (120 - i) + " secondes")));
                    break;

                case 1:
                    Bukkit.getServer().broadcastMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Timer").replace("%timer%", "1 seconde")));
                    break;

                default:
                    break;
            }

            if (i == 120) {
                setup.getVip().setVIPCooldownFinished(true);
                setup.getVip().announceVIPFinished();
                i = 0;
            }

            i++;
            return;
        }

        // Second task selecting a body guard
        if(!setup.getbG().isBodyGuardCooldownFinished()) {
            switch (i) {

                case 30:
                    Bukkit.getServer().broadcastMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Timer").replace("%timer%", "1 minute et 30 secondes")));
                    break;

                case 60:
                    Bukkit.getServer().broadcastMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Timer").replace("%timer%", "1 minute")));
                    break;

                case 90:
                    Bukkit.getServer().broadcastMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Timer").replace("%timer%", "30 secondes")));
                    break;

                case 10: case 9: case 8: case 7: case 6: case 5: case 4: case 3: case 2:
                    Bukkit.getServer().broadcastMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Timer").replace("%timer%", (120 - i) + " secondes")));
                    break;

                case 1:
                    Bukkit.getServer().broadcastMessage(this.a(setup.getCore().getConfig().getString("Messages.FirstAC.Timer").replace("%timer%", "1 seconde")));
                    break;

                default:
                    break;
            }

            if (i == 120) {
                setup.getbG().setBodyGuardCooldownFinished(true);
                setup.getbG().announceBGFinished();
                i = 0;
            }

            i++;
            return;
        }

        // Third task
        setup.getFm().teleport();

    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }

}
