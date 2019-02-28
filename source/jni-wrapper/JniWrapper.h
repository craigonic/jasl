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
 * \brief <A HREF="https://docs.oracle.com/javase/8/docs/technotes/guides/jni/">JNI</A> (Java Native Interface) wrapper.
 *
 * This <A HREF="http://en.wikipedia.org/wiki/Singleton_pattern">Singleton</A> class is used to create, manage, and close a <A HREF="https://en.wikipedia.org/wiki/Java_virtual_machine">JVM</A> (Java Virtual
 * Machine). This allows a C++ program to access native <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A> code via the JNI.
 *
 * The friend functions are provided to convert a <A HREF="http://en.cppreference.com/w/cpp/string/basic_string">std::string</A> to a Java <A HREF="http://docs.oracle.com/javase/10/docs/api/java/lang/String.html">String</A>
 * and vice-versa, implement common calls for methods that return a Java String
 * and that set up interactions with the Java code, and to access the JNI <A HREF="https://docs.oracle.com/javase/8/docs/technotes/guides/jni/spec/functions.html#interface_function_table">API</A>.
 *
 * @version 3.0
 * @author Copyright (C) 2018 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../source/jni-wrapper/JniWrapper.h.html">Source code</A>
 */

class JniWrapper final
{
	public:

		// See the documentation of these functions for more details on
		// their purpose and operation. Calling any one of them will
		// start the JVM.

		friend std::string js2ss(jstring javaString);
		friend jstring ss2js(const std::string& stdString);
		friend std::string toString(const jmethodID javaMethodId,
		                            const jobject javaObject);
		friend jmethodID methodID(const jclass javaClass,
		                          const char* methodName,
		                          const char* methodSignature,
		                          bool isStaticMethod);
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

		// These methods are accessed using the js2ss(), ss2js(),
		// toString(), methodID(), and jniEnv() functions, respectively.

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

		jstring stdStringToString(const std::string& stdString) const noexcept;

		/** <A NAME="_RETURNSTRINGRESULT_"></A>
		 * Return the result of a call to the indicated method in the
		 * specified object, which is expected to return a Java String.
		 *
		 * See <A HREF="#_TOSTRING_">toString</A>() for more details.
		 */

		std::string returnStringResult(const jmethodID javaMethodId,
		                               const jobject javaObject) const noexcept;

		/** <A NAME="_CREATEMETHODID_"></A>
		 * Return a reference to the method with the specified
		 * attributes in the indicated class.
		 *
		 * See <A HREF="#_METHODID_">methodID</A>() for more details.
		 */

		jmethodID createMethodID(const jclass javaClass,
		                         const char* methodName,
		                         const char* methodSignature,
		                         bool isStaticMethod) const noexcept;

		/** <A NAME="_JNIENV_"></A>
		 * Return a reference to the Java Native Interface API.
		 *
		 * This provides access to the methods for finding and
		 * interacting with the underlying Java code.
		 */

		JNIEnv& jniNativeInterface() const noexcept;
};

/** <A NAME="_JS2SS_"></A>
 * \brief Return the std::string representation of the specified <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A> <A HREF="http://docs.oracle.com/javase/10/docs/api/java/lang/String.html">String</A> object.
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
 * \brief Return a Java String object containing the specified <A HREF="http://en.cppreference.com/w/cpp/string/basic_string">std::string</A>.
 *
 * This function calls the <A HREF="#_STDSTRINGTOSTRING_">stdStringtoString</A>() method of the JniWrapper,
 * returning a pointer to a Java String object, which manages a copy of the
 * specified text. The returned object should <B>NOT</B> be explicitly deleted, as this
 * task will be performed by the garbage collector of the JVM. If the JniWrapper
 * object does not yet exist, it is created. An empty argument will result in a
 * matching return value.
 */

inline jstring ss2js(const std::string& stdString)
{
	return JniWrapper::instance().stdStringToString(stdString);
}

/** <A NAME="_TOSTRING_"></A>
 * \brief Return the result of a call to the indicated method in the specified object,
 * which is expected to return a Java String.
 *
 * This function calls the <A HREF="#_RETURNSTRINGRESULT_">returnStringResult</A>() method of the JniWrapper, which
 * performs the operation described above and converts the Java String into the
 * return type. If the JniWrapper object does not yet exist, it is created. NULL
 * arguments or any failure in the call will stop the program (via assertion).
 */

inline std::string toString(const jmethodID javaMethodId,
                            const jobject javaObject)
{
	return JniWrapper::instance().returnStringResult(javaMethodId,javaObject);
}

/** <A NAME="_METHODID_"></A>
 * \brief Return a reference to the method with the specified attributes in the
 * indicated class.
 *
 * This function calls the <A HREF="#_CREATEMETHODID_">createMethodID</A>() method of the JniWrapper, which
 * performs the operation described above. It is intended for use in the most
 * general cases of generating an item of this type and to support the caching
 * of them (by the caller). If the JniWrapper object does not yet exist, it is
 * created. The return value is NULL if any of the arguments are or a failure
 * occurs in generating the method ID item.
 *
 * Note a that the use of const char* instead of std::string for the name and
 * signature parameter types is a conscious choice, as it is expected that the
 * arguments will most likely be specified as string literals in the caller.
 */

inline jmethodID methodID(const jclass javaClass,
                          const char* methodName,const char* methodSignature,
                          bool isStaticMethod = false)
{
	return JniWrapper::instance().createMethodID(javaClass,methodName,
	                                             methodSignature,
	                                             isStaticMethod);
}

/**
 * \brief Return a reference to the Java Native Interface <A HREF="https://docs.oracle.com/javase/8/docs/technotes/guides/jni/spec/functions.html#interface_function_table">API</A>.
 *
 * This function calls the <A HREF="#_JNIENV_">jniNativeInterface</A>() method of the JniWrapper,
 */

inline JNIEnv& jniEnv()
{
	return JniWrapper::instance().jniNativeInterface();
}

/**
 * \brief Call signature for Java methods that have no parameters and return a
 * Java String.
 *
 * This item is associated with the ubiquitous (within Java) toString() method
 * and is intended for use as an argument to methodID().
 */

static constexpr const char* const toStringSignature = "()Ljava/lang/String;";
