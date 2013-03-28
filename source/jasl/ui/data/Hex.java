// ************************************************************************** //
// Hex.java - This class is a member of the <B>ui.data</B> package, which contains   //
//            the class definitions and implementations for objects used      //
//            to store and manage the state of an instance of jASL.           //
//                                                                            //
//            NOTE: This program is based on Advanced Squad Leader, which was //
//                  created by The Avalon Hill Hex Company, and lives on at   //
//                  at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                                //
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
 * @see <A HREF="../../../../source/jasl/ui/data/Hex.html">Source code</A>
 */

public final class Hex
{
	private String _label = null;

	public Hex(String label)
	{
		_label = label;
	}

	public String toString()
	{
		return _label;
	}

	public String toText()
	{
		StringBuffer returnString = new StringBuffer();

		returnString.append("Hex:\t");
		returnString.append(toString());
		returnString.append("\n");

		// Return the completed string to calling program.

		return returnString.toString();
	}
}
