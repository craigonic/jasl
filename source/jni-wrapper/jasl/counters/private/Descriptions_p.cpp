/**
 * \file Descriptions_p.cpp
 *
 * This file defines the internal / private elements and functions associated
 * with the <A HREF="../Descriptions.h.html">Descriptions</A> enum.
 *
 * Written By: Craig R. Campbell  -  March 2024
 */

#include "Descriptions_p.h"

#include "jasl/jni/JniEnum.h"

using namespace jasl::counters;

static struct JniEnumInterface::JniEnumData descriptionsEnumData =
	{descriptionsEnumPath, \
	 nullptr,nullptr,nullptr,nullptr,nullptr,nullptr};

// The returned string for the following function is a copy of a Java String,
// converted to the indicated type using the <A HREF="../../../JniWrapper.h.html#_JS2SS_">js2ss</A>() function.

// toString: Return the label associated with the specifed enum value.

std::string jasl::counters::toString(Descriptions description) noexcept
{
	return JniEnumInterface::convertToString(descriptionsEnumData,
	                                         static_cast<int>(description));
}

// fromDescriptionsObject: Return the Descriptions element corresponding to the
//                         value of the specified Java Enum object (i.e., an
//                         instance of Description.Descriptions).

Descriptions jasl::counters::fromDescriptionsObject(const jobject enumObject) noexcept
{
	return static_cast<Descriptions>(
		JniEnumInterface::enumValueIndex(descriptionsEnumData,
		                                 enumObject));
}

// toObject: Return an instance of a (Java) Description.Descriptions object,
//           based on the specified value.

jobject jasl::counters::toObject(Descriptions description) noexcept
{
	return JniEnumInterface::convertToObject(descriptionsEnumData,
	                                         static_cast<int>(description));
}
