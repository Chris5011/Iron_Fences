package com.chris5011.ironfences.setup;

import com.chris5011.ironfences.IronFences;
import com.chris5011.ironfences.modules.ironfences.IronFenceGate_Block;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class Registry {

    private final static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IronFences.MODID);
    private final static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IronFences.MODID);


    public static final RegistryObject<Block> IRON_FENCE_BLOCK = BLOCKS.register("iron_fence", () -> new FenceBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Item> IRON_FENCE_BLOCK_ITEM = ITEMS.register("iron_fence", () -> new BlockItem(IRON_FENCE_BLOCK.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

    public static final RegistryObject<Block> IRON_FENCE_GATE_BLOCK = BLOCKS.register("iron_fence_gate", () -> new IronFenceGate_Block(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Item> IRON_FENCE_GATE_ITEM = ITEMS.register("iron_fence_gate", () -> new BlockItem(IRON_FENCE_GATE_BLOCK.get(), new Item.Properties().group(ItemGroup.REDSTONE)));


    public static void registerRegistries() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }

}
