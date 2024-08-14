import allButBag.Smerf;
import allButBag.ScoutGroup;
import allButBag.SpecialTasksScoutGroup;
import allButBag.SolitaryConfinementGroup;
import withAttribute.Cook;
import withAttribute.Feast;
import withAttribute.Participation;

public class Main {
    public static void main(String[] args) {
        testSmerfScoutGroupAssociations();
        testSpecialTasksScoutGroupAssociations();
    }

    public static void testSmerfScoutGroupAssociations() {
        // Test case 1: Creating a Smerf and adding to a ScoutGroup
        Smerf smerf1 = new Smerf("Smerf1", 10.0, 100);
        ScoutGroup scoutGroup1 = new ScoutGroup();

        try {
            scoutGroup1.addMember(smerf1);
            assert smerf1.getScoutGroup() == scoutGroup1 : "Smerf1 should be in ScoutGroup1";
            assert scoutGroup1.getMembers().contains(smerf1) : "ScoutGroup1 should contain Smerf1";
            System.out.println("Test case 1 passed");
        } catch (Exception e) {
            System.out.println("Test case 1 failed: " + e.getMessage());
        }

        // Test case 2: Adding the same Smerf to the same ScoutGroup again
        try {
            scoutGroup1.addMember(smerf1);
            System.out.println("Test case 2 failed: Smerf1 should not be added again to ScoutGroup1");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Cannot add a smerf a second time to the ScoutGroup") : "Unexpected error message";
            System.out.println("Test case 2 passed");
        }

        // Test case 3: Creating a second Smerf and adding to a different ScoutGroup
        Smerf smerf2 = new Smerf("Smerf2", 15.0, 150);
        ScoutGroup scoutGroup2 = new ScoutGroup();

        try {
            scoutGroup2.addMember(smerf2);
            assert smerf2.getScoutGroup() == scoutGroup2 : "Smerf2 should be in ScoutGroup2";
            assert scoutGroup2.getMembers().contains(smerf2) : "ScoutGroup2 should contain Smerf2";
            System.out.println("Test case 3 passed");
        } catch (Exception e) {
            System.out.println("Test case 3 failed: " + e.getMessage());
        }

        // Test case 4: Trying to move Smerf1 to ScoutGroup2
        try {
            scoutGroup2.addMember(smerf1);
            System.out.println("Test case 4 failed: Smerf1 should not be added to ScoutGroup2");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Smerf cannot be in two different scout groups") : "Unexpected error message";
            System.out.println("Test case 4 passed");
        }

        // Test case 5: Removing Smerf1 from ScoutGroup1
        try {
            scoutGroup1.removeMember(smerf1);
            assert smerf1.getScoutGroup() == null : "Smerf1 should not be in any ScoutGroup";
            assert !scoutGroup1.getMembers().contains(smerf1) : "ScoutGroup1 should not contain Smerf1";
            System.out.println("Test case 5 passed");
        } catch (Exception e) {
            System.out.println("Test case 5 failed: " + e.getMessage());
        }

        // Test case 6: Trying to remove Smerf1 again from ScoutGroup1
        try {
            scoutGroup1.removeMember(smerf1);
            System.out.println("Test case 6 failed: Smerf1 should not be removed again from ScoutGroup1");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Cannot remove a smerf from a scout group that is not in that group") : "Unexpected error message";
            System.out.println("Test case 6 passed");
        }

        // Test case 7: Adding a Smerf to a SolitaryConfinementGroup
        SolitaryConfinementGroup solitaryGroup1 = new SolitaryConfinementGroup();
        solitaryGroup1.setPunishment("Punishment1");

        try {
            solitaryGroup1.addMember(smerf1);
            assert smerf1.getSolitaryConfinementGroup() == solitaryGroup1 : "Smerf1 should be in SolitaryConfinementGroup1";
            assert solitaryGroup1.getMembers().contains(smerf1) : "SolitaryConfinementGroup1 should contain Smerf1";
            System.out.println("Test case 7 passed");
        } catch (Exception e) {
            System.out.println("Test case 7 failed: " + e.getMessage());
        }

        // Test case 8: Trying to add a Smerf to both ScoutGroup and SolitaryConfinementGroup
        Smerf smerf3 = new Smerf("Smerf3", 20.0, 200);
        ScoutGroup scoutGroup3 = new ScoutGroup();
        SolitaryConfinementGroup solitaryGroup2 = new SolitaryConfinementGroup();
        solitaryGroup2.setPunishment("Punishment2");

        try {
            scoutGroup3.addMember(smerf3);
            solitaryGroup2.addMember(smerf3);
            System.out.println("Test case 8 failed: Smerf3 should not be added to both ScoutGroup3 and SolitaryConfinementGroup2");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Smerf cannot be in both scoutGroup and solitaryConfinementGroup") : "Unexpected error message";
            System.out.println("Test case 8 passed");
        }

        // Test case 9: Trying to add a Smerf in SolitaryConfinementGroup to a ScoutGroup
        try {
            solitaryGroup1.addMember(smerf2);
            scoutGroup2.addMember(smerf2);
            System.out.println("Test case 9 failed: Smerf2 should not be added to both SolitaryConfinementGroup1 and ScoutGroup2");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Smerf cannot be in both scoutGroup and solitaryConfinementGroup") : "Unexpected error message";
            System.out.println("Test case 9 passed");
        }
    }

    public static void testSpecialTasksScoutGroupAssociations() {
        // Test case 1: Adding a Smerf to a SpecialTasksScoutGroup that is already in the parent ScoutGroup
        Smerf smerf1 = new Smerf("Smerf1", 10.0, 100);
        ScoutGroup scoutGroup1 = new ScoutGroup();
        SpecialTasksScoutGroup specialGroup1 = new SpecialTasksScoutGroup(scoutGroup1);

        try {
            scoutGroup1.addMember(smerf1);
            specialGroup1.addSpecialMember(smerf1);
            assert specialGroup1.getSpecialMembers().contains(smerf1) : "Smerf1 should be in SpecialTasksScoutGroup1";
            System.out.println("Test case 1 passed");
        } catch (Exception e) {
            System.out.println("Test case 1 failed: " + e.getMessage());
        }

        // Test case 2: Adding a Smerf to a SpecialTasksScoutGroup that is not in the parent ScoutGroup
        Smerf smerf2 = new Smerf("Smerf2", 15.0, 150);
        ScoutGroup scoutGroup2 = new ScoutGroup();
        SpecialTasksScoutGroup specialGroup2 = new SpecialTasksScoutGroup(scoutGroup2);

        try {
            specialGroup2.addSpecialMember(smerf2);
            System.out.println("Test case 2 failed: Smerf2 should not be added to SpecialTasksScoutGroup2 because it's not in the parent ScoutGroup2");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Smerf must be a member of the parent scout group before being added to the special group") : "Unexpected error message";
            System.out.println("Test case 2 passed");
        }

        // Test case 3: Removing a Smerf from the parent ScoutGroup should remove it from the SpecialTasksScoutGroup
        try {
            scoutGroup1.removeMember(smerf1);
            assert !specialGroup1.getSpecialMembers().contains(smerf1) : "Smerf1 should be removed from SpecialTasksScoutGroup1 when removed from ScoutGroup1";
            System.out.println("Test case 3 passed");
        } catch (Exception e) {
            System.out.println("Test case 3 failed: " + e.getMessage());
        }

        // Test case 4: Adding and removing Smerf to/from SpecialTasksScoutGroup
        try {
            scoutGroup2.addMember(smerf2);
            specialGroup2.addSpecialMember(smerf2);
            assert specialGroup2.getSpecialMembers().contains(smerf2) : "Smerf2 should be in SpecialTasksScoutGroup2";

            specialGroup2.removeSpecialMember(smerf2);
            assert !specialGroup2.getSpecialMembers().contains(smerf2) : "Smerf2 should be removed from SpecialTasksScoutGroup2";
            assert !scoutGroup2.getMembers().contains(smerf2) : "Smerf2 should be alos removed from parent ScoutGroup2";
            System.out.println("Test case 4 passed");
        } catch (Exception e) {
            System.out.println("Test case 4 failed: " + e.getMessage());
        }
    }
}

