package withAttribute;

import java.time.LocalDate;

public class Participation {
    private Cook cook;
    private Feast feast;

    private LocalDate startAt;

    public Participation(Cook cook, Feast feast, LocalDate startAt) {
        if (cook == null || feast == null || startAt == null) {
            throw new IllegalArgumentException("Cook, feast and startAt cannot be null");
        }

        if (cook.getParticipations().contains(this) || feast.getParticipations().contains(this)) {
            throw new IllegalArgumentException("No duplicate Partipactions");
        }

        for (Participation p : cook.getParticipations()) {
            if (p.getFeast() == feast) {
                throw new IllegalArgumentException("No duplicate Partipactions");
            }
        }

        this.startAt = startAt;
        setCook(cook);
        setFeast(feast);
    }

    public Cook getCook() {
        return cook;
    }

    public Feast getFeast() {
        return feast;
    }

    public LocalDate getDate() {
        return startAt;
    }

    //its not necessary to change the side of this relation, so it can be private method
    //but if its public it needs to be implemented properly (for switching the employee)
    private void setCook(Cook cook) {
        this.cook = cook;
        if (!cook.getParticipations().contains(this)) {
            cook.addParticipation(this);
        }
    }

    private void setFeast(Feast feast) {
        this.feast = feast;
        if (!feast.getParticipations().contains(this)) {
            feast.addParticipation(this);
        }
    }
}
