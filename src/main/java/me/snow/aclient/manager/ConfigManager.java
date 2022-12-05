/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package me.snow.aclient.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.EnumConverter;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.manager.FriendManager;
import me.snow.aclient.manager.ModuleManager;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.Util;

public class ConfigManager
implements Util {
    public /* synthetic */ boolean savingConfig;
    public /* synthetic */ ArrayList<Feature> features;
    public /* synthetic */ boolean loadingConfig;
    public /* synthetic */ String config;

    public void saveConfig(String string) {
        this.savingConfig = true;
        this.config = String.valueOf(new StringBuilder().append("Alice/").append(string).append("/"));
        File file = new File(this.config);
        if (!file.exists()) {
            file.mkdir();
        }
        AliceMain.friendManager.saveFriends();
        for (Feature feature : this.features) {
            try {
                this.saveSettings(feature);
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        this.saveCurrentConfig();
        this.savingConfig = false;
    }

    public JsonObject writeSettings(Feature feature) {
        JsonObject jsonObject = new JsonObject();
        JsonParser jsonParser = new JsonParser();
        for (Setting setting : feature.getSettings()) {
            Object object;
            if (setting.isEnumSetting()) {
                object = new EnumConverter(((Enum)setting.getValue()).getClass());
                jsonObject.add(setting.getName(), ((EnumConverter)((Object)object)).doForward((Enum)setting.getValue()));
                continue;
            }
            if (setting.isStringSetting()) {
                object = (String)setting.getValue();
                setting.setValue(((String)object).replace(" ", "_"));
            }
            try {
                jsonObject.add(setting.getName(), jsonParser.parse(setting.getValueAsString()));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return jsonObject;
    }

    public void saveCurrentConfig() {
        File file = new File("Alice/currentconfig.txt");
        try {
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file);
                String string = this.config.replaceAll("/", "");
                fileWriter.write(string.replaceAll("Alice", ""));
                fileWriter.close();
            } else {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                String string = this.config.replaceAll("/", "");
                fileWriter.write(string.replaceAll("Alice", ""));
                fileWriter.close();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void init() {
        this.features.addAll(ModuleManager.modules);
        this.features.add(AliceMain.friendManager);
        String string = this.loadCurrentConfig();
        this.loadConfig(string);
        AliceMain.LOGGER.info("Config loaded.");
    }

    public void loadConfig(String string) {
        this.loadingConfig = true;
        List list = Arrays.stream((Object[])Objects.requireNonNull(new File("Alice").listFiles())).filter(File::isDirectory).collect(Collectors.toList());
        this.config = list.contains(new File(String.valueOf(new StringBuilder().append("Alice/").append(string).append("/")))) ? String.valueOf(new StringBuilder().append("Alice/").append(string).append("/")) : "Alice/config/";
        AliceMain.friendManager.onLoad();
        for (Feature feature : this.features) {
            try {
                this.loadSettings(feature);
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        this.saveCurrentConfig();
        this.loadingConfig = false;
    }

    public ConfigManager() {
        this.features = new ArrayList();
        this.config = "Alice/config/";
    }

    public static void setValueFromJson(Feature feature, Setting setting, JsonElement jsonElement) {
        switch (setting.getType()) {
            case "Boolean": {
                setting.setValue(jsonElement.getAsBoolean());
                break;
            }
            case "Double": {
                setting.setValue(jsonElement.getAsDouble());
                break;
            }
            case "Float": {
                setting.setValue(Float.valueOf(jsonElement.getAsFloat()));
                break;
            }
            case "Integer": {
                setting.setValue(jsonElement.getAsInt());
                break;
            }
            case "String": {
                String string = jsonElement.getAsString();
                setting.setValue(string.replace("_", " "));
                break;
            }
            case "Bind": {
                setting.setValue(new Bind.BindConverter().doBackward(jsonElement));
                break;
            }
            case "Enum": {
                try {
                    EnumConverter enumConverter = new EnumConverter(((Enum)setting.getValue()).getClass());
                    Enum enum_ = enumConverter.doBackward(jsonElement);
                    setting.setValue(enum_ == null ? setting.getDefaultValue() : enum_);
                }
                catch (Exception exception) {}
                break;
            }
            default: {
                AliceMain.LOGGER.error(String.valueOf(new StringBuilder().append("Unknown Setting type for: ").append(feature.getName()).append(" : ").append(setting.getName())));
            }
        }
    }

    private void loadPath(Path path, Feature feature) throws IOException {
        InputStream inputStream = Files.newInputStream(path, new OpenOption[0]);
        try {
            ConfigManager.loadFile(new JsonParser().parse((Reader)new InputStreamReader(inputStream)).getAsJsonObject(), feature);
        }
        catch (IllegalStateException illegalStateException) {
            AliceMain.LOGGER.error(String.valueOf(new StringBuilder().append("Bad Config File for: ").append(feature.getName()).append(". Resetting...")));
            ConfigManager.loadFile(new JsonObject(), feature);
        }
        inputStream.close();
    }

    private static void loadFile(JsonObject jsonObject, Feature feature) {
        for (Map.Entry entry : jsonObject.entrySet()) {
            String string = (String)entry.getKey();
            JsonElement jsonElement = (JsonElement)entry.getValue();
            if (feature instanceof FriendManager) {
                try {
                    AliceMain.friendManager.addFriend(new FriendManager.Friend(jsonElement.getAsString(), UUID.fromString(string)));
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
                continue;
            }
            boolean bl = false;
            for (Setting setting : feature.getSettings()) {
                if (!string.equals(setting.getName())) continue;
                try {
                    ConfigManager.setValueFromJson(feature, setting, jsonElement);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
                bl = true;
            }
            if (!bl) continue;
        }
    }

    public void saveSettings(Feature feature) throws IOException {
        Path path;
        new JsonObject();
        File file = new File(String.valueOf(new StringBuilder().append(this.config).append(this.getDirectory(feature))));
        if (!file.exists()) {
            file.mkdir();
        }
        if (!Files.exists(path = Paths.get(String.valueOf(new StringBuilder().append(this.config).append(this.getDirectory(feature)).append(feature.getName()).append(".json")), new String[0]), new LinkOption[0])) {
            Files.createFile(path, new FileAttribute[0]);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String string = gson.toJson((JsonElement)this.writeSettings(feature));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(path, new OpenOption[0])));
        bufferedWriter.write(string);
        bufferedWriter.close();
    }

    public String loadCurrentConfig() {
        File file = new File("Alice/currentconfig.txt");
        String string = "config";
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    string = scanner.nextLine();
                }
                scanner.close();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return string;
    }

    private void loadSettings(Feature feature) throws IOException {
        String string = String.valueOf(new StringBuilder().append(this.config).append(this.getDirectory(feature)).append(feature.getName()).append(".json"));
        Path path = Paths.get(string, new String[0]);
        if (!Files.exists(path, new LinkOption[0])) {
            return;
        }
        this.loadPath(path, feature);
    }

    public String getDirectory(Feature feature) {
        String string = "";
        if (feature instanceof Module) {
            string = String.valueOf(new StringBuilder().append(string).append(((Module)feature).getCategory().getName()).append("/"));
        }
        return string;
    }

    public void resetConfig(boolean bl, String string) {
        for (Feature feature : this.features) {
            feature.reset();
        }
        if (bl) {
            this.saveConfig(string);
        }
    }
}

