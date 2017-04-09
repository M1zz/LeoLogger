package com.leeo.fitlog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FitlogMain extends JavaPlugin{
    private static FitlogListener playerListener;
    
    // For the logger
    private Handler playerHandler, chatHandler, commandHandler;
    private Logger playerLog, chatLog, commandLog;
    
    
    public void onEnable(){
        initEvent();
	    System.out.println("[Fitlog] fitlog v.1.0.2 Plugin is Enable.");
	    
	    String inDate   = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());

	    // Set up logfile for player events
        playerLog = Logger.getAnonymousLogger();
        playerLog.setUseParentHandlers(false);
        
        try {
            Handler playerHandler = new FileHandler(getDataFolder() + File.separator + inDate +".log", true);
            playerLog.addHandler(playerHandler);
            playerHandler.setFormatter(new FitlogFormatter());
        } catch (SecurityException e) {
            this.getLogger().log(Level.SEVERE, "SecurityException while opening player log!", e);
        } catch (IOException e) {
            this.getLogger().log(Level.WARNING, "Could not open player log!", e);
        }
        
        playerLog.info("Start of log");
        
        // register event
        playerListener = new FitlogListener(playerLog);
        getServer().getPluginManager().registerEvents(playerListener, this);
    }
    
    public void onDisable(){
        System.out.println("[Fitlog] fitlog v.1.0.2 Plugin is Disable.");
        
        playerLog.info("End of log");
        playerLog.removeHandler(playerHandler);
        playerHandler.close();
        playerHandler = null;
        playerLog = null;
        
        playerListener = null;
    }
    
    
    private File getSaveFile() {
        return new File(this.getDataFolder(), "save.yml");
    }
    
    public void initEvent(){
        boolean problem = false;
        String error = null;
        
        File saveFile = this.getSaveFile();
        File tempSaveFile = new File(saveFile.getParentFile(), saveFile.getName() + ".temp");
        
        if (!problem) {
            File parentDir = tempSaveFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    error = "Couldn't create parent directories for temporary save file! (" + parentDir.getAbsolutePath() + ")";
                    problem = true;
                }
            }
        }

        // Register event listeners
        //PluginManager pluginManager = getServer().getPluginManager();
        //pluginManager.registerEvents(new FitlogListener(this), this);
    }
}
