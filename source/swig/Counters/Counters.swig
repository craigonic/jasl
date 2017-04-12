// ************************************************************************** //
// Counters.swig - This file is an interface description for <A HREF="http://www.swig.org/">SWIG</A>. It is used //
//                 to generate shared libraries that link to the library      //
//                 created for the <A HREF="../../jasl/counters/counters.html">jasl.counters</A> package. These libraries,    //
//                 along with the other files that are generated, allow the   //
//                 public classes of the package to be accessed from the      //
//                 supported scripting languages (<A HREF="http://www.perl.org/">Perl</A> and <A HREF="http://www.python.org/">Python</A>).           //
//                                                                            //
// Written By: Craig R. Campbell  -  August 2008                              //
// ************************************************************************** //

%module Counters;

// <A HREF="../../cni-wrapper/jaslWrapper.h.html">jaslWrapper.h</A> contains the #include declarations for the header files
// associated with the interface description files listed below. It also
// contains typedef declarations to simplify the use of each class.
//
// The List header is necessary for the corresponding typemap defined below.

%{
#include "jasl/cni/jaslWrapper.h"

#include <java/util/List.h>
%}

// This file allows the text from a Java exception to be included in a message
// shown by a scripting language.

%include ../java-exception.swig

// This file adds inline functions used to cast jasl derived class object
// pointers to and from java::lang::Object*.

%include counters-obj-cast.swig

// Add support for catching Java exceptions thrown by the following method,
// which is found in the indicated classes.

%exception setPortageLevel JAVA_EXCEPTION // Leader, Squad

// Load the interface files associated with the public classes and enums of the
// <B>jasl.counters</B> package.

// Enums.

%include Classifications.swig // <A HREF="Classifications.swig.html">Classifications</A>
%include Descriptions.swig    // <A HREF="Descriptions.swig.html">Descriptions</A>
%include InfantryTypes.swig   // <A HREF="InfantryTypes.swig.html">InfantryTypes</A>
%include Nationalities.swig   // <A HREF="Nationalities.swig.html">Nationalities</A>
%include States.swig          // <A HREF="States.swig.html">States</A>

// Classes.

// The following typemaps are applied in the interface files listed below where
// a States enum is used.

%typemap(in) ::jasl::counters::Status$States*
{
    if (!SWIG_IsOK(SWIG_ConvertPtr($input,(void**)&$1,$descriptor(States*),0)))
    {
        SWIG_exception_fail(SWIG_TypeError,
                            "in method '$symname', expecting type $descriptor(States*)");
    }
}

%typemap(out) ::java::util::List*
{
#if defined(SWIGPERL)
    AV*  statusArray = newAV();

    for (int i = 0;i < $1->size();++i)
    {
        SV* svp = sv_newmortal();

        SWIG_MakePtr(svp,$1->get(i),SWIGTYPE_p_States,0);

        av_push(statusArray,svp);
    }

    $result = newRV_inc((SV*)statusArray);
    sv_2mortal($result);
    argvi++;
#elif defined(SWIGPYTHON)
    $result = PyList_New($1->size());

    for (int i = 0;i < $1->size();++i)
    {
        PyObject* pyObject = SWIG_NewPointerObj($1->get(i),SWIGTYPE_p_States,0);

        PyList_SetItem($result,i,pyObject);
    }
#endif
}

%include Leader.swig // <A HREF="Leader.swig.html">Leader</A>
%include Squad.swig  // <A HREF="Squad.swig.html">Squad</A>
%include Unit.swig   // <A HREF="Unit.swig.html">Unit</A>
