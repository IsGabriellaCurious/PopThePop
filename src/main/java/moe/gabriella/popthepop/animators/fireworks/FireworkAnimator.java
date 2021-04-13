package moe.gabriella.popthepop.animators.fireworks;

import moe.gabriella.popthepop.PopThePop;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class FireworkAnimator extends BukkitRunnable {

    Player player;
    World world;
    Location f1;
    Location f2;
    Location f3;

    Firework fw1;
    Firework fw2;
    Firework fw3;

    public FireworkAnimator(Player player, World world) {
        this.player = player;
        this.world = world;

        f1 = new Location(world, -2004, 76, -334);
        f2 = new Location(world, -2004, 76, -349);
        f3 = new Location(world, -2005, 76, -366);
    }

    @Override
    public void run() {
        if (world == null || player == null || !player.isOnline()) {
            cancel();
            return;
        }

        for (int i = 0; i < 3; i++) {
            Location loc;
            if (i == 0) loc = f1;
            else if (i == 1) loc = f2;
            else loc = f3;

            Firework fw = (Firework) world.spawnEntity(loc, EntityType.FIREWORK);
            FireworkMeta m = fw.getFireworkMeta();

            m.setPower(10);
            m.addEffect(FireworkEffect.builder().withColor(Color.ORANGE).flicker(true).withFade(Color.PURPLE).trail(true).build());

            fw.setFireworkMeta(m);

            Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(PopThePop.getInstance(), fw::detonate, 30);
        }

    }
}
