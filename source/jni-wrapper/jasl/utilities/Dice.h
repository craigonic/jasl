/**
 * \file Dice.h
 *
 * This file declares a "wrapper" class intended to simplify access for C/C++
 * programs to the <A HREF="../../../jasl/utilities/Dice.html">Dice</A> class, which is implemented in <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A>.
 *
 * Written By: Craig R. Campbell  -  November 2010
 */

#pragma once

#include <jni.h>

#include <string>

/**
 * \brief <A HREF="../../../jasl/utilities/Dice.html">Dice</A> class <A HREF="https://docs.oracle.com/javase/8/docs/technotes/guides/jni/">JNI</A> (Java Native Interface) wrapper.
 *
 * This class is used to provide access to its namesake, which is implemented
 * in <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A>, from a C++ program. This is done through the <A HREF="../../JniWrapper.h.html">JniWrapper</A>, which
 * provides a JVM to execute the library code, as well as string conversion
 * methods.
 *
 * Note that all interactions with the JVM are expected to work, so in the event
 * of failure, the program will assert.
 *
 * @version 4.0
 * @author Copyright (C) 2010-2018 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jni-wrapper/jasl/utilities/Dice.h.html">Source code</A>
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
		 * garbage collection until the virtual machine (managed by the
		 * JniWrapper) is informed, which is done here.
		 */

		~Dice();

		// Disable the generation of a copy constructor and "="
		// operator.

		Dice(Dice& dice) = delete;
		Dice& operator=(const Dice& dice) = delete;

		/**
		 * \brief Return the result of rolling the white die.
		 */

		int whiteDieValue() const noexcept;

		/**
		 * \brief Return the result of rolling the colored die.
		 */

		int coloredDieValue() const noexcept;

		/**
		 * \brief Return the result of combining the values of the two dice.
		 */

		int combinedResult() const noexcept;

		/**
		 * \brief Return a text representation of the attributes and
		 * current state of this Dice instance.
		 *
		 * The returned string is a copy of a Java <A HREF="http://docs.oracle.com/javase/10/docs/api/java/lang/String.html">String</A>, converted to
		 * the indicated type using the <A HREF="../../JniWrapper.h.html#_JS2SS_">js2ss</A>() function.
		 */

		std::string toText() noexcept;

	private:

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

		jclass _diceClass;
};
