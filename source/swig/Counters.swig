// ************************************************************************** //
// Counters.swig - This file is an interface description for <A HREF="http://www.swig.org/">SWIG</A>. It is used //
//                 to generate shared libraries that link to the library      //
//                 created for the <A HREF="../jasl/counters/counters.html">jasl.counters</A> package. These libraries,    //
//                 along with the other files that are generated, allow the   //
//                 public classes of the package to be accessed from the      //
//                 supported scripting languages (<A HREF="http://www.perl.org/">Perl</A> and <A HREF="http://www.python.org/">Python</A>).           //
//                                                                            //
// Written By: Craig R. Campbell  -  August 2008                              //
//                                                                            //
// $Id: Counters.swig,v 1.1 2008/09/11 20:48:05 campbell Exp $
// ************************************************************************** //

%module Counters;

#ifdef SWIGPERL
// Disable the generation of default destructors for all of the classes
// associated with the files included below. This works for Perl, but not for
// Python, so DISOWN typemap is used instead. See one of the files below for
// more details.

// %nodefaultdtor;
#endif

// <A HREF="../cni-wrapper/jaslWrapper.h.html">jaslWrapper.h</A> contains the #include declarations for the header files
// associated with the interface description files listed below. It also
// contains typedef declarations to simplify the use of each class.

%{
#include "jaslWrapper.h"
%}

// Load the interface files associated with the public classes of the
// <B>jasl.counters</B> package.

%include Dice.swig // <A HREF="Dice.swig.html">Dice</A>
