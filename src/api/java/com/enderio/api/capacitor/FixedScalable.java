package com.enderio.api.capacitor;

import io.github.fabricators_of_create.porting_lib.util.NonNullSupplier;

import java.util.function.Supplier;

/**
 * A fixed value that implements ICapacitorScalable.
 * This is a utility and will always return the value provided in the constructor.
 */
public record FixedScalable(Supplier<Float> value) implements ICapacitorScalable {
    @Override
    public Supplier<Float> scaleF(NonNullSupplier<ICapacitorData> data) {
        return value;
    }

    @Override
    public Supplier<Integer> scaleI(NonNullSupplier<ICapacitorData> data) {
        return () -> Math.round(value.get());
    }
}
