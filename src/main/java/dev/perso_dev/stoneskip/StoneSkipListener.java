package dev.perso_dev.stoneskip;

import com.hypixel.hytale.server.core.event.events.ecs.DamageBlockEvent;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Listener para el rebote de rubble en agua.
 * 
 * Implementa la funcionalidad de rebote de rubble (proyectil) en agua
 * con una probabilidad del 35% y un límite máximo de 5 rebotes.
 * 
 * Referencias:
 * - https://hytalemodding.dev/en/docs/server/events
 * - https://hytale-docs.com/docs/api/server-internals/events
 */
public class StoneSkipListener {
    private final StoneSkipPlugin plugin;
    private final Random random = new Random();
    
    // Mapa para rastrear el número de rebotes por proyectil
    // Usa UUID de la entidad del proyectil como clave
    private final Map<UUID, Integer> bounceCounts = new HashMap<>();
    
    // Constantes
    private static final double BOUNCE_PROBABILITY = 0.35; // 35%
    private static final int MAX_BOUNCES = 5;
    
    public StoneSkipListener(StoneSkipPlugin plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Registra este listener en el EventRegistry de Hytale.
     * Usa getEventRegistry() del plugin para suscribirse a eventos.
     */
    public void register() {
        // Registrar eventos usando getEventRegistry() del plugin
        plugin.getEventRegistry().register(DamageBlockEvent.class, this::onBlockDamage);
        
        System.out.println("StoneSkipListener registrado - escuchando eventos de daño a bloques");
    }
    
    /**
     * Desregistra este listener del EventRegistry.
     * Limpia el mapa de rebotes.
     */
    public void unregister() {
        bounceCounts.clear();
        System.out.println("StoneSkipListener desregistrado");
    }
    
    /**
     * Maneja eventos de daño a bloques.
     * Detecta cuando un proyectil (rubble) golpea un bloque de agua y aplica rebote.
     * 
     * @param event El evento de daño a bloque
     */
    private void onBlockDamage(DamageBlockEvent event) {
        try {
            // NOTA: La implementación completa requiere acceso a APIs que pueden no estar
            // disponibles en la versión actual. Esta es una implementación básica que
            // demuestra la estructura y lógica, pero necesita ser completada con las
            // APIs correctas cuando estén disponibles.
            
            // 1. Verificar si el daño viene de un proyectil
            // TODO: Usar la API correcta para obtener la fuente de daño
            // Ejemplo esperado: Damage.Source source = event.getSource();
            // if (!(source instanceof Damage.ProjectileSource)) return;
            
            // 2. Verificar si el proyectil es rubble
            // TODO: Acceder al componente del proyectil para verificar el tipo
            // Necesitamos ProjectileComponent o similar
            
            // 3. Verificar si el bloque es agua
            // TODO: Obtener el bloque del evento y verificar su tipo
            // String blockName = getBlockName(event);
            // if (!isWaterBlock(blockName)) return;
            
            // 4. Obtener o inicializar el contador de rebotes
            // TODO: Obtener UUID único del proyectil
            // UUID projectileId = getProjectileId(projectileSource);
            // int currentBounces = bounceCounts.getOrDefault(projectileId, 0);
            
            // 5. Verificar límite de rebotes
            // if (currentBounces >= MAX_BOUNCES) return;
            
            // 6. Aplicar rebote con 35% de probabilidad
            // if (random.nextDouble() < BOUNCE_PROBABILITY) {
            //     bounceCounts.put(projectileId, currentBounces + 1);
            //     event.setCancelled(true);
            //     applyBounce(projectileSource);
            //     System.out.println("¡Boing! Rebote #" + (currentBounces + 1));
            // }
            
            // Placeholder: loguear cuando se detecta daño a bloque
            // Esto se activará para todos los daños a bloques hasta que se implemente
            // la verificación completa de proyectiles y agua
            System.out.println("Evento de daño a bloque detectado (implementación pendiente de APIs)");
            
        } catch (Exception e) {
            // Manejar errores silenciosamente para no interrumpir el juego
            System.err.println("Error en StoneSkipListener: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Obtiene el nombre del bloque desde el evento.
     * 
     * @param event El evento de daño a bloque
     * @return El nombre del bloque o null si no se puede obtener
     */
    private String getBlockName(DamageBlockEvent event) {
        try {
            // TODO: Usar la API correcta para obtener el bloque
            // La estructura exacta del DamageBlockEvent puede variar
            // Ejemplo esperado: Block block = event.getBlock();
            // return block != null ? block.getType().getName() : null;
            return null; // Placeholder hasta que la API esté disponible
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Verifica si un bloque es agua basándose en su nombre.
     * 
     * @param blockName El nombre del bloque
     * @return true si el bloque es agua, false en caso contrario
     */
    private boolean isWaterBlock(String blockName) {
        if (blockName == null) {
            return false;
        }
        String lowerName = blockName.toLowerCase();
        return lowerName.contains("water") || 
               lowerName.contains("agua") || 
               lowerName.contains("liquid") ||
               lowerName.contains("fluid");
    }
    
    /**
     * Obtiene el ID único del proyectil desde la fuente de daño.
     * 
     * @param projectileSource La fuente de daño del proyectil
     * @return El UUID del proyectil o null si no se puede obtener
     */
    private UUID getProjectileId(Damage.ProjectileSource projectileSource) {
        try {
            // TODO: Usar la API correcta para obtener el UUID del proyectil
            // La estructura exacta puede variar
            // Ejemplo esperado: Entity projectile = projectileSource.getProjectile();
            // return projectile != null ? projectile.getUuid() : null;
            return null; // Placeholder hasta que la API esté disponible
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Aplica el rebote modificando la velocidad del proyectil.
     * 
     * @param projectileSource La fuente de daño del proyectil
     */
    private void applyBounce(Damage.ProjectileSource projectileSource) {
        try {
            // Obtener el componente del proyectil para modificar su velocidad
            // La API exacta puede variar, esto es un placeholder
            // TODO: Usar la API correcta para modificar la velocidad del proyectil
            // Ejemplo esperado:
            // - Obtener ProjectileComponent
            // - Modificar la velocidad (invertir Y, reducir velocidad, etc.)
            // - Aplicar cambios
            
            System.out.println("Aplicando rebote al proyectil (placeholder - necesita API de ECS)");
        } catch (Exception e) {
            System.err.println("Error al aplicar rebote: " + e.getMessage());
        }
    }
}
