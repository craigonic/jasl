/**
 * \file JniWrapper.h
 *
 * This file declares the class infrastructure used to create and manage the
 * connection and interaction with a <A HREF="https://en.wikipedia.org/wiki/Java_virtual_machine">JVM</A> (Java Virtual Machine).
 *
 * Written By: Craig R. Campbell  -  July 2018
 */

#pragma once

#include <jni.h>

#include <string>

/**
 * \brief JNI (Java Native Interface) wrapper.
 *
 * This <A HREF="http://en.wikipedia.org/wiki/Singleton_pattern">Singleton</A> class is used to create, manage, and close a <A HREF="https://en.wikipedia.org/wiki/Java_virtual_machine">JVM</A> (Java Virtual
 * Machine). This allows a C++ program to access native Java code via the JNI.
 *
 * The friend functions are provided to convert a std::string to a Java String
 * and vice-versa, as well as to access the JNI API.
.
 *
 * @version 1.0
 * @author Craig R. Campbell
 * @see <A HREF="../../source/jni-wrapper/JniWrapper.h.html">Source code</A>
 */

class JniWrapper final
{
	public:

		// See the documentation of these functions for more details on
		// their purpose and operation. Calling any one of them will
		// start the JVM.

		friend std::string js2ss(jstring javaString);
		friend const jstring ss2js(const std::string& stdString);
		friend JNIEnv& jniEnv();

	private:

		/**
		 * Just what the name says it is.
		 *
		 * This item is created and initialized in the constructor and
		 * "shut down" in the destructor.
		 */

		JavaVM* _javaVirtualMachine;

		/**
		 * Just what the name says it is.
		 *
		 * This item is created in the constructor and accessible
		 * through the jniNativeInterface() method.
		 */

		JNIEnv* _jniNativeInterface;

		/**
		 * The constructor is declared here to enforce the use of the
		 * instance() method (via the friend functions).
		 */

		JniWrapper();

		/**
		 * In addition to its normal function, the destructor shuts down
		 * the JVM.
		 */

		~JniWrapper();

		// Disable the generation of a default copy constructor and "="
		// operator.

		JniWrapper(const JniWrapper& jniWrapper) = delete;
		JniWrapper& operator=(const JniWrapper& jniWrapper) = delete;

		/**
		 * Create (if it does not already exist) the one and only
		 * instance of an object of this type and return a reference to
		 * it.
		 *
		 * The constructor will initiate the JVM. If for some reason it
		 * cannot be started, the program will <B>assert</B>.
		 */

		static const JniWrapper& instance();

		// These methods are accessed using the js2ss(), ss2js(), and
		// jniEnv() functions, respectively.

		/** <A NAME="_STRINGTOSTDSTRING_"></A>
		 * Return the std::string representation of the specified Java
		 * String object.
		 *
		 * See <A HREF="#_JS2SS_">js2ss</A>() for more details.
		 */

		std::string stringToStdString(jstring javaString) const noexcept;

		/** <A NAME="_STDSTRINGTOSTRING_"></A>
		 * Return a Java String object containing the specified
		 * std::string.
		 *
		 * See <A HREF="#_SS2JS_">ss2js</A>() for more details.
		 */

		const jstring stdStringToString(const std::string& stdString) const noexcept;

		/** <A NAME="_JNIENV_"></A>
		 * Return a reference to the Java Native Interface API.
		 *
		 * This provides access to the methods for finding and
		 * interacting with the underlying Java code.
		 */

		JNIEnv& jniNativeInterface() const noexcept;
};

/** <A NAME="_JS2SS_"></A>
 * \brief Return the std::string representation of the specified Java String
 * object.
 *
 * This function calls the <A HREF="#_STRINGTOSTDSTRING_">stringToStdString</A>() method of the JniWrapper,
 * returning a copy of the text managed by the argument object. If the
 * JniWrapper object does not yet exist, it is created. A NULL argument will
 * result in an empty return value.
 */

inline std::string js2ss(jstring javaString)
{
	return JniWrapper::instance().stringToStdString(javaString);
}

/** <A NAME="_SS2JS_"></A>
 * \brief Return a Java String object containing the specified <std::string.
 *
 * This function calls the <A HREF="#_STDSTRINGTOSTRING_">stdStringtoString</A>() method of the JniWrapper,
 * returning a pointer to a Java String object, which manages a copy of the
 * specified text. The returned object should <B>NOT</B> be explicitly deleted, as this
 * task will be performed by the garbage collector of the JVM. If the JniWrapper
 * object does not yet exist, it is created. An empty argument will result in a
 * matching return value.
 */

inline const jstring ss2js(const std::string& stdString)
{
	return JniWrapper::instance().stdStringToString(stdString);
}

/**
 * \brief Return a reference to the Java Native Interface API.
 *
 * This function calls the <A HREF="#_JNIENV_">jniNativeInterface</A>() method of the JniWrapper,
 */

inline JNIEnv& jniEnv()
{
	return JniWrapper::instance().jniNativeInterface();
}
