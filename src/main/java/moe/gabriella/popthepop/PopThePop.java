package moe.gabriella.popthepop;

import lombok.Getter;
import moe.gabriella.popthepop.animators.fireworks.FireworkAnimator;
import moe.gabriella.popthepop.animators.title.PreMTitleAnimator;
import moe.gabriella.popthepop.animators.time.TimeAnimator;
import moe.gabriella.popthepop.commands.EmergencyChatCommand;
import moe.gabriella.popthepop.utils.Message;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PopThePop extends JavaPlugin {

    @Getter private static PopThePop instance;
    @Getter private FileConfiguration config;

    private HashMap<Player, World> worlds;
    private ArrayList<Player> disallowMove;

    public ArrayList<Player> progressMoon;

    private static HashMap<String, Long> timings = new HashMap<>();
    private static final Object syncTimings = new Object();

    public static boolean emergencyChatMode;

    @Override
    public void onEnable() {
        startTimings("PluginStartup");
        Message.log("Loading PopThePop...");

        instance = this;

        //this.saveDefaultConfig();
        //config = this.getConfig();

        worlds = new HashMap<>();
        disallowMove = new ArrayList<>();
        progressMoon = new ArrayList<>();

        emergencyChatMode = false;

        this.getCommand("emergencychat").setExecutor(new EmergencyChatCommand());

        // This was just a lil teaser for my friend lol
        /*Message.debug("Loaded event manager.");
        Message.debug("Loaded animation handler.");
        Message.warn("Day -> Monday. Disallowed user interaction for 1 day(s).");*/

        this.getServer().getPluginManager().registerEvents(new EventManager(), this);
        Message.log("PopThePop is ready! Took " + stopTimingsQuite("PluginStartup") + "ms.");
    }

    @Override
    public void onDisable() {

        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.kickPlayer(Message.format("Server is shutting down."));
        }

        for (Map.Entry<Player, World> ws : worlds.entrySet()) {
            deleteWorld(ws.getKey(), false);
        }

        Message.log("Thanks for using PopThePop! Goodbye!");
    }

    public void toggleMovement(Player player, boolean movement) {
        if (movement)
            disallowMove.remove(player);
        else
            disallowMove.add(player);
    }

    public boolean canMove(Player player) {
        return !disallowMove.contains(player);
    }


    public void beginAnimation(Player player) {
        player.sendMessage(Message.format("Hewwo, preparing..."));

        this.getServer().getScheduler().runTaskLater(this, () -> createWorld(player), 40);
    }

    public void createWorld(Player player) {
        startTimings(player.getName() + "WorldCreation");
        String worldName =  player.getName() + "-world";

        File currentDir;
        File toCopy;

        toCopy = new File("playerworld");
        currentDir = new File(worldName);
        boolean mkdirSuccess = currentDir.mkdir();
        if (!mkdirSuccess) {
            player.sendMessage(Message.format("" + ChatColor.RED + "Error creating world (folder-creation). Try relogging eeee."));
            stopTimings(player.getName() + "WorldCreation");
            return;
        }

        try {
            FileUtils.copyDirectory(toCopy, currentDir);
        } catch (IOException e) {
            player.sendMessage(Message.format("" + ChatColor.RED + "Error creating world (folder-copy). Try relogging eeee."));
            stopTimings(player.getName() + "WorldCreation");
            e.printStackTrace();
            return;
        }

        World world = Bukkit.getServer().createWorld(new WorldCreator(worldName));
        worlds.put(player, world);

        world.setFullTime(21000);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setDifficulty(Difficulty.PEACEFUL);

        // /tp Gabrlella -1959.367 90 -349.368 89.5 1.5
        player.teleport(new Location(world, -1959.367, 90, -349.368, 89.5f, 1.5f));
        player.setAllowFlight(true);
        player.setFlying(true);
        player.setGameMode(GameMode.CREATIVE);
        player.getInventory().clear();
        toggleMovement(player, false);

        this.getServer().getScheduler().runTaskLater(this, () -> {
            new PreMTitleAnimator(player, this).runTaskAsynchronously(this);
            new TimeAnimator(player).runTaskTimer(this, 0, 2);
            new FireworkAnimator(player, world).runTaskTimer(this, 0, 80);
        }, 40);

        stopTimings(player.getName() + "WorldCreation");
    }

    public void deleteWorld(Player player, boolean kick) {
        startTimings(player.getName() + "WorldDeletion");
        String worldName =  player.getName() + "-world";

        if (Bukkit.getServer().getWorld(worldName) == null || !worlds.containsKey(player))
            return;

        Message.debug("passed null checks");

        World world = worlds.get(player);
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (p.getWorld() == world)
                p.teleport(Bukkit.getServer().getWorld("world").getSpawnLocation());
        }

        Bukkit.getServer().unloadWorld(world, false);
        FileUtils.deleteQuietly(new File(worldName));
        worlds.remove(player);

        disallowMove.remove(player);
        progressMoon.remove(player);

        if (kick)
            player.kickPlayer(Message.format("Thanks for watching!"));

        stopTimings(player.getName() + "WorldDeletion");
    }

    // Timings
    public static void startTimings(String task) {
        synchronized (syncTimings) {
            timings.put(task, System.currentTimeMillis());
        }
    }

    public static Long stopTimingsQuite(String task) {
        long result;
        synchronized (syncTimings) {
            result = (System.currentTimeMillis() - timings.get(task));
            timings.remove(task);
            return result;
        }
    }

    public static Long stopTimings(String task) {
        long result = stopTimingsQuite(task);
        Message.timings(task + " has finished and took " + result + "ms (" + Message.formatTime((int) (result / 1000)) + ")");
        return result;
    }
}
