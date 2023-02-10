package com.enderio.base.common.item.darksteel.upgrades.explosive;

import com.enderio.api.capability.IDarkSteelUpgrade;
import com.enderio.base.common.Configs;
import com.enderio.base.common.config.BaseConfig;
import com.enderio.base.common.item.darksteel.upgrades.IUpgradeTier;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

import static com.enderio.base.common.lang.EIOLang.DS_UPGRADE_EXPLOSIVE_I;
import static com.enderio.base.common.lang.EIOLang.DS_UPGRADE_EXPLOSIVE_II;

public enum ExplosiveUpgradeTier implements IUpgradeTier {

    ONE(Configs.COMMON.darkSteel.upgrades.explosive.explosiveI,
        Configs.COMMON.darkSteel.upgrades.explosive.explosiveActivationCostI,
        DS_UPGRADE_EXPLOSIVE_I),
    TWO(Configs.COMMON.darkSteel.upgrades.explosive.explosiveII,
        Configs.COMMON.darkSteel.upgrades.explosive.explosiveActivationCostII,
        DS_UPGRADE_EXPLOSIVE_II);

    private final Supplier<IDarkSteelUpgrade> factory;
    private final int magnitude;
    private final int activationCost;
    private final Component displayName;

    ExplosiveUpgradeTier(int magnitude, int activationCost,
        Component displayName) {
        this.magnitude = magnitude;
        this.activationCost = activationCost;
        this.displayName = displayName;
        factory = () -> new ExplosiveUpgrade(this);
    }

    public int getMagnitude() {
        return magnitude;
    }

    @Override
    public Supplier<IDarkSteelUpgrade> getFactory() {
        return factory;
    }

    @Override
    public int getLevel() {
        return ordinal();
    }

    @Override
    public Component getDisplayName() {
        return displayName;
    }

    public int getActivationCost() {
        return activationCost;
    }
}
