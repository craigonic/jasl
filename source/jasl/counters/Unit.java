// ************************************************************************** //
// Unit.java - This class is a member of the Counters package, which contains //
//             the class definitions and implementations for objects used to  //
//             to represent the virtual playing pieces in jASL.               //
//                                                                            //
//             NOTE: This program is based on Advanced Squad Leader, a        //
//                   product of The Avalon Hill Game Company.                 //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
//                                                                            //
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Unit.java,v 1.4 2000/08/05 06:15:40 craig Exp $
// ************************************************************************** //

package Counters;

// import java.util.*; // Add to use Vector

// ************************************************************************** //
// Unit class - This is the top-level class of the Counters package. All      //
//              the classes in this Package are derived from Unit.            //
// ************************************************************************** //

abstract public class Unit
{
	// Public symbolic constants

	// These constants are available to all programs and can be used to specify
	// parameters for new objects or to build menus.

	// Recognized nationality values. The nationality variable (see
	// Fighting.java) best describes the army that the Unit belongs to, not
	// necessarily the true nationality of the soldiers in that Unit.

	public static final String ALLIED_MINOR = "Allied Minor";
	public static final String AMERICAN     = "American";
	public static final String AXIS_MINOR   = "Axis Minor";
	public static final String BRITISH      = "British";
	public static final String FINNISH      = "Finnish";
	public static final String FRENCH       = "French";
	public static final String GERMAN       = "German";
	public static final String ITALIAN      = "Italian";
	public static final String JAPANESE     = "Japanese";
	public static final String PARTISAN     = "Partisan";
	public static final String RUSSIAN      = "Russian";

	// This static array is used in the Fighting constructor to verify the
	// nationality parameter passed to it when an object is instantiated. It is
	// defined in Unit so that it is publicly accessible. It also provides the
	// potential for the list to be used for building a menu containing possible
	// or valid options.

	// NOTE: The NATIONALITIES_LIST_SIZE protected constant (see below) must be
	//       updated if any elements are added to or deleted from the 
	//       NATIONALITIES array.

	public static final String[] NATIONALITIES = { ALLIED_MINOR, AMERICAN,
	                                               AXIS_MINOR, BRITISH, FINNISH,
	                                               FRENCH, GERMAN, ITALIAN,
	                                               JAPANESE, PARTISAN,
	                                               RUSSIAN };

	// Recognized unitType values. These are used to identify specific types and
	// more specific nationalities for infantry units. The unitType variable can
	// also be used by the calling program to indicate other names or attributes
	// for the Unit it is applied to.

	public static final String PARATROOPS   = "Paratroops";  // American
	public static final String AIRBORNE     = "Airborne";    // British
	public static final String ANZAC        = "ANZAC";
	public static final String CANADIAN     = "Canadian";
	public static final String FREE_FRENCH  = "Free French";
	public static final String FREE_POLISH  = "Free Polish";
	public static final String GUARDSMEN    = "Guardsmen";
	public static final String GURKHA       = "Gurkha";
	public static final String SISSI        = "Sissi";       // Finnish
	public static final String SS           = "SS";          // German
	public static final String ENGINEERS    = "Engineers";
	public static final String COMMISSAR    = "Commissar";   // Russian
	public static final String GUARDS       = "Guards";

	// This static array is used in the Fighting constructor to verify the
	// UnitType parameter passed to it when an object is instantiated. It is
	// defined in Unit so that it is publicly accessible. It also provides the
	// potential for the list to be used for building a menu containing possible
	// or valid options.

	// NOTE: The UNIT_TYPES_LIST_SIZE protected constant (see below) must be
	//       updated if any elements are added to or deleted from the 
	//       UNIT_TYPES array.

	public static final String[] UNIT_TYPES = { PARATROOPS, AIRBORNE, ANZAC,
	                                            CANADIAN, FREE_FRENCH,
	                                            FREE_POLISH, GUARDSMEN, GURKHA,
	                                            SISSI, SS, ENGINEERS, COMMISSAR,
	                                            GUARDS };

	// These constants are used to determine if the value of the classification
	// parameter passed to the constructor is valid. They are given public
	// attributes to allow external programs to access them when specifying
	// the classification parameter in the creation of Squad objects.

	public static final String ELITE           = "Elite";
	public static final String FIRST_LINE      = "1st Line";
	public static final String SECOND_LINE     = "2nd Line";
	public static final String GREEN           = "Green";
	public static final String CONSCRIPT       = "Conscript";

	// This static array is used in the constructor to verify the classification
	// parameter passed to it when an object derived from Personnel is
	// instantiated. It is publicly accessible, potentially allowing it to be
	// used in creating a menu.

	// NOTE: The CLASSIFICATIONS_LIST_SIZE protected constant (see below) must 
	//       be updated if any elements are added to or deleted from the 
	//       CLASSIFICATIONS array.

	public static final String[] CLASSIFICATIONS = { ELITE, FIRST_LINE,
	                                                 SECOND_LINE, GREEN,
	                                                 CONSCRIPT };

	// This is one way to redo this with a static Vector. This is, however, not
	// supported by the current installed version of gcj/libgcj.

	// public static final Vector classVector = new Vector(Arrays.asList(CLASSIFICATIONS));

	// Protected symbolic constants

	// These constants are available to all subclasses and are used to name
	// public classes of the Counters hierarchy that may be directly 
	// instantiated. These values end up getting sent to this class to become
	// the value of "description".

	protected static final String CREW        = "Crew";
	protected static final String HALF_SQUAD  = "Half Squad";
	protected static final String LEADER      = "Leader";
	protected static final String SQUAD       = "Squad";

	// This static array is used in the constructor to verify the description
	// parameter passed to it when an object derived from Unit is instantiated.
	// The description parameter is generally specified as a constant in each
	// of the public classes derived from Unit. The list and the check of the
	// variable in the constructor are included for consistency.

	// NOTE: The DESCRIPTIONS_LIST_SIZE protected constant (see below) must 
	//       be updated if any elements are added to or deleted from the 
	//       DESCRIPTIONS array.

	protected static final String[] DESCRIPTIONS = { CREW, HALF_SQUAD, LEADER,
	                                                 SQUAD };

	// This variable stores the number of elements in the DESCRIPTIONS array.

	protected static final int DESCRIPTIONS_LIST_SIZE = 4;

	// This variable stores the number of elements in the NATIONALITIES array.

	protected static final int NATIONALITIES_LIST_SIZE = 11;

	// This variable stores the number of elements in the UNIT_TYPES array.

	protected static final int UNIT_TYPES_LIST_SIZE = 13;

	// This variable stores the number of elements in the CLASSIFICATIONS array.

	protected static final int CLASSIFICATIONS_LIST_SIZE = 5;

	// Protected data members

	// The foundMatch variable is used in checking an input string parameter
	// against each of the values in one of the static lists defined above. This
	// is generally done in the constructor functions.

	static boolean foundMatch = false;

	// Private data members

	// This variable contains a descriptive name for the derived object of this
	// class. It is typically set to the name of the child class being
	// instantiated (ie. "Squad").

	private String description;

	// The following strings are used as messages for any exceptions that may be
	// generated by bad data being passed to the protected constructor.

	private static final String nullPointerError = 
	    "Error: Unit(constructor) - Null parameter received.";
		
	private static final String badArgumentError = 
	    "Error: Unit(constructor) - Invalid parameter received (zero length).";

	private static final String invalidArgumentError = 
	    "Error: Unit(constructor) - Invalid parameter received : ";

	// Constructors

	// Default constructor. This is used to create arrays of this data type
	// which can be used to reference groups of objects derived from Unit.

	public Unit() {}
	
	// This constructor is used during the instantiation of classes derived
	// from Unit. The parameter is passed up the chain from the object being
	// created.

	protected Unit(String description)
		throws NullPointerException, IllegalArgumentException
	{
		// Check the input value for any problems and throw the appropriate
		// exception if necessary.

		if (description == null)
		{
			throw new NullPointerException(nullPointerError);
		}

		if (description.length() == 0)
		{
			throw new IllegalArgumentException(badArgumentError);
		}

		// The foundMatch variable is used to indicate if an entry matching the
		// description parameter was found in the list of valid
		// descriptions (DESCRIPTIONS). foundMatch is a static variable defined
		// above.

		foundMatch = false;

		// Check the nationality parameter against the valid entries list.

		for (int i = 0; i < DESCRIPTIONS_LIST_SIZE; i++)
		{
			if (description.equals(DESCRIPTIONS[i]))
			{
				foundMatch = true;
				break;
			}
		}

		// Throw an exception if a match was not found.

		if (! foundMatch)
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   description);
		}

		// If we got this far, assume that the value is OK and copy it to the
		// local description variable. 

		// At this time, it is not possible for the user to set this value. It
		// is specified as a constant (see the list above) in the constructor
		// of the public subclasses of Unit.

		this.description = description;
	}

	// Protected access methods

	// showValues - A function to display the value of the private data members
	//              of the current instance. The intent of this function is to
	//              provide text-based verification output for development and
	//              debugging. This function is only accessible to instances of
	//              the subclasses of Unit and each subclass includes a function
	//              with the same name and purpose. Since this is the top-level
	//              of the hierarchy, this version also includes a header.

	protected void showValues()
	{
		// Display header.

		System.out.println("Counter package instance values:");
		System.out.println("--------------------------------");

		// Display data stored in this class.

		System.out.println("Description             : " + description);
	}

	// Public access methods

	// getDescription - A function to return the value of the description member
	//                  variable to the calling program.

	public String getDescription()
	{
		return (description);
	}

	// The following abstract methods are defined in the subclasses of Unit.
	// This is necessary in order to allow different public class types derived
	// from Unit to be stored and accessed as the generic Unit type. It is also
	// necessary in order to access the public access methods of the entire 
	// hierarchy without casting to a specific class type.

	// Fighting.java

	abstract public String  getNationality();
	abstract public String  getIdentity();
	abstract public String  getUnitType();
	abstract public int     getFirepower();
	abstract public int     getNormalRange();

	// Mobile.java

	abstract public int     getMovement();

	// Infantry.java

	abstract public String  getStatus();
	abstract public boolean rally(boolean isLeaderPresent,int modifier);
	abstract public boolean moraleCheck(int modifier);

	// Personnel.java

	abstract public String  getClassification();
	
	// Leader.java

	abstract public int     getModifier();
}
