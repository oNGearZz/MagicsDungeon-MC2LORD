package net.ongearzz.magicsdungeonphakechomc2lord.Utils;

import com.sun.imageio.plugins.tiff.TIFFStreamMetadata;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
import io.lumine.xikage.mythicmobs.utils.particles.DustParticle;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Shulker;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsConfig {
    private File file;
    private String name;
    public YamlConfiguration yamlConfiguration;
    public List<String> chestParticle = new ArrayList<>();
    public List<String> customSpawn = new ArrayList<>();
    public List<String> doors = new ArrayList<>();
    //    public List<String> doorPhu = new ArrayList<>();
//    public static List<Location> doorphuParticle = new ArrayList<>();
    public List<String> boss = new ArrayList<>();
    public static List<Location> bossParticle = new ArrayList<>();
    public static List<Location> doorParticle = new ArrayList<>();
    public static List<Location> paticles = new ArrayList<>();
    public static List<Location> paticle2 = new ArrayList<>();
    public Map<Integer, String> datas = new HashMap<>();
    public Map<Integer, Integer> chances = new HashMap<>();
    public static boolean startParticle = false;

    public MapsConfig(String name) {
        this.name = name;
    }

    public static void loadParticle1(MapsConfig config) {
        for (int i = 0; i < config.getConfig().getStringList("CustomSpawn").size(); i++) {
            String loc = config.getConfig().getStringList("CustomSpawn").get(i).split(";")[2];
            String world = loc.split(" ")[0];
            int x = Integer.parseInt(loc.split(" ")[1]);
            int y = Integer.parseInt(loc.split(" ")[2]);
            int z = Integer.parseInt(loc.split(" ")[3]);
            Location location = new Location(Bukkit.getWorld(world), x, y, z);
            paticles.add(location);
        }
        for (int in = 0; in < config.getConfig().getStringList("LootChest").size(); in++) {
            String loc = config.getConfig().getStringList("LootChest").get(in);
            World world = Bukkit.getWorld(loc.split(" ")[0]);
            int x = Integer.parseInt(loc.split(" ")[1]);
            int y = Integer.parseInt(loc.split(" ")[2]);
            int z = Integer.parseInt(loc.split(" ")[3]);
            Location location = new Location(world, x, y, z);
            paticle2.add(location);
        }
        for (int a = 0; a < config.getConfig().getStringList("Doors").size(); a++) {
            String loc = config.getConfig().getStringList("Doors").get(a);
            int stage = Integer.parseInt(loc.split(" ")[0]);
            World world = Bukkit.getWorld(loc.split(" ")[1]);
            int x = Integer.parseInt(loc.split(" ")[2]);
            int y = Integer.parseInt(loc.split(" ")[3]);
            int z = Integer.parseInt(loc.split(" ")[4]);
            Location location = new Location(world, x, y, z);
            doorParticle.add(location);
        }
        for (int b = 0; b < config.getConfig().getStringList("DoorsPhu").size(); b++) {
            String loc = config.getConfig().getStringList("DoorsPhu").get(b);
            World world = Bukkit.getWorld(loc.split(" ")[0]);
            int x = Integer.parseInt(loc.split(" ")[1]);
            int y = Integer.parseInt(loc.split(" ")[2]);
            int z = Integer.parseInt(loc.split(" ")[3]);
            Location location = new Location(world, x, y, z);
            //    doorphuParticle.add(location);
        }
        for (int j = 0; j < config.getConfig().getStringList("boss").size(); j++) {
            String loc = config.getConfig().getStringList("boss").get(j);
            World world = Bukkit.getWorld(loc.split(" ")[0]);
            int x = Integer.parseInt(loc.split(" ")[1]);
            int y = Integer.parseInt(loc.split(" ")[2]);
            int z = Integer.parseInt(loc.split(" ")[3]);
            Location location = new Location(world, x, y, z);
            bossParticle.add(location);
        }
    }

    public void saveXreload() {
        this.file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Maps", name + ".yml");
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        save();

        try {
            reload();
            yamlConfiguration.set("CustomSpawn", customSpawn);
            yamlConfiguration.set("LootChest", chestParticle);
            yamlConfiguration.set("Doors", doors);
            //  yamlConfiguration.set("DoorsPhu", doorPhu);
            yamlConfiguration.set("boss", boss);
            yamlConfiguration.set("Items.0", "test");
            for (int i = 0; i < datas.size();i++) {
                yamlConfiguration.set("Items." + i + ".data", datas.get(i));
                yamlConfiguration.set("Items." + i + ".chance", chances.get(i));
            }
            yamlConfiguration.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }


    public FileConfiguration getConfig() {
        return yamlConfiguration;
    }

    public static void spawmParticle() {
        if (startParticle) {
            for (int i = 0; i < paticles.size(); i++) {
                Location location = paticles.get(i);
                location.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, new Location(location.getWorld(), location.getX() + 0.5, location.getY() + 1, location.getZ() + 0.5), 0, 0, 0, 0, 1);
            }
            for (int a = 0; a < paticle2.size(); a++) {
                Location loc = paticle2.get(a);
                loc.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, new Location(loc.getWorld(), loc.getX() + 0.5, loc.getY() + 1.5, loc.getZ() + 0.5), 0, 0, 0, 0, 1);
            }
            for (int b = 0; b < doorParticle.size(); b++) {
                Location location = doorParticle.get(b);
                location.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, new Location(location.getWorld(), location.getX() + 0.5, location.getY(), location.getZ() + 0.5), 0, 0, 0, 0, 1);
            }
//            for (int c = 0; c < doorphuParticle.size(); c++) {
//                Location location = doorParticle.get(c);
//                location.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, new Location(location.getWorld(), location.getX() + 0.5, location.getY(), location.getZ() + 0.5), 0, 0, 0, 0, 1);
//            }
            for (int j = 0; j < bossParticle.size(); j++) {
                Location location = bossParticle.get(j);
                location.getWorld().spawnParticle(Particle.SPELL, new Location(location.getWorld(), location.getX() + 0.5, location.getY(), location.getZ() + 0.5), 0, 0, 0, 0, 1);
            }
        }
    }


    public void load() {
        this.file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Maps", name + ".yml");
        if (!file.exists()) {
            save();
        }
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.put(name, this);
        if (getConfig().getStringList("CustomSpawn") == null) {
            getConfig().set("CustomSpawn", customSpawn);
        }
        if (getConfig().getStringList("LootChest") == null) {
            getConfig().set("LootChest", chestParticle);
        }
        if (getConfig().getStringList("Doors") == null) {
            getConfig().set("Doors", doors);
        }
        save();
        customSpawn.addAll(getConfig().getStringList("CustomSpawn"));
        chestParticle.addAll(getConfig().getStringList("LootChest"));
        doors.addAll(getConfig().getStringList("Doors"));
        boss.addAll(getConfig().getStringList("boss"));
        int Item = getConfig().contains("Items") ? getConfig().getConfigurationSection("Items").getKeys(false).size() : 0;
        for (int i = 0; i < Item; i++) {
            if (getConfig().getString("Items." + i + ".data") != null) {
                datas.put(i,getConfig().getString("Items." + i + ".data"));
                chances.put(i,getConfig().getInt("Items." + i + ".chance"));
            }
        }
        // doorPhu.addAll(getConfig().getStringList("DoorsPhu"));
    }

    public static void clearStorge() {
        bossParticle.clear();
        doorParticle.clear();
        paticles.clear();
        paticle2.clear();
    }

    public void reload() {
        customSpawn.clear();
        chestParticle.clear();
        doors.clear();
        boss.clear();
        datas.clear();
        chances.clear();
        customSpawn.addAll(getConfig().getStringList("CustomSpawn"));
        chestParticle.addAll(getConfig().getStringList("LootChest"));
        doors.addAll(getConfig().getStringList("Doors"));
        boss.addAll(getConfig().getStringList("boss"));
        int Item = getConfig().contains("Items") ? getConfig().getConfigurationSection("Items").getKeys(false).size() : 0;
        for (int i = 0; i < Item; i++) {
            if (getConfig().getString("Items." + i + ".data") != null) {
                datas.put(i,getConfig().getString("Items." + i + ".data"));
                chances.put(i, getConfig().getInt("Items." + i + ".chance"));
            }
        }
    }

    public void save() {
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> dataInv = new HashMap<>();

    public static void add(Player player, String code1, String code2) {
        String data = ItemData.itemStackArrayToBase64(player.getInventory().getContents());
        dataInv.put(player.getName(), data);
        player.getInventory().clear();
        player.getInventory().setItem(0, getIs1(code1));
        player.getInventory().setItem(1, getIs2(code2));
        player.getInventory().setItem(8, getIs3());
    }

    public static void remove(Player player) {
        String oldData = dataInv.get(player.getName());
        player.getInventory().clear();
        try {
            player.getInventory().setContents(ItemData.itemStackArrayFromBase64(oldData));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ItemStack getIs1(String code) {
        ItemStack stack = new ItemBuilder(Material.DIAMOND_HOE).setDisplayName(code)
                .addLore("Click vào Block Để Add ._.").build();
        return stack;
    }

    public static ItemStack getIs2(String code) {
        ItemStack stack = new ItemBuilder(Material.IRON_HOE).setDisplayName(code)
                .addLore("Click Vào Block Có shulker_bullet để remove ._.")
                .build();
        return stack;
    }

    public static ItemStack getIs3() {
        ItemStack stack = new ItemBuilder(Material.BARRIER).setDisplayName("Exit Edit Mode ._.")
                .build();
        return stack;
    }
}
