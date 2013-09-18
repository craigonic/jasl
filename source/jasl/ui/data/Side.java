// ************************************************************************** //
// Side.java - This class is a member of the <B>ui.data</B> package, which contains  //
//             the class definitions and implementations for objects used to  //
//             store and manage the state of an instance of jASL.             //
//                                                                            //
//             NOTE: This program is based on Advanced Squad Leader, which    //
//                   was created by The Avalon Hill Side Company, and lives   //
//                   on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                            //
//                                                                            //
// Written By: Craig R. Campbell  -  February 2013                            //
// ************************************************************************** //

package jasl.ui.data;

import java.util.*; // For Map.

import jasl.counters.Nationality;

/**
 * This is a ...
 *
 * @version 1.0
 * @author Copyright (C) 2013 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/Side.html">Source code</A>
 */

public final class Side
{
	/**
	 * Recognized side designations. These constants represent the counter
	 * types that may be directly instantiated using the public classes in
	 * the <B>counters</B> hierarchy.
	 */

	public enum Sides
	{
		/**
		 * Indicates that the ... <B>Allies</B>.
		 */

		ALLIES("Allies"),

		/**
		 * Indicates that the ... <B>Axis</B>.
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

	private Sides _side;

	private Map<String,Player> _players;

	public Side(Sides side)
	{
		_side = side;

		_players = new LinkedHashMap<String,Player>();
	}

	public static ArrayList<String> alliedNationalities()
	{
		ArrayList<String> nationalities = new ArrayList<String>();

		nationalities.add(Nationality.AMERICAN);
		nationalities.add(Nationality.BRITISH);
		nationalities.add(Nationality.RUSSIAN);
		nationalities.add(Nationality.FRENCH);
		nationalities.add(Nationality.PARTISAN);
		nationalities.add(Nationality.ALLIED_MINOR);

		return nationalities;
	}

	public static ArrayList<String> axisNationalities()
	{
		ArrayList<String> nationalities = new ArrayList<String>();

		nationalities.add(Nationality.GERMAN);
		nationalities.add(Nationality.JAPANESE);
		nationalities.add(Nationality.ITALIAN);
		nationalities.add(Nationality.FINNISH);
		nationalities.add(Nationality.AXIS_MINOR);

		return nationalities;
	}

	public static ArrayList<String> unitList(String nationality)
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

		if (nationality.compareTo(Nationality.AMERICAN) == 0)
		{
			units.add("7-4-7 Squad");
			units.add("6-6-7 Squad");
			units.add("6-6-6 Squad");
			units.add("5-5-6 Squad");
			units.add("5-3-6 Squad");
		}

		if (nationality.compareTo(Nationality.BRITISH) == 0)
		{
			units.add("6-3-8 Squad");
			units.add("4-5-8 Squad");
			units.add("4-5-7 Squad");
			units.add("4-4-7 Squad");
			units.add("4-3-6 Squad");
		}

		if (nationality.compareTo(Nationality.RUSSIAN) == 0)
		{
			units.add("6-2-8 Squad");
			units.add("4-5-8 Squad");
			units.add("4-4-7 Squad");
			units.add("5-2-7 Squad");
			units.add("4-2-6 Squad");
		}

		if (nationality.compareTo(Nationality.FRENCH) == 0)
		{
			units.add("4-5-8 Squad");
			units.add("4-5-7 Squad");
			units.add("4-3-7 Squad");
		}

		if (nationality.compareTo(Nationality.PARTISAN) == 0)
		{
			units.add("3-3-7 Squad");
		}

		if (nationality.compareTo(Nationality.ALLIED_MINOR) == 0)
		{
			units.add("4-5-8 Squad");
			units.add("4-5-7 Squad");
			units.add("4-3-7 Squad");
		}

		if (nationality.compareTo(Nationality.GERMAN) == 0)
		{
			units.add("6-5-8 Squad");
			units.add("8-3-8 Squad");
			units.add("4-6-8 Squad");
			units.add("5-4-8 Squad");
			units.add("4-6-7 Squad");
			units.add("4-4-7 Squad");
			units.add("4-3-6 Squad");
		}

		if (nationality.compareTo(Nationality.JAPANESE) == 0)
		{
			units.add("4-4-8 Squad");
			units.add("4-4-7 Squad");
			units.add("3-4-8 Squad");
			units.add("3-4-7 Squad");
			units.add("3-3-6 Squad");
			units.add("2-3-7 Squad");
			units.add("2-3-6 Squad");
		}

		if (nationality.compareTo(Nationality.ITALIAN) == 0)
		{
			units.add("4-4-7 Squad");
			units.add("3-4-7 Squad");
			units.add("3-4-6 Squad");
			units.add("3-3-6 Squad");
		}

		if (nationality.compareTo(Nationality.FINNISH) == 0)
		{
			units.add("8-3-8 Squad");
			units.add("6-4-8 Squad");
			units.add("5-3-8 Squad");
		}

		if (nationality.compareTo(Nationality.AXIS_MINOR) == 0)
		{
			units.add("4-4-7 Squad");
			units.add("3-4-7 Squad");
			units.add("3-3-6 Squad");
		}

		return units;
	}

	public boolean addPlayer(String name,String nationality,int entryTurn)
	{
		if (!_players.containsKey(name))
		{
			_players.put(name,
			             new Player(name,nationality,entryTurn));

			return true;
		}

		return false;
	}

	public ArrayList<String> playerList()
	{
		return new ArrayList<String>(_players.keySet());
	}

	public Player player(String name)
	{
		return (!_players.isEmpty()) ? _players.get(name) : null;
	}

	public String toString()
	{
		return _side.toString();
	}

	public String toText()
	{
		StringBuffer returnString = new StringBuffer();

		returnString.append("Side:\t" + toString() + "\n\n");

		returnString.append("\tNationalities:\t");

		ArrayList<String> nationalities = (Side.Sides.ALLIES == _side) ?
		                                  alliedNationalities() :
		                                  axisNationalities();

		for (String nationality : nationalities)
		{
			returnString.append("\"" + nationality + "\" ");
		}

		returnString.append("\n\n");

		for (Map.Entry<String,Player> player : _players.entrySet())
		{
			returnString.append(player.getValue().toText());
		}

		// Return the completed string to calling program.

		return returnString.toString();
	}
}
