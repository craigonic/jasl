/**
 * \file JniWrapper.cpp
 *
 * This file defines the class infrastructure used to create and manage the
 * connection and interaction with a <A HREF="https://en.wikipedia.org/wiki/Java_virtual_machine">JVM</A> (Java Virtual Machine).
 *
 * Written By: Craig R. Campbell  -  July 2018
 */

#include "JniWrapper.h"

#include <assert.h>
#include <memory>

// Constructor.

JniWrapper::JniWrapper() :
	_javaVirtualMachine(nullptr),
	_jniNativeInterface(nullptr)
{
	// This (re-formatted) code was copied from <A HREF="https://www.codeproject.com/Articles/993067/Calling-Java-from-Cplusplus-with-JNI">here</A>.

	std::unique_ptr<JavaVMOption[]> jvmOptions =
		std::make_unique<JavaVMOption[]>(1);

	jvmOptions[0].optionString = const_cast<char*>("-Djava.class.path=.");

	JavaVMInitArgs jvmInitArgs;

	jvmInitArgs.version = JNI_VERSION_1_6;
	jvmInitArgs.nOptions = 1;
	jvmInitArgs.options = jvmOptions.get();
	jvmInitArgs.ignoreUnrecognized = false;

	const jint returnCode =
		JNI_CreateJavaVM(&_javaVirtualMachine,
		                 (void**)&_jniNativeInterface,&jvmInitArgs);

	assert(returnCode == JNI_OK);
}

// Destructor.

JniWrapper::~JniWrapper()
{
	_javaVirtualMachine->DestroyJavaVM();
}

// instance: Create (if it does not already exist) the one and only instance of
//           an object of this type and return its address.

const JniWrapper& JniWrapper::instance()
{
	static JniWrapper instance;
	return instance;
}

// stringToStdString: Return std::string string representation of a Java String.
//
// This method is (intended to be) accessed using the js2ss() function. jstring
// is equivalent to ::java::lang::String*

std::string JniWrapper::stringToStdString(jstring javaString) const noexcept
{
	std::string returnString;

	if (javaString)
	{
		const char* stringCharacters =
			_jniNativeInterface->GetStringUTFChars(javaString,nullptr);

		returnString = stringCharacters;

		_jniNativeInterface->ReleaseStringUTFChars(javaString,
		                                           stringCharacters);
	}

	return returnString;
}

// stdStringToString: Return Java String representation of a std::string.
//
// This method is (intended to be) accessed using the ss2js() function. jstring
// is equivalent to ::java::lang::String*

jstring JniWrapper::stdStringToString(const std::string& stdString) const noexcept
{
	return _jniNativeInterface->NewStringUTF(stdString.c_str());
}

// returnStringResult: Return the result of a call to the indicated method in
//                     the specified object, which is expected to return a Java
//                     String.
//
// This method is (intended to be) accessed using the toString() function. The
// parameter types are defined in the JNI.

std::string JniWrapper::returnStringResult(const jmethodID javaMethodId,
                                           const jobject javaObject) const noexcept
{
	assert(nullptr != javaMethodId);
	assert(nullptr != javaObject);

	const jstring javaString =
		static_cast<jstring>(jniEnv().CallObjectMethod(javaObject,
		                                               javaMethodId));
	assert(nullptr != javaString);

	std::string returnString(stringToStdString(javaString));

	jniEnv().DeleteLocalRef(javaString);

	return returnString;
}

// createMethodID: Return a reference to the method with the specified
//                 attributes in the indicated class.
//
// This method is (intended to be) accessed using the methodID() function. The
// jclass parameter type is defined in the JNI.

jmethodID JniWrapper::createMethodID(const jclass javaClass,
                                     const char* methodName,
                                     const char* methodSignature,
                                     bool isStaticMethod) const noexcept
{
	jmethodID methodID = nullptr;

	if (javaClass && methodName && methodSignature)
	{
		if (isStaticMethod)
		{
			methodID =
				_jniNativeInterface->GetStaticMethodID(javaClass,
				                                       methodName,
				                                       methodSignature);
		}

		else
		{
			methodID =
				_jniNativeInterface->GetMethodID(javaClass,
				                                 methodName,
				                                 methodSignature);
		}
	}

	return methodID;
}

// jniNativeInterface: Return a reference to the Java Native Interface API.
//
// This method is (intended to be) accessed using the jniEnv() function.

JNIEnv& JniWrapper::jniNativeInterface() const noexcept
{
	return *_jniNativeInterface;
}
