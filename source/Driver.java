// ************************************************************************** //
// Driver.java - This file contains the Driver class, which is used to test   //
//               the basic functionality of the classes and methods in jASL.  //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, which  //
//                     was created by The Avalon Hill Game Company, and lives //
//                     on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                          //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
// ************************************************************************** //

import jasl.counters.*;
import jasl.ui.data.*;
import jasl.utilities.Dice;
import jasl.utilities.Serialization;

public class Driver
{
    public static void main(String[] args)
    {
        // Create an instance of a German Leader.

        Leader germanLeader = new Leader(Nationality.Nationalities.GERMAN,
                                         UnitType.InfantryTypes.NONE,
                                         9,9,4,-1);

        // Display all of the entered values for this instance using the
        // toText() method.

        System.out.println("\nLeader.toText() output:\n");
        System.out.print(germanLeader.toText());

        // Display an abbreviated description of this instance using the
        // toString() method.

        System.out.println("\nLeader.toString() output:\n");
        System.out.println(germanLeader.toString());

        // Serialize the Leader object, write the data to a file (Leader.ser),
        // then deserialize the data into a new object.

        germanLeader.setIdentity("Col. Klink");

        Serialization.serializeToFile(germanLeader,"/tmp/Leader.ser");

        Unit deserializedLeader =
            (Unit)Serialization.deserializeFromFile("/tmp/Leader.ser");

        // Display all of the entered values for the deserialized instance using
        // the toText() method.

        System.out.println("\n(Deserialized) Leader.toText() output:\n");
        System.out.print(deserializedLeader.toText());

        // Display an abbreviated description of the deserialized instance using
        // the toString() method.

        System.out.println("\n(Deserialized) Leader.toString() output:\n");
        System.out.println(deserializedLeader.toString());

        // Display all of the entered values for the deserialized instance using
        // the toJSON() method.

        System.out.println("\n(Deserialized) Leader.toJSON() output:\n");
        System.out.println(deserializedLeader.toJSON());

        // Create an instance of a Russian Squad.

        Squad russianSquad = new Squad(Nationality.Nationalities.RUSSIAN,
                                       UnitType.InfantryTypes.GUARDS,
                                       6,2,8,8,false,12,4,false,
                                       Classification.Classifications.ELITE,
                                       true,true,0);

        russianSquad.setIdentity("A");

        // Display all of the entered values for this instance using the
        // toText() method.

        System.out.println("\nSquad.toText() output:\n\n" +
                           russianSquad.toText());

        // Display an abbreviated description of this instance using the
        // toString() method.

        System.out.println("Squad.toString() output:\n\n" +
                           russianSquad.toString());

        // Display all of the entered values for this instance using the
        // toJSON() method.

        System.out.println("\nSquad.toJSON() output:\n\n" +
                           russianSquad.toJSON());

        // Create an array of Unit objects. These will be used to reference a
        // Leader instance and several Squad instances. These class types are
        // derived from Unit.

        System.out.println("\nBuilding Unit array with a Leader & 3 Squads\n");

        Unit[] UnitList = new Unit[4];

        UnitList[0] = new Leader(Nationality.Nationalities.AMERICAN,
                                 UnitType.InfantryTypes.NONE,9,9,4,-1);

        ((Leader)UnitList[0]).setIdentity("Sgt. Slaughter");

        UnitList[1] = new Squad(Nationality.Nationalities.AMERICAN,
                                UnitType.InfantryTypes.NONE,
                                6,6,6,6,false,11,4,false,
                                Classification.Classifications.FIRST_LINE,
                                true,false,3);

        ((Squad)UnitList[1]).setIdentity("X");

        UnitList[2] = new Squad(Nationality.Nationalities.AMERICAN,
                                UnitType.InfantryTypes.NONE,
                                6,6,6,6,false,11,4,false,
                                Classification.Classifications.FIRST_LINE,
                                true,false,3);

        ((Squad)UnitList[2]).setIdentity("Y");

        UnitList[3] = new Squad(Nationality.Nationalities.AMERICAN,
                                UnitType.InfantryTypes.NONE,
                                6,6,6,6,false,11,4,false,
                                Classification.Classifications.FIRST_LINE,
                                true,false,3);

        ((Squad)UnitList[3]).setIdentity("Z");

        System.out.println("Displaying Unit array with a Leader & 3 Squads");

        Leader leaderObject = null;
        Squad  squadObject  = null;

        for (int i = 0; i < 4; i++)
        {
            System.out.println("\nUnitList[" + i + "]:\t" +
                               UnitList[i].toString());

            if (Description.Descriptions.LEADER.toString() == UnitList[i].description())
            {
                leaderObject = (Leader)UnitList[i];

                System.out.println("\n" + leaderObject.description() +
                                   "\n" + leaderObject.identity() +
                                   "\n" + leaderObject.unitType() +
                                   "\n" + leaderObject.movement() +
                                   "\n" + leaderObject.status());
            }

            if (Description.Descriptions.SQUAD.toString() == UnitList[i].description())
            {
                squadObject = (Squad)UnitList[i];

                System.out.println("\n" + squadObject.description() +
                                   "\n" + squadObject.identity() +
                                   "\n" + squadObject.unitType() +
                                   "\n" + squadObject.movement() +
                                   "\n" + squadObject.status());
            }
        }

        // Create an instance of a German Squad (that throws some exceptions).

        System.out.println("\nTesting Exception handling during Squad creation:");

        squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                UnitType.InfantryTypes.NONE,
                                4,6,7,7,false,10,3,false,
                                Classification.Classifications.FIRST_LINE,
                                true,false,0);

        // Null Identity

        try
        {
            squadObject.setIdentity(null);
        }

        catch (Exception e) // No longer expected.
        {
            System.out.println("Caught: " + e);
        }

        // Blank Identity

        try
        {
            squadObject.setIdentity("");
        }

        catch (Exception e) // No longer expected.
        {
            System.out.println("Caught: " + e);
        }

        // Incompatible nationality and unitType

        System.out.println("\nIncompatible nationality and unitType parameters:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.BRITISH,
                                    UnitType.InfantryTypes.ENGINEERS,
                                    4,6,7,7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Incompatible description and unitType

        System.out.println("\nIncompatible description and unitType parameters:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.RUSSIAN,
                                    UnitType.InfantryTypes.COMMISSAR,
                                    4,4,7,7,false,10,3,false,
                                    Classification.Classifications.GREEN,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Firepower

        System.out.println("\nInvalid (less than 0) firepower parameter:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    -1,6,7,7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        System.out.println("\nInvalid (greater than maximum) firepower parameter:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    11,6,7,7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Range

        System.out.println("\nInvalid (less than 0) normal range parameter:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,-255,7,7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Morale (Minimum)

        System.out.println("\nInvalid (less than 0) morale parameter:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,-1,7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Morale (Maximum)

        System.out.println("\nInvalid (greater than maximum) morale parameter:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,11,7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Broken Morale (Minimum)

        System.out.println("\nInvalid (less than 0) broken morale parameter:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,-7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Broken Morale (Maximum)

        System.out.println("\nInvalid (greater than maximum) broken morale parameter:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,17,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Basic Point Value (BPV)

        System.out.println("\nInvalid (less than zero) Basic Point Value (BPV):\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,7,false,-1,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Experience Level Rating (Minimum)

        System.out.println("\nInvalid (less than zero) Experience Level Rating (ELR):\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,7,false,10,-1,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Experience Level Rating (Maximum)

        System.out.println("\nInvalid (greater than maximum) Experience Level Rating (ELR):\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,7,false,10,6,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Incompatible Classification

        System.out.println("\nIncompatible classification parameter:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.ITALIAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,7,false,10,3,false,
                                    Classification.Classifications.SS,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Smoke Placement Exponent (Minimum)

        System.out.println("\nInvalid (less than zero) Smoke Placement Exponent:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,7,false,10,3,false,
                                    Classification.Classifications.SECOND_LINE,
                                    true,false,-4);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Smoke Placement Exponent (Maximum)

        System.out.println("\nInvalid (greater than maximum) Smoke Placement Exponent:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,7,false,10,3,false,
                                    Classification.Classifications.SECOND_LINE,
                                    true,false,4);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Create an instance of a Canadian Leader (that throws an exception).
        // NOTE: It is only necessary to test the modifier, since all the other
        //       exceptions have been tested above as part of the creation of a
        //       Squad.

        System.out.println("\nTesting Exception handling during Leader creation:\n");

        // Invalid Modifier (Minimum)

        System.out.println("Invalid (less than minimum) modifier parameter:\n");

        try
        {
            leaderObject = new Leader(Nationality.Nationalities.BRITISH,
                                      UnitType.InfantryTypes.CANADIAN,
                                      10,10,5,-4);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Modifier (Maximum)

        System.out.println("\nInvalid (greater than maximum) modifier parameter:\n");

        try
        {
            leaderObject = new Leader(Nationality.Nationalities.BRITISH,
                                      UnitType.InfantryTypes.CANADIAN,
                                      10,10,5,4);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Test the Dice class.

        System.out.println("\nTesting the execution of the Dice class:\n");

        Dice theDice;

        for (int i = 0; i < 12; i++)
        {
            try
            {
                theDice = new Dice();

                // Show the results.

                System.out.print(theDice.toText() + "\n");
            }

            catch (IllegalStateException e)
            {
                System.out.println("Caught: " + e);
            }
        }

        // Test the Game class.

        System.out.println("Testing the operations of the Game class:\n");

        Game.game().addPlayer(Side.Sides.ALLIES,"Pixie",
                              Nationality.Nationalities.AMERICAN,1);
        Game.game().addPlayer(Side.Sides.AXIS,"Buddy",
                              Nationality.Nationalities.GERMAN,1);

        Player alliedPlayer = Game.game().player(Side.Sides.ALLIES,"Pixie");

        alliedPlayer.addUnit("9-1 Leader");
        alliedPlayer.addUnit("7-4-7 Squad");
        alliedPlayer.addUnit("7-4-7 Squad");
        alliedPlayer.addUnit("7-4-7 Squad");

        Player axisPlayer = Game.game().player(Side.Sides.AXIS,"Buddy");

        axisPlayer.addUnit("8-1 Leader");
        axisPlayer.addUnit("6-5-8 Squad");
        axisPlayer.addUnit("6-5-8 Squad");
        axisPlayer.addUnit("6-5-8 Squad");

        System.out.print(Game.game().toText());
        System.out.println();
    }
}
