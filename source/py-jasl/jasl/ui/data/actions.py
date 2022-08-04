"""
This module provides the Actions enum.

Written By: Craig R. Campbell  -  November 2021
"""

from enum import Enum

class Actions(Enum):
    """
    An enum class used to represent operations that can be performed by or
    with an ASL counter.

    Note that the string representation of the enum value will be returned
    as a label rather than a direct copy (e.g., "Morale Check" instead of
    "MORALE_CHECK").
    """

    CCT = 1
    IFT = 2
    MORALE_CHECK = 3
    RALLY = 4

    __enum_attr_map__ = { 'CCT':          ['Close Combat Table'], \
                          'IFT':          ['Infantry Fire Table'], \
                          'MORALE_CHECK': ['Morale Check'], \
                          'RALLY':        ['Rally'] }

    def __str__(self):
        """Returns the label associated with the enum value."""
        return self.__class__.__enum_attr_map__[self.name][0]

# @cond TEST

if __name__ == "__main__":
    mc = Actions.MORALE_CHECK
    print(mc)
    print(mc.name)
    print(mc.value)
    print(mc.__str__())
    print(mc.__repr__())

# @endcond
