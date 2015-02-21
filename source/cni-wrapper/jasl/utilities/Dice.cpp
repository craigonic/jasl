/**
 * \file Dice.cpp
 *
 * This file defines a "wrapper" class intended to simplify access for C/C++
 * programs to the <A HREF="../../../jasl/utilities/Dice.html">Dice</A> class, which is implemented in <A HREF="http://java.sun.com/">Java</A>.
 *
 * Written By: Craig R. Campbell  -  December 2010
 */

#include "jasl/cni/CniWrapper.h"
#include "jasl/utilities/Dice.h"

#include "Dice.h"

// Constructor.

Dice::Dice() :
	_dice(new jasl::utilities::Dice()),
	_dump(0)
{
}

// Destructor.

Dice::~Dice()
{
	delete [] _dump;
}

// whiteDieValue: Return the result of rolling the white die.

int Dice::whiteDieValue() const
{
	return _dice->whiteDieValue();
}

// coloredDieValue: Return the result of rolling the colored die.

int Dice::coloredDieValue() const
{
	return _dice->coloredDieValue();
}

// combinedResult: Return the result of combining the values of the two dice.

int Dice::combinedResult() const
{
	return _dice->combinedResult();
}

// toText: Return a text representation of the attributes and current state of
//         this Dice.

const char* Dice::toText()
{
	if (0 == _dump) _dump = js2cc(_dice->toText());

	return _dump;
}

/******************************************************************************/

// These functions are intended for use by C programs to access Dice objects,
// which are treated as a struct.

// rollDice: Create an instance of a Dice object, which automatically rolls
//           them.

Dice* rollDice()
{
	return new Dice();
}

// deleteDice: Free the memory associated with the specified object.

void deleteDice(Dice* dice)
{
	delete dice;
}

// whiteDieValue: Return the result of rolling the white die.

int whiteDieValue(Dice* dice)
{
	return (dice) ? dice->whiteDieValue() : 0;
}

// coloredDieValue: Return the result of rolling the colored die.

int coloredDieValue(Dice* dice)
{
	return (dice) ? dice->coloredDieValue() : 0;
}

// combinedResult: Return the result of combining the values of the two dice.

int combinedResult(Dice* dice)
{
	return (dice) ? dice->combinedResult() : 0;
}

// toText: Return a text representation of the current state of the Dice.

const char* toText(Dice* dice)
{
	return (dice) ? dice->toText() : 0;
}
