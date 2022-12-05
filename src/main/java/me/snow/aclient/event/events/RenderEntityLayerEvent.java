/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.EntityLivingBase
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public class RenderEntityLayerEvent
extends EventStage {
    public /* synthetic */ LayerRenderer<?> layer;
    public /* synthetic */ EntityLivingBase entity;

    public int hashCode() {
        int n = this.entity.hashCode();
        n = n * 31 + this.layer.hashCode();
        return n;
    }

    public EntityLivingBase getEntity() {
        return this.entity;
    }

    public LayerRenderer<?> getLayer() {
        return this.layer;
    }

    public String toString() {
        return String.valueOf(new StringBuilder().append("RenderEntityLayerEvent(entity=").append((Object)this.entity).append(", layer=").append(this.layer).append(')'));
    }

    public void setLayer(LayerRenderer<?> layerRenderer) {
        this.layer = layerRenderer;
    }

    public EntityLivingBase component1() {
        return this.entity;
    }

    public void setEntity(EntityLivingBase entityLivingBase) {
        this.entity = entityLivingBase;
    }

    public static RenderEntityLayerEvent copy$default(RenderEntityLayerEvent renderEntityLayerEvent, EntityLivingBase entityLivingBase, LayerRenderer layerRenderer, int n, Object object) {
        if ((n & 1) != 0) {
            entityLivingBase = renderEntityLayerEvent.entity;
        }
        if ((n & 2) != 0) {
            layerRenderer = renderEntityLayerEvent.layer;
        }
        return renderEntityLayerEvent.copy(entityLivingBase, layerRenderer);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof RenderEntityLayerEvent)) {
            return false;
        }
        RenderEntityLayerEvent renderEntityLayerEvent = (RenderEntityLayerEvent)((Object)object);
        return this.entity == renderEntityLayerEvent.entity && this.layer == renderEntityLayerEvent.layer;
    }

    public RenderEntityLayerEvent(EntityLivingBase entityLivingBase, LayerRenderer<?> layerRenderer) {
        this.entity = entityLivingBase;
        this.layer = layerRenderer;
    }

    public LayerRenderer<?> component2() {
        return this.layer;
    }

    public RenderEntityLayerEvent copy(EntityLivingBase entityLivingBase, LayerRenderer<?> layerRenderer) {
        return new RenderEntityLayerEvent(entityLivingBase, layerRenderer);
    }
}

