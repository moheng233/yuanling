package site.moheng.yuanling.spell.lib;

import org.graalvm.polyglot.*;

import site.moheng.yuanling.YuanLing;

public class SpellDebug {
    public final static SpellDebug INSTANCE = new SpellDebug();

    /**
     * 将实例绑定到JS上下文
     * @param context
     */
    public void bindContext(Value context) {
        context.putMember("debug", INSTANCE);
    }

    @HostAccess.Export
    public void print(Value data) {
        YuanLing.LOGGER.info(data.asString());
    }
}
