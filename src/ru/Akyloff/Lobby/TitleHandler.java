package ru.Akyloff.Lobby;

import com.destroystokyo.paper.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TitleHandler implements Listener
{
    @EventHandler
    public void onJoin (PlayerJoinEvent event) {

        if (JavaMain.getInstance().getConfig().getBoolean("Misc.JoinTitle")) {
            event.getPlayer().sendTitle(new Title(
                    ChatColor.translateAlternateColorCodes('&',
                            JavaMain.getInstance().getConfig().getString("JoinTitle.Title").replace("{player}", event.getPlayer().getName())),
                    ChatColor.translateAlternateColorCodes('&',
                            JavaMain.getInstance().getConfig().getString("JoinTitle.SubTitle").replace("{player}", event.getPlayer().getName())),
                    0, 60, 10)
            );
        }
    }
}
