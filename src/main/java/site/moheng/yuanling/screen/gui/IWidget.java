package site.moheng.yuanling.screen.gui;

import java.util.Optional;
import java.util.OptionalInt;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;

/**
 * 组件的接口
 * 渲染部分的逻辑都将会由HandlerScreen来调用
 * 数据的同步都将会由ScreenHandler来处理
 */
public interface IWidget {

    // public Optional<GuiScreenHandler> getScreenHandler();

    /**
     * 在这里初始化一些客户端和服务端都需要的逻辑
     * 比如托管同步数据等等
     */
    public default void init() {

    }

    /**
     * 在这里初始化一些仅客户端逻辑，只会在客户端调用
     */
    @Environment(EnvType.CLIENT)
    public default void initClient(){

    }

    /**
     * 在这里初始化一些仅服务端调用，只会在服务端调用
     */
    @Environment(EnvType.SERVER)
    public default void initServer() {

    }

    /**
     * 获得绝对的X坐标
     * 
     * @return 绝对的X坐标
     */
    @Environment(EnvType.CLIENT)
    public int getAbsX();

    /**
     * 获得绝对的Y坐标
     * 
     * @return 绝对的Y坐标
     */
    @Environment(EnvType.CLIENT)
    public int getAbsY();

    /**
     * 获得组件的宽度
     * 
     * @return 组件的宽度
     */
    @Environment(EnvType.CLIENT)
    public int getW();

    /**
     * 获得组件的高度
     * 
     * @return 组件的高度
     */
    @Environment(EnvType.CLIENT)
    public int getH();


    /**
     * 渲染组件
     * 仅客户端
     * 
     * @param matrices 矩阵
     * @param mouseX   鼠标X
     * @param mouseY   鼠标Y
     * @param delta    经过时间
     */
    @Environment(EnvType.CLIENT)
    public default void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

    };

    /**
     * 当鼠标点击时候触发
     * 仅客户端
     * 
     * @param mouseX
     * @param mouseY
     * @param button
     * @return
     */
    @Environment(EnvType.CLIENT)
    public default boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    /**
     * 鼠标拖动时触发
     * 仅客户端
     * 
     * @param mouseX
     * @param mouseY
     * @param button
     * @param deltaX
     * @param deltaY
     * @return
     */
    @Environment(EnvType.CLIENT)
    public default boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return false;
    }

    /**
     * 检查是否与当前组件碰撞
     * 仅客户端
     * 
     * @param collectionX
     * @param collectionY
     * @return
     */
    @Environment(EnvType.CLIENT)
    public default boolean isCollision(int collectionX, int collectionY) {
        if(collectionX > getAbsX() && collectionX < getAbsX() + getW() && collectionY > getAbsY() && collectionY < getAbsY() + getH()) {
            return true;
        }

        return false;
    }
}
