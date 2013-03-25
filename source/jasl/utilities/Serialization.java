// ************************************************************************** //
// Serialization.java - This class is part of the <B>utilities</B> package,          //
//                      which contains the definitions of objects used to     //
//                      support the classes more directly associated with the //
//                      game itself.                                          //
//                                                                            //
// Written By: Craig R. Campbell  -  March 2013                               //
// ************************************************************************** //

package jasl.utilities;

import java.io.*; // For [File|Object][In|Out]putStream classes.

/**
 * This class provides static methods used to serialize a specified object into
 * a file, as well as to read a file and deserialize its contents into an
 * object.
 *
 * @version 1.0
 * @author Copyright (C) 2013 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/utilities/Serialization.html">Source code</A>
 */

public final class Serialization
{
	// Symbolic constants

	private static final String CLASS_NAME = "Serialization";

	// Public static methods

	/**
	 * Write the specified object to the indicated file.
	 *
	 * @param object the object to be serialized.
	 * @param filename the name of the file that the serialized data
	 * should be written to.
	 *
	 * @throws NullPointerException in the case of a null object or file name
	 * @throws IllegalArgumentException in the case of a zero length file
	 * name
	 */

	public static final void serializeToFile(Object object,String filename)
	{
		// Define local constants.

		String METHOD_NAME = "serializeToFile";

		// Check the parameters received and throw the appropriate
		// exception if necessary.

		if ((null == object) || (null == filename))
		{
			throw new NullPointerException(Messages.buildErrorMessage(CLASS_NAME,
			                                                          METHOD_NAME,
			                                                          Messages.NULL_PARAMETER_MSG));
		}

		if (0 == filename.length())
		{
			throw new IllegalArgumentException(Messages.buildErrorMessage(CLASS_NAME,
			                                                              METHOD_NAME,
			                                                              Messages.ZERO_LENGTH_PARAMETER_MSG));
		}

		try
		{
			ObjectOutputStream objectOutputStream =
				new ObjectOutputStream(new FileOutputStream(filename));
			objectOutputStream.writeObject(object);
			objectOutputStream.close();
		}

		catch (IOException exception)
		{
			System.out.println("Caught: " + exception);
		}
	}

	/**
	 * Deserialize the data in the specified file into an object.
	 *
	 * @param filename the name of the file containing the data to be
	 * deserialized.
	 *
	 * @return the <CODE>Object</CODE> created during deserialization of the
	 * file data. If the process fails, the return value is null.
	 *
	 * @throws NullPointerException in the case of a null file name
	 * @throws IllegalArgumentException in the case of a zero length file
	 * name
	 */

	public static final Object deserializeFromFile(String filename)
	{
		Object object = null;

		// Define local constants.

		String METHOD_NAME = "deserializeFromFile";

		// Check the parameters received and throw the appropriate
		// exception if necessary.

		if (null == filename)
		{
			throw new NullPointerException(Messages.buildErrorMessage(CLASS_NAME,
			                                                          METHOD_NAME,
			                                                          Messages.NULL_PARAMETER_MSG));
		}

		if (0 == filename.length())
		{
			throw new IllegalArgumentException(Messages.buildErrorMessage(CLASS_NAME,
			                                                              METHOD_NAME,
			                                                              Messages.ZERO_LENGTH_PARAMETER_MSG));
		}

		try
		{
			ObjectInputStream objectInputStream =
				new ObjectInputStream(new FileInputStream(filename));
			object = objectInputStream.readObject();
			objectInputStream.close();
		}

		catch (IOException exception)
		{
			System.out.println("Caught: " + exception);
		}

		catch (ClassNotFoundException exception)
		{
			System.out.println("Caught: " + exception);
		}

		return object;
	}
}
