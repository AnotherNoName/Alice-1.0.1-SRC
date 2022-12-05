//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ItemStackHelper
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemMap
 *  net.minecraft.item.ItemShulkerBox
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntityShulkerBox
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.MapData
 *  net.minecraftforge.client.event.RenderTooltipEvent$PostText
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.input.Keyboard
 */
package me.snow.aclient.module.modules.misc;

import java.awt.Color;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render2DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.RenderUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class ToolTips
extends Module {
    private final /* synthetic */ Setting<Integer> yOffset;
    public /* synthetic */ Setting<Boolean> shulkerSpy;
    public /* synthetic */ Setting<Bind> peek;
    public /* synthetic */ Setting<Integer> cooldown;
    private /* synthetic */ int textRadarY;
    public /* synthetic */ Map<EntityPlayer, Timer> playerTimers;
    public /* synthetic */ Setting<Boolean> own;
    private static /* synthetic */ ToolTips INSTANCE;
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Integer> yPerPlayer;
    public /* synthetic */ Setting<Boolean> shulkers;
    private final /* synthetic */ Setting<Integer> xOffset;
    public /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Map<EntityPlayer, ItemStack> spiedPlayers;
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Integer> trOffset;
    private final /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Boolean> maps;
    public /* synthetic */ Setting<Boolean> offsets;
    private static final /* synthetic */ ResourceLocation MAP;
    private static final /* synthetic */ ResourceLocation SHULKER_GUI_TEXTURE;
    public /* synthetic */ Setting<Integer> invH;
    public /* synthetic */ Setting<Boolean> textColor;

    static {
        MAP = new ResourceLocation("textures/map/map_background.png");
        SHULKER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/shulker_box.png");
        INSTANCE = new ToolTips();
    }

    public static void displayInv(ItemStack itemStack, String string) {
        try {
            Item item = itemStack.getItem();
            TileEntityShulkerBox tileEntityShulkerBox = new TileEntityShulkerBox();
            ItemShulkerBox itemShulkerBox = (ItemShulkerBox)item;
            tileEntityShulkerBox.blockType = itemShulkerBox.getBlock();
            tileEntityShulkerBox.setWorldObj((World)ToolTips.mc.world);
            ItemStackHelper.func_191283_b((NBTTagCompound)Objects.requireNonNull(itemStack.getTagCompound()).getCompoundTag("BlockEntityTag"), (NonNullList)tileEntityShulkerBox.field_190596_f);
            tileEntityShulkerBox.readFromNBT(itemStack.getTagCompound().getCompoundTag("BlockEntityTag"));
            tileEntityShulkerBox.func_190575_a(string == null ? itemStack.getDisplayName() : string);
            new Thread(() -> {
                try {
                    Thread.sleep(200L);
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
                ToolTips.mc.player.displayGUIChest((IInventory)tileEntityShulkerBox);
            }).start();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void renderShulkerToolTip(ItemStack itemStack, int n, int n2, String string) {
        NBTTagCompound nBTTagCompound;
        NBTTagCompound nBTTagCompound2 = itemStack.getTagCompound();
        if (nBTTagCompound2 != null && nBTTagCompound2.hasKey("BlockEntityTag", 10) && (nBTTagCompound = nBTTagCompound2.getCompoundTag("BlockEntityTag")).hasKey("Items", 9)) {
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
            mc.getTextureManager().bindTexture(SHULKER_GUI_TEXTURE);
            RenderUtil.drawTexturedRect(n, n2, 0, 0, 176, 16, 500);
            RenderUtil.drawTexturedRect(n, n2 + 16, 0, 16, 176, 54 + this.invH.getValue(), 500);
            RenderUtil.drawTexturedRect(n, n2 + 16 + 54, 0, 160, 176, 8, 500);
            GlStateManager.disableDepth();
            Color color = new Color(0, 0, 0, 255);
            if (this.textColor.getValue().booleanValue()) {
                color = new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue());
            }
            this.renderer.drawStringWithShadow(string == null ? itemStack.getDisplayName() : string, n + 8, n2 + 6, ColorUtil.toRGBA(color));
            GlStateManager.enableDepth();
            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableColorMaterial();
            GlStateManager.enableLighting();
            NonNullList nonNullList = NonNullList.func_191197_a((int)27, (Object)ItemStack.field_190927_a);
            ItemStackHelper.func_191283_b((NBTTagCompound)nBTTagCompound, (NonNullList)nonNullList);
            for (int i = 0; i < nonNullList.size(); ++i) {
                int n3 = n + i % 9 * 18 + 8;
                int n4 = n2 + i / 9 * 18 + 18;
                ItemStack itemStack2 = (ItemStack)nonNullList.get(i);
                ToolTips.mc.getRenderItem().zLevel = 501.0f;
                RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack2, n3, n4);
                RenderUtil.itemRender.renderItemOverlayIntoGUI(ToolTips.mc.fontRendererObj, itemStack2, n3, n4, null);
                ToolTips.mc.getRenderItem().zLevel = 0.0f;
            }
            GlStateManager.disableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    @SubscribeEvent
    public void renderTooltip(RenderTooltipEvent.PostText postText) {
        MapData mapData;
        if (this.maps.getValue().booleanValue() && !postText.getStack().func_190926_b() && postText.getStack().getItem() instanceof ItemMap && (mapData = Items.FILLED_MAP.getMapData(postText.getStack(), (World)ToolTips.mc.world)) != null) {
            GlStateManager.pushMatrix();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.disableStandardItemLighting();
            mc.getTextureManager().bindTexture(MAP);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();
            int n = 7;
            float f = 135.0f;
            float f2 = 0.5f;
            GlStateManager.translate((float)postText.getX(), (float)((float)postText.getY() - f * f2 - 5.0f), (float)0.0f);
            GlStateManager.scale((float)f2, (float)f2, (float)f2);
            bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferBuilder.pos((double)(-n), (double)f, 0.0).tex(0.0, 1.0).endVertex();
            bufferBuilder.pos((double)f, (double)f, 0.0).tex(1.0, 1.0).endVertex();
            bufferBuilder.pos((double)f, (double)(-n), 0.0).tex(1.0, 0.0).endVertex();
            bufferBuilder.pos((double)(-n), (double)(-n), 0.0).tex(0.0, 0.0).endVertex();
            tessellator.draw();
            ToolTips.mc.entityRenderer.getMapItemRenderer().renderMap(mapData, false);
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }

    @Override
    public void onRender2D(Render2DEvent render2DEvent) {
        if (ToolTips.fullNullCheck() || !this.shulkerSpy.getValue().booleanValue() || !this.render.getValue().booleanValue()) {
            return;
        }
        int n = -4 + this.xOffset.getValue();
        int n2 = 10 + this.yOffset.getValue();
        this.textRadarY = 0;
        for (EntityPlayer entityPlayer : ToolTips.mc.world.playerEntities) {
            Object object;
            Timer timer;
            if (this.spiedPlayers.get((Object)entityPlayer) == null) continue;
            entityPlayer.getHeldItemMainhand();
            if (!(entityPlayer.getHeldItemMainhand().getItem() instanceof ItemShulkerBox)) {
                timer = this.playerTimers.get((Object)entityPlayer);
                if (timer == null) {
                    object = new Timer();
                    ((Timer)object).reset();
                    this.playerTimers.put(entityPlayer, (Timer)object);
                } else if (timer.passedS(this.cooldown.getValue().intValue())) {
                    continue;
                }
            } else if (entityPlayer.getHeldItemMainhand().getItem() instanceof ItemShulkerBox && (timer = this.playerTimers.get((Object)entityPlayer)) != null) {
                timer.reset();
                this.playerTimers.put(entityPlayer, timer);
            }
            object = this.spiedPlayers.get((Object)entityPlayer);
            this.renderShulkerToolTip((ItemStack)object, n, n2, entityPlayer.getName());
            this.textRadarY = (n2 += this.yPerPlayer.getValue() + 60) - 10 - this.yOffset.getValue() + this.trOffset.getValue();
        }
    }

    @Override
    public void onUpdate() {
        ItemStack itemStack;
        Slot slot;
        if (ToolTips.fullNullCheck() || !this.shulkerSpy.getValue().booleanValue()) {
            return;
        }
        if (this.peek.getValue().getKey() != -1 && ToolTips.mc.currentScreen instanceof GuiContainer && Keyboard.isKeyDown((int)this.peek.getValue().getKey()) && (slot = ((GuiContainer)ToolTips.mc.currentScreen).getSlotUnderMouse()) != null && (itemStack = slot.getStack()) != null && itemStack.getItem() instanceof ItemShulkerBox) {
            ToolTips.displayInv(itemStack, null);
        }
        for (EntityPlayer entityPlayer : ToolTips.mc.world.playerEntities) {
            if (entityPlayer == null || !(entityPlayer.getHeldItemMainhand().getItem() instanceof ItemShulkerBox) || EntityUtil.isFakePlayer(entityPlayer) || !this.own.getValue().booleanValue() && ToolTips.mc.player.equals((Object)entityPlayer)) continue;
            ItemStack itemStack2 = entityPlayer.getHeldItemMainhand();
            this.spiedPlayers.put(entityPlayer, itemStack2);
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void makeTooltip(ItemTooltipEvent itemTooltipEvent) {
    }

    public int getTextRadarY() {
        return this.textRadarY;
    }

    public ToolTips() {
        super("ToolTips", "Several tweaks for tooltips.", Module.Category.MISC, true, false, false);
        this.maps = this.register(new Setting<Boolean>("Maps", true));
        this.shulkers = this.register(new Setting<Boolean>("ShulkerViewer", true));
        this.peek = this.register(new Setting<Bind>("Peek", new Bind(-1)));
        this.shulkerSpy = this.register(new Setting<Boolean>("ShulkerSpy", true));
        this.render = this.register(new Setting<Object>("Render", Boolean.TRUE, object -> this.shulkerSpy.getValue()));
        this.own = this.register(new Setting<Object>("OwnShulker", Boolean.TRUE, object -> this.shulkerSpy.getValue()));
        this.cooldown = this.register(new Setting<Object>("ShowForS", Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(5), object -> this.shulkerSpy.getValue()));
        this.textColor = this.register(new Setting<Object>("TextColor", Boolean.FALSE, object -> this.shulkers.getValue()));
        this.red = this.register(new Setting<Object>("Red", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.textColor.getValue()));
        this.green = this.register(new Setting<Object>("Green", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.textColor.getValue()));
        this.blue = this.register(new Setting<Object>("Blue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.textColor.getValue()));
        this.alpha = this.register(new Setting<Object>("Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.textColor.getValue()));
        this.offsets = this.register(new Setting<Boolean>("Offsets", false));
        this.yPerPlayer = this.register(new Setting<Object>("Y/Player", Integer.valueOf(18), object -> this.offsets.getValue()));
        this.xOffset = this.register(new Setting<Object>("XOffset", Integer.valueOf(4), object -> this.offsets.getValue()));
        this.yOffset = this.register(new Setting<Object>("YOffset", Integer.valueOf(2), object -> this.offsets.getValue()));
        this.trOffset = this.register(new Setting<Object>("TROffset", Integer.valueOf(2), object -> this.offsets.getValue()));
        this.invH = this.register(new Setting<Object>("InvH", Integer.valueOf(3), object -> this.offsets.getValue()));
        this.spiedPlayers = new ConcurrentHashMap<EntityPlayer, ItemStack>();
        this.playerTimers = new ConcurrentHashMap<EntityPlayer, Timer>();
        this.setInstance();
    }

    public static ToolTips getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ToolTips();
        }
        return INSTANCE;
    }
}

