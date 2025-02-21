package me.roundaround.enchantmentcompat.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.roundaround.enchantmentcompat.config.EnchantmentCompatConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;
import java.util.Optional;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
  @ModifyExpressionValue(
      method = "canBeCombined", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/registry/entry/RegistryEntryList;contains" +
               "(Lnet/minecraft/registry/entry/RegistryEntry;)Z",
      ordinal = 0
  )
  )
  private static boolean firstExclusiveSetContainsSecond(
      boolean original, @Local(argsOnly = true, ordinal = 0) RegistryEntry<Enchantment> enchantment
  ) {
    return adjustExclusivity(enchantment).orElse(original);
  }

  @ModifyExpressionValue(
      method = "canBeCombined", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/registry/entry/RegistryEntryList;contains" +
               "(Lnet/minecraft/registry/entry/RegistryEntry;)Z",
      ordinal = 1
  )
  )
  private static boolean secondExclusiveSetContainsFirst(
      boolean original, @Local(argsOnly = true, ordinal = 1) RegistryEntry<Enchantment> enchantment
  ) {
    return adjustExclusivity(enchantment).orElse(original);
  }

  @Unique
  private static Optional<Boolean> adjustExclusivity(RegistryEntry<Enchantment> enchantment) {
    EnchantmentCompatConfig config = EnchantmentCompatConfig.getInstance();
    if (!config.modEnabled.getPendingValue()) {
      return Optional.empty();
    }

    if (config.infinityMending.getPendingValue() && isIn(enchantment, EnchantmentTags.BOW_EXCLUSIVE_SET)) {
      return Optional.of(false);
    }
    if (config.multishotPiercing.getPendingValue() && isIn(enchantment, EnchantmentTags.CROSSBOW_EXCLUSIVE_SET)) {
      return Optional.of(false);
    }
    if (config.protection.getPendingValue() && isIn(enchantment, EnchantmentTags.ARMOR_EXCLUSIVE_SET)) {
      return Optional.of(false);
    }
    if (config.damage.getPendingValue() && isIn(enchantment, EnchantmentTags.DAMAGE_EXCLUSIVE_SET)) {
      return Optional.of(false);
    }

    return Optional.empty();
  }

  @Unique
  private static boolean isIn(RegistryEntry<Enchantment> enchantment, TagKey<Enchantment> tagKey) {
    return Objects.equals(enchantment.value().exclusiveSet().getTagKey().orElse(null), tagKey);
  }
}
