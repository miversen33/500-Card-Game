import Cards.Card

open class CardHand{

    private val mHand : MutableList<Card> = ArrayList()

    open fun addCard(newCard : Card){
        mHand.add(newCard)
    }

    open fun seeCards() : MutableList<Card>{
        return mHand
    }

    fun removeCard(card : Card) : Card{
        mHand.remove(card)
        return card
    }

    fun getHandSize() : Int{
        return mHand.size
    }

}