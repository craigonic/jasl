// ************************************************************************** //
// Driver.c - This file contains the Driver program, which is used to test    //
//            the functionality of the Java classes defined in the jasl       //
//            package hierarchy. It is implemented to use wrapper classes     //
//            that interact with libraries generated using <A HREF="http://gcc.gnu.org/wiki/GCJ/">GCJ</A>, through the   //
//            <A HREF="http://gcc.gnu.org/onlinedocs/gcc-6.4.0/gcj/About-CNI.html#About-CNI">CNI</A> (Compiled Native Interface).                                //
//                                                                            //
//            The calls within this file are specific to the C API of the     //
//            wrapper classes.                                                //
//                                                                            //
//            NOTE: This program is based on Advanced Squad Leader, which was //
//                  created by The Avalon Hill Game Company, and lives on at  //
//                  <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                                   //
//                                                                            //
// Written By: Craig R. Campbell  -  August 2015                              //
// ************************************************************************** //

#include <stdio.h>  // For printf().

#include "jasl/cni/CniWrapper.h"
#include "jasl/cni/utilities/Dice.h"

int main(int argc,char *argv[])
{
    const CniWrapper* cniWrapper = startCniWrapper();

    if (cniWrapper)
    {
        // Test the Dice class.

        printf("\nTesting the execution of the Dice class:\n\n");

        int i;

        for (i = 0;i < 12;i++)
        {
            Dice* theDice = rollDice();

            if (theDice)
            {
//              printf("White: %d Colored: %d Combined: %2d\n",
//                     whiteDieValue(theDice),
//                     coloredDieValue(theDice),
//                     combinedResult(theDice));

                printf("%s\n",toText(theDice));

                deleteDice(theDice);
            }

            else printf("New Dice object generation failed\n");
        }

        stopCniWrapper();
    }

    else printf("New CniWrapper object generation failed\n");

    return 0;
}
