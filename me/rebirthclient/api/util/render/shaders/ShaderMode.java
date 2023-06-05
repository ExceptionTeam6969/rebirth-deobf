//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shaders;

import me.rebirthclient.api.util.render.shaders.shaders.*;

public enum ShaderMode
{
    BLUEFLAMES("BLUEFLAMES", 10, "BlueFlames", ShaderMode::lambda$static$1);
    
    private final String name;
    
    STROXINJAT("STROXINJAT", 27, "Stroxinjat", ShaderMode::lambda$static$18), 
    Aqua("Aqua", 0, "Aqua", AquaShader::INSTANCE), 
    FLOWGLOW("FLOWGLOW", 4, "FlowGLow", FlowGlShader::INSTANCE), 
    SNOW("SNOW", 16, "Snow", ShaderMode::lambda$static$7), 
    CODEX("CODEX", 12, "Codex", ShaderMode::lambda$static$3), 
    HIDEF("HIDEF", 21, "Hidef", ShaderMode::lambda$static$12), 
    GOLDEN("GOLDEN", 18, "Golden", ShaderMode::lambda$static$9), 
    SMOKE("SMOKE", 6, "Smoke", SmokeShader::INSTANCE), 
    TECHNO("TECHNO", 17, "Techno", ShaderMode::lambda$static$8), 
    CRAZY("CRAZY", 15, "Crazy", ShaderMode::lambda$static$6), 
    YIPPIEOWNS("YIPPIEOWNS", 29, "YippieOwns", ShaderMode::lambda$static$20), 
    HOMIE("HOMIE", 22, "Homie", ShaderMode::lambda$static$13), 
    SHELDON("SHELDON", 25, "Sheldon", ShaderMode::lambda$static$16), 
    SMOKY("SMOKY", 26, "Smoky", ShaderMode::lambda$static$17), 
    WEIRD("WEIRD", 28, "Weird", ShaderMode::lambda$static$19), 
    GUISHADER("GUISHADER", 20, "GuiShader", ShaderMode::lambda$static$11);
    
    private final ShaderProducer shaderProducer;
    
    HOTSHIT("HOTSHIT", 19, "HotShit", ShaderMode::lambda$static$10), 
    AQUAGLOW("AQUAGLOW", 1, "AquaGlow", AquaGlShader::INSTANCE);
    
    private static final ShaderMode[] $VALUES;
    
    FLOWBLUR("FLOWBLUR", 3, "FlowBlur", ShaderMode::lambda$static$0), 
    HOLYFUCK("HOLYFUCK", 8, "HolyFuck", HolyFuckShader::INSTANCE), 
    PURPLE("PURPLE", 30, "Purple", PurpleShader::INSTANCE), 
    GANG("GANG", 9, "Gang", GangGlShader::INSTANCE), 
    KFC("KFC", 23, "KFC", ShaderMode::lambda$static$14), 
    GAMER("GAMER", 11, "Gamer", ShaderMode::lambda$static$2), 
    OHMYLORD("OHMYLORD", 24, "Lord", ShaderMode::lambda$static$15), 
    FLOW("FLOW", 2, "Flow", FlowShader::INSTANCE), 
    RED("RED", 7, "Red", RedShader::INSTANCE), 
    DDEV("DDEV", 14, "Ddev", ShaderMode::lambda$static$5), 
    GHOST("GHOST", 5, "Glow", GlowShader::INSTANCE), 
    GALAXY("GALAXY", 13, "Galaxy", ShaderMode::lambda$static$4);
    
    private static FramebufferShader lambda$static$7() {
        return BasicShader.INSTANCE("snow.frag", 0.01f);
    }
    
    private ShaderMode(final String s, final int n, final String name, final ShaderProducer shaderProducer) {
        this.name = name;
        this.shaderProducer = shaderProducer;
    }
    
    private static FramebufferShader lambda$static$20() {
        return BasicShader.INSTANCE("yippieOwns.frag");
    }
    
    private static FramebufferShader lambda$static$10() {
        return BasicShader.INSTANCE("hotshit.frag", 0.005f);
    }
    
    private static FramebufferShader lambda$static$15() {
        return BasicShader.INSTANCE("ohmylord.frag", 0.01f);
    }
    
    private static FramebufferShader lambda$static$12() {
        return BasicShader.INSTANCE("hidef.frag", 0.05f);
    }
    
    private static FramebufferShader lambda$static$18() {
        return BasicShader.INSTANCE("stroxinjat.frag");
    }
    
    private static FramebufferShader lambda$static$13() {
        return BasicShader.INSTANCE("homie.frag", 0.001f);
    }
    
    private static FramebufferShader lambda$static$2() {
        return BasicShader.INSTANCE("gamer.frag", 0.03f);
    }
    
    private static FramebufferShader lambda$static$11() {
        return BasicShader.INSTANCE("clickguishader.frag", 0.02f);
    }
    
    private static FramebufferShader lambda$static$17() {
        return BasicShader.INSTANCE("smoky.frag", 0.001f);
    }
    
    private static FramebufferShader lambda$static$5() {
        return BasicShader.INSTANCE("ddev.frag");
    }
    
    private static FramebufferShader lambda$static$16() {
        return BasicShader.INSTANCE("sheldon.frag", 0.001f);
    }
    
    private static FramebufferShader lambda$static$0() {
        return BasicShader.INSTANCE("flowglow_z.frag", 5.0E-4f);
    }
    
    private static FramebufferShader lambda$static$14() {
        return BasicShader.INSTANCE("kfc.frag", 0.01f);
    }
    
    private static FramebufferShader lambda$static$6() {
        return BasicShader.INSTANCE("crazy.frag", 0.01f);
    }
    
    private static FramebufferShader lambda$static$9() {
        return BasicShader.INSTANCE("golden.frag", 0.01f);
    }
    
    private static FramebufferShader lambda$static$8() {
        return BasicShader.INSTANCE("techno.frag", 0.01f);
    }
    
    private static FramebufferShader lambda$static$4() {
        return BasicShader.INSTANCE("galaxy33.frag", 0.001f);
    }
    
    public FramebufferShader getShader() {
        return this.shaderProducer.INSTANCE();
    }
    
    static {
        $VALUES = new ShaderMode[] { ShaderMode.Aqua, ShaderMode.AQUAGLOW, ShaderMode.FLOW, ShaderMode.FLOWBLUR, ShaderMode.FLOWGLOW, ShaderMode.GHOST, ShaderMode.SMOKE, ShaderMode.RED, ShaderMode.HOLYFUCK, ShaderMode.GANG, ShaderMode.BLUEFLAMES, ShaderMode.GAMER, ShaderMode.CODEX, ShaderMode.GALAXY, ShaderMode.DDEV, ShaderMode.CRAZY, ShaderMode.SNOW, ShaderMode.TECHNO, ShaderMode.GOLDEN, ShaderMode.HOTSHIT, ShaderMode.GUISHADER, ShaderMode.HIDEF, ShaderMode.HOMIE, ShaderMode.KFC, ShaderMode.OHMYLORD, ShaderMode.SHELDON, ShaderMode.SMOKY, ShaderMode.STROXINJAT, ShaderMode.WEIRD, ShaderMode.YIPPIEOWNS, ShaderMode.PURPLE };
    }
    
    private static FramebufferShader lambda$static$19() {
        return BasicShader.INSTANCE("weird.frag", 0.01f);
    }
    
    private static FramebufferShader lambda$static$1() {
        return BasicShader.INSTANCE("blueflames.frag", 0.01f);
    }
    
    private static FramebufferShader lambda$static$3() {
        return BasicShader.INSTANCE("codex.frag");
    }
    
    public String getName() {
        return this.name;
    }
}
