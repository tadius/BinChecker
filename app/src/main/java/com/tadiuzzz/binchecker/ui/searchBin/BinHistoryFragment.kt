package com.tadiuzzz.binchecker.ui.searchBin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tadiuzzz.binchecker.databinding.FragmentBinHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BinHistoryFragment : Fragment() {

    private var _binding: FragmentBinHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BinHistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBinHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNewSearch.setOnClickListener {
            viewModel.onNewSearchClicked()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.openSearchBinScreenEvent.collect { binId ->
                val action =
                    BinHistoryFragmentDirections.actionSearchBinFragmentToBinDetailsFragment(
                        binId ?: -1
                    )
                findNavController().navigate(action)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}