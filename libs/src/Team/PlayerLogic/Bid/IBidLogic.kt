package pro.bladebeat.cardLibs.team.playerLogic.bid

import pro.bladebeat.cardLibs.team.Bid
import pro.bladebeat.cardLibs.team.Hand
import pro.bladebeat.cardLibs.team.PlayerInfo

interface IBidLogic {
    fun doBid(hand: Hand?, selfPlayer: PlayerInfo, otherBids: Map<PlayerInfo, Bid>) : Bid
}