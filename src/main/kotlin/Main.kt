import kotlin.random.Random

val names = arrayListOf<String>("Jozo", "Fero", "Jano", "Peter", "Samo", "Tomas", "Mario") //
val surNames = arrayListOf<String>("Vysoky", "Vajda", "Knaz", "Horvath", "Ferenc", "Knop", "Hamsik") //
var players = mutableListOf<Player>()
val combinations = names.size * surNames.size;
val teamsMap = HashMap<String, Team>();

fun main() {

    creatingPlayers()

    var appRunning: Boolean = true
    while (appRunning) {
        menuDialogue()
        var option = readLine()!!
        if (option.toInt() == 1) {
            createTeam()
        } else if (option.toInt() == 2) {
            println("Write your team name:")
            val teamName = readLine()!!
            println("Write player full name:")
            val playerName = readLine()!!
            println("Write player gender")
            val playerGender = readLine()!!
            addPlayerToTeam(teamName, playerName.split(" ")[0], playerName.split(" ")[1], playerGender)

        } else if (option.toInt() == 3) {
            println("Write the team name:")
            showTeam(readLine()!!)
        } else if (option.toInt() == 4) {

            println("Write the team name and number of generated players (teamName,XY):")
            val input = readLine()!!
            if (input.split(",").size==1) {
                println("Bad input, please try again in pattern: teamName,XY")
            } else {
                addGeneratedPlayers(input.split(",")[0], input.split(",")[1].toInt())
            }

        } else {
            appRunning = false

        }

    }

    println("End")


}

fun addGeneratedPlayers(teamName: String, numOfGenPlayers: Int) {
    if (teamsMap[teamName] != null) {
        for (i in 0..numOfGenPlayers - 1) {
            if (teamsMap[teamName]!!.addPlayerToTheTeam(players.get(Random.nextInt(0, players.size - 1)))) {

            } else {
                println("Sorry")
            }

        }
    } else {
        println("Sorry, there is no team with name like this -> ${teamName}")
        //TODO funkcia na vyhladavanie moznych moznosti timov
    }
}

fun creatingPlayers() {
    var i = 0
    var p: Player? = null
    var isUsed = true
    while (i < combinations) {

        isUsed = true
        while (isUsed) {
            p = Player(names.get(Random.nextInt(0, names.size)), surNames.get(Random.nextInt(0, surNames.size)))
            isUsed = false
            for (player in players) {
                if (p.name.equals(player.name) && p.surname.equals(player.surname) && players.size != combinations) {
                    //we generated the same player - try again
                    println("THis person is already in the list")
                    isUsed = true;
                    break
                }
            }
        }
        if (p != null) {
            players.add(i, p)
        }
        i++;
    }
}


fun deletePlayerFromTeam() {
    val name: String = readLine()!!
    val surname: String = readLine()!!

}


fun createTeam() {
    print("Set team name:")
    val teamName = readLine()!!
    println("Set full name of coach:")
    val coachName = readLine()!!
    val splittedName = coachName.split(" ")
    val teamMembers: MutableList<Player> = mutableListOf()
    val team: Team = Team(teamMembers, Coach(splittedName[0], splittedName[1]))

    teamsMap.put(teamName, team)

    //checking if the team is succesfully created
    if (teamsMap.containsKey(teamName)) {
        println("Team created succesfully")
    } else {
        println("Team created unsuccesfully, try again")
    }

}

fun addPlayerToTeam(teamName: String, playerName: String, playerSurname: String, playerGender: String) {
    val p: Player = Player(playerName, playerSurname, playerGender)
    teamsMap[teamName]?.teamMembers?.add(p)
}

fun showTeam(teamName: String) {
    val t: Team? = teamsMap[teamName];
    if (t == null) {
        println("There is no team with name :${teamName}")
    } else {
        println("Team coach: ${t.coach.name} ${t.coach.surname}")
        for (player in t.teamMembers) {
            println("${player.name}, ${player.surname}")
        }
    }
}

fun menuDialogue() {
    println("\n-----------------------------------------")
    println("Choose what you want to do: \n")
    println("Create a new team: 1")
    println("Add players to team: 2")
    println("Look at the team: 3")
    println("Generate players and add them to the team: 4")
    println("-----------------------------------------\n")

}


//CreatePlayer

