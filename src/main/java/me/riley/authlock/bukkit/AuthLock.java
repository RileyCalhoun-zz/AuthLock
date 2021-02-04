package me.riley.authlock.bukkit;

import me.riley.authlock.bukkit.handlers.AuthHandler;
import me.riley.authlock.bukkit.handlers.CommandHandler;
import me.riley.authlock.bukkit.handlers.ConfigHandler;
import me.riley.authlock.bukkit.handlers.MessageHandler;
import me.riley.authlock.bukkit.listeners.PlayerListener;
import me.riley.authlock.bukkit.utils.MCStats;
import me.riley.authlock.shared.UpdateHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class AuthLock extends JavaPlugin {

    private boolean updateAvailable = false;
    private String updateMessage = "";
    private String pluginMessage = null;
    private UpdateHandler updateHandler;
    private ConfigHandler configHandler;
    private AuthHandler authHandler;
    private MessageHandler messageHandler;

    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        updateHandler = new UpdateHandler(getConfig().getBoolean("Update Checks", true), getDescription().getVersion(), this);
        configHandler = new ConfigHandler(this);
        authHandler = new AuthHandler(this);
        messageHandler = new MessageHandler(this);

        if (getConfig().getBoolean("MCStats", true)) {
            try {
                MCStats mcstats = new MCStats(this);
                mcstats.start();
            } catch (IOException e) {
                // Failed to submit the stats :-(
            }
        }

        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                getAuthHandler().playerJoin(player.getUniqueId());
                player.getInventory().forEach(itemStack -> {
                    if (getAuthHandler().isQRCodeItem(itemStack))
                        player.getInventory().remove(itemStack);
                });
            }
        }

        getServer().getPluginCommand("2fa").setExecutor(new CommandHandler(this));
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getLogger().info("AuthLock has been enabled!");
        checkUpdates();
    }

    private boolean checkUpdates() {
        return updateHandler.isUpToDate();
    }

    public boolean isUpdateAvailable() {
        return updateAvailable;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public String getPluginMessage() {
        return pluginMessage;
    }

    public void onDisable() {

    }

    public UpdateHandler getUpdateHandler() {
        return updateHandler;
    }

    public ConfigHandler getConfigHandler() {
        return configHandler;
    }

    public AuthHandler getAuthHandler() {
        return authHandler;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }
}
