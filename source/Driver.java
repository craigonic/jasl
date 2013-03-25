// ************************************************************************** //
// Driver.java - This file contains the Driver class, which is used to test   //
//               the basic functionality of the classes and methods in the    //
//               <B>counters</B> package.                                            //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, which  //
//                     was created by The Avalon Hill Game Company, and lives //
//                     on at <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.                          //
//                                                                            //
// Written By  : Craig R. Campbell  -  December 1998                          //
//                                                                            //
// $Id: Driver.java,v 1.12 2009/11/25 06:19:14 craig Exp $
// ************************************************************************** //

import jasl.counters.*;
import jasl.utilities.Dice;
import jasl.utilities.Serialization;

public class Driver
{
    public static void main(String[] args)
    {
        // Create an instance of a German Leader.

        Leader germanLeader = new Leader("German","Lt. Fellbaum","Leader",9,9,
                                         4,-1);

        // Display all of the entered values for this instance using the
        // toString() method.

        System.out.println();
        System.out.println("Leader.toString() output:");
        System.out.println();
        System.out.print(germanLeader.toString());

        // Serialize the Leader object, write the data to a file (Leader.ser),
        // then deserialize the data into a new object.

        germanLeader.setIdentity("Col. Klink");

        Serialization.serializeToFile(germanLeader,"/tmp/Leader.ser");

        Unit deserializedLeader =
            (Unit)Serialization.deserializeFromFile("/tmp/Leader.ser");

        // Display all of the entered values for the deserialized instance using
        // the toString() method.

        System.out.println();
        System.out.println("(Deserialized) Leader.toString() output:");
        System.out.println();
        System.out.print(deserializedLeader.toString());

        // Create an instance of a Russian Squad.

        Squad russianSquad = new Squad(Nationality.RUSSIAN,"A",UnitType.GUARDS,
                                       "6",2,true,8,8,false,12,4,false,
                                       Classification.ELITE,true,0);

        // Display all of the entered values for this instance using the
        // toString() method.

        System.out.println();
        System.out.println("Squad.toString() output:");
        System.out.println();
        System.out.print(russianSquad.toString());
        System.out.println();

        // Create an array of Unit objects. These will be used to reference a
        // Leader instance and several Squad instances. These class types are
        // derived from Unit.

        System.out.println("Building Unit array with a Leader & 3 Squads");
        System.out.println();

        Unit[] UnitList = new Unit[4];

        UnitList[0] = new Leader("American","Sgt. Slaughter","Ranger",9,9,4,-1);
        UnitList[1] = new Squad("American","X","Squad","6",6,false,6,6,false,
                                11,4,false,Classification.FIRST_LINE,true,3);
        UnitList[2] = new Squad("American","Y","Squad","6",6,false,6,6,false,
                                11,4,false,Classification.FIRST_LINE,true,3);
        UnitList[3] = new Squad("American","Z","Squad","6",6,false,6,6,false,
                                11,4,false,Classification.FIRST_LINE,true,3);

        System.out.println("Displaying Unit array with a Leader & 3 Squads");

        for (int i = 0; i < 4; i++)
        {
            System.out.println();
            System.out.println("UnitList[" + i + "]:");
            System.out.println();
            System.out.println(UnitList[i].getDescription());
            System.out.println(UnitList[i].getIdentity());
            System.out.println(UnitList[i].getUnitType());
            System.out.println(UnitList[i].getMovement());
            System.out.println(UnitList[i].getStatus());
        }

        // Create an instance of a German Squad (that throws some exceptions).

        // NULL Nationality

        System.out.println();
        System.out.println("Testing Exception handling during Squad creation:");
        System.out.println();
        System.out.println("Null nationality parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(null,"5","Squad","4",6,true,7,7,
                                          false,10,3,false,
                                          Classification.FIRST_LINE,false,1);
        }

        catch (NullPointerException e)
        {
            System.out.println("Caught: " + e);
        }

        // Blank Nationality

        System.out.println();
        System.out.println("Zero-length nationality parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad("","5","Squad","4",6,false,7,7,
                                          false,10,3,false,
                                          Classification.FIRST_LINE,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Nationality

        System.out.println();
        System.out.println("Invalid nationality parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad("Mexican","5","Squad","4",6,true,7,
                                          7,false,10,3,false,
                                          Classification.FIRST_LINE,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Null Identity

        System.out.println();
        System.out.println("Null identity parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,null,"Squad","4",6,
                                          false,7,7,false,10,3,false,
                                          Classification.FIRST_LINE,false,1);
        }

        catch (NullPointerException e)
        {
            System.out.println("Caught: " + e);
        }

        // Blank Identity

        System.out.println();
        System.out.println("Zero-length identity parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"","Squad","4",6,
                                          true,7,7,false,10,3,false,
                                          Classification.FIRST_LINE,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Null unitType

        System.out.println();
        System.out.println("Null unitType parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5",null,"4",6,
                                          false,7,7,false,10,3,false,
                                          Classification.FIRST_LINE,false,1);
        }

        catch (NullPointerException e)
        {
            System.out.println("Caught: " + e);
        }

        // Blank unitType

        System.out.println();
        System.out.println("Zero-length unitType parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","","4",6,true,
                                          7,7,false,10,3,false,
                                          Classification.FIRST_LINE,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid nationality and unitType

        System.out.println();
        System.out.println("Invalid nationality and unitType parameters:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.BRITISH,"5","SS","4",6,
                                          false,7,7,false,10,3,false,
                                          Classification.FIRST_LINE,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid description and unitType

        System.out.println();
        System.out.println("Invalid description and unitType parameters:");
        System.out.println();

        try
        {
            Squad commissarSquad = new Squad(Nationality.RUSSIAN,"5",
                                             UnitType.COMMISSAR,"4",6,true,7,7,
                                             false,10,3,false,
                                             Classification.GREEN,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Firepower

        System.out.println();
        System.out.println("Invalid (less than 0) firepower parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","-1",6,
                                          false,7,7,false,10,3,false,
                                          Classification.FIRST_LINE,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        System.out.println();
        System.out.println("Invalid infantry firepower parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","88L",
                                          6,true,7,7,false,10,3,false,
                                          Classification.FIRST_LINE,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Range

        System.out.println();
        System.out.println("Invalid (less than 0) normal range parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","4",
                                          -255,false,7,7,false,10,3,false,
                                          Classification.FIRST_LINE,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Morale (Minimum)

        System.out.println();
        System.out.println("Invalid (less than 0) morale parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","4",6,
                                          false,-1,7,false,10,3,false,
                                          Classification.GREEN,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Morale (Maximum)

        System.out.println();
        System.out.println("Invalid (greater than maximum) morale parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","4",6,
                                          true,11,7,false,10,3,false,
                                          Classification.GREEN,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Broken Morale (Minimum)

        System.out.println();
        System.out.println("Invalid (less than 0) broken morale parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","4",6,
                                          false,7,-7,false,10,3,false,
                                          Classification.GREEN,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Broken Morale (Maximum)

        System.out.println();
        System.out.println("Invalid (greater than maximum) broken morale " +
                           "parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","4",6,
                                          true,7,17,false,10,3,false,
                                          Classification.GREEN,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Basic Point Value (BPV)

        System.out.println();
        System.out.println("Invalid (less than zero) Basic Point Value (BPV):");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","4",6,
                                          false,7,7,true,-1,3,false,
                                          Classification.GREEN,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Experience Level Rating (Minimum)

        System.out.println();
        System.out.println("Invalid (less than zero) Experience Level Rating (ELR):");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","4",6,
                                          true,7,7,true,10,-1,false,
                                          Classification.GREEN,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Experience Level Rating (Maximum)

        System.out.println();
        System.out.println("Invalid (greater than maximum) Experience Level Rating (ELR):");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","4",6,
                                          false,7,7,true,10,6,false,
                                          Classification.SECOND_LINE,false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Null Classification

        System.out.println();
        System.out.println("Null classification parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","4",6,
                                          true,7,7,true,10,3,false,null,false,1);
        }

        catch (NullPointerException e)
        {
            System.out.println("Caught: " + e);
        }

        // Blank Classification

        System.out.println();
        System.out.println("Zero-length classification parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","SS","4",6,
                                          true,7,7,true,10,3,false,"",false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Classification

        System.out.println();
        System.out.println("Invalid classification parameter:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"4A","SS","4",6,
                                          false,7,7,false,10,3,false,"Bozos",
                                          false,1);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Smoke Placement Exponent (Minimum)

        System.out.println();
        System.out.println("Invalid (less than zero) Smoke Placement Exponent:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","4",6,
                                          true,7,7,true,10,3,false,
                                          Classification.SECOND_LINE,false,-4);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Smoke Placement Exponent (Maximum)

        System.out.println();
        System.out.println("Invalid (greater than maximum) Smoke Placement Exponent:");
        System.out.println();

        try
        {
            Squad germanSquad = new Squad(Nationality.GERMAN,"5","Squad","4",6,
                                          true,7,7,true,10,3,false,
                                          Classification.SECOND_LINE,false,4);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Create an instance of a Canadian Leader (that throws an exception).
        // NOTE: It is only necessary to test the modifier, since all the other
        //       exceptions have been tested above as part of the creation of a
        //       Squad.

        System.out.println();
        System.out.println("Testing Exception handling during Leader creation:");
        System.out.println();
        System.out.println("Invalid (less than minimum) modifier parameter:");
        System.out.println();

        // Invalid Modifier (Minimum)

        try
        {
            Leader Grandpa = new Leader(Nationality.BRITISH,"Sgt. Powell",
                                        UnitType.CANADIAN,10,10,5,-4);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        System.out.println();
        System.out.println("Invalid (greater than maximum) modifier parameter:");
        System.out.println();

        // Invalid Modifier (Maximum)

        try
        {
            Leader Grandpa = new Leader(Nationality.BRITISH,"Sgt. Powell",
                                        UnitType.CANADIAN,10,10,5,4);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Test the Dice class.

        System.out.println();
        System.out.println("Testing the execution of the Dice class:");
        System.out.println();

        Dice theDice;

        for (int i = 0; i < 12; i++)
        {
            try
            {
                theDice = new Dice();

                // Show the results.

                System.out.print(theDice.toString());
                System.out.println();
            }

            catch (IllegalStateException e)
            {
                System.out.println("Caught: " + e);
            }
        }
    }
}
