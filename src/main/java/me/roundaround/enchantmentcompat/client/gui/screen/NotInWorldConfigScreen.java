package me.roundaround.enchantmentcompat.client.gui.screen;

import me.roundaround.roundalib.client.gui.layout.screen.ThreeSectionLayoutWidget;
import me.roundaround.roundalib.client.gui.widget.drawable.LabelWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import java.util.List;

public class NotInWorldConfigScreen extends Screen {
  private final Screen parent;
  private final ThreeSectionLayoutWidget layout = new ThreeSectionLayoutWidget(this);

  public NotInWorldConfigScreen(Screen parent) {
    super(Text.translatable("enchantmentcompat.config.title"));
    this.parent = parent;
  }

  @Override
  protected void init() {
    assert this.client != null;

    this.layout.addHeader(this.textRenderer, this.title);

    this.layout.addBody(LabelWidget.builder(this.textRenderer,
        List.of(Text.translatable("enchantmentcompat.config.notInWorld.1"),
            Text.translatable("enchantmentcompat.config.notInWorld.2")
        )
    ).alignTextCenterX().alignTextCenterY().hideBackground().showShadow().lineSpacing(2).build());

    this.layout.addFooter(ButtonWidget.builder(ScreenTexts.BACK, (b) -> this.close())
        .width(ButtonWidget.field_49479)
        .build());

    this.layout.forEachChild(this::addDrawableChild);
    this.refreshWidgetPositions();
  }

  @Override
  protected void refreshWidgetPositions() {
    this.layout.refreshPositions();
  }

  @Override
  public void close() {
    assert this.client != null;
    this.client.setScreen(this.parent);
  }
}
