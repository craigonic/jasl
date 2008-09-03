// ************************************************************************** //
// <A HREF="../cniWrapper.h.html">CniWrapper</A>.swig - This file is an interface description for <A HREF="http://www.swig.org/">SWIG</A>. It is    //
//                   used to generate libraries that link to the <A HREF="../wrappers.html">cni-wrapper</A>  //
//                   library. These libraries, along with the other files     //
//                   that are generated, are used to create, manage, and      //
//                   close a JVM (<A HREF="http://java.sun.com/">Java</A> Virtual Machine). The JVM is required  //
//                   to use the objects referenced by the other libraries     //
//                   generated in this directory from the supported scripting //
//                   languages (<A HREF="http://www.perl.org/">Perl</A> and <A HREF="http://www.python.org/">Python</A>).                             //
//                                                                            //
// Written By: Craig R. Campbell  -  August 2008                              //
//                                                                            //
// $Id: CniWrapper.swig,v 1.1 2008/09/03 20:31:38 campbell Exp $
// ************************************************************************** //

%module CniWrapper;

// A necessary declaration for building each library.

%{
#include <gcj/cni.h>
%}

// The public interface of the <A HREF="../cniWrapper.h.html">CNI_WRAPPER</A> class.

class CNI_WRAPPER
{
    public:

        CNI_WRAPPER();
        ~CNI_WRAPPER();
        const char* stringToConstChar(jstring javaString);
        jstring constCharToString(const char* constCharString);
};
