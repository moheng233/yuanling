package site.moheng.yuanling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class YuanLing implements ModInitializer {
	public static final String MOD_ID = "yuanling";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("yuanling");

    public static final ItemGroup YUANLING_GROUP = FabricItemGroupBuilder.create(new Identifier(YuanLing.MOD_ID, "base"))
		.icon(() -> new ItemStack(ModItems.LINGSHI))
		.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("YuanLing Loading!");

		LOGGER.info("YuanLing Item Loading!");

		// 初始化物品
		ModItems.init();
		// 初始化方块
		ModBlocks.init();

		// 初始化ScreenHandle
		ModScreenHandle.init();

		// 注册物品
		FieldRegistrationHandler.register(ModItems.class, MOD_ID, false);
		FieldRegistrationHandler.register(ModBlocks.class, MOD_ID, false);

		ModBlockEntitys.init();
	}
}
