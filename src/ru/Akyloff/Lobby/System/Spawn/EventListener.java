package ru.Akyloff.Lobby.System.Spawn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import ru.Akyloff.Lobby.JavaMain;

public class EventListener
        implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().teleport(JavaMain.getInstance().getLocationsConfig().getLocation("Spawn", event.getPlayer()));
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(JavaMain.getInstance().getLocationsConfig().getLocation("Spawn", event.getPlayer()));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getPlayer().getLocation().getBlockY() <= 5) {
            event.getPlayer().teleport(JavaMain.getInstance().getLocationsConfig().getLocation("Spawn", event.getPlayer()));
        }
    }
}
