package mx.itesm.tacos_de_tinga_19.veintiundias

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_acerca_de.*
import java.net.URL
import java.util.concurrent.Executors

class AcercaDe : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acerca_de, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        escribirTexto()
    }

    private fun escribirTexto() {
        val text = tVTextoGeneral
        text.movementMethod = LinkMovementMethod.getInstance()
        val texto = "<b>"+"¿Qué es nuestra aplicación?"+"</b><br>"+
                "Nuestra aplicación es una ayuda para que se creen buenos habitos, estudios dicen que se " +
                "requieren 21 días para poder acostumbrarnos y crear un nuevo habito y consideramos" +
                " que con nuestra aplicación esta tarea puede facilitarse.<br><br>" +
                "<b>"+"¿Por qué el mapache?"+"</b><br>"+
                "El mapache es un animal que relaja y nos gusto mucho la idea de usarlo como" +
                " un elemento más de la aplicación.<br><br>"+


                "<br><b>Arte:</b><br>"+
                "Sofía Rodríguez López<br>"+
                "<a href=\"https://instagram.com/chofsart?igshid=sf45m5r9fpgy\">Instagram </a>"+
                "<br><br>"+

                "<br><b>Licencia de las gráficas:</b><br>"+
                "Copyright [2020] [Philip Jahoda]<br>" +
                "Retrieved from:<br>" +
                "<a href=\"https://github.com/PhilJay/MPAndroidChart\">https://github.com/PhilJay/MPAndroidChart</a><br>" +
                "Applied in:<br>" +
                "PerfilFrag´s charts<br>" +
                "<br>" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");<br>" +
                "you may not use this file except in compliance with the License.<br>" +
                "You may obtain a copy of the License at<br>" +
                "<br>" +
                "<a href=\"http://www.apache.org/licenses/LICENSE-2.0\">   http://www.apache.org/licenses/LICENSE-2.0</a><br>" +
                "<br>" +
                "Unless required by applicable law or agreed to in writing, software " +
                "distributed under the License is distributed on an \"AS IS\" BASIS, " +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. " +
                "See the License for the specific language governing permissions and " +
                "limitations under the License.<br><br>"+


                "<br><b>Licencia del Calendario:</b><br>"+
                "The MIT License (MIT)<br>" +
                "<br>" +
                "Copyright (c) [2018] [Sundeepk]<br>" +
                "<br>" +
                "Permission is hereby granted, free of charge, to any person obtaining a copy " +
                "of this software and associated documentation files (the \"Software\"), to deal " +
                "in the Software without restriction, including without limitation the rights " +
                "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell " +
                "copies of the Software, and to permit persons to whom the Software is " +
                "furnished to do so, subject to the following conditions: " +
                "<br><br>" +
                "The above copyright notice and this permission notice shall be included in all " +
                "copies or substantial portions of the Software. " +
                "<br><br>" +
                "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR " +
                "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, " +
                "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE " +
                "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER " +
                "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, " +
                "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE " +
                "SOFTWARE.<br><br>"

        tVTextoGeneral.text = Html.fromHtml(texto)
    }

}