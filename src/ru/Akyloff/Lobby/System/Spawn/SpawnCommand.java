package ru.Akyloff.Lobby.System.Spawn;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.Akyloff.Lobby.JavaMain;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] strings) {
        if ( sender instanceof Player ){
            Player player = (Player)sender;
            player.teleport(JavaMain.getInstance().getLocationsConfig().getLocation("Spawn", player));
            sender.sendMessage(JavaMain.getInstance().getConfig().getString("Messages.spawnTeleport").replace("&", "§"));
            return true;
        }
        sender.sendMessage(JavaMain.getInstance().getConfig().getString("Messages.commandOnlyForPlayers").replace("&", "§"));
        return true;
    }
}
