package pro.bladebeat.cardLibs.cards.pile

import pro.bladebeat.cardLibs.cards.Card
import pro.bladebeat.cardLibs.cards.Rank
import pro.bladebeat.cardLibs.cards.Suit

open class Pile(vararg pileConstructors : PileConstructorState) : Iterable<Card>{

    private val mPile : MutableList<Card> = ArrayList()
    private val mPermissions : List<PileConstructorState> = pileConstructors.asList()
    private var mLock : Boolean = false


    override fun iterator(): Iterator<Card> {
        if(!mPermissions.contains(PileConstructorState.SeeableState)) throw MissingPileConstructorException("PileConstructorState Seeable is not Present")
        return PileIterator(this)
    }

    fun add(card : Card){
        if(mLock) println("Cant add cards to locked pile")
        when(mPermissions.contains(PileConstructorState.AddableState)){
            true -> mPile.add(card)
            false -> throw MissingPileConstructorException("PileConstructorState Addable is not Present")
        }
    }

    fun add(pile : Pile){
        if(mLock) println("Cant add cards to locked pile")
        when(mPermissions.contains(PileConstructorState.AddableState)){
            true -> mPile.addAll(pile.mPile)
            false -> throw MissingPileConstructorException("PileConstructorState Addable is not Present")
        }
    }

    fun add(cards : List<Card>){
        if(mLock) println("Cant add cards to locked pile")
        when(mPermissions.contains(PileConstructorState.AddableState)){
            true -> mPile.addAll(cards)
            false -> throw MissingPileConstructorException("PileConstructorState Addable is not Present")
        }
    }

//    This is basically a "pop" function. It will return the top card of the pile, if
//    the pile allows it
    fun draw() : Card{
        when(mPermissions.contains(PileConstructorState.RemovableState)){
            true -> return remove(0)
            false -> throw MissingPileConstructorException("PileConstructorState Removable is not Present")
        }
    }

    fun remove(locationInPile : Int) : Card{
        if(mLock) throw LockedPileException("Cant remove cards from locked pile")
        when(mPermissions.contains(PileConstructorState.RemovableState) && !mLock) {
            true -> return mPile.removeAt(locationInPile)
            false -> throw MissingPileConstructorException("PileConstructorState Removable is not Present")
        }
    }

    fun removeCard(card : Card){
        if(mLock) throw LockedPileException("Cant remove cards from locked pile")
        when(mPermissions.contains(PileConstructorState.RemovableState)){
            true -> mPile.remove(card)
            false -> throw MissingPileConstructorException("PileConstructorState Removable is not Present")
        }
    }

    fun clear(){
        if(mLock) throw LockedPileException("Cant clear cards from locked pile")
        when(mPermissions.contains(PileConstructorState.RemovableState)){
            true -> mPile.clear()
            false -> throw MissingPileConstructorException("PileConstructorState Removable is not Present")
        }
    }

    fun seeCards() : List<Card>{
        when(mPermissions.contains(PileConstructorState.SeeableState)){
            true -> return ArrayList(mPile)
            false -> throw MissingPileConstructorException("PileConstructorState Seeable is not Present")
        }
    }

    fun shuffle(){
        if(mLock){
            println("Cant shuffle locked deck")
            return
        }
        when(mPermissions.contains(PileConstructorState.ShuffleableState)){
            true -> mPile.shuffle()
            false -> throw MissingPileConstructorException("PileConstructorState Shuffleable is not Present")
        }
    }

    fun lock(){
        when(mPermissions.contains(PileConstructorState.LockableState)){
            true -> mLock = true
            false -> throw MissingPileConstructorException("PileConstructorState Lockable is not Present")
        }
    }


    fun unlock(){
        when(mPermissions.contains(PileConstructorState.LockableState)){
            true -> mLock = false
            false -> throw MissingPileConstructorException("PileConstructorState Lockable is not Present")
        }
    }

    fun getCardsBySuit(suit : Suit) : Pile{
        if(!mPermissions.contains(PileConstructorState.SeeableState)) throw MissingPileConstructorException("PileConstructorState Seeable is not Present")
        val cards = Pile(PileConstructorState.SeeableState, PileConstructorState.RemovableState, PileConstructorState.AddableState, PileConstructorState.ShuffleableState)
        for(card in seeCards()){
            if(card.suit == suit) cards.add(card)
        }
        return cards
    }

    fun getCardsByRank(rank : Rank) : Pile{
        if(!mPermissions.contains(PileConstructorState.SeeableState)) throw MissingPileConstructorException("PileConstructorState Seeable is not Present")
        val cards = Pile(PileConstructorState.SeeableState, PileConstructorState.RemovableState, PileConstructorState.AddableState, PileConstructorState.ShuffleableState)
        for(card in seeCards()){
            if(card.rank == rank) cards.add(card)
        }
        return cards
    }

    fun contains(card : Card) : Boolean{
        if(!mPermissions.contains(PileConstructorState.SeeableState)) throw MissingPileConstructorException("PileConstructorState Seeable is not Present")
        return seeCards().contains(card)
    }

//    Consider if this needs a permission or not. For now it doesnt
    fun size() : Int{
        return mPile.size
    }

    class MissingPileConstructorException(message: String?) : RuntimeException(message)
    class LockedPileException(message : String?) : RuntimeException(message)

    class PileIterator(_pile : Pile) : Iterator<Card> {

        private val pile : Pile = _pile
        private var position = 0

        override fun next(): Card {
            position ++
            return pile.mPile[position-1]
        }

        override fun hasNext(): Boolean {
            return position < pile.size()
        }
    }

}