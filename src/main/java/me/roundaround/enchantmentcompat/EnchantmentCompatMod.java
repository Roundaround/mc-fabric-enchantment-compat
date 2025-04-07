package me.roundaround.enchantmentcompat;

import me.roundaround.enchantmentcompat.config.EnchantmentCompatConfig;
import me.roundaround.gradle.api.annotation.Entrypoint;
import net.fabricmc.api.ModInitializer;

@Entrypoint(Entrypoint.MAIN)
public final class EnchantmentCompatMod implements ModInitializer {
  @Override
  public void onInitialize() {
    EnchantmentCompatConfig.getInstance().init();
  }
}
