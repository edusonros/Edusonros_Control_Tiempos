package com.example.composenavegacionentrepantallas.data

data class OperacionProduccion(
    val nombre: String,
    val codigo: Int,
    val valor: Int
)

object OperacionesProduccion {
    val operaciones = mutableListOf(
        OperacionProduccion("OXICORTE", 1012, 25),
        OperacionProduccion("PLASMA", 1001, 25),
        OperacionProduccion("REBARBAR", 1024, 25),
        OperacionProduccion("LASER", 1046, 25),
        OperacionProduccion("SIERRA", 1013, 25),
        OperacionProduccion("CORTE CON", 1002, 25),
        OperacionProduccion("CORTE SOPLETE", 1004, 35),
        OperacionProduccion("ENDEREZAR", 1017, 30),
        OperacionProduccion("CHAFLANAR", 1018, 30),
        OperacionProduccion("TALADRAR", 1007, 36),
        OperacionProduccion("AVELLANAR", 1008, 36),
        OperacionProduccion("ROSCAR", 1011, 36),
        OperacionProduccion("PLEGAR", 1006, 48),
        OperacionProduccion("CURVAR", 1020, 48),
        OperacionProduccion("MECANIZAR", 1053, 35),
        OperacionProduccion("CALDERERIA", 1022, 35),
        OperacionProduccion("SOLDADURA", 1005, 41),
        OperacionProduccion("SOLDADUR ROBOT", 1120, 50),
        OperacionProduccion("ESTABILIZAR", 1009, 40),
        OperacionProduccion("INSPECCION POR LIQUIDOS", 1003, 30),
        OperacionProduccion("MARCAR", 1062, 25),
        OperacionProduccion("MONTAJE", 1222, 25),
        OperacionProduccion("PINTAR", 1043, 30),
        OperacionProduccion("PREPARACIN LOGISTICA", 1050, 25),
        OperacionProduccion("MECANIZADO EXTERIOR", 2001, 100),
        OperacionProduccion("OXICORTE EXTERIOR", 2003, 100),
        OperacionProduccion("CALDERERIA EXTERIOR", 2005, 100),
        OperacionProduccion("CURVADO EXTERIOR", 2009, 100),
        OperacionProduccion("GRANALLAR-IMPRIMAR-PINTAR", 2011, 100),
        OperacionProduccion("LASER EXTERIOR", 2013, 100),
        OperacionProduccion("COMERCIAL", 2016, 25),
        OperacionProduccion("PLEGADO EXTERIOR", 2017, 100),
        OperacionProduccion("INSPECCION SOLDADURA", 2024, 100),
        OperacionProduccion("OFICINA TECNICA", 1047, 25),
        OperacionProduccion("AYUDANTE EN MAQUINA", 1023, 25),
        OperacionProduccion("MANIPULACION MATERIAL", 1033, 25),
        OperacionProduccion("TORNO-FRESA", 1040, 18),
        OperacionProduccion("PERSONAL EN PRACTICAS", 1999, 18),
        OperacionProduccion("PREPARAR MAQUINA PLEGADORA", 1015, 25),
        OperacionProduccion("PREPARAR MAQUINA CENTRO MECANIZADO", 1016, 18),
        OperacionProduccion("PREPARAR MAQUINA THOR", 1023, 18),
        OperacionProduccion("PREPARAR MAQUINA LASER", 1025, 18),
        OperacionProduccion("PREPARAR MAQUINA TALADRO", 1026, 18),
        OperacionProduccion("PREPARAR MAQUINA TORNO-FRESA", 1027, 18)
    )
}