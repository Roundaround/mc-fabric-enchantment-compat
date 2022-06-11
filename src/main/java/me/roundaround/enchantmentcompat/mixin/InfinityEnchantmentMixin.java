package me.roundaround.enchantmentcompat.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.roundaround.enchantmentcompat.EnchantmentCompatMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.EquipmentSlot;

@Mixin(InfinityEnchantment.class)
public abstract class InfinityEnchantmentMixin extends Enchantment {
  protected InfinityEnchantmentMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
    super(weight, type, slotTypes);
  }

  @Inject(method = "canAccept", at = @At(value = "HEAD"), cancellable = true)
  private void canAccept(Enchantment other, CallbackInfoReturnable<Boolean> info) {
    if (!EnchantmentCompatMod.CONFIG.MOD_ENABLED.getValue()
        || !EnchantmentCompatMod.CONFIG.INFINITY_MENDING.getValue()) {
      return;
    }

    if (other instanceof MendingEnchantment) {
      // Only adjust for mending
      info.setReturnValue(super.canAccept(other));
    }
  }
}
