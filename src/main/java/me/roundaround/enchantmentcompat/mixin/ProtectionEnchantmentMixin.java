package me.roundaround.enchantmentcompat.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.roundaround.enchantmentcompat.config.EnchantmentCompatConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ProtectionEnchantment.class)
public abstract class ProtectionEnchantmentMixin {
  @Final
  @Shadow
  public ProtectionEnchantment.Type protectionType;

  @ModifyReturnValue(method = "canAccept", at = @At("RETURN"))
  private boolean canAccept(boolean original, @Local(argsOnly = true) Enchantment other) {
    EnchantmentCompatConfig config = EnchantmentCompatConfig.getInstance();
    if (!config.modEnabled.getPendingValue() || !config.protection.getPendingValue()) {
      return original;
    }

    // TODO: Add option to scale down protection when there are multiple

    // Only adjust for protection enchantments
    return other instanceof ProtectionEnchantment otherProtect ?
        this.protectionType != otherProtect.protectionType :
        original;
  }
}
