// ************************************************************************** //
// Counter.java - This interface is part of the Counters package, which       //
//                contains the class definitions and implementations for      //
//                objects used to represent the virtual playing pieces in     //
//                jASL.                                                       //
//                                                                            //
//                NOTE: This program is based on Advanced Squad Leader, which //
//                      was created by The Avalon Hill Game Company, and      //
//                      lives on at <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.                   //
//                                                                            //
// Written By: Craig R. Campbell  -  September 2001                           //
//                                                                            //
// $Id: Counter.java,v 1.8 2007/04/20 03:40:24 craig Exp $
// ************************************************************************** //

package jasl.Counters;

import java.io.*;

/**
 * This interface is used to define public constants for the classes in the
 * Counters package.
 *
 * @version 1.8
 * @author Craig R. Campbell
 * @see <A HREF="../../../source/jasl/Counters/Counter.html">Source code</A>
 */

public interface Counter extends Serializable
{
    // Public symbolic constants

    // These constants are available to all programs and can be used to specify
    // parameters for new objects or to build menus.

    // The following strings are used to build the error messages (see below)
    // that appear when an exception is thrown. Each subclass will have a
    // CLASS_NAME constant defined in it for this purpose as well.

    // The name of the method throwing the exception. These constants are
    // defined as necessary within each subclass.

    /**
     * Indicates that a message originated in the constructor of a specific
     * class : <B>constructor</B>
     *
     * @see Unit#buildErrorMessage
     */

    public static final String CONSTRUCTOR        = "constructor";

    // This is a separator used in messages associated with illegal argument
    // exceptions where there is a conflict between two distinct values (see
    // checking of unitType in the <A HREF="Fighting.html">Fighting</A> constructor for an example).

    /**
     * Separates multiple invalid parameters in error messages for exceptions :
     * <B> and </B>
     *
     * @see Unit#buildErrorMessage
     */

    public static final String AND_SEPARATOR     = " and ";

    // Error messages.

    /**
     * Indicates that a null parameter was received by a constructor or method :
     * <B>Null parameter received.</B>
     *
     * @see Unit#buildErrorMessage
     */

    public static final String NULL_PARAMETER_MSG        =
        "Null parameter received.";

    /**
     * Indicates that a zero length parameter was received by a constructor or
     * method :
     * <B>Invalid parameter received (zero length).</B>
     *
     * @see Unit#buildErrorMessage
     */

    public static final String ZERO_LENGTH_PARAMETER_MSG =
        "Invalid parameter received (zero length).";

    /**
     * Indicates that one or more invalid parameters was received by a
     * constructor or method :
     * <B>Invalid parameter(s) received : </B>
     *
     * @see Unit#buildErrorMessage
     */

    public static final String INVALID_PARAMETER_MSG     =
        "Invalid parameter(s) received : ";

    // The following strings are used to provide labels in the output provided
    // by the toString() method in each class. They are available for use in the
    // user interface as well.

    /**
     * Provides a label for a unit's identity : <B>Identity</B>
     */

    public static final String IDENTITY_LABEL            = "Identity";

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
     * Provides a label indicating if an <A HREF="Infantry.html">Infantry</A> unit has self rally
     * capability : <B>Can Self Rally ?</B>
     */

    public static final String CAN_SELF_RALLY_LABEL      = "Can Self Rally ?";

    /**
     * Provides a label for a unit's status : <B>Status</B>
     */

    public static final String STATUS_LABEL              = "Status";

    /**
     * Provides a label for a unit's basic point value : <B>Basic Point Value</B>
     */

    public static final String BPV_LABEL                 = "Basic Point Value";

    /**
     * Provides a label for an <A HREF="Infantry.html">Infantry</A> unit's normal morale value : <B>Morale</B>
     */

    public static final String MORALE_LABEL              = "Morale";

    /**
     * Provides a label for an <A HREF="Infantry.html">Infantry</A> unit's broken morale value :
     * <B>Broken Morale</B>
     */

    public static final String BROKEN_MORALE_LABEL       = "Broken Morale";

    /**
     * Provides a label indicating if an <A HREF="Infantry.html">Infantry</A> unit has the maximum
     * experience level rating : <B>Has Maximum ELR ?</B>
     */

    public static final String HAS_MAXIMUM_ELR_LABEL     = "Has Maximum ELR ?";

    /**
     * Provides a label for an <A HREF="Infantry.html">Infantry</A> unit's experience level rating :
     * <B>Experience Level Rating</B>
     */

    public static final String ELR_LABEL                 = "Experience Level Rating";

    /**
     * Provides a label indicating if a <A HREF="Squad.html">Squad</A> has assault fire capability :
     * <B>Can Assault Fire ?</B>
     */

    public static final String CAN_ASSAULT_FIRE_LABEL    = "Can Assault Fire ?";

    /**
     * Provides a label indicating if a unit has spray fire capability :
     * <B>Can Spray Fire ?</B>
     */

    public static final String CAN_SPRAY_FIRE_LABEL      = "Can Spray Fire ?";

    /**
     * Provides a label for a <A HREF="Squad.html">Squad</A>'s smoke placement capability :
     * <B>Smoke Placement Exponent</B>
     */

    public static final String SMOKE_PLACEMENT_EXP_LABEL = "Smoke Placement Exponent";

    /**
     * Provides a label for a <A HREF="Leader.html">Leader</A>'s modifier value : <B>Modifier</B>
     */

    public static final String MODIFIER_LABEL            = "Modifier";

    /**
     * A label used in the toString() method to build exception messages :
     * <B>(toString) - </B>
     * It is appended to the class name in each toString() method definition.
     *
     * @see Unit#buildErrorMessage
     */

    public static final String TO_STRING_LABEL = "(toString) - ";

    // The following strings are provided primarily for use in the toString()
    // method.

    /**
     * Indicates the current value of a boolean data member : <B>Yes</B>
     *
     * @see #toString
     */

    public static final String YES = "Yes";

    /**
     * Indicates the current value of a boolean data member : <B>No</B>
     *
     * @see #toString
     */

    public static final String NO  = "No";

    // Access methods

    /**
     * Display the value of each of the private data members that describe an
     * instance of a class that implements this interface. Each value should be
     * preceded by a label defined in this interface. There should be no more
     * than two values, including labels, in each line of output.
     *
     * @return a tabular <CODE>String</CODE>, 80 characters wide, with one or more lines.
     */

    public abstract String toString();
}
