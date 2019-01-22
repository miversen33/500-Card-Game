package pro.bladebeat.cardLibs.team.playerLogic

import pro.bladebeat.cardLibs.cards.Card
import pro.bladebeat.cardLibs.team.Bid
import pro.bladebeat.cardLibs.team.Hand
import pro.bladebeat.cardLibs.team.PlayerInfo
import pro.bladebeat.cardLibs.team.playerLogic.bid.IBidLogic
import pro.bladebeat.cardLibs.team.playerLogic.turn.ITurnLogic

class AIPlayerLogic(bidLogic : IBidLogic, turnLogic : ITurnLogic) : IPlayerLogic{

    private val mBidLogic : IBidLogic = bidLogic
    private val mTurnLogic : ITurnLogic = turnLogic

    override fun doBid(hand: Hand?, selfPlayer : PlayerInfo, otherBids: Map<PlayerInfo, Bid>): Bid {
        return mBidLogic.doBid(hand, selfPlayer, otherBids)
    }

    override fun doTurn(hand: Hand?): Card {
        return mTurnLogic.doTurn(hand)
    }

}