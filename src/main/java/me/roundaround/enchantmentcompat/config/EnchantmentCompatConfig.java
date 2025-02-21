package me.roundaround.enchantmentcompat.config;

import me.roundaround.enchantmentcompat.EnchantmentCompatMod;
import me.roundaround.roundalib.config.ConfigPath;
import me.roundaround.roundalib.config.manage.ModConfigImpl;
import me.roundaround.roundalib.config.manage.store.WorldScopedFileStore;
import me.roundaround.roundalib.config.option.BooleanConfigOption;

public class EnchantmentCompatConfig extends ModConfigImpl implements WorldScopedFileStore {
  private static EnchantmentCompatConfig instance;

  public BooleanConfigOption modEnabled;
  public BooleanConfigOption infinityMending;
  public BooleanConfigOption multishotPiercing;
  public BooleanConfigOption protection;
  public BooleanConfigOption damage;

  private EnchantmentCompatConfig() {
    super(EnchantmentCompatMod.MOD_ID);
  }

  public static EnchantmentCompatConfig getInstance() {
    if (instance == null) {
      instance = new EnchantmentCompatConfig();
    }
    return instance;
  }

  @Override
  protected void registerOptions() {
    modEnabled = this.register(BooleanConfigOption.builder(ConfigPath.of("modEnabled"))
        .setDefaultValue(true)
        .setComment("Simple toggle for the mod! Set to false to disable.")
        .build());

    infinityMending = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("infinityMending"))
        .setDefaultValue(true)
        .setComment("Whether to allow infinity and mending to combine.")
        .build());

    multishotPiercing = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("multishotPiercing"))
        .setDefaultValue(true)
        .setComment("Whether to allow multishot and piercing to combine.")
        .build());

    protection = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("protection"))
        .setDefaultValue(true)
        .setComment("Whether to allow the different protections to combine.")
        .build());

    damage = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("damage"))
        .setDefaultValue(true)
        .setComment("Whether to allow the different damages to combine.")
        .build());
  }
}
