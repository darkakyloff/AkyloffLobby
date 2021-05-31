package ru.Akyloff.Lobby.Configs;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.Akyloff.Lobby.Util.LocationUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LocationsConfig
{
    private Map<String, Location> locationMap = new HashMap<>();
    private final JavaPlugin javaPlugin;

    public LocationsConfig (JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        reload();
    }

    public void reload ( ) {
        ConfigurationSection section = javaPlugin.getConfig().getConfigurationSection("Locations");
        locationMap = section.getKeys(false).stream().collect(Collectors.toMap(Function.identity(), s -> LocationUtil.strToLoc(section.getString(s))));
    }

    public void setLocation ( String path, Location location ){
        if ( locationMap.containsKey(path) )
            locationMap.replace(path, location);
        else
            locationMap.put(path, location);
        save();
    }

    public void save ( ) {
        javaPlugin.getConfig().set("Locations", null);
        for ( Map.Entry<String, Location> entry : locationMap.entrySet() ){
            javaPlugin.getConfig().set("Locations." + entry.getKey(), LocationUtil.locToStr(entry.getValue()));
        }
        javaPlugin.saveConfig();
    }

    public Location getLocation(String path, Player player){
        return locationMap.getOrDefault(path, player.getLocation());
    }
}