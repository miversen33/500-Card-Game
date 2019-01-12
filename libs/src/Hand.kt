package Hand

import pro.bladebeat.cardLibs.cards.pile.Pile
import pro.bladebeat.cardLibs.cards.pile.PileConstructorState

class Hand : Pile(PileConstructorState.AddableState, PileConstructorState.RemovableState, PileConstructorState.SeeableState)