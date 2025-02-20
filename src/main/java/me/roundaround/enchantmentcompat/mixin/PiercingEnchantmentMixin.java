package me.roundaround.enchantmentcompat.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.roundaround.enchantmentcompat.config.EnchantmentCompatConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.PiercingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PiercingEnchantment.class)
public abstract class PiercingEnchantmentMixin {
  @ModifyReturnValue(method = "canAccept", at = @At("RETURN"))
  private boolean canAccept(boolean original, @Local(argsOnly = true) Enchantment other) {
    EnchantmentCompatConfig config = EnchantmentCompatConfig.getInstance();
    if (!config.modEnabled.getPendingValue() || !config.multishotPiercing.getPendingValue()) {
      return original;
    }

    // Only adjust for multishot
    return other == Enchantments.MULTISHOT || original;
  }
}
