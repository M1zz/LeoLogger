package com.leeo.fitlog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FitlogLogger {
    private static Map<String, String> lastRegion = new HashMap<>();
    Logger log;
    public FitlogLogger() {
    }
    public FitlogLogger(Logger l) {
        log = l;
    }
    
    // Logger
    public String logMaker(Event event){
        String playerName   = "";
        String eventName    = "";
        String itemName     = "";
        String message      = "";
        String data         = "";
        
        eventName = event.getEventName();
        
        // Block Event
        if (event instanceof BlockBreakEvent){
            Player player = ((BlockBreakEvent) event).getPlayer();
            playerName = player.getName();
            
            itemName = ((BlockBreakEvent) event).getBlock().getType().toString();
        }
        else if (event instanceof BlockPlaceEvent){
            Player player = ((BlockPlaceEvent) event).getPlayer();
            playerName = player.getName();
            
            itemName = ((BlockBreakEvent) event).getBlock().getType().toString();
        }
        
        // Enchantment Event
        else if (event instanceof EnchantItemEvent){
            Player player = ((EnchantItemEvent) event).getEnchanter();
            playerName = player.getName();
            
            itemName = ((EnchantItemEvent) event).getItem().getItemMeta().getDisplayName();
        }
        
        // Entity Event
        else if (event instanceof PlayerDeathEvent){
            Player player = ((PlayerDeathEvent) event).getEntity();
            playerName = player.getName();
            
            message = ((PlayerDeathEvent) event).getDeathMessage();
        }
        else if (event instanceof EntityDeathEvent){
            // Event is for about all entity in the world but we collect only for hunt.
            try{
                Player player = ((EntityDeathEvent) event).getEntity().getKiller();
                playerName = player.getName();

            }
            catch(NullPointerException e){
                System.out.print("NullPointerException");
            }
            
        }
        
        // Hanging Event
        else if (event instanceof HangingBreakByEntityEvent){
            System.out.print("HangingBreakByEntityEvent");
        }
        else if (event instanceof HangingPlaceEvent){
            System.out.print("HangingPlaceEvent");
        }
        
        // Inventory Event
        else if (event instanceof CraftItemEvent){
            System.out.print("CraftItemEvent");
        }
        
        // Player Event
        else if (event instanceof PlayerInteractEvent){
            System.out.print("PlayerInteractEvent");
        }
        else if (event instanceof PlayerItemConsumeEvent){
            System.out.print("PlayerItemConsumeEvent");
        }
        else if (event instanceof PlayerItemHeldEvent){
            System.out.print("PlayerItemHeldEvent");
        }
        else if (event instanceof PlayerJoinEvent){
            Player player = ((PlayerJoinEvent) event).getPlayer();
            playerName = player.getName();
        }
        else if (event instanceof PlayerMoveEvent){
            Player player = ((PlayerMoveEvent) event).getPlayer();
            playerName = player.getName();
            
            eventName = "";
            
            Location to = ((PlayerMoveEvent) event).getTo();
            Location from = ((PlayerMoveEvent) event).getFrom();
            if (!lastRegion.containsKey(player.getName())){
                lastRegion.put(player.getName(), null);
            }
            
            if (Math.abs( (to.getBlockX()-from.getBlockX()) + (to.getBlockZ()-from.getBlockZ())) > 0 ||
                Math.abs( (to.getBlockY()-from.getBlockY())) > 0 ){
                eventName = event.getEventName();
            }
        }
        else if (event instanceof PlayerPickupItemEvent){
            Player player = ((PlayerPickupItemEvent) event).getPlayer();
            playerName = player.getName();
            
            itemName = ((PlayerPickupItemEvent) event).getItem().getItemStack().getType().toString();
        }
        else if (event instanceof PlayerQuitEvent){
            Player player = ((PlayerQuitEvent) event).getPlayer();
            playerName = player.getName();
        }
        else{
            System.out.print(event.getEventName()+"is Unexpected Case");
        }
        
        
        if (!(eventName.isEmpty())){
            data = playerName+","+eventName+","+itemName+","+message;
            System.out.print(data);
            try{
                return data;
            }
            catch(NullPointerException e){
                //System.out.print("NullPointerException");
            }
        }
        return data;
    }
    
    public static Map<String, String> getLastRegion() {
        return lastRegion;
    }
    
    public static void setLastRegion(Map<String, String> lastRegion){
        FitlogLogger.lastRegion = lastRegion;
    }
    
    public void logWriter(String data){
        System.out.print("Logger : "+data);
        log.info(data); 
    }
}
