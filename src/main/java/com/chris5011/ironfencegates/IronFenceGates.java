package com.chris5011.ironfencegates;

import com.chris5011.ironfencegates.proxy.ClientProxy;
import com.chris5011.ironfencegates.proxy.IProxy;
import com.chris5011.ironfencegates.proxy.ServerProxy;
import com.chris5011.ironfencegates.setup.ClientSetup;
import com.chris5011.ironfencegates.setup.ModSetup;
import com.chris5011.ironfencegates.setup.Registry;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("ironfencegates")
public class IronFenceGates {

    public static final String MODID = "ironfencegates";
    public static IProxy proxy = DistExecutor.unsafeRunForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public IronFenceGates() {

        //Launch the registration of all Items and Blocks
        Registry.registerRegistries();

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::setup);

        //Register the clientsetup
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::clientInit);
    }
}
