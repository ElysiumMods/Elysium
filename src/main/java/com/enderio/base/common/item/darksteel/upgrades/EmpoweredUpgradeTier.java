package com.enderio.base.common.item.darksteel.upgrades;

import com.enderio.api.capability.IDarkSteelUpgrade;
import com.enderio.base.common.Configs;
import com.enderio.base.common.lang.EIOLang;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

public enum EmpoweredUpgradeTier implements IUpgradeTier {

    ONE(Configs.COMMON.darkSteel.upgrades.empowered.maxEnergy_l1,
        Configs.COMMON.darkSteel.upgrades.empowered.damageAbsorptionChance_l1,
        Configs.COMMON.darkSteel.upgrades.empowered.activationCost_l1,
        EIOLang.DS_UPGRADE_EMPOWERED_I),
    TWO(Configs.COMMON.darkSteel.upgrades.empowered.maxEnergy_l2,
        Configs.COMMON.darkSteel.upgrades.empowered.damageAbsorptionChance_l2,
        Configs.COMMON.darkSteel.upgrades.empowered.activationCost_l2,
        EIOLang.DS_UPGRADE_EMPOWERED_II),
    THREE(Configs.COMMON.darkSteel.upgrades.empowered.maxEnergy_l3,
        Configs.COMMON.darkSteel.upgrades.empowered.damageAbsorptionChance_l3,
        Configs.COMMON.darkSteel.upgrades.empowered.activationCost_l3,
        EIOLang.DS_UPGRADE_EMPOWERED_III),
    FOUR(Configs.COMMON.darkSteel.upgrades.empowered.maxEnergy_l4,
        Configs.COMMON.darkSteel.upgrades.empowered.damageAbsorptionChance_l4,
        Configs.COMMON.darkSteel.upgrades.empowered.activationCost_l4,
        EIOLang.DS_UPGRADE_EMPOWERED_IV);

    private final Supplier<IDarkSteelUpgrade> factory;
    private final int maxStorage;
    private final float damageAbsorptionChance;
    private final int activationCost;
    private final Component displayName;

    EmpoweredUpgradeTier(int maxStorage, float damageAbsorptionChance,
        int activationCost, Component displayName) {
        this.maxStorage = maxStorage;
        this.damageAbsorptionChance = damageAbsorptionChance;
        this.activationCost = activationCost;
        this.displayName = displayName;
        factory = () -> new EmpoweredUpgrade(this);
    }

    public int getMaxStorage() {
        // TODO: 1.19: config before load
        return 100;
//        return maxStorage.get();
    }

    public float getDamageAbsorptionChance() {
        return damageAbsorptionChance;
    }

    public Supplier<IDarkSteelUpgrade> getFactory() {
        return factory;
    }

    @Override
    public int getLevel() {
        return ordinal();
    }

    public int getActivationCost() {
        return activationCost;
    }

    public Component getDisplayName() {
        return displayName;
    }
}
