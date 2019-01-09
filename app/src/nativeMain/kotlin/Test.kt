
import Cards.Card
import Cards.Deck
import Cards.Rank
import Cards.Suit

fun main(args : Array<String>){
    val cardCA = Card(Suit.CLUBS, Rank.ACE)
    val cardSA = Card(Suit.SPADES, Rank.ACE)
    val cardHA = Card(Suit.HEARTS, Rank.ACE)
    val cardDA = Card(Suit.DIAMONDS, Rank.ACE)

    val deck : Deck = Deck(mutableListOf(cardCA, cardSA, cardHA, cardDA))
    deck.shuffleDeck()
    val card1 = deck.drawCard()
    val card2 = deck.drawCard()
    val card3 = deck.drawCard()
    val card4 = deck.drawCard()
    var i : Int = 0
}