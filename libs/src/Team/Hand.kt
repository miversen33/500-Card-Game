package pro.bladebeat.cardLibs.team

import pro.bladebeat.cardLibs.cards.Card
import pro.bladebeat.cardLibs.cards.Suit
import pro.bladebeat.cardLibs.cards.pile.Pile
import pro.bladebeat.cardLibs.cards.pile.PileConstructorState

class Hand : Pile(PileConstructorState.AddableState, PileConstructorState.RemovableState, PileConstructorState.SeeableState){

    fun sort(){
        val suitMap : HashMap<Suit, ArrayList<Card>> = HashMap(4)
        suitMap[Suit.SPADES] = ArrayList()
        suitMap[Suit.CLUBS] = ArrayList()
        suitMap[Suit.HEARTS] = ArrayList()
        suitMap[Suit.DIAMONDS] = ArrayList()
        var joker : Card? = null

        for(card in seeCards()){
            if(card.suit == Suit.NULL){
                joker = card
            } else {
                suitMap[card.suit]!!.add(card)
            }
        }

        for(cardList in suitMap.values){
//            TODO("Need to change this so it sorts largest to smallest, not smallest to largest")
            cardList.sortBy { it.rank }
        }

        val unSortedHand : MutableCollection<ArrayList<Card>> = suitMap.values
        unSortedHand.sortedBy { it.size }
        val newHand : ArrayList<Card> = ArrayList()
        if(joker != null) newHand.add(joker)
        for(suit in unSortedHand) newHand.addAll(suit)

        clear()
        add(newHand)
    }

    fun getNumberOfCardsInSuit(suit : Suit) : Int{
        return getCardsBySuit(suit).size()
    }

}