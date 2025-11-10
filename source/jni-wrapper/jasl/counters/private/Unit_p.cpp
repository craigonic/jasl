/**
 * \file Unit_p.cpp
 *
 * This file defines a "wrapper" class intended to simplify access for C/C++
 * programs to the <A HREF="../../../../jasl/counters/Unit.html">Unit</A> class, which is implemented in <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A>.
 *
 * Written By: Craig R. Campbell  -  January 2018
 */

#include "counters/Unit.h"

#include "Descriptions_p.h"
#include "JniWrapper.h"

#include <assert.h>

using namespace jasl::counters;

// Initialize static member variables.

//jmethodID Unit::_constructorID              = nullptr;
jmethodID Unit::_toTextMethodID             = nullptr;
jmethodID Unit::_toStringMethodID           = nullptr;
jmethodID Unit::_toJSONMethodID             = nullptr;
jmethodID Unit::_descriptionMethodID        = nullptr;
jmethodID Unit::_fromJSONMethodID           = nullptr;

// toText: Return a text representation of the attributes and current state of
//         this Unit.

std::string Unit::toText() const noexcept
{
	if (nullptr == unitObject) return std::string();

	if (nullptr == _toTextMethodID)
	{
		_toTextMethodID =
			methodID(unitClass,"toText",toStringSignature);
	}

	assert(nullptr != _toTextMethodID);

	using ::toString; // From JniWrapper.

	return toString(_toTextMethodID,unitObject);
}

// toString: Return an abbreviated description, which may include attributes, of
//           this Unit.

std::string Unit::toString() const noexcept
{
	if (nullptr == unitObject) return std::string();

	if (nullptr == _toStringMethodID)
	{
		_toStringMethodID =
			methodID(unitClass,"toString",toStringSignature);
	}

	assert(nullptr != _toStringMethodID);

	using ::toString; // From JniWrapper.

	return toString(_toStringMethodID,unitObject);
}

// toJSON: Return a JSON representation of the attributes and current state of
//         this Unit.

std::string Unit::toJSON() const noexcept
{
	if (nullptr == unitObject) return std::string();

	if (nullptr == _toJSONMethodID)
	{
		_toJSONMethodID =
			methodID(unitClass,"toJSON",toStringSignature);
	}

	assert(nullptr != _toJSONMethodID);

	using ::toString; // From JniWrapper.

	return toString(_toJSONMethodID,unitObject);
}

// description: Return the description of this Unit.

Descriptions Unit::description() const noexcept
{
	assert (nullptr != unitObject);

	if (nullptr == _descriptionMethodID)
	{
		std::string descriptionSignature =
			std::string("()[L") + descriptionsEnumPath + ";";

		_descriptionMethodID =
			methodID(unitClass,"description",
			         descriptionSignature.c_str());
	}

	assert(nullptr != _descriptionMethodID);

	const jobject enumObject =
		static_cast<jobject>(jniEnv().CallObjectMethod(unitClass,
		                                              _descriptionMethodID));
	assert(nullptr != enumObject);

	return fromDescriptionsObject(enumObject);
}

// fromJSON: Update an instance of this class to reflect the settings within the
//           specified JSON data.

void Unit::fromJSON(const std::string& jsonData)
{
	if (nullptr == unitObject) return;

	if (nullptr == _fromJSONMethodID)
	{
		_fromJSONMethodID =
			jniEnv().GetMethodID(unitClass,"fromJSON",
			                     "(Ljava/lang/String;)V");
	}

	assert(nullptr != _fromJSONMethodID);

	// \todo Handle exception.

	jniEnv().CallObjectMethod(unitObject,_fromJSONMethodID,ss2js(jsonData));
}

/// @cond TEST
// release: Release the reference to the instance of the "wrapped" class.

void Unit::release() const noexcept
{
	if (unitObject) jniEnv().DeleteLocalRef(unitObject);
}
/// @endcond
