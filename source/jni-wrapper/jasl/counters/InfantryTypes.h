/**
 * \file InfantryTypes.h
 *
 * This file declares a proxy intended to mirror the values found in the
 * <A HREF="../../../jasl/counters/UnitType.html">InfantryTypes</A> enum, which is implemented in Java, for use in C/C++ programs.
 *
 * Written By: Craig R. Campbell  -  April 2015
 */

#ifndef CNI_INFANTRY_TYPES_H
#define CNI_INFANTRY_TYPES_H

/**
 * \brief <A HREF="../../../jasl/counters/UnitType.html">InfantryTypes</A> enum CNI (Compiled Native Interface) proxy.
 *
 * This enum provides a subset of the functionality of its namesake, which is
 * implemented in Java and compiled into a library with GCJ.
 *
 * @version 0.1
 * @author Copyright (C) 2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/cni-wrapper/jasl/counters/InfantryTypes.h.html">Source code</A>
 */

typedef enum
{
	NONE,
	PARATROOPS,
	AIRBORNE,
	ANZAC,
	CANADIAN,
	FREE_FRENCH,
	FREE_POLISH,
	GUARDSMEN,
	GURKHA,
	SISSI,
	ENGINEERS,
	COMMISSAR,
	GUARDS
} InfantryTypes;

#endif // CNI_INFANTRY_TYPES_H
