// ************************************************************************** //
// Serialization.swig - This file is an interface description for <A HREF="http://www.swig.org/">SWIG</A>. It is //
//                      used to generate shared libraries that link to the    //
//                      library created for the <A HREF="../../jasl/utilities/utilities.html">package</A> containing this       //
//                      class. These libraries, along with the other files    //
//                      that are generated, allow the methods of this class   //
//                      to be accessed from the supported scripting languages //
//                      (<A HREF="http://www.perl.org/">Perl</A> and <A HREF="http://www.python.org/">Python</A>).                                    //
//                                                                            //
//                      NOTE: This file is intended for use in the <A HREF="Utilities.swig.html">Utilities</A>  //
//                            interface file, not to build standalone         //
//                            libraries.                                      //
//                                                                            //
// Written By: Craig R. Campbell  -  May 2014                                 //
// ************************************************************************** //

%module Serialization;

// The public interface of the <A HREF="../../jasl/utilities/Serialization.html">Serialization</A> class.

class Serialization
{
    public:

        static void                  serializeToFile(::java::lang::Object*,
                                                     ::java::lang::String*);
        static ::java::lang::Object* deserializeFromFile(::java::lang::String*);
};
