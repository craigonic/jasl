/**
 * \file Dice.h
 *
 * This file declares a "wrapper" class intended to simplify access for C/C++
 * programs to the <A HREF="../../../jasl/utilities/Dice.html">Dice</A> class, which is implemented in <A HREF="http://java.sun.com/">Java</A>.
 *
 * Written By: Craig R. Campbell  -  November 2010
 */

#ifndef CNI_DICE_H
#define CNI_DICE_H

#ifdef __cplusplus
namespace jasl
{
	namespace utilities
	{
		class Dice;
	}
}

/**
 * \brief <A HREF="../../../jasl/utilities/Dice.html">Dice</A> class <A HREF="http://gcc.gnu.org/onlinedocs/gcj/About-CNI.html#About-CNI">CNI</A> (Compiled Native Interface) wrapper.
 *
 * This class is used to simplify access to its namesake, which is implemented
 * in <A HREF="http://java.sun.com/">Java</A> and compiled into a library with <A HREF="http://gcc.gnu.org/java/">GCJ</A>. It also interacts with the
 * <A HREF="../../CniWrapper.h.html">CniWrapper</A>, which provides a JVM to execute the library code, as well as
 * string conversion methods.
 */

class Dice
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
		 * The destructor frees the memory associated with the string
		 * returned by the toString() method. The "wrapped" object is
		 * freed through garbage collection by the virtual machine
		 * managed by the CniWrapper.
		 */

		~Dice();

		/** <A NAME="_WHITE_DIE_VALUE_"></A>
		 * \brief Return the result of rolling the white die.
		 */

		int whiteDieValue() const;

		/** <A NAME="_COLORED_DIE_VALUE_"></A>
		 * \brief Return the result of rolling the colored die.
		 */

		int coloredDieValue() const;

		/** <A NAME="_COMBINED_RESULT_"></A>
		 * \brief Return the result of combining the values of the two
		 * dice.
		 */

		int combinedResult() const;

		/** <A NAME="_TO_STRING_"></A>
		 * \brief Return a text representation of the attributes and
		 * current state of this Dice.
		 *
		 * The returned string includes a label and value for each data
		 * member defined for the concrete class type. <B>IT SHOULD NOT BE
		 * DELETED, AS THIS OCCURS AS PART OF THE DESTRUCTION OF THE
		 * OBJECT.</B>
		 */

		const char* toString();

	private:

		/**
		 * \brief Pointer to an instance of the "wrapped" class.
		 *
		 * This item is set in the constructor.
		 */

		jasl::utilities::Dice* _dice;

		/**
		 * The text representation of the attributes and current state
		 * for this Dice instance.
		 *
		 * This item references a copy of a Java String, converted to
		 * the indicated type using the js2cc() function. The copy is
		 * generated during the initial call to the toString() method.
		 * Subsequent calls only return the generated copy. The memory
		 * associated with it is freed in the destructor.
		 */

		const char* _dump;

		// Disable the generation of a copy constructor, and "="
		// operator.

		Dice(const Dice& dice);
		Dice& operator=(const Dice& dice);
};
#else
/******************************************************************************/

// The typedef and functions declared below are intended for use by C programs
// to access Dice objects.

/**
 * \typedef struct Dice Dice
 * \brief Declaration to provide access to Dice objects from C programs.
 */

typedef struct Dice Dice;
#endif // __cplusplus
#ifdef __cplusplus
extern "C" {
#endif // __cplusplus

/**
 * \brief Create an instance of a Dice object, which automatically rolls them.
 */

extern Dice* rollDice();

/**
 * \brief Free the memory associated with the specified object.
 */

extern void deleteDice(Dice* dice);

/**
 * \brief Return the result of rolling the white die.
 *
 * This function calls the <A HREF="#_WHITE_DIE_VALUE_">whiteDieValue</A>() method of the indicated object.
 */

extern int whiteDieValue(Dice* dice);

/**
 * \brief Return the result of rolling the colored die.
 *
 * This function calls the <A HREF="#_COLORED_DIE_VALUE_">coloredDieValue</A>() method of the indicated object.
 */

extern int coloredDieValue(Dice* dice);

/**
 * \brief Return the result of combining the values of the two dice.
 *
 * This function calls the <A HREF="#_COMBINED_RESULT_">combinedResult</A>() method of the indicated object.
 */

extern int combinedResult(Dice* dice);

/**
 * \brief Return a text representation of the current state of the Dice.
 *
 * This function calls the <A HREF="#_TO_STRING_">toString</A>() method of the indicated object.
 *
 * <B>NOTE: THE RETURNED STRING SHOULD NOT BE DELETED OR FREED. IT OCCURS AS PART
 * OF THE DESTRUCTION OF THE Dice OBJECT.</B>
 */

extern const char* toString(Dice* dice);
#ifdef __cplusplus
}
#endif // __cplusplus
#endif // CNI_DICE_H
