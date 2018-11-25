package bo.edu.uagrm.sarapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
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
import androidx.core.view.MenuItemCompat.getActionView
import androidx.recyclerview.widget.DividerItemDecoration
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit


class FichaMedicaListFragment : Fragment() {

    private val TAG = FichaMedicaListFragment::class.java.canonicalName as String
    private lateinit  var list: RecyclerView;
    private val adapter = PersonaAdapter()
    private lateinit var viewModel: PersonaViewModel
    private var query:String = "";
    private lateinit var observer:Observable<String>
    private var subscription:Disposable?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_ficha_medica_list,container,false)
        list = view.recycler_ficha_medica;
        list.layoutManager = LinearLayoutManager(context);
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(context as Context))
            .get(PersonaViewModel::class.java)
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)
        initAdapter()
        query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, viewModel.lastQueryValue())
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
        viewModel.searchPersona("Helen")
        adapter.submitList(null)
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = ""
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.app_bar_ficha_medica,menu)

        //SearchView
        val mSearch:MenuItem = menu!!.findItem(R.id.action_search)
        val mSearchView = mSearch.actionView as SearchView

        MenuItemCompat.expandActionView(mSearch);
        mSearchView.setQuery(query,false)


        observer = Observable.create(ObservableOnSubscribe<String> { observer ->
            mSearchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    observer.onNext(query!!)
                    //Log.d(TAG,query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    observer.onNext(newText!!)
                    //Log.d(TAG,newText)
                    return false
                }
            })
        })
            .map{it->it.toLowerCase().trim()}
            .debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()

        subscription = observer.subscribe{query ->
            Log.d(TAG,query)
            viewModel.searchPersona(query)
        };
    }

  /*  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_search) {
            return true;
        }
        return false;
    }*/

     override fun onDestroyView() {
        super.onDestroyView()
         Log.d(TAG,"DISPOSE")
         if(subscription!=null && !(subscription as Disposable).isDisposed )
             (subscription as Disposable).dispose()
    }
}