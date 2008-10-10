// ************************************************************************** //
// CniWrapper.swig - This file is an interface description for <A HREF="http://www.swig.org/">SWIG</A>. It is    //
//                   used to generate libraries that link to the <A HREF="../cni-wrapper/cni-wrapper.html">cni-wrapper</A>  //
//                   library. These libraries, along with the other files     //
//                   that are generated, are used to create, manage, and      //
//                   close a JVM (<A HREF="http://java.sun.com/">Java</A> Virtual Machine). The JVM is required  //
//                   to use the objects referenced by the other libraries     //
//                   generated in this directory from the supported scripting //
//                   languages (<A HREF="http://www.perl.org/">Perl</A> and <A HREF="http://www.python.org/">Python</A>).                             //
//                                                                            //
// Written By: Craig R. Campbell  -  August 2008                              //
//                                                                            //
// $Id: CniWrapper.swig,v 1.4 2008/10/10 23:08:45 campbell Exp $
// ************************************************************************** //

%module CniWrapper;

// A necessary declaration for building each library.

%{
#include <gcj/cni.h>
%}

// The stringToConstChar() method allocates memory to store the data copied from
// the <A HREF="http://java.sun.com/javase/6/docs/api/java/lang/String.html">String</A> object. This typemap causes that memory to be freed in the wrapper
// function after it creates the native data item to store the string.

%newobject CNI_WRAPPER::stringToConstChar(::java::lang::String* javaString);

// The public interface of the <A HREF="../cni-wrapper/cniWrapper.h.html">CNI_WRAPPER</A> class.

class CNI_WRAPPER
{
    public:

        CNI_WRAPPER();
        ~CNI_WRAPPER();
        const char* stringToConstChar(::java::lang::String* javaString);
        ::java::lang::String* constCharToString(const char* constCharString);
};
