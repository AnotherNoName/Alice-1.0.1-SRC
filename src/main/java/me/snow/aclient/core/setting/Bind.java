/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Converter
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 *  org.lwjgl.input.Keyboard
 */
package me.snow.aclient.core.setting;

import com.google.common.base.Converter;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.lwjgl.input.Keyboard;

public class Bind {
    private /* synthetic */ int key;

    public static Bind none() {
        return new Bind(-1);
    }

    public int getKey() {
        return this.key;
    }

    public String toString() {
        return this.isEmpty() ? "None" : (this.key < 0 ? "None" : this.capitalise(Keyboard.getKeyName((int)this.key)));
    }

    public boolean isDown() {
        return !this.isEmpty() && Keyboard.isKeyDown((int)this.getKey());
    }

    public boolean isEmpty() {
        return this.key < 0;
    }

    public void setKey(int n) {
        this.key = n;
    }

    public Bind(int n) {
        this.key = n;
    }

    private String capitalise(String string) {
        if (string.isEmpty()) {
            return "";
        }
        return String.valueOf(new StringBuilder().append(Character.toUpperCase(string.charAt(0))).append(string.length() != 1 ? string.substring(1).toLowerCase() : ""));
    }

    public static class BindConverter
    extends Converter<Bind, JsonElement> {
        public JsonElement doForward(Bind bind) {
            return new JsonPrimitive(bind.toString());
        }

        public Bind doBackward(JsonElement jsonElement) {
            String string = jsonElement.getAsString();
            if (string.equalsIgnoreCase("None")) {
                return Bind.none();
            }
            int n = -1;
            try {
                n = Keyboard.getKeyIndex((String)string.toUpperCase());
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (n == 0) {
                return Bind.none();
            }
            return new Bind(n);
        }
    }
}

