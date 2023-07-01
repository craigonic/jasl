/**
 * \file JaslErrorMessage.cpp
 *
 * This file defines a generic C++ exception class to "wrap" a Java Throwable
 * object, providing a copy of the detailed description of the error it
 * represents.
 *
 * Written By: Craig R. Campbell  -  April 2011
 */

#include <stdio.h>

#include <java/lang/Throwable.h> // For jthrowable (::java::lang::Throwable*).

#include "CniWrapper.h"          // For js2cc().
#include "JaslErrorMessage.h"

// Constructor.

JaslErrorMessage::JaslErrorMessage(jthrowable exception) :
	_text(js2cc(exception->toString()))
{
}

// Destructor.

JaslErrorMessage::~JaslErrorMessage()
{
	delete [] _text;
}

// text: Return a detailed description of the error.

const char* JaslErrorMessage::text() const
{
	return _text;
}

/******************************************************************************/

// deleteMessage: Free the memory associated with the specified object.

void deleteMessage(JaslErrorMessage* jaslErrorMessage)
{
	delete jaslErrorMessage;
}

// messageText: Return a detailed description of the error.

const char* messageText(JaslErrorMessage* jaslErrorMessage)
{
	return (jaslErrorMessage) ? jaslErrorMessage->text() : 0;
}

// printMessageText: Write the message text to stderr.

void printMessageText(JaslErrorMessage* jaslErrorMessage)
{
	fprintf(stderr,"\nCaught: %s",messageText(jaslErrorMessage));
}
