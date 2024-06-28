package composition;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ScoutGroup {
    private String name;

    private Set<Assignment> assignments = new HashSet<>();

    public ScoutGroup(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.strip().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public Set<Assignment> getAssignments() {
        return Collections.unmodifiableSet(assignments);
    }

    public void addAssignment(Assignment t1) {
        if (t1.getOwner() != null && t1.getOwner() != this) {
            throw new IllegalArgumentException("Cannot reassign an assignment");
        }
        if (t1.getOwner() == null) {
            assignments.add(t1);
        }

    }

    public void removeAssignment(Assignment t1) {
        if (t1 == null) {
            throw new IllegalArgumentException("Cannot remove null assignment");
        }
        if (t1.getOwner() != null) {
            t1.setOwner(null);
        }
        this.assignments.remove(t1);
    }
}
