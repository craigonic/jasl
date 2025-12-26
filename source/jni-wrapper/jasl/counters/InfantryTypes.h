/**
 * \file InfantryTypes.h
 *
 * This file declares a proxy intended to mirror the elements found in the
 * <A HREF="../../../jasl/counters/UnitType.html">InfantryTypes</A> Enum, which is implemented in Java, for use in C++ programs.
 *
 * Written By: Craig R. Campbell  -  December 2025
 */

#pragma once

#include <string>

namespace jasl {
namespace counters {

/**
 * \brief <A HREF="../../../jasl/counters/UnitType.html">InfantryTypes</A> enum JNI (Java Native Interface) proxy.
 *
 * This enum provides a subset of the functionality of its namesake, which is
 * implemented in Java.
 *
 * @version 0.1
 * @author Copyright (C) 2025 Craig R. Campbell (craigonic@gmail.com)
 */

enum class InfantryTypes : int
{
	None,
	Paratroops,
	Airborne,
	Anzac,
	Canadian,
	FreeFrench,
	FreePolish,
	Guardsmen,
	Gurkha,
	Sissi,
	Engineers,
	Commissar,
	Guards
};

/**
 * \brief Return the label associated with the specifed enum value.
 */

std::string toString(InfantryTypes infantryType) noexcept;

} // namespace counters
} // namespace jasl
