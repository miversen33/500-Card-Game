package Cards

import Pile.Pile
import Pile.PileConstructorState

class Deck(deck : MutableList<Card>) : Pile(PileConstructorState.AddableState, PileConstructorState.RemovableState, PileConstructorState.ShuffleableState, PileConstructorState.LockableState){

    init {
        unlock()
        for(card in deck){
            add(card)
        }
        lock()
    }

}