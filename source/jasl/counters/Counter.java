// ************************************************************************** //
// Counter.java - This interface is part of the Counters package, which       //
//                contains the class definitions and implementations for      //
//                objects used to represent the virtual playing pieces in     //
//                jASL.                                                       //
//                                                                            //
//                NOTE: This program is based on Advanced Squad Leader, a     //
//                product of The Avalon Hill Game Company.                    //
//                                                                            //
// Written By: Craig R. Campbell  -  September 2001                           //
//                                                                            //
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Counter.java,v 1.1 2001/12/08 06:37:55 craig Exp $
// ************************************************************************** //

package Counters;

import java.io.*;
import java.util.*;

// ************************************************************************** //
// Counter interface - This interface is used to define public constants for  //
//                     classes in the Counters package.                       //
// ************************************************************************** //

public interface Counter extends Serializable
{
	// Public symbolic constants

	// These constants are available to all programs and can be used to specify
	// parameters for new objects or to build menus.

	// These constants are available to all subclasses and are used to describe
	// the counter types that may be directly instantiated using classes in the
	// Counters hierarchy.

	public static final String CREW        = "Crew";
	public static final String HALF_SQUAD  = "Half Squad";
	public static final String LEADER      = "Leader";
	public static final String SQUAD       = "Squad";

	// This static array is used in the constructor to verify the description
	// parameter passed to it when an object derived from Unit is instantiated.
	// The description parameter is generally specified as a constant in each
	// of the public classes derived from Unit. The list and the check of the
	// variable in the constructor are included for consistency.

	public static final String[] DESCRIPTIONS = { CREW, HALF_SQUAD, LEADER,
	                                              SQUAD };

	// This static Vector provides an alternative method of accessing the
	// DESCRIPTIONS array. Its primary purpose is to allow the checking of a
	// string against the elements of the array without an additional loop.

	public static final Vector DESCRIPTIONS_VECTOR = new Vector(Arrays.asList(DESCRIPTIONS));

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
	// defined here so that it is publicly accessible. It also provides the
	// potential for the list to be used for building a menu containing possible
	// or valid options.

	public static final String[] NATIONALITIES = { ALLIED_MINOR, AMERICAN,
	                                               AXIS_MINOR, BRITISH, FINNISH,
	                                               FRENCH, GERMAN, ITALIAN,
	                                               JAPANESE, PARTISAN,
	                                               RUSSIAN };

	// This static Vector provides an alternative method of accessing the
	// NATIONALITIES array. Its primary purpose is to allow the checking of a
	// string against the elements of the array without an additional loop.

	public static final Vector NATIONALITIES_VECTOR = new Vector(Arrays.asList(NATIONALITIES));

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
	// defined here so that it is publicly accessible. It also provides the
	// potential for the list to be used for building a menu containing possible
	// or valid options.

	public static final String[] UNIT_TYPES = { PARATROOPS, AIRBORNE, ANZAC,
	                                            CANADIAN, FREE_FRENCH,
	                                            FREE_POLISH, GUARDSMEN, GURKHA,
	                                            SISSI, SS, ENGINEERS, COMMISSAR,
	                                            GUARDS };

	// This static Vector provides an alternative method of accessing the
	// UNIT_TYPES array. Its primary purpose is to allow the checking of a
	// string against the elements of the array without an additional loop.

	public static final Vector UNIT_TYPES_VECTOR = new Vector(Arrays.asList(UNIT_TYPES));

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

	public static final String[] CLASSIFICATIONS = { ELITE, FIRST_LINE,
	                                                 SECOND_LINE, GREEN,
	                                                 CONSCRIPT };

	// This static Vector provides an alternative method of accessing the
	// CLASSIFICATIONS array. Its primary purpose is to allow the checking of
	// a string against the elements of the array without an additional loop.

	public static final Vector CLASSIFICATIONS_VECTOR = new Vector(Arrays.asList(CLASSIFICATIONS));

	// This variable stores the number of elements in the DESCRIPTIONS array.

	public static final int DESCRIPTIONS_LIST_SIZE = DESCRIPTIONS.length;

	// This variable stores the number of elements in the NATIONALITIES array.

	public static final int NATIONALITIES_LIST_SIZE = NATIONALITIES.length;

	// This variable stores the number of elements in the UNIT_TYPES array.

	public static final int UNIT_TYPES_LIST_SIZE = UNIT_TYPES.length;

	// This variable stores the number of elements in the CLASSIFICATIONS array.

	public static final int CLASSIFICATIONS_LIST_SIZE = CLASSIFICATIONS.length;

	// The following strings are used to build the error messages (see below)
	// that appear when an exception is thrown. Each subclass will have a 
	// CLASS_NAME constant defined in it for this purpose as well.

	// The name of the method throwing the exception. These constants are
	// defined as necessary within each subclass.

	public static final String CONSTRUCTOR        = "constructor";

	// This is a separator used in messages associated with illegal argument
	// exceptions where there is a conflict between two distict values (see
	// checking of unitType in the Fighting for an example).

	public static final String AND_SEPARATOR     = " and ";

	// Error messages.

	public static final String NULL_PARAMETER_MSG     =
	    "Null parameter received.";
	public static final String ZERO_LENGTH_PARAMETER_MSG =
	    "Invalid parameter received (zero length).";
	public static final String INVALID_PARAMETER_MSG  =
	    "Invalid parameter received : ";

	// The following strings are used to provide labels in the output provided
	// by the toString() method in each class. They are also available for use
	// in the user interface as well.

	public static final String DESCRIPTION_LABEL         = "Description";
	public static final String NATIONALITY_LABEL         = "Nationality";
	public static final String IDENTITY_LABEL            = "Identity";
	public static final String UNIT_TYPE_LABEL           = "Unit Type";
	public static final String NORMAL_RANGE_LABEL        = "Normal Range";
	public static final String FIREPOWER_LABEL           = "Firepower";
	public static final String MOVEMENT_LABEL            = "Movement";
	public static final String PORTAGE_LEVEL_LABEL       = "Portage Level";
	public static final String PORTAGE_CAPACITY_LABEL    = "Portage Capacity";
	public static final String PORTAGE_VALUE_LABEL       = "Portage Value";
	public static final String CAN_SELF_RALLY_LABEL      = "Can Self Rally ?";
	public static final String STATUS_LABEL              = "Status";
	public static final String BPV_LABEL                 = "Basic Point Value";
	public static final String MORALE_LABEL              = "Morale";
	public static final String BROKEN_MORALE_LABEL       = "Broken Morale";
	public static final String HAS_MAXIMUM_ELR_LABEL     = "Has Maximum ELR ?";
	public static final String ELR_LABEL                 = "Experience Level Rating";
	public static final String CLASSIFICATION_LABEL      = "Classification";
	public static final String CAN_ASSAULT_FIRE_LABEL    = "Can Assault Fire ?";
	public static final String CAN_SPRAY_FIRE_LABEL      = "Can Spray Fire ?";
	public static final String SMOKE_PLACEMENT_EXP_LABEL = "Smoke Placement Exponent";
	public static final String MODIFIER_LABEL            = "Modifier";

	// The following values are used to set the column widths for the output of
	// the toString() method in each class.

	public static final int FIRST_COLUMN_LABEL_WIDTH     = 26;
	public static final int SECOND_COLUMN_VALUE_WIDTH    = 17;
	public static final int THIRD_COLUMN_LABEL_WIDTH     = 20;
	public static final int FOURTH_COLUMN_VALUE_WIDTH    = 17;

	// The following strings are used in the toString() method of each class to
	// indicate the current value of a boolean data member.

	public static final String YES = "Yes";
	public static final String NO  = "No";

	// This constant is used in the toString() method of classes that implement
	// this interface as a label in exception messages. It is appended to the
	// class name in each toString() method definition.

	public static final String TO_STRING_LABEL = "(toString) - ";

	// Access methods

	// toString - A method to display the values of the private data members
	//            of the current instance. The intent of this method is to
	//            provide text-based verification output for development and
	//            debugging.

	public abstract String toString();
}
