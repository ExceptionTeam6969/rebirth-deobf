//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.common.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import java.util.stream.*;
import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.mod.modules.impl.hud.*;
import me.rebirthclient.mod.modules.impl.render.*;
import me.rebirthclient.mod.modules.impl.combat.*;
import me.rebirthclient.mod.modules.impl.player.*;
import me.rebirthclient.mod.modules.impl.misc.*;
import me.rebirthclient.mod.modules.impl.movement.*;
import me.rebirthclient.mod.modules.impl.exploit.*;
import java.util.*;
import me.rebirthclient.api.managers.*;
import org.lwjgl.input.*;
import me.rebirthclient.mod.gui.screen.*;

public class ModuleManager extends Mod
{
    public final ArrayList modules;
    public List sortedLength;
    public static final Minecraft mc;
    public List sortedAbc;
    
    private static void lambda$onRender2D$3(final Render2DEvent render2DEvent, final Module module) {
        module.onRender2D(render2DEvent);
    }
    
    public void onTotemPop(final EntityPlayer entityPlayer) {
        this.modules.stream().filter(Module::isOn).forEach(ModuleManager::lambda$onTotemPop$5);
    }
    
    public void onUnloadPre() {
        this.modules.forEach(MinecraftForge.EVENT_BUS::unregister);
        this.modules.forEach(Module::onUnload);
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        this.modules.stream().filter(Module::isOn).forEach(ModuleManager::lambda$onRender3D$4);
    }
    
    public ArrayList getModulesByCategory(final Category category) {
        final ArrayList list = new ArrayList();
        this.modules.forEach(ModuleManager::lambda$getModulesByCategory$1);
        return list;
    }
    
    public void onUnloadPost() {
        final Iterator<Module> iterator = this.modules.iterator();
        if (iterator.hasNext()) {
            iterator.next().enabled.setValue(false);
        }
    }
    
    public void onLoad() {
        this.modules.stream().filter(Module::isListening).forEach(MinecraftForge.EVENT_BUS::register);
        this.modules.forEach(Module::onLoad);
    }
    
    public void onUpdate() {
        this.modules.stream().filter(Module::isOn).forEach(Module::onUpdate);
    }
    
    private static void lambda$getModulesByCategory$1(final Category category, final ArrayList list, final Module module) {
        if (module.getCategory() == category) {
            list.add(module);
        }
    }
    
    private static void lambda$onRender3D$4(final Render3DEvent render3DEvent, final Module module) {
        module.onRender3D(render3DEvent);
    }
    
    public Module getModuleByName(final String s) {
        final Iterator<Module> iterator = (Iterator<Module>)this.modules.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        final Module module = iterator.next();
        if (!Integer.valueOf(s.toUpperCase().hashCode()).equals(module.getName().toUpperCase().hashCode())) {
            return null;
        }
        return module;
    }
    
    private static void lambda$onTotemPop$5(final EntityPlayer entityPlayer, final Module module) {
        module.onTotemPop(entityPlayer);
    }
    
    public void sortModules() {
        this.sortedLength = (List)this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)ModuleManager::lambda$sortModules$0)).collect(Collectors.toList());
        (this.sortedAbc = new ArrayList(this.getEnabledModulesString())).sort(String.CASE_INSENSITIVE_ORDER);
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    public ArrayList getEnabledModulesString() {
        final ArrayList<String> list = new ArrayList<String>();
        for (final Module module : this.modules) {
            if (module.isOn() && module.isDrawn()) {
                if ((boolean)HUD.INSTANCE.onlyBind.getValue() && ((Bind)module.bind.getValue()).getKey() == -1) {
                    return null;
                }
                list.add(module.getArrayListInfo());
                return null;
            }
        }
        return list;
    }
    
    public void init() {
        this.registerModules();
    }
    
    private void registerModules() {
        this.modules.add(new Chat());
        this.modules.add(new me.rebirthclient.mod.modules.impl.client.ArrayList());
        this.modules.add(new FakeFPS());
        this.modules.add(new GuiAnimation());
        this.modules.add(new UnfocusedCPU());
        this.modules.add(new NameProtect());
        this.modules.add(new ClickGui());
        this.modules.add(new FontMod());
        this.modules.add(new HUD());
        this.modules.add(new FovMod());
        this.modules.add(new Title());
        this.modules.add(new Desktop());
        this.modules.add(new Appearance());
        this.modules.add(new BindList());
        this.modules.add(new Notifications());
        this.modules.add(new InventoryPreview());
        this.modules.add(new TargetHUD());
        this.modules.add(new AutoEsu());
        this.modules.add(new RenderSetting());
        this.modules.add(new PlaceRender());
        this.modules.add(new Highlight());
        this.modules.add(new ExplosionSpawn());
        this.modules.add(new EarthPopChams());
        this.modules.add(new PopChams());
        this.modules.add(new Rotations());
        this.modules.add(new CameraClip());
        this.modules.add(new PortalESP());
        this.modules.add(new Search());
        this.modules.add(new ChinaHat());
        this.modules.add(new CityESP());
        this.modules.add(new GlintModify());
        this.modules.add(new NoRender());
        this.modules.add(new Trajectories());
        this.modules.add(new ShaderChams());
        this.modules.add(new HoleESP());
        this.modules.add(new ItemModel());
        this.modules.add(new Tracers());
        this.modules.add(new CrystalChams());
        this.modules.add(new Chams());
        this.modules.add(new DMGParticles());
        this.modules.add(new ItemPhysics());
        this.modules.add(new BreakESP());
        this.modules.add(new Models());
        this.modules.add(new NameTags());
        this.modules.add(new ESP2D());
        this.modules.add(new Ambience());
        this.modules.add(new ESP());
        this.modules.add(new BreadCrumbs());
        this.modules.add(new VoidESP());
        this.modules.add(new NoLag());
        this.modules.add(new TileESP());
        this.modules.add(new Shader());
        this.modules.add(new Shaders());
        this.modules.add(new LogOutSpots());
        this.modules.add(new CatCrystal());
        this.modules.add(new PistonCrystal());
        this.modules.add(new PullCrystal());
        this.modules.add(new CrystalBot());
        this.modules.add(new AutoTotem());
        this.modules.add(new Burrow());
        this.modules.add(new AutoWire());
        this.modules.add(new Filler());
        this.modules.add(new WebTrap());
        this.modules.add(new AutoCity());
        this.modules.add(new CityRecode());
        this.modules.add(new PacketMine());
        this.modules.add(new Surround());
        this.modules.add(new TrapSelf());
        this.modules.add(new AntiWeak());
        this.modules.add(new AntiCity());
        this.modules.add(new AntiPiston());
        this.modules.add(new CombatSetting());
        this.modules.add(new AntiBurrow());
        this.modules.add(new ObiPlacer());
        this.modules.add(new Aura());
        this.modules.add(new AntiRegear());
        this.modules.add(new AutoArmor());
        this.modules.add(new Criticals());
        this.modules.add(new SelfWeb());
        this.modules.add(new AutoTrap());
        this.modules.add(new PacketExp());
        this.modules.add(new AutoPush());
        this.modules.add(new AutoWeb());
        this.modules.add(new TestPush());
        this.modules.add(new HoleFiller());
        this.modules.add(new AnvilAura());
        this.modules.add(new AutoReplenish());
        this.modules.add(new AntiAim());
        this.modules.add(new SpeedMine());
        this.modules.add(new AutoFish());
        this.modules.add(new AutoFuck());
        this.modules.add(new KeyPearl());
        this.modules.add(new BlockTweaks());
        this.modules.add(new Freecam());
        this.modules.add(new NoFall());
        this.modules.add(new AntiOpen());
        this.modules.add(new TpsSync());
        this.modules.add(new PacketEat());
        this.modules.add(new AutoRespawn());
        this.modules.add(new TimerModule());
        this.modules.add(new NoRotate());
        this.modules.add(new FastPlace());
        this.modules.add(new Replenish());
        this.modules.add(new ArmorWarner());
        this.modules.add(new Announcer());
        this.modules.add(new FlagDetect());
        this.modules.add(new FreeLook());
        this.modules.add(new Debug());
        this.modules.add(new AntiSpam());
        this.modules.add(new SilentDisconnect());
        this.modules.add(new TabFriends());
        this.modules.add(new ExtraTab());
        this.modules.add(new AntiNullPointer());
        this.modules.add(new AutoEZ());
        this.modules.add(new PopCounter());
        this.modules.add(new LightningDetect());
        this.modules.add(new Message());
        this.modules.add(new TNTTime());
        this.modules.add(new AutoTNT());
        this.modules.add(new AutoKit());
        this.modules.add(new AutoLogin());
        this.modules.add(new FakePlayer());
        this.modules.add(new AutoReconnect());
        this.modules.add(new KillEffects());
        this.modules.add(new Coords());
        this.modules.add(new PearlNotify());
        this.modules.add(new Peek());
        this.modules.add(new GhastNotifier());
        this.modules.add(new ToolTips());
        this.modules.add(new MCF());
        this.modules.add(new TargetStrafe());
        this.modules.add(new FastSwim());
        this.modules.add(new ElytraFly());
        this.modules.add(new AntiWeb());
        this.modules.add(new NoJumpDelay());
        this.modules.add(new AutoCenter());
        this.modules.add(new Step());
        this.modules.add(new NewStep());
        this.modules.add(new Flight());
        this.modules.add(new Speed());
        this.modules.add(new Strafe());
        this.modules.add(new LongJump());
        this.modules.add(new SafeWalk());
        this.modules.add(new NoSlowDown());
        this.modules.add(new InventoryMove());
        this.modules.add(new Scaffold());
        this.modules.add(new FastWeb());
        this.modules.add(new AutoWalk());
        this.modules.add(new FastFall());
        this.modules.add(new Sprint());
        this.modules.add(new AntiVoid());
        this.modules.add(new AntiGlide());
        this.modules.add(new Velocity());
        this.modules.add(new HoleSnap());
        this.modules.add(new Blink());
        this.modules.add(new PacketFly());
        this.modules.add(new BetterPortal());
        this.modules.add(new SuperThrow());
        this.modules.add(new SuperBow());
        this.modules.add(new Phase());
        this.modules.add(new TPCoordLog());
        this.modules.add(new GodMode());
        this.modules.add(new MultiTask());
        this.modules.add(new LiquidInteract());
        this.modules.add(new NoHitBox());
        this.modules.add(new Stresser());
        this.modules.add(new GhostHand());
        this.modules.add(new Crasher());
        this.modules.add(new XCarry());
        this.modules.add(new FakePearl());
        this.modules.add(new PearlSpoof());
        this.modules.add(new Clip());
        this.modules.add(new NoInteract());
    }
    
    public void onDeath(final EntityPlayer entityPlayer) {
        this.modules.stream().filter(Module::isOn).forEach(ModuleManager::lambda$onDeath$6);
    }
    
    public ArrayList getEnabledModules() {
        final ArrayList<Module> list = new ArrayList<Module>();
        final Iterator<Module> iterator = (Iterator<Module>)this.modules.iterator();
        if (!iterator.hasNext()) {
            return list;
        }
        final Module module = iterator.next();
        if (!module.isOn()) {
            return null;
        }
        if ((boolean)HUD.INSTANCE.onlyBind.getValue() && ((Bind)module.bind.getValue()).getKey() == -1) {
            return null;
        }
        list.add(module);
        return null;
    }
    
    public void onLogout() {
        this.modules.forEach(Module::onLogout);
    }
    
    public void onRender2D(final Render2DEvent render2DEvent) {
        this.modules.stream().filter(Module::isOn).forEach(ModuleManager::lambda$onRender2D$3);
    }
    
    public List getCategories() {
        return Arrays.asList(Category.values());
    }
    
    private static void lambda$onKeyInput$2(final int n, final Module module) {
        if (module.getBind().getKey() == n) {
            module.toggle();
        }
    }
    
    public ModuleManager() {
        this.modules = new ArrayList();
        this.sortedLength = new ArrayList();
        this.sortedAbc = new ArrayList();
    }
    
    public void onTick() {
        this.modules.stream().filter(Module::isOn).forEach(Module::onTick);
    }
    
    private static Integer lambda$sortModules$0(final Module module) {
        return Managers.TEXT.getStringWidth(((boolean)HUD.INSTANCE.lowerCase.getValue()) ? module.getArrayListInfo().toLowerCase() : module.getArrayListInfo()) * -1;
    }
    
    public void onKeyInput(final int n) {
        if (n == 0 || !Keyboard.getEventKeyState() || ModuleManager.mc.currentScreen instanceof Gui) {
            return;
        }
        this.modules.forEach(ModuleManager::lambda$onKeyInput$2);
    }
    
    public void onLogin() {
        this.modules.forEach(Module::onLogin);
    }
    
    private static void lambda$onDeath$6(final EntityPlayer entityPlayer, final Module module) {
        module.onDeath(entityPlayer);
    }
    
    public ArrayList getModules() {
        return this.modules;
    }
    
    public enum Ordering
    {
        ABC("ABC", 0), 
        LENGTH("LENGTH", 1);
        
        private static final Ordering[] $VALUES;
        
        private Ordering(final String s, final int n) {
        }
        
        static {
            $VALUES = new Ordering[] { Ordering.ABC, Ordering.LENGTH };
        }
    }
}
