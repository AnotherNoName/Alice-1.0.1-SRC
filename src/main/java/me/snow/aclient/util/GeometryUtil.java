/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumFacing
 */
package me.snow.aclient.util;

import java.util.HashMap;
import net.minecraft.util.EnumFacing;

public class GeometryUtil {
    public static final /* synthetic */ HashMap<EnumFacing, Integer> FACEMAP;

    static {
        FACEMAP = new HashMap();
        FACEMAP.put(EnumFacing.DOWN, 1);
        FACEMAP.put(EnumFacing.WEST, 16);
        FACEMAP.put(EnumFacing.NORTH, 4);
        FACEMAP.put(EnumFacing.SOUTH, 8);
        FACEMAP.put(EnumFacing.EAST, 32);
        FACEMAP.put(EnumFacing.UP, 2);
    }

    public static final class Line {
        public static final /* synthetic */ int UP_EAST;
        public static final /* synthetic */ int ALL;
        public static final /* synthetic */ int UP_WEST;
        public static final /* synthetic */ int NORTH_EAST;
        public static final /* synthetic */ int SOUTH_WEST;
        public static final /* synthetic */ int DOWN_WEST;
        public static final /* synthetic */ int DOWN_NORTH;
        public static final /* synthetic */ int NORTH_WEST;
        public static final /* synthetic */ int UP_SOUTH;
        public static final /* synthetic */ int DOWN_EAST;
        public static final /* synthetic */ int UP_NORTH;
        public static final /* synthetic */ int DOWN_SOUTH;
        public static final /* synthetic */ int SOUTH_EAST;

        static {
            UP_SOUTH = 10;
            DOWN_NORTH = 5;
            NORTH_WEST = 20;
            NORTH_EAST = 36;
            UP_EAST = 34;
            DOWN_SOUTH = 9;
            SOUTH_WEST = 24;
            DOWN_EAST = 33;
            ALL = 63;
            SOUTH_EAST = 40;
            UP_NORTH = 6;
            DOWN_WEST = 17;
            UP_WEST = 18;
        }
    }

    public static final class Quad {
        public static final /* synthetic */ int DOWN;
        public static final /* synthetic */ int ALL;
        public static final /* synthetic */ int UP;
        public static final /* synthetic */ int WEST;
        public static final /* synthetic */ int NORTH;
        public static final /* synthetic */ int SOUTH;
        public static final /* synthetic */ int EAST;

        static {
            EAST = 32;
            DOWN = 1;
            SOUTH = 8;
            ALL = 63;
            UP = 2;
            NORTH = 4;
            WEST = 16;
        }
    }
}

