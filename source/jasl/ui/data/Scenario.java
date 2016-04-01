// ************************************************************************** //
// Scenario.java - This class is a member of the <B>ui.data</B> package, which       //
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
 * This class is used to translate text data in the JSON format into the objects
 * and settings necessary to play a jASL scenario.
 *
 * @version 1.0
 * @author Copyright (C) 2016 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/Scenario.html">Source code</A>
 */

public final class Scenario
{
	// This variable is used to store the name of the scenario. It is set
	// using the value associated with the "name" key in the JSON input
	// file.

	private String _name = null;

	// Constructors

	/**
	 * Construct a new <CODE>Scenario</CODE>.
	 *
	 */

	public Scenario()
	{
	}

	// Public access methods

	/**
	 * Return a text representation of the attributes of a scenario.
	 *
	 * @return a multi-line <CODE>String</CODE> specifying an overview of the elements of
	 * the scenario.
	 */

	public String toText()
	{
		StringBuffer returnString = new StringBuffer();

		returnString.append("Scenario:\t");
		returnString.append(toString());
		returnString.append("\n");

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return the name of a scenario.
	 *
	 * @return a <CODE>String</CODE> specifying the scenario name.
	 */

	public String toString()
	{
		return _name;
	}
}
