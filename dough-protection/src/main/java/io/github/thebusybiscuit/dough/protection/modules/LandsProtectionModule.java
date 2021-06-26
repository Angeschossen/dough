package io.github.thebusybiscuit.dough.protection.modules;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

import io.github.thebusybiscuit.dough.protection.Interaction;
import io.github.thebusybiscuit.dough.protection.ProtectionModule;

import lombok.NonNull;
import me.angeschossen.lands.api.integration.LandsIntegration;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.LandWorld;
import me.angeschossen.lands.api.role.enums.RoleSetting;

public class LandsProtectionModule implements ProtectionModule {

    private LandsIntegration landsIntegration;
    private final Plugin plugin;

    public LandsProtectionModule(@NonNull Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void load() {
        this.landsIntegration = new LandsIntegration(plugin);
    }

    @Override
    public boolean hasPermission(OfflinePlayer p, Location l, Interaction action) {
        LandWorld landWorld = landsIntegration.getLandWorld(l.getWorld());

        if (landWorld == null) {
            return true;
        }

        Area area = landWorld.getArea(l);
        return area == null || area.canSetting(p.getUniqueId(), convert(action));
    }

    @Nonnull
    private RoleSetting convert(@NonNull Interaction protectableAction) {
        switch (protectableAction) {
            case PLACE_BLOCK:
                return RoleSetting.BLOCK_PLACE;
            case BREAK_BLOCK:
                return RoleSetting.BLOCK_BREAK;
            case ATTACK_PLAYER:
                return RoleSetting.ATTACK_PLAYER;
            case INTERACT_BLOCK:
                return RoleSetting.INTERACT_CONTAINER;
            case INTERACT_ENTITY:
                return RoleSetting.INTERACT_VILLAGER;
            case ATTACK_ENTITY:
                return RoleSetting.ATTACK_ANIMAL;
            default:
                return RoleSetting.BLOCK_BREAK;
        }
    }
}
