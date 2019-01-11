
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
    deck.unlock()
    deck.shuffle()
    val card1 = deck.draw()
    val card2 = deck.draw()
    val card3 = deck.draw()
    val card4 = deck.draw()
    deck.lock()
    var i : Int = 0
}