package bo.edu.uagrm.sarapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bo.edu.uagrm.sarapp.DI.Injection
import bo.edu.uagrm.sarapp.R
import bo.edu.uagrm.sarapp.adapters.PersonaAdapter
import bo.edu.uagrm.sarapp.data.model.Persona
import bo.edu.uagrm.sarapp.viewmodels.PersonaViewModel
import kotlinx.android.synthetic.main.fragment_ficha_medica_list.view.*

class FichaMedicaListFragment : Fragment() {

    private lateinit  var list: RecyclerView;
    private val adapter = PersonaAdapter()
    private lateinit var viewModel: PersonaViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_ficha_medica_list,container,false)
        list = view.recycler_ficha_medica;
        list.layoutManager = LinearLayoutManager(context);
        // Inflate the layout for this fragment


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(this))
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(context as Context))
            .get(PersonaViewModel::class.java)

        initAdapter()
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        viewModel.searchPersona(query)
        initSearch(query)
        

    }

    private fun initAdapter(){
        list.adapter = adapter
        viewModel.personas.observe(this,Observer<PagedList<Persona>> {
            Log.d("Fragment FichaMedica", "list: ${it?.size}")
            adapter.submitList(it)
        })
        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(context, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
        })
    }
    private fun initSearch(query: String) {

        updateRepoListFromInput()
    }

    private fun updateRepoListFromInput() {
        viewModel.searchPersona("A")
        adapter.submitList(null)
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "A"
    }
}