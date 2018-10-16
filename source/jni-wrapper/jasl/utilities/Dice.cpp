/**
 * \file Dice.cpp
 *
 * This file defines a "wrapper" class intended to simplify access for C/C++
 * programs to the <A HREF="../../../jasl/utilities/Dice.html">Dice</A> class, which is implemented in <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A>.
 *
 * Written By: Craig R. Campbell  -  December 2010
 */

#include "Dice.h"

#include "jasl/jni/JniWrapper.h"

#include <assert.h>

// Constructor.

Dice::Dice()
{
	_diceClass = jniEnv().FindClass("jasl/utilities/Dice");
	assert(nullptr != _diceClass);

	const jmethodID constructorID =
		jniEnv().GetMethodID(_diceClass,"<init>","()V");
	assert(nullptr != constructorID);

	_diceObject = jniEnv().NewObject(_diceClass,constructorID);
	assert(nullptr != _diceObject);
}

// Destructor.

Dice::~Dice()
{
	jniEnv().DeleteLocalRef(_diceObject);
}

// whiteDieValue: Return the result of rolling the white die.

int Dice::whiteDieValue() const noexcept
{
	const jmethodID methodID =
		jniEnv().GetMethodID(_diceClass,"whiteDieValue","()I");
	assert(nullptr != methodID);

	return jniEnv().CallIntMethod(_diceObject,methodID);
}

// coloredDieValue: Return the result of rolling the colored die.

int Dice::coloredDieValue() const noexcept
{
	const jmethodID methodID =
		jniEnv().GetMethodID(_diceClass,"coloredDieValue","()I");
	assert(nullptr != methodID);

	return jniEnv().CallIntMethod(_diceObject,methodID);
}

// combinedResult: Return the result of combining the values of the two dice.

int Dice::combinedResult() const noexcept
{
	const jmethodID methodID =
		jniEnv().GetMethodID(_diceClass,"combinedResult","()I");
	assert(nullptr != methodID);

	return jniEnv().CallIntMethod(_diceObject,methodID);
}

// toText: Return a text representation of the attributes and current state of
//         this Dice instance.

std::string Dice::toText() noexcept
{
	const jmethodID methodID =
		jniEnv().GetMethodID(_diceClass,"toText",
		                     "()Ljava/lang/String;");
	assert(nullptr != methodID);

	const jstring javaString =
		static_cast<jstring>(jniEnv().CallObjectMethod(_diceObject,methodID));
	assert(nullptr != javaString);

	std::string returnString(std::move(js2ss(javaString)));

	jniEnv().DeleteLocalRef(javaString);

	return returnString;
}
