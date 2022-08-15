data class Player(
    val name: String,
    var surname: String,
    var gender: String = "Male", //ma defaultnu hodnotu nastavenu na male lebo to bude defaultny clovek nas
){

    fun changeSurname(chSurame: String){
        surname = chSurame
        print("Name changed to: ${surname}")
    }

    fun changeGender(chGen: String){
        gender = chGen
    }


}