package bo.edu.uagrm.sarapp.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import bo.edu.uagrm.sarapp.DI.Injection
import bo.edu.uagrm.sarapp.R
import bo.edu.uagrm.sarapp.data.model.RevicionMedica
import bo.edu.uagrm.sarapp.databinding.FragmentRevicionMedicaCreateBinding
import bo.edu.uagrm.sarapp.viewmodels.RevicionMedicaCreateViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class RevicionMedicaCreateFragment : Fragment() {
    private val TAG = RevicionMedicaCreateFragment::class.java.canonicalName;
    var dateTimeStamp:Long = 0;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val personaId = RevicionMedicaCreateFragmentArgs.fromBundle(arguments).personaId
        val factory = Injection.provideRevicionMedicaCreateViewModelFactory(requireActivity(),personaId)
        val revicionMedicaCreateViewModel = ViewModelProviders.of(this,factory)
            .get(RevicionMedicaCreateViewModel::class.java)

       val binding = DataBindingUtil.inflate<FragmentRevicionMedicaCreateBinding>(inflater, R.layout.fragment_revicion_medica_create,container,false)
            .apply {
                //viewModel = revicionMedicaCreateViewModel
                setLifecycleOwner(this@RevicionMedicaCreateFragment)
            }

        binding.imgdate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                // Display Selected date in textbox
                //binding.txtDate.text=("$day/$month/$year")
                binding.txtDate.text=("$day/$month/$year")
                val str_date= "$day-$month-$year"
                val formatter = SimpleDateFormat("dd-MM-yyyy")
                val date = formatter.parse(str_date)
                dateTimeStamp = date.time
                // date.getTime() date in TimeStamp Type Long
                //binding.txtDate.text= .toString()
            }, year, month, day)
            dpd.show()
        }

         binding.btnGuardar.setOnClickListener {
             val txtPeso = binding.editPeso.text.toString()
             val txtEstatura= binding.editEstatura.text.toString()
            // val txtIMC = binding.txtIMC.text.toString()
             val txtPresionArterial = binding.editPresionArterial.text.toString()
             val txtFrecuenciaCardiaca = binding.editFrecuenciaCardiaca.text.toString()
             val txtTratamiento = binding.editTratamiento.text.toString()
             if(txtPeso=="" || txtEstatura=="" || txtPresionArterial=="" || txtFrecuenciaCardiaca=="" || txtTratamiento==""){
                 Snackbar.make(requireActivity().findViewById(R.id.activityCordinator),R.string.FieldRequired,Snackbar.LENGTH_SHORT).show()
             } else{
                 var revicionMedica= RevicionMedica(personaId = personaId,fecha = dateTimeStamp,
                     peso=txtPeso.toDouble(),
                     estatura = txtEstatura.toDouble(),
                     indiceMasaCorporal = txtPeso.toDouble()/(txtEstatura.toDouble()*txtEstatura.toDouble()),
                     presionArterial = txtPresionArterial.toInt(),
                     frecuenciaCardiaca = txtFrecuenciaCardiaca.toInt(),
                     tratamiento = txtTratamiento)

                 revicionMedicaCreateViewModel.onCreateRevicionMedica(revicionMedica)
                 binding.constraintProgressBar.visibility=View.VISIBLE
                 binding.constraintLayout.visibility=View.GONE
             }

         }
        binding.btnCancelar.setOnClickListener {
            findNavController().popBackStack()
        }
        revicionMedicaCreateViewModel.createSuccess.observe(this,Observer{ it->
            findNavController().popBackStack()
            Snackbar.make(requireActivity().findViewById(R.id.activityCordinator),R.string.UpdateSuccessMessage,Snackbar.LENGTH_LONG).show()
        })
        revicionMedicaCreateViewModel.networkError.observe(this,Observer{ it->
            binding.constraintProgressBar.visibility=View.GONE
            binding.constraintLayout.visibility=View.VISIBLE
            Snackbar.make(requireActivity().findViewById(R.id.activityCordinator),R.string.UpdateErrorMessage,Snackbar.LENGTH_LONG).show()

        })



        return binding.root
    }

}