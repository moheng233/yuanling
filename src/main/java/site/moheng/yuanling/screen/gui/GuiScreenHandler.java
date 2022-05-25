package site.moheng.yuanling.screen.gui;

import java.util.Optional;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class GuiScreenHandler extends ScreenHandler {
    public Optional<AbsWidget> root = Optional.empty();

    public GuiScreenHandler(ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Override
    public boolean canUse(PlayerEntity var1) {
        return true;
    }

}
