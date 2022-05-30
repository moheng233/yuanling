package site.moheng.yuanling;

import java.util.HashMap;

import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.loot.JCondition;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JOverride;
import net.devtech.arrp.json.models.JTextures;
import net.minecraft.util.Identifier;

public class ImprintRegister {
    public static final RuntimeResourcePack IMPRINT_PACK = RuntimeResourcePack
            .create(new Identifier(YuanLing.MOD_ID, "imprint"));

    private int customid = 0;
    public HashMap<String, ImprintData> imprintMap = new HashMap<>();

    public void registeImprint(Identifier id) {
        imprintMap.put(id.toString(), new ImprintData(id, customid++));
    }

    /**
     * 生成动态数据包，在所有符文注册后进行
     */
    public void genModel() {

        var model = JModel.model("item/generated")
                .textures(new JTextures().layer0(new Identifier(YuanLing.MOD_ID, "item/imprint").toString()));

        for (ImprintData imprint : imprintMap.values()) {
            model.addOverride(new JOverride(new JCondition().parameter("custom_model_data", imprint.customid),
                    new Identifier(imprint.id.getNamespace(), "imprint/" + imprint.id.getPath()).toString()));
        }

        IMPRINT_PACK.addModel(model, new Identifier(YuanLing.MOD_ID, "models/item/imprint"));
    }

    public record ImprintData(Identifier id, int customid) {
        public int getCustomId() {
            return customid;
        }
    }
}
