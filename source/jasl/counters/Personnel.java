// ************************************************************************** //
// Personnel.java - This class is a member of the Counters package, which     //
//                  contains the class definitions and implementations for    //
//                  objects used to represent the virtual playing pieces in   //
//                  jASL.                                                     //
//                                                                            //
//                  NOTE: This program is based on Advanced Squad Leader,     //
//                        which was created by The Avalon Hill Game Company,  //
//                        and lives on at <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.             //
//                                                                            //
// Written By     : Craig R. Campbell  -  December 1998                       //
//                                                                            //
// $Id: Personnel.java,v 1.8 2006/11/24 03:15:18 craig Exp $
// ************************************************************************** //

package Counters;

/**
 * This class is used to define the characteristics which are common to all
 * infantry units that represent more than one combat soldier. In the board
 * game, these units are referred to as multi-man counters (MMC). This class is
 * strictly a superclass and cannot be instantiated directly.
 *
 * @version 1.8
 * @author Craig R. Campbell
 * @see <A HREF="../../source/Counters/Personnel.html">Source code</A>
 */

class Personnel extends Infantry
{
    // Private symbolic constants

    // These constants are used in the constructor to pass the correct value of
    // a Personnel unit (multi-man counter) for each attribute. Other types of
    // <A HREF="Unit.html">Unit</A>s may allow the calling program to set these values but they are the
    // same for all MMCs. The movement allowance is reduced by one if the unit
    // is inexperienced.

    private static final int MOVEMENT_ALLOWANCE = 4;
    private static final int PORTAGE_CAPACITY   = 3;
    private static final int PORTAGE_VALUE      = 10;

    // This constant is used as part of the error messages (see below) that are
    // generated when an exception is thrown.

    private static final String CLASS_NAME = "Personnel";

    // Private data members

    // This variable is used to indicate that the unit that this object
    // represents is automatically given the <A HREF="Infantry.html#_MAX_ELR_">maximum ELR</A>. This flag affects how
    // the unit is replaced/reduced. It is indicated on the physical counter by
    // an underscored morale value.

    private boolean hasMaximumELR;

    // The purpose of the classification variable is to describe the experience
    // level of the object that it represents. It applies only to multi-man
    // units (squads, crews, etc).

    private String classification;

    // The following strings are used as messages for any exceptions that may be
    // generated by bad data being passed to the constructor.

    private static final String nullPointerError =
        buildErrorMessage(CLASS_NAME,CONSTRUCTOR,NULL_PARAMETER_MSG);

    private static final String zeroLengthArgumentError =
        buildErrorMessage(CLASS_NAME,CONSTRUCTOR,ZERO_LENGTH_PARAMETER_MSG);

    private static final String invalidArgumentError =
        buildErrorMessage(CLASS_NAME,CONSTRUCTOR,INVALID_PARAMETER_MSG);

    // Constructor

    // This constructor is used during the instantiation of classes derived from
    // Personnel. The parameters are passed up the chain from the object being
    // created.

    protected Personnel(String description,String nationality,String identity,
                        String unitType,String firepower,int normalRange,
                        boolean sprayFireCapable,int morale,int brokenMorale,
                        boolean selfRallyCapable,int basicPointValue,
                        int experienceLevelRating,boolean hasMaximumELR,
                        String classification)
    {
        // Pass the first 13 parameters to the superclass constructor. Note
        // that one or more variables has been set with symbolic constants.
        // These are defined at the beginning of this class and its
        // superclasses. If any exceptions are thrown, assume that they will be
        // caught and handled by the program creating the object.

        super(description,nationality,identity,unitType,firepower,normalRange,
              PORTAGE_VALUE,sprayFireCapable,MOVEMENT_ALLOWANCE,
              PORTAGE_CAPACITY,morale,brokenMorale,selfRallyCapable,
              basicPointValue,experienceLevelRating);

        // Check the value of the remaining parameter and copy the value to the
        // local copy of the corresponding variable if an exception is not
        // found.

        // Maximum ELR flag

        this.hasMaximumELR = hasMaximumELR;

        // Classification

        if (classification == null)
        {
            throw new NullPointerException(nullPointerError);
        }

        if (classification.length() == 0)
        {
            throw new IllegalArgumentException(zeroLengthArgumentError);
        }

        // Check the classification parameter against the valid entries list and
        // set the local copy if it is valid.

        if (CLASSIFICATIONS_VECTOR.contains(classification))
        {
            this.classification = classification;
        }

        // Throw an exception if a match was not found.

        else
        {
            throw new IllegalArgumentException(invalidArgumentError +
                                               classification);
        }
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

        // Maximum ELR flag

        try
        {
            returnString.append(formatTextString(HAS_MAXIMUM_ELR_LABEL,
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
            returnString.append(formatTextString(hasMaxELR() ? YES : NO,
                                                 SECOND_COLUMN_VALUE_WIDTH,
                                                 false,false));
        }

        catch (NullPointerException exception)
        {
            System.err.println(METHOD_LABEL + exception);
        }

        catch (IllegalArgumentException exception)
        {
            System.err.println(METHOD_LABEL + exception);
        }

        // Classification

        try
        {
            returnString.append(formatTextString(CLASSIFICATION_LABEL,
                                                 THIRD_COLUMN_LABEL_WIDTH,
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
            returnString.append(formatTextString(getClassification(),
                                                 FOURTH_COLUMN_VALUE_WIDTH,
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
     * Determine if this MMC inherently has the maximum experience level rating.
     * This is used to determine how a unit is replaced. It is indicated on the
     * physical counter by an underscored morale value.
     *
     * @return a <CODE>boolean</CODE> indicating if an MMC has this attribute.
     */

    public boolean hasMaxELR()
    {
        return (hasMaximumELR);
    }

    /**
     * Determine the classification of this unit. This is indicated on the front
     * of the physical counter by an alphanumeric character in the upper right
     * corner. The recognized values are listed below.
     *
     * @return a <CODE>String</CODE> specifying the classification.
     *
     * @see Counter#CLASSIFICATIONS
     * @see Counter#CLASSIFICATIONS_VECTOR
     * @see Counter#ELITE
     * @see Counter#FIRST_LINE
     * @see Counter#SECOND_LINE
     * @see Counter#GREEN
     * @see Counter#CONSCRIPT
     */

    public String getClassification()
    {
        return (classification);
    }
}
