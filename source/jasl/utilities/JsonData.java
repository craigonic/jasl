// ************************************************************************** //
// JsonData.java - This interface is part of the <B>utilities</B> package, which     //
//                 contains the definitions of interfaces and objects used to //
//                 support the classes more directly associated with the game //
//                 itself.                                                    //
//                                                                            //
// Written By: Craig R. Campbell  -  November 2016                            //
// ************************************************************************** //

package jasl.utilities;

/**
 * This interface is used to define the public constants and methods used to
 * generate and parse a JSON representation of an instance of a public class in
 * one of the other jasl packages.
 *
 * @version 1.0
 * @author Copyright (C) 2016 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/utilities/JsonData.html">Source code</A>
 */

public interface JsonData
{
	// Public symbolic constants

	// These items are intended to be used in implementations of the
	// toJSON() method (and any helper method(s) called from them).

	/**
	 * The text designating the beginning and end of a key or a string
	 * value in JSON output.
	 */

	public static final String JSON_DOUBLE_QUOTE = "\"";

	/**
	 * The text separating a key and a non-string (number or boolean) value
	 * in JSON output.
	 */

	public static final String JSON_KEY_OTHER_VALUE_SEPARATOR =
		JSON_DOUBLE_QUOTE + ":";

	/**
	 * The text separating a key and a string value in JSON output.
	 */

	public static final String JSON_KEY_STRING_VALUE_SEPARATOR =
		JSON_KEY_OTHER_VALUE_SEPARATOR + JSON_DOUBLE_QUOTE;

	/**
	 * The text indicating the beginning of an object container in JSON
	 * output.
	 *
	 * Note that the string includes a CR-LF ('\n') after the curly brace.
	 */

	public static final String JSON_OBJECT_START = "{\n";

	/**
	 * The text separating an object entry from the next one in JSON output.
	 *
	 * Note that the string includes a CR-LF ('\n') after the comma.
	 */

	public static final String JSON_OBJECT_SEPARATOR = ",\n";

	/**
	 * The text indicating the end of an object container in JSON output.
	 *
	 * Note that the string includes a CR-LF ('\n') before the curly brace.
	 */

	public static final String JSON_OBJECT_END = "\n}";

	// Access methods

	/**
	 * Display a JSON representation of an instance of a class that
	 * implements this interface.
	 *
	 * @return a <CODE>String</CODE> containing the JSON data.
	 */

	public abstract String toJSON();

	// JSON output generation helper class

	/**
	 * This class provides a set of helper methods which, along with the
	 * constants in the enclosing interface, can be used in implementations
	 * of toJSON().
	 */

	public final class JsonOutput
	{
		// Symbolic constants

		// This constant is used as part of the error messages (see
		// below) that are generated when an exception is thrown.

		private static final String CLASS_NAME =
			JsonOutput.class.getSimpleName();

		// The following methods are intended for use by the toJSON()
		// implementations in classes that implement the enclosing
		// interface.

		/**
		 * Generate a JSON name/value pair that includes the specified
		 * parameters.
		 *
		 * @param name the name associated with the entry.
		 * @param value the value as a String.
		 *
		 * @return a <CODE>String</CODE> containing the JSON pair data.
		 *
		 * @throws NullPointerException in the case of a null name or
		 * value.
		 * @throws IllegalArgumentException in the case of an empty
		 * (zero length) name.
		 */

		public static final String buildJSONPair(String name,
		                                         String value)
		{
			// Define local constants.

			String METHOD_NAME = "buildJSONPair (String value)";

			// Check the value parameter received and throw an
			// exception if it is null. The validity of the name
			// parameter will be checked in buildJSONName().

			if (null == value)
			{
				throw new NullPointerException(Messages.buildErrorMessage(CLASS_NAME,
				                                                          METHOD_NAME,
				                                                          Messages.NULL_PARAMETER_MSG));
			}

			// Create a buffer to store the string to be returned.

			StringBuffer returnString =
				new StringBuffer(buildJSONName(name));

			returnString.append(JSON_KEY_STRING_VALUE_SEPARATOR);
			returnString.append(value);
			returnString.append(JSON_DOUBLE_QUOTE);

			// Return the completed string to calling program.

			return returnString.toString();
		}

		/**
		 * Generate a JSON name/value pair that includes the specified
		 * parameters.
		 *
		 * @param name the name associated with the entry.
		 * @param value the value as an integer.
		 *
		 * @return a <CODE>String</CODE> containing the JSON pair data.
		 *
		 * @throws NullPointerException in the case of a null name.
		 * @throws IllegalArgumentException in the case of an empty
		 * (zero length) name.
		 */

		public static final String buildJSONPair(String name,int value)
		{
			// Create a buffer to store the string to be returned.
			// The validity of the name parameter will be checked in
			// buildJSONName().

			StringBuffer returnString =
				new StringBuffer(buildJSONName(name));

			returnString.append(JSON_KEY_OTHER_VALUE_SEPARATOR);
			returnString.append(value);

			// Return the completed string to calling program.

			return returnString.toString();
		}

		/**
		 * Generate a JSON name/value pair that includes the specified
		 * parameters.
		 *
		 * @param name the name associated with the entry.
		 * @param value the value as a boolean.
		 *
		 * @return a <CODE>String</CODE> containing the JSON pair data.
		 *
		 * @throws NullPointerException in the case of a null name.
		 * @throws IllegalArgumentException in the case of an empty
		 * (zero length) name.
		 */

		public static final String buildJSONPair(String name,
		                                         boolean value)
		{
			// Create a buffer to store the string to be returned.
			// The validity of the name parameter will be checked in
			// buildJSONName().

			StringBuffer returnString =
				new StringBuffer(buildJSONName(name));

			returnString.append(JSON_KEY_OTHER_VALUE_SEPARATOR);
			returnString.append(Messages.getTruthLabel(value).toLowerCase());

			// Return the completed string to calling program.

			return returnString.toString();
		}

		/**
		 * Generate a JSON name entry using the specified parameter.
		 *
		 * @param name the name associated with the entry.
		 *
		 * This method is intended only for use by the buildJSONPair()
		 * methods.
		 *
		 * @return a <CODE>String</CODE> containing the JSON pair data.
		 *
		 * @throws NullPointerException in the case of a null name.
		 * @throws IllegalArgumentException in the case of an empty
		 * (zero length) name.
		 */

		private static String buildJSONName(String name)
		{
			// Define local constants.

			String METHOD_NAME = "buildJSONName";

			// Check the parameters received and throw the
			// appropriate exception if necessary.

			if (null == name)
			{
				throw new NullPointerException(Messages.buildErrorMessage(CLASS_NAME,
				                                                          METHOD_NAME,
				                                                          Messages.NULL_PARAMETER_MSG));
			}

			if (name.isEmpty())
			{
				throw new IllegalArgumentException(Messages.buildErrorMessage(CLASS_NAME,
				                                                              METHOD_NAME,
				                                                              Messages.ZERO_LENGTH_PARAMETER_MSG));
			}

			return JSON_DOUBLE_QUOTE + name;
		}
	}
}
