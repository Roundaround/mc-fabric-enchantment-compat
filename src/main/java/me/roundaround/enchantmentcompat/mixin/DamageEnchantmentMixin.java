package me.roundaround.enchantmentcompat.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.roundaround.enchantmentcompat.config.EnchantmentCompatConfig;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;
import java.util.Optional;

@Mixin(DamageEnchantment.class)
public abstract class DamageEnchantmentMixin extends Enchantment {
  @Shadow
  @Final
  private Optional<TagKey<EntityType<?>>> applicableEntities;

  protected DamageEnchantmentMixin(Properties properties) {
    super(properties);
  }

  @ModifyReturnValue(method = "canAccept", at = @At("RETURN"))
  private boolean canAccept(boolean original, @Local(argsOnly = true) Enchantment other) {
    EnchantmentCompatConfig config = EnchantmentCompatConfig.getInstance();
    if (!config.modEnabled.getPendingValue() || !config.damage.getPendingValue()) {
      return original;
    }

    // TODO: Add option to scale down damage when there are multiple

    // Only adjust for damage enchantments
    if (!(other instanceof DamageEnchantment)) {
      return original;
    }

    Optional<TagKey<EntityType<?>>> otherApplicableEntities =
        ((DamageEnchantmentAccessor) other).getApplicableEntities();

    if (this.applicableEntities.isEmpty() || otherApplicableEntities.isEmpty()) {
      return true;
    }

    return !Objects.equals(this.applicableEntities.get(), otherApplicableEntities.get());
  }
}
