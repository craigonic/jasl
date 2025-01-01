/**
 * \file Descriptions.cpp
 *
 * This file defines a proxy intended to mirror the elements found in the
 * <A HREF="../../../jasl/counters/Description.html">Descriptions</A> Enum, which is implemented in Java, for use in C++ programs.
 *
 * Written By: Craig R. Campbell  -  March 2024
 */

#include "Descriptions.h"

#include "jasl/jni/JniEnum.h"

using namespace jasl::counters;

static struct JniEnumInterface::JniEnumData descriptionsEnumData =
	{"jasl/counters/Description$Descriptions", \
	 nullptr,nullptr,nullptr,nullptr,nullptr,nullptr};

// The returned string for the following method is a copy of a Java String,
// converted to the indicated type using the <A HREF="../../JniWrapper.h.html#_JS2SS_">js2ss</A>() function.

// toString: Return the label associated with the specifed enum value.

std::string jasl::counters::toString(Descriptions description) noexcept
{
	return JniEnumInterface::convertToString(descriptionsEnumData,
	                                         static_cast<int>(description));
}

// fromObject: Return an instance of a (Java) Description.Descriptions object,
//             based on the specified value.

Descriptions jasl::counters::fromObject(const jobject enumObject) noexcept
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
