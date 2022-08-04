"""
This module provides the Dice class, which mirrors the functionality
of Dice.java.

All of the calculations (die rolls) are performed automatically when an
instance of the class is instantiated. No interpretation of the result is
performed by this class. The calling program may only retrieve the results
of the rolling of each die individually, as well as the combined result of
the white and colored dice.

Written By: Craig R. Campbell  -  October 2021
'"""

import random

class Dice:
    """
    A class used to provide the simulated result of rolling three six sided
    dice.

    When the object is created, all three dice are "rolled" automatically.
    """

    def __init__(self):
        min_roll = 1
        max_roll = 6

        # Roll the white die.

        self.__white_die_value = random.randint(min_roll,max_roll)

        # Roll the colored die.

        self.__colored_die_value = random.randint(min_roll,max_roll)

        # Sum the white and colored dice rolls.

        self.__combined_result = self.__white_die_value + self.__colored_die_value

        # Roll the subsequent die.

        self.__subsequent_die_value = random.randint(min_roll,max_roll)

    def __str__(self):
        """Returns a string describing the result of rolling the dice."""

        white_die_label       = 'white: ' + str(self.__white_die_value)
        colored_die_label     = 'colored: ' + str(self.__colored_die_value)
        combined_result_label = 'combined: ' + str(self.__combined_result)
        subsequent_die_label  = 'subsequent: ' + str(self.__subsequent_die_value)

        return '\t'.join([white_die_label,colored_die_label,combined_result_label,subsequent_die_label])

    def white_die_value(self):
        """Returns the result of rolling the white die."""

        return self.__white_die_value

    def colored_die_value(self):
        """Returns the result of rolling the colored die."""

        return self.__colored_die_value

    def combined_result(self):
        """Returns the sum of the values of the white and colored dice."""

        return self.__combined_result

    def subsequent_die_value(self):
        """Returns the result of rolling the subsequent die."""

        return self.__subsequent_die_value

# @cond TEST

if __name__ == "__main__":
    for i in range(12):
        theDice = Dice()
        print(theDice)

# @endcond
