/**
 * \file Classifications_p.cpp
 *
 * This file defines the internal / private elements and functions associated
 * with the <A HREF="../Classifications.h.html">Classifications</A> enum.
 *
 * Written By: Craig R. Campbell  -  December 2025
 */

#include "Classifications_p.h"

#include "JniEnum.h"

using namespace jasl::counters;

static struct JniEnumInterface::JniEnumData classificationsEnumData =
	{classificationsEnumPath, \
	 nullptr,nullptr,nullptr,nullptr,nullptr,nullptr};

// The returned string for the following function is a copy of a Java String,
// converted to the indicated type using the <A HREF="../../../JniWrapper.h.html#_JS2SS_">js2ss</A>() function.

// toString: Return the label associated with the specifed enum value.

std::string jasl::counters::toString(Classifications classification) noexcept
{
	return JniEnumInterface::convertToString(classificationsEnumData,
	                                         static_cast<int>(classification));
}

// fromClassificationsObject: Return the Classifications element corresponding
//                            to the value of the specified Java Enum object
//                            (i.e., an instance of
//                            Classification.Classifications).

Classifications jasl::counters::fromClassificationsObject(const jobject enumObject) noexcept
{
	return static_cast<Classifications>(
		JniEnumInterface::enumValueIndex(classificationsEnumData,
		                                 enumObject));
}

// toObject: Return an instance of a (Java) Classification.Classifications
//           object, based on the specified value.

jobject jasl::counters::toObject(Classifications classification) noexcept
{
	return JniEnumInterface::convertToObject(classificationsEnumData,
	                                         static_cast<int>(classification));
}
