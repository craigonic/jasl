// ************************************************************************** //
// Driver.cxx - This file contains the Driver program, which is used to test  //
//              the functionality of the Java classes defined in the jasl     //
//              package hierarchy, accessed through libraries that interact   //
//              with the Java bytecode via the <A HREF="https://docs.oracle.com/javase/8/docs/technotes/guides/jni/">JNI</A> (Java Native Interface).   //
//                                                                            //
//              NOTE: This program is based on Advanced Squad Leader, which   //
//                    was created by The Avalon Hill Game Company, and lives  //
//                    on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                           //
//                                                                            //
// Written By: Craig R. Campbell  -  August 2015                              //
// ************************************************************************** //

#include "jasl/jni/counters/Classifications.h"
#include "jasl/jni/counters/Descriptions.h"
#include "jasl/jni/counters/InfantryTypes.h"
#include "jasl/jni/counters/Nationalities.h"
#include "jasl/jni/utilities/Dice.h"

#include <assert.h>
#include <memory>

using namespace jasl::counters;
using namespace jasl::utilities;

int main()
{
    // Test the counters enums.

    printf("\nTesting conversion operations of the Classifications enum:\n\n");

    printf("toString(Classifications::SS) == %s\n",
           toString(Classifications::SS).c_str());
    printf("toString(Classifications::Elite) == %s\n",
           toString(Classifications::Elite).c_str());
    printf("toString(Classifications::FirstLine) == %s\n",
           toString(Classifications::FirstLine).c_str());
    printf("toString(Classifications::SecondLine) == %s\n",
           toString(Classifications::SecondLine).c_str());
    printf("toString(Classifications::Green) == %s\n",
           toString(Classifications::Green).c_str());
    printf("toString(Classifications::Conscript) == %s\n",
           toString(Classifications::Conscript).c_str());
    printf("toString(Classifications::None) == \"%s\"\n",
           toString(Classifications::None).c_str());

    printf("\nTesting conversion operations of the Descriptions enum:\n\n");

    printf("toString(Descriptions::Crew) == %s\n",
           toString(Descriptions::Crew).c_str());
    printf("toString(Descriptions::HalfSquad) == %s\n",
           toString(Descriptions::HalfSquad).c_str());
    printf("toString(Descriptions::Leader) == %s\n",
           toString(Descriptions::Leader).c_str());
    printf("toString(Descriptions::Squad) == %s\n",
           toString(Descriptions::Squad).c_str());

    printf("\nTesting conversion operations of the InfantryTypes enum:\n\n");

    printf("toString(InfantryTypes::None) == \"%s\"\n",
           toString(InfantryTypes::None).c_str());
    printf("toString(InfantryTypes::Paratroops) == %s\n",
           toString(InfantryTypes::Paratroops).c_str());
    printf("toString(InfantryTypes::Airborne) == %s\n",
           toString(InfantryTypes::Airborne).c_str());
    printf("toString(InfantryTypes::Anzac) == %s\n",
           toString(InfantryTypes::Anzac).c_str());
    printf("toString(InfantryTypes::Canadian) == %s\n",
           toString(InfantryTypes::Canadian).c_str());
    printf("toString(InfantryTypes::FreeFrench) == %s\n",
           toString(InfantryTypes::FreeFrench).c_str());
    printf("toString(InfantryTypes::FreePolish) == %s\n",
           toString(InfantryTypes::FreePolish).c_str());
    printf("toString(InfantryTypes::Guardsmen) == %s\n",
           toString(InfantryTypes::Guardsmen).c_str());
    printf("toString(InfantryTypes::Gurkha) == %s\n",
           toString(InfantryTypes::Gurkha).c_str());
    printf("toString(InfantryTypes::Sissi) == %s\n",
           toString(InfantryTypes::Sissi).c_str());
    printf("toString(InfantryTypes::Engineers) == %s\n",
           toString(InfantryTypes::Engineers).c_str());
    printf("toString(InfantryTypes::Commissar) == %s\n",
           toString(InfantryTypes::Commissar).c_str());
    printf("toString(InfantryTypes::Guards) == %s\n",
           toString(InfantryTypes::Guards).c_str());

    printf("\nTesting conversion operations of the Nationalities enum:\n\n");

    printf("toString(Nationalities::AlliedMinor) == %s\n",
           toString(Nationalities::AlliedMinor).c_str());
    printf("toString(Nationalities::American) == %s\n",
           toString(Nationalities::American).c_str());
    printf("toString(Nationalities::AxisMinor) == %s\n",
           toString(Nationalities::AxisMinor).c_str());
    printf("toString(Nationalities::British) == %s\n",
           toString(Nationalities::British).c_str());
    printf("toString(Nationalities::Finnish) == %s\n",
           toString(Nationalities::Finnish).c_str());
    printf("toString(Nationalities::French) == %s\n",
           toString(Nationalities::French).c_str());
    printf("toString(Nationalities::German) == %s\n",
           toString(Nationalities::German).c_str());
    printf("toString(Nationalities::Italian) == %s\n",
           toString(Nationalities::Italian).c_str());
    printf("toString(Nationalities::Japanese) == %s\n",
           toString(Nationalities::Japanese).c_str());
    printf("toString(Nationalities::Partisan) == %s\n",
           toString(Nationalities::Partisan).c_str());
    printf("toString(Nationalities::Russian) == %s\n",
           toString(Nationalities::Russian).c_str());

    // Test the Dice class.

    printf("\nTesting the execution of the Dice class:\n\n");

    for (int i = 0;i < 12;i++)
    {
        const auto& theDice = std::make_unique<Dice>();

        assert(nullptr != theDice);

//      printf("White: %d Colored: %d Combined: %2d Subsequent: %d\n",
//             theDice->whiteDieValue(),
//             theDice->coloredDieValue(),
//             theDice->combinedResult(),
//             theDice->subsequentDieValue());

        printf("%s\n",theDice->toText().c_str());
    }
}
