/**
 * \file Nationalities.h
 *
 * This file declares a proxy intended to mirror the values found in the
 * <A HREF="../../../jasl/counters/Nationality.html">Nationalities</A> enum, which is implemented in Java, for use in C/C++ programs.
 *
 * Written By: Craig R. Campbell  -  April 2015
 */

#ifndef CNI_NATIONALITIES_H
#define CNI_NATIONALITIES_H

/**
 * \brief <A HREF="../../../jasl/counters/Nationality.html">Nationalities</A> enum CNI (Compiled Native Interface) proxy.
 *
 * This enum provides a subset of the functionality of its namesake, which is
 * implemented in Java and compiled into a library with GCJ.
 *
 * @version 0.1
 * @author Copyright (C) 2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/cni-wrapper/jasl/counters/Nationalities.h.html">Source code</A>
 */

typedef enum
{
	ALLIED_MINOR,
	AMERICAN,
	AXIS_MINOR,
	BRITISH,
	FINNISH,
	FRENCH,
	GERMAN,
	ITALIAN,
	JAPANESE,
	PARTISAN,
	RUSSIAN
} Nationalities;

#endif // CNI_NATIONALITIES_H
