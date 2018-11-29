package bo.edu.uagrm.sarapp.utils


fun getGrupoSanguineoFromPos(pos:Int):String{
    return when (pos){
        0->"oNegativo"
        1->"oPositivo"
        2->"aNegativo"
        3->"aPositivo"
        4->"bNegativo"
        5->"bPositivo"
        6->"abNegativo"
        7->"abPositivo"
        else -> ""
    }
}