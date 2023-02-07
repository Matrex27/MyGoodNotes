package com.example.mygoodnotes.editModule.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.mygoodnotes.R
import com.example.mygoodnotes.common.entities.NoteEntity
import com.example.mygoodnotes.mainModule.View.MainActivity
import com.example.mygoodnotes.common.utils.DatePickerFragment
import com.example.mygoodnotes.common.utils.HourPickerFragment
import com.example.mygoodnotes.databinding.FragmentEditNoteBinding
import com.example.mygoodnotes.editModule.ViewModel.EditViewModel
import com.google.android.material.snackbar.Snackbar


class EditNoteFragment : Fragment() {

    private lateinit var mBinding: FragmentEditNoteBinding
    private lateinit var mFragmentManager: FragmentManager
    private var mActivity: MainActivity? = null

    //ViewModel
    private lateinit var  mEditViewModel: EditViewModel

    //Entidad con la que se trabaja en el EditFragment
    private var mNoteEntity : NoteEntity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mEditViewModel = ViewModelProvider(requireActivity()).get(EditViewModel::class.java) //Recuperamos ViewModel

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.etDate.setOnClickListener { showDatePickerDialog() }
        mBinding.etHour.setOnClickListener { showHourPickerDialog() }
        mBinding.switchReminder.setOnClickListener { isReminder() }
        mBinding.fabBackToMain.setOnClickListener { backToMain() }

        mBinding.btnAddNote.setOnClickListener {addNote()}


        setColorButtonsListener()
        setUpViewModel()

    }

    private fun setUpViewModel(){
        mEditViewModel.getNoteSelected().observe(viewLifecycleOwner){
            updateUi(it)
        }



    }

    private fun addNote(){
        mNoteEntity = NoteEntity(name = mBinding.noteNameEt.text.toString(),
                                description = mBinding.noteDescriptionEt.text.toString(),
                                color = mBinding.noteNameTil.boxBackgroundColor,
                                isReminder = mBinding.switchReminder.isChecked,
                                date = mBinding.etDate.text.toString(),
                                hour = mBinding.etHour.text.toString()
        )

        mEditViewModel.addNote(mNoteEntity!!)
        backToMain()

        mActivity?.binding?.let {
            Snackbar.make(it.root,R.string.add_note_success, Snackbar.LENGTH_SHORT ).show()
        }

    }


    private fun updateUi(noteEntity: NoteEntity){
        mBinding.noteNameTil.setBoxBackgroundColor(noteEntity.color)
        mBinding.noteNameEt.setText(noteEntity.name)
        mBinding.noteDescriptionEt.setText(noteEntity.description)
        if (noteEntity.isReminder){
            mBinding.switchReminder.isChecked = true
            isReminder()
            mBinding.etDate.setText(noteEntity.date)
            mBinding.etDate.setText(noteEntity.description)
        }

    }


    private fun backToMain(){
        mActivity = activity as? MainActivity
        mActivity?.onBackPressed()
        mActivity?.binding?.fabAddNote?.show()
    }



    private fun setColorButtonsListener(){  //Listener para cambiar el color de la descripcion
        mBinding.btnBlue.setOnClickListener {mBinding.noteNameTil.setBoxBackgroundColorResource(
            R.color.blue_radio_group
        )}
        mBinding.btnPurple.setOnClickListener {mBinding.noteNameTil.setBoxBackgroundColorResource(
            R.color.purple_radio_group
        )}
        mBinding.btnPink.setOnClickListener {mBinding.noteNameTil.setBoxBackgroundColorResource(
            R.color.pink_radio_group
        )}
        mBinding.btnGray.setOnClickListener {mBinding.noteNameTil.setBoxBackgroundColorResource(
            R.color.gray_radio_group
        )}
        mBinding.btnYellow.setOnClickListener {mBinding.noteNameTil.setBoxBackgroundColorResource(
            R.color.yellow_radio_group
        )}
    }


    private fun isReminder(){   //Se activa al presionar el Switch de recordatorio
        if(mBinding.switchReminder.isChecked){
            mBinding.tilDate.visibility = View.VISIBLE
            mBinding.tilHour.visibility = View.VISIBLE
        }else{
            mBinding.tilDate.visibility = View.GONE
            mBinding.tilHour.visibility = View.GONE
        }
    }



    //Date & Hour Picker -> Metodos
    private fun showDatePickerDialog() {
        mFragmentManager = parentFragmentManager

        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(mFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        mBinding.etDate.setText("$day/$month/$year")
    }

    private fun showHourPickerDialog(){
        mFragmentManager = parentFragmentManager
        val hourPicker = HourPickerFragment{ onHourSelected(it)}
        hourPicker.show(mFragmentManager, "hour")
    }

    private fun onHourSelected(time: String){
        mBinding.etHour.setText(time)
    }






}