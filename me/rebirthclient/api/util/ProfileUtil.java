//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import java.net.*;
import java.nio.charset.*;
import java.io.*;
import net.minecraft.client.network.*;
import java.util.*;
import java.util.function.*;
import me.rebirthclient.mod.commands.*;
import com.mojang.util.*;
import com.google.gson.*;

public class ProfileUtil implements Wrapper
{
    public static UUID getUUIDFromName(final String s) {
        try {
            final UUIDFinder uuidFinder = new UUIDFinder(s);
            final Thread thread = new Thread(uuidFinder);
            thread.start();
            thread.join();
            return uuidFinder.getUUID();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String requestIDs(final String s) {
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("https://api.mojang.com/profiles/minecraft").openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            final OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(s.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            final String stringFromStream = getStringFromStream(bufferedInputStream);
            bufferedInputStream.close();
            httpURLConnection.disconnect();
            return stringFromStream;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String getStringFromStream(final InputStream inputStream) {
        final Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "/";
    }
    
    public static class UUIDFinder implements Runnable
    {
        private final String name;
        private volatile UUID uuid;
        static final boolean $assertionsDisabled;
        
        public UUIDFinder(final String name) {
            this.name = name;
        }
        
        private boolean lambda$run$0(final NetworkPlayerInfo networkPlayerInfo) {
            return Integer.valueOf(this.name.toUpperCase().hashCode()).equals(networkPlayerInfo.getGameProfile().getName().toUpperCase().hashCode());
        }
        
        static {
            $assertionsDisabled = !ProfileUtil.class.desiredAssertionStatus();
        }
        
        @Override
        public void run() {
            NetworkPlayerInfo networkPlayerInfo;
            try {
                networkPlayerInfo = new ArrayList<NetworkPlayerInfo>(Objects.requireNonNull(Wrapper.mc.getConnection()).getPlayerInfoMap()).stream().filter(this::lambda$run$0).findFirst().orElse(null);
                assert networkPlayerInfo != null;
                this.uuid = networkPlayerInfo.getGameProfile().getId();
            }
            catch (Exception ex2) {
                networkPlayerInfo = null;
            }
            if (networkPlayerInfo == null) {
                Command.sendMessage("Player isn't online. Looking up UUID..");
                final String requestIDs = ProfileUtil.requestIDs("[\"" + this.name + "\"]");
                if (requestIDs == null || requestIDs.isEmpty()) {
                    Command.sendMessage("Couldn't find player ID. Are you connected to the internet? (0)");
                }
                else {
                    final JsonElement parse = new JsonParser().parse(requestIDs);
                    if (parse.getAsJsonArray().size() == 0) {
                        Command.sendMessage("Couldn't find player ID. (1)");
                    }
                    else {
                        try {
                            this.uuid = UUIDTypeAdapter.fromString(parse.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString());
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            Command.sendMessage("Couldn't find player ID. (2)");
                        }
                    }
                }
            }
        }
        
        public String getName() {
            return this.name;
        }
        
        public UUID getUUID() {
            return this.uuid;
        }
    }
}
