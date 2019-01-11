package Pile

import Cards.Card

open class Pile(vararg pileConstructors : PileConstructorState){

    private val mPile : MutableList<Card> = ArrayList()
    private val mStates : List<PileConstructorState> = pileConstructors.asList()
    private var mLock : Boolean = false

    fun add(card : Card){
        if(mLock) println("Cant add cards to locked pile")
        when(mStates.contains(PileConstructorState.AddableState)){
            true -> mPile.add(card)
            false -> throw MissingPileConstructorException("PileConstructorState Addable is not Present")
        }
    }

//    This is basically a "pop" function. It will return the top card of the pile, if
//    the pile allows it
    fun draw() : Card{
        when(mStates.contains(PileConstructorState.RemovableState)){
            true -> return remove(0)
            false -> throw MissingPileConstructorException("PileConstructorState Removable is not Present")
        }
    }

    fun remove(locationInPile : Int) : Card{
        if(mLock) throw LockedPileException("Cant remove cards from locked pile")
        when(mStates.contains(PileConstructorState.RemovableState) && !mLock) {
            true -> return mPile.removeAt(locationInPile)
            false -> throw MissingPileConstructorException("PileConstructorState Removable is not Present")
        }
    }

    fun seeCards() : List<Card>{
        when(mStates.contains(PileConstructorState.SeeableState)){
            true -> return ArrayList(mPile)
            false -> throw MissingPileConstructorException("PileConstructorState Seeable is not Present")
        }
    }

    fun shuffle(){
        if(mLock){
            println("Cant shuffle locked deck")
            return
        }
        when(mStates.contains(PileConstructorState.ShuffleableState)){
            true -> mPile.shuffle()
            false -> throw MissingPileConstructorException("PileConstructorState Shuffleable is not Present")
        }
    }

    fun lock(){
        when(mStates.contains(PileConstructorState.LockableState)){
            true -> mLock = true
            false -> throw MissingPileConstructorException("PileConstructorState Lockable is not Present")
        }
    }


    fun unlock(){
        when(mStates.contains(PileConstructorState.LockableState)){
            true -> mLock = false
            false -> throw MissingPileConstructorException("PileConstructorState Lockable is not Present")
        }
    }

    class MissingPileConstructorException(message: String?) : RuntimeException(message)
    class LockedPileException(message : String?) : RuntimeException(message)

}