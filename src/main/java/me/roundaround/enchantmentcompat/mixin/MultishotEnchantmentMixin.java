package me.roundaround.enchantmentcompat.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.roundaround.enchantmentcompat.config.EnchantmentCompatConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.MultishotEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MultishotEnchantment.class)
public abstract class MultishotEnchantmentMixin {
  @ModifyReturnValue(method = "canAccept", at = @At("RETURN"))
  private boolean canAccept(boolean original, @Local(argsOnly = true) Enchantment other) {
    EnchantmentCompatConfig config = EnchantmentCompatConfig.getInstance();
    if (!config.modEnabled.getPendingValue() || !config.multishotPiercing.getPendingValue()) {
      return original;
    }

    // Only adjust for piercing
    return other == Enchantments.PIERCING || original;
  }
}
