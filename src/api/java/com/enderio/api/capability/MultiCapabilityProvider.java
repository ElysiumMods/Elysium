package com.enderio.api.capability;

import com.enderio.api.nbt.INamedNBTSerializable;
import dev.onyxstudios.cca.api.v3.component.Component;
import io.github.fabricators_of_create.porting_lib.extensions.INBTSerializable;
import io.github.fabricators_of_create.porting_lib.util.LazyOptional;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import org.jetbrains.annotations.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Automatically combines multiple capabilities together; handling serialization too.
 */
public class MultiCapabilityProvider  {
    private final Map<Component, LazyOptional<?>> capabilities;
    private final Map<String, LazyOptional<? extends INBTSerializable<Tag>>> serializedCaps;
    private int serializedNameCounter = 0;

    public MultiCapabilityProvider() {
        capabilities = new HashMap<>();
        serializedCaps = new HashMap<>();
    }

    public <T> void addSimple(Component cap, LazyOptional<?> optional) {
        capabilities.putIfAbsent(cap, optional);
    }

    public <T extends INamedNBTSerializable<Tag>> void addSerialized(Component cap, LazyOptional<? extends T> optional) {
        capabilities.putIfAbsent(cap, optional);
        serializedCaps.putIfAbsent("pend_" + serializedNameCounter++, optional);
    }

    public <T> void addSerialized(String serializedName, Component cap, LazyOptional<? extends INBTSerializable<Tag>> optional) {
        capabilities.putIfAbsent(cap, optional);
        serializedCaps.putIfAbsent(serializedName, optional);
    }

    public LazyOptional<Object> getCapability(Component cap, @Nullable Direction side) {
        return capabilities
            .getOrDefault(cap, LazyOptional.empty())
            .cast();
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        for (var entry : serializedCaps.entrySet()) {
            entry.getValue().ifPresent(
                capability -> tag.put(getSerializedName(entry.getKey(), capability), capability.serializeNBT())
            );
        }
        return tag;
    }

    public void deserializeNBT(CompoundTag nbt) {
        for (var entry : serializedCaps.entrySet()) {
            entry
                .getValue()
                .ifPresent(capability -> {
                    String key = getSerializedName(entry.getKey(), capability);

                    if (nbt.contains(key)) {
                        capability.deserializeNBT(nbt.get(key));
                    }
                });
        }
    }

    private String getSerializedName(String key, INBTSerializable<Tag> capability) {
        if (capability instanceof INamedNBTSerializable<Tag> named) {
            key = named.getSerializedName();
        }

        if (key.startsWith("pend_")) {
            // TODO: Logging somehow...
//            EnderIO.LOGGER.warn("A INamedNBTSerializable didn't return a valid name, a pending name has been mapped instead!");
        }

        return key;
    }
}
