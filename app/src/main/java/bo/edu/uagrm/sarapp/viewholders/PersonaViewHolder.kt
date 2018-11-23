package bo.edu.uagrm.sarapp.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bo.edu.uagrm.sarapp.R
import bo.edu.uagrm.sarapp.data.model.Persona
import kotlinx.android.synthetic.main.item_list_persona.view.*

class PersonaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name:TextView = view.textview_name;
    private val description:TextView = view.textview_description;

    private var persona: Persona? = null

    init{

    }

    fun bind(persona: Persona?){
        if(persona==null){
            val reurces = itemView.resources
            name.text = "Cargando..."
            description.visibility = View.GONE
        }else {
            showPersonaData(persona)
        }
    }
    private fun showPersonaData(persona: Persona){
        this.persona = persona;
        name.text = persona.name;
        var descriptionVisibility = View.GONE
        if(persona.description!=null){
            description.text = persona.description
            descriptionVisibility = View.VISIBLE
        }
        description.visibility = descriptionVisibility
    }
    companion object {
        fun create(parent:ViewGroup):PersonaViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_persona,parent,false)
            return PersonaViewHolder(view);
        }
    }
}