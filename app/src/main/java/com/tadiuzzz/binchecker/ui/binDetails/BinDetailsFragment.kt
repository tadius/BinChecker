package com.tadiuzzz.binchecker.ui.binDetails

import android.content.Intent
import android.net.Uri
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
import org.koin.core.parameter.parametersOf


class BinDetailsFragment : Fragment() {

    private var _binding: FragmentBinDetailsBinding? = null
    private val binding get() = _binding!!

    private val binId: Long? by lazy {
        val args = arguments
        if (args != null) {
            BinDetailsFragmentArgs.fromBundle(args).binId
        } else null
    }

    private val viewModel: BinDetailsViewModel by viewModel { parametersOf(binId) }

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
            viewModel.openMapEvent.collect { geo ->
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(geo)))
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.openPhoneEvent.collect { phone ->
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(phone)))
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.openWebEvent.collect { link ->
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
            }
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

                            tvCardNumberLength.text = (binInfo.number?.length ?: "?").toString()
                            val luhn = if (binInfo.number?.luhn != null) {
                                if (binInfo.number.luhn) "YES" else "NO"
                            } else "?"
                            tvCardLuhn.text = luhn

                            val countryText =
                                if (binInfo.country?.emoji != null && binInfo.country.name != null) {
                                    "${binInfo.country.emoji} ${binInfo.country.name}"
                                } else "?"
                            tvCountry.text = countryText
                            tvCountryLat.text = (binInfo.country?.latitude ?: "?").toString()
                            tvCountryLon.text = (binInfo.country?.longitude ?: "?").toString()

                            tvBank.text = binInfo.bank?.name ?: "?"
                            tvLink.text = binInfo.bank?.url ?: "?"
                            tvPhone.text = binInfo.bank?.phone ?: "?"

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

}