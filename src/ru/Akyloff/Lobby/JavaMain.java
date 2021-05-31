package ru.Akyloff.Lobby;

import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.Akyloff.Lobby.Configs.LocationsConfig;
import ru.Akyloff.Lobby.System.Spawn.EventListener;
import ru.Akyloff.Lobby.System.Spawn.SetSpawnCommand;
import ru.Akyloff.Lobby.System.Spawn.SpawnCommand;

public class JavaMain
        extends JavaPlugin {

    private static JavaMain javaMain;
    private LocationsConfig locationsConfig;

    @Override
    public void onEnable() {


        javaMain = this;
        AkyloffManager.protect();
        AkyloffManager.Message();
        saveDefaultConfig();
        this.locationsConfig = new LocationsConfig(this);
        Bukkit.getPluginManager().registerEvents(new DisabledEvens(), this);
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getPluginManager().registerEvents(new TitleHandler(), this);
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());

        if (!getConfig().getBoolean("Misc.HideTab")) return;
        ProtocolLibrary.getProtocolManager().addPacketListener(new HideTAB(this));
    }

    public static JavaMain getInstance() {
        return javaMain;
    }

    public LocationsConfig getLocationsConfig() {
        return locationsConfig;
    }
}

