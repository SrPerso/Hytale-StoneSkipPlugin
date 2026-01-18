package dev.perso_dev.stoneskip;

import net.hytale.server.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public final class StoneSkipPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new StoneSkipListener(this), this);
        getLogger().info("¡StoneSkip activated!");
    }

    @Override
    public void onDisable() {
        getLogger().info("StoneSkip desactivated.");
    }
}