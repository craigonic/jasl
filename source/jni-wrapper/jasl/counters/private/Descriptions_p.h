/**
 * \file Descriptions_p.h
 *
 * This file declares the internal / private elements and functions associated
 * with the <A HREF="../Descriptions.h.html">Descriptions</A> enum.
 *
 * Written By: Craig R. Campbell  -  January 2025
 */

#pragma once

#include "../Descriptions.h"

#include <jni.h>

namespace jasl {
namespace counters {

static constexpr const char* const descriptionsEnumPath = "jasl/counters/Description$Descriptions";

/**
 * \brief Return the Descriptions element corresponding to the value of the
 * specified Java Enum object.
 */

Descriptions fromDescriptionsObject(const jobject enumObject) noexcept;

/**
 * \brief Return an instance of a (Java) <A HREF="../../../../jasl/counters/Description.html">Description.Descriptions</A> object, based
 * on the specified value.
 */

jobject toObject(Descriptions description) noexcept;

} // namespace counters
} // namespace jasl
