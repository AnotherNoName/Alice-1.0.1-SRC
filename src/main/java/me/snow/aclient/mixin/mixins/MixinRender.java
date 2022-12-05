//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.culling.ICamera
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.AxisAlignedBB
 */
package me.snow.aclient.mixin.mixins;

import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={Render.class})
public class MixinRender<T extends Entity> {
    @Overwrite
    public boolean shouldRender(T t, ICamera iCamera, double d, double d2, double d3) {
        try {
            AxisAlignedBB axisAlignedBB = t.getRenderBoundingBox().expandXyz(0.5);
            if ((axisAlignedBB.hasNaN() || axisAlignedBB.getAverageEdgeLength() == 0.0) && t != null) {
                axisAlignedBB = new AxisAlignedBB(((Entity)t).posX - 2.0, ((Entity)t).posY - 2.0, ((Entity)t).posZ - 2.0, ((Entity)t).posX + 2.0, ((Entity)t).posY + 2.0, ((Entity)t).posZ + 2.0);
            }
            return t.isInRangeToRender3d(d, d2, d3) && (((Entity)t).ignoreFrustumCheck || iCamera.isBoundingBoxInFrustum(axisAlignedBB));
        }
        catch (Exception exception) {
            return false;
        }
    }
}

