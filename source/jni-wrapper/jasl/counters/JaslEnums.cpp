/**
 * \file JaslEnums.cpp
 *
 * This file defines a proxy intended to mirror some of the behavior of the
 * Enums in the <A HREF="../../../jasl/jasl.html">jasl</A> packages, which are implemented in Java, for use in C++
 * programs.
 *
 * Written By: Craig R. Campbell  -  January 2019
 */

#include "JaslEnums.h"

#include "jasl/jni/JniWrapper.h"

#include <assert.h>

// toClass: Return the Java Enum class reference associated with the specified
//          enum type.

jclass JaslEnumInterface::toClass(const std::string& enumPath) noexcept
{
	assert(!enumPath.empty());

	jclass enumClass = jniEnv().FindClass(enumPath.c_str());
	assert(nullptr != enumClass);

	return enumClass;
}

// convertToObject: Return an instance of the specified enum type, set to the
//                  indicated value.

jobject JaslEnumInterface::convertToObject(JaslEnumData& jaslEnumData,
                                           int enumValueIndex) noexcept
{
	if (nullptr == jaslEnumData.enumClass)
	{
		jaslEnumData.enumClass = toClass(jaslEnumData.enumPath);

		printf("enumPath: %s\n",jaslEnumData.enumPath.c_str());
//		printf("enumClass: %p\n",jaslEnumData.enumClass);
	}

	assert(nullptr != jaslEnumData.enumClass);
	assert(enumValueIndex >= 0);

	if (nullptr == jaslEnumData.valuesMethodID)
	{
		std::string valuesSignature("()[L");

		valuesSignature.append(jaslEnumData.enumPath);
		valuesSignature.append(";");

		printf("valuesSignature: %s\n",valuesSignature.c_str());

		jaslEnumData.valuesMethodID =
			methodID(jaslEnumData.enumClass,"values",
			         valuesSignature.c_str(),true);

//		printf("valuesMethodID: %p\n",jaslEnumData.valuesMethodID);
	}

	assert(nullptr != jaslEnumData.valuesMethodID);

	const jobjectArray javaObjectArray =
		static_cast<jobjectArray>(jniEnv().CallStaticObjectMethod(jaslEnumData.enumClass,
		                                                          jaslEnumData.valuesMethodID));
	assert(nullptr != javaObjectArray);

	if (nullptr == jaslEnumData.nameMethodID)
	{
		jaslEnumData.nameMethodID =
			methodID(jaslEnumData.enumClass,"name",toStringSignature);

//		printf("nameMethodID: %p\n",jaslEnumData.nameMethodID);
	}

	assert(nullptr != jaslEnumData.nameMethodID);

	jobject enumObject =
		jniEnv().GetObjectArrayElement(javaObjectArray,
		                               enumValueIndex);
	assert(nullptr != enumObject);

	const jstring javaString =
		static_cast<jstring>(jniEnv().CallObjectMethod(enumObject,
		                                               jaslEnumData.nameMethodID));
	assert(nullptr != javaString);

	if (nullptr == jaslEnumData.valueOfMethodID)
	{
		std::string valueOfSignature("(Ljava/lang/String;)L");

		valueOfSignature.append(jaslEnumData.enumPath);
		valueOfSignature.append(";");

		printf("valueOfSignature: %s\n",valueOfSignature.c_str());

		jaslEnumData.valueOfMethodID =
			methodID(jaslEnumData.enumClass,"valueOf",
			         valueOfSignature.c_str(),true);

//		printf("valueOfMethodID: %p\n",jaslEnumData.valueOfMethodID);
	}

	assert(nullptr != jaslEnumData.valueOfMethodID);

	jobject returnObject =
		jniEnv().CallStaticObjectMethod(jaslEnumData.enumClass,
		                                jaslEnumData.valueOfMethodID,
		                                javaString);
	jniEnv().DeleteLocalRef(javaString);

	return returnObject;
}

// convertToString: Return the label associated with the specifed enum value.

std::string JaslEnumInterface::convertToString(JaslEnumData& jaslEnumData,
                                               int enumValueIndex) noexcept
{
	if (nullptr == jaslEnumData.enumClass)
	{
		jaslEnumData.enumClass = toClass(jaslEnumData.enumPath);

		printf("enumPath: %s\n",jaslEnumData.enumPath.c_str());
//		printf("enumClass: %p\n",jaslEnumData.enumClass);
	}

	assert(nullptr != jaslEnumData.enumClass);
	assert(enumValueIndex >= 0);

	if (nullptr == jaslEnumData.toStringMethodID)
	{
		jaslEnumData.toStringMethodID =
			methodID(jaslEnumData.enumClass,"toString",
			         toStringSignature);

//		printf("toStringMethodID: %p\n",jaslEnumData.toStringMethodID);
	}

	assert(nullptr != jaslEnumData.toStringMethodID);

	jobject enumObject = convertToObject(jaslEnumData,enumValueIndex);
	assert(nullptr != enumObject);

	std::string returnString(toString(jaslEnumData.toStringMethodID,
	                                  enumObject));

	jniEnv().DeleteLocalRef(enumObject);

	return returnString;
}
