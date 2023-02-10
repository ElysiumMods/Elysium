package com.enderio.base.common.enchantment;

import com.enderio.base.common.Configs;
import com.enderio.core.common.util.TeleportUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class RepellentEnchantment extends EIOBaseEnchantment {
    public RepellentEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR, new EquipmentSlot[] { EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET }, () -> true);
    }

    @Override
    public int getMaxLevel() {
        return Configs.COMMON.enchantments.repellent.maxLevel;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return Configs.COMMON.enchantments.repellent.maxCostBase + Configs.COMMON.enchantments.repellent.maxCostPerLevel * pLevel;
    }

    @Override
    public int getMinCost(int pLevel) {
        return Configs.COMMON.enchantments.repellent.minCostBase + Configs.COMMON.enchantments.repellent.minCostPerLevel * pLevel;
    }

    private float getChance(int level) {
        return Configs.COMMON.enchantments.repellent.chanceBase + Configs.COMMON.enchantments.repellent.chancePerLevel * level;
    }

    private double getRange(int level) {
        return Configs.COMMON.enchantments.repellent.rangeBase + Configs.COMMON.enchantments.repellent.rangePerLevel * level;
    }

    @Override
    public void doPostHurt(LivingEntity pUser, Entity pAttacker, int pLevel) {
        if (pUser instanceof Player && pAttacker instanceof LivingEntity attacker) {
            if (pUser.getRandom().nextFloat() < getChance(pLevel)) {
                if (pAttacker instanceof Player) {
                    TeleportUtils.randomTeleport(attacker, getRange(pLevel));
                } else if (pUser.getRandom().nextFloat() < Configs.COMMON.enchantments.repellent.nonPlayerChance) {
                    TeleportUtils.randomTeleport(attacker, getRange(pLevel));
                }
            }
        }
    }
}
