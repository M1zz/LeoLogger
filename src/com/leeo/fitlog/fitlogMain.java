package com.leeo.fitlog;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class fitlogMain extends JavaPlugin{
    private static fitlogListener listener;
    
    public void onEnable(){
        initEvent();
	    System.out.println("[Fitlog] fitlog v.1.0.2 Plugin is Enable.");
    }
    
    public void onDisable(){
        System.out.println("[Fitlog] fitlog v.1.0.2 Plugin is Disable.");
    }
    
    public void initEvent(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new fitlogListener(this), this);
    }
}
