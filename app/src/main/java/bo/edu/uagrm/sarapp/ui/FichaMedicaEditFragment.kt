package bo.edu.uagrm.sarapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import bo.edu.uagrm.sarapp.DI.Injection
import bo.edu.uagrm.sarapp.R
import bo.edu.uagrm.sarapp.adapters.TextAdapter
import bo.edu.uagrm.sarapp.databinding.FragmentFichaMedicaEditBinding
import bo.edu.uagrm.sarapp.utils.getGrupoSanguineoFromPos
import bo.edu.uagrm.sarapp.viewmodels.FichaMedicaViewModel

class FichaMedicaEditFragment: Fragment() {
    private val TAG = FichaMedicaEditFragment::class.java.canonicalName;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val personaId = FichaMedicaFragmentArgs.fromBundle(arguments).personaId
        val factory = Injection.provideFichaMedicaViewModelFactory(requireActivity(),personaId)
        val fichaMedicaViewModel = ViewModelProviders.of(this,factory)
                .get(FichaMedicaViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentFichaMedicaEditBinding>(inflater, R.layout.fragment_ficha_medica_edit,container,false)
                .apply {
                    viewModel = fichaMedicaViewModel
                    setLifecycleOwner(this@FichaMedicaEditFragment)
                }
        val alergiaAdapter = TextAdapter()
        val cirugiaAdapter = TextAdapter()
        binding.list.adapter = alergiaAdapter
        binding.listCirugias.adapter = cirugiaAdapter
        binding.listCirugias.layoutManager = LinearLayoutManager(binding.root.context)
        binding.list.layoutManager = LinearLayoutManager(binding.root.context)

        binding.btnSave.setOnClickListener {
            fichaMedicaViewModel.onSaveButton(personaId,alergiaAdapter.getList(),cirugiaAdapter.getList())
            binding.constraintProgressBar.visibility=View.VISIBLE
            binding.constraintLayout.visibility=View.GONE
        }
        fichaMedicaViewModel.selectedItem.observe(this, Observer { it->
            Log.d(TAG,"valor es :"+it)
            fichaMedicaViewModel.fichaMedica.tipoSangre=getGrupoSanguineoFromPos(it)
        })

        fichaMedicaViewModel.onAddedItemAlergia.observe(this, Observer { it->
            alergiaAdapter.addItem(it)
            binding.alergiaEditText = ""
        })
        fichaMedicaViewModel.onAddedItemCirugia.observe(this, Observer { it->
            cirugiaAdapter.addItem(it)
            binding.cirugiaEditText = ""
        })
        return binding.root
    }
}