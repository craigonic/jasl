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
	return _dice->getWhiteDieValue();
}

// coloredDieValue: Return the result of rolling the colored die.

int Dice::coloredDieValue() const
{
	return _dice->getColoredDieValue();
}

// combinedResult: Return the result of combining the values of the two dice.

int Dice::combinedResult() const
{
	return _dice->getCombinedResult();
}

// toString: Return a text representation of the attributes and current state of
//           this Dice.

const char* Dice::toString()
{
	if (_dump == 0) _dump = js2cc(_dice->toString());

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

// toString: Return a text representation of the current state of the Dice.

const char* toString(Dice* dice)
{
	return (dice) ? dice->toString() : 0;
}
