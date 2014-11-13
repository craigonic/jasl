################################################################################
# Driver.py - This file contains the Driver script, which is used to test the  #
#             functionality of the public <A HREF="http://java.sun.com/">Java</A> classes defined in the <A HREF="jasl/jasl.html">jasl</A>     #
#             package hierarchy, accessed by <A HREF="http://www.python.org/">Python</A> using <A HREF="swig/swig.html">libraries</A> and files  #
#             generated by <A HREF="http://www.swig.org/">SWIG</A>.                                               #
#                                                                              #
#             The <A HREF="http://gcc.gnu.org/onlinedocs/gcj/About-CNI.html#About-CNI">CNI</A> (Compiled Native Interface) <A HREF="http://gcc.gnu.org/onlinedocs/gcj/Invocation.html#Invocation">invocation</A> functions, which  #
#             are required to use the Java classes, are initiated in this      #
#             script by the <A HREF="swig/CniWrapper.swig.html">CniWrapper</A> module. This module also provides       #
#             methods to convert a native string to or from a Java <A HREF="http://java.sun.com/javase/7/docs/api/java/lang/String.html">String</A>.     #
#                                                                              #
# Written By: Craig R. Campbell  -  August 2008                                #
################################################################################

import sys
sys.path.append("/home/campbell/jasl/lib/jasl/python")

from CniWrapper import *
from Counters   import *
from UiData     import *
from Utilities  import *

# The following call is necessary only if either the js2cc() or cc2js() function
# is NOT called (invoking either one will start the CniWrapper).

#cni_wrapper = CniWrapper_instance()

# A call to the valueOf() method of each of the following enums is necessary in
# order to use the constants associated with the type/object directly.

nationality    = Nationalities_valueOf(cc2js("GERMAN"))
unitType       = InfantryTypes_valueOf(cc2js("NONE"))
classification = Classifications_valueOf(cc2js("ELITE"))
state          = States_valueOf(cc2js("NORMAL"))

# Create an instance of a German Leader.

germanLeader = Leader(nationality,unitType,9,9,4,-1)

germanLeader.setIdentity(cc2js("Lt. Fellbaum"))

# Display all of the entered values for this instance using the toString()
# method.

print "\nLeader.toString() output:\n\n%s" % js2cc(germanLeader.toString())

# Display the output of all of the access methods declared for the Leader class
# using the instance created above.

#print "Leader class access methods and output :\n"

#print "\tdescription(): %s"           % js2cc(germanLeader.description())

#print "\tidentity(): %s"              % js2cc(germanLeader.identity())
#print "\tnationality(): %s"           % js2cc(germanLeader.nationality())
#print "\tstatus(): %s"                % js2cc(germanLeader.status())
#print "\tunitType(): %s"              % js2cc(germanLeader.unitType())

#print "\tmovement(): %d"              % germanLeader.movement()
#print "\tportageCapacity(): %d"       % germanLeader.portageCapacity()
#print "\tportageLevel(): %d"          % germanLeader.portageLevel()

#print "\tbasicPointValue(): %d"       % germanLeader.basicPointValue()
#print "\tbrokenMorale(): %d"          % germanLeader.brokenMorale()
#print "\tcanSelfRally(): %d"          % germanLeader.canSelfRally()
#print "\texperienceLevelRating(): %d" % germanLeader.experienceLevelRating()
#print "\tfirepower(): %s"             % js2cc(germanLeader.firepower())
#print "\tfirepowerEquivalent(): %d"   % germanLeader.firepowerEquivalent()
#print "\tmorale(): %d"                % germanLeader.morale()
#print "\tnormalRange(): %d"           % germanLeader.normalRange()
#print "\tportageValue(): %d"          % germanLeader.portageValue()

#print "\tmodifier(): %d"              % germanLeader.modifier()

# Serialize the Leader object, write the data to a file (Leader.ser), then
# deserialize the data into a new object.

germanLeader.setIdentity(cc2js("Col. Klink"))

serializationFile = cc2js("/tmp/Leader.ser");

Serialization_serializeToFile(toObject(germanLeader),serializationFile);

unit = fromObject(Serialization_deserializeFromFile(serializationFile));

# Display all of the entered values for the deserialized instance using the
# toString() method.

print "(Deserialized) Leader.toString() output:\n\n%s" % js2cc(unit.toString())

# Create an instance of a Russian Squad.

nationality    = Nationalities_valueOf(cc2js("RUSSIAN"))
unitType       = InfantryTypes_valueOf(cc2js("GUARDS"))

russianSquad = Squad(nationality,unitType,6,2,8,8,0,12,4,0,classification,1,1,0)

russianSquad.setIdentity(cc2js("A"))

# Display all of the entered values for this instance using the toString()
# method.

print "Squad.toString() output:\n\n%s" % js2cc(russianSquad.toString())

# Display the output of all of the access methods declared for the Squad class
# using the instance created above.

#print "Squad class access methods and output :\n"

#print "\tdescription(): %s"            % js2cc(russianSquad.description())

#print "\tidentity(): %s"               % js2cc(russianSquad.identity())
#print "\tnationality(): %s"            % js2cc(russianSquad.nationality())
#print "\tstatus(): %s"                 % js2cc(russianSquad.status())
#print "\tunitType(): %s"               % js2cc(russianSquad.unitType())

#print "\tmovement(): %d"               % russianSquad.movement()
#print "\tportageCapacity(): %d"        % russianSquad.portageCapacity()
#print "\tportageLevel(): %d"           % russianSquad.portageLevel()

#print "\tbasicPointValue(): %d"        % russianSquad.basicPointValue()
#print "\tbrokenMorale(): %d"           % russianSquad.brokenMorale()
#print "\tcanSelfRally(): %d"           % russianSquad.canSelfRally()
#print "\texperienceLevelRating(): %d"  % russianSquad.experienceLevelRating()
#print "\tfirepower(): %s"              % js2cc(russianSquad.firepower())
#print "\tfirepowerEquivalent(): %d"    % russianSquad.firepowerEquivalent()
#print "\tmorale(): %d"                 % russianSquad.morale()
#print "\tnormalRange(): %d"            % russianSquad.normalRange()
#print "\tportageValue(): %d"           % russianSquad.portageValue()

#print "\tclassification(): %s"         % js2cc(russianSquad.classification())
#print "\thasMaximumELR(): %d"          % russianSquad.hasMaximumELR()

#print "\tcanAssaultFire(): %d"         % russianSquad.canAssaultFire()
#print "\tcanSprayFire(): %d"           % russianSquad.canSprayFire()
#print "\tsmokePlacementExponent(): %d" % russianSquad.smokePlacementExponent()

# Create an array of Unit objects. These will be used to reference a Leader
# instance and several Squad instances. These class types are derived from Unit.

print "Building Unit array with a Leader & 3 Squads\n";

unitList = []

nationality    = Nationalities_valueOf(cc2js("AMERICAN"))
unitType       = InfantryTypes_valueOf(cc2js("NONE"))
classification = Classifications_valueOf(cc2js("FIRST_LINE"))

unitList.append(Leader(nationality,unitType,9,9,4,-1))

unitList[0].setIdentity(cc2js("Sgt. Slaughter"))

unitList.append(Squad(nationality,unitType,6,6,6,6,0,11,4,0,
                      classification,1,1,0))
unitList.append(Squad(nationality,unitType,6,6,6,6,0,11,4,0,
                      classification,1,1,0))
unitList.append(Squad(nationality,unitType,6,6,6,6,0,11,4,0,
                      classification,1,1,0))

unitList[1].setIdentity(cc2js("X"))
unitList[2].setIdentity(cc2js("Y"))
unitList[3].setIdentity(cc2js("Z"))

print "Displaying Unit array with a Leader & 3 Squads"

for unitIndex in xrange(4):
    print "\nUnitList[%d]:" % unitIndex

    unit = unitList[unitIndex]

    print "\n%s" % js2cc(unit.description())
    print "%s"   % js2cc(unit.identity())
    print "%s"   % js2cc(unit.unitType())
    print "%s"   % unit.movement()
    print "%s"   % js2cc(unit.status())

# Simple function to prepend "Caught: " to the beginning of an exception message
# returned by the tests below. This is done primarily to modify the output to
# match that of the other test programs.

def printException(detail):
    print "\nCaught: %s" % detail

# Create an instance of a German Squad (that throws some exceptions).

print "\nTesting Exception handling during Squad creation:"

nationality = Nationalities_valueOf(cc2js("BRITISH"))
unitType    = InfantryTypes_valueOf(cc2js("ENGINEERS"))

# Incompatible nationality and unitType

print "\nIncompatible nationality and unitType parameters:"

try:
    squad = Squad(nationality,unitType,4,6,7,7,0,10,3,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

nationality    = Nationalities_valueOf(cc2js("RUSSIAN"))
unitType       = InfantryTypes_valueOf(cc2js("COMMISSAR"))
classification = Classifications_valueOf(cc2js("GREEN"))

# Incompatible description and unitType

print "\nIncompatible description and unitType parameters:"

try:
    squad = Squad(nationality,unitType,4,4,7,7,0,10,3,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

nationality    = Nationalities_valueOf(cc2js("GERMAN"))
unitType       = InfantryTypes_valueOf(cc2js("NONE"))
classification = Classifications_valueOf(cc2js("FIRST_LINE"))

# Invalid Firepower

print "\nInvalid (less than 0) firepower parameter:"

try:
    squad = Squad(nationality,unitType,-1,6,7,7,0,10,3,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

print "\nInvalid (greater than maximum) firepower parameter:"

try:
    squad = Squad(nationality,unitType,11,6,7,7,0,10,3,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

# Invalid Range

print "\nInvalid (less than 0) normal range parameter:"

try:
    squad = Squad(nationality,unitType,4,-255,7,7,0,10,3,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

# Invalid Morale (Minimum)

print "\nInvalid (less than 0) morale parameter:"

try:
    squad = Squad(nationality,unitType,4,6,-1,7,0,10,3,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

# Invalid Morale (Maximum)

print "\nInvalid (greater than maximum) morale parameter:"

try:
    squad = Squad(nationality,unitType,4,6,11,7,0,10,3,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

# Invalid Broken Morale (Minimum)

print "\nInvalid (less than 0) broken morale parameter:"

try:
    squad = Squad(nationality,unitType,4,6,7,-7,0,10,3,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

# Invalid Broken Morale (Maximum)

print "\nInvalid (greater than maximum) broken morale parameter:"

try:
    squad = Squad(nationality,unitType,4,6,7,17,0,10,3,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

# Invalid Basic Point Value (BPV)

print "\nInvalid (less than zero) Basic Point Value (BPV):"

try:
    squad = Squad(nationality,unitType,4,6,7,7,0,-1,3,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

# Invalid Experience Level Rating (Minimum)

print "\nInvalid (less than zero) Experience Level Rating (ELR):"

try:
    squad = Squad(nationality,unitType,4,6,7,7,0,10,-1,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

# Invalid Experience Level Rating (Maximum)

print "\nInvalid (greater than maximum) Experience Level Rating (ELR):"

try:
    squad = Squad(nationality,unitType,4,6,7,7,0,10,6,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

nationality    = Nationalities_valueOf(cc2js("ITALIAN"))
classification = Classifications_valueOf(cc2js("SS"))

# Incompatible Classification

print "\nIncompatible classification parameter:"

try:
    squad = Squad(nationality,unitType,4,6,7,7,0,10,3,0,classification,1,0,0)
except ValueError as detail:
    printException(detail)

nationality    = Nationalities_valueOf(cc2js("GERMAN"))
classification = Classifications_valueOf(cc2js("SECOND_LINE"))

# Invalid Smoke Placement Exponent (Minimum)

print "\nInvalid (less than zero) Smoke Placement Exponent:"

try:
    squad = Squad(nationality,unitType,4,6,7,7,0,10,3,0,classification,1,0,-4)
except ValueError as detail:
    printException(detail)

# Invalid Smoke Placement Exponent (Maximum)

print "\nInvalid (greater than maximum) Smoke Placement Exponent:"

try:
    squad = Squad(nationality,unitType,4,6,7,7,0,10,3,0,classification,1,0,4)
except ValueError as detail:
    printException(detail)

# Create an instance of a Canadian Leader (that throws an exception).
# NOTE: It is only necessary to test the modifier, since all the other
#       exceptions have been tested above as part of the creation of a Squad.

print "\nTesting Exception handling during Leader creation:"

nationality = Nationalities_valueOf(cc2js("BRITISH"))
unitType    = InfantryTypes_valueOf(cc2js("CANADIAN"))

# Invalid Modifier (Minimum)

print "\nInvalid (less than minimum) modifier parameter:"

try:
    leader = Leader(nationality,unitType,10,10,5,-4)
except ValueError as detail:
    printException(detail)

# Invalid Modifier (Maximum)

print "\nInvalid (greater than maximum) modifier parameter:"

try:
    leader = Leader(nationality,unitType,10,10,5,4)
except ValueError as detail:
    printException(detail)

# Test the Dice class.

print "\nTesting the execution of the Dice class:\n"

for i in (list(range(12))):
    dice = Dice()

#   print "Access methods test - White: %d" % dice.whiteDieValue() + \
#         " Colored: %d"  % dice.coloredDieValue() + \
#         " Combined: %2d" % dice.combinedResult()

    print "%s" % (js2cc(dice.toString()))

# Test the Game class.

print "Testing the operations of the Game class:";

allies           = Sides_valueOf(cc2js("ALLIES"))
nationality      = Nationalities_valueOf(cc2js("AMERICAN"))
alliedPlayerName = cc2js("Pixie")

game = Game_game();

game.addPlayer(allies,alliedPlayerName,nationality,1)

axis           = Sides_valueOf(cc2js("AXIS"))
nationality    = Nationalities_valueOf(cc2js("GERMAN"))
axisPlayerName = cc2js("Buddy")

game.addPlayer(axis,axisPlayerName,nationality,1)

alliedPlayer = game.player(allies,alliedPlayerName)

leader = cc2js("9-1 Leader")
squad  = cc2js("7-4-7 Squad")

alliedPlayer.addUnit(leader);
alliedPlayer.addUnit(squad);
alliedPlayer.addUnit(squad);
alliedPlayer.addUnit(squad);

axisPlayer = game.player(axis,axisPlayerName)

leader = cc2js("8-1 Leader")
squad  = cc2js("6-5-8 Squad")

axisPlayer.addUnit(leader);
axisPlayer.addUnit(squad);
axisPlayer.addUnit(squad);
axisPlayer.addUnit(squad);

print "\n%s" % js2cc(game.toText());
