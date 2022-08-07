"""
This module provides the DiceRollCheck class, which uses a dice roll and
other factors to determine if special circumstances or a result may apply.

Note that the intent of this class is to provide a one or more options based
on the inputs. It may not cover every possible scenario.

Written By: Craig R. Campbell  -  December 2021
"""

from jasl.ui.data.actions import Actions
from jasl.ui.data.phases  import Phases
from jasl.utilities.dice  import Dice

class DiceRollCheck:
    """
    A class used to determine possible effects with the specified dice roll
    when considered with the other indicated factors (turn phase, action
    type, etc.).
    """

    # Twelve
    __cc_defender_may_withdraw = 'CC Defender may withdraw (A11.22)'
    __multiple_casualties_on_mc = 'Multiple Casualties on MC (A10.31)'
    __rally_suffers_casualty_reduction = \
        'Rally suffers Casualty Reduction (A10.64)'

    # Two
    __cc_attacker_may_withdraw = 'CC Attacker may withdraw (A11.22)'
    __p_leader_creation_in_cc = 'Possible Leader Creation in CC (A18.12)'
    __p_leader_creation_in_self_rally = \
        'Possible Leader Creation in first Self-Rally (A18.11)'
    __mc_yields_heat_of_battle = 'MC DR yields Heat of Battle (A15.1)'
    __non_self_rally_yields_heat_of_battle = \
        'Rally DR (not Self-Rally) yields Heat of Battle (A15.1)'

    # Doubles
    __ift_attack_cowers = 'IFT attack Cowers (A7.9)'

    def __init__(self,dice,phase,action):
        """
        Parameters
        ----------
        dice : Dice
            The results of a dice roll
        phase: Phases
            The phase of the current turn (e.g., Rally, Prep Fire, etc.)
        action: Actions
            The operation associated with the dice roll (e.g., Morale Check)
        """

        self.__dice = dice
        self.__phase = phase
        self.__action = action

#       print(self.dr_results())
#       print(self.parameters())

        self.__effects = []

        if dice.combined_result() == 12:
            self.__twelve()

#       if dice.combined_result() == 11:
#           self.__eleven()

        if dice.combined_result() == 2:
            self.__two()

        if dice.white_die_value() == dice.colored_die_value():
            self.__doubles()

    def __twelve(self):
#       print("Calling __twelve()")

        if self.__phase == Phases.CLOSE_COMBAT and self.__action == Actions.CCT:
#           print(self.__cc_defender_may_withdraw)
            self.__effects.append(self.__cc_defender_may_withdraw)

        if self.__action == Actions.MORALE_CHECK:
#           print(self.__multiple_casualties_on_mc)
            self.__effects.append(self.__multiple_casualties_on_mc)

        if self.__phase == Phases.RALLY and self.__action == Actions.RALLY:
#           print(self.__rally_suffers_casualty_reduction)
            self.__effects.append(self.__rally_suffers_casualty_reduction)

#   def __eleven(self):
#       print("Calling __eleven()")

    def __two(self):
#       print("Calling __two()")

        if self.__phase == Phases.CLOSE_COMBAT and self.__action == Actions.CCT:
#           print(self.__cc_attacker_may_withdraw)
            self.__effects.append(self.__cc_attacker_may_withdraw)
#           print(self.__p_leader_creation_in_cc)
            self.__effects.append(self.__p_leader_creation_in_cc)

        if self.__phase == Phases.RALLY and self.__action == Actions.RALLY:
#           print(self.__p_leader_creation_in_self_rally)
            self.__effects.append(self.__p_leader_creation_in_self_rally)
#           print(self.__non_self_rally_yields_heat_of_battle)
            self.__effects.append(self.__non_self_rally_yields_heat_of_battle)

        if self.__action == Actions.MORALE_CHECK:
#           print(self.__mc_yields_heat_of_battle)
            self.__effects.append(self.__mc_yields_heat_of_battle)

    def __doubles(self):
#       print("Calling __doubles()")

        if self.__action == Actions.IFT:
#           print(self.__ift_attack_cowers)
            self.__effects.append(self.__ift_attack_cowers)

    def dr_results(self):
        """Returns a string describing the dice roll results."""

        return '\t'.join(['Dice roll - ',str(self.__dice)])

    def parameters(self):
        """Returns a string describing the arguments specified to the class."""

        label  = 'Parameters - '
        phase  = 'phase: ' + str(self.__phase)
        action = 'action: ' + str(self.__action)

        return '\t'.join([label,phase,action])

    def effects(self):
        """Returns a list of strings describing possible effects associated
        with the dice roll. The list may be empty. """

        return self.__effects

    def __str__(self):
        """Returns a string describing the state of the current class instance."""
        output = '\n'.join([self.dr_results(),self.parameters()])

        if self.__effects: # List is not empty
            output = '\n'.join([output,'Effects -\n' + '\n'.join(self.__effects)])

        return output

# @cond TEST

if __name__ == "__main__":
    print(DiceRollCheck(Dice(),Phases.RALLY,Actions.RALLY))
    print(DiceRollCheck(Dice(),Phases.PREP_FIRE,Actions.IFT))
    print(DiceRollCheck(Dice(),Phases.DEFENSIVE_FIRE,Actions.MORALE_CHECK))
    print(DiceRollCheck(Dice(),Phases.CLOSE_COMBAT,Actions.CCT))

# @endcond
