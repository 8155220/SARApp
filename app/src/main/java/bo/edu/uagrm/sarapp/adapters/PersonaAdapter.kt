package bo.edu.uagrm.sarapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bo.edu.uagrm.sarapp.data.model.Persona
import bo.edu.uagrm.sarapp.ui.PersonaListFragmentDirections
import bo.edu.uagrm.sarapp.viewholders.PersonaViewHolder

class PersonaAdapter internal constructor
    () : PagedListAdapter<Persona,RecyclerView.ViewHolder>(PERSONA_COMPARATOR) {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val personaItem = getItem(position);
        if (personaItem != null) {
            (holder as PersonaViewHolder).bind(createOnClickListener(personaItem.key),personaItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PersonaViewHolder.create(parent);
    }

    private fun createOnClickListener(personaId:String): View.OnClickListener{
        return View.OnClickListener {
            val direction = PersonaListFragmentDirections.actionPersonaListFragmentToPersonaDetailFragment(personaId)
            it.findNavController().navigate(direction)
        }
    }

    companion object {
        private val PERSONA_COMPARATOR = object : DiffUtil.ItemCallback<Persona>() {
            override fun areItemsTheSame(oldItem: Persona, newItem: Persona): Boolean =
                oldItem.key == newItem.key

            override fun areContentsTheSame(oldItem: Persona, newItem: Persona): Boolean =
                oldItem == newItem
        }
    }
}