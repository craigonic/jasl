/**
 * \file Descriptions.h
 *
 * This file declares a proxy intended to mirror the values found in the
 * <A HREF="../../../jasl/counters/Description.html">Descriptions</A> enum, which is implemented in Java, for use in C/C++ programs.
 *
 * Written By: Craig R. Campbell  -  December 2010
 */

#ifndef CNI_DESCRIPTIONS_H
#define CNI_DESCRIPTIONS_H

/**
 * \brief <A HREF="../../../jasl/counters/Description.html">Descriptions</A> enum CNI (Compiled Native Interface) proxy.
 *
 * This enum provides a subset of the functionality of its namesake, which is
 * implemented in Java and compiled into a library with GCJ.
 *
 * @version 0.1
 * @author Copyright (C) 2010-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/cni-wrapper/jasl/counters/Descriptions.h.html">Source code</A>
 */

typedef enum
{
	CREW,
	HALF_SQUAD,
	LEADER,
	SQUAD
} Descriptions;

#endif // CNI_DESCRIPTIONS_H
