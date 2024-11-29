/**
 * \file Description.h
 *
 * This file declares a proxy intended to mirror the elements found in the
 * <A HREF="../../../jasl/counters/Description.html">Descriptions</A> Enum, which is implemented in Java, for use in C++ programs.
 *
 * Written By: Craig R. Campbell  -  March 2024
 */

#pragma once

#include <jni.h>

#include <string>

/**
 * \brief <A HREF="../../../jasl/counters/Description.html">Descriptions</A> enum JNI (Java Native Interface) proxy.
 *
 * This enum provides a subset of the functionality of its namesake, which is
 * implemented in Java.
 *
 * @version 0.1
 * @author Copyright (C) 2024 Craig R. Campbell (craigonic@gmail.com)
 */

enum class Descriptions : int
{
	Crew,
	HalfSquad,
	Leader,
	Squad
};

// The returned string for the following method is a copy of a Java String,
// converted to the indicated type using the <A HREF="../../JniWrapper.h.html#_JS2SS_">js2ss</A>() function.

/**
 * \brief Return the label associated with the specifed enum value.
 */

std::string toString(Descriptions description) noexcept;

/**
 * \brief Return an instance of a (Java) Description.Descriptions object, based
 * on the specified value.
 */

jobject toObject(Descriptions description) noexcept;
