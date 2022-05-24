package site.moheng.yuanling;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import site.moheng.yuanling.screen.TranslationTableScreen;

@Environment(EnvType.CLIENT)
public class ModClientScreen {
    public static void init() {
        HandledScreens.register(ModScreenHandle.TRANSLATION_TABLE_SCREEN_HANDLER, TranslationTableScreen::new);
    }
}
