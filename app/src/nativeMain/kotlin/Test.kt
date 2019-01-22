import pro.bladebeat.cardLibs.Deck
import pro.bladebeat.cardLibs.GameType
import pro.bladebeat.cardLibs.cards.pile.Pile
import pro.bladebeat.cardLibs.cards.pile.PileConstructorState
import pro.bladebeat.cardLibs.team.Hand
import pro.bladebeat.cardLibs.team.Player
import pro.bladebeat.cardLibs.team.playerLogic.AIPlayerLogic
import pro.bladebeat.cardLibs.team.playerLogic.ConsoleUserPlayerLogic

fun main(args : Array<String>){

    val cardLimit = 40

    val player1 = Player("Player 1", AIPlayerLogic())
    val player2 = Player("Player 2", ConsoleUserPlayerLogic())
    val player3 = Player("Player 3", ConsoleUserPlayerLogic())
    val player4 = Player("Player 4", ConsoleUserPlayerLogic())

    val p1Hand = Hand()
    val p2Hand = Hand()
    val p3Hand = Hand()
    val p4Hand = Hand()

    val centerPile = Pile(PileConstructorState.AddableState, PileConstructorState.RemovableState, PileConstructorState.SeeableState)

    val deck = Deck.Generate(GameType.GameType500)
    deck.unlock()

    var currentHand = p1Hand
    for(i in 0 until cardLimit){
        currentHand.add(deck.draw())
//        This is shitty logic. Deal with it
        when(currentHand){
            p1Hand -> currentHand = p2Hand
            p2Hand -> currentHand = p3Hand
            p3Hand -> currentHand = p4Hand
            p4Hand -> currentHand = p1Hand
        }
    }

    p1Hand.sort()

    while(deck.size() > 0) {
        centerPile.add(deck.draw())
    }

    player1.giveNewHand(p1Hand.seeCards())
    player2.giveNewHand(p2Hand.seeCards())
    player3.giveNewHand(p3Hand.seeCards())
    player4.giveNewHand(p4Hand.seeCards())

    println(player1.seeHand())
    println(player1.doBid(null))
}