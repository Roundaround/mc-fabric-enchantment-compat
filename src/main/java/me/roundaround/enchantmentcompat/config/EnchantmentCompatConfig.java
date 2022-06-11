package me.roundaround.enchantmentcompat.config;

import me.roundaround.enchantmentcompat.EnchantmentCompatMod;
import me.roundaround.roundalib.config.ModConfig;
import me.roundaround.roundalib.config.option.BooleanConfigOption;

public class EnchantmentCompatConfig extends ModConfig {
  public final BooleanConfigOption MOD_ENABLED;
  public final BooleanConfigOption INFINITY_MENDING;
  public final BooleanConfigOption PROTECTION;
  public final BooleanConfigOption DAMAGE;
  public final BooleanConfigOption MULTISHOT_PIERCING;

  public EnchantmentCompatConfig() {
    super(EnchantmentCompatMod.MOD_ID);

    MOD_ENABLED = registerConfigOption(
        BooleanConfigOption
            .builder("modEnabled", "enchantmentcompat.mod_enabled.label")
            .setComment("Simple toggle for the mod! Set to false to disable.")
            .build());

    INFINITY_MENDING = registerConfigOption(
        BooleanConfigOption
            .builder("infinityMending", "enchantmentcompat.infinity_mending.label")
            .setComment("Simple toggle for the mod! Set to false to disable.")
            .build());

    PROTECTION = registerConfigOption(
        BooleanConfigOption
            .builder("protection", "enchantmentcompat.protection.label")
            .setComment("Simple toggle for the mod! Set to false to disable.")
            .build());

    DAMAGE = registerConfigOption(
        BooleanConfigOption
            .builder("damage", "enchantmentcompat.damage.label")
            .setComment("Simple toggle for the mod! Set to false to disable.")
            .build());

    MULTISHOT_PIERCING = registerConfigOption(
        BooleanConfigOption
            .builder("multishotPiercing", "enchantmentcompat.multishot_piercing.label")
            .setComment("Simple toggle for the mod! Set to false to disable.")
            .build());
  }
}
