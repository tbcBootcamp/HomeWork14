package com.example.hw14.fragments

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw14.data.Contact
import com.example.hw14.databinding.FragmentAddContactBinding
import com.example.hw14.viewmodels.ContactsViewModel
import java.util.UUID


class AddContactFragment :
    BaseFragment<FragmentAddContactBinding>(FragmentAddContactBinding::inflate) {

    private val args: AddContactFragmentArgs by navArgs()
    private val viewModel: ContactsViewModel by viewModels()

    override fun setUp() {
        with(binding) {
            etEmail.isVisible = swWthEmail.isChecked
        }
        binding.apply {
            args.contactInfo?.apply {
                etName.setText(name)
                etNumber.setText(number)
                etEmail.setText(email)
            }
        }
    }

    override fun listeners() {
        binding.swWthEmail.setOnCheckedChangeListener { _, isChecked ->
            binding.etEmail.isVisible = isChecked
        }
        binding.btnAddContact.setOnClickListener {
            if (args.contactInfo != null) {
                editContact()
            } else {
                addContact()
            }
            findNavController().navigateUp()
        }
    }

    private fun editContact() {
        val updatedContact = Contact(
            name = binding.etName.text.toString(),
            number = binding.etNumber.text.toString(),
            email = binding.etEmail.text.toString(),
            withEmail = binding.swWthEmail.isChecked,
            id = args.contactInfo?.id ?: UUID.randomUUID()
        )
        viewModel.updateContact(updatedContact)
        val msg = "contact updated successfully"
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun addContact() {
        val newContact = Contact(
            name = binding.etName.text.toString(),
            number = binding.etNumber.text.toString(),
            email = binding.etEmail.text.toString(),
            withEmail = binding.swWthEmail.isChecked
        )
        viewModel.addContact(newContact)
        val msg = "contact added successfully"
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}