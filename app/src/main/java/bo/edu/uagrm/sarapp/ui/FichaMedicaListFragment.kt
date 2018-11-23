package bo.edu.uagrm.sarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bo.edu.uagrm.sarapp.R
import bo.edu.uagrm.sarapp.adapters.PersonaAdapter
import bo.edu.uagrm.sarapp.data.Persona
import bo.edu.uagrm.sarapp.viewmodels.PersonaViewModel
import kotlinx.android.synthetic.main.fragment_ficha_medica_list.view.*

class FichaMedicaListFragment : Fragment() {

    private lateinit  var recyclerView: RecyclerView;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_ficha_medica_list,container,false)
        recyclerView = view.recycler_ficha_medica;
        recyclerView.layoutManager = LinearLayoutManager(context);
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter=PersonaAdapter()
        var viewModel:PersonaViewModel = ViewModelProviders.of(this).get(PersonaViewModel::class.java)
        viewModel.getPersonas().observe(this, Observer { result ->
            var adapter:PersonaAdapter = recyclerView.adapter as PersonaAdapter;
            adapter.setPersonas(result);
            adapter.notifyDataSetChanged();
            //recyclerView.adapter
        })
    }

}