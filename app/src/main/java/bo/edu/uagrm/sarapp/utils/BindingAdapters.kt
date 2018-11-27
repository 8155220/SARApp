package bo.edu.uagrm.sarapp.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import bo.edu.uagrm.sarapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton


object BindingAdapters{


    @BindingAdapter("bind:hideIfNoResult")
    @JvmStatic fun hideIfNoResult(view: TextView, results:Int){
        Log.d("BindingAdapter:",results.toString())
        view.visibility = if(results==0) View.VISIBLE else View.GONE
    }


    @BindingAdapter("bind:imageFromUrl")
    @JvmStatic
    fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }

    @BindingAdapter("bind:isGone")
    @JvmStatic
    fun bindIsGone(view: FloatingActionButton, isGone: Boolean?) {
        if (isGone == null || isGone) {
            view.hide()
        } else {
            view.show()
        }
    }

    @BindingAdapter("bind:grado")
    @JvmStatic fun grado(view: TextView, results:String){
        view.text= getGrado(results)
    }

    @BindingAdapter("bind:sexo")
    @JvmStatic
    fun bindSexo(view: ImageView, sexo: String?) {
        if (!sexo.isNullOrEmpty()) {
            when (sexo) {
                "masculino" -> view.setImageResource(R.drawable.ic_male)
                "femenino" -> view.setImageResource(R.drawable.ic_female)
            }

        }
    }

    @BindingAdapter("bind:chipBackgroundColor")
    @JvmStatic
    fun bindChipBackGroundColor(view: Chip, estado: String?) {
        if (!estado.isNullOrEmpty()) {
            when (estado) {
                "activo" -> view.setChipBackgroundColorResource(R.color.estadoActivo)
                "pasivo" -> view.setChipBackgroundColorResource(R.color.estadoInactivo)
            }

        }
    }





  /*  @BindingAdapter("wateringText")
    fun bindWateringText(textView: TextView, wateringInterval: Int) {
        val resources = textView.context.resources
        val quantityString = resources.getQuantityString(R.plurals.watering_needs_suffix,
            wateringInterval, wateringInterval)

        textView.text = SpannableStringBuilder()
            .bold { append(resources.getString(R.string.watering_needs_prefix)) }
            .append(" ")
            .italic { append(quantityString) }
    }*/


    private fun getGrado(grado:String):String{
        return when(grado){
            "primerAnio"->{
                "Voluntario 1er año"
            }
            "segundoAnio"->{
                "Voluntario 2do año"
            }
            "tercerAnio"->{
                "Voluntario 3er año"
            }
            "rescatistaInicial"->{
                "Rescatista Inicial"
            }
            "rescatistaSegundo"->{
                "Rescatista Segundo"
            }
            "rescatistaPrimero"->{
                "Rescatista Primero"
            }
            "rescatistaEspecialista"->{
                "Rescatista Especialista"
            }
            "rescatistaMaster"->{
                "Rescatista Master"
            }
            "rescatistaComando"->{
                "Rescatista Comando"
            } else -> grado

        }
    }
}