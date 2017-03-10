package com.leeo.fitlog;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class fitlogListener implements Listener{
    private fitlogMain plugin;
    
    public fitlogListener(fitlogMain instance){
        plugin = instance;
    }
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = (Player) event.getEntity();
        JSONObject jsonObject = new JSONObject();
        JSONArray personArray = new JSONArray();
        JSONObject personInfo = new JSONObject();
        personInfo.put("name", player.getName().toString());
        personInfo.put("type", event.getEventName());
        personInfo.put("message", event.getDeathMessage());
        personArray.add(personInfo);
        jsonObject.put("event", personArray);
        String jsonInfo = jsonObject.toJSONString();
 
        System.out.print(jsonInfo);
    }
 
    // User Death Event
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event){
        try{ 
            Player player = event.getEntity().getKiller();
            JSONObject jsonObject = new JSONObject();
            JSONArray personArray = new JSONArray();
            JSONObject personInfo = new JSONObject();

            personInfo.put("name", player.getName().toString());
            personInfo.put("type", event.getEventName());

            personArray.add(personInfo);
            jsonObject.put("event", personArray);
            
            String jsonInfo = jsonObject.toJSONString();
     
            System.out.print("[fitlog]"+jsonInfo);
        }catch(NullPointerException e){
            System.out.print("NullPointerException");
        }
         
    }
    
}
