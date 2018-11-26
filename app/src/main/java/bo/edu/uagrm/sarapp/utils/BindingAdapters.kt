package bo.edu.uagrm.sarapp.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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
}