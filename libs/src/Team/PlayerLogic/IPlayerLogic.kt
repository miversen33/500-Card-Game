package pro.bladebeat.cardLibs.team.playerLogic

import pro.bladebeat.cardLibs.cards.Card
import pro.bladebeat.cardLibs.team.Bid
import pro.bladebeat.cardLibs.team.Hand
import pro.bladebeat.cardLibs.team.PlayerInfo
import pro.bladebeat.cardLibs.team.playerLogic.bid.IBidLogic
import pro.bladebeat.cardLibs.team.playerLogic.turn.ITurnLogic

interface IPlayerLogic : IBidLogic, ITurnLogic{
    override fun doBid(hand : Hand?, selfPlayer: PlayerInfo , otherBids : Map<PlayerInfo, Bid>) : Bid
    override fun doTurn(hand : Hand?) : Card
}