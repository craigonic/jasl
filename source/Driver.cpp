// ************************************************************************** //
// Driver.cpp - This file contains the Driver program, which is used to test  //
//              the functionality of the Java classes defined in the jasl     //
//              package hierarchy, accessed as a library generated using <A HREF="http://gcc.gnu.org/wiki/GCJ/">GCJ</A>, //
//              through the <A HREF="http://gcc.gnu.org/onlinedocs/gcc-6.4.0/gcj/About-CNI.html#About-CNI">CNI</A> (Compiled Native Interface).                  //
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
#include "jasl/utilities/JsonData.h"

#include <java/lang/Throwable.h>
#include <java/util/List.h>

#include <assert.h>
#include <stdio.h>

#include <string>
#include <vector>

using Mobile   = jasl::counters::Mobile;
using JsonData = jasl::utilities::JsonData;

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
        // These items are used to set arguments that require conversion /
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
            Serialization::serializeToFile(nullptr,serializationFile);
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

        Unit* deserializedLeader = nullptr;

        try
        {
            deserializedLeader =
                (Unit*)Serialization::deserializeFromFile(nullptr);
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
            Serialization::serializeToByteArray(nullptr);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        Unit* deserializedSquad = nullptr;

        try
        {
            deserializedSquad =
                (Unit*)Serialization::deserializeFromByteArray(nullptr);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Serialize the Squad object, writing the data to a byte array, and
        // then deserialize the data into a new object.

        russianSquad->setIdentity(cc2js("A"));

        JArray<jbyte>* serializedSquad = nullptr;

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

        // Test the fromJSON() method.

        printf("\nTesting the fromJSON() method:\n");

        printf("\nNull JSON input data:\n");

        try
        {
            deserializedSquad->fromJSON(nullptr);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        std::string deserializedSquadJSON(js2cc(deserializedSquad->toJSON()));
        std::string modifiedSquadJSON = deserializedSquadJSON;

        std::pair<std::string,std::string> testStringPair;
        std::vector<std::pair<std::string,std::string>> fromJsonSquadTestStrings;

        // Unit

        std::string validDescription = "\"Description\":\"SQUAD\"";
        size_t validDescriptionLength = validDescription.length();
        size_t descriptionPosition =
            deserializedSquadJSON.find(validDescription);
        size_t descriptionValuePosition = validDescription.find(":") + 1;

        testStringPair.first  = "Empty JSON input data";
        testStringPair.second = "";

        fromJsonSquadTestStrings.push_back(testStringPair);

        testStringPair.first  = "Updating a Squad with Leader data";
        testStringPair.second = js2cc(deserializedLeader->toJSON());

        fromJsonSquadTestStrings.push_back(testStringPair);

        testStringPair.first =
            "Updating a Squad with an invalid (wrong case) description";
        testStringPair.second =
            modifiedSquadJSON.replace(descriptionPosition,
                                      validDescriptionLength,
                                      validDescription.replace(descriptionValuePosition,
                                                               7,"\"Squad\""));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-string) description";
        testStringPair.second =
            modifiedSquadJSON.replace(descriptionPosition,
                                      validDescriptionLength,
                                      validDescription.replace(descriptionValuePosition,
                                                               7,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        // Fighting

        std::string validNationality = "\"Nationality\":\"RUSSIAN\"";
        size_t validNationalityLength = validNationality.length();
        size_t nationalityPosition =
            deserializedSquadJSON.find(validNationality);
        size_t nationalityValuePosition = validNationality.find(":") + 1;

        testStringPair.first = "Updating a Squad with a different nationality";
        testStringPair.second =
            modifiedSquadJSON.replace(nationalityPosition,
                                      validNationalityLength,
                                      validNationality.replace(nationalityValuePosition,
                                                               9,"\"GERMAN\""));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (wrong case) nationality";
        testStringPair.second =
            modifiedSquadJSON.replace(nationalityPosition,
                                      validNationalityLength,
                                      validNationality.replace(nationalityValuePosition,
                                                               9,"\"Russian\""));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-string) nationality";
        testStringPair.second =
            modifiedSquadJSON.replace(nationalityPosition,
                                      validNationalityLength,
                                      validNationality.replace(nationalityValuePosition,
                                                               9,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validUnitType = "\"Unit Type\":\"Guards\"";
        size_t validUnitTypeLength = validUnitType.length();
        size_t unitTypePosition = deserializedSquadJSON.find(validUnitType);
        size_t unitTypeValuePosition = validUnitType.find(":") + 1;

        testStringPair.first = "Updating a Squad with a different unit type";
        testStringPair.second =
            modifiedSquadJSON.replace(unitTypePosition,validUnitTypeLength,
                                      validUnitType.replace(unitTypeValuePosition,
                                                            8,"\"Gurkha\""));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-string) unit type";
        testStringPair.second =
            modifiedSquadJSON.replace(unitTypePosition,validUnitTypeLength,
                                      validUnitType.replace(unitTypeValuePosition,
                                                            8,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validIdentity = "\"Identity\":\"A\"";
        size_t validIdentityLength = validIdentity.length();
        size_t identityPosition = deserializedSquadJSON.find(validIdentity);
        size_t identityValuePosition = validIdentity.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with an invalid (non-string) identity";
        testStringPair.second =
            modifiedSquadJSON.replace(identityPosition,validIdentityLength,
                                      validIdentity.replace(identityValuePosition,
                                                            3,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validStatus = "\"Status\":1";
        size_t validStatusLength = validStatus.length();
        size_t statusPosition = deserializedSquadJSON.find(validStatus);
        size_t statusValuePosition = validStatus.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with an invalid (negative) status";
        testStringPair.second =
            modifiedSquadJSON.replace(statusPosition,validStatusLength,
                                      validStatus.replace(statusValuePosition,
                                                          1,"-2"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-integer) status";
        testStringPair.second =
            modifiedSquadJSON.replace(statusPosition,validStatusLength,
                                      validStatus.replace(statusValuePosition,
                                                          1,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        // Mobile

        std::string validMovement = "\"Movement\":4";
        size_t validMovementLength = validMovement.length();
        size_t movementPosition = deserializedSquadJSON.find(validMovement);
        size_t movementValuePosition = validMovement.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different movement value";
        testStringPair.second =
            modifiedSquadJSON.replace(movementPosition,validMovementLength,
                                      validMovement.replace(movementValuePosition,
                                                            1,"3"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-integer) movement value";
        testStringPair.second =
            modifiedSquadJSON.replace(movementPosition,validMovementLength,
                                      validMovement.replace(movementValuePosition,
                                                            1,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validPortageCapacity = "\"Portage Capacity\":3";
        size_t validPortageCapacityLength = validPortageCapacity.length();
        size_t portageCapacityPosition =
            deserializedSquadJSON.find(validPortageCapacity);
        size_t portageCapacityValuePosition = validPortageCapacity.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different portage capacity";
        testStringPair.second =
            modifiedSquadJSON.replace(portageCapacityPosition,
                                      validPortageCapacityLength,
                                      validPortageCapacity.replace(portageCapacityValuePosition,
                                                                   1,"5"));

        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-integer) portage capacity";
        testStringPair.second =
            modifiedSquadJSON.replace(portageCapacityPosition,
                                      validPortageCapacityLength,
                                      validPortageCapacity.replace(portageCapacityValuePosition,
                                                                   1,"null"));

        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validPortageLevel = "\"Portage Level\":0";
        size_t validPortageLevelLength = validPortageLevel.length();
        size_t portageLevelPosition =
            deserializedSquadJSON.find(validPortageLevel);
        size_t portageLevelValuePosition = validPortageLevel.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with an invalid (negative) portage level";
        testStringPair.second =
            modifiedSquadJSON.replace(portageLevelPosition,
                                      validPortageLevelLength,
                                      validPortageLevel.replace(portageLevelValuePosition,
                                                                1,"-1"));

        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-integer) portage level";
        testStringPair.second =
            modifiedSquadJSON.replace(portageLevelPosition,
                                      validPortageLevelLength,
                                      validPortageLevel.replace(portageLevelValuePosition,
                                                                1,"null"));

        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        // Infantry

        std::string validFirepower = "\"Firepower\":\"6\"";
        size_t validFirepowerLength = validFirepower.length();
        size_t firepowerPosition = deserializedSquadJSON.find(validFirepower);
        size_t firepowerValuePosition = validFirepower.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different firepower value";
        testStringPair.second =
            modifiedSquadJSON.replace(firepowerPosition,
                                      validFirepowerLength,
                                      validFirepower.replace(firepowerValuePosition,
                                                             3,"\"4\""));

        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-string) firepower value";
        testStringPair.second =
            modifiedSquadJSON.replace(firepowerPosition,
                                      validFirepowerLength,
                                      validFirepower.replace(firepowerValuePosition,
                                                             3,"null"));

        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validFpEquivalent = "\"Firepower Equivalent\":6";
        size_t validFpEquivalentLength = validFpEquivalent.length();
        size_t fpEquivalentPosition =
            deserializedSquadJSON.find(validFpEquivalent);
        size_t fpEquivalentValuePosition = validFpEquivalent.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different firepower equivalent value";
        testStringPair.second =
            modifiedSquadJSON.replace(fpEquivalentPosition,
                                      validFpEquivalentLength,
                                      validFpEquivalent.replace(fpEquivalentValuePosition,
                                                                1,"4"));

        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-integer) firepower equivalent value";
        testStringPair.second =
            modifiedSquadJSON.replace(fpEquivalentPosition,
                                      validFpEquivalentLength,
                                      validFpEquivalent.replace(fpEquivalentValuePosition,
                                                                1,"null"));

        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validNormalRange = "\"Normal Range\":2";
        size_t validNormalRangeLength = validNormalRange.length();
        size_t normalRangePosition =
            deserializedSquadJSON.find(validNormalRange);
        size_t normalRangeValuePosition = validNormalRange.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different normal range value";
        testStringPair.second =
            modifiedSquadJSON.replace(normalRangePosition,
                                      validNormalRangeLength,
                                      validNormalRange.replace(normalRangeValuePosition,
                                                               1,"4"));

        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-integer) normal range value";
        testStringPair.second =
            modifiedSquadJSON.replace(normalRangePosition,
                                      validNormalRangeLength,
                                      validNormalRange.replace(normalRangeValuePosition,
                                                               1,"null"));

        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validMorale = "\"Morale\":8";
        size_t validMoraleLength = validMorale.length();
        size_t moralePosition = deserializedSquadJSON.find(validMorale);
        size_t moraleValuePosition = validMorale.find(":") + 1;

        testStringPair.first = "Updating a Squad with a different morale value";
        testStringPair.second =
            modifiedSquadJSON.replace(moralePosition,validMoraleLength,
                                      validMorale.replace(moraleValuePosition,
                                                          1,"7"));

        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-integer) morale value";
        testStringPair.second =
            modifiedSquadJSON.replace(moralePosition,validMoraleLength,
                                      validMorale.replace(moraleValuePosition,
                                                          1,"null"));

        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validBrokenMorale = "\"Broken Morale\":8";
        size_t validBrokenMoraleLength = validBrokenMorale.length();
        size_t brokenMoralePosition =
            deserializedSquadJSON.find(validBrokenMorale);
        size_t brokenMoraleValuePosition = validBrokenMorale.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different broken morale value";
        testStringPair.second =
            modifiedSquadJSON.replace(brokenMoralePosition,
                                      validBrokenMoraleLength,
                                      validBrokenMorale.replace(brokenMoraleValuePosition,
                                                                1,"7"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-integer) broken morale value";
        testStringPair.second =
            modifiedSquadJSON.replace(brokenMoralePosition,
                                      validBrokenMoraleLength,
                                      validBrokenMorale.replace(brokenMoraleValuePosition,
                                                                1,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validCanSelfRally = "\"Can Self Rally ?\":false";
        size_t validCanSelfRallyLength = validCanSelfRally.length();
        size_t canSelfRallyPosition =
            deserializedSquadJSON.find(validCanSelfRally);
        size_t canSelfRallyValuePosition = validCanSelfRally.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different can self rally setting";
        testStringPair.second =
            modifiedSquadJSON.replace(canSelfRallyPosition,
                                      validCanSelfRallyLength,
                                      validCanSelfRally.replace(canSelfRallyValuePosition,
                                                                5,"true"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-boolean) can self rally setting";
        testStringPair.second =
            modifiedSquadJSON.replace(canSelfRallyPosition,
                                      validCanSelfRallyLength,
                                      validCanSelfRally.replace(canSelfRallyValuePosition,
                                                                5,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validPortageValue = "\"Portage Value\":10";
        size_t validPortageValueLength = validPortageValue.length();
        size_t portageValuePosition =
            deserializedSquadJSON.find(validPortageValue);
        size_t portageValueValuePosition = validPortageValue.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different portage value";
        testStringPair.second =
            modifiedSquadJSON.replace(portageValuePosition,
                                      validPortageValueLength,
                                      validPortageValue.replace(portageValueValuePosition,
                                                                2,"9"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-integer) portage value";
        testStringPair.second =
            modifiedSquadJSON.replace(portageValuePosition,
                                      validPortageValueLength,
                                      validPortageValue.replace(portageValueValuePosition,
                                                                2,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validBPV = "\"Basic Point Value\":12";
        size_t validBPVLength = validBPV.length();
        size_t bpvPosition = deserializedSquadJSON.find(validBPV);
        size_t bpvValuePosition = validBPV.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different basic point value";
        testStringPair.second =
            modifiedSquadJSON.replace(bpvPosition,validBPVLength,
                                      validBPV.replace(bpvValuePosition,2,"52"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-integer) basic point value";
        testStringPair.second =
            modifiedSquadJSON.replace(bpvPosition,validBPVLength,
                                      validBPV.replace(bpvValuePosition,2,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validELR = "\"Experience Level Rating\":4";
        size_t validELRLength = validELR.length();
        size_t elrPosition = deserializedSquadJSON.find(validELR);
        size_t elrValuePosition = validELR.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different experience level rating";
        testStringPair.second =
            modifiedSquadJSON.replace(elrPosition,validELRLength,
                                      validELR.replace(elrValuePosition,1,"3"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-integer) experience level rating";
        testStringPair.second =
            modifiedSquadJSON.replace(elrPosition,validELRLength,
                                      validELR.replace(elrValuePosition,1,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validInfantryType = "\"Infantry Type\":\"GUARDS\"";
        size_t validInfantryTypeLength = validInfantryType.length();
        size_t infantryTypePosition =
            deserializedSquadJSON.find(validInfantryType);
        size_t infantryTypeValuePosition = validInfantryType.find(":") + 1;

        testStringPair.first = "Updating a Squad with a different infantry type";
        testStringPair.second =
            modifiedSquadJSON.replace(infantryTypePosition,
                                      validInfantryTypeLength,
                                      validInfantryType.replace(infantryTypeValuePosition,
                                                                8,"\"NONE\""));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (wrong case) infantry type";
        testStringPair.second =
            modifiedSquadJSON.replace(infantryTypePosition,
                                      validInfantryTypeLength,
                                      validInfantryType.replace(infantryTypeValuePosition,
                                                                8,"\"Guards\""));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-string) infantry type";
        testStringPair.second =
            modifiedSquadJSON.replace(infantryTypePosition,
                                      validInfantryTypeLength,
                                      validInfantryType.replace(infantryTypeValuePosition,
                                                                8,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        // Personnel

        std::string validHasMaxELR = "\"Has Maximum ELR ?\":false";
        size_t validHasMaxELRLength = validHasMaxELR.length();
        size_t hasMaxELRPosition =
            deserializedSquadJSON.find(validHasMaxELR);
        size_t hasMaxELRValuePosition = validHasMaxELR.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different has maximum ELR setting";
        testStringPair.second =
            modifiedSquadJSON.replace(hasMaxELRPosition,validHasMaxELRLength,
                                      validHasMaxELR.replace(hasMaxELRValuePosition,
                                                             5,"true"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-boolean) has maximum ELR setting";
        testStringPair.second =
            modifiedSquadJSON.replace(hasMaxELRPosition,validHasMaxELRLength,
                                      validHasMaxELR.replace(hasMaxELRValuePosition,
                                                             5,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validClassification = "\"Classification\":\"ELITE\"";
        size_t validClassificationLength = validClassification.length();
        size_t classificationPosition =
            deserializedSquadJSON.find(validClassification);
        size_t classificationValuePosition = validClassification.find(":") + 1;

        testStringPair.first = "Updating a Squad with a different classification";
        testStringPair.second =
            modifiedSquadJSON.replace(classificationPosition,
                                      validClassificationLength,
                                      validClassification.replace(classificationValuePosition,
                                                                  7,"\"FIRST_LINE\""));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (wrong case) classification";
        testStringPair.second =
            modifiedSquadJSON.replace(classificationPosition,
                                      validClassificationLength,
                                      validClassification.replace(classificationValuePosition,
                                                                  12,"\"Elite\""));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-string) classification";
        testStringPair.second =
            modifiedSquadJSON.replace(classificationPosition,
                                      validClassificationLength,
                                      validClassification.replace(classificationValuePosition,
                                                                  7,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        // Squad

        std::string validCanAssaultFire = "\"Can Assault Fire ?\":true";
        size_t validCanAssaultFireLength = validCanAssaultFire.length();
        size_t canAssaultFirePosition =
            deserializedSquadJSON.find(validCanAssaultFire);
        size_t canAssaultFireValuePosition = validCanAssaultFire.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different can assault fire setting";
        testStringPair.second =
            modifiedSquadJSON.replace(canAssaultFirePosition,
                                      validCanAssaultFireLength,
                                      validCanAssaultFire.replace(canAssaultFireValuePosition,
                                                                  4,"false"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-boolean) can assault fire setting";
        testStringPair.second =
            modifiedSquadJSON.replace(canAssaultFirePosition,
                                      validCanAssaultFireLength,
                                      validCanAssaultFire.replace(canAssaultFireValuePosition,
                                                                  5,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validCanSprayFire = "\"Can Spray Fire ?\":true";
        size_t validCanSprayFireLength = validCanSprayFire.length();
        size_t canSprayFirePosition =
            deserializedSquadJSON.find(validCanSprayFire);
        size_t canSprayFireValuePosition = validCanSprayFire.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different can spray fire setting";
        testStringPair.second =
            modifiedSquadJSON.replace(canSprayFirePosition,
                                      validCanSprayFireLength,
                                      validCanSprayFire.replace(canSprayFireValuePosition,
                                                                4,"false"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-boolean) can spray fire setting";
        testStringPair.second =
            modifiedSquadJSON.replace(canSprayFirePosition,
                                      validCanSprayFireLength,
                                      validCanSprayFire.replace(canSprayFireValuePosition,
                                                                5,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        std::string validSPE = "\"Smoke Placement Exponent\":0";
        size_t validSPELength = validSPE.length();
        size_t spePosition = deserializedSquadJSON.find(validSPE);
        size_t speValuePosition = validSPE.find(":") + 1;

        testStringPair.first =
            "Updating a Squad with a different smoke placement exponent value";
        testStringPair.second =
            modifiedSquadJSON.replace(spePosition,validSPELength,
                                      validSPE.replace(speValuePosition,1,"3"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        testStringPair.first =
            "Updating a Squad with an invalid (non-integer) smoke placement exponent value";
        testStringPair.second =
            modifiedSquadJSON.replace(spePosition,validSPELength,
                                      validSPE.replace(speValuePosition,1,"null"));
        fromJsonSquadTestStrings.push_back(testStringPair);

        modifiedSquadJSON = deserializedSquadJSON;

        for (std::pair<std::string,std::string> testPair :
             fromJsonSquadTestStrings)
        {
            printf("\n%s:\n",testPair.first.c_str());

            try
            {
                deserializedSquad->fromJSON(cc2js(testPair.second.c_str()));
            }

            catch (jthrowable t)
            {
                printExceptionMessage(t);
            }
        }

        // Leader

        std::string deserializedLeaderJSON(js2cc(deserializedLeader->toJSON()));
        std::string validModifier = "\"Modifier\":-1";
        size_t validModifierLength = validModifier.length();
        size_t modifierPosition = deserializedLeaderJSON.find(validModifier);
        size_t modifierValuePosition = validModifier.find(":") + 1;

        std::string fromJsonLeaderTestStrings[2][2] =
        {
          {"Updating a Leader with a different modifier value",
           deserializedLeaderJSON.replace(modifierPosition,
                                          validModifierLength,
                                          validModifier.replace(modifierValuePosition,2,"-2"))},
          {"Updating a Leader with an invalid (non-integer) modifier value",
           deserializedLeaderJSON.replace(modifierPosition,
                                          validModifierLength,
                                          validModifier.replace(modifierValuePosition,2,"null"))}
        };

        for (int i = 0;i < 2;++i)
        {
            printf("\n%s:\n",fromJsonLeaderTestStrings[i][0].c_str());

            try
            {
                deserializedLeader->fromJSON(cc2js(fromJsonLeaderTestStrings[i][1].c_str()));
            }

            catch (jthrowable t)
            {
                printExceptionMessage(t);
            }
        }

        // Verify that all of the values for the Squad instance that can be
        // changed using the fromJSON() method (Identity, Status, and Portage
        // Level) work as expected.

        validIdentity     = "\"Identity\":\"A\"";
        validStatus       = "\"Status\":1";
        validPortageLevel = "\"Portage Level\":0";

        modifiedSquadJSON.replace(identityPosition,validIdentityLength,
                                  validIdentity.replace(identityValuePosition,
                                                        3,"\"B\""));
        modifiedSquadJSON.replace(statusPosition,validStatusLength,
                                  validStatus.replace(statusValuePosition,
                                                      1,"0"));
        modifiedSquadJSON.replace(portageLevelPosition,
                                  validPortageLevelLength,
                                  validPortageLevel.replace(portageLevelValuePosition,
                                                      1,"2"));

        deserializedSquad->fromJSON(cc2js(modifiedSquadJSON.c_str()));

        printf("\n(Updated with fromJSON()) Squad.toJSON() output:\n\n");
        printJavaString(deserializedSquad->toJSON());

        // Test the Unit.factory() method.

        printf("\nTesting the Unit.factory() method:");

        Unit* unitObject = nullptr;

        // Build JSON string for general and Leader specific Unit.factory()
        // testing.

        std::string newLeaderJSON(js2cc(JsonData::JSON_OBJECT_START));
        std::string objectSeparator(js2cc(JsonData::JSON_OBJECT_SEPARATOR));

        std::string factoryDescription  = "\"Description\":\"LEADER\"";
        std::string factoryNationality  = "\"Nationality\":\"AMERICAN\"";
        std::string factoryInfantryType = "\"Infantry Type\":\"NONE\"";
        std::string factoryModifier     = "\"Modifier\":-1";

        newLeaderJSON.append(factoryDescription + objectSeparator);
        newLeaderJSON.append(factoryNationality + objectSeparator);
        newLeaderJSON.append(factoryInfantryType + objectSeparator);
        newLeaderJSON.append("\"Morale\":8,\n");
        newLeaderJSON.append("\"Broken Morale\":8,\n");
        newLeaderJSON.append(factoryModifier + objectSeparator);
        newLeaderJSON.append(js2cc(JsonData::JSON_OBJECT_END));

        // Start with a successful (at least expected to be) generation of a
        // Leader using the new data.

        unitObject = Unit::factory(cc2js(newLeaderJSON.c_str()),3);

        // Display all of the entered values for the new Leader instance
        // (created with Unit.factory()) using the toJSON() method.

        printf("\n\n(Created with Unit.factory()) Leader.toJSON() output:\n\n");
        printJavaString(unitObject->toJSON());

        // Build JSON string for Squad specific Unit.factory() testing.

        std::string newSquadJSON(js2cc(JsonData::JSON_OBJECT_START));

        std::string factoryNormalRange    = "\"Normal Range\":6";
        std::string factoryClassification = "\"Classification\":\"FIRST_LINE\"";

        newSquadJSON.append("\"Description\":\"SQUAD\",\n");
        newSquadJSON.append(factoryNationality + objectSeparator);
        newSquadJSON.append(factoryInfantryType + objectSeparator);
        newSquadJSON.append("\"Firepower\":6,\n");
        newSquadJSON.append(factoryNormalRange + objectSeparator);
        newSquadJSON.append("\"Morale\":6,\n");
        newSquadJSON.append("\"Broken Morale\":6,\n");
        newSquadJSON.append("\"Can Self Rally ?\":false,\n");
        newSquadJSON.append("\"Basic Point Value\":11,\n");
        newSquadJSON.append("\"Has Maximum ELR ?\":false,\n");
        newSquadJSON.append(factoryClassification + objectSeparator);
        newSquadJSON.append("\"Can Assault Fire ?\":true,\n");
        newSquadJSON.append("\"Can Spray Fire ?\":false,\n");
        newSquadJSON.append("\"Smoke Placement Exponent\":3");
        newSquadJSON.append(js2cc(JsonData::JSON_OBJECT_END));

        // Start with a successful (at least expected to be) generation of a
        // Squad using the new data.

        unitObject = Unit::factory(cc2js(newSquadJSON.c_str()),3);

        // Display all of the entered values for the new Squad instance
        // (created with Unit.factory()) using the toJSON() method.

        printf("\n(Created with Unit.factory()) Squad.toJSON() output:\n\n");
        printJavaString(unitObject->toJSON());

        // (Attempt to) create Unit instances using Unit.factory() to test
        // exceptions.

        printf("\nTesting Exception handling for Unit.factory() method:\n");

        printf("\nNull JSON input data:\n");

        try
        {
            Unit::factory(nullptr,3);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        std::vector<std::pair<std::string,std::string>> factoryTestStrings;

        // Unit

        validDescription = "\"Description\":\"LEADER\"";
        validDescriptionLength = validDescription.length();
        descriptionPosition = newLeaderJSON.find(validDescription);
        descriptionValuePosition = validDescription.find(":") + 1;

        testStringPair.first  = "Empty JSON input data";
        testStringPair.second = "";

        factoryTestStrings.push_back(testStringPair);

        std::string modifiedLeaderJSON(newLeaderJSON);

        testStringPair.first =
            "Invalid (wrong case) Description value";
        testStringPair.second =
            modifiedLeaderJSON.replace(descriptionPosition,
                                       validDescriptionLength,
                                       validDescription.replace(descriptionValuePosition,
                                                                8,"\"Leader\""));
        factoryTestStrings.push_back(testStringPair);

        modifiedLeaderJSON = newLeaderJSON;

        testStringPair.first =
            "Invalid (non-string) Description value";
        testStringPair.second =
            modifiedLeaderJSON.replace(descriptionPosition,
                                       validDescriptionLength,
                                       validDescription.replace(descriptionValuePosition,
                                                                8,"null"));
        factoryTestStrings.push_back(testStringPair);

        modifiedLeaderJSON = newLeaderJSON;

         // Leader

        validNationality = "\"Nationality\":\"AMERICAN\"";
        validNationalityLength = validNationality.length();
        nationalityPosition = newLeaderJSON.find(validNationality);
        nationalityValuePosition = validNationality.find(":") + 1;

        validModifierLength = factoryModifier.length();
        modifierPosition = newLeaderJSON.find(factoryModifier);
        modifierValuePosition = factoryModifier.find(":") + 1;

        testStringPair.first = "Invalid (wrong case) Nationality value";
        testStringPair.second =
            modifiedLeaderJSON.replace(nationalityPosition,
                                       validNationalityLength,
                                       validNationality.replace(nationalityValuePosition,
                                                                10,"\"American\""));
        factoryTestStrings.push_back(testStringPair);

        modifiedLeaderJSON = newLeaderJSON;

        testStringPair.first = "Invalid (non-string) Nationality value";
        testStringPair.second =
            modifiedLeaderJSON.replace(nationalityPosition,
                                       validNationalityLength,
                                       validNationality.replace(nationalityValuePosition,
                                                                10,"null"));
        factoryTestStrings.push_back(testStringPair);

        modifiedLeaderJSON = newLeaderJSON;

        testStringPair.first = "Invalid (less than minimum) modifier argument";
        testStringPair.second =
            modifiedLeaderJSON.replace(modifierPosition,
                                       validModifierLength,
                                       factoryModifier.replace(modifierValuePosition,
                                                               2,"-4"));
        factoryTestStrings.push_back(testStringPair);

        modifiedSquadJSON = newSquadJSON;

         // Squad

        validClassificationLength = factoryClassification.length();
        classificationPosition = newSquadJSON.find(factoryClassification);
        classificationValuePosition = factoryClassification.find(":") + 1;

        testStringPair.first = "Invalid (for nationality) Classification value";
        testStringPair.second =
            modifiedSquadJSON.replace(classificationPosition,
                                      validClassificationLength,
                                      validClassification.replace(classificationValuePosition,
                                                                  12,"\"SS\""));
        factoryTestStrings.push_back(testStringPair);

        modifiedSquadJSON = newSquadJSON;

        testStringPair.first = "Invalid (wrong case) Classification value";
        testStringPair.second =
            modifiedSquadJSON.replace(classificationPosition,
                                      validClassificationLength,
                                      validClassification.replace(classificationValuePosition,
                                                                  4,"\"Green\""));
        factoryTestStrings.push_back(testStringPair);

        modifiedSquadJSON = newSquadJSON;

        testStringPair.first = "Invalid (non-string) Classification value";
        testStringPair.second =
            modifiedSquadJSON.replace(classificationPosition,
                                      validClassificationLength,
                                      validClassification.replace(classificationValuePosition,
                                                                  7,"null"));
        factoryTestStrings.push_back(testStringPair);

        for (std::pair<std::string,std::string> testPair : factoryTestStrings)
        {
            printf("\n%s:\n",testPair.first.c_str());

            try
            {
                Unit::factory(cc2js(testPair.second.c_str()),3);
            }

            catch (jthrowable t)
            {
                printExceptionMessage(t);
            }
        }

        // Create an array of Unit objects. These will be used to reference a
        // Leader instance and several Squad instances. These class types are
        // derived from Unit.

        printf("\nBuilding Unit array with a Leader & 3 Squads\n\n");

        // The following commented code demonstrates, based on the CNI
        // documentation, the correct/preferred way to create <A HREF="http://gcc.gnu.org/onlinedocs/gcc-6.4.0/gcj/Arrays.html#Arrays">arrays</A> of pointers
        // to Java objects.
/*
         using namespace java::lang;
         JArray<Unit*>* UnitArray =
             (JArray<Unit*>*)JvNewObjectArray(4,&Unit::class$,nullptr);

        Unit** UnitList = elements(UnitArray);
*/
        // The old fashioned way seems to work as well, at least in this case.
        // Note, however, that when the array is created this way, it should be
        // explicitly deleted (see below). The freeing of the memory associated
        // with the individual objects is handled by the JVM garbage collector.

        Unit** UnitList = new Unit*[4];

        nationality    = Nationalities::valueOf(cc2js("RUSSIAN"));
        unitType       = InfantryTypes::valueOf(cc2js("COMMISSAR"));

        UnitList[0] = new Leader(nationality,unitType,9,9,3,0);

        ((Leader*)UnitList[0])->setIdentity(cc2js("Commissar Ryzhiy"));

        unitType       = InfantryTypes::valueOf(cc2js("GUARDS"));
        classification = Classifications::valueOf(cc2js("ELITE"));

        UnitList[1] = new Squad(nationality,unitType,6,2,8,8,false,12,3,false,
                                classification,true,true,0);

        unitType       = InfantryTypes::valueOf(cc2js("NONE"));
        classification = Classifications::valueOf(cc2js("FIRST_LINE"));

        UnitList[2] = new Squad(nationality,unitType,4,4,7,7,false,7,3,false,
                                classification,false,false,0);

        classification = Classifications::valueOf(cc2js("CONSCRIPT"));

        UnitList[3] = new Squad(nationality,unitType,4,2,6,5,false,4,3,false,
                                classification,false,false,0);

        ((Squad*)UnitList[1])->setIdentity(cc2js("X"));
        ((Squad*)UnitList[2])->setIdentity(cc2js("Y"));
        ((Squad*)UnitList[2])->setStatus(brokenState);
        ((Squad*)UnitList[3])->setIdentity(cc2js("Z"));
        ((Squad*)UnitList[3])->setStatus(desperateState);

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

        squad->setIdentity(nullptr);

        // Blank Identity (no error, just clears the existing one).

        squad->setIdentity(cc2js(""));

        // Invalid portage level

        printf("\nInvalid portage level argument:\n");

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

        printf("\nIncompatible nationality and unitType arguments:\n");

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

        printf("\nIncompatible description and unitType arguments:\n");

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

        printf("\nInvalid (less than 0) firepower argument:\n");

        try
        {
            squad = new Squad(nationality,unitType,-1,6,7,7,false,10,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        printf("\nInvalid (greater than maximum) firepower argument:\n");

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

        printf("\nInvalid (less than 0) normal range argument:\n");

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

        printf("\nInvalid (less than 0) morale argument:\n");

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

        printf("\nInvalid (greater than maximum) morale argument:\n");

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

        printf("\nInvalid (less than 0) broken morale argument:\n");

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

        printf("\nInvalid (greater than maximum) broken morale argument:\n");

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

        // Incompatible Classification (only German units can be SS)

        printf("\nIncompatible classification argument (nationality mismatch):\n");

        try
        {
            squad = new Squad(nationality,unitType,4,6,7,7,false,10,3,false,
                              classification,true,false,0);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        nationality    = Nationalities::valueOf(cc2js("PARTISAN"));
        classification = Classifications::valueOf(cc2js("ELITE"));

        // Incompatible Classification (Partisan units must have empty classification)

        printf("\nIncompatible classification argument (invalid setting):\n");

        try
        {
            squad = new Squad(nationality,unitType,3,3,7,6,false,6,3,false,
                              classification,false,false,0);
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

        Leader* Grandpa = nullptr;

        nationality    = Nationalities::valueOf(cc2js("BRITISH"));
        unitType       = InfantryTypes::valueOf(cc2js("CANADIAN"));

        // Invalid Modifier (Minimum)

        printf("\nInvalid (less than minimum) modifier argument:\n");

        try
        {
            Grandpa = new Leader(nationality,unitType,10,10,5,-4);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }

        // Invalid Modifier (Maximum)

        printf("\nInvalid (greater than maximum) modifier argument:\n");

        try
        {
            Grandpa = new Leader(nationality,unitType,10,10,5,4);
        }

        catch (jthrowable t)
        {
            printExceptionMessage(t);
        }
/*
        // Test the Dice class.

        printf("\nTesting the execution of the Dice class:\n\n");

        Dice*       theDice     = nullptr;
        const char* diceResults = nullptr;

        for (int i = 0;i < 12;i++)
        {
            theDice = new Dice();

            assert(theDice);

//          printf("White: %d Colored: %d Combined: %2d\n",
//                 theDice->whiteDieValue(),
//                 theDice->coloredDieValue(),
//                 theDice->combinedResult());

            printJavaString(theDice->toText());
        } */
    }

    catch (jthrowable t)
    {
        printExceptionMessage(t);
    }

    // Test the Scenario class.

    printf("\nTesting Exception handling during Scenario creation:\n");

    Scenario* scenario = nullptr;

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
