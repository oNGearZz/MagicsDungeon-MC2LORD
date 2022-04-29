package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Storage.ArrayListStorge;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import javax.xml.stream.Location;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class WorldManager {

    public String id = String.valueOf(System.currentTimeMillis());

    public World getWorld() {
        return world;
    }

    private World world;
    private final String directory = MagicsDungeonPhakeChoMC2LORD.getInstance().getServer().getWorldContainer().getAbsolutePath();

    public void StartGenerator(String wname) {
        backup(wname);
        delete2();
        this.world = Bukkit.createWorld(new WorldCreator(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Dungeons/" + id));
    }

    private void createFolder() {
        try {
            Files.createDirectories(Paths.get(directory + "/MagicsDungeonPhakeChoMC2LORD/Dungeons/" + id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean unloadWorld() {
        return world != null && Bukkit.getServer().unloadWorld(world, false);
    }

    public void copyWorld(String map) {
        try {
            copyFileStructure(new File(directory + "/" + map), new File(directory + "/plugins/MagicsDungeonPhakeChoMC2LORD/Dungeons/" + id, id));
            new WorldCreator(directory + "/plugins/MagicsDungeonPhakeChoMC2LORD/Dungeons/" + id).createWorld();
            world = getWorld(directory + "/plugins/MagicsDungeonPhakeChoMC2LORD/Dungeons/" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public World getWorld(String newWorldName) {
        return Bukkit.getWorld(newWorldName);
    }

    private void backup(String map) {
        File srcFolder = new File(directory + "/" + map);
        File destFolder = new File(directory + "/plugins/MagicsDungeonPhakeChoMC2LORD/Dungeons/" + id);
        delete(destFolder);
        try {
            copyFolder(srcFolder, destFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete2() {
        try {
            Files.delete(Paths.get(directory + "/plugins/MagicsDungeonPhakeChoMC2LORD/Dungeons/" + id + "/session.lock"));
            Files.delete(Paths.get(directory + "/plugins/MagicsDungeonPhakeChoMC2LORD/Dungeons/" + id + "/uid.dat"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(File delete) {
        if (delete.isDirectory()) {
            String[] files = delete.list();
            if (files != null)
                for (String file : files) {
                    File toDelete = new File(file);
                    delete(toDelete);
                }
        } else {
            delete.delete();
        }
    }

    private void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists())
                dest.mkdir();
            String[] files = src.list();
            if (files != null)
                for (String file : files) {
                    File srcFile = new File(src, file);
                    File destFile = new File(dest, file);
                    copyFolder(srcFile, destFile);
                }
        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0)
                out.write(buffer, 0, length);
            in.close();
            out.close();
        }
    }

    private void copyFileStructure(File source, File target) {
        try {
            ArrayList<String> ignore = new ArrayList<>(Arrays.asList("uid.dat", "session.lock"));
            if (!ignore.contains(source.getName())) {
                if (source.isDirectory()) {
                    if (!target.exists())
                        if (!target.mkdirs())
                            throw new IOException("Couldn't create world directory!");
                    String files[] = source.list();
                    for (String file : files) {
                        File srcFile = new File(source, file);
                        File destFile = new File(target, file);
                        copyFileStructure(srcFile, destFile);
                    }
                } else {
                    InputStream in = new FileInputStream(source);
                    OutputStream out = new FileOutputStream(target);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                        out.write(buffer, 0, length);
                    in.close();
                    out.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
