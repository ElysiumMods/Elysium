package com.enderio.base.common.enchantment;

import com.enderio.base.common.Configs;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SoulBoundEnchantment extends EIOBaseEnchantment {

    public SoulBoundEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.VANISHABLE, EquipmentSlot.values(), () -> true);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return Configs.COMMON.enchantments.soulBound.maxCost;
    }

    @Override
    public int getMinCost(int pLevel) {
        return Configs.COMMON.enchantments.soulBound.minCost;
    }
}
