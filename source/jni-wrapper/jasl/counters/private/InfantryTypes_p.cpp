/**
 * \file InfantryTypes_p.cpp
 *
 * This file defines the internal / private elements and functions associated
 * with the <A HREF="../InfantryTypes.h.html">InfantryTypes</A> enum.
 *
 * Written By: Craig R. Campbell  -  December 2025
 */

#include "InfantryTypes_p.h"

#include "JniEnum.h"

using namespace jasl::counters;

static struct JniEnumInterface::JniEnumData infantryTypesEnumData =
	{infantryTypesEnumPath, \
	 nullptr,nullptr,nullptr,nullptr,nullptr,nullptr};

// The returned string for the following function is a copy of a Java String,
// converted to the indicated type using the <A HREF="../../../JniWrapper.h.html#_JS2SS_">js2ss</A>() function.

// toString: Return the label associated with the specifed enum value.

std::string jasl::counters::toString(InfantryTypes infantryType) noexcept
{
	return JniEnumInterface::convertToString(infantryTypesEnumData,
	                                         static_cast<int>(infantryType));
}

// fromInfantryTypesObject: Return the InfantryTypes element corresponding to
//                          the value of the specified Java Enum object (i.e.,
//                          an instance of UnitType.InfantryTypes).

InfantryTypes jasl::counters::fromInfantryTypesObject(const jobject enumObject) noexcept
{
	return static_cast<InfantryTypes>(
		JniEnumInterface::enumValueIndex(infantryTypesEnumData,
		                                 enumObject));
}

// toObject: Return an instance of a (Java) UnitType.InfantryTypes object, based
//           on the specified value.

jobject jasl::counters::toObject(InfantryTypes infantryType) noexcept
{
	return JniEnumInterface::convertToObject(infantryTypesEnumData,
	                                         static_cast<int>(infantryType));
}
