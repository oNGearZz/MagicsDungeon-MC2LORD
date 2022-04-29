package net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Colored;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class Party2 {
    private UUID id;

    private Player leader;

    private String name;

    private Map<UUID, Player> members;

    private Map<UUID, Player> invitations;

    private List<UUID> players = new ArrayList<>();

    private List<Player> inInvites = new ArrayList<>();

    private boolean allinv;

    private boolean inQueue;

    private boolean mute;

    public Party2(Player leader, String name) {
        this(leader, name, UUID.randomUUID());
    }

    public List<Player> getInInvites() {
        return inInvites;
    }

    public Party2(Player leader, String name, UUID id) {
        this.id = id;
        this.leader = leader;
        this.name = name;
        this.members = new HashMap<>();
        this.invitations = new HashMap<>();
    }

    public void load() {
        members.put(leader.getUniqueId(), leader);
        players.add(leader.getUniqueId());
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public boolean isInQueue() {
        return inQueue;
    }

    public void setInQueue(boolean inQueue) {
        this.inQueue = inQueue;
    }

    public boolean isAllinv() {
        return allinv;
    }

    public void setAllinv(boolean allinv) {
        this.allinv = allinv;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public UUID getID() {
        return this.id;
    }

    public Player getLeader() {
        return this.leader;
    }

    public void setLeader(Player leader) {
        this.leader = leader;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(Player member) {
        this.members.put(member.getUniqueId(), member);
        this.players.add(member.getUniqueId());
        MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(member).setParty(this);
    }

    public void addMembers(List<Player> members) {
        for (Player member : members)
            addMember(member);
    }

    public void removeMember(UUID member) {
        this.members.remove(member);
        this.players.remove(member);
        MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(Bukkit.getPlayer(member)).setParty(null);
    }

    public void removeMembers() {
        this.members.clear();
    }
    public void disband() {
        this.members.clear();
        this.players.clear();
        for (UUID uuid : players) {
            Player player = Bukkit.getPlayer(uuid);
            PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
            info2.setParty(null);
        }
        this.setLeader(null);
    }

    public Collection<Player> getMembers() {
        return Collections.unmodifiableCollection(this.members.values());
    }


    public Player getMember(UUID uuid) {
        if (this.leader.getUniqueId().equals(uuid))
            return this.leader;
        return this.members.get(uuid);
    }

    public Player getMember(String name) {
        if (this.leader.getName().equals(name))
            return this.leader;
        for (Player member : this.members.values()) {
            if (member.getName().equals(name))
                return member;
        }
        return null;
    }

    public void addInvitation(Player member) {
        this.invitations.put(member.getUniqueId(), member);
    }

    public void removeInvitation(UUID uuid) {
        this.invitations.remove(uuid);
    }

    public Player getInvitation(UUID uuid) {
        return (Player)this.invitations.get(uuid);
    }

    public void sendMessage(String message) {
        this.leader.sendMessage(message);
        for (UUID uuid : players) {
            Player player = Bukkit.getPlayer(uuid);
            player.sendMessage(Colored.msgColor(message));
        }
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Party2))
            return false;
        Party2 party = (Party2)o;
        return Objects.equals(this.id, party.id);
    }

    public int hashCode() {
        return Objects.hash(new Object[] { this.id });
    }
}
