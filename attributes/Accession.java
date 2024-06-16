import java.time.LocalDate;
import java.io.Serializable;

public class Accession implements Serializable {

    private LocalDate dateOfAccession;
    private Stork stork;

    public Accession(Stork stork) {
        this.dateOfAccession = LocalDate.now();
        this.stork = stork;
    }

    public Accession(Stork stork, LocalDate dateOfAccession) {
        this.dateOfAccession = dateOfAccession;
        this.stork = stork;
    }

    public LocalDate getDateOfAccession() {
        return this.dateOfAccession;
    }

    public Stork getAccessionStork() {
        return this.stork;
    }
}
