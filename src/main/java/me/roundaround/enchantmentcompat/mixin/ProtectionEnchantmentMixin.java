package me.roundaround.enchantmentcompat.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.roundaround.enchantmentcompat.EnchantmentCompatMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.EquipmentSlot;

@Mixin(ProtectionEnchantment.class)
public abstract class ProtectionEnchantmentMixin extends Enchantment {
  protected ProtectionEnchantmentMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
    super(weight, type, slotTypes);
  }

  @Shadow
  public ProtectionEnchantment.Type protectionType;

  @Inject(method = "canAccept", at = @At(value = "HEAD"), cancellable = true)
  private void canAccept(Enchantment other, CallbackInfoReturnable<Boolean> info) {
    if (!EnchantmentCompatMod.CONFIG.MOD_ENABLED.getValue()
        || !EnchantmentCompatMod.CONFIG.PROTECTION.getValue()) {
      return;
    }

    // TODO: Add option to scale down protection when there are multiple

    if (other instanceof ProtectionEnchantment) {
      // Only adjust for protection
      info.setReturnValue(((ProtectionEnchantment) other).protectionType != protectionType);
    }
  }
}
