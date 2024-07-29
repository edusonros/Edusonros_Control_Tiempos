package com.example.composenavegacionentrepantallas.data


data class OperarioProduccion(
    val nombre: String,
    val codigo: Int
)

object OperariosProduccion {
    val operario = mutableListOf(

        OperarioProduccion("ADRIAN BALLESTEROS", 149),
        OperarioProduccion("ADRIANA RAD", 320),
        OperarioProduccion("ALBERTO GARCIA PEREZ", 117),
        OperarioProduccion("ALIEL HADDYOUY", 324),
        OperarioProduccion("ANDRÉS SÁNCHEZ YUNTA", 219),
        OperarioProduccion("ANTONIO PÉREZ BLANCO", 230),
        OperarioProduccion("CARLOS CRESPO ORDOÑEZ", 114),
        OperarioProduccion("CÉSAR BROTO", 142),
        OperarioProduccion("DANIEL DEL HOYO", 546),
        OperarioProduccion("DAVID BLASCO ACEVES", 302),
        OperarioProduccion("EDUARDO SONSONA", 540),
        OperarioProduccion("EMILIO VICENTE PAVÓN", 226),
        OperarioProduccion("ENRIQUE MARÍN CERNUDA", 543),
        OperarioProduccion("FLAVIUS RAD", 105),
        OperarioProduccion("JAVIER FERRER LARRAYAD", 429),
        OperarioProduccion("JAVIER SALVADOR", 218),
        OperarioProduccion("JAVIER SERAL", 104),
        OperarioProduccion("JESÚS RUIZ MELENDO", 503),
        OperarioProduccion("JOAQUIN CORTES", 333),
        OperarioProduccion("JUÁN MORCILLO", 334),
        OperarioProduccion("MIGUEL ÁNGEL ORTEGA", 235),
        OperarioProduccion("MIGUEL ANGEL VA CHECA", 111),
        OperarioProduccion("RAÚL CASTILLO", 107),
        OperarioProduccion("RUBÉN GUALLAR", 225),
        OperarioProduccion("RUBÉN NUÑO MOLINA", 509),
        OperarioProduccion("SANTIAGO ALCAINE", 216),
        OperarioProduccion("SEBASTIAN NICA", 108),
        OperarioProduccion("VICTOR DIEGO DANTAS", 328),
        OperarioProduccion("LUIS VICENTE", 901),
        OperarioProduccion("RAFAEL SANCHEZ", 908)
    )

}
