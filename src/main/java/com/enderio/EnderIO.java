package com.enderio;

import com.enderio.base.common.config.BaseConfig;
import com.enderio.base.common.Configs;
import com.enderio.base.common.init.*;
import com.enderio.base.common.item.tool.SoulVialItem;
import com.enderio.base.common.lang.EIOLang;
import com.enderio.base.common.tag.EIOTags;
import com.enderio.base.data.loot.FireCraftingLootProvider;
import com.enderio.base.data.recipe.*;
import com.enderio.base.data.tags.EIOBlockTagsProvider;
import com.enderio.base.data.tags.EIOFluidTagsProvider;
import com.enderio.base.data.tags.EIOItemTagsProvider;
import com.enderio.core.EnderCore;
import com.enderio.core.common.network.CoreNetwork;
import com.tterrag.registrate.Registrate;
import io.github.fabricators_of_create.porting_lib.util.Lazy;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.config.QuiltConfig;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

//@Mod(EnderIO.MODID)
public class EnderIO implements ModInitializer {
    // The Mod ID. This is stored in EnderCore as its the furthest source away but it ensures that it is constant across all source sets.
    public static final String MODID = EnderCore.MODID;

    private static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MODID));

    public static final Logger LOGGER = LogManager.getLogger(MODID);



    public static ResourceLocation loc(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static Registrate registrate() {
        return REGISTRATE.get();
    }

    public void onInitialize(ModContainer mod) {
        // Ensure the enderio config subdirectory is present.
        /*try {
            Files.createDirectories(FMLPaths.CONFIGDIR.get().resolve(MODID));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        // Register config files

        // Setup core networking now
        CoreNetwork.networkInit();

        // Perform initialization and registration for everything so things are registered.
        EIOItems.register();
        EIOBlocks.register();
        EIOBlockEntities.register();
        EIOFluids.register();
        EIOEnchantments.register();
        EIOTags.register();
        EIOMenus.register();
        EIOPackets.register();
        EIOLang.register();
        EIORecipes.register();
        EIOLootModifiers.register();
        EIOParticles.register();

        // Run datagen after registrate is finished.
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(EventPriority.LOWEST, this::onGatherData);
        modEventBus.addListener(SoulVialItem::onCommonSetup);
    }

    public void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(event.includeServer(), new MaterialRecipes(generator));
        generator.addProvider(event.includeServer(), new BlockRecipes(generator));
        generator.addProvider(event.includeServer(), new ItemRecipes(generator));
        generator.addProvider(event.includeServer(), new GrindingBallRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new GlassRecipes(generator));
        generator.addProvider(event.includeServer(), new FireCraftingRecipes(generator));

        ForgeBlockTagsProvider b = new ForgeBlockTagsProvider(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), new EIOItemTagsProvider(generator, b, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new EIOFluidTagsProvider(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new EIOBlockTagsProvider(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new FireCraftingLootProvider(generator));
    }
}
