package me.OscarKoala.GlitchTalePlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.entity.Player;

public class GlobalListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (GlitchTalePlugin.getInstance().getConfigController().isDebugEnabled()) {
            GlitchTalePlugin.getInstance().getLogger().info(String.format("[CHAT] %s: %s", player.getName(), message));
        }
    }
}