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
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Counter.java,v 1.2 2002/02/21 06:35:22 craig Exp $
// ************************************************************************** //

package Counters;

import java.io.*;
import java.util.*;

/**
 * This interface is used to define public constants for the classes in the
 * Counters package.
 * @see <A HREF=../../docs/Counters/Counter.java.html>Source code</A>
 * @author Craig R. Campbell
 * @version 1.2
 */
public interface Counter extends Serializable
{
	// Public symbolic constants

	// These constants are available to all programs and can be used to specify
	// parameters for new objects or to build menus.

	// These constants are available to all subclasses and are used to describe
	// the counter types that may be directly instantiated using classes in the
	// Counters hierarchy.

	/**
	 * Indicates that the counter type of a unit is <B>Crew</B>
	 */
	public static final String CREW        = "Crew";

	/**
	 * Indicates that the counter type of a unit is
	 * <B>Half Squad</B>
	 */
	public static final String HALF_SQUAD  = "Half Squad";

	/**
	 * Indicates that the counter type of a unit is
	 * <B><A HREF=Leader.html>Leader</A></B>
	 */
	public static final String LEADER      = "Leader";

	/**
	 * Indicates that the counter type of a unit is
	 * <B><A HREF=Squad.html>Squad</A></B>
	 */
	public static final String SQUAD       = "Squad";

	// This static array is used in the constructor to verify the description
	// parameter passed to it when an object derived from Unit is instantiated.
	// The description parameter is generally specified as a constant in each
	// of the public classes derived from Unit. The list and the check of the
	// variable in the constructor are included for consistency.

	/**
	 * A list of the supported counter types. Each entry is directly associated
	 * with a public class derived from <A HREF=Unit.html>Unit</A>.
	 * @see Unit#getDescription
	 */
	public static final String[] DESCRIPTIONS = { CREW, HALF_SQUAD, LEADER,
	                                              SQUAD };
	/**
	 * An alternative method of accessing the list of supported counter types.
	 * @see Unit#getDescription
	 */
	public static final Vector DESCRIPTIONS_VECTOR = new Vector(Arrays.asList(DESCRIPTIONS));

	// Recognized nationality values. The nationality variable (see
	// Fighting.java) best describes the army that the Unit belongs to, not
	// necessarily the true nationality of the soldiers in that Unit.

	/**
	 * Indicates that a unit's nationality is <B>Allied Minor</B>
	 */
	public static final String ALLIED_MINOR = "Allied Minor";

	/**
	 * Indicates that a unit's nationality is <B>American</B>
	 */
	public static final String AMERICAN     = "American";

	/**
	 * Indicates that a unit's nationality is <B>Axis Minor</B>
	 */
	public static final String AXIS_MINOR   = "Axis Minor";

	/**
	 * Indicates that a unit's nationality is <B>British</B>
	 */
	public static final String BRITISH      = "British";

	/**
	 * Indicates that a unit's nationality is <B>Finnish</B>
	 */
	public static final String FINNISH      = "Finnish";

	/**
	 * Indicates that a unit's nationality is <B>French</B>
	 */
	public static final String FRENCH       = "French";

	/**
	 * Indicates that a unit's nationality is <B>German</B>
	 */
	public static final String GERMAN       = "German";

	/**
	 * Indicates that a unit's nationality is <B>Italian</B>
	 */
	public static final String ITALIAN      = "Italian";

	/**
	 * Indicates that a unit's nationality is <B>Japanese</B>
	 */
	public static final String JAPANESE     = "Japanese";

	/**
	 * Indicates that a unit's nationality is <B>Partisan</B>
	 */
	public static final String PARTISAN     = "Partisan";

	/**
	 * Indicates that a unit's nationality is <B>Russian</B>
	 */
	public static final String RUSSIAN      = "Russian";

	/**
	 * A list of the recognized unit nationalities.
	 * @see Fighting#getNationality
	 */
	public static final String[] NATIONALITIES = { ALLIED_MINOR, AMERICAN,
	                                               AXIS_MINOR, BRITISH, FINNISH,
	                                               FRENCH, GERMAN, ITALIAN,
	                                               JAPANESE, PARTISAN,
	                                               RUSSIAN };
	/**
	 * An alternative method of accessing the list of recognized unit
	 * nationalities. This object is used to verify that the nationality
	 * parameter specified for an object of a public class derived from
	 * <A HREF=Unit.html>Unit</A> matches one of the values found in the
	 * <A HREF=#NATIONALITIES>NATIONALITIES</A> array.
	 * @see Fighting#getNationality
	 */
	public static final Vector NATIONALITIES_VECTOR = new Vector(Arrays.asList(NATIONALITIES));

	// Recognized unitType values. These are used to identify specific types and
	// more specific nationalities for infantry units. The unitType variable can
	// also be used by the calling program to indicate other names or attributes
	// for the Unit it is applied to.

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>Paratroops</B>. If this value is specified as the unitType parameter
	 * for an object, the nationality parameter must be
	 * <A HREF=#AMERICAN>AMERICAN</A>.
	 */
	public static final String PARATROOPS   = "Paratroops";

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>Airborne</B>. If this value is specified as the unitType parameter for
	 * an object, the nationality parameter must be
	 * <A HREF=#BRITISH>BRITISH</A>.
	 */
	public static final String AIRBORNE     = "Airborne";

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>ANZAC</B>. If this value is specified as the unitType parameter for an
	 * object, the nationality parameter must be <A HREF=#BRITISH>BRITISH</A>.
	 */
	public static final String ANZAC        = "ANZAC";

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>Canadian</B>. If this value is specified as the unitType parameter for
	 * an object, the nationality parameter must be
	 * <A HREF=#BRITISH>BRITISH</A>.
	 */
	public static final String CANADIAN     = "Canadian";

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>Free French</B>. If this value is specified as the unitType parameter
	 * for an object, the nationality parameter must be
	 * <A HREF=#BRITISH>BRITISH</A>.
	 */
	public static final String FREE_FRENCH  = "Free French";

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>Free Polish</B>. If this value is specified as the unitType parameter
	 * for an object, the nationality parameter must be
	 * <A HREF=#BRITISH>BRITISH</A>.
	 */
	public static final String FREE_POLISH  = "Free Polish";

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>Guardsmen</B>. If this value is specified as the unitType parameter
	 * for an object, the nationality parameter must be
	 * <A HREF=#BRITISH>BRITISH</A>.
	 */
	public static final String GUARDSMEN    = "Guardsmen";

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>Gurkha</B>. If this value is specified as the unitType parameter for
	 * an object, the nationality parameter must be
	 * <A HREF=#BRITISH>BRITISH</A>.
	 */
	public static final String GURKHA       = "Gurkha";

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>Sissi</B>. If this value is specified as the unitType parameter for an
	 * object, the nationality parameter must be <A HREF=#FINNISH>FINNISH</A>.
	 */
	public static final String SISSI        = "Sissi";

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>SS</B>. If this value is specified as the unitType parameter for an
	 * object, the nationality parameter must be <A HREF=#GERMAN>GERMAN</A>.
	 */
	public static final String SS           = "SS";

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>Engineers</B>. If this value is specified as the unitType parameter
	 * for an object, the nationality parameter must be
	 * <A HREF=#GERMAN>GERMAN</A>.
	 */
	public static final String ENGINEERS    = "Engineers";

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>Commissar</B>. This value may only be specified as the unitType
	 * parameter for a <A HREF=Leader.html>Leader</A> object. The nationality
	 * parameter must be <A HREF=#RUSSIAN>RUSSIAN</A>.
	 */
	public static final String COMMISSAR    = "Commissar";

	/**
	 * Indicates that a unit's more precise nationality, type, or capability is
	 * <B>Guards</B>. If this value is specified as the unitType parameter for
	 * an object, the nationality parameter must be
	 * <A HREF=#RUSSIAN>RUSSIAN</A>.
	 */
	public static final String GUARDS       = "Guards";

	/**
	 * A list of the recognized unit nationalities, types, and capabilities.
	 * It is not necessary that the unitType value specified for a new object
	 * match a value in this list. If one of these values is specified, however,
	 * it will be checked against the nationality and/or description parameters.
	 * For example, attempting to create a French SS Squad will result in an
	 * exception.
	 * @see Fighting#getUnitType
	 */
	public static final String[] UNIT_TYPES = { PARATROOPS, AIRBORNE, ANZAC,
	                                            CANADIAN, FREE_FRENCH,
	                                            FREE_POLISH, GUARDSMEN, GURKHA,
	                                            SISSI, SS, ENGINEERS, COMMISSAR,
	                                            GUARDS };
	/**
	 * An alternative method of accessing the list of the recognized unit types.
	 * This object is used to verify that the unitType parameter specified for
	 * an object of a public class derived from <A HREF=Unit.html>Unit</A>, if
	 * it matches one of the of the values found in the
	 * <A HREF=#UNIT_TYPES>UNIT_TYPES</A> array, does not conflict with the
	 * nationality and/or description parameters.
	 * @see Fighting#getUnitType
	 */
	public static final Vector UNIT_TYPES_VECTOR = new Vector(Arrays.asList(UNIT_TYPES));

	// These constants are used to determine if the value of the classification
	// parameter passed to the constructor is valid. They are given public
	// attributes to allow external programs to access them when specifying
	// the classification parameter in the creation of Personnel objects.

	/**
	 * Indicates that a <A HREF=Personnel.html>Personnel</A> unit's
	 * classification is <B>Elite</B>
	 */
	public static final String ELITE           = "Elite";

	/**
	 * Indicates that a <A HREF=Personnel.html>Personnel</A> unit's
	 * classification is <B>1st Line</B>
	 */
	public static final String FIRST_LINE      = "1st Line";

	/**
	 * Indicates that a <A HREF=Personnel.html>Personnel</A> unit's
	 * classification is <B>2nd Line</B>
	 */
	public static final String SECOND_LINE     = "2nd Line";

	/**
	 * Indicates that a <A HREF=Personnel.html>Personnel</A> unit's
	 * classification is <B>Green</B>
	 */
	public static final String GREEN           = "Green";

	/**
	 * Indicates that a <A HREF=Personnel.html>Personnel</A> unit's
	 * classification is <B>Conscript</B>
	 */
	public static final String CONSCRIPT       = "Conscript";

	/**
	 * A list of the supported classifications.
	 * @see Personnel#getClassification
	 */
	public static final String[] CLASSIFICATIONS = { ELITE, FIRST_LINE,
	                                                 SECOND_LINE, GREEN,
	                                                 CONSCRIPT };
	/**
	 * An alternative method of accessing the list of recognized unit
	 * classifications. This object is used to verify that the classification
	 * parameter specified for an object of a public class derived from
	 * <A HREF=Unit.html>Unit</A> matches one of the values found in the
	 * <A HREF=#CLASSIFICATIONS>CLASSIFICATIONS</A> array.
	 * @see Personnel#getClassification
	 */
	public static final Vector CLASSIFICATIONS_VECTOR = new Vector(Arrays.asList(CLASSIFICATIONS));

	/**
	 * The number of elements in the <A HREF=#DESCRIPTIONS>DESCRIPTIONS</A>
	 * array and <A HREF=#DESCRIPTIONS_VECTOR>DESCRIPTIONS_VECTOR</A>.
	 */
	public static final int DESCRIPTIONS_LIST_SIZE    = DESCRIPTIONS.length;

	/**
	 * The number of elements in the <A HREF=#NATIONALITIES>NATIONALITIES</A>
	 * array and <A HREF=#NATIONALITIES_VECTOR>NATIONALITIES_VECTOR</A>.
	 */
	public static final int NATIONALITIES_LIST_SIZE   = NATIONALITIES.length;

	/**
	 * The number of elements in the <A HREF=#UNIT_TYPES>UNIT_TYPES</A> array
	 * and <A HREF=#UNIT_TYPES_VECTOR>UNIT_TYPES_VECTOR</A>.
	 */
	public static final int UNIT_TYPES_LIST_SIZE      = UNIT_TYPES.length;

	/**
	 * The number of elements in the
	 * <A HREF=#CLASSIFICATIONS>CLASSIFICATIONS</A> array and
	 * <A HREF=#CLASSIFICATIONS_VECTOR>CLASSIFICATIONS_VECTOR</A>.
	 */
	public static final int CLASSIFICATIONS_LIST_SIZE = CLASSIFICATIONS.length;

	// The following strings are used to build the error messages (see below)
	// that appear when an exception is thrown. Each subclass will have a 
	// CLASS_NAME constant defined in it for this purpose as well.

	// The name of the method throwing the exception. These constants are
	// defined as necessary within each subclass.

	/**
	 * Indicates that a message originated in the constructor of a specific
	 * class : <B>constructor</B>. This is provided primarily for building error
	 * messages for exceptions.
	 * @see Unit#buildErrorMessage
	 */
	public static final String CONSTRUCTOR        = "constructor";

	// This is a separator used in messages associated with illegal argument
	// exceptions where there is a conflict between two distinct values (see
	// checking of unitType in the Fighting constructor for an example).

	/**
	 * Separates multiple invalid parameters in error messages for exceptions :
	 * <B> and </B>. This is provided primarily for building error messages for
	 * exceptions.
	 * @see Unit#buildErrorMessage
	 */
	public static final String AND_SEPARATOR     = " and ";

	// Error messages.

	/**
	 * Indicates that a null parameter was received by a constructor or method :
	 * <B>Null parameter received</B>. This is provided primarily for building
	 * error messages for exceptions.
	 * @see Unit#buildErrorMessage
	 */
	public static final String NULL_PARAMETER_MSG        =
	    "Null parameter received.";

	/**
	 * Indicates that a zero length parameter was received by a constructor or
	 * method : <B>Invalid parameter received (zero length)</B>. This is
	 * provided primarily for building error messages for exceptions.
	 * @see Unit#buildErrorMessage
	 */
	public static final String ZERO_LENGTH_PARAMETER_MSG =
	    "Invalid parameter received (zero length).";

	/**
	 * Indicates that one or more invalid parameters was received by a
	 * constructor or method : <B>Invalid parameter(s) received : </B>. This is
	 * provided primarily for building error messages for exceptions.
	 * @see Unit#buildErrorMessage
	 */
	public static final String INVALID_PARAMETER_MSG     =
	    "Invalid parameter(s) received : ";

	// The following strings are used to provide labels in the output provided
	// by the toString() method in each class. They are available for use in the
	// user interface as well.

	/**
	 * Provides a label for a unit's counter type : <B>Description</B>
	 */
	public static final String DESCRIPTION_LABEL         = "Description";

	/**
	 * Provides a label for a unit's nationality : <B>Nationality</B>
	 */
	public static final String NATIONALITY_LABEL         = "Nationality";

	/**
	 * Provides a label for a unit's identity : <B>Identity</B>
	 */
	public static final String IDENTITY_LABEL            = "Identity";

	/**
	 * Provides a label for a unit's more precise nationality, type, or
	 * capability : <B>Unit Type</B>
	 */
	public static final String UNIT_TYPE_LABEL           = "Unit Type";

	/**
	 * Provides a label for a unit's normal range : <B>Normal Range</B>
	 */
	public static final String NORMAL_RANGE_LABEL        = "Normal Range";

	/**
	 * Provides a label for a unit's firepower : <B>Firepower</B>
	 */
	public static final String FIREPOWER_LABEL           = "Firepower";

	/**
	 * Provides a label for a unit's movement allowance : <B>Movement</B>
	 */
	public static final String MOVEMENT_LABEL            = "Movement";

	/**
	 * Provides a label for a unit's portage level : <B>Portage Level</B>
	 */
	public static final String PORTAGE_LEVEL_LABEL       = "Portage Level";

	/**
	 * Provides a label for a unit's portage capacity : <B>Portage Capacity</B>
	 */
	public static final String PORTAGE_CAPACITY_LABEL    = "Portage Capacity";

	/**
	 * Provides a label for a unit's portage value : <B>Portage Value</B>
	 */
	public static final String PORTAGE_VALUE_LABEL       = "Portage Value";

	/**
	 * Provides a label indicating if an <A HREF=Infantry.html>Infantry</A>
	 * unit has self rally capability : <B>Can Self Rally ?</B>
	 */
	public static final String CAN_SELF_RALLY_LABEL      = "Can Self Rally ?";

	/**
	 * Provides a label for a unit's status : <B>Status</B>
	 */
	public static final String STATUS_LABEL              = "Status";

	/**
	 * Provides a label for a unit's basic point value :
	 * <B>Basic Point Value</B>
	 */
	public static final String BPV_LABEL                 = "Basic Point Value";

	/**
	 * Provides a label for an <A HREF=Infantry.html>Infantry</A> unit's normal
	 * morale value : <B>Morale</B>
	 */
	public static final String MORALE_LABEL              = "Morale";

	/**
	 * Provides a label for an <A HREF=Infantry.html>Infantry</A> unit's broken
	 * morale value : <B>Broken Morale</B>
	 */
	public static final String BROKEN_MORALE_LABEL       = "Broken Morale";

	/**
	 * Provides a label indicating if an <A HREF=Infantry.html>Infantry</A>
	 * unit has the maximum experience level rating : <B>Has Maximum ELR ?</B>
	 */
	public static final String HAS_MAXIMUM_ELR_LABEL     = "Has Maximum ELR ?";

	/**
	 * Provides a label for an <A HREF=Infantry.html>Infantry</A> unit's
	 * experience level rating : <B>Experience Level Rating</B>
	 */
	public static final String ELR_LABEL                 = "Experience Level Rating";

	/**
	 * Provides a label for a <A HREF=Personnel.html>Personnel</A> (MMC) unit's
	 * classification : <B>Classification</B>
	 */
	public static final String CLASSIFICATION_LABEL      = "Classification";

	/**
	 * Provides a label indicating if a <A HREF=Squad.html>Squad</A> has assault
	 * fire capability : <B>Can Assault Fire ?</B>
	 */
	public static final String CAN_ASSAULT_FIRE_LABEL    = "Can Assault Fire ?";

	/**
	 * Provides a label indicating if a unit has spray fire capability :
	 * <B>Can Spray Fire ?</B>
	 */
	public static final String CAN_SPRAY_FIRE_LABEL      = "Can Spray Fire ?";

	/**
	 * Provides a label for a <A HREF=Squad.html>Squad</A>'s smoke placement
	 * capability : <B>Smoke Placement Exponent</B>
	 */
	public static final String SMOKE_PLACEMENT_EXP_LABEL = "Smoke Placement Exponent";

	/**
	 * Provides a label for a <A HREF=Leader.html>Leader</A>'s modifier value :
	 * <B>Modifier</B>
	 */
	public static final String MODIFIER_LABEL            = "Modifier";

	/**
	 * Indicates the current value of a boolean data member : <B>Yes</B>. This
	 * is provided primarily for use in the toString() method.
	 * @see #toString
	 */
	public static final String YES = "Yes";

	/**
	 * Indicates the current value of a boolean data member : <B>No</B>. This is
	 * provided primarily for use in the toString() method.
	 * @see #toString
	 */
	public static final String NO  = "No";

	/**
	 * A label used in the toString() method to build exception messages :
	 * <B>(toString) - </B>. It is appended to the class name in each toString()
	 * method definition.
	 * @see Unit#buildErrorMessage
	 */
	public static final String TO_STRING_LABEL = "(toString) - ";

	// Access methods

	/**
	 * Display the value of each of the private data members that describe an
	 * instance of a class that implements this interface. Each value should be
	 * preceded by a label defined in this interface. There should be no more
	 * than two values, including labels, in each line of output.
	 * @return a tabular <CODE>String</CODE>, 80 characters wide, with one or
	 * more lines.
	 */
	public abstract String toString();
}
