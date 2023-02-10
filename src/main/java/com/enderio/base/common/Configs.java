package com.enderio.base.common;

import com.enderio.EnderIO;
import org.apache.commons.lang3.tuple.Pair;
import org.quiltmc.config.api.WrappedConfig;
import org.quiltmc.config.api.annotations.Comment;
import org.quiltmc.loader.api.config.QuiltConfig;

public class Configs {
    public static final Common COMMON = QuiltConfig.create(EnderIO.MODID, "common", Common.class);
    //public static final ForgeConfigSpec COMMON_SPEC;

    public static final Client CLIENT = QuiltConfig.create(EnderIO.MODID, "client", Client.class);

    public class Client extends WrappedConfig {
        @Comment("Enable machine particles")
        public final Boolean MACHINE_PARTICLES = true;
    }

    public class Common extends WrappedConfig {
        public static class BlocksConfigs implements Section {
            @Comment("The chance of a spawner dropping a broken spawner.")
            public final float dropChance = 1.0f;
            @Comment("The explosion resistance of explosion resistant blocks.")
            public final float explosionResistance = 1200.0f;
            @Comment("The speed boost granted by the Dark Steel ladder.")
            public final float darkSteelLadderBoost = 0.15f;
            @Comment("The color of the range box of Vacuum Chest")
            public final String vacuumChestRangeColor = "0000FF";
            @Comment("The color of the range box of XP Vacuum")
            public final String vacuumXpRangeColor = "00FF00";

        }
        public static class DarkSteelConfigs implements Section {
            public static class AxeConfigs implements Section {
                public final int energyUsePerFelledLog = 1500;
            }
            public static class PickaxeConfigs implements Section {
                public final int speedBoostWhenObsidian = 50;
                public final int useObsidianBreakSpeedAtHardness = 30;
                public final int obsidianBreakPowerUse = 50;
            }
            public static class UpgradesConfigs implements Section {
                public static class EmpoweredConfigs implements Section {
                    public final int efficiencyBoost = 2;
                    public final int energyUsePerDamagePoint = 750;
                    public final int activationCost_l1 = 4;
                    public final float damageAbsorptionChance_l1 = 0.5f;
                    public final int maxEnergy_l1 = 100000;
                    public final int activationCost_l2 = 8;
                    public final float damageAbsorptionChance_l2 = 0.6f;
                    public final int maxEnergy_l2 = 150000;
                    public final int activationCost_l3 = 12;
                    public final float damageAbsorptionChance_l3 = 0.7f;
                    public final int maxEnergy_l3 = 250000;
                    public final int activationCost_l4 = 16;
                    public final float damageAbsorptionChance_l4 = 0.85f;
                    public final int maxEnergy_l4 = 1000000;
                }
                public static class ExplosiveConfigs implements Section {
                    public final int explosiveEnergyPerBlock = 30;
                    public final int explosiveI = 1;
                    public final int explosiveActivationCostI = 8;
                    public final int explosiveActivationCostII = 12;
                    public final int explosiveII = 2;
                    public final int explosivePenetrationActivationCostI = 8;
                    public final int explosivePenetrationI = 1;
                    public final int explosivePenetrationActivationCostII = 12;
                    public final int explosivePenetrationII = 2;
                }
                public final EmpoweredConfigs empowered = new EmpoweredConfigs();

                public final ExplosiveConfigs explosive = new ExplosiveConfigs();

                public final int spoonActivationCost = 4;
                public final int forkActivationCost = 4;
                public final int directActivationCost = 4;

            }
            public final AxeConfigs axe = new AxeConfigs();
            public final PickaxeConfigs pickaxe = new PickaxeConfigs();
            public final UpgradesConfigs upgrades = new UpgradesConfigs();
        }
        public static class EnchantmentsConfigs implements Section {
            public static class AutoSmelt implements Section {
                public final int maxCost = 60;
                public final int minCost = 15;
            }
            public static class Repellent implements Section {
                public final int maxLevel = 4;
                public final int maxCostBase = 10;
                public final int maxCostPerLevel = 10;
                public final int minCostBase = 10;
                public final int minCostPerLevel = 10;
                public final float chanceBase = 0.35f;
                public final float chancePerLevel = 0.1f;
                public final double rangeBase = 8d;
                public final double rangePerLevel = 8d;
                public final float nonPlayerChance = 0.75f;
            }
            public static class Shimmer implements Section {
                public final int maxCost = 100;
                public final int minCost = 1;
            }
            public static class SoulBound implements Section {
                public final int maxCost = 60;
                public final int minCost = 16;
            }
            public static class WitheringBlade implements Section {
                public final int maxCost = 100;
                public final int minCost = 1;
            }
            public static class WitheringArrow implements Section {
                public final int maxCost = 100;
                public final int minCost = 1;
            }
            // TODO: DEFAULTS FOR CROSSBOW ENCHANTMENTS
            public static class WitheringBolt implements Section {
                public final int maxCost = 100;
                public final int minCost = 1;
            }
            public static class XPBoost implements Section {
                public final int maxCostBase = 30;
                public final int maxCostPerLevel = 10;
                public final int minCostBase = 1;
                public final int minCostPerLevel = 10;
            }
            public final AutoSmelt autoSmelt = new AutoSmelt();
            public final Repellent repellent = new Repellent();
            public final Shimmer shimmer = new Shimmer();
            public final SoulBound soulBound = new SoulBound();
            public final WitheringBlade witheringBlade = new WitheringBlade();
            public final WitheringArrow witheringArrow = new WitheringArrow();
            public final WitheringBolt witheringBolt = new WitheringBolt();
            public final XPBoost xpBoost = new XPBoost();

        }
        public static class GraveConfigs implements Section {
            @Comment("Enable grave generation")
            public final boolean enableGrave = true;
        }
        public static class InfinityConfigs implements Section {
            @Comment("Should it make a sound when Grains of Infinity drops from a fire?")
            public final boolean makesSound = true;
            @Comment("How old (in ticks) does a fire have to be to be able to spawn Infinity Powder?")
            public final int fireMinAge = 260; // TODO: Work out ranges

        }
        public static class ItemsConfigs implements Section {
            public static class Food implements Section {
                @Comment("The chance of enderios teleporting the player")
                public final float enderioChance = 0.3f;
                @Comment("The range of an enderio teleport")
                public final float enderioRange = 16.0f;
            }
            public static class Electromagnet implements Section {
                public final int energyUse = 1;
                public final int maxEnergy = 100000;
                public final int range = 5;
                public final int maxItems = 20;
            }
            public static class LevitationStaff implements Section {
                public final int energyUse = 1;
                public final int maxEnergy = 1000;
            }
            // TODO: SoulVial config: uses lists, unsure how to do that with this setup
            public final Food food = new Food();
            public final Electromagnet electromagnet = new Electromagnet();
            public final LevitationStaff levitationStaff = new LevitationStaff();
        }

        public final BlocksConfigs blocks = new BlocksConfigs();
        public final DarkSteelConfigs darkSteel = new DarkSteelConfigs();
        public final EnchantmentsConfigs enchantments = new EnchantmentsConfigs();
        public final GraveConfigs grave = new GraveConfigs();
        public final InfinityConfigs grainsOfInfinity = new InfinityConfigs();
        public final ItemsConfigs items = new ItemsConfigs();
    }
}
