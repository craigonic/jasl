// ************************************************************************** //
// counters-obj-cast.swig - This file is an interface description for <A HREF="http://www.swig.org/">SWIG</A>.   //
//                          It is used to cast a pointer to a <A HREF="Counters.swig.html">Counters</A> <B>class</B>  //
//                          object to the <A HREF="http://java.sun.com/">Java</A> <A HREF="http://docs.oracle.com/javase/8/docs/api/java/lang/Object.html">Object</A> type (and vice-versa),  //
//                          primarily for <A HREF="../Utilities/Serialization.swig.html">serialization</A> through one of the    //
//                          supported scripting languages (<A HREF="http://www.perl.org/">Perl</A> and <A HREF="http://www.python.org/">Python</A>).  //
//                                                                            //
// Written By: Craig R. Campbell  -  May 2014                                 //
// ************************************************************************** //

// <A HREF="../../cni-wrapper/jaslWrapper.h.html">jaslWrapper.h</A> contains the #include declarations for the jasl class types
// used in the functions defined below. It also contains typedef declarations to
// simplify the use of each class.

%{
#include "jasl/cni/jaslWrapper.h"
#include <java/lang/Object.h>
%}

%inline
%{
::java::lang::Object* toObject(Leader* leader)
{
    return dynamic_cast<java::lang::Object*>(leader);
}

::java::lang::Object* toObject(Squad* squad)
{
    return dynamic_cast<java::lang::Object*>(squad);
}

Unit* fromObject(::java::lang::Object* object)
{
    return (Unit*)object;
}
%}
