package com.chris5011.ironfences.proxy;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;


public class ServerProxy implements IProxy {
    @Override
    public void init() {

    }

    @Override
    public Level getClientWorld() {
        throw new IllegalStateException("Only callable on the Client!");
    }

    @Override
    public Player getClientPlayer() {
        throw new IllegalStateException("Only callable on the Client!");
    }
}
