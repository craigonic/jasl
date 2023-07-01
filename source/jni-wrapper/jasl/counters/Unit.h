/**
 * \file Unit.h
 *
 * This file declares a "wrapper" class intended to simplify access for C/C++
 * programs to the <A HREF="../../../jasl/counters/Unit.html">Unit</A> class, which is implemented in Java.
 *
 * Written By: Craig R. Campbell  -  January 2010
 */

#ifndef CNI_UNIT_H
#define CNI_UNIT_H

#include "Descriptions.h"

#ifdef __cplusplus
namespace jasl
{
	namespace counters
	{
		class Unit;
	}
}

/**
 * \brief <A HREF="../../../jasl/counters/Unit.html">Unit</A> class CNI (Compiled Native Interface) wrapper.
 *
 * This class is used to simplify access to its namesake, which is implemented
 * in Java and compiled into a library with GCJ. It also interacts with the
 * <A HREF="../../CniWrapper.h.html">CniWrapper</A> and <A HREF="../../JaslErrorMessage.h.html">JaslErrorMessage</A> classes, which are used both to enable Java
 * support and keep it "behind the scenes".
 *
 * @version 0.3
 * @author Copyright (C) 2010-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/cni-wrapper/jasl/counters/Unit.h.html">Source code</A>
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

		/** <A NAME="_DESCRIPTION_TYPE_"></A>
		 * \brief Return the basic type of this Unit.
		 *
		 * The returned value is the <A HREF="Descriptions.h.html">constant</A> associated with the
		 * description() string.
		 */

		Descriptions descriptionType();

		/** <A NAME="_TO_TEXT_"></A>
		 * \brief Return a text representation of the attributes and
		 * current state of this Unit.
		 *
		 * The returned string includes a label and value for each data
		 * member defined for the concrete class type. <B>IT SHOULD NOT BE
		 * DELETED, AS THIS OCCURS AS PART OF THE DESTRUCTION OF THE
		 * OBJECT.</B>
		 */

		const char* toText();

		/** <A NAME="_TO_STRING_"></A>
		 * \brief Return an abbreviated description, which may include
		 * attributes, of this Unit.
		 *
		 * The returned string includes data specific to the concrete
		 * class type. <B>IT SHOULD NOT BE DELETED, AS THIS OCCURS AS PART
		 * OF THE DESTRUCTION OF THE OBJECT.</B>
		 */

		const char* toString();

		/** <A NAME="_TO_JSON_"></A>
		 * \brief Return a JSON representation of the attributes and
		 * current state of this Unit.
		 *
		 * The returned string includes a label (key) and value for each
		 * data member defined for the concrete class type. <B>IT SHOULD
		 * NOT BE DELETED, AS THIS OCCURS AS PART OF THE DESTRUCTION OF
		 * THE OBJECT.</B>
		 */

		const char* toJSON();

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
		 * This item references a copy of a Java String, converted to
		 * the indicated type using the <A HREF="../../CniWrapper.h.html#_JS2CC_">js2cc</A>() function. The copy is
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
		 * generated during the each call to the toText() method.
		 * Subsequent calls destroy the old string, create a new one,
		 * and return it. The memory for the results of the last call to
		 * toText() is freed in the destructor.
		 */

		const char* _dump;

		/**
		 * The abbreviated description, which may include attributes, of
		 * this Unit.
		 *
		 * This item references a copy of a Java String, converted to
		 * the indicated type using the js2cc() function. The copy is
		 * generated during the each call to the toString() method.
		 * Subsequent calls destroy the old string, create a new one,
		 * and return it. The memory for the results of the last call to
		 * toString() is freed in the destructor.
		 */

		const char* _label;

		/**
		 * The JSON representation of the attributes and current state
		 * for this Unit instance.
		 *
		 * This item references a copy of a Java String, converted to
		 * the indicated type using the js2cc() function. The copy is
		 * generated during the each call to the toJSON() method.
		 * Subsequent calls destroy the old string, create a new one,
		 * and return it. The memory for the results of the last call to
		 * toJSON() is freed in the destructor.
		 */

		const char* _json;

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
 * \brief Return the basic type of the specified Unit.
 *
 * This function calls the <A HREF="#_DESCRIPTION_TYPE_">descriptionType</A>() method of the indicated object. If
 * the Unit pointer argument is NULL, the first element of the Descriptions enum
 * will be returned.
 */

extern Descriptions descriptionType(Unit* unit);

/**
 * \brief Return a text representation of the attributes and current state of
 * the specified Unit.
 *
 * This function calls the <A HREF="#_TO_TEXT_">toText</A>() method of the indicated object.
 *
 * <B>NOTE: THE RETURNED STRING SHOULD NOT BE DELETED OR FREED. IT OCCURS AS PART
 * OF THE DESTRUCTION OF THE Unit OBJECT.</B>
 */

extern const char* toText(Unit* unit);

/**
 * \brief Return an abbreviated description, which may include attributes, of
 * the specified Unit.
 *
 * This function calls the <A HREF="#_TO_STRING_">toString</A>() method of the indicated object.
 *
 * <B>NOTE: THE RETURNED STRING SHOULD NOT BE DELETED OR FREED. IT OCCURS AS PART
 * OF THE DESTRUCTION OF THE Unit OBJECT.</B>
 */

extern const char* toString(Unit* unit);

/**
 * \brief Return a JSON representation of the attributes and current state of
 * the specified Unit.
 *
 * This function calls the <A HREF="#_TO_JSON_">toJSON</A>() method of the indicated object.
 *
 * <B>NOTE: THE RETURNED STRING SHOULD NOT BE DELETED OR FREED. IT OCCURS AS PART
 * OF THE DESTRUCTION OF THE Unit OBJECT.</B>
 */

extern const char* toJSON(Unit* unit);
#ifdef __cplusplus
}
#endif // __cplusplus
#endif // CNI_UNIT_H
