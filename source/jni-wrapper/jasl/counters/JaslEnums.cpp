/**
 * \file JaslEnums.cpp
 *
 * This file defines a proxy intended to mirror some of the behavior of the
 * <A HREF="https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/lang/Enum.html">Enum</A>s in the <A HREF="../../../jasl/jasl.html">jasl</A> packages, which are implemented in <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A>, for use in C++
 * programs.
 *
 * Written By: Craig R. Campbell  -  January 2019
 */

#include "JaslEnums.h"

#include "jasl/jni/JniWrapper.h"

#include <assert.h>
#include <type_traits>

/// @cond DEVELOPER
// Initialize static member variables.

struct JaslEnumInterface::JaslEnumData JaslEnumInterface::descriptionsEnumData =
	{"Description$Descriptions",nullptr,nullptr,nullptr,nullptr,nullptr};

struct JaslEnumInterface::JaslEnumData* JaslEnumInterface::currentEnumData = nullptr;
/// @endcond

// toClass: Return the Java Enum class reference associated with the specified
//          enum type.

template <typename EnumClass>
jclass JaslEnumInterface::toClass() noexcept
{
	std::string javaClassPath("jasl/counters/");
	std::string javaEnumPath;

	if (std::is_same_v<EnumClass,Descriptions>)
	{
		currentEnumData = &descriptionsEnumData;

		if (descriptionsEnumData.enumClass)
		{
			return descriptionsEnumData.enumClass;
		}

		javaEnumPath = descriptionsEnumData.enumPath;
	}

	assert(!javaEnumPath.empty());

	javaClassPath.append(javaEnumPath);

	jclass javaClass =
		jniEnv().FindClass(javaClassPath.c_str());
	assert(nullptr != javaClass);

	if (std::is_same_v<EnumClass,Descriptions>)
	{
		descriptionsEnumData.enumPath  = javaClassPath;
		descriptionsEnumData.enumClass = javaClass;
	}

	return javaClass;
}

// convertToObject: Return an instance of the specified enum type, set to the
//                  indicated value.

jobject JaslEnumInterface::convertToObject(jclass javaClass,
                                           int enumValueIndex) noexcept
{
	assert(nullptr != javaClass);
	assert(enumValueIndex >= 0);
	assert(nullptr != currentEnumData);

	if (nullptr == currentEnumData->valuesMethodID)
	{
		std::string valuesSignature("()[L");

		valuesSignature.append(currentEnumData->enumPath);
		valuesSignature.append(";");

		printf("valuesSignature: %s\n",valuesSignature.c_str());

		currentEnumData->valuesMethodID =
			methodID(javaClass,"values",
			         valuesSignature.c_str(),true);
	}

	assert(nullptr != currentEnumData->valuesMethodID);

	const jobjectArray javaObjectArray =
		static_cast<jobjectArray>(jniEnv().CallStaticObjectMethod(javaClass,
		                                                          currentEnumData->valuesMethodID));
	assert(nullptr != javaObjectArray);

	if (nullptr == currentEnumData->nameMethodID)
	{
		currentEnumData->nameMethodID =
			methodID(javaClass,"name",toStringSignature);
	}

	assert(nullptr != currentEnumData->nameMethodID);

	jobject enumObject =
		jniEnv().GetObjectArrayElement(javaObjectArray,
		                               enumValueIndex);
	assert(nullptr != enumObject);

	const jstring javaString =
		static_cast<jstring>(jniEnv().CallObjectMethod(enumObject,
		                                               currentEnumData->nameMethodID));
	assert(nullptr != javaString);

	if (nullptr == currentEnumData->valueOfMethodID)
	{
		std::string valueOfSignature("(Ljava/lang/String;)L");

		valueOfSignature.append(currentEnumData->enumPath);
		valueOfSignature.append(";");

		printf("valueOfSignature: %s\n",valueOfSignature.c_str());

		currentEnumData->valueOfMethodID =
			methodID(javaClass,"valueOf",
			         valueOfSignature.c_str(),true);
	}

	assert(nullptr != currentEnumData->valueOfMethodID);

	jobject returnObject =
		jniEnv().CallStaticObjectMethod(javaClass,
		                                currentEnumData->valueOfMethodID,
		                                javaString);
	jniEnv().DeleteLocalRef(javaString);

	printf("enumPath: %s\tenumClass: %p\n",
	       currentEnumData->enumPath.c_str(),currentEnumData->enumClass);
	printf("valuesMethodID: %p\tnameMethodID: %p\n",
	       currentEnumData->valuesMethodID,currentEnumData->nameMethodID);
	printf("valueOfMethodID: %p\ttoStringMethodID: %p\n",
	       currentEnumData->valueOfMethodID, currentEnumData->toStringMethodID);

	return returnObject;
}

// convertToString: Return the label associated with the specifed enum value.

std::string JaslEnumInterface::convertToString(jclass javaClass,
                                               int enumValueIndex) noexcept
{
	assert(nullptr != javaClass);
	assert(enumValueIndex >= 0);
	assert(nullptr != currentEnumData);

	if (nullptr == currentEnumData->toStringMethodID)
	{
		currentEnumData->toStringMethodID =
			methodID(javaClass,"toString",toStringSignature);
	}

	assert(nullptr != currentEnumData->toStringMethodID);

	jobject enumObject = convertToObject(javaClass,enumValueIndex);
	assert(nullptr != enumObject);

	std::string returnString(toString(currentEnumData->toStringMethodID,enumObject));

	jniEnv().DeleteLocalRef(enumObject);

	return returnString;
}

// The following declarations are necessary in order for link succcessfully.
// See <A HREF="https://isocpp.org/wiki/faq/templates#templates-defn-vs-decl">here</A> for details.

template jclass JaslEnumInterface::toClass<Descriptions>();
