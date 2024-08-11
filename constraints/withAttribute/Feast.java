package withAttribute;

import java.util.HashSet;
import java.util.Set;

public class Feast {
    private String name;

    private Set<Participation> participations = new HashSet<>();

    public Feast(String name) {
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
        if (p.getFeast() != this) {
            throw new IllegalArgumentException("Participation cannot be reassigned");
        }
        if (!participations.contains(p)) {
            participations.add(p);
        }

    }

    public void removeParticipation(Participation p) {
        participations.remove(p);
        if (p.getCook() != null && p.getCook().getParticipations().contains(p)) {
            p.getCook().removeParticipation(p);
        }

    }
}
