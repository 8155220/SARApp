package bo.edu.uagrm.sarapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bo.edu.uagrm.sarapp.R
import bo.edu.uagrm.sarapp.data.Persona
import kotlinx.android.synthetic.main.item_list_persona.view.*

class PersonaAdapter internal constructor
    () : RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {
    private var mPersonas:ArrayList<Persona> = ArrayList();
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_persona,parent,false)
        return PersonaViewHolder(view);
    }

    fun setPersonas(list:ArrayList<Persona>){
        mPersonas=list;
    }
    override fun getItemCount(): Int {
        return mPersonas.size;
    }
    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = mPersonas[position];
        holder.nombreCompleto.text = "${persona.description}";
    }
    class PersonaViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var nombreCompleto:TextView = itemView.textview_nombeCompleto;
    }
}