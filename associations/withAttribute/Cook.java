package withAttribute;

import java.util.HashSet;
import java.util.Set;

public class Cook {
    private String name;

    private Set<Participation> participations = new HashSet<>();

    public Cook(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public Set<Participation> getParticipations() {
        return new HashSet<>(participations);
    }

    public void addParticipation(Participation p) {
        if (p == null) {
           throw new IllegalArgumentException("Participation cannot be null");
        }
        if (p.getCook() != this) {
            throw new IllegalArgumentException("Participation cannot be reassigned");
        }
        if (!participations.contains(p)) {
            participations.add(p);
        }

    }

    public void removeParticipation(Participation p) {
        if (p == null) {
            throw new IllegalArgumentException("Participation cannot be null");
        }
        if (!participations.contains(p)) {
            throw new IllegalArgumentException("Cannot remove a participation that is not in the participations list");

        }
        participations.remove(p);
        if (p.getFeast() != null && p.getFeast().getParticipations().contains(p)) {
            p.getFeast().removeParticipation(p);
        }
    }
}
