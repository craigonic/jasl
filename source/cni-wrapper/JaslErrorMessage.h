/**
 * \file JaslErrorMessage.h
 *
 * This file declares a generic C++ exception class to "wrap" a <A HREF="http://java.sun.com/">Java</A> <A HREF="http://download.oracle.com/javase/6/docs/api/java/lang/Throwable.html">Throwable</A>
 * object, providing a copy of the detailed description of the error it
 * represents.
 *
 * Written By: Craig R. Campbell  -  March 2011
 */

#ifndef JASL_ERROR_MESSAGE_H
#define JASL_ERROR_MESSAGE_H

#ifdef __cplusplus
namespace java
{
	namespace lang
	{
		class Throwable;
	}
}

/**
 * \brief jASL exception wrapper.
 *
 * This class is used to create a generic C++ exception to provide access to the
 * message in the <A HREF="http://java.sun.com/">Java</A> <A HREF="http://download.oracle.com/javase/6/docs/api/java/lang/Throwable.html">Throwable</A> object specified to the constructor, leaving
 * all of its other, mostly non-essential, functionality behind.
 *
 * @version 1.0
 * @author Craig R. Campbell
 * @see <A HREF="../../source/cni-wrapper/JaslErrorMessage.h.html">Source code</A>
 */

class JaslErrorMessage
{
	public:

		/**
		 * \brief Constructor.
		 */

		JaslErrorMessage(::java::lang::Throwable* exception);

		/**
		 * \brief Destructor.
		 *
		 * The destructor frees the memory associated with the string
		 * returned by the text() method.
		 */

		~JaslErrorMessage();

		/** <A NAME="_TEXT_"></A>
		 * \brief Return a detailed description of the error.
		 *
		 * The returned string is a copy of data found in the object
		 * passed to the constructor.
		 *
		 * <B>THE RETURNED STRING SHOULD NOT BE DELETED, AS THIS OCCURS AS
		 * PART OF THE DESTRUCTION OF THE OBJECT.</B>
		 */

		const char* text() const;

	private:

		/**
		 * A copy of the message text defined within the exception
		 * object passed to the constructor.
		 *
		 * The memory associated with this item is freed in the
		 * destructor.
		 */

		const char* _text;

		// Disable the generation of a default copy constructor and "="
		// operator.

		JaslErrorMessage(const JaslErrorMessage& jaslErrorMessage);
		JaslErrorMessage& operator=(const JaslErrorMessage& jaslErrorMessage);
};
#else
/******************************************************************************/

// The typedef and functions declared below are intended for use by C programs
// to access JaslErrorMessage objects.

/**
 * \typedef struct JaslErrorMessage JaslErrorMessage
 * \brief Declaration to provide access to JaslErrorMessage objects from C
 * programs.
 */

typedef struct JaslErrorMessage JaslErrorMessage;
#endif // __cplusplus
#ifdef __cplusplus
extern "C" {
#endif // __cplusplus
/**
 * \brief Free the memory associated with the specified object.
 */

extern void deleteMessage(JaslErrorMessage* jaslErrorMessage);

/**
 * \brief Return a detailed description of the error.
 *
 * This function calls the <A HREF="#_TEXT_">text</A>() method of the indicated object.
 *
 * <B>NOTE: THE RETURNED STRING SHOULD NOT BE DELETED OR FREED. IT OCCURS AS PART
 * OF THE DESTRUCTION OF THE JaslErrorMessage OBJECT.</B>
 */

extern const char* messageText(JaslErrorMessage* jaslErrorMessage);

/**
 * \brief Write the message text to stderr.
 *
 * This function prepends a CR-LF and "Caught: " to the output of the <A HREF="#_TEXT_">text</A>()
 * method of the indicated object.
 */

extern void printMessageText(JaslErrorMessage* jaslErrorMessage);
#ifdef __cplusplus
}
#endif // __cplusplus
#endif // JASL_ERROR_MESSAGE_H
