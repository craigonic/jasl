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

#include "jasl/jni/counters/Descriptions.h"
#include "jasl/jni/utilities/Dice.h"

#include <assert.h>
#include <memory>

using namespace jasl::counters;
using namespace jasl::utilities;

int main()
{
    // Test the Descriptions enum.

    printf("\nTesting conversion operations of the Descriptions enum:\n\n");

    printf("toString(Descriptions::Crew) == %s\n",
           toString(Descriptions::Crew).c_str());
    printf("toString(Descriptions::HalfSquad) == %s\n",
           toString(Descriptions::HalfSquad).c_str());
    printf("toString(Descriptions::Leader) == %s\n",
           toString(Descriptions::Leader).c_str());
    printf("toString(Descriptions::Squad) == %s\n",
           toString(Descriptions::Squad).c_str());

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
