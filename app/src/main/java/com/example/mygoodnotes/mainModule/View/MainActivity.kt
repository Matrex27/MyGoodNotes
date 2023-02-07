package com.example.mygoodnotes.mainModule.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mygoodnotes.editModule.View.EditNoteFragment
import com.example.mygoodnotes.mainModule.Adapter.NoteListAdapter
import com.example.mygoodnotes.common.utils.OnClickListener
import com.example.mygoodnotes.R
import com.example.mygoodnotes.common.entities.NoteEntity
import com.example.mygoodnotes.databinding.ActivityMainBinding
import com.example.mygoodnotes.editModule.ViewModel.EditViewModel
import com.example.mygoodnotes.mainModule.ViewModel.MainViewModel

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityMainBinding

    //Variables adapter
    private lateinit var mAdapter: NoteListAdapter
    private lateinit var mLayoutManager: GridLayoutManager

    //ViewModel
    private lateinit var mViewModel: MainViewModel
    private lateinit var mEditViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddNote.setOnClickListener {launchEditFragment(noteEntity = NoteEntity())}

        setUpRecyclerView()
        setUpViewModel()

    }



    private fun setUpRecyclerView(){

        mAdapter = NoteListAdapter(this)
        mLayoutManager = GridLayoutManager(this, 2)

        binding.recyclerViewNotes.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = mLayoutManager
        }
    }

    private fun setUpViewModel(){
        //MAIN VIEWMODEL
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mViewModel.getNotes().observe(this){
            mAdapter.submitList(it)
        }

        //EDIT VIEWMODEL
        mEditViewModel = ViewModelProvider(this).get(EditViewModel::class.java)


    }

    private fun launchEditFragment(noteEntity: NoteEntity){
        val fragment = EditNoteFragment()

        mEditViewModel.setNoteSelected(noteEntity)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.MainActivity, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        binding.fabAddNote.hide()
    }


    //Metodos OnClickListener
    override fun onClick(noteEntity: NoteEntity) {
        launchEditFragment(noteEntity)
    }

}

