package me.roundaround.enchantmentcompat.config;

import me.roundaround.enchantmentcompat.generated.Constants;
import me.roundaround.enchantmentcompat.roundalib.config.ConfigPath;
import me.roundaround.enchantmentcompat.roundalib.config.manage.ModConfigImpl;
import me.roundaround.enchantmentcompat.roundalib.config.manage.store.WorldScopedFileStore;
import me.roundaround.enchantmentcompat.roundalib.config.option.BooleanConfigOption;
import me.roundaround.enchantmentcompat.roundalib.nightconfig.core.Config;

public class EnchantmentCompatConfig extends ModConfigImpl implements WorldScopedFileStore {
  private static EnchantmentCompatConfig instance;

  public BooleanConfigOption modEnabled;
  public BooleanConfigOption bows;
  public BooleanConfigOption crossbows;
  public BooleanConfigOption armor;
  public BooleanConfigOption damage;

  private EnchantmentCompatConfig() {
    super(Constants.MOD_ID, 2);
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

    bows = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("bows"))
        .setDefaultValue(true)
        .setComment("Allow bow enchantments (i.e. infinity, mending) to combine.")
        .build());

    crossbows = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("crossbows"))
        .setDefaultValue(true)
        .setComment("Allow crossbow enchantments (i.e. multishot, piercing) to combine.")
        .build());

    armor = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("armor"))
        .setDefaultValue(true)
        .setComment("Allow armor enchantments (i.e. X protection) to combine.")
        .build());

    damage = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("damage"))
        .setDefaultValue(true)
        .setComment("Allow damage enchantments (i.e. sharpness, smite) to combine.")
        .build());
  }

  @Override
  public boolean performConfigUpdate(int versionSnapshot, Config inMemoryConfigSnapshot) {
    if (versionSnapshot == 1) {
      inMemoryConfigSnapshot.set("bows", inMemoryConfigSnapshot.get("infinityMending"));
      inMemoryConfigSnapshot.remove("infinityMending");
      inMemoryConfigSnapshot.set("crossbows", inMemoryConfigSnapshot.get("multishotPiercing"));
      inMemoryConfigSnapshot.remove("multishotPiercing");
      inMemoryConfigSnapshot.set("armor", inMemoryConfigSnapshot.get("protection"));
      inMemoryConfigSnapshot.remove("protection");

      return true;
    }
    return false;
  }
}
