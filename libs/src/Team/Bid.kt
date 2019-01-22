package pro.bladebeat.cardLibs.team

import pro.bladebeat.cardLibs.cards.Suit

data class Bid(val bidAmount : Int, val bidSuit: Suit){
    override fun toString(): String {
        return "($bidAmount,${bidSuit.getReadableSuit()})"
    }
}