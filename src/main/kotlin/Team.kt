data class Team(
    val teamMembers: MutableList<Player> = mutableListOf<Player>(),
    var coach: Coach
){

    fun addPlayerToTheTeam(p:Player):Boolean{
        //check if player is already in the team
        if(teamMembers.contains(p)){
            println("There is a player with this name in this team")
            return false
        }
        else{
            val sizeBeforeAdding: Int = teamMembers.size
            teamMembers.add(p);
            if (teamMembers.size == sizeBeforeAdding+1){
                println("${p.name}, ${p.surname} successfully added")
            }
            else{
                println("${p.name}, ${p.surname} unsuccessfully added, try again")
            }

            return true
        }

    }

    fun changeCoach(c:Coach){
        coach = c
    }
}