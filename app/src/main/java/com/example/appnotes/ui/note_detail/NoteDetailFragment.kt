package com.example.appnotes.ui.note_detail

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.appnotes.ui.utils.showKeyboard
import com.example.apppositive.R
import com.example.apppositive.databinding.FragmentNoteDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {
    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteDetailViewModel by navGraphViewModels(R.id.note_detail_graph){defaultViewModelProviderFactory}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTextNoteContent.showKeyboard()
        binding.imageViewNoteColor.setOnClickListener{
            val action= NoteDetailFragmentDirections.actionNoteDetailFragmentToBottomSheetColorSelectorFragment()
            findNavController().navigate(action)
        }
        binding.imageViewArrowBack.setOnClickListener {
            saveNote()
        }
        //Funcion si hace el gesto hacia atras se guarde la nota
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object :OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    saveNote()
                }

            }
        )
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.selectedColor.collect{ selectedColor ->
                binding.noteContainer.setBackgroundColor(
                    ContextCompat.getColor(
                       requireContext(),selectedColor

                    )
                )
            }
        }
        //detecta si la nota es modificada navega hacia atras
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.noteHasBeenModified.collect{ noteHasBeenModified->
                if (noteHasBeenModified){
                    findNavController().popBackStack()
                }
        } }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.note.collect{
                it?.let{
                    note->
                    binding.editTextNoteTitle.setText(note.title)
                    binding.editTextNoteContent.append( "${note.content} ")
                    viewModel.updateNoteColor(note.color)
                    binding.imageViewDeleteNote.isVisible = true

                    val updatedAt = Date(note.updated)

                    val dateFormat: SimpleDateFormat = if(DateUtils.isToday(updatedAt.time)){
                        SimpleDateFormat("hh:mm a", Locale.ROOT)

                    }else{
                        SimpleDateFormat("dd/MM/y ", Locale.ROOT)

                    }
                    binding.textViewNoteModified.text = "Editado ${dateFormat.format(updatedAt)}"
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun saveNote(){
        viewModel.savedNoteChanges(
            title = binding.editTextNoteTitle.text.toString()   ,
            content = binding.editTextNoteContent.text.toString()
        )
    }
}