package me.roundaround.enchantmentcompat.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.roundaround.enchantmentcompat.EnchantmentCompatMod;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

@Mixin(DamageEnchantment.class)
public abstract class DamageEnchantmentMixin extends Enchantment {
  protected DamageEnchantmentMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
    super(weight, type, slotTypes);
  }

  @Shadow
  public int typeIndex;

  @Inject(method = "canAccept", at = @At(value = "HEAD"), cancellable = true)
  private void canAccept(Enchantment other, CallbackInfoReturnable<Boolean> info) {
    if (!EnchantmentCompatMod.CONFIG.MOD_ENABLED.getValue()
        || !EnchantmentCompatMod.CONFIG.DAMAGE.getValue()) {
      return;
    }

    // TODO: Add option to scale down damage when there are multiple

    if (other instanceof DamageEnchantment) {
      // Only adjust for damage
      info.setReturnValue(((DamageEnchantment) other).typeIndex != typeIndex);
    }
  }
}
