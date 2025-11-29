/**
 * \file Nationalities.h
 *
 * This file declares a proxy intended to mirror the elements found in the
 * <A HREF="../../../jasl/counters/Nationality.html">Nationalities</A> Enum, which is implemented in Java, for use in C++ programs.
 *
 * Written By: Craig R. Campbell  -  November 2025
 */

#pragma once

#include <string>

namespace jasl {
namespace counters {

/**
 * \brief <A HREF="../../../jasl/counters/Nationality.html">Nationalities</A> enum JNI (Java Native Interface) proxy.
 *
 * This enum provides a subset of the functionality of its namesake, which is
 * implemented in Java.
 *
 * @version 0.1
 * @author Copyright (C) 2025 Craig R. Campbell (craigonic@gmail.com)
 */

enum class Nationalities : int
{
	AlliedMinor,
	American,
	AxisMinor,
	British,
	Finnish,
	French,
	German,
	Italian,
	Japanese,
	Partisan,
	Russian
};

/**
 * \brief Return the label associated with the specifed enum value.
 */

std::string toString(Nationalities nationality) noexcept;

} // namespace counters
} // namespace jasl
