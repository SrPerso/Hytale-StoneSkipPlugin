package dev.perso_dev.stoneskip;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

public final class StoneSkipPlugin extends JavaPlugin {

    private StoneSkipListener listener;

    public StoneSkipPlugin(JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void start() {
        // Registrar el listener para eventos usando getEventRegistry()
        listener = new StoneSkipListener(this);
        listener.register();
        
        System.out.println("¡StoneSkip activado! Rebote 35% en agua.");
    }

    protected void stop() {
        if (listener != null) {
            listener.unregister();
        }
        System.out.println("StoneSkip desactivado.");
    }
}