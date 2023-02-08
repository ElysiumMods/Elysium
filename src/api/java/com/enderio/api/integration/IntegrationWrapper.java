package com.enderio.api.integration;

import io.github.fabricators_of_create.porting_lib.util.LazyOptional;
import io.github.fabricators_of_create.porting_lib.util.NonNullConsumer;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.QuiltLoader;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class IntegrationWrapper<T extends Integration> {

    private final String modid;

    @Nullable
    private final T value;

    public IntegrationWrapper(String modid, Supplier<T> supplier) {
        this.modid = modid;
        value = QuiltLoader.isModLoaded(modid) ? supplier.get() : null;
        ifPresent(integration -> {
            integration.setModid(modid);
            IntegrationManager.addIntegration(integration);
            //integration.addEventListener(FMLJavaModLoadingContext.get().getModEventBus(), MinecraftForge.EVENT_BUS);
        });
    }

    public boolean isPresent() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return value == null;
    }

    /**
     * If non-empty, invoke the specified {@link NonNullConsumer} with the object,
     * otherwise do nothing.
     *
     * @param consumer The {@link NonNullConsumer} to run if this optional is non-empty.
     * @throws NullPointerException if {@code consumer} is null and this {@link LazyOptional} is non-empty
     */
    public void ifPresent(Consumer<? super T> consumer) {
        if (isPresent())
            consumer.accept(value);
    }
}