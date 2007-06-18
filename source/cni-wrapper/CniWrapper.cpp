/**
 * \file cniWrapper.cpp
 *
 * This file defines the class infrastructure used to create and manage the
 * connection and interaction with a <A HREF="http://java.sun.com/">Java</A> Virtual Machine.
 *
 * Written By  : Craig R. Campbell  -  April 2007
 *
 * $Id: CniWrapper.cpp,v 1.1 2007/06/18 22:25:10 campbell Exp $
 */

#include "cniWrapper.h"

// Constructor.

CNI_WRAPPER::CNI_WRAPPER(void)
{
    JvCreateJavaVM(NULL);
    JvAttachCurrentThread(NULL,NULL);
}

// Destructor.

CNI_WRAPPER::~CNI_WRAPPER(void)
{
    JvDetachCurrentThread();
}

// Return const char* string representation of a Java String.
//
// This method returns a pointer to a copy of the text data in the return value
// format. IT IS THE RESPONSIBILITY OF THE CALLER TO FREE THE MEMORY ASSOCIATED
// WITH THE RETURNED STRING.

const char* CNI_WRAPPER::stringToConstChar(::java::lang::String* javaString)
{
    const char* returnString = NULL;

    if (javaString)
    {
        int javaStringLength = JvGetStringUTFLength(javaString);

        char* newCharString = new char[javaStringLength + 1];

        if (newCharString)
        {
            JvGetStringUTFRegion(javaString,0,javaStringLength,newCharString);

            newCharString[javaStringLength] = '\0';

            returnString = newCharString;
        }
    }

    return returnString;
}

// Return Java String representation of a const char* string.
//
// This method returns a pointer to a Java String object, which manages a copy
// of the specified text. The returned object should NOT be explicitly deleted,
// as this task will be performed by the garbage collector of the JVM.

::java::lang::String* CNI_WRAPPER::constCharToString(const char* constCharString)
{
    return (constCharString != NULL) ? JvNewStringUTF(constCharString) : NULL;
}

/**
 * \brief Global object used to create and manage the connection and interaction
 * with a <A HREF="http://java.sun.com/">Java</A> Virtual Machine.
 *
 * This object must be created in order to execute <A HREF="http://java.sun.com/">Java</A> code compiled with <A HREF="http://gcc.gnu.org/java/">GCJ</A>
 * through the Compiled Native Interface (<A HREF="http://gcc.gnu.org/onlinedocs/gcj/About-CNI.html#About-CNI">CNI</A>).
 */

CNI_WRAPPER* cniWrapper = NULL;
