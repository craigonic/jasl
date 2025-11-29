/**
 * \file Nationalities_p.h
 *
 * This file declares the internal / private elements and functions associated
 * with the <A HREF="../Nationalities.h.html">Nationalities</A> enum.
 *
 * Written By: Craig R. Campbell  -  November 2025
 */

#pragma once

#include "counters/Nationalities.h"

#include <jni.h>

namespace jasl {
namespace counters {

static constexpr const char* const nationalitiesEnumPath = "jasl/counters/Nationality$Nationalities";

/**
 * \brief Return the Nationalities element corresponding to the value of the
 * specified Java Enum object.
 */

Nationalities fromNationalitiesObject(const jobject enumObject) noexcept;

/**
 * \brief Return an instance of a (Java) <A HREF="../../../../jasl/counters/Nationality.html">Nationality.Nationalities</A> object, based
 * on the specified value.
 */

jobject toObject(Nationalities nationality) noexcept;

} // namespace counters
} // namespace jasl
