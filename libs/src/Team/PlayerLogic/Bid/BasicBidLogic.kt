package pro.bladebeat.cardLibs.team.playerLogic.bid

import pro.bladebeat.cardLibs.cards.Card
import pro.bladebeat.cardLibs.cards.Rank
import pro.bladebeat.cardLibs.cards.Suit
import pro.bladebeat.cardLibs.team.Bid
import pro.bladebeat.cardLibs.team.Hand
import pro.bladebeat.cardLibs.team.PlayerInfo

class BasicBidLogic : IBidLogic {

    override fun doBid(hand: Hand?, selfPlayer: PlayerInfo, otherBids: Map<PlayerInfo, Bid>): Bid {
//        This is no where near perfect. We need to value certain cards more for suit bidding.
        /** Bid is based on
         * -Total number of cards in suit
         * -Total value of cards in suit
         * -Some amount if we have opposite suit jack
         * -Some amount if we have a joker
         * -Some amount for partner suit bid
         */
//        Holy ugly batman. We need to clean and simplify this more

        var partnerBid = otherBids[selfPlayer.getPartner()]
        var suitValues = HashMap<Suit, Int>()
        val hasJoker = hand!!.contains(Card(Suit.NULL, Rank.JOKER))
        var winningSuit : Suit = Suit.NULL
        var bidValue = 1
        var winningSuitValue = 0
        for(suit in Suit.getAllSuits()){
            var value = 0
            var cards = hand.getCardsBySuit(suit)
            var count = cards.size()
            var partnerSameSuitBonus = if (partnerBid != null && partnerBid.bidSuit == suit) 1 else 0
            for(card in cards.seeCards()) value += card.rank.numericValue
            if(cards.contains(Card(suit.getOppositeSuit(), Rank.JACK))) value += 2
            if(hasJoker) value += 1
            value += count
            value += partnerSameSuitBonus

            suitValues[suit] = value
            if(value > winningSuitValue){
                winningSuit = suit
                winningSuitValue = value
            }
        }

        var midValueUsed = false
        for(card in hand.getCardsBySuit(winningSuit)){
            if(card.rank.isFaceCard){
                bidValue += 1
                continue
            }
            if(card.rank.numericValue in 7..10 && !midValueUsed){
                bidValue += 1
                midValueUsed = true
                continue
            }
        }
        if(hand.getCardsBySuit(winningSuit.getOppositeSuit()).contains(Card(winningSuit.getOppositeSuit(), Rank.JACK))) bidValue += 2
        for(suit in Suit.getAllSuits()){
            if(suit == winningSuit) continue
            val cardsBySuit = hand.getCardsBySuit(suit)
            if(suit.getOppositeSuit() == winningSuit){
                if(cardsBySuit.getCardsByRank(Rank.KING).size()  > 0 ||
                    cardsBySuit.getCardsByRank(Rank.QUEEN).size() > 0 ) bidValue +=1
            }else{
                if(cardsBySuit.getCardsByRank(Rank.KING).size()  > 0 ||
                    cardsBySuit.getCardsByRank(Rank.QUEEN).size() > 0 ||
                    cardsBySuit.getCardsByRank(Rank.JACK).size()  > 0) bidValue +=1
            }
        }
        bidValue += (hand.getCardsByRank(Rank.JOKER).size() * 2)
        bidValue += hand.getCardsByRank(Rank.ACE).size()

        if(bidValue == 4 || bidValue == 5) bidValue = 6

        if(bidValue <  4) return Bid(0, Suit.NULL)
        return Bid(bidValue, winningSuit)
    }
}