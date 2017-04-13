// ************************************************************************** //
// Driver.cpp - This file contains the Driver program, which is used to test  //
//              the functionality of the Java classes defined in the jasl     //
//              package hierarchy, accessed as a library generated using <A HREF="http://gcc.gnu.org/java/">GCJ</A>, //
//              through the <A HREF="http://gcc.gnu.org/onlinedocs/gcj/About-CNI.html#About-CNI">CNI</A> (Compiled Native Interface).                  //
//                                                                            //
//              NOTE: This program is based on Advanced Squad Leader, which   //
//                    was created by The Avalon Hill Game Company, and lives  //
//                    on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                           //
//                                                                            //
// Written By: Craig R. Campbell  -  April 2007                               //
// ************************************************************************** //


#include "jasl/cni/CniWrapper.h"
#include "jasl/cni/JaslErrorMessage.h"
#include "jasl/cni/jaslWrapper.h"

#include "jasl/counters/Mobile.h"

#include <java/lang/Throwable.h>
#include <java/util/List.h>

#include <assert.h>
#include <stdio.h>

typedef jasl::counters::Mobile Mobile;

static void printJavaString(jstring javaString)
{
    const char* convertedString = js2cc(javaString);

    if (convertedString)
    {
        printf("%s\n",convertedString);
        delete [] convertedString;
    }
}

static void printExceptionMessage(jthrowable exception)
{
    if (exception)
    {
        JaslErrorMessage jaslErrorMessage(exception);
        printf("\nCaught: %s\n",jaslErrorMessage.text());
    }
}

int main(int argc, char *argv[])
{
    // This is only necessary if an attempt is made to create an object BEFORE a
    // a call is made that starts the CniWrapper (e.g. a call to cc2js()).

//  CniWrapper* cniWrapper = CniWrapper::instance();

    try
    {
        // These items are used to set parameters that require conversion /
        // initialization from a (static) enum value.

        Nationalities*   nationality    = Nationalities::valueOf(cc2js("GERMAN"));
        InfantryTypes*   unitType       = InfantryTypes::valueOf(cc2js("NONE"));
        Classifications* classification = Classifications::valueOf(cc2js("FIRST_LINE"));
        States*          brokenState    = States::valueOf(cc2js("BROKEN"));
        States*          desperateState = States::valueOf(cc2js("DESPERATE"));

        // Create an instance of a German Leader.

        Leader* germanLeader = new Leader(nationality,unitType,9,9,4,-1);

        assert(germanLeader);

        germanLeader->setStatus(brokenState);
        germanLeader->setPortageLevel(2);

        // (Silently) verify that the status that was just set is not
        // (successfully) set again (i.e. it worked the first time).

        assert(!germanLeader->setStatus(brokenState));

        // Display all of the entered values for this instance using the
        // toText() method.

        printf("\nLeader.toText() output:\n\n");
        printJavaString(germanLeader->toText());

        // Display an abbreviated description of this instance using the
        // toString() method.

        printf("Leader.toString() output:\n\n");
        printJavaString(germanLeader->toString());

        // Test the exception handling within the Serialization class,
        // specifically the methods associated with serializing to and
        // deserializing from a file.

        printf("\nTesting Exception handling for serialization to and from a file:\n");

        jstring serializationFile = cc2js("");

        try
        {
            Serialization::serializeToFile(NULL,serializationFile);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        try
        {
            Serialization::serializeToFile(germanLeader,serializationFile);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        Unit* deserializedLeader = NULL;

        try
        {
            deserializedLeader =
                (Unit*)Serialization::deserializeFromFile(NULL);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        try
        {
             deserializedLeader =
                 (Unit*)Serialization::deserializeFromFile(serializationFile);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        try
        {
             deserializedLeader =
                 (Unit*)Serialization::deserializeFromFile(cc2js("/tmp/NonExistentFile"));
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Serialize the Leader object, write the data to a file (Leader.ser),
        // then deserialize the data into a new object.

        germanLeader->setIdentity(cc2js("Col. Klink"));

        serializationFile = cc2js("/tmp/Leader.ser");

        try
        {
            Serialization::serializeToFile(germanLeader,serializationFile);
        }

        catch (jthrowable t) // Not expected.
        {
            printExceptionMessage(t);
        }

        try
        {
            deserializedLeader =
                (Unit*)Serialization::deserializeFromFile(serializationFile);
        }

        catch (jthrowable t) // Not expected.
        {
            printExceptionMessage(t);
        }

        // Retrieve the leader's status and then use the value to restore to
        // "normal".

        ::java::util::List* statusList =
            static_cast<Leader*>(deserializedLeader)->status();

        static_cast<Leader*>(deserializedLeader)->clearStatus(static_cast<States*>(statusList->get(0)));

        // Display all of the entered values for the deserialized instance using
        // the toText() method.

        printf("\n(Deserialized) Leader.toText() output:\n\n");
        printJavaString(deserializedLeader->toText());

        // Display an abbreviated description of the deserialized instance using
        // the toString() method.

        printf("(Deserialized) Leader.toString() output:\n\n");
        printJavaString(deserializedLeader->toString());

        // Display all of the entered values for the deserialized instance using
        // the toJSON() method.

        printf("\n(Deserialized) Leader.toJSON() output:\n\n");
        printJavaString(deserializedLeader->toJSON());

        // Create an instance of a Russian Squad.

        nationality    = Nationalities::valueOf(cc2js("RUSSIAN"));
        unitType       = InfantryTypes::valueOf(cc2js("GUARDS"));
        classification = Classifications::valueOf(cc2js("ELITE"));

        Squad* russianSquad = new Squad(nationality,unitType,
                                        6,2,8,8,false,12,4,false,
                                        classification,true,true,0);

        // Display all of the entered values for this instance using the
        // toText() method.

        assert(russianSquad);

        russianSquad->setStatus(desperateState);

        printf("\nSquad.toText() output:\n\n");
        printJavaString(russianSquad->toText());

        // Display an abbreviated description of this instance using the
        // toString() method.

        printf("Squad.toString() output:\n\n");
        printJavaString(russianSquad->toString());

        // Test the exception handling within the Serialization class,
        // specifically the methods associated with serializing to and
        // deserializing from a byte array.

        printf("\nTesting Exception handling for serialization to and from a byte array:\n");

        try
        {
            Serialization::serializeToByteArray(NULL);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        Unit* deserializedSquad = NULL;

        try
        {
            deserializedSquad =
                (Unit*)Serialization::deserializeFromByteArray(NULL);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Serialize the Squad object, writing the data to a byte array, and
        // then deserialize the data into a new object.

        russianSquad->setIdentity(cc2js("A"));

        JArray<jbyte>* serializedSquad = NULL;

        try
        {
            serializedSquad =
                Serialization::serializeToByteArray(russianSquad);
        }

        catch (jthrowable t) // Not expected.
        {
            printExceptionMessage(t);
        }

        try
        {
            deserializedSquad =
                (Unit*)Serialization::deserializeFromByteArray(serializedSquad);
        }

        catch (jthrowable t) // Not expected.
        {
            printExceptionMessage(t);
        }

        // (Silently) verify that if a Unit is subject to desperation morale,
        // it's broken status can't be (underhandedly) removed.

        assert(!(static_cast<Squad*>(deserializedSquad)->clearStatus(brokenState)));

        // Retrieve the squad's status and then use the value to "reduce" it to
        // "broken".

        statusList = static_cast<Squad*>(deserializedSquad)->status();

        static_cast<Squad*>(deserializedSquad)->clearStatus(static_cast<States*>(statusList->get(0)));

        // (Silently) verify that the status that was just cleared is not
        // (successfully) cleared again (i.e. it worked the first time).

        assert(!static_cast<Squad*>(deserializedSquad)->clearStatus(static_cast<States*>(statusList->get(0))));

        // Display all of the entered values for the deserialized instance using
        // the toText() method.

        printf("\n(Deserialized) Squad.toText() output:\n\n");
        printJavaString(deserializedSquad->toText());

        // Display an abbreviated description of the deserialized instance using
        // the toString() method.

        printf("(Deserialized) Squad.toString() output:\n\n");
        printJavaString(deserializedSquad->toString());

        // Display an abbreviated description of this instance using the
        // toJSON() method.

        printf("\n(Deserialized) Squad.toJSON() output:\n\n");
        printJavaString(deserializedSquad->toJSON());

        // Create an array of Unit objects. These will be used to reference a
        // Leader instance and several Squad instances. These class types are
        // derived from Unit.

        printf("\nBuilding Unit array with a Leader & 3 Squads\n\n");

        // The following commented code demonstrates, based on the CNI
        // documentation, the correct/preferred way to create <A HREF="http://gcc.gnu.org/onlinedocs/gcj/Arrays.html#Arrays">arrays</A> of pointers
        // to Java objects.
/*
         using namespace java::lang;
         JArray<Unit*>* UnitArray = (JArray<Unit*>*)JvNewObjectArray(4,&Unit::class$,NULL);

        Unit** UnitList = elements(UnitArray);
*/
        // The old fashioned way seems to work as well, at least in this case.
        // Note, however, that when the array is created this way, it should be
        // explicitly deleted (see below). The freeing of the memory associated
        // with the individual objects is handled by the JVM garbage collector.

        Unit** UnitList = new Unit*[4];

        nationality    = Nationalities::valueOf(cc2js("AMERICAN"));
        unitType       = InfantryTypes::valueOf(cc2js("NONE"));
        classification = Classifications::valueOf(cc2js("FIRST_LINE"));

        UnitList[0] = new Leader(nationality,unitType,9,9,4,-1);

        ((Leader*)UnitList[0])->setIdentity(cc2js("Sgt. Slaughter"));

        UnitList[1] = new Squad(nationality,unitType,6,6,6,6,false,11,4,false,
                                classification,true,false,3);

        ((Squad*)UnitList[1])->setIdentity(cc2js("X"));
        ((Squad*)UnitList[1])->setStatus(brokenState);

        UnitList[2] = new Squad(nationality,unitType,6,6,6,6,false,11,4,false,
                                classification,true,false,3);

        ((Squad*)UnitList[2])->setIdentity(cc2js("Y"));
        ((Squad*)UnitList[2])->setStatus(desperateState);

        UnitList[3] = new Squad(nationality,unitType,6,6,6,6,false,11,4,false,
                                classification,true,false,3);

        ((Squad*)UnitList[3])->setIdentity(cc2js("Z"));

        printf("Displaying Unit array with a Leader & 3 Squads\n");

        for (int i = 0; i < 4; i++)
        {
            assert(UnitList[i]);

            const char* toStringOutput = js2cc(UnitList[i]->toString());

            printf("\nUnitList[%d]:\t%s\n\n",i,toStringOutput);

            delete [] toStringOutput;

            printJavaString(((Mobile*)UnitList[i])->description()->toString());
            printJavaString(((Mobile*)UnitList[i])->identity());
            printJavaString(((Mobile*)UnitList[i])->unitType());
            printf("%d\n",((Mobile*)UnitList[i])->movement());
            printJavaString(((Mobile*)UnitList[i])->status()->toString());
        }

        // If it was generated the old fashioned way (see above) delete the
        // array of Unit pointers. Note that the individual objects referenced
        // by the array are freed by the Java Virtual Machine's garbage
        // collector.

        delete [] UnitList;

        // Create an instance of a German Squad (that throws some exceptions).

        nationality    = Nationalities::valueOf(cc2js("GERMAN"));
        unitType       = InfantryTypes::valueOf(cc2js("NONE"));
        classification = Classifications::valueOf(cc2js("FIRST_LINE"));

        Squad* squad = new Squad(nationality,unitType,4,6,7,7,false,10,3,false,
                                 classification,true,false,0);

        printf("\nTesting Exception handling for Squad update methods:\n");

        // Null Identity (no error, just clears the existing one).

        squad->setIdentity(NULL);

        // Blank Identity (no error, just clears the existing one).

        squad->setIdentity(cc2js(""));

        // Invalid portage level

        printf("\nInvalid portage level parameter:\n");

        try
        {
            squad->setPortageLevel(-1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        printf("\nTesting Exception handling during Squad creation:\n");

        nationality = Nationalities::valueOf(cc2js("BRITISH"));
        unitType    = InfantryTypes::valueOf(cc2js("ENGINEERS"));

        // Incompatible nationality and unitType

        printf("\nIncompatible nationality and unitType parameters:\n");

        try
        {
            squad = new Squad(nationality,unitType,4,6,7,7,false,10,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        nationality    = Nationalities::valueOf(cc2js("RUSSIAN"));
        unitType       = InfantryTypes::valueOf(cc2js("COMMISSAR"));
        classification = Classifications::valueOf(cc2js("GREEN"));

        // Incompatible description and unitType

        printf("\nIncompatible description and unitType parameters:\n");

        try
        {
            squad = new Squad(nationality,unitType,4,4,7,7,false,10,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        nationality    = Nationalities::valueOf(cc2js("GERMAN"));
        unitType       = InfantryTypes::valueOf(cc2js("NONE"));
        classification = Classifications::valueOf(cc2js("FIRST_LINE"));

        // Invalid Firepower

        printf("\nInvalid (less than 0) firepower parameter:\n");

        try
        {
            squad = new Squad(nationality,unitType,-1,6,7,7,false,10,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        printf("\nInvalid (greater than maximum) firepower parameter:\n");

        try
        {
            squad = new Squad(nationality,unitType,11,6,7,7,false,10,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Range

        printf("\nInvalid (less than 0) normal range parameter:\n");

        try
        {
            squad = new Squad(nationality,unitType,4,-255,7,7,false,10,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Morale (Minimum)

        printf("\nInvalid (less than 0) morale parameter:\n");

        try
        {
            squad = new Squad(nationality,unitType,4,6,-1,7,false,10,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Morale (Maximum)

        printf("\nInvalid (greater than maximum) morale parameter:\n");

        try
        {
            squad = new Squad(nationality,unitType,4,6,11,7,false,10,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Broken Morale (Minimum)

        printf("\nInvalid (less than 0) broken morale parameter:\n");

        try
        {
            squad = new Squad(nationality,unitType,4,6,7,-7,false,10,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Broken Morale (Maximum)

        printf("\nInvalid (greater than maximum) broken morale parameter:\n");

        try
        {
            squad = new Squad(nationality,unitType,4,6,7,17,false,10,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Basic Point Value (BPV)

        printf("\nInvalid (less than zero) Basic Point Value (BPV):\n");

        try
        {
            squad = new Squad(nationality,unitType,4,6,7,7,false,-1,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Experience Level Rating (Minimum)

        printf("\nInvalid (less than zero) Experience Level Rating (ELR):\n");

        try
        {
            squad = new Squad(nationality,unitType,4,6,7,7,false,10,-1,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Experience Level Rating (Maximum)

        printf("\nInvalid (greater than maximum) Experience Level Rating (ELR):\n");

        try
        {
            squad = new Squad(nationality,unitType,4,6,7,7,false,10,6,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        nationality    = Nationalities::valueOf(cc2js("ITALIAN"));
        classification = Classifications::valueOf(cc2js("SS"));

        // Incompatible Classification

        printf("\nIncompatible classification parameter:\n");

        try
        {
            squad = new Squad(nationality,unitType,4,6,7,7,false,10,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        nationality    = Nationalities::valueOf(cc2js("GERMAN"));
        classification = Classifications::valueOf(cc2js("SECOND_LINE"));

        // Invalid Smoke Placement Exponent (Minimum)

        printf("\nInvalid (less than zero) Smoke Placement Exponent:\n");

        try
        {
            squad = new Squad(nationality,unitType,4,6,7,7,false,10,3,false,
                              classification,true,false,-4);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Smoke Placement Exponent (Maximum)

        printf("\nInvalid (greater than maximum) Smoke Placement Exponent:\n");

        try
        {
            squad = new Squad(nationality,unitType,4,6,7,7,false,10,3,false,
                              classification,true,false,4);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Create an instance of a Canadian Leader (that throws an exception).
        // NOTE: It is only necessary to test the modifier, since all the other
        //       exceptions have been tested above as part of the creation of a
        //       Squad.

        printf("\nTesting Exception handling during Leader creation:\n");

        Leader* Grandpa = NULL;

        nationality    = Nationalities::valueOf(cc2js("BRITISH"));
        unitType       = InfantryTypes::valueOf(cc2js("CANADIAN"));

        // Invalid Modifier (Minimum)

        printf("\nInvalid (less than minimum) modifier parameter:\n");

        try
        {
            Grandpa = new Leader(nationality,unitType,10,10,5,-4);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Modifier (Maximum)

        printf("\nInvalid (greater than maximum) modifier parameter:\n");

        try
        {
            Grandpa = new Leader(nationality,unitType,10,10,5,4);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Test the Dice class.

        printf("\nTesting the execution of the Dice class:\n\n");

        Dice*       theDice     = NULL;
        const char* diceResults = NULL;

        for (int i = 0;i < 12;i++)
        {
            theDice = new Dice();

            assert(theDice);

//          printf("White: %d Colored: %d Combined: %2d\n",
//                 theDice->whiteDieValue(),
//                 theDice->coloredDieValue(),
//                 theDice->combinedResult());

            printJavaString(theDice->toText());
        }
    }

    catch (jthrowable t)
    {
        printExceptionMessage(t);
    }

    // Test the Scenario class.

    printf("Testing Exception handling during Scenario creation:\n");

    Scenario* scenario = NULL;

    // Invalid filename (tests the constructor that accepts a String).

    printf("\nInvalid filename:\n");

    try
    {
        scenario =
            new Scenario(cc2js("scenarios/The Guard Counterattack.json"));
    }

    catch (jthrowable t)
    {
        printExceptionMessage(t);
    }

    // Valid resource path / filename and data.

    printf("\nTesting the operations of the Scenario class:");

    try
    {
        scenario =
            new Scenario(cc2js("scenarios/The Guards Counterattack.json"));
    }

    catch (jthrowable t)
    {
        printExceptionMessage(t);
    }

    // Display all of the attributes of the scenario using the toText()
    // method.

    printf("\n\nScenario.toText() output:\n\n");
    printJavaString(scenario->toText());

    // Display an abbreviated description of the scenario (its name) using
    // the toString() method.

    printf("Scenario.toString() output:\n\n");
    printJavaString(scenario->toString());

    // Test the Game class.

    printf("\nTesting the operations of the Game class:\n\n");

    Sides*  alliedSide      = Sides::valueOf(cc2js("ALLIES"));
    jstring pixie           = cc2js("Pixie");
    Nationalities* american = Nationalities::valueOf(cc2js("AMERICAN"));

    Sides*  axisSide      = Sides::valueOf(cc2js("AXIS"));
    jstring buddy         = cc2js("Buddy");
    Nationalities* german = Nationalities::valueOf(cc2js("GERMAN"));

    Game::game()->addPlayer(alliedSide,pixie,american,1);
    Game::game()->addPlayer(axisSide,buddy,german,1);

    Player* alliedPlayer = Game::game()->player(alliedSide,pixie);

    alliedPlayer->addUnit(cc2js("9-1 Leader"));
    alliedPlayer->addUnit(cc2js("7-4-7 Squad"));
    alliedPlayer->addUnit(cc2js("7-4-7 Squad"));
    alliedPlayer->addUnit(cc2js("7-4-7 Squad"));

    Player* axisPlayer = Game::game()->player(axisSide,buddy);

    axisPlayer->addUnit(cc2js("8-1 Leader"));
    axisPlayer->addUnit(cc2js("6-5-8 Squad"));
    axisPlayer->addUnit(cc2js("6-5-8 Squad"));
    axisPlayer->addUnit(cc2js("6-5-8 Squad"));

    printJavaString(Game::game()->toText());

//  delete cniWrapper; // See note about this at the beginning of this function.
}
