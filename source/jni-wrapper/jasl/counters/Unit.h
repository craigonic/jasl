/**
 * \file Unit.h
 *
 * This file declares a "wrapper" class intended to simplify access for C/C++
 * programs to the <A HREF="../../../jasl/counters/Unit.html">Unit</A> class, which is implemented in <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A>.
 *
 * Written By: Craig R. Campbell  -  January 2010
 */

#pragma once

#include "JaslEnums.h"

#include <jni.h>

#include <string>

/**
 * \brief <A HREF="../../../jasl/counters/Unit.html">Unit</A> class <A HREF="https://docs.oracle.com/javase/8/docs/technotes/guides/jni/">JNI</A> (Java Native Interface) wrapper.
 *
 * This class is used to provide access to its namesake, which is implemented
 * in <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A>, from a C++ program. This is done through the <A HREF="../../JniWrapper.h.html">JniWrapper</A>, which
 * provides a JVM to execute the library code, as well as string conversion and
 * other helper methods.
 *
 * Note that all interactions with the JVM are expected to work, so in the event
 * of failure, the program will assert.
 *
 * @version 0.4
 * @author Copyright (C) 2010-2019 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../source/jni-wrapper/jasl/counters/Unit.h.html">Source code</A>
 */

class Unit
{
	public:

		// The returned string from each of the "to" methods is a copy
		// of a Java <A HREF="https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/lang/String.html">String</A>, converted to the indicated type using the
		// <A HREF="../../JniWrapper.h.html#_JS2SS_">js2ss</A>() function.

		/**
		 * \brief Return a text representation of the attributes and
		 * current state of this Unit.
		 *
		 * The returned string includes a label and value for each data
		 * member defined for the concrete class type.
		 */

		std::string toText() const noexcept;

		/**
		 * \brief Return an abbreviated description, which may include
		 * attributes, of this Unit.
		 *
		 * The returned string includes data specific to the concrete
		 * class type.
		 */

		std::string toString() const noexcept;

		/**
		 * \brief Return a JSON representation of the attributes and
		 * current state of this Unit.
		 *
		 * The returned string includes a label (key) and value for each
		 * data member defined for the concrete class type.
		 */

		std::string toJSON() const noexcept;

		/**
		 * \brief Return the <A HREF="../../../jasl/counters/Description.html">description</A> of this Unit.
		 *
		 * The returned item is an enum representing its Java
		 * counterpart. See <A HREF="JaslEnums.h.html">here</A> for details.
		 */

		Descriptions description() const noexcept;

		/**
		 * \brief Update an instance of this class to reflect the settings
		 * within the specified JSON data.
		 *
		 * The setting for each attribute is checked against the
		 * corresponding input value. An exception will be thrown if the
		 * argument is empty, has non-matching data values, or is not
		 * valid JSON.
		 */

		void fromJSON(const std::string& jsonData);
/// @cond TEST
		/**
		 * \brief Release the reference to the instance of the "wrapped" class.
		 *
		 * The "wrapped" instance is <B>not</B> automatically freed through
		 * garbage collection until the virtual machine (managed by the
		 * JniWrapper) is informed, which is done here.
		 *
		 * This method is included for testing purposes only. It is
		 * assumed that in normal use, the memory management for the
		 * underlying item will be handled within the Java code,
		 * particularly when items of this type are managed through the
		 * <A HREF="../../../jasl/ui/data/data.html">ui.data</A> package.
		 */

		void release() const noexcept;
/// @endcond

/// @cond DEVELOPER
	protected:

		/**
		 * Reference to an instance of the "wrapped" class.
		 *
		 * This item is set in the (derived) concrete class constructor.
		 * The memory associated with it is freed by the virtual
		 * machine.
		 */

		jobject unitObject = nullptr;

		/**
		 * Reference to the "wrapped" class <B>type</B> in the Java code.
		 *
		 * This item is set in the (derived) concrete class constructor
		 * and applied there and in methods throughout the hierarchy to
		 * locate the corresponding (bytecode) class items via the
		 * virtual machine.
		 */

		jclass unitClass = nullptr;
/// @endcond

	private:

		// These items are used to "cache" the method identifiers
		// (returned by a call to GetMethodID()). They are initialized
		// to NULL and set the first time that their respective method
		// is called.

//		static jmethodID _constructorID;
		static jmethodID _toTextMethodID;
		static jmethodID _toStringMethodID;
		static jmethodID _toJSONMethodID;
		static jmethodID _descriptionMethodID;
		static jmethodID _descriptionOrdinalMethodID;
		static jmethodID _fromJSONMethodID;
};
