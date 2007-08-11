// ************************************************************************** //
// Leader.java - This class is a member of the <B>counters</B> package, which        //
//               contains the class definitions and implementations for       //
//               objects used to represent the virtual playing pieces in      //
//               jASL.                                                        //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, which  //
//                     was created by The Avalon Hill Game Company, and lives //
//                     on at <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.                          //
//                                                                            //
// Written By  : Craig R. Campbell  -  December 1998                          //
//                                                                            //
// $Id: Leader.java,v 1.11 2007/08/11 05:15:12 craig Exp $
// ************************************************************************** //

package jasl.counters;

/**
 * This class is used to represent a Leader counter.
 *
 * @version 1.11
 * @author Craig R. Campbell
 * @see <A HREF="../../../source/jasl/counters/Leader.html">Source code</A>
 */

public final class Leader extends Infantry
{
    // Private symbolic constants

    // These constants are used in the constructor to pass the correct value for
    // a Leader for each attribute. Other types of units may allow the calling
    // program to set these values but they are the same for all Leaders.

    private static final int MOVEMENT_ALLOWANCE = 6;
    private static final int PORTAGE_CAPACITY   = 1;
    private static final int PORTAGE_VALUE      = 0;

    // This constant is used as part of the error messages (see below) that are
    // generated when an exception is thrown.

    private static final String CLASS_NAME = "Leader";

    // Private data members

    // This variable contains the modifier available to the derived object of
    // this class (ie. "-2"). This modifier allows the leader to affect the
    // outcome of actions that affect other "units" that share the same "space".

    private int modifier;

    // The following string is used as a message for any exceptions that may be
    // generated by bad data being passed to the constructor.

    private static final String invalidArgumentError =
        buildErrorMessage(LEADER,CONSTRUCTOR,INVALID_PARAMETER_MSG);

    // Constructor

    /**
     * Construct a new <CODE>Leader</CODE>.
     *
     * @param nationality the nationality of the Leader. Example - <B><A HREF="Nationality.html#_BRITISH_">BRITISH</A></B>
     * @param identity an identifier for the Leader.
     * Example - <B>"Sgt. Powell"</B>
     * @param unitType a more specific nationality, type, or capability
     * description for the Leader. Example - <B><A HREF="UnitType.html#_CANADIAN_">CANADIAN</A></B>
     * @param morale the morale level of the Leader in its unbroken state.
     * Example - <B>8</B>
     * @param brokenMorale the morale level of the Leader when it is
     * broken. Example - <B>9</B>
     * @param experienceLevelRating a value used for determining when a
     * Leader should be replaced with a lower quality Leader. Example - <B>4</B>
     * @param modifier the dice roll modifier (DRM) of the Leader.
     * Example - <B>-1</B>
     *
     * @throws NullPointerException in the case of a null <CODE>String</CODE> parameter.
     * @throws IllegalArgumentException in the case of a zero length <CODE>String</CODE>
     * parameter or an invalid numeric value.
     */

    public Leader(String nationality,String identity,String unitType,int morale,
                  int brokenMorale,int experienceLevelRating,int modifier)
    {
        // Pass the first 6 parameters to the superclass constructor. Note that
        // several variables have been set with symbolic constants. These are
        // defined at the beginning of this class and its superclasses. If any
        // exceptions are thrown, assume that they will be caught and handled by
        // the program creating the object.

        super(LEADER,nationality,identity,unitType,
              Integer.toString(MIN_FIREPOWER),MIN_RANGE,PORTAGE_VALUE,
              DEFAULT_FLAG_VALUE,MOVEMENT_ALLOWANCE,PORTAGE_CAPACITY,morale,
              brokenMorale,true,MIN_BPV,experienceLevelRating);

        // Check the value of the remaining parameter and copy the value to the
        // local copy of the variable if an exception is not found.

        // Modifier

        if ((modifier < MIN_LEADERSHIP_MODIFIER) ||
            (modifier > MAX_LEADERSHIP_MODIFIER))
        {
            throw new IllegalArgumentException(invalidArgumentError + modifier);
        }

        this.modifier = modifier;
    }

    // Public access methods

    /**
     * Display the value of each of the private data members that describe the
     * current instance. All of the members, beginning with the top-level class
     * (<B><A HREF="Unit.html">Unit</A></B>) and continuing down the hierarchy to this level, are appended to
     * the returned string. Each value is preceded by a label defined in the
     * <B><A HREF="Counter.html">Counter</A></B> interface. There are no more than two values, including labels,
     * in each line of output.
     *
     * @return a multi-line tabular <CODE>String</CODE>, 80 characters wide.
     */

    public String toString()
    {
        // Define local constants.

        String METHOD_LABEL = CLASS_NAME + TO_STRING_LABEL;

        // Create a buffer to store the string to be returned, initializing it
        // with the string defined in the parent class version of this method.

        StringBuffer returnString = new StringBuffer(super.toString());

        // Add the information describing the data stored in this class
        // instance.

        try
        {
            returnString.append(formatTextString(MODIFIER_LABEL,
                                                 FIRST_COLUMN_LABEL_WIDTH,
                                                 true,false));
        }

        catch (NullPointerException exception)
        {
            System.err.println(METHOD_LABEL + exception);
        }

        catch (IllegalArgumentException exception)
        {
            System.err.println(METHOD_LABEL + exception);
        }

        try
        {
            returnString.append(formatTextString(getModifier(),
                                                 SECOND_COLUMN_VALUE_WIDTH,
                                                 false,true));
        }

        catch (NullPointerException exception)
        {
            System.err.println(METHOD_LABEL + exception);
        }

        catch (IllegalArgumentException exception)
        {
            System.err.println(METHOD_LABEL + exception);
        }

        // Return the completed string to calling program.

        return (returnString.toString());
    }

    /**
     * Determine the dice roll modifier (DRM) of this Leader.
     *
     * @return a <CODE>String</CODE> specifying the modifier value.
     */

    public String getModifier()
    {
        return (Integer.toString(modifier));
    }
}
