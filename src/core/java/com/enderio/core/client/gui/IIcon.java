package com.enderio.core.client.gui;

import com.enderio.core.common.util.Vector2i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.quiltmc.loader.api.minecraft.ClientOnly;

public interface IIcon {
    Vector2i DEFAULT_TEXTURE_SIZE = new Vector2i(256, 256);

    /**
     * @return The texture that needs to be bound to the texturemanager to be rendered
     */
    @ClientOnly
    ResourceLocation getTextureLocation();

    /**
     * @return the size of the area on the texture you want to render
     */
    @ClientOnly
    Vector2i getIconSize();

    /**
     * @return the size you want to render the texturearea at
     */
    @ClientOnly
    default Vector2i getRenderSize() {
        return getIconSize();
    }

    /**
     * @return the position your icon is on the texture
     */
    @ClientOnly
    Vector2i getTexturePosition();

    /**
     * @return a Component that is rendered on hover, if this icon is rendered on a gui
     */
    @ClientOnly
    default Component getTooltip() {
        return Component.empty();
    }

    /**
     * @return the texture size
     */
    @ClientOnly
    default Vector2i getTextureSize() {
        return DEFAULT_TEXTURE_SIZE;
    }

    /**
     * @return if this icon should render
     */
    @ClientOnly
    default boolean shouldRender() {
        return true;
    }
}
