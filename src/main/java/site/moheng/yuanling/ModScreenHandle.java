package site.moheng.yuanling;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import site.moheng.yuanling.screen.TranslationTableScreenHandler;

/**
 * MOD的ScreenHandle管理类
 */
public class ModScreenHandle {

    public static final ScreenHandlerType<TranslationTableScreenHandler> TRANSLATION_TABLE_SCREEN_HANDLER = new ScreenHandlerType<>(
            TranslationTableScreenHandler::create);

    public static void init() {
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(YuanLing.MOD_ID, "table"),
                TRANSLATION_TABLE_SCREEN_HANDLER);
    }

}
