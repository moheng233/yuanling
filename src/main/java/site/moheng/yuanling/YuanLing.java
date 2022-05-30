package site.moheng.yuanling;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import com.oracle.truffle.js.parser.GraalJSEvaluator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import site.moheng.yuanling.spell.SpellContext;
import site.moheng.yuanling.spell.SpellEngine;

/**
 * Mod主入口类
 */
public class YuanLing implements ModInitializer {
	public static final String MOD_ID = "yuanling";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ItemGroup YUANLING_GROUP = FabricItemGroupBuilder
			.create(new Identifier(YuanLing.MOD_ID, "base"))
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

		ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
			YuanLing.LOGGER.info("JS代码执行{}");
			var context = new SpellContext();
			context.eval("debug.print('JS引擎初始化成功')");

			try {
				context.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
