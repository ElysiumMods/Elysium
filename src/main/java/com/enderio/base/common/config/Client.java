package com.enderio.base.common.config;

import org.quiltmc.config.api.WrappedConfig;
import org.quiltmc.config.api.annotations.Comment;

public class Client extends WrappedConfig {
    @Comment("Enable machine particles")
    public final Boolean machineParticles = true;
}
