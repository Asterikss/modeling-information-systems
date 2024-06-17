import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        boolean firtTime = false;
        Wioska.initializeClasses();

        Wioska.listSmerfs();

        if (firtTime) {
            Stork ancientStork = new Stork("TheAncientOne", CardinalDirection.NORTH);

            Smerf PapaSmerf = new Smerf("Papa Smerf", ancientStork, "kobaltowy", LocalDate.of(993, 4, 6));
            Smerf Smerfetka = new Smerf("Smerfetka", ancientStork, "kobaltowy", LocalDate.of(1998, 5, 10));
            Smerf Osiłek = new Smerf("Osiłek", ancientStork, "kobaltowy", LocalDate.of(997, 2, 9));
            Smerf Łasuch = new Smerf("Łasuch", ancientStork, "kobaltowy", LocalDate.of(1004, 9, 8));
            Smerf Ważniak = new Smerf("Ważniak", ancientStork, "kobaltowy", LocalDate.of(997, 11, 3));
            Smerf Maruda = new Smerf("Maruda", ancientStork, "kobaltowy", LocalDate.of(997, 8, 9));
            Smerf Ciamajda = new Smerf("Ciamajda", ancientStork, "kobaltowy", LocalDate.of(1001, 6, 4));
            Smerf Pracuś = new Smerf("Pracuś", ancientStork, "kobaltowy", LocalDate.of(994, 8, 3));

            Wioska.listSmerfs();

            Wioska.saveDataToFile(Smerf.getSmerfList(), Stork.getStorkList(), Stork.getStaticId());

        } else {
            Stork newStork = new Stork(CardinalDirection.WEST);

            Smerf Śpioch = new Smerf("Śpioch", newStork, "kobaltowy");

            Wioska.listSmerfs();

            try {
                Smerf wrongSmerf = new Smerf("Niebieski Klakier", newStork, "niebieski");
            } catch (IllegalArgumentException e) {
                System.out.println("Failed to create Smerf with incorrect skin color: " + e.getMessage());
            }

            try {
                Smerf wrongSmerf2 = new Smerf("Śpioch", newStork, "kobaltowy");
            } catch (IllegalArgumentException e) {
                System.out.println("Failed to create a duplicate Smerf: " + e.getMessage());
            }

            try {
                Smerf wrongSmerf3 = new Smerf("", newStork, "kobaltowy");
            } catch (IllegalArgumentException e) {
                System.out.println("Name of a Smerf cannot be empty" + e.getMessage());
            }

            try {
                Smerf wrongSmerf4 = new Smerf(null, newStork, "kobaltowy");
            } catch (IllegalArgumentException e) {
                System.out.println("Name of a Smerf cannot be null" + e.getMessage());
            }
            try {
                Śpioch.setName("");
            } catch (IllegalArgumentException e) {
                System.out.println("Name of a Smerf cannot be empty" + e.getMessage());
            }
            try {
                Śpioch.setName("Maruda");
            } catch (IllegalArgumentException e) {
                System.out.println("No two Smerfs can have two names" + e.getMessage());
            }
            try {
                Śpioch.setName(null);
            } catch (IllegalArgumentException e) {
                System.out.println("Name of a Smerf cannot be null" + e.getMessage());
            }
            Śpioch.setName("Zgrywus");
            Smerf Zgrywus = Śpioch;

            try {
                Stork newStork2 = new Stork("", CardinalDirection.WEST);
            } catch (IllegalArgumentException e) {
                System.out.println("Name of a Stork cannot be empty" + e.getMessage());
            }

            newStork.addOrigin(CardinalDirection.NORTH);

            try {
                newStork.removeOrigin(CardinalDirection.EAST);
            } catch (IllegalArgumentException e) {
                System.out.println("Cannot remove a cardinal direction that wasn't there in the first place" + e.getMessage());
            }

            newStork.removeOrigin(CardinalDirection.NORTH);

            try {
                newStork.removeOrigin(CardinalDirection.WEST);
            } catch (IllegalArgumentException e) {
                System.out.println("Cannot remove the last cardinal direction" + e.getMessage());
            }

            System.out.println("Stork list");
            System.out.println("-------------");
            List<Stork> storkList = Stork.getStorkList();
            for (Stork stork : storkList) {
                System.out.println(stork);
            }
            System.out.println("-------------");

        }
    }
        
}
