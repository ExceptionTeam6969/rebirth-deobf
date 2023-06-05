//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.hud;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.item.*;
import java.awt.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.entity.player.*;
import java.util.*;
import java.util.function.*;
import net.minecraft.client.gui.*;

public class TargetHUD extends Module
{
    private final Setting x;
    private final Setting y;
    private final Setting backgroundAlpha;
    EntityLivingBase target;
    
    private static boolean checkIsNotPlayer(final Entity entity) {
        return !entity.equals((Object)TargetHUD.mc.player);
    }
    
    @Override
    public synchronized void onRender2D(final Render2DEvent render2DEvent) {
        if (this.target != null && !this.target.isDead) {
            final FontRenderer fontRenderer = TargetHUD.mc.fontRenderer;
            final int n = (this.target.getHealth() / this.target.getMaxHealth() > 0.66f) ? -16711936 : ((this.target.getHealth() / this.target.getMaxHealth() > 0.33f) ? -26368 : -65536);
            GlStateManager.color(1.0f, 1.0f, 1.0f);
            GuiInventory.drawEntityOnScreen((int)this.x.getValue() + 15, (int)this.y.getValue() + 32, 15, 1.0f, 1.0f, this.target);
            final LinkedList<ItemStack> list = new LinkedList<ItemStack>();
            final LinkedList<ItemStack> list2 = new LinkedList<ItemStack>();
            this.target.getArmorInventoryList().forEach(TargetHUD::lambda$onRender2D$0);
            int n2 = list2.size() - 1;
            if (n2 >= 0) {
                list.add((ItemStack)list2.get(n2));
                --n2;
                return;
            }
            int n3 = 0;
            switch (list.size()) {
                case 0: {
                    if (!this.target.getHeldItemMainhand().isEmpty() && !this.target.getHeldItemOffhand().isEmpty()) {
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemMainhand(), (int)this.x.getValue() + 28, (int)this.y.getValue() + 18);
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemOffhand(), (int)this.x.getValue() + 43, (int)this.y.getValue() + 18);
                        n3 += 45;
                        break;
                    }
                    if (!this.target.getHeldItemMainhand().isEmpty() || !this.target.getHeldItemOffhand().isEmpty()) {
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemMainhand().isEmpty() ? this.target.getHeldItemOffhand() : this.target.getHeldItemMainhand(), (int)this.x.getValue() + 28, (int)this.y.getValue() + 18);
                        n3 += 30;
                        break;
                    }
                    break;
                }
                case 1: {
                    n3 = 15;
                    TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)list.get(0), (int)this.x.getValue() + 28, (int)this.y.getValue() + 18);
                    if (!this.target.getHeldItemMainhand().isEmpty() && !this.target.getHeldItemOffhand().isEmpty()) {
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemMainhand(), (int)this.x.getValue() + 43, (int)this.y.getValue() + 18);
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemOffhand(), (int)this.x.getValue() + 58, (int)this.y.getValue() + 18);
                        n3 += 45;
                        break;
                    }
                    if (!this.target.getHeldItemMainhand().isEmpty() || !this.target.getHeldItemOffhand().isEmpty()) {
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemMainhand().isEmpty() ? this.target.getHeldItemOffhand() : this.target.getHeldItemMainhand(), (int)this.x.getValue() + 43, (int)this.y.getValue() + 18);
                        n3 += 30;
                        break;
                    }
                    break;
                }
                case 2: {
                    n3 = 30;
                    TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)list.get(0), (int)this.x.getValue() + 28, (int)this.y.getValue() + 18);
                    TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)list.get(1), (int)this.x.getValue() + 43, (int)this.y.getValue() + 18);
                    if (!this.target.getHeldItemMainhand().isEmpty() && !this.target.getHeldItemOffhand().isEmpty()) {
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemMainhand(), (int)this.x.getValue() + 58, (int)this.y.getValue() + 18);
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemOffhand(), (int)this.x.getValue() + 73, (int)this.y.getValue() + 18);
                        n3 += 45;
                        break;
                    }
                    if (!this.target.getHeldItemMainhand().isEmpty() || !this.target.getHeldItemOffhand().isEmpty()) {
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemMainhand().isEmpty() ? this.target.getHeldItemOffhand() : this.target.getHeldItemMainhand(), (int)this.x.getValue() + 58, (int)this.y.getValue() + 18);
                        n3 += 30;
                        break;
                    }
                    break;
                }
                case 3: {
                    n3 = 45;
                    TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)list.get(0), (int)this.x.getValue() + 28, (int)this.y.getValue() + 18);
                    TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)list.get(1), (int)this.x.getValue() + 43, (int)this.y.getValue() + 18);
                    TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)list.get(2), (int)this.x.getValue() + 58, (int)this.y.getValue() + 18);
                    if (!this.target.getHeldItemMainhand().isEmpty() && !this.target.getHeldItemOffhand().isEmpty()) {
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemMainhand(), (int)this.x.getValue() + 73, (int)this.y.getValue() + 18);
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemOffhand(), (int)this.x.getValue() + 98, (int)this.y.getValue() + 18);
                        n3 += 45;
                        break;
                    }
                    if (!this.target.getHeldItemMainhand().isEmpty() || !this.target.getHeldItemOffhand().isEmpty()) {
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemMainhand().isEmpty() ? this.target.getHeldItemOffhand() : this.target.getHeldItemMainhand(), (int)this.x.getValue() + 73, (int)this.y.getValue() + 18);
                        n3 += 30;
                        break;
                    }
                    break;
                }
                case 4: {
                    n3 = 60;
                    TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)list.get(0), (int)this.x.getValue() + 28, (int)this.y.getValue() + 18);
                    TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)list.get(1), (int)this.x.getValue() + 43, (int)this.y.getValue() + 18);
                    TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)list.get(2), (int)this.x.getValue() + 58, (int)this.y.getValue() + 18);
                    TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)list.get(3), (int)this.x.getValue() + 73, (int)this.y.getValue() + 18);
                    if (!this.target.getHeldItemMainhand().isEmpty() && !this.target.getHeldItemOffhand().isEmpty()) {
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemMainhand(), (int)this.x.getValue() + 98, (int)this.y.getValue() + 18);
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemOffhand(), (int)this.x.getValue() + 113, (int)this.y.getValue() + 18);
                        n3 += 45;
                        break;
                    }
                    if (!this.target.getHeldItemMainhand().isEmpty() || !this.target.getHeldItemOffhand().isEmpty()) {
                        TargetHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(this.target.getHeldItemMainhand().isEmpty() ? this.target.getHeldItemOffhand() : this.target.getHeldItemMainhand(), (int)this.x.getValue() + 98, (int)this.y.getValue() + 18);
                        n3 += 30;
                        break;
                    }
                    break;
                }
            }
            int n4 = (int)this.y.getValue() + 35;
            final int n5 = fontRenderer.getStringWidth(this.target.getName()) + 30;
            int n6;
            if (fontRenderer.getStringWidth(this.target.getName()) > n3) {
                n6 = (int)this.x.getValue() + n5;
            }
            else {
                n6 = (int)this.x.getValue() + n3 + 30;
            }
            n6 += 5;
            n4 += 5;
            Gui.drawRect((int)this.x.getValue() - 2, (int)this.y.getValue(), n6, n4, new Color(0, 0, 0, (int)this.backgroundAlpha.getValue()).getRGB());
            Gui.drawRect((int)this.x.getValue() - 2, n4 - 2, (int)this.x.getValue() + (int)(this.target.getHealth() / this.target.getMaxHealth() * (n6 - (int)this.x.getValue())), n4, n);
            fontRenderer.drawString(this.target.getName(), (float)((int)this.x.getValue() + 30), (float)((int)this.y.getValue() + 8), new Color(255, 255, 255).getRGB(), true);
        }
    }
    
    public TargetHUD() {
        super("TargetHUD", "description", Category.HUD);
        this.x = this.add(new Setting("X", 50, 0, 2000));
        this.y = this.add(new Setting("Y", 50, 0, 2000));
        this.backgroundAlpha = this.add(new Setting("Alpha", 80, 0, 255));
        this.target = (EntityLivingBase)TargetHUD.mc.player;
    }
    
    private static double applyAsDouble(final EntityLivingBase entityLivingBase) {
        return entityLivingBase.getDistance((Entity)TargetHUD.mc.player);
    }
    
    private static void lambda$onRender2D$0(final List list, final ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            list.add(itemStack);
        }
    }
    
    @Override
    public synchronized void onTick() {
        final LinkedList<EntityLivingBase> list = new LinkedList<EntityLivingBase>();
        TargetHUD.mc.world.loadedEntityList.stream().filter(EntityPlayer.class::isInstance).filter(TargetHUD::checkIsNotPlayer).map(EntityLivingBase.class::cast).sorted(Comparator.comparingDouble((ToDoubleFunction<? super T>)TargetHUD::applyAsDouble)).forEach(list::add);
        if (!list.isEmpty()) {
            this.target = (EntityLivingBase)list.get(0);
        }
        else {
            this.target = (EntityLivingBase)TargetHUD.mc.player;
        }
        if (TargetHUD.mc.currentScreen instanceof GuiChat) {
            this.target = (EntityLivingBase)TargetHUD.mc.player;
        }
    }
}
