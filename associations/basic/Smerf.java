// package org.example.model.basic;
package basic;

import java.util.Set;

public class Smerf {
    private String name;
    private Hut hut;

    public Smerf(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public Hut getHut() {
        return hut;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setHut(Hut hut) {
        if(this.hut == hut) {
            return;
        }
        if (hut == null) { // previous hut not null
            Hut prev_hut = this.hut;
            this.hut = null;
            if (prev_hut.getResidents().contains(this)) {
                prev_hut.removeResident(this);
            }
        } else {
            if (this.hut != null) {
                Hut prev_hut = this.hut;
                this.hut = hut;
                prev_hut.removeResident(this);
            } else {
                this.hut = hut;
            }
            hut.addResident(this);
        }
    }
}
