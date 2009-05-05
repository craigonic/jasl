/**
 * \file jaslWrapper.h
 *
 * This file contains a set of typedef declarations intended to simplify the use
 * of the public classes in the jasl libraries when accessed through the <A HREF="http://gcc.gnu.org/onlinedocs/gcj/About-CNI.html#About-CNI">CNI</A>
 * (Compiled Native Interface) wrapper.
 *
 * Written By  : Craig R. Campbell  -  June 2007
 *
 * $Id: jaslWrapper.h,v 1.2 2009/05/05 16:29:37 campbell Exp $
 */

#ifndef JASL_WRAPPER_H // {
#define JASL_WRAPPER_H

#include <jasl/counters/Unit.h>
#include <jasl/counters/Leader.h>
#include <jasl/counters/Squad.h>

#include <jasl/utilities/Dice.h>

/**
 * \typedef jasl::counters::Unit Unit
 * \brief Shorthand declaration for a <A HREF="../../source/jasl/counters/Unit.html">Unit</A> object.
 */

typedef jasl::counters::Unit Unit;

/**
 * \typedef jasl::counters::Leader Leader
 * \brief Shorthand declaration for a <A HREF="../../source/jasl/counters/Leader.html">Leader</A> object.
 */

typedef jasl::counters::Leader Leader;

/**
 * \typedef jasl::counters::Squad Squad
 * \brief Shorthand declaration for a <A HREF="../../source/jasl/counters/Squad.html">Squad</A> object.
 */

typedef jasl::counters::Squad Squad;

/**
 * \typedef jasl::utilities::Dice Dice
 * \brief Shorthand declaration for a <A HREF="../../source/jasl/utilities/Dice.html">Dice</A> object.
 */

typedef jasl::utilities::Dice Dice;

#endif // } JASL_WRAPPER_H
