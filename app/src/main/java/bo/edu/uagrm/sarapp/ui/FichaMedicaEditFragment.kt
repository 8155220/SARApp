package bo.edu.uagrm.sarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import bo.edu.uagrm.sarapp.DI.Injection
import bo.edu.uagrm.sarapp.R
import bo.edu.uagrm.sarapp.adapters.TextAdapter
import bo.edu.uagrm.sarapp.data.model.FichaMedica
import bo.edu.uagrm.sarapp.databinding.FragmentFichaMedicaEditBinding
import bo.edu.uagrm.sarapp.utils.getGrupoSanguineoFromPos
import bo.edu.uagrm.sarapp.utils.getPosFromGrupoSanguineo
import bo.edu.uagrm.sarapp.viewmodels.FichaMedicaEditViewModel
import com.google.android.material.snackbar.Snackbar

class FichaMedicaEditFragment: Fragment() {
    private val TAG = FichaMedicaEditFragment::class.java.canonicalName;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val personaId = FichaMedicaFragmentArgs.fromBundle(arguments).personaId
        val factory = Injection.provideFichaMedicaEditViewModelFactory(requireActivity(),personaId)
        val fichaMedicaEditViewModel = ViewModelProviders.of(this,factory)
                .get(FichaMedicaEditViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentFichaMedicaEditBinding>(inflater, R.layout.fragment_ficha_medica_edit,container,false)
                .apply {
                    viewModel = fichaMedicaEditViewModel
                    setLifecycleOwner(this@FichaMedicaEditFragment)
                }
        val alergiaAdapter = TextAdapter()
        val cirugiaAdapter = TextAdapter()
        binding.list.adapter = alergiaAdapter
        binding.listCirugias.adapter = cirugiaAdapter
        binding.listCirugias.layoutManager = LinearLayoutManager(binding.root.context)
        binding.list.layoutManager = LinearLayoutManager(binding.root.context)

        binding.btnSave.setOnClickListener {

            fichaMedicaEditViewModel.onSaveButton(personaId,alergiaAdapter.getList(),cirugiaAdapter.getList())
            binding.constraintProgressBar.visibility=View.VISIBLE
            binding.constraintLayout.visibility=View.GONE
        }

        fichaMedicaEditViewModel.fichaMedicaLocal.observe(this, Observer { it->
            if(it!=null){
                alergiaAdapter.setList(it.alergias)
                cirugiaAdapter.setList(it.cirugias)
                binding.spinnerGrupoSanguineo.setSelection(getPosFromGrupoSanguineo(it.tipoSangre))
            } else {
                alergiaAdapter.setList(ArrayList<String>())
                cirugiaAdapter.setList(ArrayList<String>())
                binding.spinnerGrupoSanguineo.setSelection(8) // 8=no define
            }

        })

        fichaMedicaEditViewModel.updateSuccess.observe(this,Observer{ it->
            //val direction = PersonaDetailFragmentDirections.actionPersonaDetailFragmentDestToFichaMedicaFragment(personaId)
            findNavController().popBackStack()
            Snackbar.make(requireActivity().findViewById(R.id.activityCordinator),R.string.UpdateSuccessMessage,Snackbar.LENGTH_LONG).show()


        })
        fichaMedicaEditViewModel.updateError.observe(this, Observer { it->
            binding.constraintProgressBar.visibility=View.GONE
            binding.constraintLayout.visibility=View.VISIBLE
            Snackbar.make(requireActivity().findViewById(R.id.activityCordinator),R.string.UpdateErrorMessage,Snackbar.LENGTH_LONG).show()
        })


        fichaMedicaEditViewModel.selectedItem.observe(this, Observer { it->
            //fichaMedicaViewModel.fichaMedica.tipoSangre=getGrupoSanguineoFromPos(it)
            (fichaMedicaEditViewModel.fichaMedicaLocal.value as FichaMedica).tipoSangre =getGrupoSanguineoFromPos(it)

        })

        fichaMedicaEditViewModel.onAddedItemAlergia.observe(this, Observer { it->
            alergiaAdapter.addItem(it)
            binding.alergiaEditText = ""
        })
        fichaMedicaEditViewModel.onAddedItemCirugia.observe(this, Observer { it->
            cirugiaAdapter.addItem(it)
            binding.cirugiaEditText = ""
        })
        return binding.root
    }

}