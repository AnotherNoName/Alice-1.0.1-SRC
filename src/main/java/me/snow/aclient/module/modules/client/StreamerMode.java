//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.potion.PotionEffect
 */
package me.snow.aclient.module.modules.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.TextUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.PotionEffect;

public class StreamerMode
extends Module {
    public /* synthetic */ Setting<Integer> height;
    public /* synthetic */ Setting<Integer> width;
    private /* synthetic */ SecondScreenFrame window;

    @Override
    public void onEnable() {
        EventQueue.invokeLater(() -> {
            if (this.window == null) {
                this.window = new SecondScreenFrame();
            }
            this.window.setVisible(true);
        });
    }

    @Override
    public void onDisable() {
        if (this.window != null) {
            this.window.setVisible(false);
        }
        this.window = null;
    }

    @Override
    public void onUpdate() {
        if (this.window != null) {
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("Alice - 1.0.1");
            arrayList.add("");
            arrayList.add(String.valueOf(new StringBuilder().append("Fps: ").append(Minecraft.debugFPS)));
            arrayList.add(String.valueOf(new StringBuilder().append("TPS: ").append(AliceMain.serverManager.getTPS())));
            arrayList.add(String.valueOf(new StringBuilder().append("Ping: ").append(AliceMain.serverManager.getPing()).append("ms")));
            arrayList.add(String.valueOf(new StringBuilder().append("Speed: ").append(AliceMain.speedManager.getSpeedKpH()).append("km/h")));
            arrayList.add(String.valueOf(new StringBuilder().append("Time: ").append(new SimpleDateFormat("h:mm a").format(new Date()))));
            boolean bl = StreamerMode.mc.world.getBiome(StreamerMode.mc.player.getPosition()).getBiomeName().equals("Hell");
            int n = (int)StreamerMode.mc.player.posX;
            int n2 = (int)StreamerMode.mc.player.posY;
            int n3 = (int)StreamerMode.mc.player.posZ;
            float f = !bl ? 0.125f : 8.0f;
            int n4 = (int)(StreamerMode.mc.player.posX * (double)f);
            int n5 = (int)(StreamerMode.mc.player.posZ * (double)f);
            String string = String.valueOf(new StringBuilder().append("XYZ ").append(n).append(", ").append(n2).append(", ").append(n3).append(" [").append(n4).append(", ").append(n5).append("]"));
            String string2 = AliceMain.rotationManager.getDirection4D(false);
            arrayList.add("");
            arrayList.add(string2);
            arrayList.add(string);
            arrayList.add("");
            for (Module object2 : AliceMain.moduleManager.sortedModules) {
                String string3 = TextUtil.stripColor(object2.getFullArrayString());
                arrayList.add(string3);
            }
            arrayList.add("");
            for (PotionEffect potionEffect : AliceMain.potionManager.getOwnPotions()) {
                String string4 = TextUtil.stripColor(AliceMain.potionManager.getColoredPotionString(potionEffect));
                arrayList.add(string4);
            }
            arrayList.add("");
            Map<String, Integer> map2 = EntityUtil.getTextRadarPlayers();
            if (!map2.isEmpty()) {
                for (Map.Entry entry : map2.entrySet()) {
                    String string5 = TextUtil.stripColor((String)entry.getKey());
                    arrayList.add(string5);
                }
            }
            this.window.setToDraw(arrayList);
        }
    }

    @Override
    public void onLogout() {
        if (this.window != null) {
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("Alice - 1.0.1");
            arrayList.add("");
            arrayList.add("No Connection.");
            this.window.setToDraw(arrayList);
        }
    }

    @Override
    public void onUnload() {
        this.disable();
    }

    public StreamerMode() {
        super("StreamerMode", "Displays client info in a second window.", Module.Category.CLIENT, false, false, false);
        this.width = this.register(new Setting<Integer>("Width", 600, 100, 3160));
        this.height = this.register(new Setting<Integer>("Height", 900, 100, 2140));
    }

    @Override
    public void onLoad() {
        this.disable();
    }

    public class SecondScreenFrame
    extends JFrame {
        private /* synthetic */ SecondScreen panel;

        public void setToDraw(ArrayList<String> arrayList) {
            this.panel.setToDraw(arrayList);
        }

        private void initUI() {
            this.panel = new SecondScreen();
            this.add(this.panel);
            this.setResizable(true);
            this.pack();
            this.setTitle("Alice - Info");
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(2);
        }

        public SecondScreenFrame() {
            this.initUI();
        }
    }

    public class SecondScreen
    extends JPanel {
        private /* synthetic */ ArrayList<String> toDraw;
        private final /* synthetic */ int B_WIDTH;
        private /* synthetic */ Font font;
        private final /* synthetic */ int B_HEIGHT;

        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            this.drawScreen(graphics);
        }

        private void drawScreen(Graphics graphics) {
            Font font = this.font;
            FontMetrics fontMetrics = this.getFontMetrics(font);
            graphics.setColor(Color.white);
            graphics.setFont(font);
            int n = 40;
            for (String string : this.toDraw) {
                graphics.drawString(string, (this.getWidth() - fontMetrics.stringWidth(string)) / 2, n);
                n += 20;
            }
            Toolkit.getDefaultToolkit().sync();
        }

        @Override
        public void setFont(Font font) {
            this.font = font;
        }

        private void initBoard() {
            this.setBackground(Color.black);
            this.setFocusable(true);
            this.setPreferredSize(new Dimension(this.B_WIDTH, this.B_HEIGHT));
        }

        public SecondScreen() {
            this.B_WIDTH = StreamerMode.this.width.getValue();
            this.B_HEIGHT = StreamerMode.this.height.getValue();
            this.font = new Font("Verdana", 0, 20);
            this.toDraw = new ArrayList();
            this.initBoard();
        }

        public void setWindowSize(int n, int n2) {
            this.setPreferredSize(new Dimension(n, n2));
        }

        public void setToDraw(ArrayList<String> arrayList) {
            this.toDraw = arrayList;
            this.repaint();
        }
    }
}

