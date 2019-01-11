package Cards

class Card(val suit : Suit, val rank : Rank){

    fun getReadableCardInfo() : String{
        return "(${suit.getReadableSuit()}"+" | "+"${rank.getRank()})"
    }

    override fun toString(): String {
        return getReadableCardInfo()
    }
}