package com.chris5011.ironfencegates.setup;

import com.chris5011.ironfencegates.IronFenceGates;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = IronFenceGates.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void clientInit(final FMLClientSetupEvent event){

    }
}
