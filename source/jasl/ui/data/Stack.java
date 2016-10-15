// ************************************************************************** //
// Stack.java - This class is a member of the <B>ui.data</B> package, which contains //
//              the class definitions and implementations for objects used to //
//              store and manage the state of an instance of jASL.            //
//                                                                            //
//              NOTE: This program is based on Advanced Squad Leader, which   //
//                    was created by The Avalon Hill Game Company, and lives  //
//                    on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                           //
//                                                                            //
// Written By: Craig R. Campbell  -  January 2016                             //
// ************************************************************************** //

package jasl.ui.data;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import jasl.counters.Unit;
import jasl.utilities.Messages;

/**
 * This class is used to provide data management for one or more <A HREF="../../counters/Unit.html">Unit</A> objects.
 * This includes positioning, temporary state (e.g. how much a unit moved, if it
 * has fired or not, etc.), and, where applicable, portaging support. It also
 * allows items of this type that are "wrapping" a Unit to be grouped together.
 *
 * @version 1.1
 * @author Copyright (C) 2016 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/Stack.html">Source code</A>
 */

public final class Stack
{
	// This variable is used to store the Unit object managed by an instance
	// of this class. It is initialized to null and must remain so in order
	// for the management of sub-stacks to work.

	private String _unit;

	// This variable is used to store the ID associated with the Unit
	// managed by a stack. It is initialized to zero and may optionally be
	// changed to suit the needs of the calling program. It is not used
	// internally by this class.

	private int _unitID;

	// This variable is used to store the ID associated with a Stack that is
	// managing a unit. It is initialized to zero and is either used
	// directly, if not zero, or applied if it is, to set or determine the
	// unique key value for a portaged item or sub-stack.

	private int _stackID;

	// This variable is used to store one or more items (each a Unit
	// "wrapped" in its own Stack instance) that are carried/portaged by the
	// the Unit in this instance. Items can only be added to or removed from
	// this item if the instance is NOT managing sub-stacks. The "key" value
	// is set using the same rules applied to adding a sub-stack.

	private LinkedHashMap<Integer,Stack> _portagedItems;

	// This variable is used to store one or more items, each a Stack that
	// is managing a unit. The instance is not allowed to manage items that
	// are not (i.e. that support the addition of other sub-stacks).

	private LinkedHashMap<Integer,Stack> _subStacks;

	// This variable is used to store the label associated with the current
	// position of the stack. Its setting, along with that of _unit,
	// determine the return value of the toString() method.

	private String _positionLabel;

	// The following strings are used as messages for any exceptions that
	// may be generated by bad data being passed one of the constructors.

	private static final String CLASS_NAME = Stack.class.getSimpleName();

	private static final String nullPointerError =
		Messages.buildErrorMessage(CLASS_NAME,Messages.CONSTRUCTOR,
		                           Messages.NULL_PARAMETER_MSG);

	private static final String invalidArgumentError =
		Messages.buildErrorMessage(CLASS_NAME,Messages.CONSTRUCTOR,
		                           Messages.INVALID_PARAMETER_MSG);
	// Constructors

	/**
	 * Construct a new <CODE>Stack</CODE>.
	 *
	 * This constructor is intended to "wrap" a single Unit object, allowing
	 * portaged items to be associated with it. If it is used to create a
	 * new instance, the methods associated with sub-stacks will have no
	 * effect. The unit ID is intended for use in referencing the "owner" of
	 * any portaged items. If the stack ID has a value greater than zero, it
	 * will be applied if the instance is added to another as a sub-stack.
	 * For both the unit and stack ID, if the argument value is less than
	 * zero, the setting will be initialized to zero. All Stack instances
	 * should either have their ID set to zero <B>or</B> have a unique (greater
	 * than zero) ID. Mixing the two may result in an unexpected failure
	 * when adding a portaged item to an instance managing a Unit or adding
	 * that instance as a sub-stack.
	 *
	 * @param unit the unit to be managed through the instance
	 * @param unitID the identifier associated with the unit
	 * @param stackID the identifier associated with the stack
	 *
	 * @throws NullPointerException in the case of a null unit argument.
	 *
	 * @see #unit
	 * @see #portagedItems
	 * @see #addPortagedItem
	 * @see #takePortagedItem
	 */

	public Stack(String unit,int unitID,int stackID)
	{
		// Copy the value of each argument to the corresponding variable
		// if an exception is not found.

		// Unit

		if (null == unit)
		{
			throw new NullPointerException(nullPointerError);
		}

		_unit = unit;

		// Unit ID

		_unitID = (unitID > 0) ? unitID : 0;

		// Stack ID

		_stackID = (stackID > 0) ? stackID : 0;

		// Create the portaged item list.

		_portagedItems = new LinkedHashMap<Integer,Stack>();

		// Make the position label blank.

		setPositionLabel(null);
	}

	/**
	 * Construct a new <CODE>Stack</CODE>.
	 *
	 * This is a convenience constructor. It has the same effect as calling
	 * the other one that accepts a Unit object argument with zero specified
	 * as the argument for the unitID and stackID parameters. It is
	 * recommended that usage of these two constructors <B>not</B> be mixed,
	 * particularly if the instances are to be managed as sub-stacks.
	 *
	 * @param unit the unit to be managed through the instance
	 *
	 * @throws NullPointerException in the case of a null unit argument.
	 */

	public Stack(String unit)
	{
		this(unit,0,0);
	}

	/**
	 * Construct a new <CODE>Stack</CODE>.
	 *
	 * This constructor is intended to allow more than one instance (each
	 * created with one of the constructors that accepts a Unit object
	 * argument) to be managed (e.g. moved) as a group. An object of this
	 * type is intended primarily to maintain a common Position setting for
	 * all of the sub-stacks. When the initial one is added, its position,
	 * if set, will be used to initialize the setting for the new instance.
	 * As each sub-stack is added, including the first one, its position
	 * setting will be cleared. When one is removed, its position will be
	 * set to reflect the setting within the parent (this) instance.
	 *
	 * @param stack the initial stack to be managed through the instance
	 *
	 * @throws NullPointerException in the case of a null stack argument.
	 * @throws IllegalArgumentException in the case where a stack argument
	 * is <B>not</B> managing a Unit (i.e. it supports the addition of sub-stacks).
	 *
	 * @see #subStacks
	 * @see #addSubStack
	 * @see #takeSubStack
	 */

	public Stack(Stack stack)
	{
		if (null == stack)
		{
			throw new NullPointerException(nullPointerError);
		}

		if (null == stack.unit())
		{
			throw new IllegalArgumentException(invalidArgumentError);
		}

		setPositionLabel(stack.positionLabel());

		_subStacks = new LinkedHashMap<Integer,Stack>();

		addSubStack(stack);
	}

	// Public access methods

	/**
	 * Return a text representation of the attributes of a stack.
	 *
	 * The output includes the unit and stack IDs and either the position or
	 * the unit label for the current instance. The same data for portaged
	 * items or sub-stacks, if they exist, will also appear.
	 *
	 * @return a multi-line <CODE>String</CODE> specifying an overview of the elements of
	 * the stack.
	 *
	 * @see #toString
	 */

	public String toText()
	{
		StringBuffer returnString = new StringBuffer();

		returnString.append("Stack (");
		returnString.append(unitID() + "," + stackID() + "):\t");
		returnString.append(toString());

		if ((null != _unit) && !_portagedItems.isEmpty())
		{
			returnString.append("\n");

			for (Stack stack : _portagedItems.values())
			{
				returnString.append("\t" + stack.toString());
			}
		}

		if ((null != _subStacks) && !_subStacks.isEmpty())
		{
			returnString.append("\n");

			for (Stack stack : _subStacks.values())
			{
				returnString.append("\t" + stack.toString());
			}
		}

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return the label associated with a stack.
	 *
	 * The position label will be returned if it is not empty. Otherwise,
	 * the method will return the label for the first Unit item found. In
	 * the event that the instance is empty (all of the sub-stacks have been
	 * removed), the return value is null.
	 *
	 * @return a <CODE>String</CODE> specifying the corresponding label.
	 */

	public String toString()
	{
		if (!_positionLabel.isEmpty())
		{
			return positionLabel();
		}

		if (null != _unit) return _unit.toString();

		if (!_subStacks.isEmpty())
		{
			return _subStacks.get(_subStacks.keySet().toArray()[0]).toString();
		}

		return null;
	}

	/**
	 * Return the ID associated with the Unit managed by a stack.
	 *
	 * @return an <CODE>int</CODE> specifying the unit ID.
	 */

	public int unitID()
	{
		return _unitID;
	}

	/**
	 * Return the ID associated with a Stack that is managing a unit.
	 *
	 * @return an <CODE>int</CODE> specifying the stack ID.
	 */

	public int stackID()
	{
		return _stackID;
	}

	/**
	 * Return the label associated with the current position of a stack.
	 *
	 * @return a <CODE>String</CODE> specifying the stack position. Note that it may be
	 * empty for portaged items and sub-stacks. It should never be null.
	 *
	 * @see #setPositionLabel
	 */

	public String positionLabel()
	{
		return _positionLabel;
	}

	// Public update methods

	/**
	 * Change the label associated with the current position of a stack.
	 *
	 * If the argument is null, the position label will empty. This method
	 * should be used with care, as its setting (or not) affects the data
	 * returned by toString(). It is recommended that it only be applied to
	 * a Stack that is managing a Unit (but not any of its portaged items)
	 * or a Stack that is managing a group of sub-stacks (but not any of
	 * them).
	 *
	 * @param positionLabel the label associated with the new position
	 * of the stack
	 *
	 * @see #positionLabel
	 */

	public void setPositionLabel(String positionLabel)
	{
		_positionLabel =
			(null == positionLabel) ? new String() : positionLabel;
	}

	// Public access methods (Single Stack)

	/**
	 * Provide access to the Unit object managed by an instance.
	 *
	 * @return the <CODE>Unit</CODE> "wrapped" by the instance. The return value is null
	 * if the instance is managing sub-stacks.
	 */

	public String unit()
	{
		return _unit;
	}

	/**
	 * Provide access to the portaged items (Stacks) associated with the
	 * Unit managed by an instance.
	 *
	 * This method allows the items (i.e. Units) being portaged to be
	 * modified without changing the stack itself. It should <B>not</B> be used to
	 * move (change the position or owner of) a portaged item. The key for
	 * each entry may be used as an argument to the takePortagedItem()
	 * method, which (obviously) does allow changes to the stack.
	 *
	 * @return a <CODE>Map</CODE> of key-value pairs associated with the
	 * portaged Units managed by this instance. The return value is null if
	 * the instance is <B>not</B> managing a Unit object or there are no portaged
	 * items.
	 *
	 * @see #takePortagedItem
	 */

	public Map<Integer,Stack> portagedItems()
	{
		if ((null != _unit) && !_portagedItems.isEmpty())
		{
			return Collections.unmodifiableMap(_portagedItems);
		}

		return null;
	}

	// Public access methods (Stack collection)

	/**
	 * Provide access to the Stack objects managed by an instance with
	 * sub-stacks.
	 *
	 * This method allows the items (i.e. Units) within a sub-stack to be
	 * modified without changing the stack itself or the group of
	 * sub-stacks. It should <B>not</B> be used to move (change the position
	 * of) a sub-stack, although through it, specifically one of the Stack
	 * items, a portaged item could be moved (position-wise if mobile or to
	 * another Unit if not). The key for each entry may be used as an
	 * argument to the takeSubStack() method, which (obviously) does allow
	 * changes to the stack.
	 *
	 * @return a <CODE>Map</CODE> of key-value pairs associated with the
	 * sub-stacks managed by this instance. The return value is null if the
	 * instance is managing a Unit object or there are no sub-stacks.
	 *
	 * @see #takeSubStack
	 */

	public Map<Integer,Stack> subStacks()
	{
		if ((null == _unit) && !_subStacks.isEmpty())
		{
			return Collections.unmodifiableMap(_subStacks);
		}

		return null;
	}

	// Public update methods (Single Stack)

	/**
	 * Add another item to be portaged by the Unit managed by an instance.
	 *
	 * If a position has been set in the Stack argument, it will be cleared.
	 *
	 * @param item the Unit (also managed by a Stack) to be portaged
	 *
	 * @return a <CODE>boolean</CODE> indicating whether or not the specified Stack was
	 * added successfully. It should always be true unless the argument is
	 * null, the instance is <B>not</B> managing a Unit object or the specified
	 * item is not (i.e. also supports addition of sub-stacks), the new
	 * stack has an ID greater than zero that matches another portaged item,
	 * or the item within the stack <B>cannot</B> legitimately be portaged by the
	 * Unit managed by this instance.
	 */

	public boolean addPortagedItem(Stack item)
	{
		// Don't allow the new Stack to be added if either this instance
		// is not managing a Unit or the one to be added is not
		// (managing a Unit).

		if ((null == item) || (null == _unit) || (null == item.unit()))
		{
			return false;
		}

		// TODO: Verify that the item within the stack can be legitimately
		// portaged by the Unit managed by this instance.

		return addStack(item,_portagedItems);
	}

	/**
	 * Return the portaged item associated with the specified ID.
	 *
	 * This method allows the caller to remove the item from control of the
	 * Unit managed by this instance. If set, the position setting within
	 * the departing item will be updated to match this instance.
	 *
	 * @param itemID the ID associated with the Stack to be removed
	 *
	 * @return the <CODE>Stack</CODE> with an ID matching the argument. If a matching
	 * item is not found or the current instance is <B>not</B> managing a Unit, the
	 * return value is null.
	 *
	 * @see #portagedItems
	 */

	public Stack takePortagedItem(Integer itemID)
	{
		Stack subStack = null;

		if ((null != _unit) && !_portagedItems.isEmpty() &&
		    _portagedItems.containsValue(itemID))
		{
			subStack = _portagedItems.remove(itemID);

			subStack.setPositionLabel(_positionLabel);
		}

		return subStack;
	}

	// Public update methods (Stack collection)

	/**
	 * Add another Stack to the list managed by an instance.
	 *
	 * If a position has been set in the Stack argument, it will be cleared.
	 *
	 * @param stack the Stack to be added
	 *
	 * @return a <CODE>boolean</CODE> indicating whether or not the specified Stack was
	 * added successfully. It should always be true unless the argument is
	 * null, the instance is managing a Unit object or the specified item is
	 * not (i.e. also supports addition of sub-stacks), or the new stack has
	 * an ID greater than zero that matches an existing sub-stack.
	 */

	public boolean addSubStack(Stack stack)
	{
		// Don't allow the new Stack to be added if either this instance
		// is managing a Unit or the one to be added is not (managing a
		// Unit).

		if ((null == stack) || (null != _unit) || (null == stack.unit()))
		{
			return false;
		}

		return addStack(stack,_subStacks);
	}

	/**
	 * Return the sub-stack associated with the specified ID.
	 *
	 * This method allows the caller to remove the sub-stack from the stack,
	 * taking control of it. The position setting within the departing
	 * sub-stack will be updated to match this instance.
	 *
	 * @param stackID the ID associated with the Stack to be removed
	 *
	 * @return the <CODE>Stack</CODE> with an ID matching the argument. If a matching
	 * item is not found or the current instance is managing a Unit, the
	 * return value is null.
	 *
	 * @see #subStacks
	 */

	public Stack takeSubStack(Integer stackID)
	{
		Stack subStack = null;

		if ((null == _unit) && !_subStacks.isEmpty() &&
		    _subStacks.containsValue(stackID))
		{
			subStack = _subStacks.remove(stackID);

			subStack.setPositionLabel(_positionLabel);
		}

		return subStack;
	}

	// Private update methods (Stack collection)

	/**
	 * Add the specified stack to the indicated stack group.
	 *
	 * This helper method is called from addPortagedItem() and
	 * addSubStack(). Its primary purpose is to implement the rules
	 * associated with setting the ID/Key correctly in the Map. Whether or
	 * not the stack should be added to the specified Map is assumed to have
	 * been determined before this method is called.
	 *
	 * @param stack the Stack to be added
	 * @param destinationStack the (internal) Map that the Stack should
	 * be referenced by
	 *
	 * @return a <CODE>boolean</CODE> indicating whether or not the specified Stack was
	 * added successfully. It should always be true unless one of the
	 * arguments is null or the new stack has an ID greater than zero that
	 * matches a stack in the Map.
	 */

	private boolean addStack(Stack stack,
	                         LinkedHashMap<Integer,Stack> destinationStack)
	{
		if ((null == stack) || (null == destinationStack)) return false;

		Integer subStackKey = Integer.valueOf(stack.stackID());

		// When the stack ID is zero, the keys in the sub-stack map are
		// set incrementally.

		if ((0 == subStackKey.intValue()) && !destinationStack.isEmpty())
		{
			Set<Integer> subStackKeySet = destinationStack.keySet();

			// Assume that the next key ID is 1 greater than the
			// last one added.

			subStackKey = Integer.valueOf(destinationStack.size());

			// If an entry with the new key exists, assume that a
			// previously added sub-stack has been removed and
			// attempt to find (starting at zero) a "free" ID.

			if (subStackKeySet.contains(subStackKey))
			{
				for (int i = 0;i < destinationStack.size();++i)
				{
					subStackKey = Integer.valueOf(i);

					if (!subStackKeySet.contains(subStackKey)) break;
				}
			}
		}

		// This should only happen if sub-stacks with unique IDs are
		// used and a duplicate exists OR when unique IDs and generated
		// keys (stack ID equals zero (see above)) are mixed.

		if (!destinationStack.isEmpty() &&
		    destinationStack.keySet().contains(subStackKey))
		{
			return false;
		}

		// Clear the position label in the new stack and then add it.

		stack.setPositionLabel(null); // Specifying null makes it empty.

		destinationStack.put(subStackKey,stack);

		return true;
	}
}
