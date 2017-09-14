// ************************************************************************** //
// CniWrapper.swig - This file is an interface description for <A HREF="http://www.swig.org/">SWIG</A>. It is    //
//                   used to generate libraries that link to the <A HREF="../../cni-wrapper/cni-wrapper.html">cni-wrapper</A>  //
//                   library. These libraries, along with the other files     //
//                   that are generated, are used to create, manage, and      //
//                   close a JVM (<A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A> Virtual Machine). The JVM is required  //
//                   to use the objects referenced by the other libraries     //
//                   generated in this directory from the supported scripting //
//                   languages (<A HREF="http://www.perl.org/">Perl</A> and <A HREF="http://www.python.org/">Python</A>).                             //
//                                                                            //
// Written By: Craig R. Campbell  -  August 2008                              //
// ************************************************************************** //

%module CniWrapper;

// A necessary declaration for building each library.

%{
#include <gcj/cni.h>
%}

// The stringToConstChar() method and the js2cc() function allocate memory to
// store the data copied from the <A HREF="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html">String</A> object. This typemap causes that memory
// to be freed in the wrapper function after it creates the native data item to
// store the string.

%newobject CniWrapper::stringToConstChar(::java::lang::String* javaString);
%newobject js2cc(::java::lang::String* javaString);

// The public interface of the <A HREF="../../cni-wrapper/CniWrapper.h.html">CniWrapper</A> class.

class CniWrapper
{
    public:

        ~CniWrapper();
        static CniWrapper* instance();
        const char* stringToConstChar(::java::lang::String* javaString) const;
        ::java::lang::String* constCharToString(const char* constCharString) const;

    private:

        CniWrapper();
};

extern const char* js2cc(::java::lang::String* javaString);
extern const ::java::lang::String* cc2js(const char* constCharString);
