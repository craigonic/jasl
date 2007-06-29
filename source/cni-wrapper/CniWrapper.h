/**
 * \file cniWrapper.h
 *
 * This file declares the class infrastructure used to create and manage the
 * connection and interaction with a <A HREF="http://java.sun.com/">Java</A> Virtual Machine.
 *
 * Written By  : Craig R. Campbell  -  April 2007
 *
 * $Id: CniWrapper.h,v 1.2 2007/06/29 17:55:06 campbell Exp $
 */

#ifndef CNI_WRAPPER_H // {
#define CNI_WRAPPER_H

#include <gcj/cni.h>

/**
 * \brief <A HREF="http://gcc.gnu.org/onlinedocs/gcj/About-CNI.html#About-CNI">CNI</A> (Compiled Native Interface) wrapper.
 *
 * This class is used to create, manage, and close a JVM (<A HREF="http://java.sun.com/">Java</A> Virtual
 * Machine). This allows a C/C++ program to access <A HREF="http://java.sun.com/">Java</A> code compiled using <A HREF="http://gcc.gnu.org/java/">GCJ</A>.
 *
 * Methods are also provided to convert const char* strings to <A HREF="http://java.sun.com/">Java</A> <A HREF="http://java.sun.com/javase/6/docs/api/java/lang/String.html">String</A>
 * objects and vice-versa.
 *
 * @version 1.1
 * @author Craig R. Campbell
 * @see <A HREF="../../source/wrappers/cniWrapper.h.html">Source code</A>
 */

class CNI_WRAPPER
{
    public:

        CNI_WRAPPER(void);
        ~CNI_WRAPPER(void);

        /**
         * \brief Return const char* string representation of a <A HREF="http://java.sun.com/">Java</A> <A HREF="http://java.sun.com/javase/6/docs/api/java/lang/String.html">String</A>.
         *
         * This method returns a pointer to a copy of the text data in the
         * return value format. IT IS THE RESPONSIBILITY OF THE CALLER TO FREE
         * THE MEMORY ASSOCIATED WITH THE RETURNED STRING.
         *
         * Note that jstring is equivalent to ::java::lang::String*
         */

        const char* stringToConstChar(jstring javaString);

        /**
         * \brief Return <A HREF="http://java.sun.com/">Java</A> <A HREF="http://java.sun.com/javase/6/docs/api/java/lang/String.html">String</A> representation of a const char* string.
         *
         * This method returns a pointer to a <A HREF="http://java.sun.com/">Java</A> <A HREF="http://java.sun.com/javase/6/docs/api/java/lang/String.html">String</A> object, which manages
         * a copy of the specified text. The returned object should NOT be
         * explicitly deleted, as this task will be performed by the garbage
         * collector of the JVM.
         *
         * Note that jstring is equivalent to ::java::lang::String*
         */

        jstring constCharToString(const char* constCharString);
};

// Global object used to create and manage the connection and interaction with
// a Java Virtual Machine.
//
// This object must be created in order to execute Java code compiled with GCJ
// through the Compiled Native Interface (CNI).

extern CNI_WRAPPER* cniWrapper;

#endif // } CNI_WRAPPER_H
