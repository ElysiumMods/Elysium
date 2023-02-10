package com.enderio.base.common.item.darksteel.upgrades;

import com.enderio.api.capability.IDarkSteelUpgrade;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

public interface IUpgradeTier {

    int getLevel();

    int getActivationCost();

    Component getDisplayName();

    Supplier<IDarkSteelUpgrade> getFactory();

}
