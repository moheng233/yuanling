package site.moheng.yuanling.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import site.moheng.yuanling.ModScreenHandle;

public class TranslationTableScreenHandler extends ScreenHandler {

    protected TranslationTableScreenHandler(int syncId, PlayerInventory inventory) {
        super(ModScreenHandle.TRANSLATION_TABLE_SCREEN_HANDLER, syncId);
    }

    @Override
    public boolean canUse(PlayerEntity var1) {
        return true;
    }

    public static TranslationTableScreenHandler create(int syncId, PlayerInventory inventory) {
        return new TranslationTableScreenHandler(syncId, inventory);
    };

}
