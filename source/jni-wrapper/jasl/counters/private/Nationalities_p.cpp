/**
 * \file Nationalities_p.cpp
 *
 * This file defines the internal / private elements and functions associated
 * with the <A HREF="../Nationalities.h.html">Nationalities</A> enum.
 *
 * Written By: Craig R. Campbell  -  November 2025
 */

#include "Nationalities_p.h"

#include "JniEnum.h"

using namespace jasl::counters;

static struct JniEnumInterface::JniEnumData nationalitiesEnumData =
	{nationalitiesEnumPath, \
	 nullptr,nullptr,nullptr,nullptr,nullptr,nullptr};

// The returned string for the following function is a copy of a Java String,
// converted to the indicated type using the <A HREF="../../../JniWrapper.h.html#_JS2SS_">js2ss</A>() function.

// toString: Return the label associated with the specifed enum value.

std::string jasl::counters::toString(Nationalities nationality) noexcept
{
	return JniEnumInterface::convertToString(nationalitiesEnumData,
	                                         static_cast<int>(nationality));
}

// fromNationalitiesObject: Return the Nationalities element corresponding to
//                          the value of the specified Java Enum object (i.e.,
//                          an instance of Nationality.Nationalities).

Nationalities jasl::counters::fromNationalitiesObject(const jobject enumObject) noexcept
{
	return static_cast<Nationalities>(
		JniEnumInterface::enumValueIndex(nationalitiesEnumData,
		                                 enumObject));
}

// toObject: Return an instance of a (Java) Nationality.Nationalities object,
//           based on the specified value.

jobject jasl::counters::toObject(Nationalities nationality) noexcept
{
	return JniEnumInterface::convertToObject(nationalitiesEnumData,
	                                         static_cast<int>(nationality));
}
