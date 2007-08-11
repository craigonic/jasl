// ************************************************************************** //
// Description.java - This interface is part of the <B>counters</B> package, which   //
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
// $Id: Description.java,v 1.3 2007/08/11 05:15:12 craig Exp $
// ************************************************************************** //

package jasl.counters;

import java.util.*; // For Vector.

/**
 * This interface is used to define public constants and required methods
 * associated with the general description of a <A HREF="Unit.html">Unit</A>.
 *
 * @version 1.3
 * @author Craig R. Campbell
 * @see <A HREF="../../../source/jasl/counters/Description.html">Source code</A>
 */

public interface Description
{
    // Recognized description values. These constants represent the counter
    // types that may be directly instantiated using classes in the counters
    // hierarchy.

    /** <A NAME="_CREW_"></A>
     * Indicates that the counter type of a unit is <B>Crew</B>.
     */

    public static final String CREW        = "Crew";

    /** <A NAME="_HALF_SQUAD_"></A>
     * Indicates that the counter type of a unit is <B>Half Squad</B>.
     */

    public static final String HALF_SQUAD  = "Half Squad";

    /** <A NAME="_LEADER_"></A>
     * Indicates that the counter type of a unit is <B><A HREF="Leader.html">Leader</A></B>.
     */

    public static final String LEADER      = "Leader";

    /** <A NAME="_SQUAD_"></A>
     * Indicates that the counter type of a unit is <B><A HREF="Squad.html">Squad</A></B>.
     */

    public static final String SQUAD       = "Squad";

    /** <A NAME="_DESCRIPTIONS_"></A>
     * A list of the supported counter types. Each entry is directly associated
     * with a public class derived from <A HREF="Unit.html">Unit</A>.
     *
     * @see #getDescription
     */

    public static final String[] DESCRIPTIONS = { CREW, HALF_SQUAD, LEADER,
                                                  SQUAD };

    /** <A NAME="_DESCRIPTIONS_VECTOR_"></A>
     * An alternative method of accessing the list of supported counter types.
     *
     * @see #getDescription
     */

    public static final Vector DESCRIPTIONS_VECTOR = new Vector(Arrays.asList(DESCRIPTIONS));

    /**
     * The number of elements in the <A HREF="#_DESCRIPTIONS_">DESCRIPTIONS</A> array and <A HREF="#_DESCRIPTIONS_VECTOR_">DESCRIPTIONS_VECTOR</A>.
     */

    public static final int DESCRIPTIONS_LIST_SIZE    = DESCRIPTIONS.length;

    /**
     * Provides a label for a unit's counter type : <B>Description</B>
     */

    public static final String DESCRIPTION_LABEL         = "Description";

    // Access methods.

    /**
     * Determine the description of a unit. The recognized values are listed
     * below.
     *
     * @return a <CODE>String</CODE> specifying the unit description.
     *
     * @see #DESCRIPTIONS
     * @see #DESCRIPTIONS_VECTOR
     * @see #CREW
     * @see #HALF_SQUAD
     * @see #LEADER
     * @see #SQUAD
     */

    public abstract String getDescription();
}
