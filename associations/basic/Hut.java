package basic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Hut {
    private String name;
    private String district;

    private Set<Smerf> residents = new HashSet<>();

    public Hut(String name, String district) {
        setName(name);
        setDistrict(district);
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public void setName(String name) {
        if (name == null || name.strip().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setDistrict(String district) {
        if (district == null || district.strip().isEmpty()) {
            throw new IllegalArgumentException("District cannot be null or empty");
        }
        this.district = district;
    }

    public void addResident(Smerf smerf) {
        if (smerf == null) {
            throw new IllegalArgumentException("Cannot add null resident");
        }
        this.residents.add(smerf);
        if (smerf.getHut() != this) {
            smerf.setHut(this);
        }
    }

    public void removeResident(Smerf smerf) {
        if (smerf == null) {
            throw new IllegalArgumentException("Cannot remove null resident");
        }
        this.residents.remove(smerf);
        if (smerf.getHut() != null) {
            smerf.setHut(null);
        }
    }

    public Set<Smerf> getResidents() {
        return Collections.unmodifiableSet(residents);
    }

    public String toString() {
        return "Hut: " + this.name;
    }

}
