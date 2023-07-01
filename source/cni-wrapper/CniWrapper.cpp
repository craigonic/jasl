/**
 * \file CniWrapper.cpp
 *
 * This file defines the class infrastructure used to create and manage the
 * connection and interaction with a Java Virtual Machine.
 *
 * Written By: Craig R. Campbell  -  April 2007
 */

#include "CniWrapper.h"

// Initialize the static pointer to this Singleton object. This item is set in
// the instance() method.

CniWrapper* CniWrapper::_instance = 0;

// Constructor.

CniWrapper::CniWrapper()
{
	JvCreateJavaVM(0);
	JvAttachCurrentThread(0,0);
}

// Destructor.

CniWrapper::~CniWrapper()
{
	JvDetachCurrentThread();

	_instance = 0;
}

// instance: Create (if it does not already exist) the one and only instance of
//           an object of this type and return its address.

CniWrapper* CniWrapper::instance()
{
	if (_instance == 0) _instance = new CniWrapper();

	return _instance;
}

// stringToConstChar: Return const char* string representation of a Java String.
//
// This method is (intended to be) accessed using the js2cc() function. jstring
// is equivalent to ::java::lang::String*

const char* CniWrapper::stringToConstChar(jstring javaString) const
{
	const char* returnString = 0;

	if (javaString)
	{
		int javaStringLength = JvGetStringUTFLength(javaString);

		char* newCharString = new char[javaStringLength + 1];

		if (newCharString)
		{
			JvGetStringUTFRegion(javaString,0,javaStringLength,
			                     newCharString);

			newCharString[javaStringLength] = '\0';

			returnString = newCharString;
		}
	}

	return returnString;
}

// constCharToString: Return Java String representation of a const char* string.
//
// This method is (intended to be) accessed using the cc2js() function. jstring
// is equivalent to ::java::lang::String*

const jstring CniWrapper::constCharToString(const char* constCharString) const
{
	return (constCharString != 0) ? JvNewStringUTF(constCharString) : 0;
}

/******************************************************************************/

// startCniWrapper: Create the one and only instance of a CniWrapper object.

const CniWrapper* startCniWrapper()
{
	return CniWrapper::instance();
}

// stopCniWrapper: Destroy the one and only instance of a CniWrapper object.

void stopCniWrapper()
{
	delete CniWrapper::instance();
}

// js2cc: Return the const char* string representation of the specified Java
//        String object.

const char* js2cc(jstring javaString)
{
	return CniWrapper::instance()->stringToConstChar(javaString);
}

// cc2js: Return a Java String object containing the specified const char*
//        string.

const jstring cc2js(const char* constCharString)
{
	return CniWrapper::instance()->constCharToString(constCharString);
}

// delcc: Release the memory allocated for the specified string using delete.

void delcc(const char* constCharString)
{
	delete [] constCharString;
}
