/**
 * \file jaslWrapper.h
 *
 * This file contains a set of typedef declarations intended to simplify the use
 * of the public classes in the jasl libraries when accessed through the <A HREF="http://gcc.gnu.org/onlinedocs/gcc-6.3.0/gcj/About-CNI.html#About-CNI">CNI</A>
 * (Compiled Native Interface) wrapper.
 *
 * Written By: Craig R. Campbell  -  June 2007
 */

#pragma once

#include "jasl/counters/Classification$Classifications.h"
#include "jasl/counters/Description$Descriptions.h"
#include "jasl/counters/Nationality$Nationalities.h"
#include "jasl/counters/UnitType$InfantryTypes.h"
#include "jasl/counters/Status$States.h"

#include "jasl/counters/Unit.h"
#include "jasl/counters/Leader.h"
#include "jasl/counters/Squad.h"

#include "jasl/ui/data/Game.h"
#include "jasl/ui/data/Player.h"
#include "jasl/ui/data/Side$Sides.h"

#include "jasl/utilities/Dice.h"
#include "jasl/utilities/Serialization.h"

// Counters

/**
 * \typedef jasl::counters::Classification$Classifications Classifications
 * \brief Shorthand declaration for the <A HREF="../../source/jasl/counters/Classification.html">Classifications</A> enum.
 */

typedef jasl::counters::Classification$Classifications Classifications;

/**
 * \typedef jasl::counters::Description$Descriptions Descriptions
 * \brief Shorthand declaration for the <A HREF="../../source/jasl/counters/Description.html">Descriptions</A> enum.
 */

typedef jasl::counters::Description$Descriptions Descriptions;

/**
 * \typedef jasl::counters::Nationality$Nationalities Nationalities
 * \brief Shorthand declaration for the <A HREF="../../source/jasl/counters/Nationality.html">Nationalities</A> enum.
 */

typedef jasl::counters::Nationality$Nationalities Nationalities;

/**
 * \typedef jasl::counters::UnitType$InfantryTypes InfantryTypes
 * \brief Shorthand declaration for the <A HREF="../../source/jasl/counters/UnitType.html">InfantryTypes</A> enum.
 */

typedef jasl::counters::UnitType$InfantryTypes InfantryTypes;

/**
 * \typedef jasl::counters::Status$States States
 * \brief Shorthand declaration for the <A HREF="../../source/jasl/counters/Status.html">States</A> enum.
 */

typedef jasl::counters::Status$States States;

/**
 * \typedef jasl::counters::Unit Unit
 * \brief Shorthand declaration for a <A HREF="../../source/jasl/counters/Unit.html">Unit</A> object.
 */

typedef jasl::counters::Unit Unit;

/**
 * \typedef jasl::counters::Leader Leader
 * \brief Shorthand declaration for a <A HREF="../../source/jasl/counters/Leader.html">Leader</A> object.
 */

typedef jasl::counters::Leader Leader;

/**
 * \typedef jasl::counters::Squad Squad
 * \brief Shorthand declaration for a <A HREF="../../source/jasl/counters/Squad.html">Squad</A> object.
 */

typedef jasl::counters::Squad Squad;

// UI

/**
 * \typedef jasl::ui::data::Game Game
 * \brief Shorthand declaration for a <A HREF="../../source/jasl/ui/data/Game.html">Game</A> object.
 */

typedef jasl::ui::data::Game Game;

/**
 * \typedef jasl::ui::data::Player Player
 * \brief Shorthand declaration for a <A HREF="../../source/jasl/ui/data/Player.html">Player</A> object.
 */

typedef jasl::ui::data::Player Player;

/**
 * \typedef jasl::ui::data::Side$Sides Sides
 * \brief Shorthand declaration for the <A HREF="../../source/jasl/ui/data/Side.html">Sides</A> enum.
 */

typedef jasl::ui::data::Side$Sides Sides;

// Utilities

/**
 * \typedef jasl::utilities::Dice Dice
 * \brief Shorthand declaration for a <A HREF="../../source/jasl/utilities/Dice.html">Dice</A> object.
 */

typedef jasl::utilities::Dice Dice;

/**
 * \typedef jasl::utilities::Serialization Serialization
 * \brief Shorthand declaration for the <A HREF="../../source/jasl/utilities/Serialization.html">Serialization</A> object.
 */

typedef jasl::utilities::Serialization Serialization;
