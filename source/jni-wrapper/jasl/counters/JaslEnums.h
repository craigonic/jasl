/**
 * \file JaslEnums.h
 *
 * This file declares a proxy intended to mirror some of the behavior of the
 * Enums in the <A HREF="../../../jasl/jasl.html">jasl</A> packages, which are implemented in Java, for use in C++
 * programs.
 *
 * Written By: Craig R. Campbell  -  November 2018
 */

#pragma once

#include <jni.h>

#include <string>

/**
 * \brief JNI (Java Native Interface) proxy for a Java Enum.
 *
 * The functions in this struct provide a subset of the functionality of each of
 * the enums, which are implemented in Java. This is done through the
 * <A HREF="../../JniWrapper.h.html">JniWrapper</A>, which provides a JVM to execute the library code, as well as
 * string conversion and other helper methods.
 *
 * @version 0.3
 * @author Copyright (C) 2018-2024 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../source/jni-wrapper/jasl/counters/JaslEnums.h.html">Source code</A>
 */

struct JaslEnumInterface final
{
	/**
	 * The "cached" JNI access data associated with a Java Enum class type.
	 *
	 * An item of this type is expected to be instantiated for each Enum
	 * that uses this interface. It is initialized in the implementation
	 * file, with the enumPath being set to match the Java Enum class name
	 * and the other entries being set to NULL. For each instance, the
	 * second element is set in the toClass() function. The rest are set in
	 * the indicated "convertTo" function.
	 */

	struct JaslEnumData final
	{
		std::string enumPath;
		jclass      enumClass;        // toClass()
		jmethodID   valuesMethodID;   // convertToObject()
		jmethodID   nameMethodID;     // ""
		jmethodID   valueOfMethodID;  // ""
		jmethodID   toStringMethodID; // convertToString()
	};

	/**
	 * Return the Java Enum class reference associated with the specified
	 * enum type.
	 *
	 * The type is specified through the enumPath parameter. This item is
	 * used to find the corresponding class, which is returned to the
	 * caller.
	 *
	 * The return value is used in the "convertTo" functions as part of the
	 * generation of method IDs, as well as in the calls to Java methods.
	 */

	static jclass toClass(const std::string& enumPath) noexcept;

	/**
	 * Return an instance of the specifed Enum element.
	 *
	 * The Enum type is designated by the enumPath element of the
	 * jaslEnumData parameter, with the value corresponding to the specified
	 * index. On the initial call the relevant "method ID" elements of the
	 * JaslEnumData parameter will also be set.
	 */

	static jobject convertToObject(JaslEnumData& jaslEnumData,
	                               int enumValueIndex) noexcept;
	/**
	 * Return the label associated with the specifed Enum element.
	 *
	 * The Enum type is designated by the enumPath element of the
	 * jaslEnumData parameter, with the text corresponding to the specified
	 * index. On the initial call the toString "method ID" element of the
	 * JaslEnumData parameter will also be set.
	 */

	static std::string convertToString(JaslEnumData& jaslEnumData,
	                                   int enumValueIndex) noexcept;
};
