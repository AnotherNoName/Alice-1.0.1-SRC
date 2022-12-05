/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.manager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import me.snow.aclient.AliceMain;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.Module;

public class FileManager
extends Feature {
    private final /* synthetic */ Path base;
    private final /* synthetic */ Path notebot;

    public FileManager() {
        this.base = this.getMkDirectory(this.getRoot(), "Alice");
        this.notebot = this.getMkDirectory(this.base, "notebot");
        this.getMkDirectory(this.base, "util");
        for (Module.Category category : AliceMain.moduleManager.getCategories()) {
            Path path = this.getMkDirectory(this.base, "config");
            this.getMkDirectory(path, category.getName());
        }
    }

    public Path getConfig() {
        return this.getBasePath().resolve("config");
    }

    private void createDirectory(Path path) {
        try {
            if (!Files.isDirectory(path, new LinkOption[0])) {
                if (Files.exists(path, new LinkOption[0])) {
                    Files.delete(path);
                }
                Files.createDirectories(path, new FileAttribute[0]);
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static List<String> readTextFileAllLines(String string) {
        try {
            Path path = Paths.get(string, new String[0]);
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        }
        catch (IOException iOException) {
            System.out.println(String.valueOf(new StringBuilder().append("WARNING: Unable to read file, creating new file: ").append(string)));
            FileManager.appendTextFile("", string);
            return Collections.emptyList();
        }
    }

    private Path lookupPath(Path path, String ... arrstring) {
        return Paths.get(path.toString(), arrstring);
    }

    public static void appendTextFile(String string, String string2) {
        try {
            Path path = Paths.get(string2, new String[0]);
            Files.write(path, Collections.singletonList(string), StandardCharsets.UTF_8, Files.exists(path, new LinkOption[0]) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        }
        catch (IOException iOException) {
            System.out.println(String.valueOf(new StringBuilder().append("WARNING: Unable to write file: ").append(string2)));
        }
    }

    private String[] expandPath(String string) {
        return string.split(":?\\\\\\\\|\\/");
    }

    public Path getCache() {
        return this.getBasePath().resolve("cache");
    }

    public Path getMkBaseResolve(String ... arrstring) {
        Path path = this.getBaseResolve(arrstring);
        this.createDirectory(path.getParent());
        return path;
    }

    private Stream<String> expandPaths(String ... arrstring) {
        return Arrays.stream(arrstring).map(this::expandPath).flatMap(Arrays::stream);
    }

    public Path getNotebot() {
        return this.getBasePath().resolve("notebot");
    }

    private Path getMkDirectory(Path path, String ... arrstring) {
        if (arrstring.length < 1) {
            return path;
        }
        Path path2 = this.lookupPath(path, arrstring);
        this.createDirectory(path2);
        return path2;
    }

    public Path getMkConfigDirectory(String ... arrstring) {
        return this.getMkDirectory(this.getConfig(), this.expandPaths(arrstring).collect(Collectors.joining(File.separator)));
    }

    public Path getBaseResolve(String ... arrstring) {
        String[] arrstring2 = (String[])this.expandPaths(arrstring).toArray(String[]::new);
        if (arrstring2.length < 1) {
            throw new IllegalArgumentException("missing path");
        }
        return this.lookupPath(this.getBasePath(), arrstring2);
    }

    public Path getMkBaseDirectory(String ... arrstring) {
        return this.getMkDirectory(this.getBasePath(), this.expandPaths(arrstring).collect(Collectors.joining(File.separator)));
    }

    private Path getRoot() {
        return Paths.get("", new String[0]);
    }

    public Path getBasePath() {
        return this.base;
    }
}

