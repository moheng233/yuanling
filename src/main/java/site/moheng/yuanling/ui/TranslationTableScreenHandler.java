package site.moheng.yuanling.ui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.TranslatableText;
import site.moheng.yuanling.ModScreenHandle;

public class TranslationTableScreenHandler extends SyncedGuiDescription {

    protected TranslationTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ModScreenHandle.TRANSLATION_TABLE_SCREEN_HANDLER, syncId, playerInventory);

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 300);
        root.setInsets(Insets.ROOT_PANEL);

        WLabel label = new WLabel(new TranslatableText("ui.yuanling.table.lable"));
        root.add(label, 10, 10);

        root.validate(this);
    }

    public static TranslationTableScreenHandler create(int syncId, PlayerInventory inventory) {
        return new TranslationTableScreenHandler(syncId, inventory);
    }

}
