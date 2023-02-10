package com.enderio.base.common.item.darksteel;

import com.enderio.base.common.Configs;
import com.enderio.base.common.capability.DarkSteelUpgradeable;
import com.enderio.base.common.init.EIOItems;
import com.enderio.base.common.item.darksteel.upgrades.EmpoweredUpgrade;
import com.enderio.base.common.item.darksteel.upgrades.SpoonUpgrade;
import com.enderio.base.common.item.darksteel.upgrades.explosive.ExplosivePenetrationUpgrade;
import com.enderio.base.common.item.darksteel.upgrades.explosive.ExplosiveUpgrade;
import com.enderio.base.common.item.darksteel.upgrades.explosive.ExplosiveUpgradeHandler;
import com.enderio.base.common.lang.EIOLang;
import com.enderio.core.common.util.EnergyUtil;
import com.enderio.core.common.util.TooltipUtil;
import io.github.fabricators_of_create.porting_lib.util.ToolAction;
import io.github.fabricators_of_create.porting_lib.util.ToolActions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class DarkSteelPickaxeItem extends PickaxeItem implements IDarkSteelItem {

    public DarkSteelPickaxeItem(Properties pProperties) {
        super(EIOItems.DARK_STEEL_TIER, 1, -2.8F, pProperties);
    }

    @Override
    public void setDamage(final ItemStack stack, final int newDamage) {
        int finalDamage = getEmpoweredUpgrade(stack).map(empoweredUpgrade -> empoweredUpgrade.adjustDamage(getDamage(stack), newDamage)).orElse(newDamage);
        super.setDamage(stack, finalDamage);
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        final float baseSpeed = canHarvest(pStack, pState) ? speed : 1.0f;
        float adjustedSpeed = getEmpoweredUpgrade(pStack).map(empoweredUpgrade -> empoweredUpgrade.adjustDestroySpeed(baseSpeed)).orElse(baseSpeed);
        adjustedSpeed = ExplosiveUpgradeHandler.adjustDestroySpeed(adjustedSpeed, pStack);
        if (useObsidianMining(pState, pStack)) {
            adjustedSpeed += Configs.COMMON.darkSteel.pickaxe.speedBoostWhenObsidian;
        }
        return adjustedSpeed;
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (useObsidianMining(pState, pStack)) {
            EnergyUtil.extractEnergy(pStack, Configs.COMMON.darkSteel.pickaxe.obsidianBreakPowerUse, false);
        }
        ExplosiveUpgradeHandler.onMineBlock(pStack, pLevel, pPos, pEntityLiving);
        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return canHarvest(stack, state) && TierSortingRegistry.isCorrectTierForDrops(getTier(), state);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (hasSpoon(pContext.getItemInHand())) {
            return Items.DIAMOND_SHOVEL.useOn(pContext);
        }
        return super.useOn(pContext);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return super.canPerformAction(stack, toolAction) || (hasSpoon(stack) && ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction));
    }

    @Override
    public void addCreativeItems(NonNullList<ItemStack> pItems, Item item) {
        IDarkSteelItem.super.addCreativeItems(pItems, item);
        //Include a fully upgraded version without explosive upgrades
        ItemStack itemStack = createFullyUpgradedStack(item);
        DarkSteelUpgradeable.removeUpgrade(itemStack, ExplosiveUpgrade.NAME);
        DarkSteelUpgradeable.removeUpgrade(itemStack, ExplosivePenetrationUpgrade.NAME);
        pItems.add(itemStack);
    }

    private boolean canHarvest(ItemStack stack, BlockState state) {
        return state.is(BlockTags.MINEABLE_WITH_PICKAXE) || (state.is(BlockTags.MINEABLE_WITH_SHOVEL) && hasSpoon(stack));
    }

    private boolean hasSpoon(ItemStack stack) {
        return DarkSteelUpgradeable.hasUpgrade(stack, SpoonUpgrade.NAME);
    }

    private boolean useObsidianMining(BlockState pState, ItemStack stack) {
        return EnergyUtil.getEnergyStored(stack) >= Configs.COMMON.darkSteel.pickaxe. && treatBlockAsObsidian(pState);
    }

    private boolean treatBlockAsObsidian(BlockState pState) {
        return pState.getBlock() == Blocks.OBSIDIAN || (Configs.COMMON.darkSteel.pickaxe.useObsidianBreakSpeedAtHardness > 0
            && pState.getBlock().defaultDestroyTime() >= Configs.COMMON.darkSteel.pickaxe.useObsidianBreakSpeedAtHardness);
    }

    @Override
    public void addCurrentUpgradeTooltips(ItemStack itemStack, List<Component> tooltips, boolean isDetailed) {
        if(isDetailed && getEmpoweredUpgrade(itemStack).isPresent()) {
            tooltips.add(TooltipUtil.withArgs(EIOLang.DS_UPGRADE_EMPOWERED_EFFICIENCY, Configs.COMMON.darkSteel.upgrades.empowered.efficiencyBoost));
            tooltips.add(TooltipUtil.withArgs(EIOLang.DS_UPGRADE_EMPOWERED_OBSIDIAM_EFFICIENCY, Configs.COMMON.darkSteel.pickaxe.speedBoostWhenObsidian));
        }
        IDarkSteelItem.super.addCurrentUpgradeTooltips(itemStack, tooltips, isDetailed);
    }

    // region Common for all tools

    @Override
    public boolean isFoil(ItemStack pStack) {
        return DarkSteelUpgradeable.hasUpgrade(pStack, EmpoweredUpgrade.NAME);
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (allowedIn(pCategory)) {
            addCreativeItems(pItems, this);
        }
    }

    // endregion

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return isDurabilityBarVisible(pStack);
    }
}
