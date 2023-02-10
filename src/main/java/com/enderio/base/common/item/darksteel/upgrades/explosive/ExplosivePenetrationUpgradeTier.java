package com.enderio.base.common.item.darksteel.upgrades.explosive;

import com.enderio.api.capability.IDarkSteelUpgrade;
import com.enderio.base.common.Configs;
import com.enderio.base.common.config.BaseConfig;
import com.enderio.base.common.item.darksteel.upgrades.IUpgradeTier;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

import static com.enderio.base.common.lang.EIOLang.DS_UPGRADE_EXPLOSIVE_PENETRATION_I;
import static com.enderio.base.common.lang.EIOLang.DS_UPGRADE_EXPLOSIVE_PENETRATION_II;

public enum ExplosivePenetrationUpgradeTier implements IUpgradeTier {

    ONE(Configs.COMMON.darkSteel.upgrades.explosive.explosivePenetrationI, Configs.COMMON.darkSteel.upgrades.explosive.explosivePenetrationActivationCostI,
        DS_UPGRADE_EXPLOSIVE_PENETRATION_I),
    TWO(Configs.COMMON.darkSteel.upgrades.explosive.explosivePenetrationII, Configs.COMMON.darkSteel.upgrades.explosive.explosivePenetrationActivationCostII,
        DS_UPGRADE_EXPLOSIVE_PENETRATION_II);

    private final Supplier<IDarkSteelUpgrade> factory;
    private final int magnitude;
    private final int activationCost;
    private final Component displayName;

    ExplosivePenetrationUpgradeTier(int magnitude, int activationCost, Component displayName) {
        this.magnitude = magnitude;
        this.activationCost = activationCost;
        this.displayName = displayName;
        factory = () -> new ExplosivePenetrationUpgrade(this);
    }

    public int getMagnitude() {
        return magnitude;
    }

    public Supplier<IDarkSteelUpgrade> getFactory() {
        return factory;
    }

    public int getActivationCost() {
        return activationCost;
    }

    public Component getDisplayName() {
        return displayName;
    }

    @Override
    public int getLevel() {
        return ordinal();
    }
}
