// ************************************************************************** //
// Driver.java - This file contains the Driver class, which is used to test   //
//               the basic functionality of the classes and methods in the    //
//               Counter package.                                             //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, a      //
//                       product of The Avalon Hill Game Company.             //
//                                                                            //
// Written By  : Craig R. Campbell  -  December 1998                          //
//                                                                            //
// $Header: /tmp/java/jasl.cvs/jasl/source/Driver.java,v 1.1 1999/06/23 04:54:59 craig Exp $
// ************************************************************************** //

import Counters.*;

public class Driver
{
	public static void main(String[] args)
	{
		// Create an instance of a German Leader.

		Leader germanLeader = new Leader("German",6,9,9,true,-1,"Lt. Fellbaum");

		// Display all of the entered values for this instance using the 
		// showValues() method.

		System.out.println();
		germanLeader.showValues();

		// Create an instance of a Russian Squad.

		Squad russianSquad = new Squad("Russian",4,7,7,false,4,4,10,'1');

		// Display all of the entered values for this instance using the 
		// showValues() method.

		System.out.println();
		russianSquad.showValues();

		// Create an array of Unit objects. These will be used to reference a
		// Leader instance and several Squad instances. These class types are
		// derived from Unit.

		// Unit[] UnitList = new Unit[4];
		Unit aUnit;

		aUnit = new Leader("American",6,9,9,true,-1,"Sgt. Slaughter");

		System.out.println();
		System.out.println(aUnit.getDescription());
		System.out.println(aUnit.getNationality());
		System.out.println(aUnit.getMovement());
		System.out.println(aUnit.getStatus());
	}
}
