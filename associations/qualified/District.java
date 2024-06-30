package qualified;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class District {
    private String name;

    // name of the house is a qualifier here
    private Map<String, House> houses = new HashMap<>();


    public District(String name) {
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

    public Map<String, House> getHouses() {
        return new HashMap<>(houses);
    }

    public House getHouseByName(String name) {
        return houses.get(name);
    }

    public void addHouse(House h) {
        if (h == null) {
            throw new IllegalArgumentException("Cannot add null house");
        }
        if (houses.containsValue(h)) {
            throw new IllegalArgumentException("Cannot add a house again");
        }
        houses.put(h.getName(), h);
        if (h.getDistrict() != this) {
            h.setDistrict(this);
        }
    }

    public void removeHouse(House h) {
        if (h == null) {
            throw new IllegalArgumentException("Cannot remove null house");
        }
        if (!houses.containsValue(h)) {
            throw new IllegalArgumentException("Cannot remove non existing house");
        }
        houses.remove(h.getName());
        if (h.getDistrict() == this) {
            h.setDistrict(null);
        }
    }
}
