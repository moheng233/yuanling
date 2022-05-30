package site.moheng.yuanling.spell;

import org.graalvm.polyglot.*;

import site.moheng.yuanling.spell.lib.SpellDebug;

public class SpellContext implements AutoCloseable {
    public final Context jsContext;

    public SpellContext() {

        // 新建JS上下文
        jsContext = Context.newBuilder("js")
                .engine(SpellEngine.INSTANCE.jsEngine)
                .allowAllAccess(true)
                .allowCreateProcess(false)
                .allowCreateThread(false)
                .allowIO(false)
                .allowExperimentalOptions(false)
                .allowHostClassLookup(s -> false)
                .build();

        // 绑定接口
        var bind = jsContext.getBindings("js");

        // 调试相关接口
        SpellDebug.INSTANCE.bindContext(bind);
    }

    public void eval(String code) {
        jsContext.eval("js", code);
    }

    @Override
    public void close() throws Exception {
        jsContext.close();
    }
}
