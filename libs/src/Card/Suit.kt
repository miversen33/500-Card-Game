package pro.bladebeat.cardLibs.cards

enum class Suit(val suit : String) {
    HEARTS("Hearts"), CLUBS("Clubs"), SPADES("Spades"), DIAMONDS("Diamonds"), NULL ("Null Suit");

    companion object {
        /**
         * This will not return the null Suit
         */
        fun getAllSuits() : List<Suit>{
            return listOf(HEARTS, CLUBS, SPADES, DIAMONDS)
        }

        fun parseSuit(suit : String) : Suit{
            if(HEARTS.suit.startsWith(suit)) return HEARTS
            if(CLUBS.suit.startsWith(suit)) return CLUBS
            if(SPADES.suit.startsWith(suit)) return SPADES
            if(DIAMONDS.suit.startsWith(suit)) return DIAMONDS
            println("Received odd suit. Returning Null Suit")
            return NULL
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

    fun getOppositeSuit() : Suit{
        return when{
            isHearts() -> DIAMONDS
            isDiamonds() -> HEARTS
            isSpades() -> CLUBS
            isClubs() -> SPADES
            else -> NULL
        }
    }
    fun getReadableSuit() : String{
        return this.suit
    }
}