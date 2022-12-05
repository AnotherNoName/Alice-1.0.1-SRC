/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.snow.aclient.event.events;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class CollisionBoundingBoxEvent
extends Event {
    private final /* synthetic */ Block block;
    private final /* synthetic */ BlockPos position;
    private final /* synthetic */ AxisAlignedBB collisionBox;
    private final /* synthetic */ Entity entity;
    private final /* synthetic */ List<AxisAlignedBB> collisionList;

    public BlockPos getPosition() {
        return this.position;
    }

    public CollisionBoundingBoxEvent(Block block, BlockPos blockPos, Entity entity, AxisAlignedBB axisAlignedBB, List<AxisAlignedBB> list) {
        this.block = block;
        this.position = blockPos;
        this.entity = entity;
        this.collisionBox = axisAlignedBB;
        this.collisionList = list;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public AxisAlignedBB getCollisionBox() {
        return this.collisionBox;
    }

    public List<AxisAlignedBB> getCollisionList() {
        return this.collisionList;
    }

    public Block getBlock() {
        return this.block;
    }
}

