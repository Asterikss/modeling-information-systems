package composition;

import java.util.HashSet;
import java.util.Set;

public class Assignment {

    private static Set<Assignment> extent = new HashSet<>();
    private String description;

    private ScoutGroup owner;

    public Assignment(String description, ScoutGroup owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null");
        }
        setDescription(description);
        setOwner(owner);
        extent.add(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.strip().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description;
    }

    public ScoutGroup getOwner() {
        return owner;
    }

    public void setOwner(ScoutGroup s1) {
        if(this.owner == s1) {
            return;
        }
        if (s1 == null) { // previous owner not null
            ScoutGroup prev_owner = this.owner;
            this.owner = null;
            if (prev_owner.getAssignments().contains(this)) {
                prev_owner.removeAssignment(this);
            }
            extent.remove(this);
        } else {
            if (this.owner != null) {
                this.owner.removeAssignment(this);
            }
            s1.addAssignment(this);
            this.owner = s1;
        }
    }

    public static Set<Assignment> getExtent() {
        return new HashSet<>(extent);
    }

    // for tests
    public static void resetExtent() {
        extent.clear();
    }
}

