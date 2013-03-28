// ************************************************************************** //
// Player.java - This class is a member of the <B>ui.data</B> package, which         //
//               contains the class definitions and implementations for       //
//               objects used to store and manage the state of an instance of //
//               jASL.                                                        //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, which  //
//                     was created by The Avalon Hill Player Company, and     //
//                     lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                    //
//                                                                            //
// Written By: Craig R. Campbell  -  February 2013                            //
// ************************************************************************** //

package jasl.ui.data;

import java.util.*; // For Map.

/**
 * This is a ...
 *
 * @version 1.0
 * @author Copyright (C) 2013 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/Player.html">Source code</A>
 */

public final class Player
{
	private String _name;
	private String _nationality;
	private int    _entryTurn;

	private ArrayList<String> _unitList;

	public Player(String name,String nationality,int entryTurn)
	{
		_name = name;
		_nationality = nationality;
		_entryTurn = entryTurn;

		_unitList = new ArrayList<String>();
	}

	public String toString()
	{
		return _name;
	}

	public String nationality()
	{
		return _nationality;
	}

	public int entryTurn()
	{
		return _entryTurn;
	}

	public List<String> unitList()
	{
		return Collections.unmodifiableList(_unitList);
	}

	public void addUnit(String unit)
	{
		_unitList.add(unit);
	}

	public String toText()
	{
		StringBuffer returnString = new StringBuffer();

		returnString.append("Player:\t" + toString() + "\n\n");
		returnString.append("\tNationality:\t" + nationality() + "\n");
		returnString.append("\tEntry Turn :\t" +
		                     Integer.toString(_entryTurn) + "\n");

		returnString.append("\n\tUnits:\n");

		for (String unit : unitList())
		{
			returnString.append("\t\t" + unit + "\n");
		}

		// Return the completed string to calling program.

		return returnString.toString();
	}
}
