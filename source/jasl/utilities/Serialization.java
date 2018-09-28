// ************************************************************************** //
// Serialization.java - This class is part of the <B>utilities</B> package,          //
//                      which contains the definitions of objects used to     //
//                      support the classes more directly associated with the //
//                      game itself.                                          //
//                                                                            //
// Written By: Craig R. Campbell  -  March 2013                               //
// ************************************************************************** //

package jasl.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class provides static methods used to serialize a specified object into
 * a file or a byte array, as well as to read one of these and deserialize the
 * contents into an object.
 *
 * @version 2.0
 * @author Copyright (C) 2013-2016 Craig R. Campbell (craigonic@gmail.com)
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
	 * @throws NullPointerException in the case of a null object or file
	 * name.
	 * @throws IllegalArgumentException in the case of a zero length file
	 * name.
	 * @throws Exception in the case where an error occurs while opening the
	 * file, writing the serialized data to it, or serializing the object.
	 */

	public static final void serializeToFile(Object object,String filename)
		throws Exception
	{
		// Define local constants.

		String METHOD_NAME = "serializeToFile";

		// Check the arguments received and throw the appropriate
		// exception if necessary.

		if ((null == object) || (null == filename))
		{
			throw new NullPointerException(Messages.buildErrorMessage(CLASS_NAME,
			                                                          METHOD_NAME,
			                                                          Messages.NULL_PARAMETER_MSG));
		}

		if (filename.isEmpty())
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

		catch (Exception exception)
		{
			throw exception;
		}
	}

	/**
	 * Serialize the specified object into a byte array.
	 *
	 * @param object the object to be serialized.
	 *
	 * @return a <CODE>byte[]</CODE> array containing the serialized data.
	 *
	 * @throws NullPointerException in the case of a null object.
	 * @throws Exception in the case where an error occurs while serializing
	 * the object.
	 */

	public static final byte[] serializeToByteArray(Object object)
		throws Exception
	{
		// Define local constants.

		String METHOD_NAME = "serializeToByteArray";

		// Check the argument received and throw an exception if
		// necessary.

		if (null == object)
		{
			throw new NullPointerException(Messages.buildErrorMessage(CLASS_NAME,
			                                                          METHOD_NAME,
			                                                          Messages.NULL_PARAMETER_MSG));
		}

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		try
		{
			ObjectOutputStream objectOutputStream =
				new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(object);
			objectOutputStream.close();
		}

		catch (Exception exception)
		{
			throw exception;
		}

		return byteArrayOutputStream.toByteArray();
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
	 * @throws NullPointerException in the case of a null file name.
	 * @throws IllegalArgumentException in the case of a zero length file
	 * name.
	 * @throws Exception in the case where an error occurs while opening the
	 * file or deserializing the data within it.
	 */

	public static final Object deserializeFromFile(String filename)
		throws Exception
	{
		Object object = null;

		// Define local constants.

		String METHOD_NAME = "deserializeFromFile";

		// Check the argument received and throw the appropriate
		// exception if necessary.

		if (null == filename)
		{
			throw new NullPointerException(Messages.buildErrorMessage(CLASS_NAME,
			                                                          METHOD_NAME,
			                                                          Messages.NULL_PARAMETER_MSG));
		}

		if (filename.isEmpty())
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

		catch (Exception exception)
		{
			throw exception;
		}

		return object;
	}

	/**
	 * Deserialize the data in the specified byte array into an object.
	 *
	 * @param byteArray the byte array containing the data to be
	 * deserialized.
	 *
	 * @return the <CODE>Object</CODE> created during deserialization of the
	 * byte array. If the process fails, the return value is null.
	 *
	 * @throws NullPointerException in the case of a null byte array.
	 * @throws IllegalArgumentException in the case of a zero length byte
	 * array.
	 * @throws Exception in the case where an error occurs while
	 * deserializing the data within the byte array.
	 */

	public static final Object deserializeFromByteArray(byte[] byteArray)
		throws Exception
	{
		Object object = null;

		// Define local constants.

		String METHOD_NAME = "deserializeFromByteArray";

		// Check the argument received and throw the appropriate
		// exception if necessary.

		if (null == byteArray)
		{
			throw new NullPointerException(Messages.buildErrorMessage(CLASS_NAME,
			                                                          METHOD_NAME,
			                                                          Messages.NULL_PARAMETER_MSG));
		}

		if (0 == byteArray.length)
		{
			throw new IllegalArgumentException(Messages.buildErrorMessage(CLASS_NAME,
			                                                              METHOD_NAME,
			                                                              Messages.ZERO_LENGTH_PARAMETER_MSG));
		}

		try
		{
			ObjectInputStream objectInputStream =
				new ObjectInputStream(new ByteArrayInputStream(byteArray));
			object = objectInputStream.readObject();
			objectInputStream.close();
		}

		catch (Exception exception)
		{
			throw exception;
		}

		return object;
	}
}
