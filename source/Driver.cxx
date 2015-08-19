// ************************************************************************** //
// Driver.cxx - This file contains the Driver program, which is used to test  //
//              the functionality of the Java classes defined in the jasl     //
//              package hierarchy. It is implemented to use wrapper classes   //
//              that interact with libraries generated using <A HREF="http://gcc.gnu.org/java/">GCJ</A>, through the //
//              <A HREF="http://gcc.gnu.org/onlinedocs/gcj/About-CNI.html#About-CNI">CNI</A> (Compiled Native Interface).                              //
//                                                                            //
//              The calls within this file are specific to the C++ API of the //
//              wrapper classes. A C++11 compiler is required to build this   //
//              program.                                                      //
//                                                                            //
//              NOTE: This program is based on Advanced Squad Leader, which   //
//                    was created by The Avalon Hill Game Company, and lives  //
//                    on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                           //
//                                                                            //
// Written By: Craig R. Campbell  -  August 2015                              //
// ************************************************************************** //

#include <memory>   // For unique_ptr().
#include <stdio.h>  // For printf().

#include "jasl/cni/CniWrapper.h"
#include "jasl/cni/utilities/Dice.h"

int main(int argc,char *argv[])
{
    auto cniWrapper = std::unique_ptr<CniWrapper>(CniWrapper::instance());

    // Test the Dice class.

    printf("\nTesting the execution of the Dice class:\n\n");

    for (int i = 0;i < 12;i++)
    {
        auto theDice = std::unique_ptr<Dice>(new Dice);

        if (theDice)
        {
//          printf("White: %d Colored: %d Combined: %2d\n",
//                 theDice->whiteDieValue(),
//                 theDice->coloredDieValue(),
//                 theDice->combinedResult());

            printf("%s\n",theDice->toText());
        }

        else printf("New Dice object generation failed\n");
    }
}
