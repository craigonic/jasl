/**
 * \file Classifications_p.h
 *
 * This file declares the internal / private elements and functions associated
 * with the <A HREF="../Classifications.h.html">Classifications</A> enum.
 *
 * Written By: Craig R. Campbell  -  December 2025
 */

#pragma once

#include "counters/Classifications.h"

#include <jni.h>

namespace jasl {
namespace counters {

static constexpr const char* const classificationsEnumPath = "jasl/counters/Classification$Classifications";

/**
 * \brief Return the Classifications element corresponding to the value of the
 * specified Java Enum object.
 */

Classifications fromClassificationsObject(const jobject enumObject) noexcept;

/**
 * \brief Return an instance of a (Java) <A HREF="../../../../jasl/counters/Classification.html">Classification.Classifications</A> object,
 * based on the specified value.
 */

jobject toObject(Classifications classification) noexcept;

} // namespace counters
} // namespace jasl
