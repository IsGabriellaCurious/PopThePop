package moe.gabriella.popthepop.guis;

import me.gabriella.gabsgui.GUIBase;
import me.gabriella.gabsgui.GUIButton;
import me.gabriella.gabsgui.GUIItem;
import moe.gabriella.popthepop.PopThePop;
import moe.gabriella.popthepop.animators.title.MTitleAnimator;
import moe.gabriella.popthepop.animators.title.NoMTitleAnimator;
import moe.gabriella.popthepop.animators.title.PostMTitleAnimator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MessagesGui extends GUIBase {

    public MessagesGui(JavaPlugin plugin, Player player) {
        super(plugin, player, "§d§lWell do you?", 9, true);
    }

    @Override
    public void setupItems() {
        GUIItem yes = new GUIItem(Material.EMERALD_BLOCK).amount(1).displayName("§aYes I would!");
        yes.button(new GUIButton() {
            @Override
            public boolean leftClick() {
                getPlayer().closeInventory();
                new MTitleAnimator(getPlayer(), PopThePop.getInstance()).runTaskAsynchronously(PopThePop.getInstance());
                return true;
            }

            @Override public boolean leftClickShift() { return false; }
            @Override public boolean rightClick() { return false; }
            @Override public boolean rightClickShift() { return false; }
        });

        GUIItem no = new GUIItem(Material.REDSTONE_BLOCK).amount(1).displayName("§cEeee, no thanks!");
        no.button(new GUIButton() {
            @Override
            public boolean leftClick() {
                getPlayer().closeInventory();
                new NoMTitleAnimator(getPlayer(), PopThePop.getInstance()).runTaskAsynchronously(PopThePop.getInstance());
                return true;
            }

            @Override public boolean leftClickShift() { return false; }
            @Override public boolean rightClick() { return false; }
            @Override public boolean rightClickShift() { return false; }
        });

        addItem(3, yes);
        addItem(5, no);
    }
}
