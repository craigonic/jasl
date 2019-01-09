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

// Initialize static member variables.

jmethodID Dice::_constructorID           = nullptr;
jmethodID Dice::_whiteDieValueMethodID   = nullptr;
jmethodID Dice::_coloredDieValueMethodID = nullptr;
jmethodID Dice::_combinedResultMethodID  = nullptr;
jmethodID Dice::_toTextMethodID          = nullptr;

// Constructor.

Dice::Dice()
{
	_diceClass = jniEnv().FindClass("jasl/utilities/Dice");
	assert(nullptr != _diceClass);

	if (nullptr == _constructorID)
	{
		_constructorID = methodID(_diceClass,"<init>","()V");
	}

	assert(nullptr != _constructorID);

	_diceObject = jniEnv().NewObject(_diceClass,_constructorID);
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
	if (nullptr == _whiteDieValueMethodID)
	{
		_whiteDieValueMethodID =
			methodID(_diceClass,"whiteDieValue","()I");
	}

	assert(nullptr != _whiteDieValueMethodID);

	return jniEnv().CallIntMethod(_diceObject,_whiteDieValueMethodID);
}

// coloredDieValue: Return the result of rolling the colored die.

int Dice::coloredDieValue() const noexcept
{
	if (nullptr == _coloredDieValueMethodID)
	{
		_coloredDieValueMethodID =
			methodID(_diceClass,"coloredDieValue","()I");
	}

	assert(nullptr != _coloredDieValueMethodID);

	return jniEnv().CallIntMethod(_diceObject,_coloredDieValueMethodID);
}

// combinedResult: Return the result of combining the values of the two dice.

int Dice::combinedResult() const noexcept
{
	if (nullptr == _combinedResultMethodID)
	{
		_combinedResultMethodID =
			methodID(_diceClass,"combinedResult","()I");
	}

	assert(nullptr != _combinedResultMethodID);

	return jniEnv().CallIntMethod(_diceObject,_combinedResultMethodID);
}

// toText: Return a text representation of the attributes and current state of
//         this Dice instance.

std::string Dice::toText() const noexcept
{
	if (nullptr == _toTextMethodID)
	{
		_toTextMethodID =
			methodID(_diceClass,"toText",toStringSignature);
	}

	assert(nullptr != _toTextMethodID);

	return toString(_toTextMethodID,_diceObject);
}
