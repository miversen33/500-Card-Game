class Card(val suit : Suit, val rank : Rank){

    fun getSuit() : Suit{
        return suit
    }

    fun getRank() : Rank{
        return rank;
    }

    fun getReadableCardInfo() : String{
        return getSuit().getReadableSuit()
    }
}