package com.enderio.base.common;

import com.enderio.EnderIO;
import com.enderio.base.common.config.Client;
import com.enderio.base.common.config.Common;
import org.quiltmc.loader.api.config.QuiltConfig;

public class Configs {
    public static final Common COMMON = QuiltConfig.create(EnderIO.MODID, "common", Common.class);
    public static final Client CLIENT = QuiltConfig.create(EnderIO.MODID, "client", Client.class);
}
