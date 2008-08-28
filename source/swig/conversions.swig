// ************************************************************************** //
// conversions.swig - This file is an interface description for <A HREF="http://www.swig.org/">SWIG</A>. It      //
//                    defines a typemap, which is inserted into the generated //
//                    code to do just what the filename implies with objects  //
//                    of a specific type.                                     //
//                                                                            //
// Written By: Craig R. Campbell  -  August 2008                              //
//                                                                            //
// $Id: conversions.swig,v 1.1 2008/08/28 23:34:11 campbell Exp $
// ************************************************************************** //

// Convert perl string to a Java String using JvNewStringLatin1 and vice-versa.
//
// This code was copied from the source code for <A HREF="http://search.cpan.org/~rusekd/GCJ-Cni/">GCJ::Cni</A>

#ifdef SWIGPERL
%typemap(in) jstring
{
    // Reference to an object.

    if (SvROK($input)) $1 = (jstring)$input;

    // A Perl string.

    if (SvPOK($input)) $1 = JvNewStringLatin1(SvPV($input,PL_na));
}
#endif
