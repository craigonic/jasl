// ************************************************************************** //
// Side.java - This class is a member of the <B>ui.data</B> package, which contains  //
//             the class definitions and implementations for objects used to  //
//             store and manage the state of an instance of jASL.             //
//                                                                            //
//             NOTE: This program is based on Advanced Squad Leader, which    //
//                   was created by The Avalon Hill Game Company, and lives   //
//                   on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                            //
//                                                                            //
// Written By: Craig R. Campbell  -  February 2013                            //
// ************************************************************************** //

package jasl.ui.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import jasl.counters.Nationality;

/**
 * This class is used to manage the <A HREF="Player.html">player</A>s associated with a designated side
 * in a <A HREF="Scenario.html">scenario</A>. It also includes an enum defining the available sides (Allies
 * and Axis) and methods to provide a list of the nationalities on each of them.
 *
 * @version 1.5
 * @author Copyright (C) 2013-2016 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/Side.html">Source code</A>
 */

public final class Side
{
	/**
	 * Recognized side designations. These constants represent the side that
	 * a combatant (player, counter, etc.) is associated with.
	 */

	public enum Sides
	{
		/**
		 * Indicates that the combatant is fighting for the <B>Allies</B>.
		 */

		ALLIES("Allies"),

		/**
		 * Indicates that the combatant is fighting for one of the <B>Axis</B>
		 * powers.
		 */

		AXIS("Axis");

		// Private data members

		// The label associated with the enum constant.

		private final String _label;

		// Constructor

		Sides(String label)
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

	// This variable is used to store the side that the players managed by
	// an instance of this class, as well as the units they control/command,
	// are fighting for.

	private Sides _side;

	// This variable is used to store a list of the players associated with
	// the side that an instance of this class represents.

	private Map<String,Player> _players;

	// Constructor

	/**
	 * Construct a new <CODE>Side</CODE>.
	 *
	 * @param side the side that the instance is to represent
	 * Example - <B>Sides.ALLIES</B>
	 *
	 * @see #player
	 * @see #playerList
	 */

	public Side(Sides side)
	{
		_side = side;

		_players = new LinkedHashMap<String,Player>();
	}

	// Public access methods

	/**
	 * Return a text representation of the attributes of a side.
	 *
	 * The output includes the name of the side, the nationalities
	 * associated with it, and summary information for each player.
	 *
	 * @return a multi-line <CODE>String</CODE> specifying an overview of the elements of
	 * the side.
	 *
	 * @see #unitList
	 */

	public String toText()
	{
		StringBuffer returnString = new StringBuffer();

		returnString.append("Side:\t" + toString() + "\n\n");

		returnString.append("\tNationalities:\t");

		ArrayList<Nationality.Nationalities> nationalities =
			(Side.Sides.ALLIES == _side) ?
			alliedNationalities() : axisNationalities();

		for (Nationality.Nationalities nationality : nationalities)
		{
			returnString.append("\"" + nationality.toString() + "\" ");
		}

		returnString.append("\n\n");

		for (Map.Entry<String,Player> player : _players.entrySet())
		{
			returnString.append(player.getValue().toText());
		}

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return the name of a side.
	 *
	 * @return a <CODE>String</CODE> specifying the side's name.
	 */

	public String toString()
	{
		return _side.toString();
	}

	/**
	 * Return the names of all the players on a side.
	 *
	 * @return an <CODE>ArrayList</CODE> of the names.
	 */

	public ArrayList<String> playerList()
	{
		return new ArrayList<String>(_players.keySet());
	}

	/**
	 * Provide access to the data, including units, for a player.
	 *
	 * @return the <CODE>Player</CODE> instance associated with the specified name. The
	 * return value is null if a player with the name does not exist on the
	 * side.
	 */

	public Player player(String name)
	{
		return (!_players.isEmpty()) ? _players.get(name) : null;
	}

	/**
	 * Return the nationalities associated with the Allies.
	 *
	 * @return an <CODE>ArrayList</CODE> of the corresponding nationalities.
	 */

	public static ArrayList<Nationality.Nationalities> alliedNationalities()
	{
		ArrayList<Nationality.Nationalities> nationalities =
			new ArrayList<Nationality.Nationalities>();

		nationalities.add(Nationality.Nationalities.AMERICAN);
		nationalities.add(Nationality.Nationalities.BRITISH);
		nationalities.add(Nationality.Nationalities.RUSSIAN);
		nationalities.add(Nationality.Nationalities.FRENCH);
		nationalities.add(Nationality.Nationalities.PARTISAN);
		nationalities.add(Nationality.Nationalities.ALLIED_MINOR);

		return nationalities;
	}

	/**
	 * Return the nationalities associated with the Axis.
	 *
	 * @return an <CODE>ArrayList</CODE> of the corresponding nationalities.
	 */

	public static ArrayList<Nationality.Nationalities> axisNationalities()
	{
		ArrayList<Nationality.Nationalities> nationalities =
			new ArrayList<Nationality.Nationalities>();

		nationalities.add(Nationality.Nationalities.GERMAN);
		nationalities.add(Nationality.Nationalities.JAPANESE);
		nationalities.add(Nationality.Nationalities.ITALIAN);
		nationalities.add(Nationality.Nationalities.FINNISH);
		nationalities.add(Nationality.Nationalities.AXIS_MINOR);

		return nationalities;
	}

	// This method is deprecated. It should be removed after the Scenario
	// and Stack classes are fully implemented.

	public static ArrayList<String> unitList(Nationality.Nationalities nationality)
	{
		ArrayList<String> units = new ArrayList<String>();

		units.add("10-3 Leader");
		units.add("10-2 Leader");
		units.add("9-2 Leader");
		units.add("9-1 Leader");
		units.add("8-1 Leader");
		units.add("8-0 Leader");
		units.add("7-0 Leader");
		units.add("6+1 Leader");

		if (Nationality.Nationalities.AMERICAN == nationality)
		{
			units.add("7-4-7 Squad");
			units.add("6-6-7 Squad");
			units.add("6-6-6 Squad");
			units.add("5-5-6 Squad");
			units.add("5-3-6 Squad");
		}

		if (Nationality.Nationalities.BRITISH == nationality)
		{
			units.add("6-3-8 Squad");
			units.add("4-5-8 Squad");
			units.add("4-5-7 Squad");
			units.add("4-4-7 Squad");
			units.add("4-3-6 Squad");
		}

		if (Nationality.Nationalities.RUSSIAN == nationality)
		{
			units.add("6-2-8 Squad");
			units.add("4-5-8 Squad");
			units.add("4-4-7 Squad");
			units.add("5-2-7 Squad");
			units.add("4-2-6 Squad");
		}

		if (Nationality.Nationalities.FRENCH == nationality)
		{
			units.add("4-5-8 Squad");
			units.add("4-5-7 Squad");
			units.add("4-3-7 Squad");
		}

		if (Nationality.Nationalities.PARTISAN == nationality)
		{
			units.add("3-3-7 Squad");
		}

		if (Nationality.Nationalities.ALLIED_MINOR == nationality)
		{
			units.add("4-5-8 Squad");
			units.add("4-5-7 Squad");
			units.add("4-3-7 Squad");
		}

		if (Nationality.Nationalities.GERMAN == nationality)
		{
			units.add("6-5-8 Squad");
			units.add("8-3-8 Squad");
			units.add("4-6-8 Squad");
			units.add("5-4-8 Squad");
			units.add("4-6-7 Squad");
			units.add("4-4-7 Squad");
			units.add("4-3-6 Squad");
		}

		if (Nationality.Nationalities.JAPANESE == nationality)
		{
			units.add("4-4-8 Squad");
			units.add("4-4-7 Squad");
			units.add("3-4-8 Squad");
			units.add("3-4-7 Squad");
			units.add("3-3-6 Squad");
			units.add("2-3-7 Squad");
			units.add("2-3-6 Squad");
		}

		if (Nationality.Nationalities.ITALIAN == nationality)
		{
			units.add("4-4-7 Squad");
			units.add("3-4-7 Squad");
			units.add("3-4-6 Squad");
			units.add("3-3-6 Squad");
		}

		if (Nationality.Nationalities.FINNISH == nationality)
		{
			units.add("8-3-8 Squad");
			units.add("6-4-8 Squad");
			units.add("5-3-8 Squad");
		}

		if (Nationality.Nationalities.AXIS_MINOR == nationality)
		{
			units.add("4-4-7 Squad");
			units.add("3-4-7 Squad");
			units.add("3-3-6 Squad");
		}

		return units;
	}

	// Public update methods

	/**
	 * Add a new player to a side.
	 *
	 * @param name the name of the new player
	 * @param nationality the nationality of the majority of the units that
	 * the player will command. Example - <B><A HREF="../../counters/Nationality.html#_FRENCH_">FRENCH</A></B>
	 * @param entryTurn the turn when the units initially managed by the
	 * player enter the game.
	 * Example - <B>3</B>
	 *
	 * @return a <CODE>boolean</CODE> indicating if the player was added successfully or
	 * not. The value may be false if the name matches that of an existing
	 * player or if the nationality does not belong to the side. The
	 * generation of the new <A HREF="Player.html">Player</A> instance will also throw an exception if
	 * the name or entryTurn argument is invalid.
	 */

	public boolean addPlayer(String name,
	                         Nationality.Nationalities nationality,
	                         int entryTurn)
	{
		// Verify that the specified nationality matches one of the ones
		// associated with the side this instance represents.

		ArrayList<Nationality.Nationalities> validNationalities =
			(Side.Sides.ALLIES == _side) ? alliedNationalities() :
			                               axisNationalities();

		if (validNationalities.contains(nationality) &&
		    (!_players.containsKey(name)))
		{
			_players.put(name,
			             new Player(name,nationality,entryTurn));

			return true;
		}

		return false;
	}
}
