// ************************************************************************** //
// Driver.java - This file contains the Driver class, which is used to test   //
//               the basic functionality of the classes and methods in the    //
//               Counters package.                                            //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, a      //
//                     product of The Avalon Hill Game Company.               //
//                                                                            //
// Written By  : Craig R. Campbell  -  December 1998                          //
//                                                                            //
// $Header: /tmp/java/jasl.cvs/jasl/source/Driver.java,v 1.3 1999/09/14 05:43:04 craig Exp $
// ************************************************************************** //

import Counters.*;

public class Driver
{
	public static void main(String[] args)
	{
		// Create an instance of a German Leader.

		Leader germanLeader = new Leader("German","Lt. Fellbaum",9,9,true,-1);

		// Display all of the entered values for this instance using the 
		// showValues() method.

		System.out.println();
		System.out.println("Leader.showValues() output:");
		System.out.println();
		germanLeader.showValues();

		// Create an instance of a Russian Squad.

		Squad russianSquad = new Squad("Russian","A",4,4,7,7,false,7,
		                               Squad.FIRST_LINE);

		// Display all of the entered values for this instance using the 
		// showValues() method.

		System.out.println();
		System.out.println("Squad.showValues() output:");
		System.out.println();
		russianSquad.showValues();

		// Create an array of Unit objects. These will be used to reference a
		// Leader instance and several Squad instances. These class types are
		// derived from Unit.

		System.out.println("Building Unit array with a Leader & 3 Squads:");

		Unit[] UnitList = new Unit[4];

		UnitList[0] = new Leader("American","Sgt. Slaughter",9,9,true,-1);
		UnitList[1] = new Squad("American","X",6,6,6,6,false,7,Squad.FIRST_LINE);
		UnitList[2] = new Squad("American","Y",6,6,6,6,false,7,Squad.FIRST_LINE);
		UnitList[3] = new Squad("American","Z",6,6,6,6,false,7,Squad.FIRST_LINE);

		System.out.println("Displaying Unit array with a Leader & 3 Squads:");

		for (int i = 0; i < 4; i++)
		{
			System.out.println();
			System.out.println("UnitList[" + i + "]:");
			System.out.println();
			System.out.println(UnitList[i].getDescription());
			System.out.println(UnitList[i].getNationality());
			System.out.println(UnitList[i].getIdentity());
			System.out.println(UnitList[i].getUnitType());
			System.out.println(UnitList[i].getMovement());
			System.out.println(UnitList[i].getStatus());
			System.out.println(UnitList[i].getClassification());
			System.out.println(UnitList[i].getModifier());
		}

		// Create an instance of a German Squad (that throws some exceptions).

		System.out.println();
		System.out.println("Testing Exception handling during Squad creation:");
		System.out.println();
		System.out.println("Null nationality parameter:");
		System.out.println();

		// NULL Nationality

		try
		{
			Squad germanSquad = new Squad(null,"5",4,6,7,7,false,7,Squad.
			                              FIRST_LINE);
		}

		catch (NullPointerException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Zero-length nationality parameter:");
		System.out.println();

		// Blank Nationality

		try
		{
			Squad germanSquad = new Squad("","5",4,6,7,7,false,7,Squad.
			                              FIRST_LINE);
		}

		catch (IllegalArgumentException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Null identity parameter:");
		System.out.println();

		// Null Identity

		try
		{
			Squad germanSquad = new Squad("German",null,4,6,7,7,false,7,
			                              Squad.ELITE);
		}

		catch (NullPointerException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Zero-length identity parameter:");
		System.out.println();

		// Blank Identity

		try
		{
			Squad germanSquad = new Squad("German","",4,6,7,7,false,7,
			                              Squad.FIRST_LINE);
		}

		catch (IllegalArgumentException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Invalid (less than 0) firepower parameter:");
		System.out.println();

		// Invalid Firepower

		try
		{
			Squad germanSquad = new Squad("German","5",-1,6,7,7,false,7,
			                              Squad.SECOND_LINE);
		}

		catch (IllegalArgumentException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Invalid (less than 0) normal range parameter:");
		System.out.println();

		// Invalid Range

		try
		{
			Squad germanSquad = new Squad("German","5",4,-255,7,7,false,7,
			                              Squad.GREEN);
		}

		catch (IllegalArgumentException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Invalid (less than 0) morale parameter:");
		System.out.println();

		// Invalid Morale (Minimum)

		try
		{
			Squad germanSquad = new Squad("German","5",4,6,-1,7,false,7,
			                              Squad.GREEN);
		}

		catch (IllegalArgumentException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Invalid (greater than maximum) morale parameter:");
		System.out.println();

		// Invalid Morale (Maximum)

		try
		{
			Squad germanSquad = new Squad("German","5",4,6,11,7,false,7,
			                              Squad.GREEN);
		}

		catch (IllegalArgumentException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Invalid (less than 0) broken morale parameter:");
		System.out.println();

		// Invalid Broken Morale (Minimum)

		try
		{
			Squad germanSquad = new Squad("German","5",4,6,7,-7,false,7,
			                              Squad.GREEN);
		}

		catch (IllegalArgumentException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Invalid (greater than maximum) broken morale " + 
		                   "parameter:");
		System.out.println();

		// Invalid Broken Morale (Maximum)

		try
		{
			Squad germanSquad = new Squad("German","5",4,6,7,17,false,7,
			                              Squad.GREEN);
		}

		catch (IllegalArgumentException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Invalid (less than zero) basic point value (BPV):");
		System.out.println();

		// Invalid Basic Point Value (BPV)

		try
		{
			Squad germanSquad = new Squad("German","5",4,6,7,7,true,-1,
			                              Squad.GREEN);
		}

		catch (IllegalArgumentException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Null classification parameter:");
		System.out.println();

		// Null Classification

		try
		{
			Squad germanSquad = new Squad("German","A",4,6,7,7,false,7,null);
		}

		catch (NullPointerException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Zero-length classification parameter:");
		System.out.println();

		// Blank Classification

		try
		{
			Squad germanSquad = new Squad("German","SS",4,6,7,7,false,7,"");
		}

		catch (IllegalArgumentException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Invalid classification parameter:");
		System.out.println();

		// Invalid Classification

		try
		{
			Squad germanSquad = new Squad("German","4A",4,6,7,7,false,7,"REMF");
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
			Leader Grandpa = new Leader("Canadian","Sgt. Powell",10,10,true,-4);
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
			Leader Grandpa = new Leader("Canadian","Sgt. Powell",10,10,true,4);
		}

		catch (IllegalArgumentException e)
		{
			System.out.println("Caught: " + e);
		}

		System.out.println();
		System.out.println("Testing the execution of the Dice class:");
		System.out.println();

		// Test the Dice class.

		Dice theDice;

		for (int i = 0; i < 12; i++)
		{
			try
			{
				theDice = new Dice();

				// Show the results.

				theDice.showValues();
				System.out.println();
			}

			catch (IllegalStateException e)
			{
				System.out.println("Caught: " + e);
			}
		}
	}
}
