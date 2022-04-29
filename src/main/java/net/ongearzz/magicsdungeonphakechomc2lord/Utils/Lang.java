package net.ongearzz.magicsdungeonphakechomc2lord.Utils;

import com.sun.org.apache.xpath.internal.objects.XString;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lang {
    public File file;
    public YamlConfiguration getConfig() {
        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder(), "Lang.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        this.file = file;
        return configuration;
    }
    public static void load() {
        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder(), "Lang.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            try {
                List<String> playerQuit = new ArrayList<>();
                playerQuit.add("<player> da thoat khoi dungeon!");
                List<String> party1 = new ArrayList<>();
                party1.add("Bạn Chưa Có Party Để Invite!");
                List<String> party2 = new ArrayList<>();
                party2.add("Ban Ko The Moi Chinh Ban Vao Party!");
                List<String> party3 = new ArrayList<>();
                party3.add("Người Chơi Này Đang Ngoại Tuyến!");
                List<String> party4 = new ArrayList<>();
                party4.add("PartyCreateThanhCong");
                List<String> party5 = new ArrayList<>();
                party5.add("Bạn Đã Có Party!");
                List<String> party6 = new ArrayList<>();
                party6.add("PartyDisbandThanhCong");
                List<String> party7 = new ArrayList<>();
                party7.add("Bạn Không Phải Chủ Party!");
                List<String> party8 = new ArrayList<>();
                party8.add("Ban Chưa Có Party Để Thực Hiện Điều Này!");
                List<String> party9 = new ArrayList<>();
                party9.add("<player> Đã Tham Gia Vào Party!!!!!");
                List<String> party10 = new ArrayList<>();
                party10.add("Party Này Không Dành Cho Cộng Đồng! (ko ở trong chế độ queue)");
                List<String> party11 = new ArrayList<>();
                party11.add("<player> đã thoát khỏi party!");
                List<String> party12 = new ArrayList<>();
                party12.add("<player> đã bị kick khỏi party!");
                List<String> party13 = new ArrayList<>();
                party13.add("Chi Party Leader Moi Su Dung Duoc Lenh Nay!");
                List<String> party14 = new ArrayList<>();
                party14.add("Nguoi Nay Khong Ton Tai Trong Party");
                List<String> party15 = new ArrayList<>();
                party15.add("<leaderCu> đã chuyển quyền leader cho <leaderMoi>!");
                List<String> party16 = new ArrayList<>();
                party16.add("Party Cua Ban Da Dc Mute!");
                List<String> party17 = new ArrayList<>();
                party17.add("Party Cua Ban Da Dc Bo Mute!");
                List<String> party18 = new ArrayList<>();
                party18.add("/dpc + [message]");
                List<String> party19 = new ArrayList<>();
                party19.add("&6&m---------------");
                party19.add("&aLeader: <leader>");
                party19.add("&aMembers: ");
                party19.add("&7-&a <players>");
                party19.add("&6&m---------------");
                List<String> party20 = new ArrayList<>();
                party20.add("&6&m---------------");
                party20.add("party cua <leader> đang queue!");
                party20.add("<join>");
                party20.add("hoac su dung lenh:");
                party20.add("/dp join <leader>!");
                party20.add("&6&m---------------");
                List<String> party21 = new ArrayList<>();
                party21.add("&6&m---------------");
                party21.add("&7-§a <sender> Mời Bạn Tham Gia Vào");
                party21.add("&7- §aParty Của <leader>!");
                party21.add("<join>");
                party21.add("&7- §a Hoặc Sử Dụng Lệnh: /DP join <leader>");
                party21.add("&6&m---------------");
                List<String> party22 = new ArrayList<>();
                party22.add("&6&m---------------");
                party22.add("&7-§a <leader> Mời Bạn Tham Gia Vào");
                party22.add("&7- §aParty Của Họ!");
                party22.add("<join>");
                party22.add("&7- §a Hoặc Sử Dụng Lệnh: /DP join <leader>");
                party22.add("&6&m---------------");
                List<String> party23 = new ArrayList<>();
                party23.add("&6&m---------------");
                party23.add("&a<senderXleader> đã mời <player> vào party!");
                party23.add("&6&m---------------");
                List<String> party24 = new ArrayList<>();
                party24.add("&6&m---------------");
                party24.add("&8- &a/DP Create &8-&7");
                party24.add("&8- &a/DP join + <Party> &8-&7");
                party24.add("&8- &a/DP list &8-&7");
                party24.add("&8- &a/DP disband &8-&7");
                party24.add("&8- &a/DP invite + <player> &8-&7");
                party24.add("&6&m---------------");
                List<String> party25 = new ArrayList<>();
                party25.add("PartyCreateKoThanhCong!");
                List<String> party26 = new ArrayList<>();
                party26.add("Party ko Tồn Tại!");
                List<String> party27 = new ArrayList<>();
                party27.add("<player> bây giờ sẽ là leader mới!");
                List<String> party28 = new ArrayList<>();
                party28.add("người này đã tồn tại trong party!");

                configuration.set("ig.ENDLootChest.RewardChestTitle", "&6Reward Chest");
                configuration.set("ig.ENDLootChest.RewardChestError", "Ban Da Mo LootChest Roi!");
                configuration.set("ig.PlayerQuit", playerQuit);
                configuration.set("ig.keyError", "ban Chua Co Key De Mo Door Nay!");
                configuration.set("ig.MoDoorThanhCong", "Mở Thành Công Cửa # <stage>");
                configuration.set("ig.MoDoorBossThanhCong", "Mở Thành Công Cửa Boss!");

                configuration.set("party.KoCoPartyDeInvite",party1);
                configuration.set("party.PartyCreateThanhCong",party4);
                configuration.set("party.PartyDisbandThanhCong", party6);
                configuration.set("party.ChuaCoParty", party8);
                configuration.set("party.chatHelp", party18);
                configuration.set("party.list", party19);
                configuration.set("party.queue", party20);
                configuration.set("party.MemberInvite", party21);
                configuration.set("party.LeaderInvite", party22);
                configuration.set("party.ThongBaoDaInviteThanhCong", party23);
                configuration.set("party.helpList", party24);
                configuration.set("party.PartyCreateKoThanhCong", party25);
                configuration.set("party.notFound", party26);

                configuration.set("party.a", party2);
                configuration.set("party.b", party3);
                configuration.set("party.c", party7);
                configuration.set("party.d", party9);
                configuration.set("party.e", party10);
                configuration.set("party.f", party11);
                configuration.set("party.g", party12);
                configuration.set("party.h", party13);
                configuration.set("party.i", party14);
                configuration.set("party.j", party15);
                configuration.set("party.k", party16);
                configuration.set("party.l", party17);
                configuration.set("party.m", party5);
                configuration.set("party.n", party27);
                configuration.set("party.o", party28);

                configuration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getNguoiNayDaTonTaiTrongParty() {
        return getConfig().getStringList("party.o");
    }

    public List<String> getPartyNotFound() {
        return getConfig().getStringList("party.notFound");
    }
    public List<String> getPartyCreateKoThanhCong() {
        return getConfig().getStringList("party.PartyCreateKoThanhCong");
    }
    public List<String> getHelpList() {
        return getConfig().getStringList("party.helpList");
    }
    public List<String> getA() {
        return getConfig().getStringList("party.a");
    }
    public List<String> getB() {
        return getConfig().getStringList("party.b");
    }
    public List<String> getC() {
        return getConfig().getStringList("party.c");
    }
    public List<String> getD() {
        return getConfig().getStringList("party.d");
    }
    public List<String> getE() {
        return getConfig().getStringList("party.e");
    }
    public List<String> getF() {
        return getConfig().getStringList("party.f");
    }
    public List<String> getG() {
        return getConfig().getStringList("party.g");
    }
    public List<String> getH() {
        return getConfig().getStringList("party.h");
    }
    public List<String> getI() {
        return getConfig().getStringList("party.i");
    }
    public List<String> getJ() {
        return getConfig().getStringList("party.j");
    }
    public List<String> getK() {
        return getConfig().getStringList("party.k");
    }
    public List<String> getL() {
        return getConfig().getStringList("party.l");
    }
    public List<String> getM() {
        return getConfig().getStringList("party.m");
    }
    public List<String> getN() {
        return getConfig().getStringList("party.n");
    }
    public List<String> getThongBaoDaInviteThanhCong() {
        return getConfig().getStringList("party.ThongBaoDaInviteThanhCong");
    }
    public List<String> getLeaderInvite() {
        return getConfig().getStringList("party.LeaderInvite");
    }
    public List<String> getMemberInvite() {
        return getConfig().getStringList("party.MemberInvite");
    }
    public List<String> getqueue() {
        return getConfig().getStringList("party.queue");
    }
    public List<String> getlist() {
        return getConfig().getStringList("party.list");
    }
    public List<String> getchatHelp() {
        return getConfig().getStringList("party.chatHelp");
    }
    public List<String> getChuaCoParty() {
        return getConfig().getStringList("party.ChuaCoParty");
    }
    public List<String> getKoCoPartyDeInvite() {
        return getConfig().getStringList("party.KoCoPartyDeInvite");
    }
    public List<String> getPartyCreateThanhCong() {
        return getConfig().getStringList("party.PartyCreateThanhCong");
    }
    public List<String> getPartyDisbandThanhCong() {
        return getConfig().getStringList("party.PartyDisbandThanhCong");
    }
    public String getRewarChestTitle() {
        return Colored.msgColor(getConfig().getString("ig.ENDLootChest.RewardChestTitle"));
    }
    public String getRewardChestError() {
        return Colored.msgColor(getConfig().getString("ig.ENDLootChest.RewardChestError"));
    }
    public List<String> getPlayerQuit() {
        return getConfig().getStringList("ig.PlayerQuit");
    }
    public String getKeyError() {
        return Colored.msgColor(getConfig().getString("ig.keyError"));
    }
    public String getMoDoorThanhCong(int stage) {
        return Colored.msgColor(getConfig().getString("ig.MoDoorThanhCong")).replace("<stage>",String.valueOf(stage));
    }
    public String getMoCuaBossThanhCong() {
        return Colored.msgColor(getConfig().getString("ig.MoDoorBossThanhCong"));
    }

}
