// ************************************************************************** //
// Personnel.java - This class is a member of the Counters package, which     //
//                  contains the class definitions and implementations for    //
//                  objects used to represent the virtual playing pieces in   //
//                  jASL.                                                     //
//                                                                            //
//                  NOTE: This program is based on Advanced Squad Leader, a   //
//                        product of The Avalon Hill Game Company.            //
//                                                                            //
// Written By     : Craig R. Campbell  -  December 1998                       //
//                                                                            //
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Personnel.java,v 1.1 1999/06/23 05:12:15 craig Exp $
// ************************************************************************** //

package Counters;

// ************************************************************************** //
// Personnel class - This class is used to define the characteristics of all  //
//                   infantry "units" other than leaders, those that          //
//                   represent more than one combat soldier. The exception to //
//                   this definition is the Hero class. Instances of this     //
//                   class may not be instantiated directly. It is strictly a //
//                   parent class.                                            //
// ************************************************************************** //

class Personnel extends Infantry
{
	// Private data members

	// This variable contains the firepower available to the derived object of
	// this class (ie. "4").

	private byte firepower;

	// This variable contains the normal range of the derived object of this
	// class (ie. "6").

	private byte normalRange;

	// This variable contains the maximum portage points that the derived object
	// of this class can carry without affecting the number of movement points
	// available to it.

	private byte portageCapacity;

	// This variable contains the current number of portage points of equipment
	// that the derived object of this class is carrying.

	private byte portageLevel;

	// Constructors

	// This constructor is used to instantiate a Personnel object.

	protected Personnel(String description,String nationality,byte movement,byte
	                    morale,byte brokenMorale,boolean canSelfRally,byte
					    firepower,byte normalRange,byte portageCapacity)
	{
		// After passing the first 6 parameters to the superclass constructor,
		// check the values of the remaining 3 parameters and copy the values to
		// the local copy of each corresponding variable.

		super(description,nationality,movement,morale,brokenMorale,
		      canSelfRally);

		if (firepower > 0)
		{
			this.firepower = firepower;
		}

		if (normalRange > 0)
		{
			this.normalRange = normalRange;
		}

		if ((portageCapacity >= 0) && (portageCapacity < 11))
		{
			this.portageCapacity = portageCapacity;
		}

		this.portageLevel = 0; // Assume that weapon possession will be 
		                       // determined after the object is instantiated.
	}

	// Protected access methods

	// showValues - A function to display the value of the private data members
	//              of the current instance. The intent of this function is to
	//              provide text-based verification output for development and
	//              debugging. This function is only accessible to instances of
	//              the subclasses of Personnel and each subclass includes a
	//              function with the same name and purpose.

	protected void showValues()
	{
		// Display data stored in the parent class.

		super.showValues();

		// Display data stored in this class.

		System.out.println("Normal Range\t: " + normalRange + "\tFirepower\t: "
		                   + firepower);

		System.out.println("Portage Capacity: " + portageCapacity +
		                   "\tPortageLevel\t: " + portageLevel);
	}

	// Public access methods

	// getFirepower - A function to return the value of the firepower member
	//                variable to the calling program.

	public byte getFirepower()
	{
		return (firepower);
	}

	// getNormalRange - A function to return the value of the normalRange member
	//                  variable to the calling program.

	public byte getNormalRange()
	{
		return (normalRange);
	}
}
