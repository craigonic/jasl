// ************************************************************************** //
// Fighting.java - This class is a member of the Counters package, which      //
//                 contains the class definitions and implementations for     //
//                 objects used to represent the virtual playing pieces in    //
//                 jASL.                                                      //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader, a    //
//                       product of The Avalon Hill Game Company.             //
//                                                                            //
// Written By    : Craig R. Campbell  -  December 1998                        //
//                                                                            //
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Fighting.java,v 1.1 1999/06/12 05:58:55 craig Exp $
// ************************************************************************** //

package Counters;

// ************************************************************************** //
// Fighting class - This class is used to distinguish between units that have //
//                  dynamic characteristics (ie. movement, firing, etc). from //
//                  units that are used to modify a space on the "board"      //
//                  (ie. wire, foxholes, etc). Instances of this class may    //
//                  not be instantiated directly. It is strictly a parent     //
//                  class.                                                    //
// ************************************************************************** //

class Fighting extends Unit
{
	// Private data members

	// This variable contains the nationality of the derived object of this
	// class (ie. "American").

	private String nationality;

	// Constructors

	// This constructor is used during the instantiation of classes derived from
	// Fighting. The parameters are passed up the chain from the object being
	// created.

	protected Fighting(String description,String nationality)
	{
		// After passing the first parameter to the superclass constructor,
		// check the value of the remaining parameter and copy the value to
		// the local copy of the corresponding variable.

		super(description);

		if (nationality.length() > 0)
		{
			this.nationality = nationality;
		}
	}

	// Protected access methods

	// showValues - A function to display the value of the private data members
	//              of the current instance. The intent of this function is to
	//              provide text-based verification output for development and
	//              debugging. This function is only accessible to instances of
	//              the subclasses of Fighting and each subclass includes a
	//              function with the same name and purpose.

	protected void showValues()
	{
		// Display data stored in the parent class.

		super.showValues();

		// Display data stored in this class.

		System.out.println("Nationality\t: " + nationality);
	}

	// Public access methods

	// getNationality - A function to return the value of the nationality member
	//                  variable to the calling program.

	public String getNationality()
	{
		return (nationality);
	}
}
