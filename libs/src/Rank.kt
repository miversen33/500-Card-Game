package Cards

enum class Rank(val numericValue : Int, val isFaceCard : Boolean = false){
    TWO(2),
    THREE(3),
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
//    Should probably figure out how to dictate Ace as High or Low
    ACE(14),
    JOKER(16);

    fun getRank() : Int {
        return this.numericValue
    }
}