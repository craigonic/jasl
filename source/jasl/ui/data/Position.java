// ************************************************************************** //
// Position.java - This class is a member of the <B>ui.data</B> package, which       //
//                 contains the class definitions and implementations for     //
//                 objects used to store and manage the state of an instance  //
//                 of jASL.                                                   //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader,      //
//                       which was created by The Avalon Hill Game Company,   //
//                       and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.              //
//                                                                            //
// Written By: Craig R. Campbell  -  January 2016                             //
// ************************************************************************** //

package jasl.ui.data;

/**
 * This is a ...
 *
 * @version 1.0
 * @author Copyright (C) 2016 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/Position.html">Source code</A>
 */

public final class Position
{
	private String _label = null;

	public Position(String label)
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

		returnString.append("Position:\t");
		returnString.append(toString());
		returnString.append("\n");

		// Return the completed string to calling program.

		return returnString.toString();
	}
}
