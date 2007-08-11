// ************************************************************************** //
// UnitType.java - This interface is part of the <B>counters</B> package, which      //
//                 contains the class definitions and implementations for     //
//                 objects used to represent the virtual playing pieces in    //
//                 jASL.                                                      //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader,      //
//                       which was created by The Avalon Hill Game Company,   //
//                       and lives on at <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.              //
//                                                                            //
// Written By: Craig R. Campbell  -  December 2006                            //
//                                                                            //
// $Id: UnitType.java,v 1.3 2007/08/11 05:15:12 craig Exp $
// ************************************************************************** //

package jasl.counters;

import java.util.*; // For Vector.

/**
 * This interface is used to define public constants and required methods
 * associated with the unit type of <A HREF="Fighting.html">Fighting</A> units.
 *
 * @version 1.3
 * @author Craig R. Campbell
 * @see <A HREF="../../../source/jasl/counters/UnitType.html">Source code</A>
 */

public interface UnitType
{
    // Recognized unitType values. These are used to identify specific types and
    // more specific nationalities for infantry units. The unitType variable can
    // also be used by the calling program to indicate other names or attributes
    // for the <A HREF="Unit.html">Unit</A> it is applied to.

    /** <A NAME="_PARATROOPS_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>Paratroops</B>. If this value is specified as the unitType parameter for an
     * object, the nationality parameter must be <A HREF="Nationality.html#_AMERICAN_">AMERICAN</A>.
     */

    public static final String PARATROOPS        = "Paratroops";

    /** <A NAME="_AIRBORNE_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>Airborne</B>. If this value is specified as the unitType parameter for an
     * object, the nationality parameter must be <A HREF="Nationality.html#_BRITISH_">BRITISH</A>.
     */

    public static final String AIRBORNE          = "Airborne";

    /** <A NAME="_ANZAC_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>ANZAC</B>. If this value is specified as the unitType parameter for an
     * object, the nationality parameter must be <A HREF="Nationality.html#_BRITISH_">BRITISH</A>.
     */

    public static final String ANZAC             = "ANZAC";

    /** <A NAME="_CANADIAN_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>Canadian</B>. If this value is specified as the unitType parameter for an
     * object, the nationality parameter must be <A HREF="Nationality.html#_BRITISH_">BRITISH</A>.
     */

    public static final String CANADIAN          = "Canadian";

    /** <A NAME="_FREE_FRENCH_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>Free French</B>. If this value is specified as the unitType parameter for an
     * object, the nationality parameter must be <A HREF="Nationality.html#_BRITISH_">BRITISH</A>.
     */

    public static final String FREE_FRENCH       = "Free French";

    /** <A NAME="_FREE_POLISH_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>Free Polish</B>. If this value is specified as the unitType parameter for an
     * object, the nationality parameter must be <A HREF="Nationality.html#_BRITISH_">BRITISH</A>.
     */

    public static final String FREE_POLISH       = "Free Polish";

    /** <A NAME="_GUARDSMEN_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>Guardsmen</B>. If this value is specified as the unitType parameter for an
     * object, the nationality parameter must be <A HREF="Nationality.html#_BRITISH_">BRITISH</A>.
     */

    public static final String GUARDSMEN         = "Guardsmen";

    /** <A NAME="_GURKHA_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>Gurkha</B>. If this value is specified as the unitType parameter for an
     * object, the nationality parameter must be <A HREF="Nationality.html#_BRITISH_">BRITISH</A>.
     */

    public static final String GURKHA            = "Gurkha";

    /** <A NAME="_SISSI_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>Sissi</B>. If this value is specified as the unitType parameter for an
     * object, the nationality parameter must be <A HREF="Nationality.html#_FINNISH_">FINNISH</A>.
     */

    public static final String SISSI             = "Sissi";

    /** <A NAME="_SS_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>SS</B>. If this value is specified as the unitType parameter for an object,
     * the nationality parameter must be <A HREF="Nationality.html#_GERMAN_">GERMAN</A>.
     */

    public static final String SS                = "SS";

    /** <A NAME="_ENGINEERS_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>Engineers</B>. If this value is specified as the unitType parameter for an
     * object, the nationality parameter must be <A HREF="Nationality.html#_GERMAN_">GERMAN</A>.
     */

    public static final String ENGINEERS         = "Engineers";

    /** <A NAME="_COMMISSAR_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>Commissar</B>. This value may only be specified as the unitType parameter for
     * a <A HREF="Leader.html">Leader</A> object. The nationality parameter must be <A HREF="Nationality.html#_RUSSIAN_">RUSSIAN</A>.
     */

    public static final String COMMISSAR         = "Commissar";

    /** <A NAME="_GUARDS_"></A>
     * Indicates that a unit's more precise nationality, type, or capability is
     * <B>Guards</B>. If this value is specified as the unitType parameter for an
     * object, the nationality parameter must be <A HREF="Nationality.html#_RUSSIAN_">RUSSIAN</A>.
     */

    public static final String GUARDS            = "Guards";

    /** <A NAME="_UNIT_TYPES_"></A>
     * A list of the recognized unit nationalities, types, and capabilities.
     * It is not necessary that the unitType value specified for a new object
     * match a value in this list. If one of these values is specified, however,
     * it will be checked against the nationality and/or description parameters.
     * For example, attempting to create a <A HREF="Nationality.html#_FRENCH_">French</A> <A HREF="#_SS_">SS</A> <A HREF="Squad.html">Squad</A> will result in an
     * exception.
     *
     * @see Fighting#getUnitType
     */

    public static final String[] UNIT_TYPES      = { PARATROOPS, AIRBORNE,
                                                     ANZAC, CANADIAN,
                                                     FREE_FRENCH, FREE_POLISH,
                                                     GUARDSMEN, GURKHA, SISSI,
                                                     SS, ENGINEERS, COMMISSAR,
                                                     GUARDS };

    /** <A NAME="_UNIT_TYPES_VECTOR_"></A>
     * An alternative method of accessing the list of the recognized unit types.
     * This object is used to verify that the unitType parameter specified for
     * an object of a public class derived from <A HREF="Unit.html">Unit</A>, if it matches one of the
     * of the values found in the <A HREF="#_UNIT_TYPES_">UNIT_TYPES</A> array, does not conflict with the
     * nationality and/or description parameters.
     *
     * @see Fighting#getUnitType
     */

    public static final Vector UNIT_TYPES_VECTOR = new Vector(Arrays.asList(UNIT_TYPES));

    /**
     * The number of elements in the <A HREF="#_UNIT_TYPES_">UNIT_TYPES</A> array and <A HREF="#_UNIT_TYPES_VECTOR_">UNIT_TYPES_VECTOR</A>.
     */

    public static final int UNIT_TYPES_LIST_SIZE = UNIT_TYPES.length;

    /**
     * Provides a label for a unit's more precise nationality, type, or
     * capability : <B>Unit Type</B>
     */

    public static final String UNIT_TYPE_LABEL   = "Unit Type";

    // Access methods.

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
     * @see #AIRBORNE
     * @see #ANZAC
     * @see #CANADIAN
     * @see #COMMISSAR
     * @see #ENGINEERS
     * @see #FREE_FRENCH
     * @see #FREE_POLISH
     * @see #GUARDS
     * @see #GUARDSMEN
     * @see #GURKHA
     * @see #PARATROOPS
     * @see #SISSI
     * @see #SS
     */

    public abstract String getUnitType();
}
