package bo.edu.uagrm.sarapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bo.edu.uagrm.sarapp.data.model.Persona
import bo.edu.uagrm.sarapp.databinding.ItemListPersonaBinding
import bo.edu.uagrm.sarapp.ui.PersonaListFragmentDirections

class PersonaAdapter internal constructor
    () : PagedListAdapter<Persona,PersonaAdapter.PersonaViewHolder>(PERSONA_COMPARATOR) {


    override fun onBindViewHolder(holder: PersonaAdapter.PersonaViewHolder, position: Int) {
        val personaItem = getItem(position) as Persona;

        holder.apply {
            bind(createOnClickListener(personaItem.key),personaItem)
            itemView.tag=personaItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaAdapter.PersonaViewHolder {
        return PersonaViewHolder(ItemListPersonaBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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


    class PersonaViewHolder(private val binding: ItemListPersonaBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener,item: Persona){
            binding.apply {
                Log.d("PersonaViewHolder","Click " )
                clickListener = listener
                persona = item
                executePendingBindings()
            }
        }

    }

}