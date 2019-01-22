package pro.bladebeat.cardLibs.cards

class Card(val suit : Suit, val rank : Rank){

    private fun getReadableCardInfo() : String{
        return "($rank"+" | "+"${suit.getReadableSuit()})"
    }

    override fun toString(): String {
        return getReadableCardInfo()
    }

    fun isJoker() : Boolean{
        return equals(Card(Suit.NULL, Rank.JOKER))
    }
//
//    override fun equals(other: Any?): Boolean {
//        return other == this
//    }
}