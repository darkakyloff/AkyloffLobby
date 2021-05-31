package ru.Akyloff.Lobby;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class DisabledEvens
        implements Listener {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        int health = JavaMain.getInstance().getConfig().getInt("Misc.DefaultHealth");
        double speed = JavaMain.getInstance().getConfig().getDouble("Misc.DefaultSpeed");

        event.getPlayer().setMaxHealth(health);
        event.getPlayer().setHealth(health);


        event.getPlayer().setFlySpeed((float) speed);
        event.getPlayer().setWalkSpeed((float) speed);


        if (JavaMain.getInstance().getConfig().getBoolean("Misc.Invisibility")) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
        }

        if (JavaMain.getInstance().getConfig().getBoolean("Misc.HideStream"))
            event.setJoinMessage(null);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
        if (JavaMain.getInstance().getConfig().getBoolean("Misc.HideStream"))
            event.setQuitMessage(null);
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        if (JavaMain.getInstance().getConfig().getBoolean("Misc.HideStream"))
            event.setLeaveMessage(null);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (JavaMain.getInstance().getConfig().getBoolean("Misc.HideStream"))
            event.setDeathMessage(null);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.Move"))
            return;

        Location to = event.getTo();
        Location from = event.getFrom();

        if (to.getX() == from.getX() && to.getY() == from.getY() && to.getZ() == from.getZ())
            return;
        if (!event.getPlayer().isFlying() && to.getY() < from.getY())
            return;
        if (!event.getPlayer().hasPermission("akylofflobby.bypass")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.Chat"))
            return;
        if (!event.getPlayer().hasPermission("akylofflobby.bypass")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

        List<String> whitelist = JavaMain.getInstance().getConfig().getStringList("CommandWhiteList");
        String msg = event.getMessage();


        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.Commands"))
            return;
        int whitespaceIndex = msg.indexOf(' ');
        String commandLabel;

        if (whitespaceIndex == -1) {
            commandLabel = msg.substring(1);
        } else {
          commandLabel = msg.substring(1, whitespaceIndex);
        }

        if (whitelist.contains(commandLabel))
            return;

        if (!event.getPlayer().hasPermission("akylofflobby.bypass"))
            event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.BreakBlock"))
            return;
        if (!event.getPlayer().hasPermission("akylofflobby.bypass"))
            event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.PlaceBlock"))
            return;
        if (!event.getPlayer().hasPermission("akylofflobby.bypass"))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.ItemDrop"))
            return;
        if (!event.getPlayer().hasPermission("akylofflobby.bypass"))
            event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.Damage"))
            return;

        Player p = (Player) event.getEntity();
        if (!p.getPlayer().hasPermission("akylofflobby.bypass") && event.getEntity() == p)
            event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.Invsentory"))
            return;
        if (!event.getWhoClicked().hasPermission("akylofflobby.bypass"))
            event.setCancelled(true);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.Explode"))
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.Leaves"))
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.Food"))
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.Fire"))
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.Wheat"))
            return;
        Action a = event.getAction();
        if (!event.getPlayer().hasPermission("akylofflobby.bypass") && event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL)
            event.setCancelled(true);
    }

    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.PhysicsFire"))
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.PhysicsFire"))
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        if (!JavaMain.getInstance().getConfig().getBoolean("Disabled.Pickup"))
            return;
        if (!event.getPlayer().hasPermission("akylofflobby.bypass"))
            event.setCancelled(true);
    }

}
