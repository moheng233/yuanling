package site.moheng.yuanling.screen.gui;

import java.util.Optional;

/**
 * 抽象的组件类
 */
public abstract class AbsWidget implements IWidget {
    private int offsetX;
    private int offsetY;

    /**
     * 父组件
     */
    public Optional<IWidget> parent = Optional.empty();

    /**
     * 获得绝对的X坐标
     * @return 绝对的X坐标
     */
    @Override
    public int getAbsX() {
        if (parent.isPresent()) {
            return parent.get().getAbsX() + offsetX;
        }
        return offsetX;
    }

    /**
     * 获得绝对的Y坐标
     * @return 绝对的Y坐标
     */
    @Override
    public int getAbsY() {
        if (parent.isPresent()) {
            return parent.get().getAbsY() + offsetY;
        }
        return offsetY;
    }
    
    AbsWidget(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

}
