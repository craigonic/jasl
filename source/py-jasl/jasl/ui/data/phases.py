"""
This module provides the Phases enum, which mirrors the functionality
of its counterpart in Phase.java.

Written By: Craig R. Campbell  -  November 2021
"""

from enum import Enum

class Phases(Enum):
    """
    An enum class used to represent the phases within each half of a turn
    while playing ASL.

    Note that the string representation of the enum value will be returned
    as a label rather than a direct copy (e.g., "Prep Fire" instead of
    "PREP_FIRE".
    """

    RALLY = 1
    PREP_FIRE = 2
    MOVEMENT = 3
    DEFENSIVE_FIRE = 4
    ADVANCING_FIRE = 5
    ROUT = 6
    ADVANCE = 7
    CLOSE_COMBAT = 8

    __enum_attr_map__ = { 'RALLY':          ['Rally'], \
                          'PREP_FIRE':      ['Prep Fire'], \
                          'MOVEMENT':       ['Movement'], \
                          'DEFENSIVE_FIRE': ['Defensive Fire'], \
                          'ADVANCING_FIRE': ['Advancing Fire'], \
                          'ROUT':           ['Rout'], \
                          'ADVANCE':        ['Advance'], \
                          'CLOSE_COMBAT':   ['Close Combat'] }

    def __str__(self):
        """Returns the label associated with the enum value."""
        return self.__class__.__enum_attr_map__[self.name][0]

# @cond TEST

if __name__ == "__main__":
    movement = Phases.MOVEMENT
    print(movement)
    print(movement.name)
    print(movement.value)
    print(movement.__str__())
    print(movement.__repr__())

# @endcond
