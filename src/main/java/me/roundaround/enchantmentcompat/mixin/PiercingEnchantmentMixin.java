package me.roundaround.enchantmentcompat.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.roundaround.enchantmentcompat.EnchantmentCompatMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.MultishotEnchantment;
import net.minecraft.enchantment.PiercingEnchantment;
import net.minecraft.entity.EquipmentSlot;

@Mixin(PiercingEnchantment.class)
public abstract class PiercingEnchantmentMixin extends Enchantment {
  protected PiercingEnchantmentMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
    super(weight, type, slotTypes);
  }

  @Inject(method = "canAccept", at = @At(value = "HEAD"), cancellable = true)
  private void canAccept(Enchantment other, CallbackInfoReturnable<Boolean> info) {
    if (!EnchantmentCompatMod.CONFIG.MOD_ENABLED.getValue()
        || !EnchantmentCompatMod.CONFIG.MULTISHOT_PIERCING.getValue()) {
      return;
    }

    if (other instanceof MultishotEnchantment) {
      // Only adjust for multishot
      info.setReturnValue(super.canAccept(other));
    }
  }
}
