// ************************************************************************** //
// Nationality.java - This interface is part of the <B>counters</B> package, which   //
//                    contains the class definitions and implementations for  //
//                    objects used to represent the virtual playing pieces in //
//                    jASL.                                                   //
//                                                                            //
//                    NOTE: This program is based on Advanced Squad Leader,   //
//                          which was created by The Avalon Hill Game         //
//                          Company, and lives on at <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.  //
//                                                                            //
// Written By: Craig R. Campbell  -  December 2006                            //
//                                                                            //
// $Id: Nationality.java,v 1.3 2007/08/11 05:15:12 craig Exp $
// ************************************************************************** //

package jasl.counters;

import java.util.*; // For Vector.

/**
 * This interface is used to define public constants and required methods
 * associated with the nationality of <A HREF="Fighting.html">Fighting</A> units.
 *
 * @version 1.3
 * @author Craig R. Campbell
 * @see <A HREF="../../../source/jasl/counters/Nationality.html">Source code</A>
 */

public interface Nationality
{
    // Recognized nationality values. The nationality variable (see
    // <A HREF="Fighting.html">Fighting</A>.java) best describes the army that the <A HREF="Unit.html">Unit</A> belongs to, not
    // necessarily the true nationality of the soldiers in that <A HREF="Unit.html">Unit</A>.

    /** <A NAME="_ALLIED_MINOR_"></A>
     * Indicates that a unit's nationality is <B>Allied Minor</B>.
     */

    public static final String ALLIED_MINOR         = "Allied Minor";

    /** <A NAME="_AMERICAN_"></A>
     * Indicates that a unit's nationality is <B>American</B>.
     */

    public static final String AMERICAN             = "American";

    /** <A NAME="_AXIS_MINOR_"></A>
     * Indicates that a unit's nationality is <B>Axis Minor</B>.
     */

    public static final String AXIS_MINOR           = "Axis Minor";

    /** <A NAME="_BRITISH_"></A>
     * Indicates that a unit's nationality is <B>British</B>.
     */

    public static final String BRITISH              = "British";

    /** <A NAME="_FINNISH_"></A>
     * Indicates that a unit's nationality is <B>Finnish</B>.
     */

    public static final String FINNISH              = "Finnish";

    /** <A NAME="_FRENCH_"></A>
     * Indicates that a unit's nationality is <B>French</B>.
     */

    public static final String FRENCH               = "French";

    /** <A NAME="_GERMAN_"></A>
     * Indicates that a unit's nationality is <B>German</B>.
     */

    public static final String GERMAN               = "German";

    /** <A NAME="_ITALIAN_"></A>
     * Indicates that a unit's nationality is <B>Italian</B>.
     */

    public static final String ITALIAN              = "Italian";

    /** <A NAME="_JAPANESE_"></A>
     * Indicates that a unit's nationality is <B>Japanese</B>.
     */

    public static final String JAPANESE             = "Japanese";

    /** <A NAME="_PARTISAN_"></A>
     * Indicates that a unit's nationality is <B>Partisan</B>.
     */

    public static final String PARTISAN             = "Partisan";

    /** <A NAME="_RUSSIAN_"></A>
     * Indicates that a unit's nationality is <B>Russian</B>.
     */

    public static final String RUSSIAN              = "Russian";

    /** <A NAME="_NATIONALITIES_"></A>
     * A list of the recognized unit nationalities.
     *
     * @see #getNationality
     */

    public static final String[] NATIONALITIES      = { ALLIED_MINOR, AMERICAN,
                                                        AXIS_MINOR, BRITISH,
                                                        FINNISH, FRENCH, GERMAN,
                                                        ITALIAN, JAPANESE,
                                                        PARTISAN, RUSSIAN };

    /** <A NAME="_NATIONALITIES_VECTOR_"></A>
     * An alternative method of accessing the list of recognized unit
     * nationalities.
     *
     * @see #getNationality
     */

    public static final Vector NATIONALITIES_VECTOR = new Vector(Arrays.asList(NATIONALITIES));

    /**
     * The number of elements in the <A HREF="#_NATIONALITIES_">NATIONALITIES</A> array and
     * <A HREF="#_NATIONALITIES_VECTOR_">NATIONALITIES_VECTOR</A>.
     */

    public static final int NATIONALITIES_LIST_SIZE = NATIONALITIES.length;

    /**
     * Provides a label for a unit's nationality : <B>Nationality</B>
     */

    public static final String NATIONALITY_LABEL    = "Nationality";

    // Access methods.

    /**
     * Determine the nationality of a unit. The recognized values are listed
     * below.
     *
     * @return a <CODE>String</CODE> specifying the unit's nationality.
     *
     * @see #ALLIED_MINOR
     * @see #AMERICAN
     * @see #AXIS_MINOR
     * @see #BRITISH
     * @see #FINNISH
     * @see #FRENCH
     * @see #GERMAN
     * @see #ITALIAN
     * @see #JAPANESE
     * @see #PARTISAN
     * @see #RUSSIAN
     */

    public abstract String getNationality();
}
