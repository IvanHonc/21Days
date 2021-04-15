package mx.itesm.tacos_de_tinga_19.veintiundias

//Define el metodo que se ejecuta para avisar que hay un click sobre el renglon
//Autor: Viviana Osorio Nieto
interface ClickListener {
    // hizo click en el renglon (posicion)
    fun clicked(position: Int)
}