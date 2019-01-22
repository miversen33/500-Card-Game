package pro.bladebeat.cardLibs.team

class Team(vararg players : Player) {

    private val mPlayers : List<Player> = players.asList()
    private val mBidsTaken : HashMap<Player, Int> = HashMap()
    private var mScore = 0
    private var mCurrentRoundBid = 0
    private var mCurrentTakenBids = 0

    fun getCurrentBid() : Int {
        return mCurrentRoundBid
    }

    fun getCurrentScore() : Int {
        return mScore
    }

    fun getCurrentTakenBids() : Int {
        return mCurrentTakenBids
    }

    fun getPlayersNames() : List<String>{
        var playersNames = ArrayList<String>()
        for(player in mPlayers) playersNames.add(player.getName())
        return playersNames
    }

    fun roundOver(roundScore : Int){
        mScore += roundScore
        mBidsTaken.clear()
        mCurrentTakenBids = 0
        mCurrentRoundBid = 0
    }

}