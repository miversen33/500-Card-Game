package pro.bladebeat.cardLibs.cards.pile

import pro.bladebeat.cardLibs.cards.Card

open class Pile(vararg pileConstructors : PileConstructorState){

    private val mPile : MutableList<Card> = ArrayList()
    private val mPermissions : List<PileConstructorState> = pileConstructors.asList()
    private var mLock : Boolean = false

    fun add(card : Card){
        if(mLock) println("Cant add cards to locked pile")
        when(mPermissions.contains(PileConstructorState.AddableState)){
            true -> mPile.add(card)
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

//    Consider if this needs a permission or not. For now it doesnt
    fun size() : Int{
        return mPile.size
    }

    class MissingPileConstructorException(message: String?) : RuntimeException(message)
    class LockedPileException(message : String?) : RuntimeException(message)

}