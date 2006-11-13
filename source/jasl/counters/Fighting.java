// ************************************************************************** //
// Fighting.java - This class is a member of the Counters package, which      //
//                 contains the class definitions and implementations for     //
//                 objects used to represent the virtual playing pieces in    //
//                 jASL.                                                      //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader,      //
//                       which was created by The Avalon Hill Game Company,   //
//                       and lives on at <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.              //
//                                                                            //
// Written By    : Craig R. Campbell  -  December 1998                        //
//                                                                            //
// $Id: Fighting.java,v 1.10 2006/11/13 22:47:25 campbell Exp $
// ************************************************************************** //

package Counters;

/**
 * This class is used to define units that have dynamic characteristics
 * (movement, firing, etc). This class is strictly a superclass and cannot be
 * instantiated directly.
 *
 * @version 1.10
 * @author Craig R. Campbell
 * @see <A HREF="../../source/Counters/Fighting.html">Source code</A>
 */

abstract class Fighting extends Unit
{
    // Public symbolic constants

    /** Minimum valid firepower value : <B>0</B>                    */

    public static final int MIN_FIREPOWER           = 0;

    /** Minimum valid movement (factors or points) value : <B>0</B> */

    public static final int MIN_MOVEMENT            = 0;

    /** Minimum valid range value : <B>0</B>                        */

    public static final int MIN_RANGE               = 0;

    /** Minimum valid portage value : <B>0</B>                      */

    public static final int MIN_PORTAGE_VALUE       = 0;

    /** <A NAME="_MAX_PORTAGE_VALUE_"></A> Maximum valid portage value : <B>99</B>                     */

    public static final int MAX_PORTAGE_VALUE       = 99;

    /** Minimum valid leadership modifier value : <B>-3</B>         */

    public static final int MIN_LEADERSHIP_MODIFIER = -3;

    /** Maximum valid leadership modifier value : <B>1</B>          */

    public static final int MAX_LEADERSHIP_MODIFIER =  1;

    // Private symbolic constants

    // This constant is used as part of the error messages (see below) that are
    // generated when an exception is thrown.

    private static final String CLASS_NAME = "Fighting";

    // Private data members

    // This variable contains the nationality of the derived object of this
    // class (ie. "American" or "German"). It must match one of the values found
    // in the <A HREF="Counter.html#_NATIONALITIES_">NATIONALITIES</A> list.

    private String nationality;

    // This variable contains a simple identifier for the counter, typically
    // a single alphanumeric character. It is also used to store the full name
    // for <A HREF="Leader.html">Leader</A>s and Heroes.

    private String identity;

    // This variable is used to store the specific type of the counter. It is
    // intended for vehicles (Pz VIb, T-34/76, etc.) and weapons (FlaK 30, ATR,
    // etc.) for more precise identification. Infantry units will typically
    // have the same value as Unit.description but this could also be used for
    // special infantry designations such as SS, Gurkha, Paratroopers, etc.
    // Examples of possible values are found in the <A HREF="Counter.html#_UNIT_TYPES_">UNIT_TYPES</A> list. If a value
    // from this list is specified, it will be checked against the specified
    // nationality and, in some cases, the description.

    private String unitType;

    // This variable contains the firepower value of the derived object of this
    // class (ie. "4" or "88LL").

    private String firepower;

    // This variable contains the normal range of the derived object of this
    // class (ie. "6").

    private int normalRange;

    // This variable contains the level of portage points of a derived object
    // of this class (how much it costs to carry a unit of this type). If this
    // value is set to <A HREF="#_MAX_PORTAGE_VALUE_">MAX_PORTAGE_VALUE</A>, it cannot be carried by another unit.

    private int portageValue;

    // This flag variable indicates whether or not the counter that this object
    // represents is capable of spraying fire. This is indicated on the physical
    // counter by an underscored range value.

    private boolean sprayFireCapable;

    // This variable contains the current status of the derived object of this
    // class. The labels for the status values are defined as protected symbolic
    // constants in the appropriate subclass. This value is used as a bit-field
    // with the status values corresponding to the individual bits.

    private int status;

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
    // Fighting. The parameters are passed up the chain from the object being
    // created.

    protected Fighting(String description,String nationality,String identity,
                       String unitType,String firepower,int normalRange,
                       int portageValue,boolean sprayFireCapable)
    {
        // Pass the first parameter to the superclass constructor. If any
        // exceptions are thrown, assume that they will be caught and handled by
        // the program creating the object.

        super(description);

        // Check the value of each remaining parameter and copy the value to
        // the local copy of the corresponding variable if an exception is not
        // found.

        // Nationality

        if (nationality == null)
        {
            throw new NullPointerException(nullPointerError);
        }

        if (nationality.length() == 0)
        {
            throw new IllegalArgumentException(zeroLengthArgumentError);
        }

        // Check the nationality parameter against the valid entries list and
        // set the local copy if it is valid.

        if (NATIONALITIES_VECTOR.contains(nationality))
        {
            this.nationality = nationality;
        }

        // Throw an exception if a match was not found.

        else
        {
            throw new IllegalArgumentException(invalidArgumentError +
                                               nationality);
        }

        // Identity

        setIdentity(identity);

        // Unit Type

        if (unitType == null)
        {
            throw new NullPointerException(nullPointerError);
        }

        if (unitType.length() == 0)
        {
            throw new IllegalArgumentException(zeroLengthArgumentError);
        }

        if ((unitType.equals(PARATROOPS)) && (!(nationality.equals(AMERICAN))))
        {
            throw new IllegalArgumentException(invalidArgumentError +
                                               nationality + AND_SEPARATOR +
                                               unitType);
        }

        if (((unitType.equals(AIRBORNE))    || (unitType.equals(ANZAC))       ||
             (unitType.equals(CANADIAN))    || (unitType.equals(FREE_FRENCH)) ||
             (unitType.equals(FREE_POLISH)) || (unitType.equals(GUARDSMEN))   ||
             (unitType.equals(GURKHA)))     && (!(nationality.equals(BRITISH))))
        {
            throw new IllegalArgumentException(invalidArgumentError +
                                               nationality + AND_SEPARATOR +
                                               unitType);
        }

        if ((unitType.equals(SISSI)) && (!(nationality.equals(FINNISH))))
        {
            throw new IllegalArgumentException(invalidArgumentError +
                                              nationality + AND_SEPARATOR +
                                              unitType);
        }

        if (((unitType.equals(SS)) || (unitType.equals(ENGINEERS))) &&
            (!(nationality.equals(GERMAN))))
        {
            throw new IllegalArgumentException(invalidArgumentError +
                                               nationality + AND_SEPARATOR +
                                               unitType);
        }

        if (((unitType.equals(COMMISSAR)) || (unitType.equals(GUARDS))) &&
            (!(nationality.equals(RUSSIAN))))
        {
            throw new IllegalArgumentException(invalidArgumentError +
                                               nationality + AND_SEPARATOR +
                                               unitType);
        }

        if ((unitType.equals(COMMISSAR)) && (!(description.equals(LEADER))))
        {
            throw new IllegalArgumentException(invalidArgumentError +
                                               description +
                                               AND_SEPARATOR + unitType);
        }

        this.unitType = unitType;

        // Firepower

        if (firepower == null)
        {
            throw new NullPointerException(nullPointerError);
        }

        if (firepower.length() == 0)
        {
            throw new IllegalArgumentException(zeroLengthArgumentError);
        }

        // Additional checks of the firepower value may be performed in
        // subclasses. For example, a firepower value of "20L" specified for an
        // an infantry unit would cause an exception to be thrown.

        this.firepower = firepower;

        // Normal Range

        if (normalRange < MIN_RANGE)
        {
            throw new IllegalArgumentException(invalidArgumentError +
                                               normalRange);
        }

        this.normalRange = normalRange;

        // Portage Value

        if ((portageValue < MIN_PORTAGE_VALUE) ||
            (portageValue > MAX_PORTAGE_VALUE))
        {
            throw new IllegalArgumentException(invalidArgumentError +
                                               portageValue);
        }

        this.portageValue = portageValue;

        // Spray Fire Capability

        this.sprayFireCapable = sprayFireCapable;

        // Set the initial status to the default. This value will be set more
        // specifically by a subclass constructor or the calling program through
        // the use of the setStatus() method.

        this.status = DEFAULT_INT_VALUE;
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

        // Nationality

        try
        {
            returnString.append(formatTextString(NATIONALITY_LABEL,
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
            returnString.append(formatTextString(getNationality(),
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

        // Unit Type

        try
        {
            returnString.append(formatTextString(UNIT_TYPE_LABEL,
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
            returnString.append(formatTextString(getUnitType(),
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

        // Identity

        try
        {
            returnString.append(formatTextString(IDENTITY_LABEL,
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
            returnString.append(formatTextString(getIdentity(),
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

        // Firepower

        try
        {
            returnString.append(formatTextString(FIREPOWER_LABEL,
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
            returnString.append(formatTextString(getFirepower(),
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

        // Spray Fire Capability

        try
        {
            returnString.append(formatTextString(CAN_SPRAY_FIRE_LABEL,
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
            returnString.append(formatTextString(canSprayFire() ? YES : NO,
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

        // Normal Range

        try
        {
            returnString.append(formatTextString(NORMAL_RANGE_LABEL,
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
            returnString.append(formatTextString(getNormalRange(),
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

        // Portage Value

        try
        {
            returnString.append(formatTextString(PORTAGE_VALUE_LABEL,
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
            returnString.append(formatTextString(getPortageValue(),
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

        // Status

        try
        {
            returnString.append(formatTextString(STATUS_LABEL,
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
            returnString.append(formatTextString(getStatus(),
                                                 80 - FIRST_COLUMN_LABEL_WIDTH,
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
     * Determine the nationality of this unit. The recognized nationalities are
     * listed below.
     *
     * @return a <CODE>String</CODE> specifying the unit's nationality.
     *
     * @see Counter#NATIONALITIES
     * @see Counter#NATIONALITIES_VECTOR
     * @see Counter#ALLIED_MINOR
     * @see Counter#AMERICAN
     * @see Counter#AXIS_MINOR
     * @see Counter#BRITISH
     * @see Counter#FINNISH
     * @see Counter#FRENCH
     * @see Counter#GERMAN
     * @see Counter#ITALIAN
     * @see Counter#JAPANESE
     * @see Counter#PARTISAN
     * @see Counter#RUSSIAN
     */

    public String getNationality()
    {
        return (nationality);
    }

    /**
     * Determine the identity of this unit. This is typically a single
     * alphanumeric character, but it may also be a full name (ie. for <B><A HREF="Leader.html">Leader</A></B>s
     * and Heroes).
     *
     * @return a <CODE>String</CODE> specifying the unit's identity.
     */

    public String getIdentity()
    {
        return (identity);
    }

    /**
     * Determine the type of this unit. This provides more accurate
     * identification and application of attributes associated with specific
     * unit types. For example, it may specify vehicle names (Pz VIb, T-34/76,
     * etc.) as well as special infantry designations (SS, Gurkha, Paratroopers,
     * etc.).
     *
     * @return a <CODE>String</CODE> specifying a more precise description of the unit's
     * nationality, type, or capability.
     *
     * @see Counter#UNIT_TYPES
     * @see Counter#UNIT_TYPES_VECTOR
     * @see Counter#AIRBORNE
     * @see Counter#ANZAC
     * @see Counter#CANADIAN
     * @see Counter#COMMISSAR
     * @see Counter#ENGINEERS
     * @see Counter#FREE_FRENCH
     * @see Counter#FREE_POLISH
     * @see Counter#GUARDS
     * @see Counter#GUARDSMEN
     * @see Counter#GURKHA
     * @see Counter#PARATROOPS
     * @see Counter#SISSI
     * @see Counter#SS
     */

    public String getUnitType()
    {
        return (unitType);
    }

    /**
     * Determine the firepower of this unit. This may be an infantry fire table
     * (IFT) value or a gun type designation (ie. 88LL).
     *
     * @return a <CODE>String</CODE> specifying the unit's firepower.
     */

    public String getFirepower()
    {
        return (firepower);
    }

    /**
     * Determine the maximum range that this unit may fire its weapon(s) at full
     * effect.
     *
     * @return a <CODE>String</CODE> specifying the normal range of the unit's weapon(s).
     */

    public String getNormalRange()
    {
        return (Integer.toString(normalRange));
    }

    /**
     * Determine the portage value of this unit. This is a measure of the "cost"
     * to another unit to carry this unit. If the value is <A HREF="#_MAX_PORTAGE_VALUE_">MAX_PORTAGE_VALUE</A>,
     * the unit may not be carried.
     *
     * @return a <CODE>String</CODE> specifying the portage value.
     */

    public String getPortageValue()
    {
        return (Integer.toString(portageValue));
    }

    /**
     * Determine if this unit is capable of spraying fire (attacking two
     * adjacent hexes with a single fire action). This is indicated on the
     * physical counter by an underscored range value.
     *
     * @return a <CODE>boolean</CODE> indicating if a unit has this capability.
     */

    public boolean canSprayFire()
    {
        return (sprayFireCapable);
    }

    // The following methods will be overridden by subclass versions.

    /**
     * Determine the current status of this unit.
     *
     * @return a comma delimited <CODE>String</CODE> describing the unit status. The list
     * will include one or more of the string constants, defined in the
     * subclasses, that are associated with unit status.
     *
     * @see #setStatus
     */

    public String getStatus()
    {
        return (DEFAULT_STRING_VALUE);
    }

    /**
     * Attempt to restore this unit's status to <A HREF="Unit.html#_NORMAL_">NORMAL</A>.
     *
     * @param isLeaderPresent indicates if a <B><A HREF="Leader.html">Leader</A></B> is present, which may
     * determine if a restoration attempt can be made or not.
     * @param modifier the applicable dice roll modifier for the attempt.
     * This includes leadership DRM as well as other factors.
     *
     * @return a <CODE>boolean</CODE> indicating if the status of a unit is changed as a
     * result of calling this method.
     */

    public boolean restore(boolean isLeaderPresent,int modifier)
    {
        return (DEFAULT_FLAG_VALUE);
    }

    /**
     * Perform a morale or task check on this unit.
     *
     * @param modifier the applicable dice roll modifier for the check.
     * This includes leadership DRM as well as other factors.
     *
     * @return a <CODE>boolean</CODE> indicating if the status of a unit is changed as a
     * result of calling this method.
     */

    public boolean check(int modifier)
    {
        return (DEFAULT_FLAG_VALUE);
    }

    // Public update methods

    /**
     * Set a new identity for this unit.
     *
     * @param newIdentity the new identity.
     *
     * @throws NullPointerException in the case of a null identity
     * @throws IllegalArgumentException in the case of a zero length identity
     */

    public void setIdentity(String newIdentity)
    {
        // Define local constants.

        String METHOD_NAME = "setIdentity";

        // Check the parameters received and throw the appropriate exception
        // if necessary.

        if (newIdentity == null)
        {
            throw new NullPointerException(buildErrorMessage(CLASS_NAME,
                                                             METHOD_NAME,
                                                             NULL_PARAMETER_MSG));
        }

        if (newIdentity.length() == 0)
        {
            throw new IllegalArgumentException(buildErrorMessage(CLASS_NAME,
                                                                 METHOD_NAME,
                                                                 ZERO_LENGTH_PARAMETER_MSG));
        }

        identity = newIdentity;
    }

    /**
     * Set the status for this unit. The interpretation of the status value
     * will be determined by the subclass type.
     *
     * @param newStatus the new status. This value will be used as a mask
     * to set one or more bits in the status value.
     *
     * @see #getStatus
     */

    public void setStatus(int newStatus)
    {
        if (newStatus >= DEFAULT_INT_VALUE)
        {
            status = newStatus;
        }
    }
}
