package com.lemillion.minke.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.composethemeadapter.MdcTheme
import com.lemillion.minke.databinding.FragmentAccountBinding
import com.lemillion.minke.ui.AccountView
import com.lemillion.minke.viewmodels.AccountListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private val viewModel: AccountListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAccountBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.composeAccount.setContent {
            MdcTheme {
                AccountView(viewModel)
            }
        }
        return binding.root
    }
}