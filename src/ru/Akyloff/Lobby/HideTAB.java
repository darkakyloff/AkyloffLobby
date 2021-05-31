package ru.Akyloff.Lobby;

import com.comphenix.protocol.*;
import com.comphenix.protocol.PacketType.Play.*;
import com.comphenix.protocol.events.*;
import org.bukkit.plugin.java.*;

public class HideTAB extends PacketAdapter {

	public HideTAB(JavaPlugin plugin) {
		super(plugin, new PacketType[] { Server.PLAYER_INFO });
	}

	public void onPacketSending(PacketEvent event) {
		event.setCancelled(true);
	}
}