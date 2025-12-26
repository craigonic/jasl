/**
 * \file InfantryTypes_p.h
 *
 * This file declares the internal / private elements and functions associated
 * with the <A HREF="../InfantryTypes.h.html">InfantryTypes</A> enum.
 *
 * Written By: Craig R. Campbell  -  December 2025
 */

#pragma once

#include "counters/InfantryTypes.h"

#include <jni.h>

namespace jasl {
namespace counters {

static constexpr const char* const infantryTypesEnumPath = "jasl/counters/UnitType$InfantryTypes";

/**
 * \brief Return the InfantryTypes element corresponding to the value of the
 * specified Java Enum object.
 */

InfantryTypes fromInfantryTypesObject(const jobject enumObject) noexcept;

/**
 * \brief Return an instance of a (Java) <A HREF="../../../../jasl/counters/UnitType.html">UnitType.InfantryTypes</A> object, based
 * on the specified value.
 */

jobject toObject(InfantryTypes infantryType) noexcept;

} // namespace counters
} // namespace jasl
