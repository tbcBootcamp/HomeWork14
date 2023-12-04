package com.example.hw14.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hw14.adapter.RecyclerAdapter
import com.example.hw14.data.Contact
import com.example.hw14.databinding.FragmentHomeBinding
import com.example.hw14.viewmodels.ContactsViewModel
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: ContactsViewModel by viewModels()
    private lateinit var adapter: RecyclerAdapter

    override fun setUp() {
        adapter = RecyclerAdapter(
            clickEdit = { contact -> editContact(contact) },
            clickDelete = { contact -> removeContact(contact) }
        )
        binding.myRecycler.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.myRecycler.adapter = adapter

        // Observe changes in the contacts list
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.contacts.collect { contacts ->
                    adapter.submitList(contacts)
                }
            }
        }

        // Load initial contacts data
        viewModel.loadContacts()
    }

    override fun listeners() {
        with(binding) {
            refresher.setOnRefreshListener {
                refresh()
            }
            btnAdd.setOnClickListener {
                navigateToAddFragment()
            }

        }
    }

    private fun editContact(contact: Contact) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToAddContactFragment(contact)
        )
    }

    private fun removeContact(contact: Contact) {
        viewModel.removeContact(contact)
    }

    private fun refresh() {
        viewModel.loadContacts()
        binding.refresher.isRefreshing = false
    }

    private fun navigateToAddFragment() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddContactFragment(null))
    }
}