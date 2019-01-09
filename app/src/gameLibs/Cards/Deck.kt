package Cards

import CardHand
import java.util.*

class Deck(cards : MutableList<Card>) : CardHand() {

    private var deckLock : Boolean = false

    init {
        for(card : Card in cards){
            addCard(card)
        }
        lockDeck()
    }

    override fun addCard(newCard: Card) {
        if(!deckLock){
            super.addCard(newCard)
        } else {
//            We should probably create a custom logger for this
            println("Cant add cards to the deck. Cheater")
        }
    }

    fun shuffleDeck(){
        unlockDeck()
        seeCards().shuffle()
        lockDeck()
    }

    fun drawCard() : Card {
        unlockDeck()
        val topCard : Card = seeCards()[0]
        lockDeck()
        removeCard(topCard)
        return topCard
    }

    private fun unlockDeck(){
        deckLock = false
    }

    private fun lockDeck(){
        deckLock = true
    }

    //    You dont get to see the cards in the deck bucko
    override fun seeCards(): MutableList<Card> {
        when(deckLock){
            false -> return super.seeCards()
            else -> {
                println("You dont get the see the cards in the deck bucko")
                return ArrayList()
            }
        }
    }
}