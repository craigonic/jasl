// ************************************************************************** //
// Game.java - This class is a member of the <B>ui.data</B> package, which contains  //
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jasl.counters.Nationality;

/**
 * This is a singleton class used to provide overall management of an instance
 * of the game.
 *
 * @version 1.3
 * @author Copyright (C) 2013-2014 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/Game.html">Source code</A>
 */

public final class Game
{
	private Map<Side.Sides,Side> _sides;

	private Side.Sides _activeSide = Side.Sides.ALLIES;

	private ArrayList<String> _alliedPlayers = new ArrayList<String>();
	private ArrayList<String> _axisPlayers   = new ArrayList<String>();

	private boolean _setupComplete = false;

	private static Game _game = null;

	private Game()
	{
		_sides = new TreeMap<Side.Sides,Side>();

		_sides.put(Side.Sides.ALLIES,new Side(Side.Sides.ALLIES));
		_sides.put(Side.Sides.AXIS,new Side(Side.Sides.AXIS));
	}

	public static Game game()
	{
		if (null == _game) _game = new Game();

		return _game;
	}

	public Side.Sides activeSide()
	{
		return _activeSide;
	}

	public void setStartingSide(Side.Sides side)
	{
		if (!_setupComplete) _activeSide = side;
	}

	public ArrayList<Nationality.Nationalities> nationalities(Side.Sides side)
	{
		return (Side.Sides.ALLIES == side) ?
		       Side.alliedNationalities() : Side.axisNationalities();
	}

	public ArrayList<String> availableUnits(Nationality.Nationalities nationality)
	{
		return Side.unitList(nationality);
	}

	public boolean addPlayer(Side.Sides side,
	                         String name,
	                         Nationality.Nationalities nationality,
	                         int entryTurn)
	{
		if ((!_setupComplete) &&
		    _sides.get(side).addPlayer(name,nationality,entryTurn))
		{
			ArrayList<String> list = (Side.Sides.ALLIES == side) ?
			                         _alliedPlayers : _axisPlayers;
			list.clear();
			list.addAll(_sides.get(side).playerList());

			return true;
		}

		return false;
	}

	public List<String> playerList(Side.Sides side)
	{
		return Collections.unmodifiableList((Side.Sides.ALLIES == side) ?
		                                    _alliedPlayers : _axisPlayers);
	}

	public Player player(Side.Sides side,String name)
	{
		return _sides.get(side).player(name);
	}

	public boolean setupComplete()
	{
		return _setupComplete;
	}

	public void setSetupComplete()
	{
		_alliedPlayers.clear();
		_alliedPlayers.addAll(_sides.get(Side.Sides.ALLIES).playerList());

		_axisPlayers.clear();
		_axisPlayers.addAll(_sides.get(Side.Sides.AXIS).playerList());

		_setupComplete = true;
	}

	public String toText()
	{
		StringBuffer returnString = new StringBuffer();

		// Add the data for each Side object.

		for (Map.Entry<Side.Sides,Side> side : _sides.entrySet())
		{
			returnString.append(side.getValue().toText() + "\n");
		}

		// Add the state of local data members (excluding the sides).

		returnString.append("Active side   :\t");
		returnString.append(activeSide().toString() + "\n");
		returnString.append("Setup Complete:\t");
		returnString.append(((setupComplete()) ? "true" : "false") + "\n");

		// Return the completed string to calling program.

		return returnString.toString();
	}
}
