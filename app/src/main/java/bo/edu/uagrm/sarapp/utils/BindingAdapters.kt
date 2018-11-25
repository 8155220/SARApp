package bo.edu.uagrm.sarapp.utils

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter


object BindingAdapters{


    @BindingAdapter("bind:hideIfNoResult")
    @JvmStatic fun hideIfNoResult(view: TextView, results:Int){
        Log.d("BindingAdapter:",results.toString())
        view.visibility = if(results==0) View.VISIBLE else View.GONE
    }
}