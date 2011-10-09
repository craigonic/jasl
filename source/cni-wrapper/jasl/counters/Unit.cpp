/**
 * \file Unit.cpp
 *
 * This file defines a "wrapper" class intended to simplify access for C/C++
 * programs to the <A HREF="../../../jasl/counters/Unit.html">Unit</A> class, which is implemented in <A HREF="http://java.sun.com/">Java</A>.
 *
 * Written By: Craig R. Campbell  -  January 2010
 */

#include "jasl/cni/CniWrapper.h"
#include "jasl/counters/Unit.h"

#include "Unit.h"

// Constructor.

Unit::Unit() :
	_unit(0),_description(0),_dump(0)
{
}

// Destructor.

Unit::~Unit()
{
	delete [] _description;
	delete [] _dump;
}

// description: Return the description of this Unit.

const char* Unit::description()
{
	if (_description == 0)
	{
		_description = js2cc(_unit->getDescription());
	}

	return _description;
}

// toString: Return a text representation of the attributes and current state of
//           this Unit.

const char* Unit::toString()
{
	if (_dump) delete [] _dump;

	_dump = js2cc(_unit->toString());

	return _dump;
}

/******************************************************************************/

// These functions are intended for use by C programs to access Unit objects,
// which are treated as a struct.

// description: Return the description of the specified Unit.

const char* description(Unit* unit)
{
	return (unit) ? unit->description() : 0;
}

// toString: Return a text representation of the attributes and current state of
//           the specified Unit.

const char* toString(Unit* unit)
{
	return (unit) ? unit->toString() : 0;
}
