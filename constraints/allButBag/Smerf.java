package allButBag;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Smerf {
    private String name;
    private double foodRations;
    private int age;
    private String capColor;
    private ScoutGroup scoutGroup;
    private SolitaryConfinementGroup solitaryConfinementGroup;

    private List<Smerf> extent = new ArrayList<>();

    public Smerf(String name, double foodRations, int age) {
        setName(name);
        setFoodRations(foodRations);
        setAge(age);
        this.capColor = "white";
        this.extent.add(this);
    }

    public double getFoodRations() {
        return this.foodRations;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getCapColor() {
        return this.capColor;
    }

    public ScoutGroup getScoutGroup() {
        return this.scoutGroup;
    }

    public SolitaryConfinementGroup getSolitaryConfinementGroup() {
        return this.solitaryConfinementGroup;
    }

    public List<Smerf> getExtent() {
        List<Smerf> copy = new ArrayList<>(this.extent);
        Collections.sort(copy, new Comparator<Smerf>() {
            @Override
            public int compare(Smerf s1, Smerf s2) {
                return Integer.compare(s1.getAge(), s2.getAge());
            }
        });
        return copy;
    }

    public void setFoodRations(double foodRations) {
        if (foodRations < this.foodRations) {
            throw new IllegalArgumentException("new foodRations value cannot be smaller than the old one");
        }
        this.foodRations = foodRations;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        for (Smerf smerf : extent) {
            if (smerf.getName().equals(name)) {
                throw new IllegalArgumentException("Name must be unique");
            }
        }
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be a positive value");
        }
        this.age = age;
    }

    public void setScoutGroup(ScoutGroup scoutGroup) {
        if(this.scoutGroup == scoutGroup) {
            return;
        }
        if (scoutGroup == null) {
            ScoutGroup prevScoutGroup = this.scoutGroup;
            this.scoutGroup = null;
            if (prevScoutGroup.getMembers().contains(this)) {
                prevScoutGroup.removeMember(this);
            }
        } else {
            if (this.solitaryConfinementGroup != null) { // XOR
                throw new IllegalArgumentException("Smerf cannot be in both scoutGroup and solitaryConfinementGroup");
            }
            if (this.scoutGroup != null) {
                // throw new IllegalArgumentException("Smerf cannot be in two different scout groups");
                ScoutGroup prevScoutGroup = this.scoutGroup;
                this.scoutGroup = scoutGroup;
                prevScoutGroup.removeMember(this);
            } else {
                this.scoutGroup = scoutGroup;
            }
            if (!scoutGroup.getMembers().contains(this)) {
                scoutGroup.addMember(this);
            }
        }
    }

    public void setSolitaryConfinementGroup(SolitaryConfinementGroup solitaryConfinementGroup) {
        if(this.solitaryConfinementGroup == solitaryConfinementGroup) {
            return;
        }
        if (solitaryConfinementGroup == null) {
            SolitaryConfinementGroup prevSolitaryConfinementGroup = this.solitaryConfinementGroup;

            this.solitaryConfinementGroup = null;
            if (prevSolitaryConfinementGroup.getMembers().contains(this)) {
                prevSolitaryConfinementGroup.removeMember(this);
            }
        } else {
            if (this.scoutGroup != null) { // XOR
                throw new IllegalArgumentException("Smerf cannot be in both scoutGroup and solitaryConfinementGroup");
            }
            if (this.solitaryConfinementGroup != null) {
                SolitaryConfinementGroup prevSolitaryConfinementGroup = this.solitaryConfinementGroup;
                this.solitaryConfinementGroup = solitaryConfinementGroup;
                prevSolitaryConfinementGroup.removeMember(this);
            } else {
                this.solitaryConfinementGroup = solitaryConfinementGroup;
            }
            if (!solitaryConfinementGroup.getMembers().contains(this)) {
                solitaryConfinementGroup.addMember(this);
            }
        }
    }

    public void setCapColor(String capColor) {
        if (capColor == null || capColor.strip().isEmpty()) {
            throw new IllegalArgumentException("Cap color cannot be null or empty");
        }
        if (this.age < 150) {
            throw new IllegalArgumentException("Cannot change the cap color for a Smerf that is younger than 150 years");
        }
        if (this.age < 400 && capColor.equals("white")) {
            throw new IllegalArgumentException("Cannot change the cap color to white for a Smerf that is older than 400 years");
        }
        this.capColor = capColor;
    }

}
