package site.moheng.yuanling.ui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class TranslationTableScreen extends CottonInventoryScreen<TranslationTableScreenHandler> {

    public TranslationTableScreen(TranslationTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

}
