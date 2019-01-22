package pro.bladebeat.cardLibs.team.playerLogic

import pro.bladebeat.cardLibs.cards.Card
import pro.bladebeat.cardLibs.cards.Suit
import pro.bladebeat.cardLibs.team.Bid
import pro.bladebeat.cardLibs.team.Hand
import pro.bladebeat.cardLibs.team.PlayerInfo

class ConsoleUserPlayerLogic : IPlayerLogic {

    private val BID_PROMPT = "Please enter your bid as Number, Followed by Rank, or Null. Example is 10,H or 6,D, 0 or null are both acceptable"
    private val REDO_BID_PROMPT ="You did not enter a proper bid"
    private val NULL_BID = "NULL"

    /**
     * @hand is not used
     */
    override fun doBid(hand : Hand?, selfPlayer : PlayerInfo, otherBids : Map<PlayerInfo, Bid>): Bid {
        var bid = checkBidFormatting(getBid(false))
        while(bid === null) bid = checkBidFormatting(getBid(true))
        return bid
    }

    private fun getBid(redo : Boolean) : String?{
        if(redo)println(REDO_BID_PROMPT)
        println(BID_PROMPT)
        return readLine()
    }

    private fun checkBidFormatting(bid : String?) : Bid?{
        if(bid === null || bid.isBlank() || bid.isEmpty()) return null
//        Some regex magic to check if the string is not just a plain number
        if(!bid.contains(",") && !bid.matches("-?\\d+(\\.\\d+)?".toRegex())) return null
        if((bid.matches("-?\\d+(\\.\\d+)?".toRegex()) && Integer.parseInt(bid) == 0) || bid.toUpperCase() == NULL_BID) return Bid(0,Suit.NULL)
        if(bid.matches("-?\\d+(\\.\\d+)?".toRegex()) && (Integer.parseInt(bid) > 0 || Integer.parseInt(bid) < 0)) return null
        val valueS = bid.split(",")[0]
        val suitS = bid.split(",")[1]
        if(valueS.isBlank() || valueS.isEmpty() || suitS.isBlank() || suitS.isEmpty()) return null
        val value = Integer.parseInt(valueS)
        val suit = Suit.parseSuit(suitS)
        println("Value Received is $value")
        println("Suit received is $suit")
        return Bid(value, suit)
    }

    override fun doTurn(hand : Hand?): Card {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}