package me.roundaround.enchantmentcompat.config;

import me.roundaround.enchantmentcompat.EnchantmentCompatMod;
import me.roundaround.roundalib.config.ModConfig;
import me.roundaround.roundalib.config.option.BooleanConfigOption;

public class EnchantmentCompatConfig extends ModConfig {
  public final BooleanConfigOption MOD_ENABLED;
  public final BooleanConfigOption INFINITY_MENDING;
  public final BooleanConfigOption MULTISHOT_PIERCING;
  public final BooleanConfigOption PROTECTION;
  public final BooleanConfigOption DAMAGE;

  public EnchantmentCompatConfig() {
    super(EnchantmentCompatMod.MOD_ID);

    MOD_ENABLED = registerConfigOption(BooleanConfigOption.builder(this,
            "modEnabled",
            "enchantmentcompat.mod_enabled.label")
        .setComment("Simple toggle for the mod! Set to false to disable.")
        .build());

    INFINITY_MENDING = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
            "infinityMending",
            "enchantmentcompat.infinity_mending.label")
        .setComment("Whether to allow infinity and mending to combine.")
        .build());

    MULTISHOT_PIERCING = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
            "multishotPiercing",
            "enchantmentcompat.multishot_piercing.label")
        .setComment("Whether to allow multishot and piercing to combine.")
        .build());

    PROTECTION = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
            "protection",
            "enchantmentcompat.protection.label")
        .setComment("Whether to allow the different protections to combine.")
        .build());

    DAMAGE = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
            "damage",
            "enchantmentcompat.damage.label")
        .setComment("Whether to allow the different damages to combine.")
        .build());
  }
}
