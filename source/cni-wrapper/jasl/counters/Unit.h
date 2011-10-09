/**
 * \file Unit.h
 *
 * This file declares a "wrapper" class intended to simplify access for C/C++
 * programs to the <A HREF="../../../jasl/counters/Unit.html">Unit</A> class, which is implemented in <A HREF="http://java.sun.com/">Java</A>.
 *
 * Written By: Craig R. Campbell  -  January 2010
 */

#ifndef CNI_UNIT_H
#define CNI_UNIT_H

#ifdef __cplusplus
namespace jasl
{
	namespace counters
	{
		class Unit;
	}
}

/**
 * \brief <A HREF="../../../jasl/counters/Unit.html">Unit</A> class <A HREF="http://gcc.gnu.org/onlinedocs/gcj/About-CNI.html#About-CNI">CNI</A> (Compiled Native Interface) wrapper.
 *
 * This class is used to simplify access to its namesake, which is implemented
 * in <A HREF="http://java.sun.com/">Java</A> and compiled into a library with <A HREF="http://gcc.gnu.org/java/">GCJ</A>. It also interacts with the
 * <A HREF="../../CniWrapper.h.html">CniWrapper</A> and <A HREF="../../JaslErrorMessage.h.html">JaslErrorMessage</A> classes, which are used both to enable Java
 * support and keep it "behind the scenes".
 */

class Unit
{
	public:

		/** <A NAME="_DESCRIPTION_"></A>
		 * \brief Return the description of this Unit.
		 *
		 * The returned string matches (mostly, since it may include
		 * extra space) the name of the concrete class type. The names
		 * are listed <A HREF="../../../jasl/counters/Description.html">here</A>. <B>IT SHOULD NOT BE DELETED, AS THIS OCCURS AS
		 * PART OF THE DESTRUCTION OF THE OBJECT.</B>
		 */

		const char* description();

		/** <A NAME="_TO_STRING_"></A>
		 * \brief Return a text representation of the attributes and
		 * current state of this Unit.
		 *
		 * The returned string includes a label and value for each data
		 * member defined for the concrete class type. <B>IT SHOULD NOT BE
		 * DELETED, AS THIS OCCURS AS PART OF THE DESTRUCTION OF THE
		 * OBJECT.</B>
		 */

		const char* toString();

	protected:

		/**
		 * \brief Pointer to an instance of the "wrapped" class.
		 *
		 * This item is set in the constructor of a derived concrete
		 * class.
		 */

		jasl::counters::Unit* _unit;

		/**
		 * \brief Constructor.
		 *
		 * The constructor is used only to initialize the member data
		 * pointers declared in this class.
		 */

		Unit();

		/**
		 * \brief Destructor.
		 *
		 * The destructor frees the memory associated with the strings
		 * returned by the access methods. The "wrapped" object is freed
		 * through garbage collection by the virtual machine managed by
		 * the CniWrapper.
		 */

		virtual ~Unit();

	private:

		/**
		 * The description setting for this Unit instance.
		 *
		 * This item references a copy of a Java <A HREF="http://java.sun.com/javase/6/docs/api/java/lang/String.html">String</A>, converted to
		 * the indicated type using the js2cc() function. The copy is
		 * generated during the initial call to the description()
		 * method. Subsequent calls return this item. The memory is
		 * freed in the destructor.
		 */

		const char* _description;

		/**
		 * The text representation of the attributes and current state
		 * for this Unit instance.
		 *
		 * This item references a copy of a Java String, converted to
		 * the indicated type using the js2cc() function. The copy is
		 * generated during the each call to the toString() method.
		 * Subsequent calls destroy the old string, create a new one,
		 * and return it. The memory for the results of the last call to
		 * toString() is freed in the destructor.
		 */

		const char* _dump;

		// Disable the generation of a copy constructor, and "="
		// operator.

		Unit(const Unit& unit);
		Unit& operator=(const Unit& unit);
};
#else
/******************************************************************************/

// The typedef and functions declared below are intended for use by C programs
// to access Unit objects.

/**
 * \typedef struct Unit Unit
 * \brief Declaration to provide access to Unit objects from C programs.
 */

typedef struct Unit Unit;
#endif // __cplusplus
#ifdef __cplusplus
extern "C" {
#endif // __cplusplus
/**
 * \brief Return the description of the specified Unit.
 *
 * This function calls the <A HREF="#_DESCRIPTION_">description</A>() method of the indicated object.
 *
 * <B>NOTE: THE RETURNED STRING SHOULD NOT BE DELETED OR FREED. IT OCCURS AS PART
 * OF THE DESTRUCTION OF THE Unit OBJECT.</B>
 */

extern const char* description(Unit* unit);

/**
 * \brief Return a text representation of the attributes and current state of
 * the specified Unit.
 *
 * This function calls the <A HREF="#_TO_STRING_">toString</A>() method of the indicated object.
 *
 * <B>NOTE: THE RETURNED STRING SHOULD NOT BE DELETED OR FREED. IT OCCURS AS PART
 * OF THE DESTRUCTION OF THE Unit OBJECT.</B>
 */

extern const char* toString(Unit* unit);
#ifdef __cplusplus
}
#endif // __cplusplus
#endif // CNI_UNIT_H
