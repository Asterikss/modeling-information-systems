package qualified;

public class House {
    private String name;
    private District district;

    public House(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.strip().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (district != null) {
            throw new IllegalArgumentException("Cannot change the name of the house that is already assigned to a district");
        }
        this.name = name;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        if(this.district == district) {
            return;
        }
        if (district == null) { // previous district not null
            District prev_district = this.district;
            this.district = null;
            if (prev_district.getHouses().containsValue(this)) {
                prev_district.removeHouse(this);
            }
        } else {
            if (this.district != null) {
                District prev_district = this.district;
                this.district = district;
                prev_district.removeHouse(this);
            } else {
                this.district = district;
            }
            if (!district.getHouses().containsValue(this)) {
                district.addHouse(this);
            }
        }

    }
}
