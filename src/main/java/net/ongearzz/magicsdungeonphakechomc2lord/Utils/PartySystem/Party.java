package net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem;

import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Storage.HashMapStorge;

import java.util.ArrayList;
import java.util.List;

public class Party {
    private String partyID;

    private String leader;

    private List<String> member = new ArrayList<>();

    public Party(String leader) {
        this.leader = leader;
        this.partyID = String.valueOf(System.currentTimeMillis());
    }

    public List<String> getMember() {
        return member;
    }

    public void load() {
        HashMapStorge.getInstance().getParty().put(leader, this);
    }

    public String getPartyID() {
        return partyID;
    }

    public String getLeader() {
        return leader;
    }

    public void createParty(String chuParty) {
        member.add(chuParty);
        // id = 0;
    }

    public void addMember(String mem) {
        member.add(mem);
    }
}
