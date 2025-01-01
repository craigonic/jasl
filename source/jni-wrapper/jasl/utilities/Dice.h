/**
 * \file Dice.h
 *
 * This file declares a "wrapper" class intended to simplify access for C/C++
 * programs to the <A HREF="../../../jasl/utilities/Dice.html">Dice</A> class, which is implemented in <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A>.
 *
 * Written By: Craig R. Campbell  -  January 2018
 */

#pragma once

#include <jni.h>

#include <string>

namespace jasl {
namespace utilities {

/**
 * \brief <A HREF="../../../jasl/utilities/Dice.html">Dice</A> class <A HREF="https://docs.oracle.com/javase/8/docs/technotes/guides/jni/">JNI</A> (Java Native Interface) wrapper.
 *
 * This class is used to provide access to its namesake, which is implemented
 * in Java, from a C++ program.
 *
 * Note that all interactions with the JVM are expected to work, so in the event
 * of failure, the program will assert.
 *
 * @version 5.0
 * @author Copyright (C) 2018-2024 Craig R. Campbell (craigonic@gmail.com)
 */

class Dice final
{
	public:

		/**
		 * \brief Constructor.
		 *
		 * The constructor creates an instance of the "wrapped" class,
		 * which results in an automatic roll of the dice.
		 */

		Dice();

		/**
		 * \brief Destructor.
		 *
		 * The "wrapped" instance is <B>not</B> automatically freed through
		 * garbage collection until the Java virtual machine is
		 * informed, which is done here.
		 */

		~Dice();

		/**
		 * \brief Return the result of rolling the white die.
		 */

		int whiteDieValue() const noexcept;

		/**
		 * \brief Return the result of rolling the colored die.
		 */

		int coloredDieValue() const noexcept;

		/**
		 * \brief Return the result of combining the values of the two
		 * (white and colored) dice.
		 */

		int combinedResult() const noexcept;

		/**
		 * \brief Return the result of rolling the subsequent die.
		 */

		int subsequentDieValue() const noexcept;

		// The returned string from toText() is a copy of a Java String,
		// converted to the indicated type using the <A HREF="../../JniWrapper.h.html#_JS2SS_">js2ss</A>() function.

		/**
		 * \brief Return a text representation of the attributes and
		 * current state of this Dice instance.
		 */

		std::string toText() const noexcept;

	private:

		// Disable the generation of a copy constructor and "="
		// operator.

		Dice(Dice& dice) = delete;
		Dice& operator=(const Dice& dice) = delete;

		/**
		 * Pointer to an instance of the "wrapped" class.
		 *
		 * This item is set in the constructor. The memory associated
		 * with it is freed by the virtual machine after notification
		 * is given in the destructor.
		 */

		jobject _diceObject;

		/**
		 * Reference to the "wrapped" class in the Java code.
		 *
		 * This item is set in the constructor and applied in the
		 * constructor and the methods to locate the corresponding
		 * (bytecode) class items via the virtual machine.
		 */

		static jclass _diceClass;

		// These items are used to "cache" the method identifiers
		// (returned by a call to GetMethodID()). They are initialized
		// to NULL and set the first time that their respective method
		// is called.

		static jmethodID _constructorID;
		static jmethodID _whiteDieValueMethodID;
		static jmethodID _coloredDieValueMethodID;
		static jmethodID _combinedResultMethodID;
		static jmethodID _subsequentDieValueMethodID;
		static jmethodID _toTextMethodID;
};

} // namespace utilities
} // namespace jasl
