//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.api.util.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.*;
import me.rebirthclient.mod.*;
import me.rebirthclient.mod.modules.*;
import java.nio.file.attribute.*;
import java.nio.file.*;
import me.rebirthclient.mod.commands.*;
import me.rebirthclient.mod.modules.settings.*;
import java.awt.*;
import java.util.stream.*;
import com.google.gson.*;
import java.io.*;
import java.util.*;

public class ConfigManager implements Wrapper
{
    public String config;
    public final ArrayList mods;
    
    public void init() {
        this.mods.addAll(Managers.MODULES.modules);
        this.mods.add(Managers.FRIENDS);
        this.loadConfig(this.loadCurrentConfig());
        Rebirth.LOGGER.info("Config loaded.");
    }
    
    public ConfigManager() {
        this.mods = new ArrayList();
        this.config = "Rebirth/config/";
    }
    
    public String getDirectory(final Mod mod) {
        String string = "";
        if (mod instanceof Module) {
            string = string + ((Module)mod).getCategory().getName() + "/";
        }
        return string;
    }
    
    public void saveCurrentConfig() {
        final File file = new File("Rebirth/currentconfig.txt");
        try {
            if (file.exists()) {
                final FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(this.config.replaceAll("/", "").replaceAll("Rebirth", ""));
                fileWriter.close();
            }
            else {
                file.createNewFile();
                final FileWriter fileWriter2 = new FileWriter(file);
                fileWriter2.write(this.config.replaceAll("/", "").replaceAll("Rebirth", ""));
                fileWriter2.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadSettings(final Mod mod) throws IOException {
        final Path value = Paths.get(this.config + this.getDirectory(mod) + mod.getName() + ".json", new String[0]);
        if (!Files.exists(value, new LinkOption[0])) {
            return;
        }
        this.loadPath(value, mod);
    }
    
    public void saveSettings(final Mod mod) throws IOException {
        new JsonObject();
        final File file = new File(this.config + this.getDirectory(mod));
        if (!file.exists()) {
            file.mkdir();
        }
        final Path value = Paths.get(this.config + this.getDirectory(mod) + mod.getName() + ".json", new String[0]);
        if (!Files.exists(value, new LinkOption[0])) {
            Files.createFile(value, (FileAttribute<?>[])new FileAttribute[0]);
        }
        final String json = new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)this.writeSettings(mod));
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(value, new OpenOption[0])));
        bufferedWriter.write(json);
        bufferedWriter.close();
    }
    
    public void saveConfig(final String s) {
        this.config = "Rebirth/" + s + "/";
        final File file = new File(this.config);
        if (!file.exists()) {
            file.mkdir();
        }
        Managers.FRIENDS.saveFriends();
        final Iterator<Mod> iterator = (Iterator<Mod>)this.mods.iterator();
        if (iterator.hasNext()) {
            final Mod mod = iterator.next();
            try {
                this.saveSettings(mod);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }
        this.saveCurrentConfig();
    }
    
    private static void loadFile(final JsonObject jsonObject, final Mod mod) {
        final Iterator<Map.Entry<String, V>> iterator = (Iterator<Map.Entry<String, V>>)jsonObject.entrySet().iterator();
        if (!iterator.hasNext()) {
            return;
        }
        final Map.Entry<String, V> entry = iterator.next();
        final String s = entry.getKey();
        final JsonElement jsonElement = (JsonElement)entry.getValue();
        if (mod instanceof FriendManager) {
            try {
                Managers.FRIENDS.addFriend(new FriendManager.Friend(jsonElement.getAsString(), UUID.fromString(s)));
                return;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        }
        final Iterator<Setting> iterator2 = mod.getSettings().iterator();
        if (iterator2.hasNext()) {
            final Setting setting = iterator2.next();
            if (Integer.valueOf(setting.getName().hashCode()).equals(s.hashCode())) {
                try {
                    setValueFromJson(mod, setting, jsonElement);
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
            if (Integer.valueOf(("should" + setting.getName()).hashCode()).equals(s.hashCode())) {
                try {
                    setValueFromJson(mod, setting, jsonElement);
                }
                catch (Exception ex3) {}
            }
            if (Integer.valueOf(("Rainbow" + setting.getName()).hashCode()).equals(s.hashCode())) {
                if (!Mod.fullNullCheck()) {
                    Command.sendMessage("rainbow test" + jsonElement.getAsBoolean());
                }
                setting.isRainbow = jsonElement.getAsBoolean();
            }
        }
    }
    
    public static void setValueFromJson(final Mod mod, final Setting setting, final JsonElement jsonElement) {
        final String type = setting.getType();
        int n = -1;
        switch (type.hashCode()) {
            case 1729365000: {
                if (Integer.valueOf("Boolean".hashCode()).equals(type.hashCode())) {
                    n = 0;
                    break;
                }
                break;
            }
            case 2052876273: {
                if (Integer.valueOf("Double".hashCode()).equals(type.hashCode())) {
                    n = 1;
                    break;
                }
                break;
            }
            case 67973692: {
                if (Integer.valueOf("Float".hashCode()).equals(type.hashCode())) {
                    n = 2;
                    break;
                }
                break;
            }
            case -672261858: {
                if (Integer.valueOf("Integer".hashCode()).equals(type.hashCode())) {
                    n = 3;
                    break;
                }
                break;
            }
            case -1808118735: {
                if (Integer.valueOf("String".hashCode()).equals(type.hashCode())) {
                    n = 4;
                    break;
                }
                break;
            }
            case 2070621: {
                if (Integer.valueOf("Bind".hashCode()).equals(type.hashCode())) {
                    n = 5;
                    break;
                }
                break;
            }
            case 2165025: {
                if (Integer.valueOf("Enum".hashCode()).equals(type.hashCode())) {
                    n = 6;
                    break;
                }
                break;
            }
            case 65290051: {
                if (Integer.valueOf("Color".hashCode()).equals(type.hashCode())) {
                    n = 7;
                    break;
                }
                break;
            }
        }
        switch (n) {
            case 0: {
                setting.setValue(jsonElement.getAsBoolean());
            }
            case 1: {
                setting.setValue(jsonElement.getAsDouble());
            }
            case 2: {
                setting.setValue(jsonElement.getAsFloat());
            }
            case 3: {
                setting.setValue(jsonElement.getAsInt());
            }
            case 4: {
                try {
                    setting.setValue(jsonElement.getAsString().replace("_", " "));
                }
                catch (Exception ex) {}
            }
            case 5: {
                try {
                    setting.setValue(new Bind.BindConverter().doBackward(jsonElement));
                }
                catch (Exception ex2) {}
            }
            case 6: {
                try {
                    final Enum doBackward = new EnumConverter(((Enum)setting.getValue()).getClass()).doBackward(jsonElement);
                    setting.setValue((doBackward == null) ? setting.getDefaultValue() : doBackward);
                }
                catch (Exception ex3) {}
            }
            case 7: {
                try {
                    if (setting.hasBoolean) {
                        setting.injectBoolean(jsonElement.getAsBoolean());
                    }
                    try {
                        setting.setValue(new Color(jsonElement.getAsInt(), true));
                    }
                    catch (Exception ex4) {}
                }
                catch (Exception ex5) {}
            }
            default: {
                Rebirth.LOGGER.error("Unknown Setting type for: " + mod.getName() + " : " + setting.getName());
            }
        }
    }
    
    public boolean configExists(final String s) {
        return Arrays.stream((Object[])Objects.requireNonNull((T[])new File("Rebirth").listFiles())).filter(File::isDirectory).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()).contains(new File("Rebirth/" + s + "/"));
    }
    
    private void loadPath(final Path path, final Mod mod) throws IOException {
        final InputStream inputStream = Files.newInputStream(path, new OpenOption[0]);
        try {
            loadFile(new JsonParser().parse((Reader)new InputStreamReader(inputStream)).getAsJsonObject(), mod);
        }
        catch (IllegalStateException ex) {
            Rebirth.LOGGER.error("Bad Config File for: " + mod.getName() + ". Resetting...");
            loadFile(new JsonObject(), mod);
        }
        inputStream.close();
    }
    
    public String loadCurrentConfig() {
        final File file = new File("Rebirth/currentconfig.txt");
        String nextLine = "config";
        try {
            if (file.exists()) {
                final Scanner scanner = new Scanner(file);
                if (scanner.hasNextLine()) {
                    nextLine = scanner.nextLine();
                    return null;
                }
                scanner.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return nextLine;
    }
    
    public JsonObject writeSettings(final Mod mod) {
        final JsonObject jsonObject = new JsonObject();
        final JsonParser jsonParser = new JsonParser();
        final Iterator<Setting> iterator = (Iterator<Setting>)mod.getSettings().iterator();
        if (!iterator.hasNext()) {
            return jsonObject;
        }
        final Setting setting = iterator.next();
        if (setting.getValue() instanceof Color) {
            jsonObject.add(setting.getName(), jsonParser.parse(String.valueOf(((Color)setting.getValue()).getRGB())));
            if (setting.hasBoolean) {
                jsonObject.add("should" + setting.getName(), jsonParser.parse(String.valueOf(setting.booleanValue)));
            }
            jsonObject.add("Rainbow" + setting.getName(), jsonParser.parse(String.valueOf(setting.isRainbow)));
            return null;
        }
        if (setting.isEnumSetting()) {
            jsonObject.add(setting.getName(), new EnumConverter(((Enum)setting.getValue()).getClass()).doForward((Enum)setting.getValue()));
            return null;
        }
        if (setting.isStringSetting()) {
            setting.setValue(((String)setting.getValue()).replace(" ", "_"));
        }
        try {
            jsonObject.add(setting.getName(), jsonParser.parse(setting.getValue().toString()));
        }
        catch (Exception ex) {}
        return null;
    }
    
    public void loadConfig(final String s) {
        if (Arrays.stream((Object[])Objects.requireNonNull((T[])new File("Rebirth").listFiles())).filter(File::isDirectory).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()).contains(new File("Rebirth/" + s + "/"))) {
            this.config = "Rebirth/" + s + "/";
        }
        else {
            this.config = "Rebirth/config/";
        }
        Managers.FRIENDS.onLoad();
        final Iterator<Mod> iterator = (Iterator<Mod>)this.mods.iterator();
        if (iterator.hasNext()) {
            final Mod mod = iterator.next();
            try {
                this.loadSettings(mod);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }
        this.saveCurrentConfig();
    }
}
