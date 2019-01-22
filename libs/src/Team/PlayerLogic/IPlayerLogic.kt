package pro.bladebeat.cardLibs.team.playerLogic

import pro.bladebeat.cardLibs.cards.Card
import pro.bladebeat.cardLibs.team.Bid
import pro.bladebeat.cardLibs.team.Hand
import pro.bladebeat.cardLibs.team.PlayerInfo

interface IPlayerLogic {
    fun doBid(hand : Hand?, otherBids : Map<PlayerInfo, Bid>?) : Bid
    fun doTurn(hand : Hand?) : Card
}