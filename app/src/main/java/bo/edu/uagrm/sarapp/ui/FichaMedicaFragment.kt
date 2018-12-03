package bo.edu.uagrm.sarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import bo.edu.uagrm.sarapp.DI.Injection
import bo.edu.uagrm.sarapp.R
import bo.edu.uagrm.sarapp.adapters.RevicionMedicaAdapter
import bo.edu.uagrm.sarapp.databinding.FragmentFichaMedicaBinding
import bo.edu.uagrm.sarapp.viewmodels.FichaMedicaViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_persona_list.*

class FichaMedicaFragment: Fragment() {
    private val TAG = FichaMedicaFragment::class.java.canonicalName;
    private lateinit var fichaMedicaViewModel:FichaMedicaViewModel;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val personaId = FichaMedicaFragmentArgs.fromBundle(arguments).personaId
        val factory = Injection.provideFichaMedicaViewModelFactory(requireActivity(),personaId)
        fichaMedicaViewModel = ViewModelProviders.of(this,factory)
                .get(FichaMedicaViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentFichaMedicaBinding>(inflater, R.layout.fragment_ficha_medica,container,false)
                .apply {
                    viewModel = fichaMedicaViewModel
                    setLifecycleOwner(this@FichaMedicaFragment)
                }
        val adapter = RevicionMedicaAdapter()
        binding.recyclerRevicionMedica.adapter=adapter
        binding.recyclerRevicionMedica.layoutManager = LinearLayoutManager(binding.root.context)

        binding.imgEdit.setOnClickListener {
            val direction = FichaMedicaFragmentDirections.actionFichaMedicaFragmentDestToFichaMedicaEditFragmentDest(personaId)
            it.findNavController().navigate(direction)
        }
        binding.imgAddRevicionMedica.setOnClickListener {
            val direction= FichaMedicaFragmentDirections.actionFichaMedicaFragmentDestToRevicionMedicaCreateFragmentDest(personaId)
            it.findNavController().navigate(direction)
        }

        fichaMedicaViewModel.updateSuccess.observe(this, Observer{ it->
            //val direction = PersonaDetailFragmentDirections.actionPersonaDetailFragmentDestToFichaMedicaFragment(personaId)
            swipeToRefresh.isRefreshing = false
            Snackbar.make(requireActivity().findViewById(R.id.activityCordinator),R.string.SyncSuccessMessage,
                Snackbar.LENGTH_LONG).show()
        })
        fichaMedicaViewModel.updateError.observe(this, Observer { it->
            swipeToRefresh.isRefreshing = false
            Snackbar.make(requireActivity().findViewById(R.id.activityCordinator),R.string.UpdateErrorMessage, Snackbar.LENGTH_LONG).show()
        })

        fichaMedicaViewModel.revicionMedicaList.observe(this,Observer{it->
            adapter.setList(it)
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeToRefresh.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent)
        swipeToRefresh.setOnRefreshListener {
            fichaMedicaViewModel.updatefichaMedica()
        }
    }
}