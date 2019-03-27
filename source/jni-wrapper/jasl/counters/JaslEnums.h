/**
 * \file JaslEnums.h
 *
 * This file declares a proxy intended to mirror some of the behavior of the
 * <A HREF="https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/lang/Enum.html">Enum</A>s in the <A HREF="../../../jasl/jasl.html">jasl</A> packages, which are implemented in <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A>, for use in C++
 * programs.
 *
 * Written By: Craig R. Campbell  -  November 2018
 */

#pragma once

#include <jni.h>

#include <string>

/**
 * \brief <A HREF="../../../jasl/counters/Description.html">Descriptions</A> enum <A HREF="https://docs.oracle.com/javase/8/docs/technotes/guides/jni/">JNI</A> (Java Native Interface) proxy.
 */

enum class Descriptions : int
{
	CREW,
	HALF_SQUAD,
	LEADER,
	SQUAD
};
/// @cond DEVELOPER
/**
 * \brief <A HREF="https://docs.oracle.com/javase/8/docs/technotes/guides/jni/">JNI</A> (Java Native Interface) proxy for <A HREF="../../../jasl/jasl.html">jasl</A> <A HREF="https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/lang/Enum.html">Enum</A>s.
 *
 * The functions in this struct provide a subset of the functionality of each of
 * the enums, which are implemented in <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A>. This is done through the
 * <A HREF="../../JniWrapper.h.html">JniWrapper</A>, which provides a JVM to execute the library code, as well as
 * string conversion and other helper methods.
 *
 * @version 0.2
 * @author Copyright (C) 2018-2019 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../source/jni-wrapper/jasl/counters/JaslEnums.h.html">Source code</A>
 */

struct JaslEnumInterface final
{
	private:

		// See the documentation of these functions for more details on
		// their purpose and operation.

		friend jobject toObject(Descriptions description) noexcept;
		friend std::string toString(Descriptions description) noexcept;

		/**
		 * The "cached" JNI access data associated with an enum class
		 * type.
		 *
		 * An item for each enum type is declared below. Each one is
		 * initialized in the implementation file, with the enumPath
		 * being set to match the Java Enum class name and the other
		 * entries being set to NULL. For each instance, the first two
		 * entries are modified and set, respectively, in the toClass()
		 * function. The rest are set in the indicated "convertTo"
		 * function.
		 */

		struct JaslEnumData final
		{
			std::string enumPath;
			jclass      enumClass;
			jmethodID   valuesMethodID;   // convertToObject()
			jmethodID   nameMethodID;     // ""
			jmethodID   valueOfMethodID;  // ""
			jmethodID   toStringMethodID; // convertToString()
		};

		/**
		 * The "cached" JNI access data associated with the Descriptions
		 * enum class.
		 */

		static struct JaslEnumData descriptionsEnumData;

		/**
		 * JaslEnumData item associated with the most recent enum
		 * <B>type</B> specified.
		 *
		 * This item is set in toClass() and used in the "convertTo"
		 * functions to access the cached (e.g. method ID) data.
		 */

		static struct JaslEnumData* currentEnumData;

		/**
		 * Return the Java Enum class reference associated with the
		 * specified enum type.
		 *
		 * The return value is used in the "convertTo" functions in the
		 * generation of method IDs, as well as in the calls to Java
		 * methods.
		 */

		template <typename EnumClass>
		static jclass toClass() noexcept;

		/**
		 * Return an instance of the specified enum type, set to the
		 * indicated value.
		 *
		 * The javaClass argument designates the type (e.g.
		 * Descriptions).
		 */

		static jobject convertToObject(jclass javaClass,
		                               int enumValueIndex) noexcept;
		/**
		 * Return the label associated with the specifed enum value.
		 *
		 * The javaClass argument is the toClass() representation of the
		 * enum type (e.g. Descriptions).
		 */

		static std::string convertToString(jclass javaClass,
		                                   int enumValueIndex) noexcept;
};
/// @endcond

/**
 * \brief Return an instance of a (Java) Description.Descriptions object, based on the
 * specified value.
 */

inline jobject toObject(Descriptions description) noexcept
{
	return JaslEnumInterface::convertToObject(JaslEnumInterface::toClass<Descriptions>(),
	                                          static_cast<int>(description));
}

// The returned string for each of the following methods is a copy of a Java
// <A HREF="https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/lang/String.html">String</A>, converted to the indicated type using the <A HREF="../../JniWrapper.h.html#_JS2SS_">js2ss</A>() function.

/**
 * \brief Return the label associated with the specifed enum value.
 */

inline std::string toString(Descriptions description) noexcept
{
	return JaslEnumInterface::convertToString(JaslEnumInterface::toClass<Descriptions>(),
	                                          static_cast<int>(description));
}
