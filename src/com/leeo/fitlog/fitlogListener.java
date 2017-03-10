package com.leeo.fitlog;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class fitlogListener implements Listener{
    private fitlogMain plugin;
    
    JSONObject jsonObject = new JSONObject();
    JSONArray personArray = new JSONArray();
    JSONObject personInfo = new JSONObject();
    
    public void jsonInitilalize(){ 
        // Json formmater
        JSONObject jsonObject = new JSONObject();
        JSONArray personArray = new JSONArray();
        JSONObject personInfo = new JSONObject();
    }
    
    public fitlogListener(fitlogMain instance){
        plugin = instance;
    }
    
    // User Death Event
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = (Player) event.getEntity();

        personInfo.put("name", player.getName().toString());
        personInfo.put("type", event.getEventName());
        personInfo.put("message", event.getDeathMessage());
        personArray.add(personInfo);
        jsonObject.put("event", personArray);
        String jsonInfo = jsonObject.toJSONString();
 
        System.out.print(jsonInfo);
        jsonInfo = "";
    }
    
    // Entity Death Event
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event){
        try{ 
            Player player = event.getEntity().getKiller();

            personInfo.put("name", player.getName().toString());
            personInfo.put("type", event.getEventName());
            personArray.add(personInfo);
            jsonObject.put("event", personArray);
            
            String jsonInfo = jsonObject.toJSONString();
     
            System.out.print(jsonInfo);
            jsonInfo = "";
        }catch(NullPointerException e){
            System.out.print("NullPointerException");
        }    
    }
    
    // User item consume event
    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent event){
        Player player = (Player) event.getPlayer();

        try{
            personInfo.put("name", player.getName().toString());
            personInfo.put("type", event.getEventName());
            personInfo.put("item", player.getItemInHand().getItemMeta().getDisplayName().toString());
            personArray.add(personInfo);

            jsonObject.put("event", personArray);

            String jsonInfo = jsonObject.toJSONString();
     
            System.out.print(jsonInfo);
     
        }catch(NullPointerException e){
            personInfo.put("name", player.getName().toString());
            personInfo.put("type", event.getEventName());
            personInfo.put("item", player.getItemInHand().getData().getItemType().toString());

            personArray.add(personInfo);
            jsonObject.put("event", personArray);

            String jsonInfo = jsonObject.toJSONString();
     
            System.out.print(jsonInfo);
            jsonInfo = "";
        }
    }
    
    // User interact 
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack hand = player.getItemInHand();
        
        personInfo.put("name", player.getName().toString());
        personInfo.put("type", event.getEventName());
        personInfo.put("item", player.getItemInHand().getType().toString());
        personArray.add(personInfo);
        jsonObject.put("event", personArray);

        String jsonInfo = jsonObject.toJSONString();
        System.out.print(jsonInfo);
        jsonInfo = "";
    }

    // User block break event
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();

        personInfo.put("name", player.getName().toString());
        personInfo.put("type", event.getEventName());
        personInfo.put("item", event.getBlock().getType().toString().toString());
        personArray.add(personInfo);
        
        jsonObject.put("event", personArray);
        
        String jsonInfo = jsonObject.toJSONString();
 
        System.out.print(jsonInfo);
        jsonInfo = "";
    }

    // User block place event
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();

        personInfo.put("name", player.getName().toString());
        personInfo.put("type", event.getEventName());
        personInfo.put("item", event.getBlock().getType().toString());
        personArray.add(personInfo);
        jsonObject.put("event", personArray);

        String jsonInfo = jsonObject.toJSONString();
 
        System.out.print(jsonInfo);
        jsonInfo = "";
    }

    // User item enchant event
    @EventHandler
    public void onEnchantItem(EnchantItemEvent event){
        Player player = event.getEnchanter();

        personInfo.put("name", player.getName().toString());
        personInfo.put("type", event.getEventName());
        personInfo.put("item", event.getItem().getItemMeta().getDisplayName().toString());
        personInfo.put("element", event.getEnchantsToAdd());
        
        personArray.add(personInfo);

        jsonObject.put("event", personArray);

        String jsonInfo = jsonObject.toJSONString();
 
        System.out.print(jsonInfo);
        jsonInfo = "";
    }


    // Hanging item break event 
    public void onHangingBreakEvent(HangingBreakByEntityEvent event){
        Player player = (Player) event.getRemover();

        personInfo.put("name", player.getName().toString());
        personInfo.put("type", event.getEventName());
        personInfo.put("item", event.getEntity().getName().toString());

        personArray.add(personInfo);

        jsonObject.put("event", personArray);

        String jsonInfo = jsonObject.toJSONString();
 
        System.out.print(jsonInfo);
        jsonInfo = "";
    }
    
    // Hanging item place event 
    public void onHangingPlaceEvent(HangingPlaceEvent event){
        Player player = event.getPlayer();

        personInfo.put("name", player.getName().toString());
        personInfo.put("type", event.getEventName());
        personInfo.put("item", event.getBlock().getBiome().toString());

        personArray.add(personInfo);

        jsonObject.put("event", personArray);

        String jsonInfo = jsonObject.toJSONString();
 
        System.out.print(jsonInfo);
        jsonInfo = "";
    }

    // Item craft event
    @EventHandler
    public void onCraftItemEvent(CraftItemEvent event){
        Player player = (Player) event.getWhoClicked();
        
        ItemStack result = event.getCurrentItem();
        //ItemStack result = event.getRecipe().getResult();
        int mount = result.getAmount();
        for (int i = 0; i < mount; i++){
            personInfo.put("name", player.getName().toString());
            personInfo.put("type", event.getEventName());
            personInfo.put("item", result.getData().toString());

            personArray.add(personInfo);

            jsonObject.put("event", personArray);

            String jsonInfo = jsonObject.toJSONString();
     
            System.out.print(jsonInfo);
            jsonInfo = "";
        }
    }

    // User join event
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        personInfo.put("name", player.getName().toString());
        personInfo.put("type", event.getEventName());
        personArray.add(personInfo);

        jsonObject.put("event", personArray);

        String jsonInfo = jsonObject.toJSONString();
 
        System.out.print(jsonInfo);
        jsonInfo = "";
    }   
  
    // User quit event
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        Player player = event.getPlayer();

        personInfo.put("name", player.getName().toString());
        personInfo.put("type", event.getEventName());

        personArray.add(personInfo);

        jsonObject.put("event", personArray);

        String jsonInfo = jsonObject.toJSONString();
 
        System.out.print(jsonInfo);
        jsonInfo = "";
    }

    @EventHandler
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event){
        try{
            int slot = event.getNewSlot();
            Player player = event.getPlayer();
            
            PlayerInventory inv = player.getInventory();
            ItemStack is = inv.getItem(slot);
            ItemMeta im = is.getItemMeta();
    
            personInfo.put("name", player.getName().toString());
            personInfo.put("type", event.getEventName());
            personInfo.put("item", is.getItemMeta().getDisplayName().toString());

            personArray.add(personInfo);

            jsonObject.put("event", personArray);

            String jsonInfo = jsonObject.toJSONString();
     
            System.out.print(jsonInfo);
            jsonInfo = "";
        }catch(NullPointerException e){
            System.out.print("NullPointerException");
        }

    }
    
    @EventHandler
    public void onPickupItemEvent(PlayerPickupItemEvent event){
        Player player = event.getPlayer();
        
        
        personInfo.put("name", player.getName().toString());
        personInfo.put("type", event.getEventName());
        personInfo.put("item", event.getItem().getItemStack().getType().toString());

        personArray.add(personInfo);
        jsonObject.put("event", personArray);
        String jsonInfo = jsonObject.toJSONString();
 
        System.out.print(jsonInfo);
    }
    
    private static Map<String, String> lastRegion = new HashMap<>();
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        
        Player player = event.getPlayer();
        Location to = event.getTo();
        Location from = event.getFrom();
        if (!lastRegion.containsKey(player.getName())){
            lastRegion.put(player.getName(), null);
        }

        personInfo.put("name", player.getName().toString());
        
        if (Math.abs( (to.getBlockX()-from.getBlockX()) + (to.getBlockZ()-from.getBlockZ())) > 0 ){
            
            JSONObject jsonObject = new JSONObject();
            JSONArray personArray = new JSONArray();
            JSONObject personInfo = new JSONObject();
            
            personInfo.put("type", event.getEventName());
            personArray.add(personInfo);
            jsonObject.put("event", personArray);

            String jsonInfo = jsonObject.toJSONString();
     
            System.out.print(jsonInfo);
            
            
        }       
        if ((to.getBlockY()-from.getBlockY()) > 0 ){
            
            JSONObject jsonObject = new JSONObject();
            JSONArray personArray = new JSONArray();
            JSONObject personInfo = new JSONObject();
            
            personInfo.put("type", "PlayerJumpEvent");
            personArray.add(personInfo);
            jsonObject.put("event", personArray);

            String jsonInfo = jsonObject.toJSONString();
     
            System.out.print(jsonInfo);
        }
        
    }
    
    public static Map<String, String> getLastRegion() {
        return lastRegion;
    }
    
    public static void setLastRegion(Map<String, String> lastRegion){
        fitlogListener.lastRegion = lastRegion;
    }




}
