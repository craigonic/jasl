// ************************************************************************** //
// UiData.swig - This file is an interface description for <A HREF="http://www.swig.org/">SWIG</A>. It is used   //
//               to generate shared libraries that link to the library        //
//               created for the <A HREF="../../jasl/ui/data/data.html">jasl.ui.data</A> package. These libraries, along //
//               with the other files that are generated, allow the public    //
//               classes of the package to be accessed from the supported     //
//               scripting languages (<A HREF="http://www.perl.org/">Perl</A> and <A HREF="http://www.python.org/">Python</A>).                       //
//                                                                            //
// Written By: Craig R. Campbell  -  October 2014                             //
// ************************************************************************** //

%module UiData;

// <A HREF="../../cni-wrapper/jaslWrapper.h.html">jaslWrapper.h</A> contains the #include declarations for the header files
// associated with the interface description files listed below. It also
// contains typedef declarations to simplify the use of each class.

%{
#include "jasl/cni/jaslWrapper.h"
%}

// This file allows the text from a Java exception to be included in a message
// shown by a scripting language.

%include ../java-exception.swig

// Load the interface files associated with the public classes and enums of the
// <B>jasl.ui.data</B> package.

// Enums.

%include Sides.swig  // <A HREF="Sides.swig.html">Sides</A>

// Classes.

%include Game.swig     // <A HREF="Game.swig.html">Game</A>
%include Player.swig   // <A HREF="Player.swig.html">Player</A>
%include Scenario.swig // <A HREF="Scenario.swig.html">Scenario</A>
