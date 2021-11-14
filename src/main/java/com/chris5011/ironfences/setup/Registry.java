package com.chris5011.ironfences.setup;

import com.chris5011.ironfences.IronFences;
import com.chris5011.ironfences.modules.copperfences.CopperFenceGate_Block;
import com.chris5011.ironfences.modules.ironfences.IronFenceGate_Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class Registry {

    private final static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IronFences.MODID);
    private final static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IronFences.MODID);
//    private final static DeferredRegister<Item> FORGE_ITEM_TAGS = DeferredRegister.create(ForgeRegistries.ITEMS, "forge");


    // Iron Fence:
    public static final RegistryObject<Block> IRON_FENCE_BLOCK = BLOCKS.register("iron_fence", () -> new FenceBlock(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Item> IRON_FENCE_BLOCK_ITEM = ITEMS.register("iron_fence", () -> new BlockItem(IRON_FENCE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    //Iron Fence Gate:
    public static final RegistryObject<Block> IRON_FENCE_GATE_BLOCK = BLOCKS.register("iron_fence_gate", () -> new IronFenceGate_Block(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Item> IRON_FENCE_GATE_ITEM = ITEMS.register("iron_fence_gate", () -> new BlockItem(IRON_FENCE_GATE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));


    public static final RegistryObject<Item> COPPER_NUGGET_ITEM = ITEMS.register("copper_nugget", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    //    public static final RegistryObject<Item> COPPER_NUGGET_ITEM = FORGE_ITEM_TAGS.register("nuggets/copper", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    //Copper Fence:
    public static final RegistryObject<Block> COPPER_FENCE_BLOCK = BLOCKS.register("copper_fence", () -> new FenceBlock(Block.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE).strength(5.0F, 6.0F).sound(SoundType.COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Item> COPPER_FENCE_BLOCK_ITEM = ITEMS.register("copper_fence", () -> new BlockItem(COPPER_FENCE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    //Copper Fence Gate:
    public static final RegistryObject<Block> COPPER_FENCE_GATE_BLOCK = BLOCKS.register("copper_fence_gate", () -> new CopperFenceGate_Block(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(5.0F, 6.0F).sound(SoundType.COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Item> COPPER_FENCE_GATE_ITEM = ITEMS.register("copper_fence_gate", () -> new BlockItem(COPPER_FENCE_GATE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    //Gold Fence:
    public static final RegistryObject<Block> GOLD_FENCE_BLOCK = BLOCKS.register("gold_fence", () -> new FenceBlock(Block.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).strength(5.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().isRedstoneConductor((p_61036_, p_61037_, p_61038_) -> true)));
    public static final RegistryObject<Item> GOLD_FENCE_BLOCK_ITEM = ITEMS.register("gold_fence", () -> new BlockItem(GOLD_FENCE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    //Gold Fence Gate:
    public static final RegistryObject<Block> GOLD_FENCE_GATE_BLOCK = BLOCKS.register("gold_fence_gate", () -> new FenceGateBlock(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().isRedstoneConductor((p_61036_, p_61037_, p_61038_) -> true)));
    public static final RegistryObject<Item> GOLD_FENCE_GATE_ITEM = ITEMS.register("gold_fence_gate", () -> new BlockItem(GOLD_FENCE_GATE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    //Walls:

    //Iron Wall:
    public static final RegistryObject<Block> IRON_WALL_BLOCK = BLOCKS.register("iron_wall", () -> new WallBlock(Block.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Item> IRON_WALL_ITEM = ITEMS.register("iron_wall", () -> new BlockItem(IRON_WALL_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    //Copper Wall:
    public static final RegistryObject<Block> COPPER_WALL_BLOCK = BLOCKS.register("copper_wall", () -> new WallBlock(Block.Properties.copy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<Item> COPPER_WALL_ITEM = ITEMS.register("copper_wall", () -> new BlockItem(COPPER_WALL_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    //Gold Wall:
    public static final RegistryObject<Block> GOLD_WALL_BLOCK = BLOCKS.register("gold_wall", () -> new WallBlock(Block.Properties.copy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Item> GOLD_WALL_ITEM = ITEMS.register("gold_wall", () -> new BlockItem(GOLD_WALL_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));


    public static void registerRegistries() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
//        FORGE_ITEM_TAGS.register(eventBus);
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }

}
