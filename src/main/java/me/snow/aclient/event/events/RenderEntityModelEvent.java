/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class RenderEntityModelEvent
extends EventStage {
    public /* synthetic */ float limbSwingAmount;
    public /* synthetic */ Entity entity;
    public /* synthetic */ float limbSwing;
    public /* synthetic */ float headPitch;
    public /* synthetic */ float age;
    public /* synthetic */ ModelBase modelBase;
    public /* synthetic */ float headYaw;
    public /* synthetic */ float scale;

    public RenderEntityModelEvent(int n, ModelBase modelBase, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
        super(n);
        this.modelBase = modelBase;
        this.entity = entity;
        this.limbSwing = f;
        this.limbSwingAmount = f2;
        this.age = f3;
        this.headYaw = f4;
        this.headPitch = f5;
        this.scale = f6;
    }
}

