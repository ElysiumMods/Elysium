package com.enderio.api.nbt;

import io.github.fabricators_of_create.porting_lib.extensions.INBTSerializable;
import net.minecraft.nbt.Tag;

/**
 * Makes an NBT serializable object have it's own name.
 */
public interface INamedNBTSerializable<T extends Tag> extends INBTSerializable<T> {
    /**
     * Get the serialized name.
     * Must not change based on the state!
     */
    String getSerializedName();
}
