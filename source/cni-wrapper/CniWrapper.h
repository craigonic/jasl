/**
 * \file CniWrapper.h
 *
 * This file declares the class infrastructure used to create and manage the
 * connection and interaction with a Java Virtual Machine.
 *
 * Written By: Craig R. Campbell  -  April 2007
 */

#ifndef CNI_WRAPPER_H
#define CNI_WRAPPER_H

#ifdef __cplusplus
#include <gcj/cni.h>

/**
 * \brief CNI (Compiled Native Interface) wrapper.
 *
 * This <A HREF="http://en.wikipedia.org/wiki/Singleton_pattern">Singleton</A> class is used to create, manage, and close a JVM (Java Virtual
 * Machine). This allows a C/C++ program to access Java code compiled using GCJ.
 *
 * Methods are also provided to convert const char* strings to Java String
 * objects and vice-versa.
 *
 * @version 2.0
 * @author Craig R. Campbell
 * @see <A HREF="../../source/cni-wrapper/CniWrapper.h.html">Source code</A>
 */

class CniWrapper
{
	public:

		/**
		 * \brief Destructor.
		 *
		 * In addition to its normal function, the destructor shuts down
		 * the JVM.
		 */

		~CniWrapper();

		/**
		 * \brief Create (if it does not already exist) the one and only
		 * instance of an object of this type and return its address.
		 *
		 * The constructor will initiate the JVM.
		 */

		static CniWrapper* instance();

		// These methods are (intended to be) accessed using the js2cc()
		// and cc2js() functions, respectively.

		/** <A NAME="_STRINGTOCONSTCHAR_"></A>
		 * \brief Return the const char* string representation of the
		 * specified Java String object.
		 *
		 * See <A HREF="#_JS2CC_">js2cc</A>() for more details.
		 */

		const char* stringToConstChar(jstring javaString) const;

		/** <A NAME="_CONSTCHARTOSTRING_"></A>
		 * \brief Return a Java String object containing the specified
		 * const char* string.
		 *
		 * See <A HREF="#_CC2JS_">cc2js</A>() for more details.
		 */

		const jstring constCharToString(const char* constCharString) const;

	private:

		/**
		 * The address of the one and only instance of an object of this
		 * type.
		 *
		 * This item is set in the instance() method and set to NULL
		 * when the object is destroyed.
		 */

		static CniWrapper* _instance;

		/**
		 * The constructor is declared here to enforce the use of the
		 * instance() method.
		 */

		CniWrapper();

		// Disable the generation of a default copy constructor and "="
		// operator.

		CniWrapper(const CniWrapper& cniWrapper);
		CniWrapper& operator=(const CniWrapper& cniWrapper);
};
/******************************************************************************/
#else
/**
 * \brief Declaration to allow a CniWrapper object to be created from a C
 * program.
 */

typedef struct CniWrapper CniWrapper;

/**
 * \brief Declaration to allow a Java String object to be referenced from a C
 * program.
 *
 * jstring is equivalent to ::java::lang::String*.
 */

typedef struct jstring* jstring;
#endif // __cplusplus
#ifdef __cplusplus
extern "C" {
#endif // __cplusplus
/**
 * \brief Create the one and only instance of a CniWrapper object.
 *
 * If an instance does not already exist, one is created. Note that if the
 * returned item is deleted (explicitly or by using stopCniWrapper()), the Java
 * Virtual Machine is destroyed and Java/CNI code will no longer function.
 */

extern const CniWrapper* startCniWrapper();

/**
 * \brief Destroy the one and only instance of a CniWrapper object.
 *
 * Note that when this function is called, the Java Virtual Machine managed by
 * the CniWrapper is destroyed as well. This will prevent any Java/CNI code from
 * executing.
 */

extern void stopCniWrapper();

/** <A NAME="_JS2CC_"></A>
 * \brief Return the const char* string representation of the specified Java
 * String object.
 *
 * This function calls the <A HREF="#_STRINGTOCONSTCHAR_">stringToConstChar</A>() method of the CniWrapper,
 * returning a pointer to a copy of the text managed by the parameter object. If
 * the CniWrapper object does not yet exist, it is created. A NULL parameter
 * will result in a matching return value.
 *
 * <B>NOTE: IT IS THE RESPONSIBILITY OF THE CALLER TO FREE THE MEMORY ASSOCIATED
 * WITH THE RETURNED STRING.</B> The delcc() function is provided for just this
 * purpose.
 */

extern const char* js2cc(jstring javaString);

/** <A NAME="_CC2JS_"></A>
 * \brief Return a Java String object containing the specified const char*
 * string.
 *
 * This function calls the <A HREF="#_CONSTCHARTOSTRING_">constCharToString</A>() method of the CniWrapper,
 * returning a pointer to a Java String object, which manages a copy of the
 * specified text. The returned object should <B>NOT</B> be explicitly deleted,
 * as this task will be performed by the garbage collector of the JVM. If the
 * CniWrapper object does not yet exist, it is created. A NULL parameter will
 * result in a matching return value.
 */

extern const jstring cc2js(const char* constCharString);

/**
 * \brief Release the memory allocated for the specified string using delete.
 *
 * This function is intended for use in C programs to destroy the item returned
 * by js2cc().
 */

extern void delcc(const char* constCharString);
#ifdef __cplusplus
}
#endif // __cplusplus
#endif // CNI_WRAPPER_H
