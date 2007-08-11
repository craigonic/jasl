// ************************************************************************** //
// Classification.java - This interface is part of the <B>counters</B> package,      //
//                       which contains the class definitions and             //
//                       implementations for objects used to represent the    //
//                       virtual playing pieces in jASL.                      //
//                                                                            //
//                       NOTE: This program is based on Advanced Squad        //
//                             Leader, which was created by The Avalon Hill   //
//                             Game Company, and lives on at                  //
//                             <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.                        //
//                                                                            //
// Written By: Craig R. Campbell  -  November 2006                            //
//                                                                            //
// $Id: Classification.java,v 1.4 2007/08/11 05:15:12 craig Exp $
// ************************************************************************** //

package jasl.counters;

import java.util.*; // For Vector.

/**
 * This interface is used to define public constants and required methods
 * associated with the classification of <A HREF="Personnel.html">Personnel</A> units.
 *
 * @version 1.4
 * @author Craig R. Campbell
 * @see <A HREF="../../../source/jasl/counters/Classification.html">Source code</A>
 */

public interface Classification
{
    // Public symbolic constants

    // These constants are used to determine if the value of the classification
    // parameter passed to a constructor is valid. They are given public
    // attributes to allow external programs to access them when specifying
    // the classification parameter in the creation of <A HREF="Personnel.html">Personnel</A> objects.

    /** <A NAME="_ELITE_"></A>
     * Indicates that a <A HREF="Personnel.html">Personnel</A> unit's classification is <B>Elite</B>.
     */

    public static final String ELITE                  = "Elite";

    /** <A NAME="_FIRST_LINE_"></A>
     * Indicates that a <A HREF="Personnel.html">Personnel</A> unit's classification is <B>1st Line</B>.
     */

    public static final String FIRST_LINE             = "1st Line";

    /** <A NAME="_SECOND_LINE_"></A>
     * Indicates that a <A HREF="Personnel.html">Personnel</A> unit's classification is <B>2nd Line</B>.
     */

    public static final String SECOND_LINE            = "2nd Line";

    /** <A NAME="_GREEN_"></A>
     * Indicates that a <A HREF="Personnel.html">Personnel</A> unit's classification is <B>Green</B>.
     */

    public static final String GREEN                  = "Green";

    /** <A NAME="_CONSCRIPT_"></A>
     * Indicates that a <A HREF="Personnel.html">Personnel</A> unit's classification is <B>Conscript</B>.
     */

    public static final String CONSCRIPT              = "Conscript";

    /** <A NAME="_CLASSIFICATIONS_"></A>
     * A list of the supported classifications.
     *
     * @see #getClassification
     */

    public static final String[] CLASSIFICATIONS      = { ELITE, FIRST_LINE,
                                                          SECOND_LINE, GREEN,
                                                          CONSCRIPT };

    /** <A NAME="_CLASSIFICATIONS_VECTOR_"></A>
     * An alternative method of accessing the list of recognized unit
     * classifications. This object is used to verify that the classification
     * parameter specified for an object of a public class derived from <A HREF="Unit.html">Unit</A>
     * matches one of the values found in the <A HREF="#_CLASSIFICATIONS_">CLASSIFICATIONS</A> array.
     *
     * @see #getClassification
     */

    public static final Vector CLASSIFICATIONS_VECTOR = new Vector(Arrays.asList(CLASSIFICATIONS));

    /**
     * The number of elements in the <A HREF="#_CLASSIFICATIONS_">CLASSIFICATIONS</A> array and
     * <A HREF="#_CLASSIFICATIONS_VECTOR_">CLASSIFICATIONS_VECTOR</A>.
     */

    public static final int CLASSIFICATIONS_LIST_SIZE = CLASSIFICATIONS.length;

    // This constant is provided primarily for use in displaying the
    // classification setting of a <A HREF="Personnel.html">Personnel</A> unit using an objects toString()
    // method.

    /**
     * Provides a label for a <A HREF="Personnel.html">Personnel</A> (MMC) unit's classification :
     * <B>Classification</B>
     */

    public static final String CLASSIFICATION_LABEL   = "Classification";

    // Access methods

    /**
     * Determine the classification of a unit. This is indicated on the front of
     * the physical counter by an alphanumeric character in the upper right
     * corner. The recognized values are listed below.
     *
     * @return a <CODE>String</CODE> specifying the classification.
     *
     * @see #ELITE
     * @see #FIRST_LINE
     * @see #SECOND_LINE
     * @see #GREEN
     * @see #CONSCRIPT
     */

    public abstract String getClassification();
}
