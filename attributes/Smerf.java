import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class Smerf implements Serializable {

    private static final String SKINCOLOR = "kobaltowy";
    private static List<Smerf> smerfList = new ArrayList<>();

    private String name;
    private Accession accession;

    public Smerf(String name, Stork stork, String newSmerfSkinColor) {
        if (!SKINCOLOR.equals(newSmerfSkinColor)) {
            throw new IllegalArgumentException("Wrong skin color!!!");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        for (Smerf smerf : smerfList) {
            if (smerf.name.equals(name)) {
                throw new IllegalArgumentException("Name must be unique");
            }
        }
        this.accession = new Accession(stork);
        this.name = name;
        smerfList.add(this);
    }

    public Smerf(String name, Stork stork, String newSmerfSkinColor, LocalDate dateOfAccession) {
        if (!SKINCOLOR.equals(newSmerfSkinColor)) {
            throw new IllegalArgumentException("Wrong skin color!!!");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        for (Smerf smerf : smerfList) {
            if (smerf.name.equals(name)) {
                throw new IllegalArgumentException("Name must be unique");
            }
        }
        this.accession = new Accession(stork, dateOfAccession);
        this.name = name;
        smerfList.add(this);
    }

    public static void setSmurfList(List<Smerf> newSmerfList) {
        smerfList = new ArrayList<Smerf>(newSmerfList);
    }

    public Accession getAccession() {
        return this.accession;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        for (Smerf smerf : smerfList) {
            if (smerf.name.equals(name)) {
                throw new IllegalArgumentException("Name must be unique");
            }
        }
        this.name = name;
    }

    public static String getSkinColor() {
        return SKINCOLOR;
    }

    public static List<Smerf> getSmerfList() {
        return Collections.unmodifiableList(smerfList);
    }

    public static int getSmerfsJoinedThisYear() {
        int count = 0;
        int currentYear = LocalDate.now().getYear();
    
        System.out.println("Smerfs that joined this year");
        System.out.println("-------------");
        for (Smerf smerf : smerfList) {
            if (smerf.accession.getDateOfAccession().getYear() == currentYear) {
                count++;
            }
        }
        System.out.println("-------------");
    
        return count;
    }

    public String toString() {
        return "Smerf{name= " + name + ", dateOfAccession= " + accession.getDateOfAccession() + "}";
    }
    
}
