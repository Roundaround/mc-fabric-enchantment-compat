package me.roundaround.enchantmentcompat.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.roundaround.enchantmentcompat.EnchantmentCompatMod;
import me.roundaround.roundalib.client.gui.screen.ConfigScreen;

public class ModMenuImpl implements ModMenuApi {
  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return (screen) -> new ConfigScreen(screen, EnchantmentCompatMod.CONFIG);
  }
}
