// ************************************************************************** //
// Utilities.swig - This file is an interface description for <A HREF="http://www.swig.org/">SWIG</A>. It is     //
//                  used to generate shared libraries that link to the        //
//                  library created for the <A HREF="../jasl/utilities/utilities.html">jasl.utilities</A> package. These     //
//                  libraries, along with the other files that are generated, //
//                  allow the public classes of the package to be accessed    //
//                  from the supported scripting languages (<A HREF="http://www.perl.org/">Perl</A> and <A HREF="http://www.python.org/">Python</A>). //
//                                                                            //
// Written By: Craig R. Campbell  -  August 2009                              //
// ************************************************************************** //

%module Utilities;

// <A HREF="../cni-wrapper/jaslWrapper.h.html">jaslWrapper.h</A> contains the #include declarations for the header files
// associated with the interface description files listed below. It also
// contains typedef declarations to simplify the use of each class.

%{
#include "jasl/cni/jaslWrapper.h"
%}

// Load the interface files associated with the public classes of the
// <B>jasl.utilities</B> package.

%include Dice.swig          // <A HREF="Dice.swig.html">Dice</A>
%include Serialization.swig // <A HREF="Serialization.swig.html">Serialization</A>
