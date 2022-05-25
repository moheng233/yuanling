package site.moheng.yuanling.screen.gui;

import java.util.AbstractList;

import org.checkerframework.checker.units.qual.h;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.collection.DefaultedList;

/**
 * 没有实现背景绘制的组件容器
 */
public class WPanel extends AbsWidget implements IWidgetContainer {
    private DefaultedList<AbsWidget> widgets = DefaultedList.of();

    private int width;
    private int hight;

    WPanel(int offsetX, int offsetY, int width, int hight) {
        super(offsetX, offsetY);
        this.width = width;
        this.hight = hight;
    }

    @Override
    public int getW() {
        return width;
    }

    @Override
    public int getH() {
        return hight;
    }

    @Override
    public AbstractList<AbsWidget> getWidgets() {
        return widgets;
    }
    
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        // 绘制背景
        renderBackager(matrices, mouseX, mouseY, delta);
        // 绘制子组件
        this.renderChildWidget(matrices, mouseX, mouseY, delta);
    }

    private void renderBackager(MatrixStack matrices, int mouseX, int mouseY, float delta) {

    }
}
