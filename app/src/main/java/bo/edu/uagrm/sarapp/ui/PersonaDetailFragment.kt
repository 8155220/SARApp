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
import bo.edu.uagrm.sarapp.DI.Injection
import bo.edu.uagrm.sarapp.R
import bo.edu.uagrm.sarapp.databinding.FragmentPersonaDetailBinding
import bo.edu.uagrm.sarapp.viewmodels.PersonaDetailViewModel


class PersonaDetailFragment : Fragment() {
   // private lateinit var viewModel:PersonaDetailViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val personaId = PersonaDetailFragmentArgs.fromBundle(arguments).personaId
        val factory = Injection.providePersonaDetailViewModelFactory(requireActivity(),personaId)
        val personaDetailViewModel = ViewModelProviders.of(this,factory)
            .get(PersonaDetailViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentPersonaDetailBinding>(inflater, R.layout.fragment_persona_detail,container,false)
            .apply{
                viewModel = personaDetailViewModel
                setLifecycleOwner(this@PersonaDetailFragment)
                /*fab.setOnClickListener{view->
                    //personaDetailViewModel.
                    Snackbar.make(view,R.string.test_data,Snackbar.LENGTH_SHORT).show()
                }*/
            }

       binding.fichaMedica.setOnClickListener{
           val direction = PersonaDetailFragmentDirections.actionPersonaDetailFragmentDestToFichaMedicaFragment(personaId)
           it.findNavController().navigate(direction)
       }
       personaDetailViewModel.persona.observe(this, Observer { persona->

       })
        return binding.root
    }


}