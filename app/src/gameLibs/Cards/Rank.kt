package Cards

enum class Rank(val numericValue : Int, val isFaceCard : Boolean = false){
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11, true),
    QUEEN(12, true),
    KING(13, true),
    ACE(14),
    //    Not sure if this should be 14 or 0
    JOKER(16);

    fun getRank() : Int {
        return this.numericValue
    }
}