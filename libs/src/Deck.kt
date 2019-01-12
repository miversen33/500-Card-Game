package pro.bladebeat.cardLibs

import pro.bladebeat.cardLibs.cards.Card
import pro.bladebeat.cardLibs.cards.Rank
import pro.bladebeat.cardLibs.cards.Suit
import pro.bladebeat.cardLibs.cards.pile.Pile
import pro.bladebeat.cardLibs.cards.pile.PileConstructorState

class Deck(deck : MutableList<Card>) : Pile(PileConstructorState.AddableState, PileConstructorState.RemovableState, PileConstructorState.ShuffleableState, PileConstructorState.LockableState){

    init {
        unlock()
        for(card in deck){
            add(card)
        }
        lock()
    }

    companion object {

        private const val MAX_SHUFFLES =  7

        fun Generate(type : DeckType, preShuffled : Boolean = true) : Deck{
            var deck : Deck
            when(type){
                DeckType.GameType500 -> deck = generate500Deck()
                else -> deck = generateStandardDeck()
            }
            if(preShuffled){
                deck.unlock()
                for(i in 0 .. MAX_SHUFFLES) {
                    deck.shuffle()
                }
                deck.lock()
            }
            return deck
        }

        private fun createAllCards() : MutableList<Card>{
            val cards : MutableList<Card> = ArrayList()
            for(rank in Rank.getAllRanks()){
//              Jokers dont have Suits, and there is usually only 2 of them
//              So we will make them at the end
                if(rank === Rank.JOKER) continue
                for(suit in Suit.getAllSuits()){
                    cards.add(Card(suit, rank))
                }
            }
            cards.add(Card(Suit.HEARTS, Rank.JOKER))
            cards.add(Card(Suit.DIAMONDS, Rank.JOKER))

            return cards
        }

        private fun generateStandardDeck() : Deck{
            return Deck(createAllCards())
        }

        private fun generate500Deck() : Deck {
            val deck : Deck = generateStandardDeck()
            deck.unlock()
            for(i in 0 .. 7){
                deck.draw()
            }
            deck.remove(deck.size()-1)
            return deck
        }
    }
}