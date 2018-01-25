// ************************************************************************** //
// Driver.java - This file contains the Driver class, which is used to test   //
//               the basic functionality of the classes and methods in jASL.  //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, which  //
//                     was created by The Avalon Hill Game Company, and lives //
//                     on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                          //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
// ************************************************************************** //

import java.io.IOException;
import java.util.List;

import jasl.counters.*;
import jasl.ui.data.*;
//import jasl.utilities.Dice;
import jasl.utilities.JsonData;
import jasl.utilities.Serialization;

public class Driver
{
    public static void main(String[] args)
    {
        // Create an instance of a German Leader.

        Leader germanLeader = new Leader(Nationality.Nationalities.GERMAN,
                                         UnitType.InfantryTypes.NONE,
                                         9,9,4,-1);

        germanLeader.setStatus(Status.States.BROKEN);
        germanLeader.setPortageLevel(2);

        // (Silently) verify that the status that was just set is not
        // (successfully) set again (i.e. it worked the first time).

        assert(!germanLeader.setStatus(Status.States.BROKEN));

        // Display all of the entered values for this instance using the
        // toText() method.

        System.out.println("\nLeader.toText() output:\n");
        System.out.print(germanLeader.toText());

        // Display an abbreviated description of this instance using the
        // toString() method.

        System.out.println("\nLeader.toString() output:\n");
        System.out.println(germanLeader.toString());

        // Test the exception handling within the Serialization class,
        // specifically the methods associated with serializing to and
        // deserializing from a file.

        System.out.println("\nTesting Exception handling for serialization to and from a file:\n");

        String serializationFile = "";

        try
        {
            Serialization.serializeToFile(null,serializationFile);
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e + "\n");
        }

        try
        {
            Serialization.serializeToFile(germanLeader,serializationFile);
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e + "\n");
        }

        Unit deserializedLeader = null;

        try
        {
            deserializedLeader =
                (Unit)Serialization.deserializeFromFile(null);
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e + "\n");
        }

        try
        {
             deserializedLeader =
                 (Unit)Serialization.deserializeFromFile(serializationFile);
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e + "\n");
        }

        try
        {
             deserializedLeader =
                 (Unit)Serialization.deserializeFromFile("/tmp/NonExistentFile");
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e);
        }

        // Serialize the Leader object, write the data to a file (Leader.ser),
        // then deserialize the data into a new object.

        germanLeader.setIdentity("Col. Klink");

        try
        {
            Serialization.serializeToFile(germanLeader,"/tmp/Leader.ser");
        }

        catch (Exception e) // Not expected.
        {
            System.out.println("Caught: " + e);
        }

        deserializedLeader = null;

        try
        {
            deserializedLeader =
                (Unit)Serialization.deserializeFromFile("/tmp/Leader.ser");
        }

        catch (Exception e) // Not expected.
        {
            System.out.println("Caught: " + e);
        }

        // Retrieve the leader's status and then use the value to restore to
        // "normal".

        List<Status.States> statusList = ((Leader)deserializedLeader).status();

        ((Leader)deserializedLeader).clearStatus((Status.States)statusList.get(0));

        // Display all of the entered values for the deserialized instance using
        // the toText() method.

        System.out.println("\n(Deserialized) Leader.toText() output:\n");
        System.out.print(deserializedLeader.toText());

        // Display an abbreviated description of the deserialized instance using
        // the toString() method.

        System.out.println("\n(Deserialized) Leader.toString() output:\n");
        System.out.println(deserializedLeader.toString());

        // Display all of the entered values for the deserialized instance using
        // the toJSON() method.

        System.out.println("\n(Deserialized) Leader.toJSON() output:\n");
        System.out.println(deserializedLeader.toJSON());

        // Create an instance of a Russian Squad.

        Squad russianSquad = new Squad(Nationality.Nationalities.RUSSIAN,
                                       UnitType.InfantryTypes.GUARDS,
                                       6,2,8,8,false,12,4,false,
                                       Classification.Classifications.ELITE,
                                       true,true,0);

        russianSquad.setStatus(Status.States.DESPERATE);

        // Display all of the entered values for this instance using the
        // toText() method.

        System.out.println("\nSquad.toText() output:\n\n" +
                           russianSquad.toText());

        // Display an abbreviated description of this instance using the
        // toString() method.

        System.out.println("Squad.toString() output:\n\n" +
                           russianSquad.toString());

        // Test the exception handling within the Serialization class,
        // specifically the methods associated with serializing to and
        // deserializing from a byte array.

        System.out.println("\nTesting Exception handling for serialization to and from a byte array:\n");

        try
        {
            Serialization.serializeToByteArray(null);
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e + "\n");
        }

        Unit deserializedSquad = null;

        try
        {
            deserializedSquad =
                (Unit)Serialization.deserializeFromByteArray(null);
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e + "\n");
        }

        try
        {
            deserializedSquad =
                (Unit)Serialization.deserializeFromByteArray(new byte[0]);
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e);
        }

        // Serialize the Squad object, writing the data to a byte array, and
        // then deserialize the data into a new object.

        russianSquad.setIdentity("A");

        byte[] serializedSquad = null;

        try
        {
            serializedSquad = Serialization.serializeToByteArray(russianSquad);
        }

        catch (Exception e) // Not expected.
        {
            System.out.println("Caught: " + e);
        }

        deserializedSquad = null;

        try
        {
            deserializedSquad =
                (Unit)Serialization.deserializeFromByteArray(serializedSquad);
        }

        catch (Exception e) // Not expected.
        {
            System.out.println("Caught: " + e);
        }

        // (Silently) verify that if a Unit is subject to desperation morale,
        // it's broken status can't be (underhandedly) removed.

        assert(!((Squad)deserializedSquad).clearStatus(Status.States.BROKEN));

        // Retrieve the squad's status and then use the value to "reduce" it to
        // "broken".

        statusList = ((Squad)deserializedSquad).status();

        ((Squad)deserializedSquad).clearStatus((Status.States)statusList.get(0));

        // (Silently) verify that the status that was just cleared is not
        // (successfully) cleared again (i.e. it worked the first time).

        assert(!((Squad)deserializedSquad).clearStatus((Status.States)(statusList.get(0))));

        // Display all of the entered values for the deserialized instance using
        // the toText() method.

        System.out.println("\n(Deserialized) Squad.toText() output:\n");
        System.out.print(deserializedSquad.toText());

        // Display an abbreviated description of the deserialized instance using
        // the toString() method.

        System.out.println("\n(Deserialized) Squad.toString() output:\n");
        System.out.println(deserializedSquad.toString());

        // Display all of the entered values for the deserialized instance using
        // the toJSON() method.

        System.out.println("\n(Deserialized) Squad.toJSON() output:\n\n" +
                           deserializedSquad.toJSON());

        // Test the fromJSON() method.

        System.out.println("\nTesting the fromJSON() method:");

        String deserializedSquadJSON = deserializedSquad.toJSON();

        String validDescription    = "\"Description\":\"SQUAD\"";
        String validNationality    = "\"Nationality\":\"RUSSIAN\"";
        String validUnitType       = "\"Unit Type\":\"Guards\"";
        String validIdentity       = "\"Identity\":\"A\"";
        String validStatus         = "\"Status\":1";
        String validMovement       = "\"Movement\":4";
        String validPortageCap     = "\"Portage Capacity\":3";
        String validPortageLevel   = "\"Portage Level\":0";
        String validFirepower      = "\"Firepower\":\"6\"";
        String validFpEquivalent   = "\"Firepower Equivalent\":6";
        String validNormalRange    = "\"Normal Range\":2";
        String validMorale         = "\"Morale\":8";
        String validBrokenMorale   = "\"Broken Morale\":8";
        String validCanSelfRally   = "\"Can Self Rally \\?\":false";
        String validPortageValue   = "\"Portage Value\":10";
        String validBPV            = "\"Basic Point Value\":12";
        String validELR            = "\"Experience Level Rating\":4";
        String validInfantryType   = "\"Infantry Type\":\"GUARDS\"";
        String validHasMaxELR      = "\"Has Maximum ELR \\?\":false";
        String validClassification = "\"Classification\":\"ELITE\"";
        String validCanAssaultFire = "\"Can Assault Fire \\?\":true";
        String validCanSprayFire   = "\"Can Spray Fire \\?\":true";
        String validSPE            = "\"Smoke Placement Exponent\":0";

        String fromJsonSquadTestStrings[][] =
        {
         // Unit

          {"Null JSON input data",null},
          {"Empty JSON input data",""},

          {"Updating a Squad with Leader data",deserializedLeader.toJSON()},
          {"Updating a Squad with an invalid (wrong case) description",
           deserializedSquadJSON.replaceAll(validDescription,
                                            validDescription.replaceAll("\"SQUAD\"",
                                                                        "\"Squad\""))},
          {"Updating a Squad with an invalid (non-string) description",
           deserializedSquadJSON.replaceAll(validDescription,
                                            validDescription.replaceAll("\"SQUAD\"",
                                                                        "null"))},
         // Fighting

          {"Updating a Squad with a different nationality",
           deserializedSquadJSON.replaceAll(validNationality,
                                            validNationality.replaceAll("\"RUSSIAN\"",
                                                                        "\"GERMAN\""))},
          {"Updating a Squad with an invalid (wrong case) nationality",
           deserializedSquadJSON.replaceAll(validNationality,
                                            validNationality.replaceAll("\"RUSSIAN\"",
                                                                        "\"Russian\""))},
          {"Updating a Squad with an invalid (non-string) nationality",
           deserializedSquadJSON.replaceAll(validNationality,
                                            validNationality.replaceAll("\"RUSSIAN\"",
                                                                        "null"))},

          {"Updating a Squad with a different unit type",
           deserializedSquadJSON.replaceAll(validUnitType,
                                            validUnitType.replaceAll("\"Guards\"",
                                                                     "\"Gurkha\""))},
          {"Updating a Squad with an invalid (non-string) unit type",
           deserializedSquadJSON.replaceAll(validUnitType,
                                            validUnitType.replaceAll("\"Guards\"",
                                                                     "null"))},

          {"Updating a Squad with an invalid (non-string) identity",
           deserializedSquadJSON.replaceAll(validIdentity,
                                            validIdentity.replaceAll("\"A\"",
                                                                     "null"))},

          {"Updating a Squad with an invalid (negative) status",
           deserializedSquadJSON.replaceAll(validStatus,
                                            validStatus.replaceAll("1","-2"))},
          {"Updating a Squad with an invalid (non-integer) status",
           deserializedSquadJSON.replaceAll(validStatus,
                                            validStatus.replaceAll("1","null"))},

         // Mobile

          {"Updating a Squad with a different movement value",
           deserializedSquadJSON.replaceAll(validMovement,
                                            validMovement.replaceAll("4","3"))},
          {"Updating a Squad with an invalid (non-integer) movement value",
           deserializedSquadJSON.replaceAll(validMovement,
                                            validMovement.replaceAll("4","null"))},

          {"Updating a Squad with a different portage capacity",
           deserializedSquadJSON.replaceAll(validPortageCap,
                                            validPortageCap.replaceAll("3","5"))},
          {"Updating a Squad with an invalid (non-integer) portage capacity",
           deserializedSquadJSON.replaceAll(validPortageCap,
                                            validPortageCap.replaceAll("3","null"))},

          {"Updating a Squad with an invalid (negative) portage level",
           deserializedSquadJSON.replaceAll(validPortageLevel,
                                            validPortageLevel.replaceAll("0","-1"))},
          {"Updating a Squad with an invalid (non-integer) portage level",
           deserializedSquadJSON.replaceAll(validPortageLevel,
                                            validPortageLevel.replaceAll("0","null"))},
         // Infantry

          {"Updating a Squad with a different firepower value",
           deserializedSquadJSON.replaceAll(validFirepower,
                                            validFirepower.replaceAll("\"6\"",
                                                                      "\"4\""))},
          {"Updating a Squad with an invalid (non-string) firepower value",
           deserializedSquadJSON.replaceAll(validFirepower,
                                            validFirepower.replaceAll("\"6\"",
                                                                      "null"))},

          {"Updating a Squad with a different firepower equivalent value",
           deserializedSquadJSON.replaceAll(validFpEquivalent,
                                            validFpEquivalent.replaceAll("6","4"))},
          {"Updating a Squad with an invalid (non-integer) firepower equivalent value",
           deserializedSquadJSON.replaceAll(validFpEquivalent,
                                            validFpEquivalent.replaceAll("6","null"))},

          {"Updating a Squad with a different normal range value",
           deserializedSquadJSON.replaceAll(validNormalRange,
                                            validNormalRange.replaceAll("2","4"))},
          {"Updating a Squad with an invalid (non-integer) normal range value",
           deserializedSquadJSON.replaceAll(validNormalRange,
                                            validNormalRange.replaceAll("2","null"))},

          {"Updating a Squad with a different morale value",
           deserializedSquadJSON.replaceAll(validMorale,
                                            validMorale.replaceAll("8","7"))},
          {"Updating a Squad with an invalid (non-integer) morale value",
           deserializedSquadJSON.replaceAll(validMorale,
                                            validMorale.replaceAll("8","null"))},

          {"Updating a Squad with a different broken morale value",
           deserializedSquadJSON.replaceAll(validBrokenMorale,
                                            validBrokenMorale.replaceAll("8","7"))},
          {"Updating a Squad with an invalid (non-integer) broken morale value",
           deserializedSquadJSON.replaceAll(validBrokenMorale,
                                            validBrokenMorale.replaceAll("8","null"))},

          {"Updating a Squad with a different can self rally setting",
           deserializedSquadJSON.replaceAll(validCanSelfRally,
                                            validCanSelfRally.replaceAll("false",
                                                                         "true"))},
          {"Updating a Squad with an invalid (non-boolean) can self rally setting",
           deserializedSquadJSON.replaceAll(validCanSelfRally,
                                            validCanSelfRally.replaceAll("false",
                                                                         "null"))},

          {"Updating a Squad with a different portage value",
           deserializedSquadJSON.replaceAll(validPortageValue,
                                            validPortageValue.replaceAll("10","9"))},
          {"Updating a Squad with an invalid (non-integer) portage value",
           deserializedSquadJSON.replaceAll(validPortageValue,
                                            validPortageValue.replaceAll("10","null"))},

          {"Updating a Squad with a different basic point value",
           deserializedSquadJSON.replaceAll(validBPV,
                                            validBPV.replaceAll("12","52"))},
          {"Updating a Squad with an invalid (non-integer) basic point value",
           deserializedSquadJSON.replaceAll(validBPV,
                                            validBPV.replaceAll("12","null"))},

          {"Updating a Squad with a different experience level rating",
           deserializedSquadJSON.replaceAll(validELR,
                                            validELR.replaceAll("4","3"))},
          {"Updating a Squad with an invalid (non-integer) experience level rating",
           deserializedSquadJSON.replaceAll(validELR,
                                            validELR.replaceAll("4","null"))},

          {"Updating a Squad with a different infantry type",
           deserializedSquadJSON.replaceAll(validInfantryType,
                                            validInfantryType.replaceAll("\"GUARDS\"",
                                                                        "\"NONE\""))},
          {"Updating a Squad with an invalid (wrong case) infantry type",
           deserializedSquadJSON.replaceAll(validInfantryType,
                                            validInfantryType.replaceAll("\"GUARDS\"",
                                                                        "\"Guards\""))},
          {"Updating a Squad with an invalid (non-string) infantry type",
           deserializedSquadJSON.replaceAll(validInfantryType,
                                            validInfantryType.replaceAll("\"GUARDS\"",
                                                                        "null"))},
         // Personnel

          {"Updating a Squad with a different has maximum ELR setting",
           deserializedSquadJSON.replaceAll(validHasMaxELR,
                                            validHasMaxELR.replaceAll("false",
                                                                      "true"))},
          {"Updating a Squad with an invalid (non-boolean) has maximum ELR setting",
           deserializedSquadJSON.replaceAll(validHasMaxELR,
                                            validHasMaxELR.replaceAll("false",
                                                                      "null"))},

          {"Updating a Squad with a different classification",
           deserializedSquadJSON.replaceAll(validClassification,
                                            validClassification.replaceAll("\"ELITE\"",
                                                                           "\"FIRST_LINE\""))},
          {"Updating a Squad with an invalid (wrong case) classification",
           deserializedSquadJSON.replaceAll(validClassification,
                                            validClassification.replaceAll("\"ELITE\"",
                                                                           "\"Elite\""))},
          {"Updating a Squad with an invalid (non-string) classification",
           deserializedSquadJSON.replaceAll(validClassification,
                                            validClassification.replaceAll("\"ELITE\"",
                                                                           "null"))},
         // Squad

          {"Updating a Squad with a different can assault fire setting",
           deserializedSquadJSON.replaceAll(validCanAssaultFire,
                                            validCanAssaultFire.replaceAll("true",
                                                                           "false"))},
          {"Updating a Squad with an invalid (non-boolean) can assault fire setting",
           deserializedSquadJSON.replaceAll(validCanAssaultFire,
                                            validCanAssaultFire.replaceAll("true",
                                                                           "null"))},

          {"Updating a Squad with a different can spray fire setting",
           deserializedSquadJSON.replaceAll(validCanSprayFire,
                                            validCanSprayFire.replaceAll("true",
                                                                         "false"))},
          {"Updating a Squad with an invalid (non-boolean) can spray fire setting",
           deserializedSquadJSON.replaceAll(validCanSprayFire,
                                            validCanSprayFire.replaceAll("true",
                                                                         "null"))},

          {"Updating a Squad with a different smoke placement exponent value",
           deserializedSquadJSON.replaceAll(validSPE,
                                            validSPE.replaceAll("0","3"))},
          {"Updating a Squad with an invalid (non-integer) smoke placement exponent value",
           deserializedSquadJSON.replaceAll(validSPE,
                                            validSPE.replaceAll("0","null"))}
        };

        for (int i = 0;i < fromJsonSquadTestStrings.length;++i)
        {
            System.out.println("\n" + fromJsonSquadTestStrings[i][0] + ":\n");

            try
            {
                deserializedSquad.fromJSON(fromJsonSquadTestStrings[i][1]);
            }

            catch (Exception e)
            {
                System.out.println("Caught: " + e);
            }
        }

        // Leader

        String deserializedLeaderJSON = deserializedLeader.toJSON();
        String validModifier          = "\"Modifier\":-1";

        String fromJsonLeaderTestStrings[][] =
        {
          {"Updating a Leader with a different modifier value",
           deserializedLeaderJSON.replaceAll(validModifier,
                                             validModifier.replaceAll("-1","-2"))},
          {"Updating a Leader with an invalid (non-integer) modifier value",
           deserializedLeaderJSON.replaceAll(validModifier,
                                             validModifier.replaceAll("-1","null"))}
        };

        for (int i = 0;i < fromJsonLeaderTestStrings.length;++i)
        {
            System.out.println("\n" + fromJsonLeaderTestStrings[i][0] + ":\n");

            try
            {
                deserializedLeader.fromJSON(fromJsonLeaderTestStrings[i][1]);
            }

            catch (Exception e)
            {
                System.out.println("Caught: " + e);
            }
        }

        // Verify that all of the values for the Squad instance that can be
        // changed using the fromJSON() method (Identity, Status, and Portage
        // Level) work as expected.

        deserializedSquadJSON =
            deserializedSquadJSON.replaceAll(validIdentity,
                                             validIdentity.replaceAll("\"A\"",
                                                                      "\"B\""));
        deserializedSquadJSON =
            deserializedSquadJSON.replaceAll(validStatus,
                                             validStatus.replaceAll("1","0"));
        deserializedSquadJSON =
            deserializedSquadJSON.replaceAll(validPortageLevel,
                                             validPortageLevel.replaceAll("0","2"));

        deserializedSquad.fromJSON(deserializedSquadJSON);

        System.out.println("\n(Updated with fromJSON()) Squad.toJSON() output:\n\n" +
                           deserializedSquad.toJSON());

        // Test the Unit.factory() method.

        System.out.println("\nTesting the Unit.factory() method:");

        Unit unitObject = null;

        // Build JSON string for general and Leader specific Unit.factory()
        // testing.

        StringBuffer newLeaderJSON =
            new StringBuffer(JsonData.JSON_OBJECT_START);

        String factoryDescription  = "\"Description\":\"LEADER\"";
        String factoryNationality  = "\"Nationality\":\"AMERICAN\"";
        String factoryInfantryType = "\"Infantry Type\":\"NONE\"";
        String factoryModifier     = "\"Modifier\":-1";

        newLeaderJSON.append(factoryDescription + JsonData.JSON_OBJECT_SEPARATOR);
        newLeaderJSON.append(factoryNationality + JsonData.JSON_OBJECT_SEPARATOR);
        newLeaderJSON.append(factoryInfantryType + JsonData.JSON_OBJECT_SEPARATOR);
        newLeaderJSON.append("\"Morale\":8,\n");
        newLeaderJSON.append("\"Broken Morale\":8,\n");
        newLeaderJSON.append(factoryModifier + JsonData.JSON_OBJECT_SEPARATOR);
        newLeaderJSON.append(JsonData.JSON_OBJECT_END);

        String newLeaderJsonString = newLeaderJSON.toString();

        // Start with a successful (at least expected to be) generation of a
        // Leader using the new data.

        unitObject = Unit.factory(newLeaderJsonString,3);

        // Display all of the entered values for the new Leader instance
        // (created with Unit.factory()) using the toJSON() method.

        System.out.println("\n(Created with Unit.factory()) Leader.toJSON() output:\n\n" +
                           unitObject.toJSON());

        // Build JSON string for Squad specific Unit.factory() testing.

        StringBuffer newSquadJSON =
            new StringBuffer(JsonData.JSON_OBJECT_START);

        String factoryNormalRange    = "\"Normal Range\":6";
        String factoryClassification = "\"Classification\":\"FIRST_LINE\"";

        newSquadJSON.append("\"Description\":\"SQUAD\",\n");
        newSquadJSON.append(factoryNationality + JsonData.JSON_OBJECT_SEPARATOR);
        newSquadJSON.append(factoryInfantryType + JsonData.JSON_OBJECT_SEPARATOR);
        newSquadJSON.append("\"Firepower\":6,\n");
        newSquadJSON.append(factoryNormalRange + JsonData.JSON_OBJECT_SEPARATOR);
        newSquadJSON.append("\"Morale\":6,\n");
        newSquadJSON.append("\"Broken Morale\":6,\n");
        newSquadJSON.append("\"Can Self Rally ?\":false,\n");
        newSquadJSON.append("\"Basic Point Value\":11,\n");
        newSquadJSON.append("\"Has Maximum ELR ?\":false,\n");
        newSquadJSON.append(factoryClassification + JsonData.JSON_OBJECT_SEPARATOR);
        newSquadJSON.append("\"Can Assault Fire ?\":true,\n");
        newSquadJSON.append("\"Can Spray Fire ?\":false,\n");
        newSquadJSON.append("\"Smoke Placement Exponent\":3");
        newSquadJSON.append(JsonData.JSON_OBJECT_END);

        String newSquadJsonString = newSquadJSON.toString();

        // Start with a successful (at least expected to be) generation of a
        // Squad using the new data.

        unitObject = Unit.factory(newSquadJsonString,3);

        // Display all of the entered values for the new Squad instance
        // (created with Unit.factory()) using the toJSON() method.

        System.out.println("\n(Created with Unit.factory()) Squad.toJSON() output:\n\n" +
                           unitObject.toJSON());

        // (Attempt to) create Unit instances using Unit.factory() to test
        // exceptions.

        System.out.println("\nTesting Exception handling for Unit.factory() method:");

        String factoryTestStrings[][] =
        {
         // Unit

          {"Null JSON input data",null},
          {"Empty JSON input data",""},
          {"Invalid (wrong case) Description value",
           newLeaderJsonString.replaceAll(factoryDescription,
                                          factoryDescription.replaceAll("\"LEADER\"",
                                                                        "\"Leader\""))},
          {"Invalid (non-string) Description value",
           newLeaderJsonString.replaceAll(factoryDescription,
                                          factoryDescription.replaceAll("\"LEADER\"",
                                                                        "null"))},
         // Leader

          {"Invalid (wrong case) Nationality value",
           newLeaderJsonString.replaceAll(factoryNationality,
                                          factoryNationality.replaceAll("\"AMERICAN\"",
                                                                        "\"American\""))},
          {"Invalid (non-string) Nationality value",
           newLeaderJsonString.replaceAll(factoryNationality,
                                          factoryNationality.replaceAll("\"AMERICAN\"",
                                                                        "null"))},
          {"Invalid (less than minimum) modifier argument",
           newLeaderJsonString.replaceAll(factoryModifier,
                                          factoryModifier.replaceAll("-1","-4"))},
         // Squad

          {"Invalid (for nationality) Classification value",
           newSquadJsonString.replaceAll(factoryClassification,
                                         factoryClassification.replaceAll("\"FIRST_LINE\"",
                                                                          "\"SS\""))},
          {"Invalid (wrong case) Classification value",
           newSquadJsonString.replaceAll(factoryClassification,
                                         factoryClassification.replaceAll("\"FIRST_LINE\"",
                                                                          "\"Green\""))},
          {"Invalid (non-string) Classification value",
           newSquadJsonString.replaceAll(factoryClassification,
                                         factoryClassification.replaceAll("\"FIRST_LINE\"",
                                                                          "null"))}
        };

        for (int i = 0;i < factoryTestStrings.length;++i)
        {
            System.out.println("\n" + factoryTestStrings[i][0] + ":\n");

            try
            {
                unitObject = Unit.factory(factoryTestStrings[i][1],3);
            }

            catch (Exception e)
            {
                System.out.println("Caught: " + e);
            }
        }

        // Create an array of Unit objects. These will be used to reference a
        // Leader instance and several Squad instances. These class types are
        // derived from Unit.

        System.out.println("\nBuilding Unit array with a Leader & 3 Squads\n");

        Unit[] UnitList = new Unit[4];

        UnitList[0] = new Leader(Nationality.Nationalities.RUSSIAN,
                                 UnitType.InfantryTypes.COMMISSAR,9,9,3,0);

        ((Leader)UnitList[0]).setIdentity("Commissar Ryzhiy");

        UnitList[1] = new Squad(Nationality.Nationalities.RUSSIAN,
                                UnitType.InfantryTypes.GUARDS,
                                6,2,8,8,false,12,3,false,
                                Classification.Classifications.ELITE,
                                true,true,0);

        UnitList[2] = new Squad(Nationality.Nationalities.RUSSIAN,
                                UnitType.InfantryTypes.NONE,
                                4,4,7,7,false,7,3,false,
                                Classification.Classifications.FIRST_LINE,
                                false,false,0);

        UnitList[3] = new Squad(Nationality.Nationalities.RUSSIAN,
                                UnitType.InfantryTypes.NONE,
                                4,2,6,5,false,4,3,false,
                                Classification.Classifications.CONSCRIPT,
                                false,false,0);

        ((Squad)UnitList[1]).setIdentity("X");
        ((Squad)UnitList[2]).setIdentity("Y");
        ((Squad)UnitList[2]).setStatus(Status.States.BROKEN);
        ((Squad)UnitList[3]).setIdentity("Z");
        ((Squad)UnitList[3]).setStatus(Status.States.DESPERATE);

        System.out.println("Displaying Unit array with a Leader & 3 Squads");

        Leader leaderObject = null;
        Squad  squadObject  = null;

        for (int i = 0; i < 4; i++)
        {
            System.out.println("\nUnitList[" + i + "]:\t" +
                               UnitList[i].toString());

            if (Description.Descriptions.LEADER == UnitList[i].description())
            {
                leaderObject = (Leader)UnitList[i];

                System.out.println("\n" + leaderObject.description() +
                                   "\n" + leaderObject.identity() +
                                   "\n" + leaderObject.unitType() +
                                   "\n" + leaderObject.movement() +
                                   "\n" + leaderObject.status());
            }

            if (Description.Descriptions.SQUAD == UnitList[i].description())
            {
                squadObject = (Squad)UnitList[i];

                System.out.println("\n" + squadObject.description() +
                                   "\n" + squadObject.identity() +
                                   "\n" + squadObject.unitType() +
                                   "\n" + squadObject.movement() +
                                   "\n" + squadObject.status());
            }
        }

        // Create an instance of a German Squad (that throws some exceptions).

        squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                UnitType.InfantryTypes.NONE,
                                4,6,7,7,false,10,3,false,
                                Classification.Classifications.FIRST_LINE,
                                true,false,0);

        System.out.println("\nTesting Exception handling for Squad update methods:");

        // Null Identity (no error, just clears the existing one).

        squadObject.setIdentity(null);

        // Blank Identity (no error, just clears the existing one).

        squadObject.setIdentity("");

        // Invalid portage level

        System.out.println("\nInvalid portage level argument:\n");

        try
        {
            squadObject.setPortageLevel(-1);
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e);
        }

        System.out.println("\nTesting Exception handling during Squad creation:");

        // Incompatible nationality and unitType

        System.out.println("\nIncompatible nationality and unitType arguments:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.BRITISH,
                                    UnitType.InfantryTypes.ENGINEERS,
                                    4,6,7,7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Incompatible description and unitType

        System.out.println("\nIncompatible description and unitType arguments:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.RUSSIAN,
                                    UnitType.InfantryTypes.COMMISSAR,
                                    4,4,7,7,false,10,3,false,
                                    Classification.Classifications.GREEN,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Firepower

        System.out.println("\nInvalid (less than 0) firepower argument:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    -1,6,7,7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        System.out.println("\nInvalid (greater than maximum) firepower argument:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    11,6,7,7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Range

        System.out.println("\nInvalid (less than 0) normal range argument:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,-255,7,7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Morale (Minimum)

        System.out.println("\nInvalid (less than 0) morale argument:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,-1,7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Morale (Maximum)

        System.out.println("\nInvalid (greater than maximum) morale argument:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,11,7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Broken Morale (Minimum)

        System.out.println("\nInvalid (less than 0) broken morale argument:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,-7,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Broken Morale (Maximum)

        System.out.println("\nInvalid (greater than maximum) broken morale argument:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,17,false,10,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Basic Point Value (BPV)

        System.out.println("\nInvalid (less than zero) Basic Point Value (BPV):\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,7,false,-1,3,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Experience Level Rating (Minimum)

        System.out.println("\nInvalid (less than zero) Experience Level Rating (ELR):\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,7,false,10,-1,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Experience Level Rating (Maximum)

        System.out.println("\nInvalid (greater than maximum) Experience Level Rating (ELR):\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,7,false,10,6,false,
                                    Classification.Classifications.FIRST_LINE,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Incompatible Classification (only German units can be SS)

        System.out.println("\nIncompatible classification argument (nationality mismatch):\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.ITALIAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,7,false,10,3,false,
                                    Classification.Classifications.SS,
                                    true,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Incompatible Classification (Partisan units must have empty classification)

        System.out.println("\nIncompatible classification argument (invalid setting):\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.PARTISAN,
                                    UnitType.InfantryTypes.NONE,
                                    3,3,7,6,false,6,3,false,
                                    Classification.Classifications.ELITE,
                                    false,false,0);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Smoke Placement Exponent (Minimum)

        System.out.println("\nInvalid (less than zero) Smoke Placement Exponent:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,7,false,10,3,false,
                                    Classification.Classifications.SECOND_LINE,
                                    true,false,-4);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Smoke Placement Exponent (Maximum)

        System.out.println("\nInvalid (greater than maximum) Smoke Placement Exponent:\n");

        try
        {
            squadObject = new Squad(Nationality.Nationalities.GERMAN,
                                    UnitType.InfantryTypes.NONE,
                                    4,6,7,7,false,10,3,false,
                                    Classification.Classifications.SECOND_LINE,
                                    true,false,4);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Create an instance of a Canadian Leader (that throws an exception).
        // NOTE: It is only necessary to test the modifier, since all the other
        //       exceptions have been tested above as part of the creation of a
        //       Squad.

        System.out.println("\nTesting Exception handling during Leader creation:\n");

        // Invalid Modifier (Minimum)

        System.out.println("Invalid (less than minimum) modifier argument:\n");

        try
        {
            leaderObject = new Leader(Nationality.Nationalities.BRITISH,
                                      UnitType.InfantryTypes.CANADIAN,
                                      10,10,5,-4);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid Modifier (Maximum)

        System.out.println("\nInvalid (greater than maximum) modifier argument:\n");

        try
        {
            leaderObject = new Leader(Nationality.Nationalities.BRITISH,
                                      UnitType.InfantryTypes.CANADIAN,
                                      10,10,5,4);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Caught: " + e);
        }
/*
        // Test the Dice class.

        System.out.println("\nTesting the execution of the Dice class:\n");

        Dice theDice;

        for (int i = 0; i < 12; i++)
        {
            try
            {
                theDice = new Dice();

                // Show the results.

                System.out.print(theDice.toText() + "\n");
            }

            catch (IllegalStateException e)
            {
                System.out.println("Caught: " + e);
            }
        }
*/
        // Test the Scenario class.

        // Valid resource path / filename and data.

        System.out.println("\nTesting the operations of the Scenario class:");

        try
        {
            Scenario.scenario().load(Driver.class.getResourceAsStream("/scenarios/The Guards Counterattack.json"));
        }

        catch (Exception e) // Specific to reading the JSON data (not expected).
        {
            System.out.println("Caught: " + e);
        }

        // Display all of the attributes of the scenario using the toText()
        // method.

        System.out.println("\nScenario.toText() output:\n");
        System.out.print(Scenario.scenario().toText());

        // Display an abbreviated description of the scenario (its name) using
        // the toString() method.

        System.out.println("\nScenario.toString() output:\n");
        System.out.println(Scenario.scenario().toString());

        // (Attempt to) call Scenario.load() with various null objects, a bad
        // filename, and modified JSON data to test exceptions.

        System.out.println("\nTesting Exception handling for Scenario.load() method:\n");

        // Null filename (tests the load() method that accepts a String).

        System.out.println("Null filename:\n");

        try
        {
            Scenario.scenario().load((String)null);
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e);
        }

        // Invalid filename (tests the load() method that accepts a String).

        System.out.println("\nInvalid filename:\n");

        try
        {
            Scenario.scenario().load("scenarios/The Guard Counterattack.json");
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e);
        }

        // Null InputStream (tests the load() method that accepts one).

        System.out.println("\nNull InputStream:\n");

        try
        {
            Scenario.scenario().load((java.io.InputStream)null);
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e);
        }

        // Null string (tests the load() method that accepts a StringBuffer).

        System.out.println("\nNull StringBuffer:\n");

        try
        {
            Scenario.scenario().load((StringBuffer)null);
        }

        catch (Exception e)
        {
            System.out.println("Caught: " + e);
        }

        // Modified JSON data.

        // Build JSON string for Scenario (JSON) data exception handling tests.

        StringBuffer scenarioTestJSON =
            new StringBuffer(JsonData.JSON_OBJECT_START);

        String scenarioName  = "\"name\":\"Test Scenario\"";
        String startingSide  = "\"starting side\":\"ALLIES\"";
        String sides         = "\"sides\":";
        String alliedSide    = "\"side\":\"ALLIES\"";
        String elr           = "\"Experience Level Rating\":3";
        String san           = "\"SAN\":6";
        String turns         = "\"turns\":5";
        String setsUpFirst   = "\"sets up first\":false";
        String formations    = "\"formations\":";
        String formationName = "\"name\":\"A Platoon\"";
        String entryTurn     = "\"entry turn\":1";
        String units         = "\"units\":";
        String copies        = "\"copies\":3";
        String morale        = "\"Morale\":9";

        scenarioTestJSON.append(scenarioName + JsonData.JSON_OBJECT_SEPARATOR);
        scenarioTestJSON.append(startingSide + JsonData.JSON_OBJECT_SEPARATOR);

         scenarioTestJSON.append("\"sides\":\n[\n");

          scenarioTestJSON.append(JsonData.JSON_OBJECT_START + alliedSide +
                                  JsonData.JSON_OBJECT_SEPARATOR);
          scenarioTestJSON.append(elr + JsonData.JSON_OBJECT_SEPARATOR);
          scenarioTestJSON.append(san + JsonData.JSON_OBJECT_SEPARATOR);
          scenarioTestJSON.append(turns + JsonData.JSON_OBJECT_SEPARATOR);
          scenarioTestJSON.append(setsUpFirst + JsonData.JSON_OBJECT_SEPARATOR);

           scenarioTestJSON.append(formations + "\n" + "[\n" +
                                   JsonData.JSON_OBJECT_START);

           scenarioTestJSON.append(formationName +
                                   JsonData.JSON_OBJECT_SEPARATOR);
           scenarioTestJSON.append(entryTurn +
                                   JsonData.JSON_OBJECT_SEPARATOR);
            scenarioTestJSON.append(units + "\n" + "[\n");

             scenarioTestJSON.append(JsonData.JSON_OBJECT_START +
                                     factoryDescription +
                                     JsonData.JSON_OBJECT_SEPARATOR);
             scenarioTestJSON.append(factoryNationality +
                                     JsonData.JSON_OBJECT_SEPARATOR);
             scenarioTestJSON.append(factoryInfantryType +
                                     JsonData.JSON_OBJECT_SEPARATOR);
             scenarioTestJSON.append(morale + JsonData.JSON_OBJECT_SEPARATOR);
             scenarioTestJSON.append("\"Broken Morale\":9,\n");
             scenarioTestJSON.append(factoryModifier +
                                     JsonData.JSON_OBJECT_END + ",\n");

             scenarioTestJSON.append(JsonData.JSON_OBJECT_START +
                                     copies + JsonData.JSON_OBJECT_SEPARATOR +
                                     "\"Description\":\"SQUAD\"" +
                                     JsonData.JSON_OBJECT_SEPARATOR);
             scenarioTestJSON.append(factoryNationality +
                                     JsonData.JSON_OBJECT_SEPARATOR);
             scenarioTestJSON.append(factoryInfantryType +
                                     JsonData.JSON_OBJECT_SEPARATOR);
             scenarioTestJSON.append("\"Firepower\":6,\n");
             scenarioTestJSON.append(factoryNormalRange +
                                     JsonData.JSON_OBJECT_SEPARATOR);
             scenarioTestJSON.append("\"Morale\":6,\n");
             scenarioTestJSON.append("\"Broken Morale\":6,\n");
             scenarioTestJSON.append("\"Can Self Rally ?\":false,\n");
             scenarioTestJSON.append("\"Basic Point Value\":11,\n");
             scenarioTestJSON.append("\"Has Maximum ELR ?\":false,\n");
             scenarioTestJSON.append(factoryClassification +
                                     JsonData.JSON_OBJECT_SEPARATOR);
             scenarioTestJSON.append("\"Can Assault Fire ?\":true,\n");
             scenarioTestJSON.append("\"Can Spray Fire ?\":false,\n");
             scenarioTestJSON.append("\"Smoke Placement Exponent\":3");
             scenarioTestJSON.append(JsonData.JSON_OBJECT_END);

            scenarioTestJSON.append("\n]");

           scenarioTestJSON.append(JsonData.JSON_OBJECT_END);
           scenarioTestJSON.append("\n]");

          scenarioTestJSON.append(JsonData.JSON_OBJECT_END);

         scenarioTestJSON.append("\n]");

        scenarioTestJSON.append(JsonData.JSON_OBJECT_END);

//      System.out.println("\nscenarioTestJSON:\n" + scenarioTestJSON.toString());

        String scenarioTestJsonString = scenarioTestJSON.toString();

        String scenarioTestStrings[][] =
        {
         {"Empty scenario name value",
          scenarioTestJsonString.replaceAll(scenarioName,
                                            scenarioName.replaceAll("\"Test Scenario\"",
                                                                    "\"\""))},
         {"Invalid (non-string) scenario name value",
          scenarioTestJsonString.replaceAll(scenarioName,
                                            scenarioName.replaceAll("\"Test Scenario\"",
                                                                    "null"))},
         {"Invalid (wrong case) starting side value",
          scenarioTestJsonString.replaceAll(startingSide,
                                            startingSide.replaceAll("\"ALLIES\"",
                                                                    "\"Allies\""))},
         {"Invalid (non-string) starting side value",
          scenarioTestJsonString.replaceAll(startingSide,
                                            startingSide.replaceAll("\"ALLIES\"",
                                                                    "null"))},
         {"Invalid sides array entry",
          scenarioTestJsonString.replaceAll(sides,sides + "2")},
         {"Invalid (wrong case) side value",
          scenarioTestJsonString.replaceAll(alliedSide,
                                            alliedSide.replaceAll("\"ALLIES\"",
                                                                  "\"Allies\""))},
         {"Invalid (non-string) side value",
          scenarioTestJsonString.replaceAll(alliedSide,
                                            alliedSide.replaceAll("\"ALLIES\"",
                                                                  "null"))},
         {"Less than minimum experience level rating",
          scenarioTestJsonString.replaceAll(elr,elr.replaceAll("3","-1"))},
         {"Greater than maximum experience level rating",
          scenarioTestJsonString.replaceAll(elr,elr.replaceAll("3","6"))},
         {"Invalid (non-integer) experience level rating",
          scenarioTestJsonString.replaceAll(elr,elr.replaceAll("3","null"))},
         {"Less than minimum sniper activation number",
          scenarioTestJsonString.replaceAll(san,san.replaceAll("6","-1"))},
         {"Initial sniper activation number of 1",
          scenarioTestJsonString.replaceAll(san,san.replaceAll("6","1"))},
         {"Greater than maximum sniper activation number",
          scenarioTestJsonString.replaceAll(san,san.replaceAll("6","8"))},
         {"Invalid (non-integer) sniper activation number",
          scenarioTestJsonString.replaceAll(san,san.replaceAll("6","null"))},
         {"Less than minimum number of turns",
          scenarioTestJsonString.replaceAll(turns,turns.replaceAll("5","-5"))},
         {"Invalid (non-integer) number of turns",
          scenarioTestJsonString.replaceAll(turns,turns.replaceAll("5","null"))},
         {"Invalid (non-boolean) sets up first setting",
          scenarioTestJsonString.replaceAll(setsUpFirst,
                                            setsUpFirst.replaceAll("false",
                                                                   "null"))},
         {"Invalid formations array entry",
          scenarioTestJsonString.replaceAll(formations,formations + "3")},
         {"Empty formation name value",
          scenarioTestJsonString.replaceAll(formationName,
                                            formationName.replaceAll("\"A Platoon\"",
                                                                     "\"\""))},
         {"Invalid (non-string) formation name value",
          scenarioTestJsonString.replaceAll(formationName,
                                            formationName.replaceAll("\"A Platoon\"",
                                                                     "null"))},
         {"Less than minimum entry turn value",
          scenarioTestJsonString.replaceAll(entryTurn,
                                            entryTurn.replaceAll("1","-1"))},
         {"Greater than maximum entry turn value",
          scenarioTestJsonString.replaceAll(entryTurn,
                                            entryTurn.replaceAll("1","6"))},
         {"Invalid (non-integer) entry turn value",
          scenarioTestJsonString.replaceAll(entryTurn,
                                            entryTurn.replaceAll("1","null"))},
         {"Invalid units array entry",
          scenarioTestJsonString.replaceAll(units,units + "3")},
         {"Less than minimum copies value",
          scenarioTestJsonString.replaceAll(copies,
                                            copies.replaceAll("3","-1"))},
         {"Invalid (non-integer) copies value",
          scenarioTestJsonString.replaceAll(copies,
                                            copies.replaceAll("3","null"))},
         {"Out-of-range parameter value for new Unit object",
          scenarioTestJsonString.replaceAll(morale,
                                            morale.replaceAll("9","-1"))},
         {"Invalid (non-integer) parameter value for new Unit object",
          scenarioTestJsonString.replaceAll(morale,
                                            morale.replaceAll("9","null"))},
        };

        for (int i = 0;i < scenarioTestStrings.length;++i)
        {
            System.out.println("\n" + scenarioTestStrings[i][0] + ":\n");

            try
            {
                Scenario.scenario().load(new StringBuffer(scenarioTestStrings[i][1]));
            }

            catch (Exception e)
            {
                System.out.println("Caught: " + e);
            }
        }

        // Test the Game class.

        System.out.println("\nTesting the operations of the Game class:\n");

        Game.game().addPlayer(Side.Sides.ALLIES,"Pixie",
                              Nationality.Nationalities.AMERICAN,1);
        Game.game().addPlayer(Side.Sides.AXIS,"Buddy",
                              Nationality.Nationalities.GERMAN,1);

        Player alliedPlayer = Game.game().player(Side.Sides.ALLIES,"Pixie");

        alliedPlayer.addUnit("9-1 Leader");
        alliedPlayer.addUnit("7-4-7 Squad");
        alliedPlayer.addUnit("7-4-7 Squad");
        alliedPlayer.addUnit("7-4-7 Squad");

        Player axisPlayer = Game.game().player(Side.Sides.AXIS,"Buddy");

        axisPlayer.addUnit("8-1 Leader");
        axisPlayer.addUnit("6-5-8 Squad");
        axisPlayer.addUnit("6-5-8 Squad");
        axisPlayer.addUnit("6-5-8 Squad");

        System.out.print(Game.game().toText());
        System.out.println();
    }
}
