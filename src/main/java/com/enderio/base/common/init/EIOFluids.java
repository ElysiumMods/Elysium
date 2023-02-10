package com.enderio.base.common.init;

import com.enderio.EnderIO;
import com.enderio.base.common.item.EIOCreativeTabs;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.FluidBuilder;
import com.tterrag.registrate.fabric.SimpleFlowableFluid;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.entry.FluidEntry;
import dev.architectury.core.fluid.ArchitecturyFlowingFluid;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;

// TODO: Fluid behaviours and some cleaning. https://github.com/SleepyTrousers/EnderIO-Rewrite/issues/34

// TODO: Registrate tint color support, it was some reason omitted in my original PR

@SuppressWarnings("unused")
public class EIOFluids {
    private static final Registrate REGISTRATE = EnderIO.registrate();

    public static final FluidEntry<? extends SimpleFlowableFluid> NUTRIENT_DISTILLATION = fluid("nutrient_distillation")
        .register();

    public static final FluidEntry<? extends SimpleFlowableFluid> DEW_OF_THE_VOID = fluid("dew_of_the_void")
        .lang("Fluid of the Void")
        .register();

    public static final FluidEntry<? extends SimpleFlowableFluid> VAPOR_OF_LEVITY = gasFluid("vapor_of_levity")
        .register();

    public static final FluidEntry<? extends SimpleFlowableFluid> HOOTCH = fluid("hootch")
        .register();

    public static final FluidEntry<? extends SimpleFlowableFluid> ROCKET_FUEL = fluid("rocket_fuel")
        .register();

    public static final FluidEntry<? extends SimpleFlowableFluid> FIRE_WATER = fluid("fire_water")
        .register();

    public static final FluidEntry<? extends SimpleFlowableFluid> XP_JUICE = fluid("xp_juice")
        .register();

    public static final FluidEntry<? extends SimpleFlowableFluid> LIQUID_SUNSHINE = fluid("liquid_sunshine")
        .register();

    public static final FluidEntry<? extends SimpleFlowableFluid> CLOUD_SEED = fluid("cloud_seed")
        .register();

    public static final FluidEntry<? extends SimpleFlowableFluid> CLOUD_SEED_CONCENTRATED = fluid("cloud_seed_concentrated")
        .register();

    private static FluidBuilder<? extends SimpleFlowableFluid, Registrate> fluid(String name) {
        return baseFluid(name)
            .bucket()
            .model(EIOFluids::bucketModel)
            .tab(() -> EIOCreativeTabs.MAIN)
            .build();
    }

    private static FluidBuilder<? extends SimpleFlowableFluid, Registrate> gasFluid(String name) {
        return baseFluid(name)
            .bucket()
            .model((ctx, prov) -> bucketModel(ctx, prov).flipGas(true))
            .tab(() -> EIOCreativeTabs.MAIN)
            .build();
    }

    private static FluidBuilder<? extends SimpleFlowableFluid, Registrate> baseFluid(String name) {
        return REGISTRATE.fluid(name, EnderIO.loc("block/fluid_" + name + "_still"),
            EnderIO.loc("block/fluid_" + name + "_flowing"))
            .source(SimpleFlowableFluid.Source::new)
            .block()
            .build();
    }

    private static DynamicFluidContainerModelBuilder<ItemModelBuilder> bucketModel(DataGenContext<Item, BucketItem> ctx, RegistrateItemModelProvider prov) {
        return prov
            .withExistingParent(ctx.getName(), new ResourceLocation(EnderIO.MODID, "item/bucket"))
            .customLoader(DynamicFluidContainerModelBuilder::begin)
            .fluid(ctx.get().asItem());
    }

    public static void register() {}
}
