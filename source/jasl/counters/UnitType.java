// ************************************************************************** //
// UnitType.java - This interface is part of the <B>counters</B> package, which      //
//                 contains the class definitions and implementations for     //
//                 objects used to represent the virtual playing pieces in    //
//                 jASL.                                                      //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader,      //
//                       which was created by The Avalon Hill Game Company,   //
//                       and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.                //
//                                                                            //
// Written By: Craig R. Campbell  -  December 2006                            //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constant and required method
 * associated with the unit type of <A HREF="Fighting.html">Fighting</A> units. The method is intended for
 * operation on a String member variable within the implementing class.
 *
 * @version 3.0
 * @author Copyright (C) 2006-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/UnitType.html">Source code</A>
 */

public interface UnitType
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying a more
	// specific description of a <A HREF="Fighting.html">Fighting</A> unit using an objects toText()
	// method.

	/**
	 * Provides a label for the unit's more precise nationality, type, or
	 * capability : <B>Unit Type</B>
	 */

	public static final String UNIT_TYPE_LABEL = "Unit Type";

	// Recognized Infantry unit types.

	/**
	 * Recognized unit type values for Infantry. These are used to identify
	 * additional characteristics/abilities or a more specific nationality.
	 */

	public enum InfantryTypes
	{
		/** <A NAME="_NONE_"></A>
		 * Indicates that a more specific nationality, ability,
		 * characteristic, etc. does not apply to an Infantry unit.
		 */

		NONE(""),

		/** <A NAME="_PARATROOPS_"></A>
		 * Indicates that a unit's more precise type or capability is
		 * <B>Paratroops</B>. If this value is specified as the unitType
		 * parameter for an object, the nationality parameter must be
		 * <B>American</B>.
		 *
		 * @see Nationality.Nationalities#AMERICAN
		 */

		PARATROOPS("Paratroops"),

		/** <A NAME="_AIRBORNE_"></A>
		 * Indicates that a unit's more precise type or capability is
		 * <B>Airborne</B>. If this value is specified as the unitType
		 * parameter for an object, the nationality parameter must be
		 * <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		AIRBORNE("Airborne"),

		/** <A NAME="_ANZAC_"></A>
		 * Indicates that a unit's more precise nationality is <B>ANZAC</B>. If
		 * this value is specified as the unitType parameter for an
		 * object, the nationality parameter must be <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		ANZAC("ANZAC"),

		/** <A NAME="_CANADIAN_"></A>
		 * Indicates that a unit's more precise nationality is <B>Canadian</B>.
		 * If this value is specified as the unitType parameter for an
		 * object, the nationality parameter must be <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		CANADIAN("Canadian"),

		/** <A NAME="_FREE_FRENCH_"></A>
		 * Indicates that a unit's more precise nationality is
		 * <B>Free French</B>. If this value is specified as the unitType
		 * parameter for an object, the nationality parameter must be
		 * <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		FREE_FRENCH("Free French"),

		/** <A NAME="_FREE_POLISH_"></A>
		 * Indicates that a unit's more precise nationality is
		 * <B>Free Polish</B>. If this value is specified as the unitType
		 * parameter for an object, the nationality parameter must be
		 * <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		FREE_POLISH("Free Polish"),

		/** <A NAME="_GUARDSMEN_"></A>
		 * Indicates that a unit's more precise type is <B>Guardsmen</B>. If
		 * this value is specified as the unitType parameter for an
		 * object, the nationality parameter must be <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		GUARDSMEN("Guardsmen"),

		/** <A NAME="_GURKHA_"></A>
		 * Indicates that a unit's more precise type or capability is
		 * <B>Gurkha</B>. If this value is specified as the unitType parameter
		 * for an object, the nationality parameter must be <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		GURKHA("Gurkha"),

		/** <A NAME="_SISSI_"></A>
		 * Indicates that a unit's more precise type or capability is
		 * <B>Sissi</B>. If this value is specified as the unitType parameter
		 * for an object, the nationality parameter must be <B>FINNISH</B>.
		 *
		 * @see Nationality.Nationalities#FINNISH
		 */

		SISSI("Sissi"),

		/** <A NAME="_ENGINEERS_"></A>
		 * Indicates that a unit's more precise type or capability is
		 * <B>Engineers</B>. If this value is specified as the unitType
		 * parameter for an object, the nationality parameter must be
		 * <B>German</B>.
		 *
		 * @see Nationality.Nationalities#GERMAN
		 */

		ENGINEERS("Engineers"),

		/** <A NAME="_COMMISSAR_"></A>
		 * Indicates that a unit's more precise type is <B>Commissar</B>. This
		 * value may only be specified as the unitType parameter for a
		 * <B>Leader</B> object. The nationality parameter must be
		 * <B>Russian</B>.
		 *
		 * @see Nationality.Nationalities#RUSSIAN
		 * @see Leader
		 */

		COMMISSAR("Commissar"),

		/** <A NAME="_GUARDS_"></A>
		 * Indicates that a unit's more precise type is <B>Guards</B>. If this
		 * value is specified as the unitType parameter for an object,
		 * the nationality parameter must be <B>Russian</B>.
		 *
		 * @see Nationality.Nationalities#RUSSIAN
		 */

		GUARDS("Guards");

		// Private data members

		// The label associated with the enum constant.

		private final String _label;

		// Constructor

		InfantryTypes(String label)
		{
			_label = label;
		}

		// Public access method

		/**
		 * Returns the label associated with the enum constant.
		 *
		 * @return the <CODE>String</CODE> associated with the constant.
		 */

		public String toString()
		{
			return _label;
		}
	}

	// Access methods

	/**
	 * Return the formal / specific type of a unit. This provides more
	 * accurate identification and application of attributes associated with
	 * specific unit types. For example, it may specify vehicle names
	 * (Pz VIb, T-34/76, etc.) as well as special infantry designations
	 * (Gurkha, Paratroopers, etc.).
	 *
	 * @return a <CODE>String</CODE> specifying the more precise description of the
	 * unit's nationality, type, or capability.
	 */

	public abstract String unitType();
}
