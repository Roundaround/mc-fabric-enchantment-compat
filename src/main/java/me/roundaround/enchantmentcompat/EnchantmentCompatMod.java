package me.roundaround.enchantmentcompat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.roundaround.enchantmentcompat.config.EnchantmentCompatConfig;
import net.fabricmc.api.ModInitializer;

public final class EnchantmentCompatMod implements ModInitializer {
  public static final String MOD_ID = "enchantmentcompat";
  public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
  public static final EnchantmentCompatConfig CONFIG = new EnchantmentCompatConfig();

  @Override
  public void onInitialize() {
    CONFIG.init();
  }
}
