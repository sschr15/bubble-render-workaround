package sschr15.mods.bubbles.mixins;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import sschr15.mods.bubbles.InGameHudChecks;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Redirect(method = "renderStatusBars", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isSubmergedIn(Lnet/minecraft/tag/Tag;)Z"))
    private boolean isSubmergedIn(PlayerEntity entity, TagKey<Fluid> tagKey) {
        return InGameHudChecks.showBubbles(entity, tagKey);
    }
}
