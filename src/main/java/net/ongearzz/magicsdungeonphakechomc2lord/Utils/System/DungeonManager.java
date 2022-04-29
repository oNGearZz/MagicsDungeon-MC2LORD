package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import net.minecraft.server.v1_12_R1.*;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Map1.Manager;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.*;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Class.LevelSystem.Level;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Npc.NpcCreate;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Keys.BossKeyStack;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Keys.NormalKeyStack;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.ScoreBoards.CreateBoards;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.PI;

public class DungeonManager {

    private DungeonManager getThis;

    public String id;

    public File file;

    public ArrayList<Player> players;

    public boolean start;

    public WorldManager worldManager;

    public String worldDau;

    private YamlConfiguration yaml;

    public int stages = 0;

    public List<Location> lootchestList = new ArrayList<>();

    public List<Location> chestDaMo = new ArrayList<>();

    public String worldFolderDirec;

    public List<String> mobsCanGiet = new ArrayList<>();

    public String key;

    public Integer keyid;

    public List<Location> blocks = new ArrayList<>();

    public List<Location> blocksDoorPhu = new ArrayList<>();

    public List<UUID> bossMob = new ArrayList<>();

    public int maxStage;

    public boolean boss;

    public boolean bossKey;

    public boolean miniBossDeath;

    public boolean b = false;

    public MapsConfig mapsConfig;

    public double mobsize;

    public double phanTramMoiMob;

    private int times;

    private boolean aBoolean = false;

    public Map<Player, Integer> score = new HashMap<>();

    public List<Player> daMoEndLoot = new ArrayList<>();

    public Map<Integer, Player> armorstandRe = new HashMap<Integer, Player>();

    public List<Player> needRefIGG1 = new ArrayList<>();

    private Map<Player, ArmorStand> originArmorStand = new HashMap<>();

    private Lang lang = new Lang();

    private final MagicsDungeonPhakeChoMC2LORD plugin = MagicsDungeonPhakeChoMC2LORD.getInstance();

    public DungeonManager(String mapid, ArrayList<Player> players, WorldManager worldManager, String worldDau) {
        this.start = false;
        this.worldDau = worldDau;
        this.id = mapid;
        this.worldManager = worldManager;
        this.players = players;
        for (Player player : players) {
            PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
            info2.SetManager(this);
            info2.setIg(false);
        }
        worldFolderDirec = plugin.getDataFolder() + "/Dungeons/" + mapid;
        plugin.dulieuDungeon.put(worldFolderDirec, "");
        this.mapsConfig = MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.get(worldDau);
        this.maxStage = mapsConfig.doors.size();
        this.mobsize = mapsConfig.customSpawn.size();
        this.phanTramMoiMob = 100 / mobsize;
        this.getThis = this;
    }

    public int getD = 100;

    public int getC = 200;

    public int getB = 300;

    public int getA = 500;

    public int getS = 800;

    public String getScore(Player player) {
        int score = getScore().get(player);
        if (score >= getS) {
            return "S";
        } else if (score >= getA) {
            if (score < 800) {
                return "A";
            }
        } else if (score >= getB) {
            if (score < 500) {
                return "B";
            }
        } else if (score >= getC) {
            if (score < 300) {
                return "C";
            }
        } else if (score >= getD) {
            if (score < 200) {
                return "D";
            }
        }
        return null;
    }

    private void timeChecker() {
        new BukkitRunnable() {
            @Override
            public void run() {
                times++;
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 20);
    }

    public int getTimes() {
        return times;
    }

    public int getMinute() {
        int t = 1;
        while (t * 60 < getTimes())
            t++;
        return (t - 1);
    }

    public int getSecond() {
        return getTimes() - (60 * getMinute());
    }

    public String getTime() {
        return getMinute() + "m" + getSecond() + "s";
    }

    public org.bukkit.inventory.ItemStack getRewardStack() {
        ItemStack stack = new ItemBuilder(ItemBuilder.SkullWithValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ0NDk4YTBmZTI3ODk1NmUzZDA0MTM1ZWY0YjEzNDNkMDU0OGE3ZTIwOGM2MWIxZmI2ZjNiNGRiYzI0MGRhOCJ9fX0="))
                .build();
        return stack;
    }

    public void loadEndLootChest(Player player) {
        daMoEndLoot.add(player);
    }

    public void rewardGUI(Player player) {
        if (!daMoEndLoot.contains(player)) {
            loadEndLootChest(player);
            Inventory inventory = Bukkit.createInventory(null, 54, lang.getRewarChestTitle());
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE)
                        .setDisplayName("§b")
                        .build());
            }
//            inventory.setItem(11, new ItemBuilder(Material.IRON_SWORD).setDisplayName("test1").build());
//            inventory.setItem(13, new ItemBuilder(Material.GOLD_SWORD).setDisplayName("test2").build());
//            inventory.setItem(15, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("test3").build());
            loadItem(inventory,player,11);
            loadItem(inventory,player,13);
            loadItem(inventory,player, 15);
            inventory.setItem(45, new ItemBuilder(Material.SKULL_ITEM)
                    .setHead(player.getName())
                    .setDisplayName("§7" + player.getName() + "'s lootChest!")
                    .build());

            inventory.setItem(40, new ItemBuilder(Material.EMERALD_BLOCK)
                    .setDisplayName("§aMở")
                    .build());

            ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§a");
            stack.setItemMeta(meta);

            inventory.setItem(30, stack);
            inventory.setItem(31, stack);
            inventory.setItem(32, stack);
            inventory.setItem(39, stack);
            inventory.setItem(41, stack);
            inventory.setItem(48, stack);
            inventory.setItem(49, stack);
            inventory.setItem(50, stack);
            player.openInventory(inventory);
        } else {
            player.sendMessage(lang.getRewardChestError());
        }
    }

    public void loadItem(Inventory inventory,Player player, int slot) {
        TinhPhanTram tinhPhanTram = new TinhPhanTram();
        String score = getScore(player);
        YamlConfiguration configuration = plugin.rewardConfig.getConfiguration();
        for (int i = 0; i < configuration.getStringList(score + ".Items").size(); i++) {
            int chance = configuration.getInt(score + ".Items." + i + ".chance");
            if (tinhPhanTram.tinhPhanTram(chance)) {
                ItemStack stack = ItemData.GetItemStack(score + ".Items." + i + ".data");
                inventory.setItem(slot, stack);
            } else {
                if (inventory.getItem(slot).getType() == Material.AIR) {
                    loadItem(inventory,player,slot);
                }
            }
        }
    }

    public void openLootChest(Player player, Inventory inventory) {
        Location location = Manager.rewardLoc(worldManager.getWorld());
        ItemStack item1 = inventory.getItem(11);
        ItemStack item2 = inventory.getItem(13);
        ItemStack item3 = inventory.getItem(15);
        if (item1 != null && item2 != null && item3 != null) {
            if (item1.getType() != Material.AIR && item2.getType() != Material.AIR && item3.getType() != Material.AIR) {
                //6.7 // 5.85
                player.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, Manager.rewardLoc(worldManager.getWorld()), 0, 0, 0, 0, 1);
                PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
                new BukkitRunnable() {
                    ArmorStand stan1;
                    ArmorStand stan2;
                    ArmorStand stan3;
                    int tick = 0;

                    @Override
                    public void run() {
                        if (info2.isIg()) {
                            if (info2.getManager() != null) {
                                DungeonManager manager = info2.getManager();
                                if (tick == 30) {
                                    ArmorStand stand1 = (ArmorStand) player.getWorld().spawnEntity(Manager.rewardLoc(worldManager.getWorld()), EntityType.ARMOR_STAND);
                                    ArmorStand stand2 = (ArmorStand) player.getWorld().spawnEntity(Manager.rewardLoc(worldManager.getWorld()), EntityType.ARMOR_STAND);
                                    ArmorStand stand3 = (ArmorStand) player.getWorld().spawnEntity(Manager.rewardLoc(worldManager.getWorld()), EntityType.ARMOR_STAND);
                                    stan1 = stand1;
                                    stan2 = stand2;
                                    stan3 = stand3;
                                }
                                if (tick <= 30) {
                                    if (info2.isB()) {
                                        EulerAngle eulerAngle = originArmorStand.get(player).getHeadPose().add(0, 0, 0.2);
                                        originArmorStand.get(player).setHeadPose(eulerAngle);
                                        info2.setB(false);
                                    } else {
                                        EulerAngle eulerAngle = originArmorStand.get(player).getHeadPose().add(0, 0, -0.2);
                                        originArmorStand.get(player).setHeadPose(eulerAngle);
                                        info2.setB(true);
                                    }
                                } else {
                                    Location locS1 = locationDoubleToInt(stan1.getLocation());
                                    Location locS2 = locationDoubleToInt(stan2.getLocation());
                                    Location locS3 = locationDoubleToInt(stan3.getLocation());

                                    Location playerLocation = locationDoubleToInt(player.getLocation());
                                    if (locS1.equals(playerLocation)) {
                                        if (!stan1.isDead()) {
                                            armorStandReward(stan1, player);
                                        }
                                    }
                                    if (locS2.equals(playerLocation)) {
                                        if (!stan2.isDead()) {
                                            armorStandReward(stan2, player);
                                        }
                                    }
                                    if (locS3.equals(playerLocation)) {
                                        if (!stan3.isDead()) {
                                            armorStandReward(stan3, player);
                                        }
                                    }
                                }
                                if (tick == 31) {
                                    EulerAngle eulerAngle = originArmorStand.get(player).getHeadPose().add(0, 0, 0.2);
                                    originArmorStand.get(player).setHeadPose(eulerAngle);
                                    setupStand1(stan1, item1);
                                    setupStand1(stan2, item2);
                                    setupStand1(stan3, item3);
                                    hideArmorStand(stan1, player);
                                    hideArmorStand(stan2, player);
                                    hideArmorStand(stan3, player);
                                    player.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, Manager.rewardLoc(worldManager.getWorld()), 0, 0, 0, 0, 1);
                                    net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.animation.armorstandAnimation.start(stan1, location, 2);
                                    net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.animation.armorstandAnimation.start2(stan2, location, 6.7);
                                    net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.animation.armorstandAnimation.start2(stan3, location, 5.85);
                                }
                            }
                        }
                        tick++;
                    }
                }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 2);
            }
        }
    }

    private void armorStandReward(ArmorStand stand, Player player) {
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
        if (info2.isIg()) {
            if (info2.getManager() != null) {
                if (info2.isB()) {
                    if (stand != null) {
                        if (stand.getHelmet() != null) {
                            ItemStack stack = stand.getHelmet();
                            player.getInventory().addItem(stack);
                            stand.remove();
                        }
                    }
                }
            }
        }
    }

    public void hideArmorStand(ArmorStand stand, Player owner) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : players) {
                    if (player != owner) {
                        PacketPlayOutEntityDestroy deadArmorstand = new PacketPlayOutEntityDestroy(stand.getEntityId());
                        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(deadArmorstand);
                    }
                }
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 0);
    }

    public Location locationDoubleToInt(Location location) {
        final int x = location.getBlockX();
        final int y = location.getBlockY();
        final int z = location.getBlockZ();
        final Location location1 = new Location(location.getWorld(), x, y, z);
        return location1;
    }

    public void setupStand1(ArmorStand stand, ItemStack stack) {
        new BukkitRunnable() {
            @Override
            public void run() {
                EulerAngle eulerAngle = stand.getHeadPose();
                EulerAngle euler = eulerAngle.add(0, 0.15, 0);
                stand.setHeadPose(euler);
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 1);
        stand.setHelmet(stack);
        stand.setInvulnerable(true);
        stand.setGravity(false);
        stand.setVisible(false);
        stand.setCustomName(stack.getItemMeta().getDisplayName());
        stand.setCustomNameVisible(true);
        stand.setCanPickupItems(true);
    }

    public void reward() {
        rewardParticle1();
        for (Player player : players) {
            ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(Manager.rewardLoc(worldManager.getWorld()), EntityType.ARMOR_STAND);
            stand.setInvulnerable(true);
            stand.setGravity(false);
            stand.setVisible(false);
            stand.setCustomName(lang.getRewarChestTitle());
            stand.setCustomNameVisible(true);
            stand.setCanPickupItems(false);
            stand.setHelmet(getRewardStack());
            armorstandRe.put(stand.getEntityId(), player);
            Player finalP = player;
            int x = stand.getLocation().getBlockX();
            int y = stand.getLocation().getBlockY();
            int z = stand.getLocation().getBlockZ();
            Location newloc = new Location(stand.getWorld(), x + 0.5, y, z + 0.5);
            stand.teleport(newloc);
            this.originArmorStand.put(player, stand);
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (int i = 0; i < players.size(); i++) {
                        Player player1 = players.get(i);
                        for (Player polayoi : Bukkit.getOnlinePlayers()) {
                            if (polayoi != finalP) {
                                PacketPlayOutEntityDestroy deadArmorstand = new PacketPlayOutEntityDestroy(stand.getEntityId());
                                ((CraftPlayer) polayoi).getHandle().playerConnection.sendPacket(deadArmorstand);
                            }
                        }
                    }
                }
            }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 0);
        }
    }


    public void rewardParticle1() {
        Location location = Manager.rewardLoc(worldManager.getWorld());
        new BukkitRunnable() {
            int a = 0;

            @Override
            public void run() {
                double gocc, x, z;
                gocc = 2 * PI * a / 20;
                x = Math.cos(gocc) * 6;
                z = Math.sin(gocc) * 6;
                location.add(x, 0, z);
                location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, location, 0, 0, 0, 0, 1);
                LightningStrike strike = location.getWorld().strikeLightning(location);
                strike.setInvulnerable(true);
                strike.setFireTicks(0);
                location.subtract(x, 0, z);
                if (a < 20) {
                    a++;
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 0);
    }


    public void checker() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (boss) {
                    if (miniBossDeath) {
                        for (Player player : players) {
                            Location location = player.getLocation();
                            List<String> locations = Manager.getLoc2(worldManager.getWorld());
                            for (String s : locations) {
                                int x = Integer.parseInt(s.split(" ")[0]);
                                int y = Integer.parseInt(s.split(" ")[1]);
                                int z = Integer.parseInt(s.split(" ")[2]);
                                Location aloc = new Location(worldManager.getWorld(), x, y, z);
                                int xx = location.getBlockX();
                                int yy = location.getBlockY();
                                int zz = location.getBlockZ();
                                Location ploc = new Location(worldManager.getWorld(), xx, yy, zz);
                                if (aloc.equals(ploc)) {
                                    aBoolean = true;
                                    startBoss();
                                    this.cancel();
                                }
                            }
                        }
                        if (aBoolean) {
                            for (Player player : players) {
                                player.teleport(Manager.getbossloc(worldManager.getWorld()));
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 20);
    }

    public void startBoss() {
        for (int i = 0; i < mapsConfig.boss.size(); i++) {
            String world = mapsConfig.boss.get(i).split(" ")[0];
            World realWorld = Bukkit.getWorld(world);
            int x = Integer.parseInt(mapsConfig.boss.get(i).split(" ")[1]);
            int y = Integer.parseInt(mapsConfig.boss.get(i).split(" ")[2]);
            int z = Integer.parseInt(mapsConfig.boss.get(i).split(" ")[3]);
            String mmName = mapsConfig.boss.get(i).split(" ")[4];
            Location location = new Location(worldManager.getWorld(), x, y, z);
            ActiveMob activeMob = MythicMobs.inst().getMobManager().spawnMob(mmName, new Location(
                    location.getWorld(), location.getX(), location.getY() + 1, location.getZ()
            ));
            bossMob.add(activeMob.getEntity().getUniqueId());
        }
        this.b = true;
    }

    public Player getMember(int i) {
        return players.get(i);
    }

    public Player getChuParty() {
        return players.get(0);
    }

    public void load() {
        checker();
        plugin.managerList.put(id, this);
        ActiveWorld.getGetActiveWorld().add(worldManager.getWorld().getName());
        file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Dungeons/" + id, "Dungeon.yml");
        yaml = YamlConfiguration.loadConfiguration(file);
        try {
            yaml.save(file);
            for (Player player : players) {
                yaml.set("Class." + player.getName() + ".class", Level.getPlayerClass(player));
            }
            yaml.set("Mobs", mapsConfig.customSpawn);
            yaml.set("DP1", "");
            yaml.set("DP2", "");
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClassBieuTuong(Player player) {
        String s = getConfig().getString("Class." + player.getName() + ".class");
        if (s.equals("SatThu")) {
            return "S";
        }
        if (s.equals("DoDon")) {
            return "D";
        }
        if (s.equals("PhapSu")) {
            return "P";
        }
        if (s.equals("XaThu")) {
            return "X";
        }
        if (s.equals("HoTro")) {
            return "H";
        }
        return null;
    }


    public void setBossKey(boolean key) {
        this.bossKey = key;
        this.boss = true;
    }

    public void stop() {
        //vip stop
        for (Player player : players) {
            PlayerInfo2 info2 = new PlayerInfo2(player);
            info2.SetManager(null);
            info2.setIg(false);
        }
    }

    public void stop2() {
        for (Player player : players) {
            PlayerInfo2 info2 = new PlayerInfo2(player);
            info2.SetManager(null);
            info2.setIg(false);
        }
    }

    public void removePlayer(Player player) {
        for (Player players : players) {
            for (String s : lang.getPlayerQuit()) {
                players.sendMessage(s.replace("<player>", player.getDisplayName()));
            }
        }
        players.remove(player);
    }

    public String getId() {
        return id;
    }

    public void exploded(Location location) {
        TinhRadius tinhRadius = new TinhRadius();
        tinhRadius.Radius(location);
        List<Location> listBlock = tinhRadius.blocks;

        for (Location value : listBlock) {
            value.getBlock().setType(Material.AIR);
            location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, value, 1);
        }
    }

    public void exploded2(Location location) {
        location.getBlock().setType(Material.AIR);
        location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, location, 1);
    }


    public void start(Player player) {
        MapsConfig mapsConfig = MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.get(worldDau);
        this.start = true;
        startStages(stages);
        player.closeInventory();
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
        info2.setIg(true);
        for (int i = 0; i < mapsConfig.chestParticle.size(); i++) {
            World world = worldManager.getWorld();
            int x = Integer.parseInt(mapsConfig.chestParticle.get(i).split(" ")[1]);
            int y = Integer.parseInt(mapsConfig.chestParticle.get(i).split(" ")[2]);
            int z = Integer.parseInt(mapsConfig.chestParticle.get(i).split(" ")[3]);
            Location location = new Location(world, x, (y + 1), z);
            Block block = location.getBlock();
            block.setType(Material.CHEST);
        }
        //load active block(doors);
        if (mapsConfig.getConfig().contains("Doors")) {
            List<String> list = mapsConfig.getConfig().getStringList("Doors");
            for (String s : list) {
                int stage = Integer.parseInt(s.split(" ")[0]);
                String world = s.split(" ")[1];
                World realworld = Bukkit.getWorld(world);
                int x = Integer.parseInt(s.split(" ")[2]);
                int y = Integer.parseInt(s.split(" ")[3]);
                int z = Integer.parseInt(s.split(" ")[4]);
                Location loc = new Location(realworld, x, y, z);
                TinhRadius tinhRadius = new TinhRadius();
                tinhRadius.Radius(loc);
                List<Location> blocks = tinhRadius.blocks;
                List<String> blocks1 = new ArrayList<>();
                List<String> blocks2 = new ArrayList<>();
                for (Location location : blocks) {
                    blocks1.add(location.toString());
                    blocks2.add(worldManager.getWorld().getName() + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());
                    this.getConfig().set("D1." + stage, blocks1);
                    this.getConfig().set("D2." + location.toString(), stage);
                    this.getConfig().set("D3." + stage, blocks2);
                    this.blocks.add(location);
                }
            }
        } else {
            cancel();
        }
        this.maxStage = mapsConfig.getConfig().getStringList("Doors").size();
        saveFile();
        if (mapsConfig.getConfig().contains("DoorsPhu")) {
            List<String> list = null;
            for (String string : list) {
                String world = string.split(" ")[0];
                World realWorld = Bukkit.getWorld(world);
                int x = Integer.parseInt(string.split(" ")[1]);
                int y = Integer.parseInt(string.split(" ")[2]);
                int z = Integer.parseInt(string.split(" ")[3]);
                Location location = new Location(realWorld, x, y, z);
                TinhRadius tinhRadius = new TinhRadius();
                tinhRadius.Radius(location);
                List<Location> blocks = tinhRadius.blocks; //TODO: loc;
                List<String> block2 = tinhRadius.blocks2; //TODO: String;
                blocksDoorPhu = tinhRadius.blocks;
                int dp = 0;
                if (getConfig().contains("DP1")) {
                    dp = getConfig().contains("DP1") ? getConfig().getConfigurationSection("DP1").getKeys(false).size() : 0;
                }
                getConfig().set("DP1." + dp, blocks);
                getConfig().set("DP2." + dp, block2);
                saveFile();
            }
        }
        saveFile();
    }

    public void cancel() {

    }

    public double getCleared() {
        int i = mapsConfig.customSpawn.size() - getConfig().getStringList("Mobs").size();
        return phanTramMoiMob * i;
    }

    public void startStagePhu(int stages) {

    }

    public void rewardMiniBoss() {
        List<String> blocks = Manager.getLoc2(worldManager.getWorld());
        for (String s : blocks) {
            int x = Integer.parseInt(s.split(" ")[0]);
            int y = Integer.parseInt(s.split(" ")[1]);
            int z = Integer.parseInt(s.split(" ")[2]);
            if (y == 69) {
                Location loc = new Location(worldManager.getWorld(), x, y, z);
                LightningStrike lightningStrike = loc.getWorld().strikeLightning(loc);
                lightningStrike.setInvulnerable(true);
            }
            for (Player player : players) {
                int oldScore = getScore().get(player);
                getScore().put(player,(oldScore + 60));
            }
        }
    }

    public void startStages(int stages) {
        MapsConfig mapsConfig = MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.get(worldDau);
        for (int i = 0; i < mapsConfig.customSpawn.size(); i++) {
            String loc = mapsConfig.getConfig().getStringList("CustomSpawn").get(i).split(";")[2];
            if (mapsConfig.getConfig().getStringList("CustomSpawn").get(i).split(";")[0].equalsIgnoreCase(String.valueOf(stages))) {
                World world = worldManager.getWorld();
                int x = Integer.parseInt(loc.split(" ")[1]);
                int y = Integer.parseInt(loc.split(" ")[2]);
                int z = Integer.parseInt(loc.split(" ")[3]);
                Location location = new Location(world, x, (y + 1), z);
                ActiveMob activeMob = MythicMobs.inst().getMobManager().spawnMob(mapsConfig.getConfig().getStringList("CustomSpawn").get(i).split(";")[1], location);
                mobsCanGiet.add(stages + ";" + activeMob.getEntity().getUniqueId());
            }
        }
    }


    public void dropKey(int stages, Location location, boolean boss) {
        location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, new Location(location.getWorld(), location.getX() + 0.5, location.getY(), location.getZ() + 0.5), 1, 0, 0, 0, 1);
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        if (!boss) {
            armorStand.setCustomName("§6Key Dùng Để Mở Cỏng #" + stages);
        } else {
            armorStand.setCustomName("§6Key Dùng Để Mở Cỏng Boss!");
        }
        armorStand.setCustomNameVisible(true);
        armorStand.setVisible(false);
        if (!boss) {
            armorStand.setHelmet(NormalKeyStack.getStack());
        } else {
            armorStand.setHelmet(BossKeyStack.getStack());
        }
        armorStand.setInvulnerable(true);
        armorStand.setCanPickupItems(true);
        armorStand.setGravity(false);
        new BukkitRunnable() {
            final World world = armorStand.getWorld();
            final int x = armorStand.getLocation().getBlockX();
            final int y = armorStand.getLocation().getBlockY();
            final int z = armorStand.getLocation().getBlockZ();
            final Location location = new Location(world, x, y, z);
            int tick = 0;

            @Override
            public void run() {
                EulerAngle eulerAngle = armorStand.getHeadPose();
                EulerAngle eulerAngle1 = eulerAngle.add(0, 0.1F, 0);
                armorStand.setHeadPose(eulerAngle1);
                for (Player player : players) {
                    Location playerloc = new Location(player.getWorld(),
                            player.getLocation().getBlockX(),
                            player.getLocation().getBlockY(),
                            player.getLocation().getBlockZ());
                    if (location.equals(playerloc)) {
                        armorStand.remove();
                        if (armorStand.getCustomName().contains("§6Key Dùng Để Mở Cỏng #")) {
                            keyid = stages;
                        } else if (armorStand.getCustomName().equals("§6Key Dùng Để Mở Cỏng Boss!")) {
                            keyid = stages;
                            setBossKey(true);
                        }
                        this.cancel();
                    }
                }
                if (armorStand.isDead()) {
                    if (armorStand.getCustomName().contains("§6Key Dùng Để Mở Cỏng #")) {
                        keyid = stages;
                    } else if (armorStand.getCustomName().equals("§6Key Dùng Để Mở Cỏng Boss!")) {
                        keyid = stages;
                        setBossKey(true);
                    }
                    this.cancel();
                }
                if (this.isCancelled()) {
                    for (Player player : players) {
                        player.sendMessage("test");
                    }
                }
                if (tick >= 1500) {
                    if (armorStand.getCustomName().contains("§6Key Dùng Để Mở Cỏng #")) {
                        keyid = stages;
                    } else if (armorStand.getCustomName().equals("§6Key Dùng Để Mở Cỏng Boss!")) {
                        keyid = stages;
                        setBossKey(true);
                    }
                    this.cancel();
                }
                tick++;
            }
        }.runTaskTimer(plugin, 0, 0);
        this.key = String.valueOf(stages);
    }

    public void waitToStart() {
        for (Player player : players) {
            player.teleport(Manager.spawmLoc(worldManager.getWorld()));
        }
        new BukkitRunnable() {
            int tick = 15;

            @Override
            public void run() {
                if (tick >= 0) {
                    tick--;
                }
                if (tick == 14) {
                    spawnDungeonManager(players.get(0));
                    CreateBoards boards = new CreateBoards();
                    boards.create(getThis);
//                    TAB tab = new TAB(getLeader(), TAB.SIZE_FOUR);
//                    tab.initTable();
//                    tab.updateSlot(3, "test");
//                    PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
//                    if (info2.getTab() == null) {
//                        info2.setTab(tab);
//                    }
                }
                if (tick < 15) {
                    for (Player player : players) {
                        player.sendMessage("§aDungeon Sẽ Bắt Đầu Trong " + (tick + 1) + "s");
                    }
                }
                if (tick == 0) {
//                    PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
//                    if (info2.getTab() != null) {
//                        TAB tab = info2.getTab();
//                        tab.clearAll();
//                    }
                    this.cancel();
                    exploded(Manager.wallLoc(worldManager.getWorld()));
                    for (Player player : players) {
                        start(player);
                    }
                    timeChecker();
                }
            }
        }.runTaskTimer(plugin, 0, 20L);
    }

    public void spawnNpc(Player pl) {
        try {
            NpcCreate.createDungeonNpc1(pl, Manager.npcLoc(worldManager.getWorld()), "Dungeon Manager");
            hideNpc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void spawnDungeonManager(Player player) {
        ZombieVillager zombieVillager = (ZombieVillager) player.getWorld().spawnEntity(Manager.npcLoc(worldManager.getWorld()),EntityType.ZOMBIE_VILLAGER);
        zombieVillager.setCustomName("§cDungeon Manager");
        zombieVillager.setCustomNameVisible(true);
        zombieVillager.setInvulnerable(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!Objects.equals(zombieVillager.getLocation(), Manager.npcLoc(worldManager.getWorld()))) {
                    zombieVillager.teleport(Manager.npcLoc(worldManager.getWorld()));
                }
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(),0,0);
    }

    public void hideNpc() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            NpcCreate.sendPacket(player);
        }
    }

    public YamlConfiguration getConfig() {
        return yaml;
    }

    public void saveFile() {
        try {
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBlocksDoorPhu(List<Location> blocksDoorPhu) {
        this.blocksDoorPhu = blocksDoorPhu;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public void setChestDaMo(List<Location> chestDaMo) {
        this.chestDaMo = chestDaMo;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setGetThis(DungeonManager getThis) {
        this.getThis = getThis;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setMapsConfig(MapsConfig mapsConfig) {
        this.mapsConfig = mapsConfig;
    }

    public void setKeyid(Integer keyid) {
        this.keyid = keyid;
    }

    public void setYaml(YamlConfiguration yaml) {
        this.yaml = yaml;
    }

    public void setLootchestList(List<Location> lootchestList) {
        this.lootchestList = lootchestList;
    }

    public void setMaxStage(int maxStage) {
        this.maxStage = maxStage;
    }

    public void setMiniBossDeath(boolean miniBossDeath) {
        this.miniBossDeath = miniBossDeath;
    }

    public void setMobsize(double mobsize) {
        this.mobsize = mobsize;
    }

    public void setMobsCanGiet(List<String> mobsCanGiet) {
        this.mobsCanGiet = mobsCanGiet;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setPhanTramMoiMob(double phanTramMoiMob) {
        this.phanTramMoiMob = phanTramMoiMob;
    }

    public void setStages(int stages) {
        this.stages = stages;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setWorldDau(String worldDau) {
        this.worldDau = worldDau;
    }

    public void setWorldFolderDirec(String worldFolderDirec) {
        this.worldFolderDirec = worldFolderDirec;
    }

    public void setWorldManager(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    public File getFile() {
        return file;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean isBoss() {
        return boss;
    }

    public boolean isBossKey() {
        return bossKey;
    }

    public boolean isMiniBossDeath() {
        return miniBossDeath;
    }

    public boolean isStart() {
        return start;
    }

    public double getMobsize() {
        return mobsize;
    }

    public double getPhanTramMoiMob() {
        return phanTramMoiMob;
    }

    public DungeonManager getGetThis() {
        return getThis;
    }

    public int getMaxStage() {
        return maxStage;
    }

    public int getStages() {
        return stages;
    }

    public Integer getKeyid() {
        return keyid;
    }

    public List<Location> getBlocks() {
        return blocks;
    }

    public List<Location> getBlocksDoorPhu() {
        return blocksDoorPhu;
    }

    public List<Location> getChestDaMo() {
        return chestDaMo;
    }

    public List<Location> getLootchestList() {
        return lootchestList;
    }

    public List<String> getMobsCanGiet() {
        return mobsCanGiet;
    }

    public MagicsDungeonPhakeChoMC2LORD getPlugin() {
        return plugin;
    }

    public MapsConfig getMapsConfig() {
        return mapsConfig;
    }

    public String getKey() {
        return key;
    }

    public String getWorldDau() {
        return worldDau;
    }

    public String getWorldFolderDirec() {
        return worldFolderDirec;
    }

    @Override
    public String toString() {
        return "DungeonManager{" +
                "getThis=" + getThis +
                ", id='" + id + '\'' +
                ", file=" + file +
                ", players=" + players +
                ", start=" + start +
                ", worldManager=" + worldManager +
                ", worldDau='" + worldDau + '\'' +
                ", yaml=" + yaml +
                ", stages=" + stages +
                ", lootchestList=" + lootchestList +
                ", chestDaMo=" + chestDaMo +
                ", worldFolderDirec='" + worldFolderDirec + '\'' +
                ", mobsCanGiet=" + mobsCanGiet +
                ", key='" + key + '\'' +
                ", keyid=" + keyid +
                ", blocks=" + blocks +
                ", blocksDoorPhu=" + blocksDoorPhu +
                ", maxStage=" + maxStage +
                ", boss=" + boss +
                ", bossKey=" + bossKey +
                ", miniBossDeath=" + miniBossDeath +
                ", mapsConfig=" + mapsConfig +
                ", mobsize=" + mobsize +
                ", phanTramMoiMob=" + phanTramMoiMob +
                ", times=" + times +
                ", plugin=" + plugin +
                '}';
    }

    public boolean isB() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DungeonManager manager = (DungeonManager) o;
        return start == manager.start && stages == manager.stages && maxStage == manager.maxStage && boss == manager.boss && bossKey == manager.bossKey && miniBossDeath == manager.miniBossDeath && b == manager.b && Double.compare(manager.mobsize, mobsize) == 0 && Double.compare(manager.phanTramMoiMob, phanTramMoiMob) == 0 && times == manager.times && Objects.equals(getThis, manager.getThis) && Objects.equals(id, manager.id) && Objects.equals(file, manager.file) && Objects.equals(players, manager.players) && Objects.equals(worldManager, manager.worldManager) && Objects.equals(worldDau, manager.worldDau) && Objects.equals(yaml, manager.yaml) && Objects.equals(lootchestList, manager.lootchestList) && Objects.equals(chestDaMo, manager.chestDaMo) && Objects.equals(worldFolderDirec, manager.worldFolderDirec) && Objects.equals(mobsCanGiet, manager.mobsCanGiet) && Objects.equals(key, manager.key) && Objects.equals(keyid, manager.keyid) && Objects.equals(blocks, manager.blocks) && Objects.equals(blocksDoorPhu, manager.blocksDoorPhu) && Objects.equals(bossMob, manager.bossMob) && Objects.equals(mapsConfig, manager.mapsConfig) && Objects.equals(armorstandRe, manager.armorstandRe) && Objects.equals(plugin, manager.plugin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getThis, id, file, players, start, worldManager, worldDau, yaml, stages, lootchestList, chestDaMo, worldFolderDirec, mobsCanGiet, key, keyid, blocks, blocksDoorPhu, bossMob, maxStage, boss, bossKey, miniBossDeath, b, mapsConfig, mobsize, phanTramMoiMob, times, armorstandRe, plugin);
    }

    public List<UUID> getBossMob() {
        return bossMob;
    }

    public Map<Integer, Player> getArmorstandRe() {
        return armorstandRe;
    }

    public void setArmorstandRe(Map<Integer, Player> armorstandRe) {
        this.armorstandRe = armorstandRe;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public void setBlocks(List<Location> blocks) {
        this.blocks = blocks;
    }

    public void setBossMob(List<UUID> bossMob) {
        this.bossMob = bossMob;
    }

    public YamlConfiguration getYaml() {
        return yaml;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public List<Player> getNeedRefIGG1() {
        return needRefIGG1;
    }

    public List<Player> getDaMoEndLoot() {
        return daMoEndLoot;
    }

    public Map<Player, Integer> getScore() {
        return score;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public void setDaMoEndLoot(List<Player> daMoEndLoot) {
        this.daMoEndLoot = daMoEndLoot;
    }

    public void setNeedRefIGG1(List<Player> needRefIGG1) {
        this.needRefIGG1 = needRefIGG1;
    }

    public void setScore(Map<Player, Integer> score) {
        this.score = score;
    }
}































































