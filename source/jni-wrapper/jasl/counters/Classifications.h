/**
 * \file Classifications.h
 *
 * This file declares a proxy intended to mirror the values found in the
 * <A HREF="../../../jasl/counters/Classification.html">Classifications</A> enum, which is implemented in Java, for use in C/C++
 * programs.
 *
 * Written By: Craig R. Campbell  -  April 2015
 */

#ifndef CNI_CLASSIFICATIONS_H
#define CNI_CLASSIFICATIONS_H

/**
 * \brief <A HREF="../../../jasl/counters/Classification.html">Classifications</A> enum CNI (Compiled Native Interface) proxy.
 *
 * This enum provides a subset of the functionality of its namesake, which is
 * implemented in Java and compiled into a library with GCJ.
 *
 * @version 0.2
 * @author Copyright (C) 2015-2017 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/cni-wrapper/jasl/counters/Classifications.h.html">Source code</A>
 */

typedef enum
{
	SS,
	ELITE,
	FIRST_LINE,
	SECOND_LINE,
	GREEN,
	CONSCRIPT,
	NONE
} Classifications;

#endif // CNI_CLASSIFICATIONS_H
