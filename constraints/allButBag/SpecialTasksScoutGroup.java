package allButBag;

import java.util.ArrayList;
import java.util.List;

public class SpecialTasksScoutGroup {
    private String specialMissionDescription;
    private ScoutGroup parentScoutGroup;
    private List<Smerf> specialMembers = new ArrayList<>();

    public SpecialTasksScoutGroup(ScoutGroup parentGroup) {
        if (parentGroup == null) {
            throw new IllegalArgumentException("parent group cannot be null");
        }
        this.parentScoutGroup = parentGroup;
        if (!parentGroup.getSubgroups().contains(this)) {
            parentGroup.addSubgroup(this);
        }
    }

    public void addSpecialMember(Smerf smerf) {
        if (!parentScoutGroup.getMembers().contains(smerf)) {
            throw new IllegalArgumentException("Smerf must be a member of the parent scout group before being added to the special group");
        }
        if (!specialMembers.contains(smerf)) {
            specialMembers.add(smerf);
        }
    }

    public void removeSpecialMember(Smerf smerf) {
        if (smerf == null) {
            throw new IllegalArgumentException("Null smerf cannot be removed");
        }

        specialMembers.remove(smerf);

        if (parentScoutGroup.getMembers().contains(smerf)) {
            parentScoutGroup.removeMember(smerf);
        }
    }

    public List<Smerf> getSpecialMembers() {
        return new ArrayList<>(specialMembers);
    }

    public ScoutGroup getParent() {
        return this.parentScoutGroup;
    }

    public void setSpecialMissionDescription(String specialMissionDescription) {
        if (specialMissionDescription == null || specialMissionDescription.strip().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.specialMissionDescription = specialMissionDescription;
    }

    public String getSpecialMissionDescription() {
        return this.specialMissionDescription;
    }
    
}
