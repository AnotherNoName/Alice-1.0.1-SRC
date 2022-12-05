//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.mojang.util.UUIDTypeAdapter
 *  net.minecraft.advancements.AdvancementManager
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockEnderChest
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.network.NetworkPlayerInfo
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  org.apache.commons.io.IOUtils
 */
package me.snow.aclient.util;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.util.UUIDTypeAdapter;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.Util;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockObsidian;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class PlayerUtil
implements Util {
    private static /* synthetic */ JsonParser PARSER;
    public static /* synthetic */ Timer timer;

    public static String getNameFromUUID(String string) {
        try {
            lookUpName lookUpName2 = new lookUpName(string);
            Thread thread2 = new Thread(lookUpName2);
            thread2.start();
            thread2.join();
            return lookUpName2.getName();
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static EntityPlayer findLookingPlayer(double d) {
        EntityPlayer entityPlayer22;
        ArrayList<EntityPlayer> arrayList = new ArrayList<EntityPlayer>();
        for (EntityPlayer entityPlayer22 : PlayerUtil.mc.world.playerEntities) {
            if (entityPlayer22.getName().equals(PlayerUtil.mc.player.getName()) || AliceMain.friendManager.isFriend(entityPlayer22.getName()) || entityPlayer22.isDead || !((double)PlayerUtil.mc.player.getDistanceToEntity((Entity)entityPlayer22) <= d)) continue;
            arrayList.add(entityPlayer22);
        }
        EntityPlayer entityPlayer3 = null;
        entityPlayer22 = PlayerUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks());
        Vec3d vec3d = PlayerUtil.mc.player.getLook(mc.getRenderPartialTicks());
        int n = 2;
        for (int i = 0; i < (int)d; ++i) {
            for (int j = n; j > 0; --j) {
                for (Entity entity : arrayList) {
                    AxisAlignedBB axisAlignedBB = entity.getEntityBoundingBox();
                    double d2 = entityPlayer22.xCoord + vec3d.xCoord * (double)i + vec3d.xCoord / (double)j;
                    double d3 = entityPlayer22.yCoord + vec3d.yCoord * (double)i + vec3d.yCoord / (double)j;
                    double d4 = entityPlayer22.zCoord + vec3d.zCoord * (double)i + vec3d.zCoord / (double)j;
                    if (!(axisAlignedBB.maxY >= d3) || !(axisAlignedBB.minY <= d3) || !(axisAlignedBB.maxX >= d2) || !(axisAlignedBB.minX <= d2) || !(axisAlignedBB.maxZ >= d4) || !(axisAlignedBB.minZ <= d4)) continue;
                    entityPlayer3 = (EntityPlayer)entity;
                }
            }
        }
        return entityPlayer3;
    }

    public static double[] directionSpeed(double d) {
        float f = PlayerUtil.mc.player.movementInput.field_192832_b;
        float f2 = PlayerUtil.mc.player.movementInput.moveStrafe;
        float f3 = PlayerUtil.mc.player.prevRotationYaw + (PlayerUtil.mc.player.rotationYaw - PlayerUtil.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
        double d3 = Math.cos(Math.toRadians(f3 + 90.0f));
        double d4 = (double)f * d * d3 + (double)f2 * d * d2;
        double d5 = (double)f * d * d2 - (double)f2 * d * d3;
        return new double[]{d4, d5};
    }

    public static boolean IsEating() {
        return PlayerUtil.mc.player != null && PlayerUtil.mc.player.getHeldItemMainhand().getItem() instanceof ItemFood && PlayerUtil.mc.player.isHandActive();
    }

    public static void faceVector(Vec3d vec3d, boolean bl) {
        float[] arrf = EntityUtil.getLegitRotations(vec3d);
        Util.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], bl ? (float)MathHelper.normalizeAngle((int)((int)arrf[1]), (int)360) : arrf[1], Util.mc.player.onGround));
    }

    private static JsonElement getResources(URL uRL, String string) throws Exception {
        return PlayerUtil.getResources(uRL, string, null);
    }

    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ));
    }

    static {
        timer = new Timer();
        PARSER = new JsonParser();
    }

    public static BlockPos getPlayerPosFloored() {
        return new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ));
    }

    public static int findObiInHotbar() {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = PlayerUtil.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock)) continue;
            Block block = ((ItemBlock)itemStack.getItem()).getBlock();
            if (block instanceof BlockEnderChest) {
                return i;
            }
            if (!(block instanceof BlockObsidian)) continue;
            return i;
        }
        return -1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static JsonElement getResources(URL uRL, String string, JsonElement jsonElement) throws Exception {
        HttpsURLConnection httpsURLConnection = null;
        try {
            Closeable closeable;
            httpsURLConnection = (HttpsURLConnection)uRL.openConnection();
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setRequestMethod(string);
            httpsURLConnection.setRequestProperty("Content-Type", "application/json");
            if (jsonElement != null) {
                closeable = new DataOutputStream(httpsURLConnection.getOutputStream());
                ((DataOutputStream)closeable).writeBytes(AdvancementManager.field_192783_b.toJson(jsonElement));
                ((FilterOutputStream)closeable).close();
            }
            closeable = new Scanner(httpsURLConnection.getInputStream());
            StringBuilder stringBuilder = new StringBuilder();
            while (((Scanner)closeable).hasNextLine()) {
                stringBuilder.append(((Scanner)closeable).nextLine());
                stringBuilder.append('\n');
            }
            ((Scanner)closeable).close();
            String string2 = String.valueOf(stringBuilder);
            JsonElement jsonElement2 = PARSER.parse(string2);
            return jsonElement2;
        }
        finally {
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
        }
    }

    public static List<String> getHistoryOfNames(UUID uUID) {
        try {
            JsonArray jsonArray = PlayerUtil.getResources(new URL(String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(PlayerUtil.getIdNoHyphens(uUID)).append("/names"))), "GET").getAsJsonArray();
            ArrayList arrayList = Lists.newArrayList();
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String string = jsonObject.get("name").getAsString();
                long l = jsonObject.has("changedToAt") ? jsonObject.get("changedToAt").getAsLong() : 0L;
                arrayList.add(String.valueOf(new StringBuilder().append(string).append("\u00a78").append(new Date(l))));
            }
            Collections.sort(arrayList);
            return arrayList;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static double getDistance(Entity entity) {
        return PlayerUtil.mc.player.getDistanceToEntity(entity);
    }

    public static double getDistance(BlockPos blockPos) {
        return PlayerUtil.mc.player.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
    }

    public static double getDistance(Vec3d vec3d) {
        return PlayerUtil.mc.player.getDistance(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
    }

    public static boolean isPlayerMoving() {
        return PlayerUtil.mc.player.movementInput.moveStrafe != 0.0f || PlayerUtil.mc.player.movementInput.field_192832_b != 0.0f;
    }

    public static String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "/";
    }

    public static String requestIDs(String string) {
        try {
            String string2 = "https://api.mojang.com/profiles/minecraft";
            URL uRL = new URL("https://api.mojang.com/profiles/minecraft");
            HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(string.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            String string3 = PlayerUtil.convertStreamToString(bufferedInputStream);
            ((InputStream)bufferedInputStream).close();
            httpURLConnection.disconnect();
            return string3;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static double[] directionSpeed(double d, EntityPlayerSP entityPlayerSP) {
        Minecraft minecraft = Minecraft.getMinecraft();
        float f = entityPlayerSP.movementInput.field_192832_b;
        float f2 = entityPlayerSP.movementInput.moveStrafe;
        float f3 = entityPlayerSP.prevRotationYaw + (entityPlayerSP.rotationYaw - entityPlayerSP.prevRotationYaw) * minecraft.getRenderPartialTicks();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
        double d3 = Math.cos(Math.toRadians(f3 + 90.0f));
        double d4 = (double)f * d * d3 + (double)f2 * d * d2;
        double d5 = (double)f * d * d2 - (double)f2 * d * d3;
        return new double[]{d4, d5};
    }

    public static String getIdNoHyphens(UUID uUID) {
        return uUID.toString().replaceAll("-", "");
    }

    public static UUID getUUIDFromName(String string) {
        try {
            lookUpUUID lookUpUUID2 = new lookUpUUID(string);
            Thread thread2 = new Thread(lookUpUUID2);
            thread2.start();
            thread2.join();
            return lookUpUUID2.getUUID();
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static boolean isInHole(Entity entity) {
        BlockPos blockPos = new BlockPos(Math.floor(entity.getPositionVector().xCoord), Math.floor(entity.getPositionVector().yCoord + 0.2), Math.floor(entity.getPositionVector().zCoord));
        return PlayerUtil.mc.world.getBlockState((BlockPos)blockPos.down()).getBlock().blockResistance >= 1200.0f && PlayerUtil.mc.world.getBlockState((BlockPos)blockPos.east()).getBlock().blockResistance >= 1200.0f && PlayerUtil.mc.world.getBlockState((BlockPos)blockPos.west()).getBlock().blockResistance >= 1200.0f && PlayerUtil.mc.world.getBlockState((BlockPos)blockPos.north()).getBlock().blockResistance >= 1200.0f && PlayerUtil.mc.world.getBlockState((BlockPos)blockPos.south()).getBlock().blockResistance >= 1200.0f;
    }

    public static String getNameFromUUID(UUID uUID) {
        try {
            lookUpName lookUpName2 = new lookUpName(uUID);
            Thread thread2 = new Thread(lookUpName2);
            thread2.start();
            thread2.join();
            return lookUpName2.getName();
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static class lookUpUUID
    implements Runnable {
        private volatile /* synthetic */ UUID uuid;
        private final /* synthetic */ String name;

        public UUID getUUID() {
            return this.uuid;
        }

        public lookUpUUID(String string) {
            this.name = string;
        }

        @Override
        public void run() {
            NetworkPlayerInfo networkPlayerInfo2;
            Object object;
            try {
                object = new ArrayList(Objects.requireNonNull(Util.mc.getConnection()).getPlayerInfoMap());
                networkPlayerInfo2 = object.stream().filter(networkPlayerInfo -> networkPlayerInfo.getGameProfile().getName().equalsIgnoreCase(this.name)).findFirst().orElse(null);
                assert (networkPlayerInfo2 != null);
                this.uuid = networkPlayerInfo2.getGameProfile().getId();
            }
            catch (Exception exception) {
                networkPlayerInfo2 = null;
            }
            if (networkPlayerInfo2 == null) {
                Command.sendMessage("Player isn't online. Looking up UUID..");
                object = PlayerUtil.requestIDs(String.valueOf(new StringBuilder().append("[\"").append(this.name).append("\"]")));
                if (object == null || ((String)object).isEmpty()) {
                    Command.sendMessage("Couldn't find player ID. Are you connected to the internet? (0)");
                } else {
                    JsonElement jsonElement = new JsonParser().parse((String)object);
                    if (jsonElement.getAsJsonArray().size() == 0) {
                        Command.sendMessage("Couldn't find player ID. (1)");
                    } else {
                        try {
                            String string = jsonElement.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
                            this.uuid = UUIDTypeAdapter.fromString((String)string);
                        }
                        catch (Exception exception) {
                            exception.printStackTrace();
                            Command.sendMessage("Couldn't find player ID. (2)");
                        }
                    }
                }
            }
        }

        public String getName() {
            return this.name;
        }
    }

    public static class lookUpName
    implements Runnable {
        private final /* synthetic */ UUID uuidID;
        private volatile /* synthetic */ String name;
        private final /* synthetic */ String uuid;

        public lookUpName(String string) {
            this.uuid = string;
            this.uuidID = UUID.fromString(string);
        }

        public String getName() {
            return this.name;
        }

        @Override
        public void run() {
            this.name = this.lookUpName();
        }

        public lookUpName(UUID uUID) {
            this.uuidID = uUID;
            this.uuid = uUID.toString();
        }

        public String lookUpName() {
            EntityPlayer entityPlayer = null;
            if (Util.mc.world != null) {
                entityPlayer = Util.mc.world.getPlayerEntityByUUID(this.uuidID);
            }
            if (entityPlayer == null) {
                String string = String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(this.uuid.replace("-", "")).append("/names"));
                try {
                    String string2 = IOUtils.toString((URL)new URL(string));
                    JSONArray jSONArray = (JSONArray)JSONValue.parseWithException(string2);
                    String string3 = jSONArray.get(jSONArray.size() - 1).toString();
                    JSONObject jSONObject = (JSONObject)JSONValue.parseWithException(string3);
                    return jSONObject.get("name").toString();
                }
                catch (IOException | ParseException exception) {
                    exception.printStackTrace();
                    return null;
                }
            }
            return entityPlayer.getName();
        }
    }
}

