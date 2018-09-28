/**
 * \file Unit.cpp
 *
 * This file defines a "wrapper" class intended to simplify access for C/C++
 * programs to the <A HREF="../../../jasl/counters/Unit.html">Unit</A> class, which is implemented in <A HREF="http://www.oracle.com/technetwork/java/index.html">Java</A>.
 *
 * Written By: Craig R. Campbell  -  January 2010
 */

#include "jasl/cni/CniWrapper.h"
#include "jasl/counters/Description$Descriptions.h"
#include "jasl/counters/Unit.h"

#include "Unit.h"

// Constructor.

Unit::Unit() :
	_unit(0),_description(0),_dump(0),_label(0),_json(0)
{
}

// Destructor.

Unit::~Unit()
{
	delete [] _description;
	delete [] _dump;
	delete [] _label;
	delete [] _json;
}

// description: Return the description of this Unit.

const char* Unit::description()
{
	if (0 == _description)
	{
		_description = js2cc(_unit->description()->toString());
	}

	return _description;
}

// description: Return the basic type of this Unit.

Descriptions Unit::descriptionType()
{
	return Descriptions(_unit->description()->ordinal());
}

// toText: Return a text representation of the attributes and current state of
//         this Unit.

const char* Unit::toText()
{
	if (_dump) delete [] _dump;

	_dump = js2cc(_unit->toText());

	return _dump;
}

// toString: Return an abbreviated description, which may include attributes, of
//           this Unit.

const char* Unit::toString()
{
	if (_label) delete [] _label;

	_label = js2cc(_unit->toString());

	return _label;
}

// toJSON: Return a JSON representation of the attributes and current state of
//         this Unit.

const char* Unit::toJSON()
{
	if (_json) delete [] _json;

	_json = js2cc(_unit->toJSON());

	return _json;
}

/******************************************************************************/

// These functions are intended for use by C programs to access Unit objects,
// which are treated as a struct.

// description: Return the description of the specified Unit.

const char* description(Unit* unit)
{
	return (unit) ? unit->description() : 0;
}

// descriptionType: Return the basic type of the specified Unit.

Descriptions descriptionType(Unit* unit)
{
	return (unit) ? unit->descriptionType() : Descriptions(0);
}

// toText: Return a text representation of the attributes and current state of
//         the specified Unit.

const char* toText(Unit* unit)
{
	return (unit) ? unit->toText() : 0;
}

// toString: Return an abbreviated description, which may include attributes, of
//           the specified Unit.

const char* toString(Unit* unit)
{
	return (unit) ? unit->toString() : 0;
}

// toJSON: Return a JSON representation of the attributes and current state of
//         the specified Unit.

const char* toJSON(Unit* unit)
{
	return (unit) ? unit->toJSON() : 0;
}
