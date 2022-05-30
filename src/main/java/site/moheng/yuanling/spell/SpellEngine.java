package site.moheng.yuanling.spell;

import org.graalvm.polyglot.*;

import site.moheng.yuanling.YuanLing;

public class SpellEngine {
    public final static SpellEngine INSTANCE = new SpellEngine();

    public final Engine jsEngine;

    public SpellEngine() {
        jsEngine = Engine.newBuilder()
                .build();

        
    }
}
