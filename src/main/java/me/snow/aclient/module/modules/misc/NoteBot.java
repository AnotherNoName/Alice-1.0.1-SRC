//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.network.play.server.SPacketBlockAction
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ClientEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.MathUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.server.SPacketBlockAction;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoteBot
extends Module {
    private /* synthetic */ boolean tuned;
    private /* synthetic */ BlockPos endPos;
    private /* synthetic */ int soundIndex;
    private /* synthetic */ int tuneIndex;
    private final /* synthetic */ File file;
    private /* synthetic */ int index;
    private /* synthetic */ Map<Sound, BlockPos[]> soundPositions;
    private final /* synthetic */ Setting<String> loadFileSet;
    private final /* synthetic */ Setting<Boolean> downloadSongs;
    private /* synthetic */ IRegister[] registers;
    private /* synthetic */ BlockPos nextPos;
    private final /* synthetic */ Map<Sound, Byte> soundBytes;
    private final /* synthetic */ Setting<Boolean> tune;
    private final /* synthetic */ List<BlockPos> posList;
    private final /* synthetic */ Setting<Boolean> active;
    private final /* synthetic */ List<SoundEntry> soundEntries;
    private /* synthetic */ BlockPos currentPos;
    private /* synthetic */ int tuneStage;
    private /* synthetic */ Map<BlockPos, AtomicInteger> posPitch;

    @Override
    public void onEnable() {
        if (NoteBot.nullCheck()) {
            this.disable();
            return;
        }
        this.soundEntries.clear();
        this.getNoteBlocks();
        this.soundIndex = 0;
        this.index = 0;
        this.resetTuning();
    }

    private BlockPos getRegisterPos(SoundRegister soundRegister) {
        SoundEntry soundEntry2 = this.soundEntries.stream().filter(soundEntry -> soundEntry.getRegister().equals(soundRegister)).findFirst().orElse(null);
        if (soundEntry2 == null) {
            return null;
        }
        return soundEntry2.getPos();
    }

    public static Map<Sound, BlockPos[]> setUpSoundMap() {
        NoteBot.mc.player.getPosition();
        LinkedHashMap<Sound, BlockPos[]> linkedHashMap = new LinkedHashMap<Sound, BlockPos[]>();
        HashMap hashMap = new HashMap();
        Arrays.asList(Sound.values()).forEach(sound -> {
            BlockPos[] arrblockPos = new BlockPos[25];
            linkedHashMap.put((Sound)((Object)sound), arrblockPos);
            hashMap.put(sound, new AtomicInteger());
        });
        for (int i = -6; i < 6; ++i) {
            for (int j = -1; j < 5; ++j) {
                for (int k = -6; k < 6; ++k) {
                    Sound sound2;
                    int n;
                    BlockPos blockPos = NoteBot.mc.player.getPosition().add(i, j, k);
                    Block block = NoteBot.mc.world.getBlockState(blockPos).getBlock();
                    if (!(NoteBot.distanceSqToCenter(blockPos) < 27.040000000000003) || block != Blocks.NOTEBLOCK || (n = ((AtomicInteger)hashMap.get((Object)(sound2 = NoteBot.getSoundFromBlockState(NoteBot.mc.world.getBlockState(blockPos.down()))))).getAndIncrement()) >= 25) continue;
                    linkedHashMap.get((Object)sound2)[n] = blockPos;
                }
            }
        }
        return linkedHashMap;
    }

    public NoteBot() {
        super("NoteBot", "Plays songs.", Module.Category.MISC, true, false, false);
        this.tune = this.register(new Setting<Boolean>("Tune", false));
        this.active = this.register(new Setting<Boolean>("Active", false));
        this.downloadSongs = this.register(new Setting<Boolean>("DownloadSongs", false));
        this.loadFileSet = this.register(new Setting<String>("Load", "Load File..."));
        this.soundBytes = new HashMap<Sound, Byte>();
        this.soundEntries = new ArrayList<SoundEntry>();
        this.posList = new ArrayList<BlockPos>();
        this.file = new File(AliceMain.fileManager.getNotebot().toString());
    }

    @Override
    public void onLoad() {
        if (NoteBot.fullNullCheck()) {
            this.disable();
        }
    }

    private void tunePre() {
        this.currentPos = null;
        if (this.tuneStage == 1 && this.getAtomicBlockPos(null) == null) {
            if (this.tuned) {
                Command.sendMessage("Done tuning.");
                this.tune.setValue(false);
            } else {
                this.tuned = true;
                this.tuneStage = 0;
                this.tuneIndex = 0;
            }
        } else {
            if (this.tuneStage != 0) {
                this.nextPos = this.currentPos = this.getAtomicBlockPos(this.nextPos);
            } else {
                while (this.tuneIndex < 250 && this.currentPos == null) {
                    this.currentPos = this.soundPositions.get((Object)Sound.values()[(int)Math.floor(this.tuneIndex / 25)])[this.tuneIndex % 25];
                    ++this.tuneIndex;
                }
            }
            if (this.currentPos != null) {
                AliceMain.rotationManager.lookAtPos(this.currentPos);
            }
        }
    }

    public static Sound getSoundFromBlockState(IBlockState iBlockState) {
        if (iBlockState.getBlock() == Blocks.CLAY) {
            return Sound.CLAY;
        }
        if (iBlockState.getBlock() == Blocks.GOLD_BLOCK) {
            return Sound.GOLD;
        }
        if (iBlockState.getBlock() == Blocks.WOOL) {
            return Sound.WOOL;
        }
        if (iBlockState.getBlock() == Blocks.PACKED_ICE) {
            return Sound.ICE;
        }
        if (iBlockState.getBlock() == Blocks.BONE_BLOCK) {
            return Sound.BONE;
        }
        if (iBlockState.getMaterial() == Material.ROCK) {
            return Sound.ROCK;
        }
        if (iBlockState.getMaterial() == Material.SAND) {
            return Sound.SAND;
        }
        if (iBlockState.getMaterial() == Material.GLASS) {
            return Sound.GLASS;
        }
        return iBlockState.getMaterial() == Material.WOOD ? Sound.WOOD : Sound.NONE;
    }

    private void noteBotPost() {
        for (int i = 0; i < this.posList.size(); ++i) {
            BlockPos blockPos = this.posList.get(i);
            if (blockPos == null) continue;
            if (i != 0) {
                float[] arrf = MathUtil.calcAngle(NoteBot.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((double)((float)blockPos.getX() + 0.5f), (double)((float)blockPos.getY() + 0.5f), (double)((float)blockPos.getZ() + 0.5f)));
                NoteBot.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], arrf[1], NoteBot.mc.player.onGround));
            }
            this.clickNoteBlock(blockPos);
        }
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayerEvent(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.downloadSongs.getValue().booleanValue()) {
            this.downloadSongs();
            Command.sendMessage("Songs downloaded");
            this.downloadSongs.setValue(false);
        }
        if (updateWalkingPlayerEvent.getStage() == 0) {
            if (this.tune.getValue().booleanValue()) {
                this.tunePre();
            } else if (this.active.getValue().booleanValue()) {
                this.noteBotPre();
            }
        } else if (this.tune.getValue().booleanValue()) {
            this.tunePost();
        } else if (this.active.getValue().booleanValue()) {
            this.noteBotPost();
        }
    }

    public static void unzip(File file, File file2) {
        ZipEntry zipEntry;
        ZipInputStream zipInputStream;
        byte[] arrby = new byte[1024];
        try {
            if (!file2.exists()) {
                file2.mkdir();
            }
            zipInputStream = new ZipInputStream(new FileInputStream(file));
            zipEntry = zipInputStream.getNextEntry();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return;
        }
        while (true) {
            FileOutputStream fileOutputStream;
            try {
                int n;
                if (zipEntry == null) break;
                String string = zipEntry.getName();
                File file3 = new File(file2, string);
                new File(file3.getParent()).mkdirs();
                fileOutputStream = new FileOutputStream(file3);
                while ((n = zipInputStream.read(arrby)) > 0) {
                    fileOutputStream.write(arrby, 0, n);
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                return;
            }
            try {
                fileOutputStream.close();
                zipEntry = zipInputStream.getNextEntry();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                return;
            }
        }
        try {
            zipInputStream.closeEntry();
            zipInputStream.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void resetTuning() {
        if (NoteBot.mc.world == null || NoteBot.mc.player == null) {
            this.disable();
            return;
        }
        this.tuned = true;
        this.soundPositions = NoteBot.setUpSoundMap();
        this.posPitch = new LinkedHashMap<BlockPos, AtomicInteger>();
        this.soundPositions.values().forEach(arrblockPos -> Arrays.asList(arrblockPos).forEach(blockPos -> {
            if (blockPos != null) {
                this.endPos = blockPos;
                this.posPitch.put((BlockPos)blockPos, new AtomicInteger(-1));
            }
        }));
        this.tuneStage = 0;
        this.tuneIndex = 0;
    }

    private void tunePost() {
        if (this.tuneStage == 0 && this.currentPos != null) {
            EnumFacing enumFacing = BlockUtil.getFacing(this.currentPos);
            NoteBot.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.currentPos, enumFacing));
            NoteBot.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.currentPos, enumFacing));
        } else if (this.currentPos != null) {
            this.posPitch.get((Object)this.currentPos).decrementAndGet();
            NoteBot.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.currentPos, BlockUtil.getFacing(this.currentPos), EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
        }
    }

    private void fillSoundBytes() {
        this.soundBytes.clear();
        for (Sound sound : Sound.values()) {
            this.soundBytes.put(sound, (byte)0);
        }
    }

    private void getNoteBlocks() {
        this.fillSoundBytes();
        for (int i = -6; i < 6; ++i) {
            for (int j = -1; j < 5; ++j) {
                for (int k = -6; k < 6; ++k) {
                    Sound sound;
                    byte by;
                    BlockPos blockPos = NoteBot.mc.player.getPosition().add(i, j, k);
                    Block block = NoteBot.mc.world.getBlockState(blockPos).getBlock();
                    if (!(blockPos.distanceSqToCenter(NoteBot.mc.player.posX, NoteBot.mc.player.posY + (double)NoteBot.mc.player.getEyeHeight(), NoteBot.mc.player.posZ) < 27.0) || block != Blocks.NOTEBLOCK || (by = this.soundBytes.get((Object)(sound = NoteBot.getSoundFromBlockState(NoteBot.mc.world.getBlockState(blockPos.down())))).byteValue()) > 25) continue;
                    this.soundEntries.add(new SoundEntry(blockPos, new SoundRegister(sound, by)));
                    this.soundBytes.replace(sound, (byte)(by + 1));
                }
            }
        }
    }

    @SubscribeEvent
    public void onSettingChange(ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting() != null && this.equals(clientEvent.getSetting().getFeature())) {
            if (clientEvent.getSetting().equals(this.loadFileSet)) {
                String string = this.loadFileSet.getPlannedValue();
                try {
                    this.registers = NoteBot.createRegister(new File(String.valueOf(new StringBuilder().append("Alice/notebot/").append(string))));
                    Command.sendMessage(String.valueOf(new StringBuilder().append("Loaded: ").append(string)));
                }
                catch (Exception exception) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("An Error occurred with ").append(string)));
                    exception.printStackTrace();
                }
                clientEvent.setCanceled(true);
            } else if (clientEvent.getSetting().equals(this.tune) && this.tune.getPlannedValue().booleanValue()) {
                this.resetTuning();
            }
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (this.tune.getValue().booleanValue() && receive.getPacket() instanceof SPacketBlockAction && this.tuneStage == 0 && this.soundPositions != null) {
            SPacketBlockAction sPacketBlockAction = (SPacketBlockAction)receive.getPacket();
            Sound sound = Sound.values()[sPacketBlockAction.getData1()];
            int n = sPacketBlockAction.getData2();
            BlockPos[] arrblockPos = this.soundPositions.get((Object)sound);
            for (int i = 0; i < 25; ++i) {
                BlockPos blockPos = arrblockPos[i];
                if (!sPacketBlockAction.getBlockPosition().equals((Object)blockPos)) continue;
                if (this.posPitch.get((Object)blockPos).intValue() != -1) break;
                int n2 = i - n;
                if (n2 < 0) {
                    n2 += 25;
                }
                this.posPitch.get((Object)blockPos).set(n2);
                if (n2 == 0) break;
                this.tuned = false;
                break;
            }
            if (this.endPos.equals((Object)sPacketBlockAction.getBlockPosition()) && this.tuneIndex >= this.posPitch.values().size()) {
                this.tuneStage = 1;
            }
        }
    }

    private static double distanceSqToCenter(BlockPos blockPos) {
        double d = Math.abs(NoteBot.mc.player.posX - (double)blockPos.getX() - 0.5);
        double d2 = Math.abs(NoteBot.mc.player.posY + (double)NoteBot.mc.player.getEyeHeight() - (double)blockPos.getY() - 0.5);
        double d3 = Math.abs(NoteBot.mc.player.posZ - (double)blockPos.getZ() - 0.5);
        return d * d + d2 * d2 + d3 * d3;
    }

    private void clickNoteBlock(BlockPos blockPos) {
        EnumFacing enumFacing = BlockUtil.getFacing(blockPos);
        NoteBot.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, enumFacing));
        NoteBot.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, blockPos, enumFacing));
    }

    private void noteBotPre() {
        this.posList.clear();
        if (this.registers == null) {
            return;
        }
        while (this.index < this.registers.length) {
            BlockPos blockPos;
            IRegister iRegister;
            IRegister iRegister2 = this.registers[this.index];
            if (iRegister2 instanceof SimpleRegister) {
                iRegister = (SimpleRegister)iRegister2;
                if (++this.soundIndex >= ((SimpleRegister)iRegister).getSound()) {
                    ++this.index;
                    this.soundIndex = 0;
                }
                if (this.posList.size() > 0) {
                    blockPos = this.posList.get(0);
                    AliceMain.rotationManager.lookAtPos(blockPos);
                }
                return;
            }
            if (!(iRegister2 instanceof SoundRegister)) continue;
            iRegister = (SoundRegister)iRegister2;
            blockPos = this.getRegisterPos((SoundRegister)iRegister);
            if (blockPos != null) {
                this.posList.add(blockPos);
            }
            ++this.index;
        }
        this.index = 0;
    }

    private void downloadSongs() {
        new Thread(() -> {
            try {
                File file = new File(this.file, "songs.zip");
                FileChannel fileChannel = new FileOutputStream(file).getChannel();
                ReadableByteChannel readableByteChannel = Channels.newChannel(new URL("https://www.futureclient.net/future/songs.zip").openStream());
                fileChannel.transferFrom(readableByteChannel, 0L, Long.MAX_VALUE);
                NoteBot.unzip(file, this.file);
                file.deleteOnExit();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }).start();
    }

    private BlockPos getAtomicBlockPos(BlockPos blockPos) {
        AtomicInteger atomicInteger;
        BlockPos blockPos2;
        Iterator<Map.Entry<BlockPos, AtomicInteger>> iterator2 = this.posPitch.entrySet().iterator();
        do {
            if (!iterator2.hasNext()) {
                return null;
            }
            Map.Entry<BlockPos, AtomicInteger> entry = iterator2.next();
            blockPos2 = entry.getKey();
            atomicInteger = entry.getValue();
        } while (blockPos2 == null || blockPos2.equals((Object)blockPos) || atomicInteger.intValue() <= 0);
        return blockPos2;
    }

    private static IRegister[] createRegister(File file) throws IOException {
        int n;
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] arrby = new byte[fileInputStream.available()];
        fileInputStream.read(arrby);
        ArrayList<IRegister> arrayList = new ArrayList<IRegister>();
        boolean bl = true;
        for (int n2 : arrby) {
            n = n2;
            if (n != 64) continue;
            bl = false;
            break;
        }
        int n3 = 0;
        int n4 = 0;
        while (n4 < arrby.length) {
            byte by = arrby[n3];
            if (by == (bl ? (byte)5 : 64)) {
                byte[] arrby2 = new byte[]{arrby[++n3], arrby[++n3]};
                n = arrby2[0] & 0xFF | (arrby2[1] & 0xFF) << 8;
                arrayList.add(new SimpleRegister(n));
            } else {
                arrayList.add(new SoundRegister(Sound.values()[by], arrby[++n3]));
            }
            n4 = ++n3;
        }
        return arrayList.toArray(new IRegister[0]);
    }

    public static class SimpleRegister
    implements IRegister {
        private /* synthetic */ int sound;

        public int getSound() {
            return this.sound;
        }

        public void setSound(int n) {
            this.sound = n;
        }

        public SimpleRegister(int n) {
            this.sound = n;
        }
    }

    public static interface IRegister {
    }

    public static class SoundRegister
    implements IRegister {
        private final /* synthetic */ Sound sound;
        private final /* synthetic */ byte soundByte;

        public Sound getSound() {
            return this.sound;
        }

        public byte getSoundByte() {
            return this.soundByte;
        }

        public boolean equals(Object object) {
            if (object instanceof SoundRegister) {
                SoundRegister soundRegister = (SoundRegister)object;
                return soundRegister.getSound() == this.getSound() && soundRegister.getSoundByte() == this.getSoundByte();
            }
            return false;
        }

        public SoundRegister(Sound sound, byte by) {
            this.sound = sound;
            this.soundByte = by;
        }
    }

    public static enum Sound {
        NONE,
        GOLD,
        GLASS,
        BONE,
        WOOD,
        CLAY,
        ICE,
        SAND,
        ROCK,
        WOOL;

    }

    public static class SoundEntry {
        private final /* synthetic */ SoundRegister register;
        private final /* synthetic */ BlockPos pos;

        public SoundEntry(BlockPos blockPos, SoundRegister soundRegister) {
            this.pos = blockPos;
            this.register = soundRegister;
        }

        public SoundRegister getRegister() {
            return this.register;
        }

        public BlockPos getPos() {
            return this.pos;
        }
    }
}

