enum class Suit(val suit : String) {
    HEARTS("Hearts"), CLUBS("Clubs"), SPADES("Spades"), DIAMONDS("Diamonds");

    fun isHearts() : Boolean{
        return this === HEARTS
    }

    fun isClubs() : Boolean{
        return this === CLUBS
    }

    fun isSpades() : Boolean{
        return this === SPADES
    }

    fun isDiamonds() : Boolean{
        return this === DIAMONDS
    }

    fun getReadableSuit() : String{
        return this.suit
    }
}