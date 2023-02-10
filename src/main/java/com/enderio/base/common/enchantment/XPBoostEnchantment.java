package com.enderio.base.common.enchantment;

import com.enderio.base.common.Configs;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class XPBoostEnchantment extends EIOBaseEnchantment {

    public XPBoostEnchantment() {
        super(Rarity.COMMON, EIOEnchantmentCategories.XPBOOST, new EquipmentSlot[] { EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND }, () -> true);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return Configs.COMMON.enchantments.xpBoost.maxCostBase + Configs.COMMON.enchantments.xpBoost.maxCostPerLevel * pLevel;
    }

    @Override
    public int getMinCost(int pLevel) {
        return Configs.COMMON.enchantments.xpBoost.minCostBase + Configs.COMMON.enchantments.xpBoost.minCostPerLevel * pLevel;
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return super.checkCompatibility(pOther) && pOther != Enchantments.SILK_TOUCH;
    }
}
