import basic.Hut;
import basic.Smerf;
import composition.ScoutGroup;
import composition.Assignment;
import qualified.District;
import qualified.House;
import withAttribute.Cook;
import withAttribute.Feast;
import withAttribute.Participation;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        basicAssociationTest();
        compositionTest();
        qualifiedTest();
        withAttrTest();

    }

    public static void basicAssociationTest() { //Hut Smerf Test
        Hut h1 = new Hut("hut1", "district1");
        Smerf s1 = new Smerf("smerf1");

        setDeptTest(h1, s1);

        Hut h2 = new Hut("hut1", "district1");
        Smerf s2 = new Smerf("smerf1");

        addEmpTest(h2, s2);

        Hut h3 = new Hut("hut1", "district1");
        Smerf s3 = new Smerf("smerf1");

        addAndRemoveFromEmp(h3, s3);

        Hut h4 = new Hut("hut1", "district1");
        Smerf s4 = new Smerf("smerf1");

        addAndRemoveFromDept(h4, s4);

        Hut h5 = new Hut("hut2", "district2");
        Hut h6 = new Hut("hut1", "district1");
        Smerf s5 = new Smerf("smerf1");

        testReplaceDepartment(h5, s5, h6);

    }

    public static void setDeptTest(Hut h1, Smerf s1) {
        System.out.println("setDeptTest");
        s1.setHut(h1);
        assert(h1 == s1.getHut());
        assert(h1.getResidents().contains(s1));
    }

    public static void addEmpTest(Hut h1, Smerf s1) {
        System.out.println("addEmpTest");
        // should not allow to add relation with a null for a collection
        // assertThrows(IllegalArgumentException.class, () -> h1.addResident(null));
        h1.addResident(s1);
        assert(h1 == s1.getHut());
        assert(h1.getResidents().contains(s1));
    }

    public static void addAndRemoveFromEmp(Hut h1, Smerf s1) {
        System.out.println("addAndRemoveFromEmp");
        s1.setHut(h1);
        assert(h1 == s1.getHut());
        assert(h1.getResidents().contains(s1));

        s1.setHut(null);
        assert(null == s1.getHut());
        assert(!h1.getResidents().contains(s1));

    }

    public static void addAndRemoveFromDept(Hut h1, Smerf s1) {
        System.out.println("addAndRemoveFromDept");
        s1.setHut(h1);
        assert(h1 == s1.getHut());
        assert(h1.getResidents().contains(s1));

        h1.removeResident(s1);
        assert(null == s1.getHut());
        assert(!h1.getResidents().contains(s1));

    }

    public static void testReplaceDepartment(Hut h1, Smerf s1, Hut h2) {
        System.out.println("testReplaceDepartment");
        s1.setHut(h1);
        assert(h1 == s1.getHut());
        assert(h1.getResidents().contains(s1));

        s1.setHut(h2);

        //first relation should be removed
        assert(!h1.getResidents().contains(s1));

        //secondary relation should be set
        assert(h2 == s1.getHut());
        assert(h2.getResidents().contains(s1));
    }

    public static void compositionTest() { //ScoutGroup Assignment Test
        Assignment.resetExtent();
        ScoutGroup s1 = new ScoutGroup("name");
        createAssignmentTest(s1);

        Assignment.resetExtent();
        ScoutGroup s2 = new ScoutGroup("name");
        removeAssignmentFromProjectTest(s2);

        Assignment.resetExtent();
        ScoutGroup s3 = new ScoutGroup("name1");
        ScoutGroup s4 = new ScoutGroup("name2");
        switchAssignmentOwner(s3, s4);

    }
    
    public static void createAssignmentTest(ScoutGroup p1) {
        System.out.println("createAssignmentTest");
        //you cannot create a task without a project
        // assertThrows(IllegalArgumentException.class, () -> { new Assignment("desc", null); });
        Assignment t1 = new Assignment("desc", p1);
        assert(p1 == t1.getOwner());
        assert(p1.getAssignments().contains(t1));
    }

    public static void removeAssignmentFromProjectTest(ScoutGroup p1) {
        System.out.println("removeAssignmentFromProjectTest");
        Assignment t1 = new Assignment("desc", p1);
        assert(p1 == t1.getOwner());
        assert(p1.getAssignments().contains(t1));
        assert(Assignment.getExtent().contains(t1));

        //remove from project
        p1.removeAssignment(t1);
        assert(null == t1.getOwner());
        assert(!p1.getAssignments().contains(t1));

        //task should not exists anymore
        assert(!Assignment.getExtent().contains(t1));
    }

    public static void switchAssignmentOwner(ScoutGroup p1, ScoutGroup p2) {
        System.out.println("switchAssignmentOwner");
        Assignment t1 = new Assignment("desc", p1);
        assert(p1 == t1.getOwner());
        assert(p1.getAssignments().contains(t1));

        try {
            p2.addAssignment(t1);
        } catch(Exception e) {
            System.out.println("switchAssignmentOwner1");
            assert(e instanceof IllegalArgumentException);
        }
        //its not possible to change an owner of a composed object
        // assertThrows(IllegalArgumentException.class,
        //     () -> {
        //         p2.addAssignment(t1);
        //     }
        // );
    }

    public static void qualifiedTest() { // District House
        District d1 = new District("dist1");
        House h1 = new House("house1");
        createTest(d1, h1);

        District d2 = new District("dist1");
        House h2 = new House("house1");
        createTest2(d2, h2);

        District d3 = new District("dist1");
        House h3 = new House("house1");
        removeTest(d3, h3);

        District d4 = new District("dist1");
        House h4 = new House("house1");
        removeTest2(d4, h4);
        
    }

    public static void createTest(District c1, House d1) {
        System.out.println("createTest");
        c1.addHouse(d1);
        assert(c1.getHouses().get(d1.getName()) == d1);
        System.out.println(d1.getDistrict());
        System.out.println(c1);
        assert(d1.getDistrict() == c1);

        //check if getDeptByName works properly
        assert(d1 == c1.getHouseByName(d1.getName()));
    }


    public static void createTest2(District c1, House d1) {
        System.out.println("createTest2");
        d1.setDistrict(c1);
        assert(c1.getHouses().get(d1.getName()) == d1);
        System.out.println(d1.getDistrict());
        System.out.println(c1);
        assert(d1.getDistrict() == c1);

        //check if getDeptByName works properly
        // assert(d1.getName() == c1.getHouseByName(d1.getName()));
        assert(d1 == c1.getHouseByName(d1.getName()));
    }

    public static void removeTest(District c1, House d1) {
        System.out.println("removeTest");
        c1.addHouse(d1);
        assert(c1.getHouses().get(d1.getName()) == d1);
        assert(d1.getDistrict() == c1);

        c1.removeHouse(d1);
        assert(d1.getDistrict() == null);
        assert(!c1.getHouses().containsKey(d1.getName()));
    }

    public static void removeTest2(District c1, House d1) {
        System.out.println("removeTest2");
        c1.addHouse(d1);
        assert(c1.getHouses().get(d1.getName()) == d1);
        assert(d1.getDistrict() == c1);

        d1.setDistrict(null);
        assert(d1.getDistrict() == null);
        assert(!c1.getHouses().containsKey(d1.getName()));
    }

    public static void withAttrTest() { //Cook Feast
        Cook c1 = new Cook("name1");
        Cook c2 = new Cook("name2");
        Feast f1 = new Feast("feast1");
        Feast f2 = new Feast("feast2");

        createSuccessfully(c1, c2, f1, f2);
    }

    public static void createSuccessfully(Cook e1, Cook e2, Feast p1, Feast p2) {
        System.out.println("createSuccessfullyTest");
        try {
            Participation p_worng = new Participation(null, p1, LocalDate.now());
        } catch(Exception e) {
            System.out.println("createSuccessfullyTest1");
            assert(e instanceof IllegalArgumentException);
        }

        try {
            Participation p_worng2 = new Participation(e1, null, LocalDate.now());
        } catch(Exception e) {
            System.out.println("createSuccessfullyTest2");
            assert(e instanceof IllegalArgumentException);
        }
        // assertThrows(IllegalArgumentException.class,
        //         () -> { new Participation(null, p1, LocalDate.now()); },
        //         "you should not allow to create a association object with null employee");
        // assertThrows(IllegalArgumentException.class,
        //         () -> { new Participation(e1, null, LocalDate.now()); },
        //         "you should not allow to create a association object with null project");


        Participation part1 = new Participation(e1, p1, LocalDate.now());

        //all references should be set
        assert(e1 == part1.getCook());
        assert(p1 == part1.getFeast());
        assert(e1.getParticipations().contains(part1));
        assert(p1.getParticipations().contains(part1));

        try {
            p2.addParticipation(part1);
        } catch(Exception e) {
            System.out.println("createSuccessfullyTest3");
            assert(e instanceof IllegalArgumentException);
        }
        //attempt to use add this participation to unrelated Project
        // assertThrows(
        //     IllegalArgumentException.class,
        //     () -> {
        //         p2.addParticipation(part1);
        //     }
        // );

        try {
            e2.addParticipation(part1);
        } catch(Exception e) {
            System.out.println("createSuccessfullyTest4");
            assert(e instanceof IllegalArgumentException);
        }
        //attempt to use add this participation to unrelated employee
        // assertThrows(
        //     IllegalArgumentException.class,
        //     () -> {
        //         e2.addParticipation(part1);
        //     }
        // );

        try {
            Participation p_wrong3 = new Participation(e1, p1, LocalDate.now());
        } catch(Exception e) {
            System.out.println("createSuccessfullyTest5");
            assert(e instanceof IllegalArgumentException);
        }
        //for normal association (not bag or history)
        // we should not be able to make duplicate relation
        // assertThrows(IllegalArgumentException.class,
        //     () -> { new Participation(e1, p1, LocalDate.now()); }
        // );

        e1.removeParticipation(part1);
        // //now all 4 references should be removed
        // assert(part1.getEmployee() == null);
        // assert(part1.getProject() == null);
        assert(!e1.getParticipations().contains(part1));
        assert(!p1.getParticipations().contains(part1));
    }



    public static void displayRedistents(Hut hut) {
        if (hut.getResidents().size() == 0) {
            System.out.println("No one");
        } else {
            for (Smerf s : hut.getResidents()) {
                System.out.println(s.getName());
            }
        }
    }

    public static void displaySmerfHut(Smerf s) {
        System.out.println(s.getHut());
    }

}
