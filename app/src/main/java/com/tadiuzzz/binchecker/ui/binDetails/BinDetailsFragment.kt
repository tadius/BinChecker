package com.tadiuzzz.binchecker.ui.binDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tadiuzzz.binchecker.databinding.FragmentBinDetailsBinding
import com.tadiuzzz.binchecker.ui.binDetails.model.BinDetailsState
import org.koin.androidx.viewmodel.ext.android.viewModel

class BinDetailsFragment : Fragment() {

    private var _binding: FragmentBinDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BinDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBinDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etBin.doOnTextChanged { text, start, before, count ->
            viewModel.onBinTextChanged(text.toString())
        }
        binding.btnSearch.setOnClickListener {
            viewModel.onSearchBtnClicked()
        }

        binding.btnOpenMap.setOnClickListener {
            viewModel.onMapBtnClicked()
        }

        binding.tvLink.setOnClickListener {
            viewModel.onLinkClicked()
        }

        binding.tvPhone.setOnClickListener {
            viewModel.onPhoneClicked()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.binText.collect { bin ->
                if (binding.etBin.text.toString() != bin) {
                    binding.etBin.setText(bin)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.binDetailsState.collect { state ->
                when (state) {
                    is BinDetailsState.Editing -> {
                        binding.flProgress.isVisible = false
                        binding.tvError.visibility = View.INVISIBLE

                        clearFields()
                    }
                    is BinDetailsState.Success -> {
                        binding.flProgress.isVisible = false
                        binding.tvError.visibility = View.INVISIBLE

                        val binInfo = state.binInfo
                        with(binding) {
                            tvScheme.text = binInfo.scheme
                            tvType.text = binInfo.type
                            tvBrand.text = binInfo.brand
                            tvPrepaid.text = if (binInfo.prepaid) "YES" else "NO"

                            binInfo.number?.let {
                                tvCardNumberLength.text = binInfo.number.length.toString()
                                tvCardLuhn.text = if (binInfo.number.luhn) "YES" else "NO"
                            }

                            binInfo.country?.let {
                                val countryText = "${binInfo.country.emoji} ${binInfo.country.name}"
                                tvCountry.text = countryText
                                tvCountryLat.text = binInfo.country.latitude.toString()
                                tvCountryLon.text = binInfo.country.longitude.toString()
                            }

                            binInfo.bank?.let {
                                tvBank.text = binInfo.bank.name
                                tvLink.text = binInfo.bank.url
                                tvPhone.text = binInfo.bank.phone
                            }

                        }
                    }
                    is BinDetailsState.Loading -> {
                        binding.flProgress.isVisible = true
                        binding.tvError.visibility = View.INVISIBLE
                        clearFields()
                    }
                    is BinDetailsState.Error -> {
                        binding.flProgress.isVisible = false
                        binding.tvError.isVisible = true
                        binding.tvError.text = state.errorText
                        clearFields()
                    }
                }

            }
        }
    }

    private fun clearFields() {
        with(binding) {
            tvScheme.text = "?"
            tvType.text = "?"
            tvBrand.text = "?"
            tvPrepaid.text = "?"

            tvCardNumberLength.text = "?"
            tvCardLuhn.text = "?"

            tvCountry.text = "?"
            tvCountryLat.text = "?"
            tvCountryLon.text = "?"

            tvBank.text = "?"
            tvLink.text = "?"
            tvPhone.text = "?"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //TODO pass argument
}