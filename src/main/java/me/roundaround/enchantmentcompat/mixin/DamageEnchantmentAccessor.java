package me.roundaround.enchantmentcompat.mixin;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Optional;

@Mixin(DamageEnchantment.class)
public interface DamageEnchantmentAccessor {
  @Accessor
  Optional<TagKey<EntityType<?>>> getApplicableEntities();
}
