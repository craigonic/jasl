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

#include "CniWrapper.h"
#include "JaslErrorMessage.h"
#include "jaslWrapper.h"

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
    try
    {
        // These items are used to set parameters requiring conversion from
        // const char* to String for use in generating the jasl::counters
        // objects below.

        jstring nationality    = NULL;
        jstring identity       = NULL;
        jstring unitType       = NULL;
        jstring firepower      = NULL;
        jstring classification = NULL;

        // Create an instance of a German Leader.

        nationality = cc2js("German");
        identity    = cc2js("Lt. Fellbaum");
        unitType    = cc2js("Leader");

        Leader* germanLeader = new Leader(nationality,identity,unitType,
                                          9,9,4,-1);

        // Display all of the entered values for this instance using the
        // toString() method.

        if (germanLeader)
        {
            const char* germanLeaderDetails = js2cc(germanLeader->toString());

            if (germanLeaderDetails)
            {
                printf("\nLeader.toString() output:\n\n%s\n",
                       germanLeaderDetails);
                delete [] germanLeaderDetails;
                germanLeaderDetails = NULL;
            }
        }

        // Create an instance of a Russian Squad.

        nationality    = cc2js("Russian");
        identity       = cc2js("A");
        unitType       = cc2js("Guards");
        firepower      = cc2js("6");
        classification = cc2js("Elite");

        Squad* russianSquad = new Squad(nationality,identity,unitType,firepower,
                                        2,true,8,8,false,12,4,false,
                                        classification,true,0);

        // Display all of the entered values for this instance using the
        // toString() method.

        if (russianSquad)
        {
            const char* russianSquadDetails = js2cc(russianSquad->toString());

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
         JArray<Unit*>* UnitArray = (JArray<Unit*>*)JvNewObjectArray(4,&Unit::class$,NULL);

        Unit** UnitList = elements(UnitArray);
*/
        // The old fashioned way seems to work as well, at least in this case.
        // Note, however, that when the array is created this way, it should be
        // explicitly deleted (see below). The freeing of the memory associated
        // with the individual objects is handled by the JVM garbage collector.

        Unit** UnitList = new Unit*[4];

        nationality = cc2js("American");
        identity    = cc2js("Sgt. Slaughter");
        unitType    = cc2js("Ranger");

        UnitList[0] = new Leader(nationality,identity,unitType,9,9,4,-1);

        identity       = cc2js("X");
        unitType       = cc2js("Squad");
        firepower      = cc2js("6");
        classification = cc2js("1st Line");

        UnitList[1] = new Squad(nationality,identity,unitType,firepower,6,false,
                                6,6,false,11,4,false,classification,true,3);

        identity = cc2js("Y");

        UnitList[2] = new Squad(nationality,identity,unitType,firepower,6,false,
                                6,6,false,11,4,false,classification,true,3);

        identity = cc2js("Z");

        UnitList[3] = new Squad(nationality,identity,unitType,firepower,6,false,
                                6,6,false,11,4,false,classification,true,3);

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

        Squad* germanSquad = NULL;

        // NULL Nationality

        printf("\nTesting Exception handling during Squad creation:\n");
        printf("\nNull nationality parameter:\n");

        nationality = NULL;
        identity    = cc2js("5");
        firepower   = cc2js("4");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,
                                    6,true,7,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Blank Nationality

        printf("\nZero-length nationality parameter:\n");

        nationality = cc2js("");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    false,7,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Nationality

        printf("\nInvalid nationality parameter:\n");

        nationality = cc2js("Mexican");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    true,7,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Null Identity

        printf("\nNull identity parameter:\n");

        nationality = cc2js("German");
        identity    = NULL;

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    false,7,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Blank Identity

        printf("\nZero-length identity parameter:\n");

        identity = cc2js("");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    true,7,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Null unitType

        printf("\nNull unitType parameter:\n");

        unitType = NULL;
        identity = cc2js("5");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    false,7,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Blank unitType

        printf("\nZero-length unitType parameter:\n");

        unitType = cc2js("");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    true,7,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid nationality and unitType

        printf("\nInvalid nationality and unitType parameters:\n");

        nationality = cc2js("British");
        unitType    = cc2js("SS");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    false,7,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid description and unitType

        printf("\nInvalid description and unitType parameters:\n");

        nationality    = cc2js("Russian");
        unitType       = cc2js("Commissar");
        classification = cc2js("Green");

        try
        {
            russianSquad = new Squad(nationality,identity,unitType,firepower,6,
                                     true,7,7,false,10,3,false,classification,
                                     false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Firepower

        printf("\nInvalid (less than 0) firepower parameter:\n");

        nationality    = cc2js("German");
        unitType       = cc2js("Squad");
        firepower      = cc2js("-1");
        classification = cc2js("1st Line");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    false,7,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        printf("\nInvalid infantry firepower parameter:\n");

        firepower = cc2js("88L");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    true,7,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Range

        printf("\nInvalid (less than 0) normal range parameter:\n");

        firepower = cc2js("4");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,
                                    -255,false,7,7,false,10,3,false,
                                    classification,false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Morale (Minimum)

        printf("\nInvalid (less than 0) morale parameter:\n");

        classification = cc2js("Green");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    false,-1,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Morale (Maximum)

        printf("\nInvalid (greater than maximum) morale parameter:\n");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    true,11,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Broken Morale (Minimum)

        printf("\nInvalid (less than 0) broken morale parameter:\n");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    false,7,-7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Broken Morale (Maximum)

        printf("\nInvalid (greater than maximum) broken morale parameter:\n");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    true,7,17,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Basic Point Value (BPV)

        printf("\nInvalid (less than zero) Basic Point Value (BPV):\n");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    false,7,7,true,-1,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Experience Level Rating (Minimum)

        printf("\nInvalid (less than zero) Experience Level Rating (ELR):\n");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    true,7,7,true,10,-1,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Experience Level Rating (Maximum)

        printf("\nInvalid (greater than maximum) Experience Level Rating (ELR):\n");

        classification = cc2js("2nd Line");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    false,7,7,true,10,6,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Null Classification

        printf("\nNull classification parameter:\n");

        classification = NULL;

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    true,7,7,true,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Blank Classification

        printf("\nZero-length classification parameter:\n");

        unitType       = cc2js("SS");
        classification = cc2js("");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    true,7,7,true,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Classification

        printf("\nInvalid classification parameter:\n");

        identity       = cc2js("4A");
        classification = cc2js("Bozos");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    false,7,7,false,10,3,false,classification,
                                    false,1);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Smoke Placement Exponent (Minimum)

        printf("\nInvalid (less than zero) Smoke Placement Exponent:\n");

        identity       = cc2js("5");
        classification = cc2js("2nd Line");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    true,7,7,true,10,3,false,classification,
                                    false,-4);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Smoke Placement Exponent (Maximum)

        printf("\nInvalid (greater than maximum) Smoke Placement Exponent:\n");

        try
        {
            germanSquad = new Squad(nationality,identity,unitType,firepower,6,
                                    true,7,7,true,10,3,false,classification,
                                    false,4);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Create an instance of a Canadian Leader (that throws an exception).
        // NOTE: It is only necessary to test the modifier, since all the other
        //       exceptions have been tested above as part of the creation of a
        //       Squad.

        Leader* Grandpa = NULL;

        // Invalid Modifier (Minimum)

        printf("\nTesting Exception handling during Leader creation:\n");
        printf("\nInvalid (less than minimum) modifier parameter:\n");

        nationality = cc2js("British");
        identity    = cc2js("Sgt. Powell");
        unitType    = cc2js("Canadian");

        try
        {
            Grandpa = new Leader(nationality,identity,unitType,10,10,5,-4);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Modifier (Maximum)

        printf("\nInvalid (greater than maximum) modifier parameter:\n");

        try
        {
            Grandpa = new Leader(nationality,identity,unitType,10,10,5,4);
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
//                     theDice->getWhiteDieValue(),
//                     theDice->getColoredDieValue(),
//                     theDice->getCombinedResult());

                printJavaString(theDice->toString());
            }

            else printf("New Dice object generation failed\n");
        }
    }

    catch (jthrowable t)
    {
        printExceptionMessage(t);
    }
}
