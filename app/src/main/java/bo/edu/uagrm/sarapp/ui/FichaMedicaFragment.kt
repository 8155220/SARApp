package bo.edu.uagrm.sarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import bo.edu.uagrm.sarapp.DI.Injection
import bo.edu.uagrm.sarapp.R
import bo.edu.uagrm.sarapp.databinding.FragmentFichaMedicaBinding
import bo.edu.uagrm.sarapp.viewmodels.FichaMedicaViewModel

class FichaMedicaFragment: Fragment() {
    private val TAG = FichaMedicaFragment::class.java.canonicalName;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val personaId = FichaMedicaFragmentArgs.fromBundle(arguments).personaId
        val factory = Injection.provideFichaMedicaViewModelFactory(requireActivity(),personaId)
        val fichaMedicaViewModel = ViewModelProviders.of(this,factory)
                .get(FichaMedicaViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentFichaMedicaBinding>(inflater, R.layout.fragment_ficha_medica,container,false)
                .apply {
                    viewModel = fichaMedicaViewModel
                    setLifecycleOwner(this@FichaMedicaFragment)
                }
        binding.imgEdit.setOnClickListener {
//            fichaMedicaViewModel.onSaveButton(personaId,alergiaAdapter.getList(),cirugiaAdapter.getList())
//            binding.constraintProgressBar.visibility=View.VISIBLE
//            binding.constraintLayout.visibility=View.GONE
            val direction = FichaMedicaFragmentDirections.actionFichaMedicaFragmentToFichaMedicaEditFragment(personaId)
            it.findNavController().navigate(direction)
        }


        return binding.root
    }
}