//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 */
package me.snow.aclient.module.modules.render;

import java.awt.Color;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.module.modules.client.HUD;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class BlockHighlight
extends Module {
    private final /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Boolean> colorSync;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    public /* synthetic */ Setting<Boolean> customOutline;
    public /* synthetic */ Setting<Boolean> rolling;
    private final /* synthetic */ Setting<Integer> cRed;
    private final /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Integer> cGreen;
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Integer> cBlue;
    private final /* synthetic */ Setting<Integer> cAlpha;
    public /* synthetic */ Setting<Boolean> box;

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        RayTraceResult rayTraceResult = BlockHighlight.mc.objectMouseOver;
        if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos blockPos = rayTraceResult.getBlockPos();
            if (this.rolling.getValue().booleanValue()) {
                RenderUtil.drawProperGradientBlockOutline(blockPos, new Color(HUD.getInstance().colorMap.get(0)), new Color(HUD.getInstance().colorMap.get(this.renderer.scaledHeight / 4)), new Color(HUD.getInstance().colorMap.get(this.renderer.scaledHeight / 2)), 1.0f);
            } else {
                RenderUtil.drawBoxESP(blockPos, this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.customOutline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
            }
        }
    }

    public BlockHighlight() {
        super("BlockHighlight", "Highlights the block u look at.", Module.Category.RENDER, false, false, false);
        this.red = this.register(new Setting<Integer>("Red", 0, 0, 255));
        this.green = this.register(new Setting<Integer>("Green", 255, 0, 255));
        this.blue = this.register(new Setting<Integer>("Blue", 0, 0, 255));
        this.alpha = this.register(new Setting<Integer>("Alpha", 255, 0, 255));
        this.colorSync = this.register(new Setting<Boolean>("Sync", false));
        this.rolling = this.register(new Setting<Object>("Rolling", Boolean.FALSE, object -> this.colorSync.getValue()));
        this.box = this.register(new Setting<Boolean>("Box", false));
        this.boxAlpha = this.register(new Setting<Object>("BoxAlpha", Integer.valueOf(125), Integer.valueOf(0), Integer.valueOf(255), object -> this.box.getValue()));
        this.outline = this.register(new Setting<Boolean>("Outline", true));
        this.lineWidth = this.register(new Setting<Object>("LineWidth", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f), object -> this.outline.getValue()));
        this.customOutline = this.register(new Setting<Object>("CustomLine", Boolean.FALSE, object -> this.outline.getValue()));
        this.cRed = this.register(new Setting<Object>("OL-Red", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.customOutline.getValue() != false && this.outline.getValue() != false));
        this.cGreen = this.register(new Setting<Object>("OL-Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.customOutline.getValue() != false && this.outline.getValue() != false));
        this.cBlue = this.register(new Setting<Object>("OL-Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.customOutline.getValue() != false && this.outline.getValue() != false));
        this.cAlpha = this.register(new Setting<Object>("OL-Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.customOutline.getValue() != false && this.outline.getValue() != false));
    }
}

