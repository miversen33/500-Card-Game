package pro.bladebeat.cardLibs.cards

enum class Suit(val suit : String) {
    HEARTS("Hearts"), CLUBS("Clubs"), SPADES("Spades"), DIAMONDS("Diamonds");

    companion object {
        fun getAllSuits() : List<Suit>{
            return listOf(HEARTS, CLUBS, SPADES, DIAMONDS)
        }
    }

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