//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParser
 *  com.mojang.util.UUIDTypeAdapter
 *  javafx.util.Pair
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockConcretePowder
 *  net.minecraft.block.BlockEnderChest
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.client.network.NetworkPlayerInfo
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package me.snow.aclient.util.ca.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.util.UUIDTypeAdapter;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import javafx.util.Pair;
import javax.annotation.Nullable;
import me.snow.aclient.AliceMain;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.ca.util.CrystalUtilCa;
import net.minecraft.block.Block;
import net.minecraft.block.BlockConcretePowder;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockObsidian;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class PlayerUtilCa
implements Util {
    private static final /* synthetic */ BlockPos[] surroundOffset;

    public static ArrayList<Pair<EntityPlayer, ArrayList<BlockPos>>> GetPlayersReadyToBeCitied() {
        ArrayList<Pair<EntityPlayer, ArrayList<BlockPos>>> arrayList = new ArrayList<Pair<EntityPlayer, ArrayList<BlockPos>>>();
        for (Entity entity : PlayerUtilCa.mc.world.playerEntities.stream().filter(entityPlayer -> !AliceMain.friendManager.isFriend(entityPlayer.getName())).collect(Collectors.toList())) {
            ArrayList<BlockPos> arrayList2 = new ArrayList<BlockPos>();
            for (int i = 0; i < 4; ++i) {
                BlockPos blockPos = PlayerUtilCa.GetPositionVectorBlockPos(entity, surroundOffset[i]);
                if (PlayerUtilCa.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) continue;
                boolean bl = false;
                switch (i) {
                    case 0: {
                        bl = CrystalUtilCa.canPlaceCrystal(blockPos.north(1).down(), false, false);
                        break;
                    }
                    case 1: {
                        bl = CrystalUtilCa.canPlaceCrystal(blockPos.east(1).down(), false, false);
                        break;
                    }
                    case 2: {
                        bl = CrystalUtilCa.canPlaceCrystal(blockPos.south(1).down(), false, false);
                        break;
                    }
                    case 3: {
                        bl = CrystalUtilCa.canPlaceCrystal(blockPos.west(1).down(), false, false);
                    }
                }
                if (!bl) continue;
                arrayList2.add(blockPos);
            }
            if (arrayList2.isEmpty()) continue;
            arrayList.add((Pair<EntityPlayer, ArrayList<BlockPos>>)new Pair((Object)((EntityPlayer)entity), arrayList2));
        }
        return arrayList;
    }

    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(PlayerUtilCa.mc.player.posX), Math.floor(PlayerUtilCa.mc.player.posY), Math.floor(PlayerUtilCa.mc.player.posZ));
    }

    public static ItemStack getItemStackFromItem(Item item) {
        if (PlayerUtilCa.mc.player == null) {
            return null;
        }
        for (int i = 0; i <= 9; ++i) {
            if (PlayerUtilCa.mc.player.inventory.getStackInSlot(i).getItem() != item) continue;
            return PlayerUtilCa.mc.player.inventory.getStackInSlot(i);
        }
        return null;
    }

    public static double getBaseMoveSpeed() {
        double d = 0.2873;
        if (PlayerUtilCa.mc.player != null && PlayerUtilCa.mc.player.isPotionActive(Potion.getPotionById((int)1))) {
            int n = PlayerUtilCa.mc.player.getActivePotionEffect(Potion.getPotionById((int)1)).getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
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
            String string3 = PlayerUtilCa.convertStreamToString(bufferedInputStream);
            ((InputStream)bufferedInputStream).close();
            httpURLConnection.disconnect();
            return string3;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static double[] forward(double d) {
        float f = PlayerUtilCa.mc.player.movementInput.field_192832_b;
        float f2 = PlayerUtilCa.mc.player.movementInput.moveStrafe;
        float f3 = PlayerUtilCa.mc.player.prevRotationYaw + (PlayerUtilCa.mc.player.rotationYaw - PlayerUtilCa.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
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

    public static BlockPos GetPositionVectorBlockPos(Entity entity, @Nullable BlockPos blockPos) {
        Vec3d vec3d = entity.getPositionVector();
        if (blockPos == null) {
            return new BlockPos(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
        }
        return new BlockPos(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord).add((Vec3i)blockPos);
    }

    public static List<BlockPos> getSphere(BlockPos blockPos, float f, int n, boolean bl, boolean bl2, int n2) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n3 = blockPos.getX();
        int n4 = blockPos.getY();
        int n5 = blockPos.getZ();
        int n6 = n3 - (int)f;
        while ((float)n6 <= (float)n3 + f) {
            int n7 = n5 - (int)f;
            while ((float)n7 <= (float)n5 + f) {
                int n8 = bl2 ? n4 - (int)f : n4 - n;
                while (true) {
                    float f2 = n8;
                    float f3 = bl2 ? (float)n4 + f : (float)(n4 + n);
                    if (!(f2 < f3)) break;
                    double d = (n3 - n6) * (n3 - n6) + (n5 - n7) * (n5 - n7) + (bl2 ? (n4 - n8) * (n4 - n8) : 0);
                    if (!(!(d < (double)(f * f)) || bl && d < (double)((f - 1.0f) * (f - 1.0f)))) {
                        BlockPos blockPos2 = new BlockPos(n6, n8 + n2, n7);
                        arrayList.add(blockPos2);
                    }
                    ++n8;
                }
                ++n7;
            }
            ++n6;
        }
        return arrayList;
    }

    public static String convertStreamToString(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String string = null;
        while ((string = bufferedReader.readLine()) != null) {
            stringBuilder.append(String.valueOf(new StringBuilder().append(string).append("\n")));
        }
        inputStream.close();
        return String.valueOf(stringBuilder);
    }

    public static Item getBestItem(Block block) {
        String string = block.getHarvestTool(block.getDefaultState());
        if (string != null) {
            switch (string) {
                case "axe": {
                    return Items.DIAMOND_AXE;
                }
                case "shovel": {
                    return Items.DIAMOND_SHOVEL;
                }
            }
            return Items.DIAMOND_PICKAXE;
        }
        return Items.DIAMOND_PICKAXE;
    }

    public static BlockPos getPlayerPos(double d) {
        return new BlockPos(Math.floor(PlayerUtilCa.mc.player.posX), Math.floor(PlayerUtilCa.mc.player.posY + d), Math.floor(PlayerUtilCa.mc.player.posZ));
    }

    static {
        surroundOffset = new BlockPos[]{new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0)};
    }

    public static boolean isInHole() {
        BlockPos blockPos = PlayerUtilCa.getPlayerPos();
        return PlayerUtilCa.mc.world.getBlockState(blockPos.east()).getBlock() != Blocks.AIR && PlayerUtilCa.mc.world.getBlockState(blockPos.west()).getBlock() != Blocks.AIR && PlayerUtilCa.mc.world.getBlockState(blockPos.north()).getBlock() != Blocks.AIR && PlayerUtilCa.mc.world.getBlockState(blockPos.south()).getBlock() != Blocks.AIR;
    }

    public static int findSandInHotbar() {
        for (int i = 0; i < 9; ++i) {
            Block block;
            ItemStack itemStack = PlayerUtilCa.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock) || !((block = ((ItemBlock)itemStack.getItem()).getBlock()) instanceof BlockConcretePowder)) continue;
            return i;
        }
        return -1;
    }

    public static boolean isMoving(EntityLivingBase entityLivingBase) {
        return entityLivingBase.field_191988_bg != 0.0f || entityLivingBase.moveStrafing != 0.0f;
    }

    public static void setSpeed(EntityLivingBase entityLivingBase, double d) {
        double[] arrd = PlayerUtilCa.forward(d);
        entityLivingBase.motionX = arrd[0];
        entityLivingBase.motionZ = arrd[1];
    }

    public static FacingDirection getFacing() {
        int n = (int)Math.floor(PlayerUtilCa.mc.player.getRotationYawHead());
        if (n <= 0) {
            n += 360;
        }
        n = (n % 360 + 360) % 360;
        int n2 = (n += 45) / 45;
        switch (n2) {
            case 0: 
            case 1: {
                return FacingDirection.SOUTH;
            }
            case 2: 
            case 3: {
                return FacingDirection.WEST;
            }
            case 4: 
            case 5: {
                return FacingDirection.NORTH;
            }
            case 6: 
            case 7: {
                return FacingDirection.EAST;
            }
        }
        return FacingDirection.SOUTH;
    }

    public static int findObiInHotbar() {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = PlayerUtilCa.mc.player.inventory.getStackInSlot(i);
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

    public static class lookUpUUID
    implements Runnable {
        private volatile /* synthetic */ UUID uuid;
        private final /* synthetic */ String name;

        public lookUpUUID(String string) {
            this.name = string;
        }

        @Override
        public void run() {
            JsonElement jsonElement;
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
            if (networkPlayerInfo2 == null && (object = PlayerUtilCa.requestIDs(String.valueOf(new StringBuilder().append("[\"").append(this.name).append("\"]")))) != null && !((String)object).isEmpty() && (jsonElement = new JsonParser().parse((String)object)).getAsJsonArray().size() != 0) {
                try {
                    String string = jsonElement.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
                    this.uuid = UUIDTypeAdapter.fromString((String)string);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }

        public UUID getUUID() {
            return this.uuid;
        }

        public String getName() {
            return this.name;
        }
    }

    public static enum FacingDirection {
        NORTH,
        SOUTH,
        EAST,
        WEST;

    }
}

