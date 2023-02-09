package com.enderio.api.capability;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

/**
 * Implement for an item that should use the {@link MultiCapabilityProvider} when initializing capabilities.
 */
public interface IMultiCapabilityItem {
    @Nullable
    default ComponentProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return initCapabilities(stack, nbt, new MultiCapabilityProvider());
    }

    @Nullable ComponentProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt, MultiCapabilityProvider provider);
}
