package allButBag;

import java.util.ArrayList;
import java.util.List;

public class SolitaryConfinementGroup {
    private String punishment;
    private List<Smerf> members = new ArrayList<>();

    public void addMember(Smerf smerf) {
        if (smerf == null) {
            throw new IllegalArgumentException("Smerf cannot be null");
        }
        if (members.contains(smerf)) {
            throw new IllegalArgumentException("Cannot add a smerf a second time to the solitary confinement groups");
        }
        if (smerf.getSolitaryConfinementGroup() != null) {
            throw new IllegalArgumentException("Smerf cannot be in two different solitary confinement groups");
        }
        if (smerf.getScoutGroup() != null) { // XOR
            throw new IllegalArgumentException("Smerf cannot be in both scoutGroup and solitaryConfinementGroup");
        }
        members.add(smerf);
        if (smerf.getSolitaryConfinementGroup() != this) {
            smerf.setSolitaryConfinementGroup(this);
        }
    }

    public void removeMember(Smerf smerf) {
        if (smerf == null) {
            throw new IllegalArgumentException("Cannot remove null Smerf");
        }

        if (!members.contains(smerf)) {
            throw new IllegalArgumentException("Cannot remove a smerf from a solitary confinement group that is not in that group");
        }

        this.members.remove(smerf);

        if (smerf.getSolitaryConfinementGroup() != null) {
            smerf.setSolitaryConfinementGroup(null);
        }
    }

    public List<Smerf> getMembers() {
        return new ArrayList<>(members);
    }

    public void setPunishment(String punishment) {
        if (punishment == null || punishment.strip().isEmpty()) {
            throw new IllegalArgumentException("punishment cannot be null or empty");
        }
        this.punishment = punishment;
    }

    public String getPunishment() {
        return this.punishment;
    }

}
