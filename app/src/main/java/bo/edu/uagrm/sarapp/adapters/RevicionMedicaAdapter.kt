package bo.edu.uagrm.sarapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bo.edu.uagrm.sarapp.data.model.RevicionMedica
import bo.edu.uagrm.sarapp.databinding.ItemListRevicionMedicaBinding

class RevicionMedicaAdapter internal constructor() : RecyclerView.Adapter<RevicionMedicaAdapter.RevicionMedicaViewHolder>(){

    private var list:MutableList<RevicionMedica> = ArrayList()

    override fun onBindViewHolder(holder: RevicionMedicaAdapter.RevicionMedicaViewHolder, position: Int) {
        //  val data = getItemId(position)
        Log.d("entroajoi23joi33","")
        val data = list.get(position)
        holder.apply {
            bind(data)
            itemView.tag=data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevicionMedicaAdapter.RevicionMedicaViewHolder {
        return RevicionMedicaViewHolder(
            ItemListRevicionMedicaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    public fun getList()= list

    public fun setList(newList:MutableList<RevicionMedica> = ArrayList()){
        list=newList
        //notifyItemRangeInserted(0,list.size)
        notifyDataSetChanged()
    }


    override fun getItemCount() = list.size

    class RevicionMedicaViewHolder(private val binding: ItemListRevicionMedicaBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:RevicionMedica){
            binding.apply {
                //txt=RevicionMedica;
                Log.d("entroajoi23joi","")

                revicionMedica=item
                executePendingBindings()
            }
        }
    }

}