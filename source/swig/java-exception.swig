// ************************************************************************** //
// java-exception.swig - This file is an interface description for <A HREF="http://www.swig.org/">SWIG</A>. It   //
//                       is used to allow Java exceptions to be caught and    //
//                       for the message associated with one to be retrieved  //
//                       for display through one of the supported scripting   //
//                       languages (<A HREF="http://www.perl.org/">Perl</A> and <A HREF="http://www.python.org/">Python</A>).                         //
//                                                                            //
// Written By: Craig R. Campbell  -  March 2014                               //
// ************************************************************************** //

// The STATIC declaration in cni.h conflicts with one used by SWIG.

#ifdef SWIGPERL
%{
#undef STATIC
%}
#endif

%{
#include <gcj/cni.h>
#include <java/lang/Throwable.h>
%}

%header
%{
static const char* js2cc(::java::lang::String* javaString)
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
%}

// The js2cc() function allocates memory to store the data copied from the
// <A HREF="http://docs.oracle.com/javase/8/docs/api/java/lang/String.html">String</A> object. This typemap causes that memory to be freed in the wrapper
// function after it creates the native data item to store the string.

%newobject js2cc(::java::lang::String* javaString);

%include exception.i

%define JAVA_EXCEPTION
{
    try
    {
        $action
    }

    catch (jthrowable exception)
    {
        SWIG_exception(SWIG_ValueError,js2cc(exception->toString()));
    }
}
%enddef
