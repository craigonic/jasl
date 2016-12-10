// ************************************************************************** //
// Player.java - This class is a member of the <B>ui.data</B> package, which         //
//               contains the class definitions and implementations for       //
//               objects used to store and manage the state of an instance of //
//               jASL.                                                        //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, which  //
//                     was created by The Avalon Hill Game Company, and lives //
//                     on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                          //
//                                                                            //
// Written By: Craig R. Campbell  -  February 2013                            //
// ************************************************************************** //

package jasl.ui.data;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import jasl.counters.Nationality;
import jasl.utilities.Messages;

/**
 * This class is used to provide data management for a player during a jASL
 * session. It includes the player's name, the nationality of the majority of
 * the <A HREF="../../counters/Unit.html">Unit</A>s under his or her control, and the turn that he or she enters the
 * game.
 * <P>
 * <B>Unit Management</B>
 * <P>
 * This section describes the recommended procedures for adding, accessing, and
 * grouping units.
 * <P>
 * Before the actual battle begins, Unit objects should be assigned to a player
 * using the addUnit() method. This "wraps" each item in a <A HREF="Stack.html">Stack</A> object,
 * allowing them to be managed as groups (e.g. portaged items, a set of items
 * that act (move, fire, etc.) together). The "stacks" managed by a player are
 * accessible through the stackList() method. Its primary purpose is to provide
 * the index of an item to be manipulated. This index is used as an argument to
 * the takeStack() method. Individual stacks must first be "taken" (i.e.
 * referenced by a temporary variable) before being moved. Note that (for now)
 * only one stack may be "taken" at a time.
 * <P>
 * <I>Example 1: Portaging an item</I>
 * <P>
 * - Retrieve a list of Units (Stacks) using the stackList() method. <P>
 * - "Take" the Unit to be portaged, by specifying its index to takeStack(). <P>
 * - Referencing the desired owner Unit (Stack) by its index (use the get()
 *   method of List), call the Stack.addPortagedItem() method, passing the
 *   "taken" (i.e. to be portaged) object as the argument.
 * <P>
 * <I>Example 2: Transferring a portaged item from one Unit to another</I>
 * <P>
 * - Retrieve a list of Units (Stacks) using the stackList() method. <P>
 * - Select the Unit (Stack) that is currently carrying the item and use it to
 *   call Stack.portagedItems(). This method will return a Map. <P>
 * - Using the selected Unit object, call Stack.takePortagedItem(), passing the
 *   "key" associated with the desired (portaged) item. Keep a reference to the
 *   returned object. <P>
 * - From the list returned by stackList(), select the Unit (Stack) that the
 *   item should be transferred to. Use it to call Stack.addPortagedItem(),
 *   passing the referenced object as an argument.
 * <P>
 * <I>Example 3: Grouping Units into a single stack</I>
 * <P>
 * - Create a new Stack object, passing the first stack to be included
 *   (retrieved as above using the takeStack() method) in the new group as an
 *   argument. <P>
 * - Retrieve additional stacks as desired (again using takeStack()) and add
 *   them to the new Stack object using Stack.addSubStack(). Note that the
 *   indexes of the source stack (retrieved via stackList()) change after each
 *   takeStack() call. <P>
 * - Pass the new Stack object as an argument to addStack().
 * <P>
 * <I>Example 4: Removing a single stack (Unit and portaged item(s)) from a group of stacks</I>
 * <P>
 * - Using the stackList() method and the get() method of List, reference the
 *   desired Stack object (group of stacks). <P>
 * - Call Stack.subStacks() using the referenced object. This method returns
 *   a Map, which provides a "key" for each sub-stack. <P>
 * - Using the initial (group) Stack object, pass the desired "key" as an
 *   argument to Stack.takeSubStack(). <P>
 * - Pass the item returned by takeSubStack() to the addStack() method.
 *
 * @version 2.0
 * @author Copyright (C) 2013-2016 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/Player.html">Source code</A>
 */

public final class Player
{
	// Private data members

	// This variable is used to store the player's name. It (obviously) may
	// not be null or empty.

	private String _name;

	// This variable contains the nationality (e.g. "American" or "German")
	// of the majority of the Units that the player controls. It is intended
	// primarily for informational use, since a player could potentially
	// control items (counters) with no nationality or a nationality
	// associated with the other "side" (e.g. captured items).

	private Nationality.Nationalities _nationality;

	// This variable is used to store the turn number that the player (and
	// the units he or she manages) will enter the game. It must be greater
	// than zero.

	private int _entryTurn;

	// This variable is used to store the units (in Stacks) managed by the
	// player. The list is updated using the addUnit(), addStack(), and
	// takeStack() methods. Units (via a Stack) can be accessed through
	// stackList().

	private ArrayList<Stack> _stackList;

	// The following strings are used as messages for any exceptions that
	// may be generated by bad data being passed to the constructor.

	private static final String CLASS_NAME = Player.class.getSimpleName();

	private static final String nullPointerError =
		Messages.buildErrorMessage(CLASS_NAME,Messages.CONSTRUCTOR,
		                           Messages.NULL_PARAMETER_MSG);

	private static final String zeroLengthArgumentError =
		Messages.buildErrorMessage(CLASS_NAME,Messages.CONSTRUCTOR,
		                           Messages.ZERO_LENGTH_PARAMETER_MSG);

	private static final String invalidArgumentError =
		Messages.buildErrorMessage(CLASS_NAME,Messages.CONSTRUCTOR,
		                           Messages.INVALID_PARAMETER_MSG);
	// Constructor

	/**
	 * Construct a new <CODE>Player</CODE>.
	 *
	 * @param name the name of the player.
	 * @param nationality the nationality of the majority of the units
	 * that the player controls. Example - <B><A HREF="../../counters/Nationality.html#_GERMAN_">GERMAN</A></B>
	 * @param entryTurn the turn when the units initially managed by the
	 * player enter the game. Example - <B>1</B>
	 *
	 * @throws NullPointerException in the case of a null name argument.
	 * @throws IllegalArgumentException in the case of an empty name or an
	 * invalid (less than 1) entry turn argument.
	 */

	public Player(String name,Nationality.Nationalities nationality,
	              int entryTurn)
	{
		// Copy the value of each remaining parameter to the
		// corresponding variable if an exception is not found.

		// Name

		if (null == name)
		{
			throw new NullPointerException(nullPointerError);
		}

		if (name.isEmpty())
		{
			throw new IllegalArgumentException(zeroLengthArgumentError);
		}

		_name = name;

		// Nationality

		_nationality = nationality;

		// Entry turn

		if (entryTurn < 1)
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   entryTurn);
		}

		_entryTurn = entryTurn;

		// Empty list of Stacks.

		_stackList = new ArrayList<Stack>();
	}

	// Public access methods

	/**
	 * Return a text representation of the attributes of a player.
	 * <P>
	 * The output includes basic information about the units managed by the
	 * player.
	 *
	 * @return a multi-line <CODE>String</CODE> specifying the current settings of an
	 * instance of this type.
	 *
	 * @see #stackList
	 */

	public String toText()
	{
		StringBuffer returnString = new StringBuffer();

		returnString.append("Player:\t" + toString() + "\n\n");
		returnString.append("\tNationality:\t" + nationality() + "\n");
		returnString.append("\tEntry Turn :\t" +
		                     Integer.toString(_entryTurn) + "\n");

		returnString.append("\n\tUnits:\n");

		for (Stack stack : stackList())
		{
			returnString.append("\t\t" + stack.toText() + "\n");
		}

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return the name of a player.
	 *
	 * @return a <CODE>String</CODE> specifying the player's name.
	 */

	public String toString()
	{
		return _name;
	}

	/**
	 * Return the nationality of the majority of the units that a player
	 * controls.
	 *
	 * @return a <CODE>String</CODE> specifying the nationality.
	 */

	public String nationality()
	{
		return _nationality.toString();
	}

	/**
	 * Return the turn that the units initially managed by a player will
	 * enter the game.
	 *
	 * @return an <CODE>int</CODE> specifying the entry turn.
	 */

	public int entryTurn()
	{
		return _entryTurn;
	}

	/**
	 * Provide access to the units (in their respective Stacks) managed by a
	 * player.
	 *
	 * @return an <B>unmodifiable</B> <CODE>List</CODE> of the player's Stack items.
	 */

	public List<Stack> stackList()
	{
		return Collections.unmodifiableList(_stackList);
	}

	// Public update methods

	/**
	 * Place a unit under a player's command.
	 * <P>
	 * The Unit will be wrapped/managed by a Stack object. Note that this
	 * method is intended for use only in initial Unit assignment (i.e. it
	 * should not be called after active play has begun).
	 *
	 * @param unit the Unit to be added.
	 *
	 * @throws NullPointerException in the case of a null Unit object.
	 *
	 * @see #addStack
	 */

	public void addUnit(String unit)
	{
		if (null == unit)
		{
			throw new NullPointerException(Messages.buildErrorMessage(CLASS_NAME,
			                                                          "addUnit",
			                                                          Messages.NULL_PARAMETER_MSG));
		}

		_stackList.add(new Stack(unit));
	}

	/**
	 * Add another Stack to the group managed by a player.
	 *
	 * @param stack the Stack to be added.
	 *
	 * @throws NullPointerException in the case of a null Stack object.
	 */

	public void addStack(Stack stack)
	{
		if (null == stack)
		{
			throw new NullPointerException(Messages.buildErrorMessage(CLASS_NAME,
			                                                          "addStack",
			                                                          Messages.NULL_PARAMETER_MSG));
		}

		_stackList.add(stack);
	}

	/**
	 * Return the sub-stack referenced by the specified index.
	 * <P>
	 * If the Stack to be removed is currently managing (i.e. portaging)
	 * other Units (e.g. support weapons) and/or contains a group of Stacks,
	 * all of those items will be included with the returned object.
	 *
	 * @param index the (zero-based) index of the Stack to be removed.
	 *
	 * @return the <CODE>Stack</CODE> at the index matching the argument.
	 *
	 * @throws IndexOutOfBoundsException in the case where index is less than
	 * zero or greater than the number of Stacks in the list.
	 */

	public Stack takeStack(int index)
	{
		Stack departingStack;

		try
		{
			departingStack = _stackList.remove(index);
		}

		catch (IndexOutOfBoundsException exception)
		{
			throw new IndexOutOfBoundsException(Messages.buildErrorMessage(CLASS_NAME,
			                                                               "takeStack",
			                                                               Messages.INVALID_PARAMETER_MSG + index));
		}

		return departingStack;
	}
}
