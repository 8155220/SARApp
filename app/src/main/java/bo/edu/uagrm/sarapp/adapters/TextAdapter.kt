package bo.edu.uagrm.sarapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bo.edu.uagrm.sarapp.databinding.ItemListTextBinding

class TextAdapter internal constructor() : RecyclerView.Adapter<TextAdapter.TextViewHolder>(){

    private var list:MutableList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAdapter.TextViewHolder {
        return TextViewHolder(ItemListTextBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TextAdapter.TextViewHolder, position: Int) {
        //  val data = getItemId(position)
          val data = list.get(position)
        holder.apply {
            bind(createOnClickListener(position),data)
            itemView.tag=data
        }
    }
    private fun createOnClickListener(pos:Int): View.OnClickListener{
        return View.OnClickListener {
            removeItem(pos)
        }
    }

    public fun getList()= list
    public fun setList(newList:MutableList<String> = ArrayList()){
        list=newList
        notifyItemRangeInserted(0,list.size)
    }


    override fun getItemCount() = list.size

    fun addItem(item:String){
        if(!list.contains(item)) {
            list.add(item)
            notifyItemInserted(list.size)
        }
    }
    fun removeItem(pos:Int){
        list.removeAt(pos)
        notifyItemRemoved(pos)
        notifyItemRangeChanged(pos,list.size)
    }
    class TextViewHolder(private val binding:ItemListTextBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(listener:View.OnClickListener,data:String =""){
            binding.apply {
                //txt=text;
                removeListener=listener
                text=data
                executePendingBindings()
            }
        }
    }
}