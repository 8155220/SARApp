package bo.edu.uagrm.sarapp.data.model

data class FichaMedica(
    var tipoSangre:String = "",
    var alergias:MutableList<String>,
    var cirugias:MutableList<String>,
    var htaApp:Boolean=false,
    var htaApf:Boolean=false,
    var diabetesApp:Boolean=false,
    var diabetesApf:Boolean=false,
    var cardiopatiaApp:Boolean=false,
    var cardiopatiaApf:Boolean=false,
    var tuberculosisApp:Boolean=false,
    var tuberculosisApf:Boolean=false,
    var chagasApp:Boolean=false,
    var chagasApf:Boolean=false,
    var asmaBronquialApp:Boolean=false,
    var asmaBronquialApf:Boolean=false,
    var otras:String=""
) {
}