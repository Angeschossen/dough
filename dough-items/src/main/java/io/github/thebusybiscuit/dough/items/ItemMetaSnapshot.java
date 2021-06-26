package io.github.thebusybiscuit.dough.items;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.Getter;
import lombok.NonNull;

/**
 * This is an immutable version of ItemMeta.
 * Use this class to optimize your ItemStack#getItemMeta() calls by returning
 * a field of this immutable copy.
 * 
 * @author TheBusyBiscuit
 *
 */
@Getter
public class ItemMetaSnapshot {

    private final Optional<String> displayName;
    private final Optional<List<String>> lore;
    private OptionalInt customModelData;

    private final Set<ItemFlag> itemFlags;
    private final Map<Enchantment, Integer> enchants;

    public ItemMetaSnapshot(@Nonnull Supplier<ItemMeta> supplier) {
        this(supplier.get());
    }

    public ItemMetaSnapshot(@Nonnull ItemStack item) {
        this(item.getItemMeta());
    }

    public ItemMetaSnapshot(@Nonnull ItemMeta meta) {
        this.displayName = meta.hasDisplayName() ? Optional.of(meta.getDisplayName()) : Optional.empty();
        this.lore = meta.hasLore() ? Optional.of(meta.getLore()) : Optional.empty();
        this.customModelData = meta.hasCustomModelData() ? OptionalInt.of(meta.getCustomModelData()) : OptionalInt.empty();

        this.itemFlags = meta.getItemFlags();
        this.enchants = meta.getEnchants();
    }
}
