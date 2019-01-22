package pro.bladebeat.cardLibs.team

import pro.bladebeat.cardLibs.cards.Card
import pro.bladebeat.cardLibs.team.playerLogic.IPlayerLogic

class Player(name : String, playerLogic : IPlayerLogic) : PlayerInfo{

    private var mName = name
    private val hand : Hand = Hand()
    private val mPlayerLogic : IPlayerLogic = playerLogic
    private var mPartner : PlayerInfo? = null

    fun setPartner(partner : PlayerInfo){
        if(mPartner != null){
            println("Cannot overwrite partner")
        } else {
            mPartner = partner
        }
    }

    fun giveNewHand(newHand : List<Card>){
        clearHand()
        for(card in newHand){
            hand.add(card)
        }
    }

    private fun clearHand(){
        hand.clear()
    }

    fun seeHand() : List<Card>{
        return hand.seeCards()
    }

    fun changeName(newName : String){
        mName = newName
    }

    override fun getName() : String{
        return mName
    }

    fun doTurn() : Card{
        return mPlayerLogic.doTurn(hand)
    }

    fun doBid(otherBids : Map<PlayerInfo, Bid>) : Bid{
        return mPlayerLogic.doBid(hand, this, otherBids)
    }

    override fun getPartner(): PlayerInfo {
        if(mPartner == null) throw UnsetPartnerException("Partner isn't set. Crashing now. Thank me later")
        return mPartner as PlayerInfo
    }

    class UnsetPartnerException(message: String?) : RuntimeException(message)
}