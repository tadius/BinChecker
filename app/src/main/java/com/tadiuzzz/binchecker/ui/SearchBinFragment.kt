package com.tadiuzzz.binchecker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.tadiuzzz.binchecker.databinding.FragmentSearchBinBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchBinFragment : Fragment() {

    private var _binding: FragmentSearchBinBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchBinViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etBin.doOnTextChanged { text, start, before, count ->
            viewModel.onBinTextChanged(text.toString())
        }
        binding.btnSearch.setOnClickListener {
            viewModel.onSearchBtnClicked()
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        viewModel.binText.observe(viewLifecycleOwner) { bin ->
            if (binding.etBin.text.toString() != bin) {
                binding.etBin.setText(bin)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}