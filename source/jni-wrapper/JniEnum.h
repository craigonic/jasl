/**
 * \file JniEnum.h
 *
 * This file declares a proxy intended to mirror some of the behavior of a Java
 * Enum, for use in C++ programs.
 *
 * Written By: Craig R. Campbell  -  November 2018
 */

#pragma once

#include <jni.h>

#include <string>

/**
 * \brief JNI (Java Native Interface) proxy for a Java Enum.
 *
 * The data item and functions in this struct provide a subset of the
 * functionality of a Java Enum, allowing a (Java) instance to accessed from a
 * C++ program. This is done through the <A HREF="JniWrapper.h.html">JniWrapper</A>, which provides a JVM to
 * execute the library code, as well as string conversion and other helper
 * methods.
 *
 * @version 0.4
 * @author Copyright (C) 2018-2024 Craig R. Campbell (craigonic@gmail.com)
 */

struct JniEnumInterface final
{
	/**
	 * The "cached" JNI access data associated with a Java Enum class type.
	 *
	 * An item of this type is expected to be instantiated for each Enum
	 * that uses this interface. It is initialized in the implementation
	 * file, with the enumPath being set to match the class path of the
	 * Enum. The other entries are set the first time that the instance is
	 * passed to one of the "convertTo" functions.
	 */

	struct JniEnumData final
	{
		/// The class path of the Enum (e.g., "path/to/EnumName").
		std::string enumPath;
		/// The Java Class object associated with the Enum.
		jclass      enumClass        = nullptr; // JniWrapper::toClass()
		/// A reference to the values() method of the Enum.
		jmethodID   valuesMethodID   = nullptr; // convertToObject()
		/// A reference to the name() method of the Enum.
		jmethodID   nameMethodID     = nullptr; // ""
		/// A reference to the valueOf() method of the Enum.
		jmethodID   valueOfMethodID  = nullptr; // ""
		/// A reference to the toString() method of the Enum.
		jmethodID   toStringMethodID = nullptr; // convertToString()
	};

	/**
	 * Return an instance of the specifed Enum element.
	 *
	 * The Enum type is designated by the enumPath element of the
	 * jniEnumData parameter, with the value corresponding to the specified
	 * index. On the initial call the relevant "method ID" elements of the
	 * JniEnumData parameter will also be set.
	 */

	static jobject convertToObject(JniEnumData& jniEnumData,
	                               int enumValueIndex) noexcept;
	/**
	 * Return the label associated with the specifed Enum element.
	 *
	 * The Enum type is designated by the enumPath element of the
	 * jniEnumData parameter, with the text corresponding to the specified
	 * index. On the initial call the toString "method ID" element of the
	 * JniEnumData parameter will also be set.
	 */

	static std::string convertToString(JniEnumData& jniEnumData,
	                                   int enumValueIndex) noexcept;
};
