import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Stork implements Serializable {

    private static int staticId = 0;
    private static List<Stork> storkList = new ArrayList<>();

    private int id;
    private boolean isKnown;
    private String name;

    private Set<CardinalDirection> cameFrom = new HashSet<>();
    
    public Stork(String name, CardinalDirection dir) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.isKnown = true;
        this.name = name;
        this.cameFrom.add(dir);
        this.id = staticId;
        staticId++;
        storkList.add(this);
    }

    public Stork(CardinalDirection dir) {
        this.isKnown = false;
        this.cameFrom.add(dir);
        this.id = staticId;
        staticId++;
        storkList.add(this);
    }

    public static void setStorkList(List<Stork> newStorkList) {
        storkList = new ArrayList<Stork>(newStorkList);
    }

    public void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name != null) {
            this.isKnown = true;
        }
        this.name = name;
    }

    public boolean isKnown() {
        return this.isKnown;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public void addOrigin(CardinalDirection dir) {
        this.cameFrom.add(dir);
    }
    
    public void removeOrigin(CardinalDirection dir) {
        if (!this.cameFrom.contains(dir)) {
            throw new IllegalArgumentException("This cardinal direction is not present in the set");
        }
        if (this.cameFrom.size() <= 1) {
            throw new IllegalArgumentException("Cannot remove the last cardinal direction from the set");
        }
        this.cameFrom.remove(dir);
    }

    public static List<Stork> getStorkList() {
        return Collections.unmodifiableList(storkList);
    }

    public static int getStorksFromDirection(CardinalDirection dir) {
        int count = 0;
        for (Stork stork : storkList) {
            if (stork.cameFrom.contains(dir)) {
                count++;
            }
        }

        return count;
    }

    public static void setStaticId(int newStaticId) {
        staticId = newStaticId;
    }

    public static int getStaticId() {
        return staticId;
    }

    public String toString() {
        return "Stork{id= " + id + ", name= " + name + "}";
    }

}
