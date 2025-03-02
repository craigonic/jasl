/**
 * \file Descriptions.h
 *
 * This file declares a proxy intended to mirror the elements found in the
 * <A HREF="../../../jasl/counters/Description.html">Descriptions</A> Enum, which is implemented in Java, for use in C++ programs.
 *
 * Written By: Craig R. Campbell  -  March 2024
 */

#pragma once

#include <string>

namespace jasl {
namespace counters {

/**
 * \brief <A HREF="../../../jasl/counters/Description.html">Descriptions</A> enum JNI (Java Native Interface) proxy.
 *
 * This enum provides a subset of the functionality of its namesake, which is
 * implemented in Java.
 *
 * @version 0.3
 * @author Copyright (C) 2024-2025 Craig R. Campbell (craigonic@gmail.com)
 */

enum class Descriptions : int
{
	Crew,
	HalfSquad,
	Leader,
	Squad
};

/**
 * \brief Return the label associated with the specifed enum value.
 */

std::string toString(Descriptions description) noexcept;

} // namespace counters
} // namespace jasl
