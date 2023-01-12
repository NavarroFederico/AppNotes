package com.example.appnotes.ui.note_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apppositive.R
import com.example.apppositive.databinding.FragmentBottomSheetColorSelectorBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BottomSheetColorSelectorFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetColorSelectorBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var colorSelectorAdapter: ColorSelectorAdapter

    private val viewModel: NoteDetailViewModel by navGraphViewModels(R.id.note_detail_graph) { defaultViewModelProviderFactory }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetColorSelectorBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewColor.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = colorSelectorAdapter

        }
        colorSelectorAdapter.setOnItemClickListener { selectedColor ->
            viewModel.updateNoteColor(selectedColor)
            this.dismiss()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}