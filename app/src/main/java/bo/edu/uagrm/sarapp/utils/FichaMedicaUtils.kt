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
        8->"NoEspecificado"
        else -> ""
    }
}
fun getViewValueGrupoSanguineoGromValueUtils(value:String):String{
    return when (value){
        "oNegativo"->"O RH negativo"
        "oPositivo"->"O RH positivo"
        "aNegativo"->"A RH negativo"
        "aPositivo"->"A RH positivo"
        "bNegativo"->"B RH negativo"
        "bPositivo"->"B RH positivo"
        "abNegativo"->"AB RH negativo"
        "abPositivo"->"AB RH positivo"
        "NoEspecificado"->"NoEspecificado"
        else ->"Error"
    }
}
fun getPosFromGrupoSanguineo(grupoSanguineo:String = "oPositivo"):Int{
    return when (grupoSanguineo){
        "oNegativo"->0
        "oPositivo"->1
        "aNegativo"->2
        "aPositivo"->3
        "bNegativo"->4
        "bPositivo"->5
        "abNegativo"->6
        "abPositivo"->7
        "NoEspecificado"->8
        else ->-1
    }
}