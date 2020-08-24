/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.utils;

import fr.rqndomhax.challengers.core.Core;
import net.minecraft.server.v1_12_R1.ChatComponentText;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;

public class Tablist {

    private int cooldown;
    private int ipCharIndex;

    public void registerTab(Core core){

        new BukkitRunnable(){

            @Override
            public void run(){
                PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
                Object header = new ChatComponentText(
                        "§8§l≫ §6§lChallengers §8§l≪ " +
                        "\n\n" + colorIpAt() + "" +
                        "\n " +
                        "\n§r§8» §8§m                        §r §8«");

                Object footer = new ChatComponentText(
                        " §r§8» §8§m                        §r §8« " +
                          "\n " +
                          "\n §r§f§l● §6Développeur §f» §e§lRqndomHax §f§l●");

                try {

                    Field a = packet.getClass().getDeclaredField("a");
                    a.setAccessible(true);
                    Field b = packet.getClass().getDeclaredField("b");
                    b.setAccessible(true);

                    a.set(packet, header);
                    b.set(packet, footer);

                    if(Bukkit.getOnlinePlayers().size() == 0) return;
                    for(Player player : Bukkit.getOnlinePlayers()){
                        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
                    }

                } catch (NoSuchFieldException | IllegalAccessException e){
                    e.printStackTrace();
                }

            }

        }.runTaskTimerAsynchronously(core, 0, 1);

    }

    private String colorIpAt() {
        String ip = "Créée par Psi4";

        if (cooldown > 0) {
            cooldown--;
            return ChatColor.YELLOW + ip;
        }

        StringBuilder formattedIp = new StringBuilder();

        if (ipCharIndex > 0) {
            formattedIp.append(ip, 0, ipCharIndex - 1);
            formattedIp.append(ChatColor.GOLD).append(ip.charAt(ipCharIndex - 1));
        } else {
            formattedIp.append(ip, 0, ipCharIndex);
        }

        formattedIp.append(ChatColor.RED).append(ip.charAt(ipCharIndex));

        if (ipCharIndex + 1 < ip.length()) {
            formattedIp.append(ChatColor.GOLD).append(ip.charAt(ipCharIndex + 1));

            if (ipCharIndex + 2 < ip.length())
                formattedIp.append(ChatColor.YELLOW).append(ip.substring(ipCharIndex + 2));

            ipCharIndex++;
        } else {
            ipCharIndex = 0;
            cooldown = 50;
        }

        return ChatColor.YELLOW + formattedIp.toString();
    }

}
