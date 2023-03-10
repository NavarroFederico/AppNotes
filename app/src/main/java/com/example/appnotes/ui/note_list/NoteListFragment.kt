package com.example.appnotes.ui.note_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.appnotes.ui.utils.changeStatusBarColor
import com.example.appnotes.ui.utils.hideKeyboard
import com.example.apppositive.R
import com.example.apppositive.databinding.FragmentNoteListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment : Fragment() {
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var noteListAdapter: NoteListAdapter

    private val viewModel: NoteListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.imageViewDarkMode.setOnClickListener { viewModel.toggleDarkMode() }

        binding.imageViewLayoutList.setOnClickListener { viewModel.toggleLayoutMode() }
        binding.recyclerViewNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteListAdapter
        }
        binding.fabNewNota.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment()
            findNavController().navigate(action)

        }

        noteListAdapter.setOnItemClicklistener { noteId ->
            val action =
                NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(noteId)
            findNavController().navigate(action)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            //Nos permite hacer la busqueda cuando el usuario aprete el boton aceptar en el teclado
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null) {
                    searchDatabase(query)
                    binding.searchView.hideKeyboard()
                    true
                } else {
                    false
                }
            }

            //onQueryTextChange a medida que vaya modificando el texto de la busqueda.
            override fun onQueryTextChange(query: String?): Boolean {
                return if (query != null) {
                    searchDatabase(query)
                    true
                } else {
                    false
                }
            }
        })
        //llama a la lista de notas y reacciona a sus cambios
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.noteList.collect { noteList ->
                noteListAdapter.submitList(noteList)
            }
        }
        //observa el estado de nuestra variable darkMode
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.darkMode.collect{
                isDarkMode ->
                if(isDarkMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }

        //observa el estado si esta en linear o en grid
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.linearLayoutMode.collect{ isLinearLayout ->
                if(isLinearLayout){
                    binding.imageViewLayoutList.setImageDrawable(
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_grid)
                    )
                    binding.recyclerViewNotes.layoutManager = LinearLayoutManager(requireContext())
                }else{
                    binding.imageViewLayoutList.setImageDrawable(
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_list)
                    )
                    binding.recyclerViewNotes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                }

            }        }
    }

    private fun searchDatabase(query: String) {
        viewModel.updateQuery(query)
    }



override fun onResume() {
    super.onResume()
    requireActivity().window.changeStatusBarColor(R.color.app_bg_color)
    viewModel.getNotes()
    //  viewModel.getNotesCache()

}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}

}