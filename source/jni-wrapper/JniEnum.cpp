/**
 * \file JniEnum.cpp
 *
 * This file defines a proxy intended to mirror some of the behavior of a Java
 * Enum, for use in C++ programs.
 *
 * Written By: Craig R. Campbell  -  January 2019
 */

#include "JniEnum.h"

#include "JniWrapper.h"

#include <assert.h>

static void setEnumClass(JniEnumInterface::JniEnumData& jniEnumData)
{
	// If it is not already set, retrieve a reference to the Java Class
	// object associated with the class path. This will be used in the
	// subsequent calls to retrieve method IDs.

	if (nullptr == jniEnumData.enumClass)
	{
		jclass localReference = toClass(jniEnumData.enumPath);
		jniEnumData.enumClass =
			static_cast<jclass>(jniEnv().NewGlobalRef(localReference));
		jniEnv().DeleteLocalRef(localReference); // Necessary?

		printf("enumPath: %s\n",jniEnumData.enumPath.c_str());
//		printf("enumClass: %p\n",jniEnumData.enumClass);
	}

	assert(nullptr != jniEnumData.enumClass);
}

static void setValuesMethodID(JniEnumInterface::JniEnumData& jniEnumData)
{
	// If it is not already set, retrieve a reference to the values() method
	// of the Enum.

	if (nullptr == jniEnumData.valuesMethodID)
	{
		std::string valuesSignature("()[L");

		valuesSignature.append(jniEnumData.enumPath);
		valuesSignature.append(";");

		printf("valuesSignature: %s\n",valuesSignature.c_str());

		jniEnumData.valuesMethodID =
			methodID(jniEnumData.enumClass,"values",
			         valuesSignature.c_str(),true);

//		printf("valuesMethodID: %p\n",jniEnumData.valuesMethodID);
	}

	assert(nullptr != jniEnumData.valuesMethodID);
}

static jobject enumObjectAtIndex(const JniEnumInterface::JniEnumData& jniEnumData,
                                 int enumValueIndex)
{
	// Use the Class reference and the values() method reference to retrieve
	// a list of all of the elements of the Enum. The items will be in the
	// order that they were declared.

	const jobjectArray javaObjectArray =
		static_cast<jobjectArray>(jniEnv().CallStaticObjectMethod(jniEnumData.enumClass,
		                                                          jniEnumData.valuesMethodID));
	assert(nullptr != javaObjectArray);

	// Return a reference to the Enum element at the specified index.

	return jniEnv().GetObjectArrayElement(javaObjectArray,enumValueIndex);
}

// convertToObject: Return an instance of the specifed Enum element.

jobject JniEnumInterface::convertToObject(JniEnumData& jniEnumData,
                                          int enumValueIndex) noexcept
{
	setEnumClass(jniEnumData);

	assert(enumValueIndex >= 0);

	setValuesMethodID(jniEnumData);

	// Retrieve a reference to the Enum element at the specified index.

	const jobject enumObject = enumObjectAtIndex(jniEnumData,enumValueIndex);
	assert(nullptr != enumObject);

	// If it is not already set, retrieve a reference to the name() method
	// of the Enum.

	if (nullptr == jniEnumData.nameMethodID)
	{
		jniEnumData.nameMethodID =
			methodID(jniEnumData.enumClass,"name",toStringSignature);

//		printf("nameMethodID: %p\n",jniEnumData.nameMethodID);
	}

	assert(nullptr != jniEnumData.nameMethodID);

	// Use the name() method reference to retrieve the identifier of the
	// selected list object.

	const jstring javaString =
		static_cast<jstring>(jniEnv().CallObjectMethod(enumObject,
		                                               jniEnumData.nameMethodID));
	assert(nullptr != javaString);

	// If it is not already set, retrieve a reference to the valueOf()
	// method of the Enum.

	if (nullptr == jniEnumData.valueOfMethodID)
	{
		std::string valueOfSignature("(Ljava/lang/String;)L");

		valueOfSignature.append(jniEnumData.enumPath);
		valueOfSignature.append(";");

		printf("valueOfSignature: %s\n",valueOfSignature.c_str());

		jniEnumData.valueOfMethodID =
			methodID(jniEnumData.enumClass,"valueOf",
			         valueOfSignature.c_str(),true);

//		printf("valueOfMethodID: %p\n",jniEnumData.valueOfMethodID);
	}

	assert(nullptr != jniEnumData.valueOfMethodID);

	// Use the Class reference and the valueOf() method reference, with the
	// element name retrieved above, to retrieve a new instance of the Enum
	// with the specified value.

	jobject returnObject =
		jniEnv().CallStaticObjectMethod(jniEnumData.enumClass,
		                                jniEnumData.valueOfMethodID,
		                                javaString);

	jniEnv().DeleteLocalRef(javaString); // Necessary?

	return returnObject;
}

// convertToString: Return the label associated with the specifed Enum element.

std::string JniEnumInterface::convertToString(JniEnumData& jniEnumData,
                                              int enumValueIndex) noexcept
{
	setEnumClass(jniEnumData);

	assert(enumValueIndex >= 0);

	// If it is not already set, retrieve a reference to the toString()
	// method of the Enum.

	if (nullptr == jniEnumData.toStringMethodID)
	{
		jniEnumData.toStringMethodID =
			methodID(jniEnumData.enumClass,"toString",
			         toStringSignature);

//		printf("toStringMethodID: %p\n",jniEnumData.toStringMethodID);
	}

	assert(nullptr != jniEnumData.toStringMethodID);

	setValuesMethodID(jniEnumData);

	// Retrieve a reference to the Enum element at the specified index.

	const jobject enumObject = enumObjectAtIndex(jniEnumData,enumValueIndex);
	assert(nullptr != enumObject);

	// Use the toString() method reference to retrieve the "friendly" name
	// for the specified Enum value.

	std::string returnString(toString(jniEnumData.toStringMethodID,
	                                  enumObject));

	jniEnv().DeleteLocalRef(enumObject); // Necessary?

	return returnString;
}

int JniEnumInterface::enumValueIndex(JniEnumData& jniEnumData,
                                     const jobject enumObject) noexcept
{
	setEnumClass(jniEnumData);

	assert(nullptr != enumObject);

	// If it is not already set, retrieve a reference to the ordinal()
	// method of the Enum.

	if (nullptr == jniEnumData.ordinalMethodID)
	{
		jniEnumData.ordinalMethodID =
			methodID(jniEnumData.enumClass,"ordinal","()I");

//		printf("ordinalMethodID: %p\n",jniEnumData.ordinalMethodID);
	}

	assert(nullptr != jniEnumData.ordinalMethodID);

	// Use the ordinal() method reference to retrieve the index of the
	// specified Enum object.

	return jniEnv().CallIntMethod(enumObject,
	                              jniEnumData.ordinalMethodID);
}
