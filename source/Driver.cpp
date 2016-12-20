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

#include <stdio.h>

#include <java/lang/Throwable.h> // For jthrowable.

#include "jasl/cni/CniWrapper.h"
#include "jasl/cni/JaslErrorMessage.h"
#include "jasl/cni/jaslWrapper.h"

#include "jasl/counters/Mobile.h"

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

        // Create an instance of a German Leader.

        Leader* germanLeader = new Leader(nationality,unitType,9,9,4,-1);

        // Display all of the entered values for this instance using the
        // toText() method.

        if (germanLeader)
        {
            printf("\nLeader.toText() output:\n\n");
            printJavaString(germanLeader->toText());

            // Display an abbreviated description of this instance using the
            // toString() method.

            printf("Leader.toString() output:\n\n");
            printJavaString(germanLeader->toString());

            // Serialize the Leader object, write the data to a file
            // (Leader.ser), then deserialize the data into a new object.

            germanLeader->setIdentity(cc2js("Col. Klink"));

            jstring serializationFile = cc2js("/tmp/Leader.ser");

            try
            {
                Serialization::serializeToFile(germanLeader,serializationFile);
            }

            catch (jthrowable t) // Not expected.
            {
                printExceptionMessage(t);
            }

            Unit* deserializedLeader = NULL;

            try
            {
                deserializedLeader =
                    (Unit*)Serialization::deserializeFromFile(serializationFile);
            }

            catch (jthrowable t) // Not expected.
            {
                printExceptionMessage(t);
            }

            // Display all of the entered values for the deserialized instance
            // using the toText() method.

            printf("\n(Deserialized) Leader.toText() output:\n\n");
            printJavaString(deserializedLeader->toText());

            // Display an abbreviated description of the deserialized instance
            // using the toString() method.

            printf("(Deserialized) Leader.toString() output:\n\n");
            printJavaString(deserializedLeader->toString());

            // Display all of the entered values for the deserialized instance
            // using the toJSON() method.

            printf("\n(Deserialized) Leader.toJSON() output:\n\n");
            printJavaString(deserializedLeader->toJSON());
        }

        // Create an instance of a Russian Squad.

        nationality    = Nationalities::valueOf(cc2js("RUSSIAN"));
        unitType       = InfantryTypes::valueOf(cc2js("GUARDS"));
        classification = Classifications::valueOf(cc2js("ELITE"));

        Squad* russianSquad = new Squad(nationality,unitType,
                                        6,2,8,8,false,12,4,false,
                                        classification,true,true,0);

        // Display all of the entered values for this instance using the
        // toText() method.

        if (russianSquad)
        {
            printf("\nSquad.toText() output:\n\n");
            printJavaString(russianSquad->toText());

            // Display an abbreviated description of this instance using the
            // toString() method.

            printf("Squad.toString() output:\n\n");
            printJavaString(russianSquad->toString());

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

            Unit* deserializedSquad = NULL;

            try
            {
                deserializedSquad =
                    (Unit*)Serialization::deserializeFromByteArray(serializedSquad);
            }

            catch (jthrowable t) // Not expected.
            {
                printExceptionMessage(t);
            }

            // Display all of the entered values for the deserialized instance
            // using the toText() method.

            printf("\n(Deserialized) Squad.toText() output:\n\n");
            printJavaString(deserializedSquad->toText());

            // Display an abbreviated description of the deserialized instance
            // using the toString() method.

            printf("(Deserialized) Squad.toString() output:\n\n");
            printJavaString(deserializedSquad->toString());

            // Display an abbreviated description of this instance using the
            // toJSON() method.

            printf("\n(Deserialized) Squad.toJSON() output:\n\n");
            printJavaString(deserializedSquad->toJSON());
        }

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

        UnitList[2] = new Squad(nationality,unitType,6,6,6,6,false,11,4,false,
                                classification,true,false,3);

        ((Squad*)UnitList[2])->setIdentity(cc2js("Y"));

        UnitList[3] = new Squad(nationality,unitType,6,6,6,6,false,11,4,false,
                                classification,true,false,3);

        ((Squad*)UnitList[3])->setIdentity(cc2js("Z"));

        printf("Displaying Unit array with a Leader & 3 Squads\n");

        for (int i = 0; i < 4; i++)
        {
            if (UnitList[i])
            {
                const char* toStringOutput = js2cc(UnitList[i]->toString());

                printf("\nUnitList[%d]:\t%s\n\n",i,toStringOutput);

                if (toStringOutput) delete [] toStringOutput;

                printJavaString(((Mobile*)UnitList[i])->description());
                printJavaString(((Mobile*)UnitList[i])->identity());
                printJavaString(((Mobile*)UnitList[i])->unitType());
                printf("%d\n",((Mobile*)UnitList[i])->movement());
                printJavaString(((Mobile*)UnitList[i])->status());
            }

            else printf("UnitList[%d] is NULL\n",i);
        }

        // If it was generated the old fashioned way (see above) delete the
        // array of Unit pointers. Note that the individual objects referenced
        // by the array are freed by the Java Virtual Machine's garbage
        // collector.

        delete [] UnitList;

        // Create an instance of a German Squad (that throws some exceptions).

        printf("\nTesting Exception handling during Squad creation:\n");

        nationality    = Nationalities::valueOf(cc2js("GERMAN"));
        unitType       = InfantryTypes::valueOf(cc2js("NONE"));
        classification = Classifications::valueOf(cc2js("FIRST_LINE"));

        Squad* squad = new Squad(nationality,unitType,4,6,7,7,false,10,3,false,
                                 classification,true,false,0);

        // Null Identity

        try
        {
            squad->setIdentity(NULL);
        }

        catch (jthrowable t) // No longer expected.
        {
            printExceptionMessage(t);
        }

        // Blank Identity

        try
        {
            squad->setIdentity(cc2js(""));
        }

        catch (jthrowable t) // No longer expected.
        {
            printExceptionMessage(t);
        }

        // Incompatible nationality and unitType

        printf("\nIncompatible nationality and unitType parameters:\n");

        try
        {
            nationality    = Nationalities::valueOf(cc2js("BRITISH"));
            unitType       = InfantryTypes::valueOf(cc2js("ENGINEERS"));

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

            if (theDice)
            {
//              printf("White: %d Colored: %d Combined: %2d\n",
//                     theDice->whiteDieValue(),
//                     theDice->coloredDieValue(),
//                     theDice->combinedResult());

                printJavaString(theDice->toText());
            }

            else printf("New Dice object generation failed\n");
        }
    }

    catch (jthrowable t)
    {
        printExceptionMessage(t);
    }

    // Test the Game class.

    printf("Testing the operations of the Game class:\n\n");

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
