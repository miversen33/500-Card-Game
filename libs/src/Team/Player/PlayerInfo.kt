package pro.bladebeat.cardLibs.team

interface PlayerInfo {
    fun getName() : String
    fun getPartner() : PlayerInfo
}