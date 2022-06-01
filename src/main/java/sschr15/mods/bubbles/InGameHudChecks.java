package sschr15.mods.bubbles;

import io.github.apace100.origins.power.OriginsPowerTypes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.TagKey;

public class InGameHudChecks {
    public static boolean showBubbles(PlayerEntity player, TagKey<Fluid> tagKey) {

        //region: Water breathing effect
        if (player.hasStatusEffect(StatusEffects.WATER_BREATHING)) {
            return false; // Never show air bar if the player has water breathing
        }
        //endregion

        //region: Conduit power
        if (player.hasStatusEffect(StatusEffects.CONDUIT_POWER)) {
            return false; // Never show air bar if the player has conduit power
        }
        //endregion

        //region: Origins
        try {
            Class.forName("io.github.apace100.origins.power.OriginsPowerTypes");
            // If the class exists, Origins is installed, meaning we need to do more checks!

            if (OriginsPowerTypes.WATER_BREATHING.isActive(player)) {
                return !player.isSubmergedIn(tagKey); // Invert the normal check
            }
        } catch (ClassNotFoundException e) {
            // Origins is not installed, so we can continue trying other stuff
        }
        //endregion

        //region: Any generic "gills" effect
        if (player.getStatusEffects().stream().anyMatch(effect -> effect.getEffectType().getTranslationKey().contains(".gills"))) {
            return !player.isSubmergedIn(tagKey); // Invert the normal check
        }
        //endregion

        // Default to the normal check
        return player.isSubmergedIn(tagKey);
    }
}
