package pro.bladebeat.cardLibs.team.playerLogic.turn

import pro.bladebeat.cardLibs.cards.Card
import pro.bladebeat.cardLibs.team.Hand

interface ITurnLogic {
    fun doTurn(hand: Hand?) : Card
}