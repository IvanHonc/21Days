package mx.itesm.tacos_de_tinga_19.veintiundias

interface ClickListener {
    fun clicked(posicion: Int)

    // Opcionales
    fun longClicked(posicion: Int) { }
}