package com.enderio.base.common.enchantment;

import com.enderio.base.common.Configs;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class AutoSmeltEnchantment extends EIOBaseEnchantment {

    public AutoSmeltEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[] { EquipmentSlot.MAINHAND }, () -> true);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return Configs.COMMON.enchantments.autoSmelt.maxCost;
    }

    @Override
    public int getMinCost(int pLevel) {
        return Configs.COMMON.enchantments.autoSmelt.minCost;
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return super.checkCompatibility(pOther) && pOther != Enchantments.SILK_TOUCH;
    }
}
