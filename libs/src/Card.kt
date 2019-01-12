package pro.bladebeat.cardLibs.cards

class Card(val suit : Suit, val rank : Rank){

    fun getReadableCardInfo() : String{
        return "($rank"+" | "+"${suit.getReadableSuit()})"
    }

    override fun toString(): String {
        return getReadableCardInfo()
    }

    override fun equals(other: Any?): Boolean {
        return other == this
    }
}