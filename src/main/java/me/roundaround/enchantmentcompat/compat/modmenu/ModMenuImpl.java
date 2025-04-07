package me.roundaround.enchantmentcompat.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.roundaround.enchantmentcompat.client.gui.screen.NotInWorldConfigScreen;
import me.roundaround.enchantmentcompat.config.EnchantmentCompatConfig;
import me.roundaround.enchantmentcompat.generated.Constants;
import me.roundaround.enchantmentcompat.roundalib.client.gui.screen.ConfigScreen;
import me.roundaround.gradle.api.annotation.Entrypoint;

@Entrypoint(Entrypoint.MOD_MENU)
public class ModMenuImpl implements ModMenuApi {
  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return (screen) -> {
      EnchantmentCompatConfig config = EnchantmentCompatConfig.getInstance();
      if (!config.isReady()) {
        return new NotInWorldConfigScreen(screen);
      }
      return new ConfigScreen(screen, Constants.MOD_ID, config);
    };
  }
}
