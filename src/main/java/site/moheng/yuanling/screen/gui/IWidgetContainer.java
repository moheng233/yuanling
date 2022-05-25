package site.moheng.yuanling.screen.gui;

import java.util.AbstractList;
import java.util.Optional;

import net.minecraft.client.util.math.MatrixStack;

public interface IWidgetContainer extends IWidget {
    public AbstractList<AbsWidget> getWidgets();

    public default IWidgetContainer addWidget(AbsWidget widget) {
        getWidgets().add(widget);
        widget.parent = Optional.of(this);

        return this;
    }

    public default void renderChildWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        for (AbsWidget widget : getWidgets()) {
            widget.render(matrices, mouseX, mouseY, delta);
        }
    }
}
