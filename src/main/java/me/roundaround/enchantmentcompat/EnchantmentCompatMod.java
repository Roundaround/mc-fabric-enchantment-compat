package me.roundaround.enchantmentcompat;

import me.roundaround.enchantmentcompat.config.EnchantmentCompatConfig;
import net.fabricmc.api.ModInitializer;

public final class EnchantmentCompatMod implements ModInitializer {
  public static final String MOD_ID = "enchantmentcompat";

  @Override
  public void onInitialize() {
    EnchantmentCompatConfig.getInstance().init();
  }
}
