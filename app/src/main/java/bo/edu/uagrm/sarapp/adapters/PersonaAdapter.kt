package bo.edu.uagrm.sarapp.adapters

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bo.edu.uagrm.sarapp.data.model.Persona
import bo.edu.uagrm.sarapp.viewholders.PersonaViewHolder

class PersonaAdapter internal constructor
    () : PagedListAdapter<Persona,RecyclerView.ViewHolder>(PERSONA_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PersonaViewHolder.create(parent);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val personaItem = getItem(position);
        if (personaItem != null) {
            (holder as PersonaViewHolder).bind(personaItem)
        }
    }

    companion object {
        private val PERSONA_COMPARATOR = object : DiffUtil.ItemCallback<Persona>() {
            override fun areItemsTheSame(oldItem: Persona, newItem: Persona): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Persona, newItem: Persona): Boolean =
                oldItem == newItem
        }
    }
}