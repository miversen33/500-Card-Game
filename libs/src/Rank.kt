package pro.bladebeat.cardLibs.cards

enum class Rank(val numericValue : Int, val readableName : String = "", val isFaceCard : Boolean = false){
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10," 10"),
    JACK(11, "Jack", true),
    QUEEN(12, "Queen", true),
    KING(13, "King", true),
//    Should probably figure out how to dictate Ace as High or Low
    ACE(14, "Ace"),
    JOKER(16, "Joker");



    companion object {
        fun getAllRanks() : List<Rank>{
            return listOf<Rank>(TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE, JOKER)
        }
    }

    fun getRank() : Int {
        return this.numericValue
    }

    override fun toString(): String {
        return readableName
    }
}