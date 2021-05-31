package ru.Akyloff.Lobby.System.Spawn;


import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.Akyloff.Lobby.JavaMain;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] strings) {
        if( sender instanceof Player ){
            if ( !sender.hasPermission("akylofflobby.commands.setspawn") ){
                sender.sendMessage(JavaMain.getInstance().getConfig().getString("Messages.noPermissions").replace("&", "§"));
                return true;
            }

            Location location = ((Player)sender).getLocation();

            JavaMain.getInstance().getLocationsConfig().setLocation("Spawn", location);
            sender.sendMessage(JavaMain.getInstance().getConfig().getString("Messages.setSpawn").replace("&", "§"));
            return true;
        }
        sender.sendMessage(JavaMain.getInstance().getConfig().getString("Messages.commandOnlyForPlayers").replace("&", "§"));
        return true;
    }
}
