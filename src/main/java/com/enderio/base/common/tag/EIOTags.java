package com.enderio.base.common.tag;

import com.enderio.EnderIO;
import com.enderio.base.common.block.glass.GlassCollisionPredicate;
import com.enderio.base.common.block.glass.GlassIdentifier;
import com.enderio.base.common.block.glass.GlassLighting;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
//import net.minecraftforge.versions.forge.ForgeVersion;

import java.util.HashMap;
import java.util.Map;

public class EIOTags {

    public static void register() {
        Items.init();
        Blocks.init();
        Fluids.init();
    }

    public static class Items {

        private static void init() {}
    
        public static final TagKey<Item> WRENCH = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "tools/wrench"));

        public static final TagKey<Item> DUSTS_LAPIS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "dusts/lapis"));
        public static final TagKey<Item> DUSTS_COAL = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "dusts/coal"));
        public static final TagKey<Item> DUSTS_IRON = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "dusts/iron"));
        public static final TagKey<Item> DUSTS_GOLD = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "dusts/gold"));
        public static final TagKey<Item> DUSTS_COPPER = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "dusts/copper"));
        public static final TagKey<Item> DUSTS_TIN = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "dusts/tin"));
        public static final TagKey<Item> DUSTS_ENDER = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "dusts/ender"));
        public static final TagKey<Item> DUSTS_OBSIDIAN = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "dusts/obsidian"));
        public static final TagKey<Item> DUSTS_ARDITE = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "dusts/ardite"));
        public static final TagKey<Item> DUSTS_COBALT = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "dusts/cobalt"));
        public static final TagKey<Item> DUSTS_QUARTZ = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "dusts/quartz"));
        public static final TagKey<Item> DUSTS_SULFUR = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "dusts/sulfur"));

        public static final TagKey<Item> INSULATION_METAL = TagKey.create(Registry.ITEM_REGISTRY, EnderIO.loc("insulation_metals"));
        
        public static final TagKey<Item> SILICON = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "silicon"));
        public static final TagKey<Item> GEARS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "gears"));
        public static final TagKey<Item> GEARS_WOOD = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "gears/wood"));
        public static final TagKey<Item> GEARS_STONE = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "gears/stone"));
        public static final TagKey<Item> GEARS_IRON = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "gears/stone"));
        public static final TagKey<Item> GEARS_ENERGIZED = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "gears/energized"));
        public static final TagKey<Item> GEARS_VIBRANT = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "gears/vibrant"));
        public static final TagKey<Item> GEARS_DARK_STEEL = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", "gears/dark_steel"));

        public static final TagKey<Item> FUSED_QUARTZ = TagKey.create(Registry.ITEM_REGISTRY, EnderIO.loc("fused_quartz"));
        public static final TagKey<Item> CLEAR_GLASS = TagKey.create(Registry.ITEM_REGISTRY, EnderIO.loc("clear_glass"));

        public static final TagKey<Item> BROKEN_SPAWNER_BLACKLIST = TagKey.create(Registry.ITEM_REGISTRY, EnderIO.loc("blacklists/broken_spawner"));
        public static final TagKey<Item> ELECTROMAGNET_BLACKLIST = TagKey.create(Registry.ITEM_REGISTRY, EnderIO.loc("blacklists/electromagnet"));

        public static final Map<GlassIdentifier, TagKey<Item>> GLASS_TAGS = createGlassTags();

        public static Map<GlassIdentifier, TagKey<Item>> createGlassTags() {
            Map<GlassIdentifier, TagKey<Item>> map = new HashMap<>();
            for (GlassLighting lighting: GlassLighting.values()) {
                for (GlassCollisionPredicate collisionPredicate: GlassCollisionPredicate.values()) {
                    for (Boolean isFused: new boolean[]{false, true}) {
                        GlassIdentifier identifier = new GlassIdentifier(lighting, collisionPredicate, isFused);
                        map.put(identifier, TagKey.create(Registry.ITEM_REGISTRY, EnderIO.loc(identifier.glassName())));
                    }
                }
            }
            return map;
        }
    }

    public static class Blocks {

        private static void init() {}

        public static final TagKey<Block> FUSED_QUARTZ = TagKey.create(Registry.BLOCK_REGISTRY, EnderIO.loc("fused_quartz"));
        public static final TagKey<Block> CLEAR_GLASS = TagKey.create(Registry.BLOCK_REGISTRY, EnderIO.loc("clear_glass"));
        public static final TagKey<Block> DARK_STEEL_TIER = TagKey.create(Registry.BLOCK_REGISTRY, EnderIO.loc("needs_dark_steel"));
        public static final TagKey<Block> DARK_STEEL_EXPLODABLE_DENY_LIST = TagKey.create(Registry.BLOCK_REGISTRY, EnderIO.loc("dark_steel_explodable_deny_list"));
        public static final TagKey<Block> DARK_STEEL_EXPLODABLE_ALLOW_LIST = TagKey.create(Registry.BLOCK_REGISTRY, EnderIO.loc("dark_steel_explodable_allow_list"));

    }
    
    public static class Fluids {
        private static void init() {}

        public static final TagKey<Fluid> COLD_FIRE_IGNITER_FUEL = TagKey.create(Registry.FLUID_REGISTRY, EnderIO.loc("fluid_fuel/cold_fire_igniter"));
        public static final TagKey<Fluid> STAFF_OF_LEVITY_FUEL = TagKey.create(Registry.FLUID_REGISTRY, EnderIO.loc("fluid_fuel/staff_of_levity"));

    }
}
