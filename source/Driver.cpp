// ************************************************************************** //
// Driver.cpp - This file contains the Driver program, which is used to test  //
//              the functionality of the Java classes defined in the jasl     //
//              package hierarchy, accessed as a library generated using <A HREF="http://gcc.gnu.org/java/">GCJ</A>, //
//              through the <A HREF="http://gcc.gnu.org/onlinedocs/gcj/About-CNI.html#About-CNI">CNI</A> (Compiled Native Interface).                  //
//                                                                            //
//              NOTE: This program is based on Advanced Squad Leader, which   //
//                    was created by The Avalon Hill Game Company, and lives  //
//                    on at <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.                           //
//                                                                            //
// Written By  : Craig R. Campbell  -  April 2007                             //
//                                                                            //
// $Id: Driver.cpp,v 1.1 2007/06/22 22:22:05 campbell Exp $
// ************************************************************************** //

#include <stdio.h>

#include <java/lang/Throwable.h>

#include <jasl/Counters/Unit.h>
#include <jasl/Counters/Leader.h>
#include <jasl/Counters/Squad.h>
#include <jasl/Counters/Dice.h>

#include <cniWrapper.h>

#pragma GCC java_exceptions

static void printJavaString(::java::lang::String* javaString)
{
    if (cniWrapper)
    {
        const char* convertedString = cniWrapper->stringToConstChar(javaString);

        if (convertedString)
        {
            printf("%s\n",convertedString);
            delete [] convertedString;
        }
    }
}

static void printExceptionMessage(java::lang::Throwable* exception)
{
    if (exception)
    {
        printf("\nCaught: ");
        printJavaString(exception->toString());
    }
}

int main(int argc, char *argv[])
{
    try
    {
        // Create a CNI_WRAPPER object. This object is used to create and manage
        // the connection and interaction with a Java Virtual Machine. It also
        // provides access to the methods used to convert const char* strings to
        // Java Strings and vice-versa. It must be deleted when the program is
        // closed.

        cniWrapper = new CNI_WRAPPER();

        if (cniWrapper == NULL) return -1;

        // These items are used to set parameters requiring conversion from
        // const char* to String for use in generating the jasl::Counters
        // objects below.

        ::java::lang::String* nationality    = NULL;
        ::java::lang::String* identity       = NULL;
        ::java::lang::String* unitType       = NULL;
        ::java::lang::String* firepower      = NULL;
        ::java::lang::String* classification = NULL;

        // Create an instance of a German Leader.

        nationality = cniWrapper->constCharToString("German");
        identity    = cniWrapper->constCharToString("Lt. Fellbaum");
        unitType    = cniWrapper->constCharToString("Leader");

        jasl::Counters::Leader* germanLeader = new jasl::Counters::Leader(nationality,
                                                                          identity,
                                                                          unitType,
                                                                          9,9,4,
                                                                          -1);

        // Display all of the entered values for this instance using the
        // toString() method.

        if (germanLeader)
        {
            const char* germanLeaderDetails = cniWrapper->stringToConstChar(germanLeader->toString());

            if (germanLeaderDetails)
            {
                printf("\nLeader.toString() output:\n\n%s\n",
                       germanLeaderDetails);
                delete [] germanLeaderDetails;
                germanLeaderDetails = NULL;
            }
        }

        // Create an instance of a Russian Squad.

        nationality    = cniWrapper->constCharToString("Russian");
        identity       = cniWrapper->constCharToString("A");
        unitType       = cniWrapper->constCharToString("Guards");
        firepower      = cniWrapper->constCharToString("6");
        classification = cniWrapper->constCharToString("Elite");

        jasl::Counters::Squad* russianSquad = new jasl::Counters::Squad(nationality,
                                                                        identity,
                                                                        unitType,
                                                                        firepower,
                                                                        2,true,
                                                                        8,8,
                                                                        false,
                                                                        12,4,
                                                                        false,
                                                                        classification,
                                                                        true,0);

        // Display all of the entered values for this instance using the
        // toString() method.

        if (russianSquad)
        {
            const char* russianSquadDetails = cniWrapper->stringToConstChar(russianSquad->toString());

            if (russianSquadDetails)
            {
                printf("Squad.toString() output:\n\n%s\n",russianSquadDetails);
                delete [] russianSquadDetails;
                russianSquadDetails = NULL;
            }
        }

        // Create an array of Unit objects. These will be used to reference a
        // Leader instance and several Squad instances. These class types are
        // derived from Unit.

        printf("Building Unit array with a Leader & 3 Squads\n\n");

        // The following commented code demonstrates, based on the CNI
        // documentation, the correct/preferred way to create <A HREF="http://gcc.gnu.org/onlinedocs/gcj/Arrays.html#Arrays">arrays</A> of pointers
        // to Java objects.
/*
         using namespace java::lang;
         JArray<jasl::Counters::Unit*>* UnitArray = (JArray<jasl::Counters::Unit*>*)JvNewObjectArray(4,&jasl::Counters::Unit::class$,NULL);

        jasl::Counters::Unit** UnitList = elements(UnitArray);
*/
        // The old fashioned way seems to work as well, at least in this case.
        // Note, however, that when the array is created this way, it should be
        // explicitly deleted (see below). The freeing of the memory associated
        // with the individual objects is handled by the JVM garbage collector.

        jasl::Counters::Unit** UnitList = new jasl::Counters::Unit*[4];

        nationality = cniWrapper->constCharToString("American");
        identity    = cniWrapper->constCharToString("Sgt. Slaughter");
        unitType    = cniWrapper->constCharToString("Ranger");

        UnitList[0] = new jasl::Counters::Leader(nationality,identity,unitType,
                                                 9,9,4,-1);

        identity       = cniWrapper->constCharToString("X");
        unitType       = cniWrapper->constCharToString("Squad");
        firepower      = cniWrapper->constCharToString("6");
        classification = cniWrapper->constCharToString("1st Line");

        UnitList[1] = new jasl::Counters::Squad(nationality,identity,unitType,
                                                firepower,6,false,6,6,false,11,
                                                4,false,classification,true,3);

        identity = cniWrapper->constCharToString("Y");

        UnitList[2] = new jasl::Counters::Squad(nationality,identity,unitType,
                                                firepower,6,false,6,6,false,11,
                                                4,false,classification,true,3);

        identity = cniWrapper->constCharToString("Z");

        UnitList[3] = new jasl::Counters::Squad(nationality,identity,unitType,
                                                firepower,6,false,6,6,false,11,
                                                4,false,classification,true,3);

        printf("Displaying Unit array with a Leader & 3 Squads\n");

        for (int i = 0; i < 4; i++)
        {
            if (UnitList[i])
            {
                printf("\nUnitList[%d]:\n\n",i);

                printJavaString(UnitList[i]->getDescription());
                printJavaString(UnitList[i]->getIdentity());
                printJavaString(UnitList[i]->getUnitType());
                printJavaString(UnitList[i]->getMovement());
                printJavaString(UnitList[i]->getStatus());
            }

            else printf("UnitList[%d] is NULL\n",i);
        }

        // If it was generated the old fashioned way (see above) delete the
        // array of Unit pointers. Note that the individual objects referenced
        // by the array are freed by the Java Virtual Machine's garbage
        // collector.

        delete [] UnitList;

        // Create an instance of a German Squad (that throws some exceptions).

        jasl::Counters::Squad* germanSquad = NULL;

        // NULL Nationality

        printf("\nTesting Exception handling during Squad creation:\n");
        printf("\nNull nationality parameter:\n");

        nationality = NULL;
        identity    = cniWrapper->constCharToString("5");
        firepower   = cniWrapper->constCharToString("4");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,7,
                                                    7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Blank Nationality

        printf("\nZero-length nationality parameter:\n");

        nationality = cniWrapper->constCharToString("");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,false,
                                                    7,7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Nationality

        printf("\nInvalid nationality parameter:\n");

        nationality = cniWrapper->constCharToString("Mexican");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,7,
                                                    7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Null Identity

        printf("\nNull identity parameter:\n");

        nationality = cniWrapper->constCharToString("German");
        identity    = NULL;

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,false,
                                                    7,7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Blank Identity

        printf("\nZero-length identity parameter:\n");

        identity = cniWrapper->constCharToString("");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,
                                                    7,7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Null unitType

        printf("\nNull unitType parameter:\n");

        unitType = NULL;
        identity = cniWrapper->constCharToString("5");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,false,
                                                    7,7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Blank unitType

        printf("\nZero-length unitType parameter:\n");

        unitType = cniWrapper->constCharToString("");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,
                                                    7,7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid nationality and unitType

        printf("\nInvalid nationality and unitType parameters:\n");

        nationality = cniWrapper->constCharToString("British");
        unitType    = cniWrapper->constCharToString("SS");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,false,
                                                    7,7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid description and unitType

        printf("\nInvalid description and unitType parameters:\n");

        nationality    = cniWrapper->constCharToString("Russian");
        unitType       = cniWrapper->constCharToString("Commissar");
        classification = cniWrapper->constCharToString("Green");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,
                                                    7,7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Firepower

        printf("\nInvalid (less than 0) firepower parameter:\n");

        nationality    = cniWrapper->constCharToString("German");
        unitType       = cniWrapper->constCharToString("Squad");
        firepower      = cniWrapper->constCharToString("-1");
        classification = cniWrapper->constCharToString("1st Line");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,false,
                                                    7,7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        printf("\nInvalid infantry firepower parameter:\n");

        firepower = cniWrapper->constCharToString("88L");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,7,
                                                    7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Range

        printf("\nInvalid (less than 0) normal range parameter:\n");

        firepower = cniWrapper->constCharToString("4");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,-255,
                                                    false,7,7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Morale (Minimum)

        printf("\nInvalid (less than 0) morale parameter:\n");

        classification = cniWrapper->constCharToString("Green");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,false,
                                                    -1,7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Morale (Maximum)

        printf("\nInvalid (greater than maximum) morale parameter:\n");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,
                                                    11,7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Broken Morale (Minimum)

        printf("\nInvalid (less than 0) broken morale parameter:\n");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,false,
                                                    7,-7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Broken Morale (Maximum)

        printf("\nInvalid (greater than maximum) broken morale parameter:\n");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,
                                                    7,17,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Basic Point Value (BPV)

        printf("\nInvalid (less than zero) Basic Point Value (BPV):\n");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,false,
                                                    7,7,true,-1,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Experience Level Rating (Minimum)

        printf("\nInvalid (less than zero) Experience Level Rating (ELR):\n");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,7,
                                                    7,true,10,-1,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Experience Level Rating (Maximum)

        printf("\nInvalid (greater than maximum) Experience Level Rating (ELR):\n");

        classification = cniWrapper->constCharToString("2nd Line");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,false,
                                                    7,7,true,10,6,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Null Classification

        printf("\nNull classification parameter:\n");

        classification = NULL;

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,7,
                                                    7,true,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Blank Classification

        printf("\nZero-length classification parameter:\n");

        unitType       = cniWrapper->constCharToString("SS");
        classification = cniWrapper->constCharToString("");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,7,
                                                    7,true,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Classification

        printf("\nInvalid classification parameter:\n");

        identity       = cniWrapper->constCharToString("4A");
        classification = cniWrapper->constCharToString("Bozos");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,false,
                                                    7,7,false,10,3,false,
                                                    classification,false,1);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Smoke Placement Exponent (Minimum)

        printf("\nInvalid (less than zero) Smoke Placement Exponent:\n");

        identity       = cniWrapper->constCharToString("5");
        classification = cniWrapper->constCharToString("2nd Line");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,7,
                                                    7,true,10,3,false,
                                                    classification,false,-4);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Smoke Placement Exponent (Maximum)

        printf("\nInvalid (greater than maximum) Smoke Placement Exponent:\n");

        try
        {
            germanSquad = new jasl::Counters::Squad(nationality,identity,
                                                    unitType,firepower,6,true,7,
                                                    7,true,10,3,false,
                                                    classification,false,4);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Create an instance of a Canadian Leader (that throws an exception).
        // NOTE: It is only necessary to test the modifier, since all the other
        //       exceptions have been tested above as part of the creation of a
        //       Squad.

        jasl::Counters::Leader* Grandpa = NULL;

        // Invalid Modifier (Minimum)

        printf("\nTesting Exception handling during Leader creation:\n");
        printf("\nInvalid (less than minimum) modifier parameter:\n");

        nationality = cniWrapper->constCharToString("British");
        identity    = cniWrapper->constCharToString("Sgt. Powell");
        unitType    = cniWrapper->constCharToString("Canadian");

        try
        {
            Grandpa = new jasl::Counters::Leader(nationality,identity,unitType,
                                                 10,10,5,-4);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Invalid Modifier (Maximum)

        printf("\nInvalid (greater than maximum) modifier parameter:\n");

        try
        {
            Grandpa = new jasl::Counters::Leader(nationality,identity,unitType,
                                                 10,10,5,4);
        }

        catch (java::lang::Throwable* t)
        {
            printExceptionMessage(t);
        }

        // Test the Dice class.

        printf("\nTesting the execution of the Dice class:\n\n");

        jasl::Counters::Dice* theDice     = NULL;
        const char*           diceResults = NULL;

        for (int i = 0;i < 12;i++)
        {
            theDice = new jasl::Counters::Dice();

            if (theDice)
            {
//              printf("White: %d Colored: %d Combined: %2d\n",
//                     theDice->getWhiteDieValue(),
//                     theDice->getColoredDieValue(),
//                     theDice->getCombinedResult());

                printJavaString(theDice->toString());
            }

            else printf("New Dice object generation failed\n");
        }
    }

    catch (java::lang::Throwable* t)
    {
        printExceptionMessage(t);
    }

    // Free the CNI wrapper object, which will shut down the Java Virtual
    // Machine.

    if (cniWrapper) delete cniWrapper;
}
