package allButBag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class ScoutGroup {
    private String missionDescription;
    private List<Smerf> members = new ArrayList<>();
    private Set<SpecialTasksScoutGroup> subgroups = new HashSet<>();

    public void addMember(Smerf smerf) {
        if (smerf == null) {
            throw new IllegalArgumentException("Smerf cannot be null");
        }
        if (members.contains(smerf)) {
            throw new IllegalArgumentException("Cannot add a smerf a second time to the ScoutGroup");
        }
        if (smerf.getScoutGroup() != null) {
            throw new IllegalArgumentException("Smerf cannot be in two different scout groups");
        }
        if (smerf.getSolitaryConfinementGroup() != null) { // XOR
            throw new IllegalArgumentException("Smerf cannot be in both scoutGroup and solitaryConfinementGroup");
        }
        members.add(smerf);
        if (smerf.getScoutGroup() != this) {
            smerf.setScoutGroup(this);
        }
    }
    public void removeMember(Smerf smerf) {
        if (smerf == null) {
            throw new IllegalArgumentException("Cannot remove null Smerf");
        }

        if (!members.contains(smerf)) {
            throw new IllegalArgumentException("Cannot remove a smerf from a scout group that is not in that group");
        }

        this.members.remove(smerf);

        for (SpecialTasksScoutGroup subgroup : subgroups) {
            subgroup.removeSpecialMember(smerf);
        }

        if (smerf.getScoutGroup() != null) {
            smerf.setScoutGroup(null);
        }
    }

    public void addSubgroup(SpecialTasksScoutGroup subgroup) {
        if (subgroup == null) {
            throw new IllegalArgumentException("Cannot add null subgroup");
        }
        if (subgroup.getParent() != this) {
            throw new IllegalArgumentException("Cannot reassign an assignment");
        }
        subgroups.add(subgroup);
    }
    
    public List<Smerf> getMembers() {
        return new ArrayList<Smerf>(members);
    }

    public Set<SpecialTasksScoutGroup> getSubgroups() {
        return new HashSet<SpecialTasksScoutGroup>(subgroups);
    }

    public void setMissionDescription(String missionDescription) {
        if (missionDescription == null || missionDescription.strip().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.missionDescription = missionDescription;
    }

    public String getMissionDescription() {
        return this.missionDescription;
    }
}
