#!/usr/bin/perl
################################################################################
# Driver.pl - This file contains the Driver script, which is used to test the  #
#             functionality of the public <A HREF="http://java.sun.com/">Java</A> classes defined in the <A HREF="jasl/jasl.html">jasl</A>     #
#             package hierarchy, accessed by <A HREF="http://www.perl.org/">Perl</A> using <A HREF="swig/swig.html">libraries</A> and files    #
#             generated by <A HREF="http://www.swig.org/">SWIG</A>.                                               #
#                                                                              #
#             The <A HREF="http://gcc.gnu.org/onlinedocs/gcj/About-CNI.html#About-CNI">CNI</A> (Compiled Native Interface) <A HREF="http://gcc.gnu.org/onlinedocs/gcj/Invocation.html#Invocation">invocation</A> functions, which  #
#             are required to use the Java classes, are initiated in this      #
#             script by the <A HREF="swig/CniWrapper.swig.html">CniWrapper</A> module. This module also provides       #
#             methods to convert a native string to or from a Java <A HREF="http://java.sun.com/javase/7/docs/api/java/lang/String.html">String</A>.     #
#                                                                              #
# Written By: Craig R. Campbell  -  August 2008                                #
################################################################################

#$PERLLIB_INC = "/home/campbell/java/jasl/lib";
#unshift(@INC,$PERLLIB_INC);

#use diagnostics;
#use diagnostics -verbose;
use strict;
use warnings;

use CniWrapper;
use Counters;
use UiData;
use Utilities;

# The following call is necessary only if either the js2cc() or cc2js() function
# is NOT called (invoking either one will start the CniWrapper).

#my $cni_wrapper = CniWrapper::CniWrapper::instance();

# A call to the valueOf() method of each of the following enums is necessary in
# order to use the constants associated with the type/object directly.

my $description = Counters::Descriptions::valueOf(CniWrapper::cc2js("LEADER"));
my $nationality = Counters::Nationalities::valueOf(CniWrapper::cc2js("AMERICAN"));
my $unitType = Counters::InfantryTypes::valueOf(CniWrapper::cc2js("NONE"));
my $classification = Counters::Classifications::valueOf(CniWrapper::cc2js("GREEN"));
my $brokenState = Counters::States::valueOf(CniWrapper::cc2js("BROKEN"));
my $desperateState = Counters::States::valueOf(CniWrapper::cc2js("DESPERATE"));

# Create an instance of a German Leader.

my $germanLeader = new Counters::Leader($Counters::Nationalities_GERMAN,
                                        $Counters::InfantryTypes_NONE,
                                        9,9,4,-1);

$germanLeader->setStatus($brokenState);
$germanLeader->setPortageLevel(2);

# (Silently) verify that the status that was just set is not (successfully) set
# again (i.e. it worked the first time).

if ($germanLeader->setStatus($brokenState))
{
    die "Status changed when existing state specified again!";
}

# Display all of the entered values for this instance using the toText() method.

printf("\nLeader.toText() output:\n\n%s\n",
       CniWrapper::js2cc($germanLeader->toText()));

# Display an abbreviated description of this instance using the toString()
# method.

printf("Leader.toString() output:\n\n%s\n",
       CniWrapper::js2cc($germanLeader->toString()));

# Display the output of all of the access methods declared for the Leader class
# using the instance created above.

#printf("Leader class access methods and output :\n");

#printf("\n\tdescription() - name: %s\tlabel: %s\n",
#       CniWrapper::js2cc($germanLeader->description()->name()),
#       CniWrapper::js2cc($germanLeader->description()->toString()));

#printf("\tidentity(): %s\n",
#       CniWrapper::js2cc($germanLeader->identity()));
#printf("\tnationality() - name: %s\tlabel: %s\n",
#       CniWrapper::js2cc($germanLeader->nationality()->name()),
#       CniWrapper::js2cc($germanLeader->nationality()->toString()));
#my @statusList = @{$germanLeader->status()};
#printf("\tstatus() - name: %s label: %s\n",
#       CniWrapper::js2cc($statusList[0]->name()),
#       CniWrapper::js2cc($statusList[0]->toString()));
#printf("\tunitType(): %s\n",
#       CniWrapper::js2cc($germanLeader->unitType()));

#printf("\tmovement(): %d\n",$germanLeader->movement());
#printf("\tportageCapacity(): %d\n",$germanLeader->portageCapacity());
#printf("\tportageLevel(): %d\n",$germanLeader->portageLevel());

#printf("\tbasicPointValue(): %d\n",$germanLeader->basicPointValue());
#printf("\tbrokenMorale(): %d\n",$germanLeader->brokenMorale());
#printf("\tcanSelfRally(): %d\n",$germanLeader->canSelfRally());
#printf("\texperienceLevelRating(): %d\n",
#       $germanLeader->experienceLevelRating());
#printf("\tfirepower(): %s\n",
#       CniWrapper::js2cc($germanLeader->firepower()));
#printf("\tfirepowerEquivalent(): %d\n",$germanLeader->firepowerEquivalent());
#printf("\tinfantryType() - name: %s\tlabel: %s\n",
#       CniWrapper::js2cc($germanLeader->infantryType()->name()),
#       CniWrapper::js2cc($germanLeader->infantryType()->toString()));
#printf("\tmorale(): %d\n",$germanLeader->morale());
#printf("\tnormalRange(): %d\n",$germanLeader->normalRange());
#printf("\tportageValue(): %d\n",$germanLeader->portageValue());

#printf("\tmodifier(): %d\n\n",$germanLeader->modifier());

# Test the exception handling within the Serialization class, specifically the
# methods associated with serializing to and deserializing from a file.

printf("\nTesting Exception handling for serialization to and from a file:\n");

my $serializationFile = CniWrapper::cc2js("");

my $status = eval
{
    Utilities::Serialization::serializeToFile(undef,$serializationFile);
};

printException($@) if (!defined($status));

$status = eval
{
    Utilities::Serialization::serializeToFile(Counters::toObject($germanLeader),
                                              $serializationFile);
};

printException($@) if (!defined($status));

my $deserializedLeader = undef;

$status = eval
{
    $deserializedLeader =
        Utilities::Serialization::deserializeFromFile(undef);
};

printException($@) if (!defined($status));

$status = eval
{
     $deserializedLeader =
         Utilities::Serialization::deserializeFromFile($serializationFile);
};

printException($@) if (!defined($status));

$status = eval
{
     $deserializedLeader =
         Utilities::Serialization::deserializeFromFile(CniWrapper::cc2js("/tmp/NonExistentFile"));
};

printException($@) if (!defined($status));

# Serialize the Leader object, write the data to a file (Leader.ser), then
# deserialize the data into a new object.

$germanLeader->setIdentity(CniWrapper::cc2js("Col. Klink"));

$serializationFile = CniWrapper::cc2js("/tmp/Leader.ser");

$status = eval
{
    Utilities::Serialization::serializeToFile(Counters::toObject($germanLeader),
                                              $serializationFile);
};

printException($@) if (!defined($status)); # Not expected.

$deserializedLeader = undef;

$status = eval
{
    $deserializedLeader =
        Counters::unitToLeader(Counters::fromObject(Utilities::Serialization::deserializeFromFile($serializationFile)));
};

printException($@) if (!defined($status)); # Not expected.

# Retrieve the leader's status and then use the value to restore to "normal".

my @statusList = @{$deserializedLeader->status()};

$deserializedLeader->clearStatus($statusList[0]);

# Display all of the entered values for the deserialized instance using the
# toText() method.

printf("(Deserialized) Leader.toText() output:\n\n%s\n",
       CniWrapper::js2cc($deserializedLeader->toText()));

# Display an abbreviated description of the deserialized instance using the
# toString() method.

printf("(Deserialized) Leader.toString() output:\n\n%s\n\n",
       CniWrapper::js2cc($deserializedLeader->toString()));

# Display all of the entered values for the deserialized instance using the
# toJSON() method.

printf("(Deserialized) Leader.toJSON() output:\n\n%s\n\n",
       CniWrapper::js2cc($deserializedLeader->toJSON()));

# Create an instance of a Russian Squad.

my $russianSquad = new Counters::Squad($Counters::Nationalities::RUSSIAN,
                                       $Counters::InfantryTypes::GUARDS,
                                       6,2,8,8,0,12,4,0,
                                       $Counters::Classifications::ELITE,1,1,0);

$russianSquad->setStatus($desperateState);

# Display all of the entered values for this instance using the toText() method.

printf("Squad.toText() output:\n\n%s\n",
       CniWrapper::js2cc($russianSquad->toText()));

# Display an abbreviated description of this instance using the toString()
# method.

printf("Squad.toString() output:\n\n%s\n\n",
       CniWrapper::js2cc($russianSquad->toString()));

# Display the output of all of the access methods declared for the Squad class
# using the instance created above.

#printf("Squad class access methods and output :\n");

#printf("\n\tdescription() - name: %s\tlabel: %s\n",
#       CniWrapper::js2cc($russianSquad->description()->name()),
#       CniWrapper::js2cc($russianSquad->description()->toString()));

#printf("\tidentity(): %s\n",
#       CniWrapper::js2cc($russianSquad->identity()));
#printf("\tnationality() - name: %s\tlabel: %s\n",
#       CniWrapper::js2cc($russianSquad->nationality()->name()),
#       CniWrapper::js2cc($russianSquad->nationality()->toString()));
#my @statusList = @{$russianSquad->status()};
#printf("\tstatus() - name: %s label: %s\n",
#       CniWrapper::js2cc($statusList[0]->name()),
#       CniWrapper::js2cc($statusList[0]->toString()));
#printf("\tunitType(): %s\n",
#       CniWrapper::js2cc($russianSquad->unitType()));

#printf("\tmovement(): %d\n",$russianSquad->movement());
#printf("\tportageCapacity(): %d\n",$russianSquad->portageCapacity());
#printf("\tportageLevel(): %d\n",$russianSquad->portageLevel());

#printf("\tbasicPointValue(): %d\n",$russianSquad->basicPointValue());
#printf("\tbrokenMorale(): %d\n",$russianSquad->brokenMorale());
#printf("\tcanSelfRally(): %d\n",$russianSquad->canSelfRally());
#printf("\texperienceLevelRating(): %d\n",
#       $russianSquad->experienceLevelRating());
#printf("\tfirepower(): %s\n",
#       CniWrapper::js2cc($russianSquad->firepower()));
#printf("\tfirepowerEquivalent(): %d\n",$russianSquad->firepowerEquivalent());
#printf("\tinfantryType() - name: %s\tlabel: %s\n",
#       CniWrapper::js2cc($russianSquad->infantryType()->name()),
#       CniWrapper::js2cc($russianSquad->infantryType()->toString()));
#printf("\tmorale(): %d\n",$russianSquad->morale());
#printf("\tnormalRange(): %d\n",$russianSquad->normalRange());
#printf("\tportageValue(): %d\n",$russianSquad->portageValue());

#printf("\tclassification() - name: %s\tlabel: %s\n",
#       CniWrapper::js2cc($russianSquad->classification()->name()),
#       CniWrapper::js2cc($russianSquad->classification()->toString()));
#printf("\thasMaximumELR(): %d\n",$russianSquad->hasMaximumELR());
#printf("\tcanAssaultFire(): %d\n",$russianSquad->canAssaultFire());
#printf("\tcanSprayFire(): %d\n",$russianSquad->canSprayFire());
#printf("\tsmokePlacementExponent(): %d\n",
#       $russianSquad->smokePlacementExponent());

# Test the exception handling within the Serialization class, specifically the
# methods associated with serializing to and deserializing from a byte array.

printf("Testing Exception handling for serialization to and from a byte array:\n");

$status = eval
{
    Utilities::Serialization::serializeToByteArray(undef);
};

printException($@) if (!defined($status));

my $deserializedSquad = undef;

$status = eval
{
    $deserializedSquad =
        Utilities::Serialization::deserializeFromByteArray(undef);
};

printException($@) if (!defined($status));

# Serialize the Squad object, writing the data to a byte array, and then
# deserialize the data into a new object.

$russianSquad->setIdentity(CniWrapper::cc2js("A"));

my $serializedSquad = undef;

$status = eval
{
    $serializedSquad =
        Utilities::Serialization::serializeToByteArray(Counters::toObject($russianSquad));
};

printException($@) if (!defined($status)); # Not expected.

$deserializedSquad = undef;

$status = eval
{
    $deserializedSquad =
        Counters::unitToSquad(Counters::fromObject(Utilities::Serialization::deserializeFromByteArray($serializedSquad)));
};

printException($@) if (!defined($status)); # Not expected.

# (Silently) verify that if a Unit is subject to desperation morale, it's broken
# status can't be (underhandedly) removed.

if ($deserializedSquad->clearStatus($brokenState))
{
    die "Broken status cleared when subject to desperation morale!";
}

# Retrieve the squad's status and then use the value to "reduce" it to "broken".

@statusList = @{$deserializedSquad->status()};

$deserializedSquad->clearStatus($statusList[0]);

# (Silently) verify that the status that was just cleared is not (successfully)
# cleared again (i.e. it worked the first time).

if ($deserializedSquad->clearStatus($statusList[0]))
{
    die "Status cleared when previous state specified again!";
}

# Display all of the entered values for the deserialized instance using the
# toText() method.

printf("\n(Deserialized) Squad.toText() output:\n\n%s\n",
       CniWrapper::js2cc($deserializedSquad->toText()));

# Display an abbreviated description of the deserialized instance using the
# toString() method.

printf("(Deserialized) Squad.toString() output:\n\n%s\n\n",
       CniWrapper::js2cc($deserializedSquad->toString()));

# Display all of the entered values for the deserialized instance using the
# toJSON() method.

printf("(Deserialized) Squad.toJSON() output:\n\n%s\n\n",
       CniWrapper::js2cc($deserializedSquad->toJSON()));

# Create an array of Unit objects. These will be used to reference a Leader
# instance and several Squad instances. These class types are derived from Unit.

printf("Building Unit array with a Leader & 3 Squads\n");

my @unitList = ();

$nationality    = $Counters::Nationalities::AMERICAN;
$unitType       = $Counters::InfantryTypes::NONE;
$classification = $Counters::Classifications::FIRST_LINE;

push @unitList,new Counters::Leader($nationality,$unitType,9,9,4,-1);

$unitList[0]->setIdentity(CniWrapper::cc2js("Sgt. Slaughter"));

push @unitList,new Counters::Squad($nationality,$unitType,
                                   6,6,6,6,0,11,4,0,$classification,1,1,0);
push @unitList,new Counters::Squad($nationality,$unitType,
                                   6,6,6,6,0,11,4,0,$classification,1,1,0);
push @unitList,new Counters::Squad($nationality,$unitType,
                                   6,6,6,6,0,11,4,0,$classification,1,1,0);

$unitList[1]->setIdentity(CniWrapper::cc2js("X"));
$unitList[1]->setStatus($brokenState);
$unitList[2]->setIdentity(CniWrapper::cc2js("Y"));
$unitList[2]->setStatus($desperateState);
$unitList[3]->setIdentity(CniWrapper::cc2js("Z"));

printf("\nDisplaying Unit array with a Leader & 3 Squads\n");

my $unitIndex = 0;

foreach my $unit (@unitList)
{
    my @statusList   = @{$unit->status()};
    my $statusString = "";

    # Note that this would not be a good solution if the list was expected to
    # contain more than one entry, but it works here for testing purposes.

    foreach (@statusList)
    {
        $statusString = CniWrapper::js2cc($_->toString());
    }

    printf("\nUnitList[%d]:\t%s\n",
           $unitIndex++,CniWrapper::js2cc($unit->toString()));

    printf("\n%s\n%s\n%s\n%d\n[%s]\n",
           CniWrapper::js2cc($unit->description()->toString()),
           CniWrapper::js2cc($unit->identity()),
           CniWrapper::js2cc($unit->unitType()),
           $unit->movement(),$statusString);
}

# Create an instance of a German Squad (that throws some exceptions).

printf("\nTesting Exception handling for Squad update methods:\n");

$nationality    = $Counters::Nationalities::GERMAN;
$unitType       = $Counters::InfantryTypes::NONE;

my $squad = new Counters::Squad($nationality,$unitType,4,6,7,7,0,10,3,0,
                                $classification,1,0,0);

# Null Identity (no error, just clears the existing one).

$squad->setIdentity(undef);

# Blank Identity (no error, just clears the existing one).

$squad->setIdentity(CniWrapper::cc2js(""));

# Invalid portage level

printf("\nInvalid portage level parameter:\n");

$status = eval
{
    $squad->setPortageLevel(-1);
};

printException($@) if (!defined($status));

printf("\nTesting Exception handling during Squad creation:\n");

$nationality    = $Counters::Nationalities::BRITISH;
$unitType       = $Counters::InfantryTypes::ENGINEERS;
$classification = $Counters::Classifications::FIRST_LINE;

# Incompatible nationality and unitType

printf("\nIncompatible nationality and unitType parameters:\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,6,7,7,0,10,3,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

$nationality    = $Counters::Nationalities::RUSSIAN;
$unitType       = $Counters::InfantryTypes::COMMISSAR;
$classification = $Counters::Classifications::GREEN;

# Incompatible description and unitType

printf("\nIncompatible description and unitType parameters:\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,4,7,7,0,10,3,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

$nationality    = $Counters::Nationalities::GERMAN;
$unitType       = $Counters::InfantryTypes::NONE;
$classification = $Counters::Classifications::FIRST_LINE;

# Invalid Firepower

printf("\nInvalid (less than 0) firepower parameter:\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,-1,6,7,7,0,10,3,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

printf("\nInvalid (greater than maximum) firepower parameter:\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,11,6,7,7,0,10,3,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

# Invalid Range

printf("\nInvalid (less than 0) normal range parameter:\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,-255,7,7,0,10,3,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

# Invalid Morale (Minimum)

printf("\nInvalid (less than 0) morale parameter:\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,6,-1,7,0,10,3,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

# Invalid Morale (Maximum)

printf("\nInvalid (greater than maximum) morale parameter:\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,6,11,7,0,10,3,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

# Invalid Broken Morale (Minimum)

printf("\nInvalid (less than 0) broken morale parameter:\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,6,7,-7,0,10,3,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

# Invalid Broken Morale (Maximum)

printf("\nInvalid (greater than maximum) broken morale parameter:\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,6,7,17,0,10,3,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

# Invalid Basic Point Value (BPV)

printf("\nInvalid (less than zero) Basic Point Value (BPV):\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,6,7,7,0,-1,3,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

# Invalid Experience Level Rating (Minimum)

printf("\nInvalid (less than zero) Experience Level Rating (ELR):\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,6,7,7,0,10,-1,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

# Invalid Experience Level Rating (Maximum)

printf("\nInvalid (greater than maximum) Experience Level Rating (ELR):\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,6,7,7,0,10,6,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

$nationality    = $Counters::Nationalities::ITALIAN;
$classification = $Counters::Classifications::SS;

# Incompatible Classification

printf("\nIncompatible classification parameter:\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,6,7,7,0,10,3,0,
                                 $classification,1,0,0);
};

printException($@) if (!defined($status));

$nationality    = $Counters::Nationalities::GERMAN;
$classification = $Counters::Classifications::SECOND_LINE;

# Invalid Smoke Placement Exponent (Minimum)

printf("\nInvalid (less than zero) Smoke Placement Exponent:\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,6,7,7,0,10,3,0,
                                 $classification,1,0,-4);
};

printException($@) if (!defined($status));

# Invalid Smoke Placement Exponent (Maximum)

printf("\nInvalid (greater than maximum) Smoke Placement Exponent:\n");

$status = eval
{
    $squad = new Counters::Squad($nationality,$unitType,4,6,7,7,0,10,3,0,
                                 $classification,1,0,4);
};

printException($@) if (!defined($status));

# Create an instance of a Canadian Leader (that throws an exception).
# NOTE: It is only necessary to test the modifier, since all the other
#       exceptions have been tested above as part of the creation of a Squad.

printf("\nTesting Exception handling during Leader creation:\n");

$nationality    = $Counters::Nationalities::BRITISH;
$unitType       = $Counters::InfantryTypes::CANADIAN;

# Invalid Modifier (Minimum)

printf("\nInvalid (less than minimum) modifier parameter:\n");

$status = eval
{
    my $leader = new Counters::Leader($nationality,$unitType,10,10,5,-4);
};

printException($@) if (!defined($status));

# Invalid Modifier (Maximum)

printf("\nInvalid (greater than maximum) modifier parameter:\n");

$status = eval
{
    my $leader = new Counters::Leader($nationality,$unitType,10,10,5,4);
};

printException($@) if (!defined($status));

# Test the Dice class.

printf("\nTesting the execution of the Dice class:\n\n");

for (my $i = 0;$i < 12;$i++)
{
    my $theDice = new Utilities::Dice();

#   printf("Access methods test - White: %d Colored: %d Combined: %2d\n",
#          $theDice->whiteDieValue(),
#          $theDice->coloredDieValue(),
#          $theDice->combinedResult());

    printf("%s\n",CniWrapper::js2cc($theDice->toText()));
}

# Test the Game class.

printf("Testing the operations of the Game class:\n");

my $allies           = UiData::Sides::valueOf(CniWrapper::cc2js("ALLIES"));
$nationality         = $Counters::Nationalities::AMERICAN;
my $alliedPlayerName = CniWrapper::cc2js("Pixie");

my $game = UiData::Game::game();

$game->addPlayer($allies,$alliedPlayerName,$nationality,1);

my $axis           = $UiData::Sides::AXIS;
$nationality       = $Counters::Nationalities::GERMAN;
my $axisPlayerName = CniWrapper::cc2js("Buddy");

$game->addPlayer($axis,$axisPlayerName,$nationality,1);

my $alliedPlayer = $game->player($allies,$alliedPlayerName);

my $leader = CniWrapper::cc2js("9-1 Leader");
$squad     = CniWrapper::cc2js("7-4-7 Squad");

$alliedPlayer->addUnit($leader);
$alliedPlayer->addUnit($squad);
$alliedPlayer->addUnit($squad);
$alliedPlayer->addUnit($squad);

my $axisPlayer = $game->player($axis,$axisPlayerName);

$leader = CniWrapper::cc2js("8-1 Leader");
$squad  = CniWrapper::cc2js("6-5-8 Squad");

$axisPlayer->addUnit($leader);
$axisPlayer->addUnit($squad);
$axisPlayer->addUnit($squad);
$axisPlayer->addUnit($squad);

printf("\n%s\n",CniWrapper::js2cc($game->toText()));

################################################################################
# printException - a subroutine to do just what its name says. In this case, it
#                  is used to modify an exception message to match the output of
#                  the other test programs.
################################################################################

sub printException
{
    my $inputString = shift;

    $inputString =~ s/ValueError/Caught:/;
    $inputString =~ s/ at .*\.pm line \d+\.//;
    $inputString =~ s/ at .\/Driver line \d+\.//;

    printf("\n%s",$inputString);
}
