// ************************************************************************** //
// Squad.java - This class is a member of the Counters package, which         //
//              contains the class definitions and implementations for        //
//              objects used to represent the virtual playing pieces in jASL. //
//                                                                            //
//              NOTE: This program is based on Advanced Squad Leader, a       //
//                    product of The Avalon Hill Game Company.                //
//                                                                            //
// Written By : Craig R. Campbell  -  December 1998                           //
//                                                                            //
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Squad.java,v 1.1 1999/06/23 05:18:13 craig Exp $
// ************************************************************************** //

package Counters;

// ************************************************************************** //
// Squad class - This class is used to define the unique characteristics of   //
//               squad "units". Instances of this class may be instantiated   //
//               directly.                                                    //
// ************************************************************************** //

public class Squad extends Personnel
{
	// Private data members

	// This variable contains the classification of the derived object of this
	// class. The status is stored as a single character locally. A descriptive
	// string is returned to the calling program if the value of this variable
	// is requested. The purpose of this variable is to describe the experience
	// level of the object that it represents and applies only to multi-man
	// units (squads, crews, etc). The following classifications are recognized:

	// 'E' - Elite
	// '1' - 1st Line
	// '2' - 2nd Line
	// 'G' - Green
	// 'C' - Conscript

	private char classification;

	// Constructor

	// This constructor is used to instantiate a Squad object.

	public Squad(String nationality,byte movement,byte morale,byte brokenMorale,
	             boolean canSelfRally,byte firepower,byte normalRange,byte
				 portageCapacity,char classification)
	{
		// After passing the first 8 parameters to the superclass constructor,
		// check the value of the remaining parameter and copy the value to
		// the local copy of the corresponding variable.

		super("Squad",nationality,movement,morale,brokenMorale,
		      canSelfRally,firepower,normalRange,portageCapacity);

		if ((classification == 'E') || (classification == '1') ||
		    (classification == '2') || (classification == 'G') ||
			(classification == 'C'))
		{
			this.classification = classification;
		}
	}

	// Public access methods

	// showValues - A function to display the value of the private data members
	//              of the current instance. The intent of this function is to
	//              provide text-based verification output for development and
	//              debugging.

	public void showValues()
	{
		// Display data stored in the parent class.

		super.showValues();

		// Display data stored in this class.

		System.out.println("Classification\t: " + getClassification());
	}

	// getClassification - A function to return the value of the classification
	//                     member variable to the calling program. The value is
	//                     stored as a single character, but an associated
	//                     descriptive string is returned.

	public String getClassification()
	{
		switch(classification)
		{
			case 'E' : return ("Elite");
			case '1' : return ("1st Line");
			case '2' : return ("2nd Line");
			case 'G' : return ("Green");
			case 'C' : return ("Conscript");
			default  : return ("Unknown");
		}
	}
}
