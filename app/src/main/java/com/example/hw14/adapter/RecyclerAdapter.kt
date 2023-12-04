package com.example.hw14.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw14.data.Contact
import com.example.hw14.databinding.RecyclerItemBinding
import com.example.hw14.databinding.RecyclerItemWithEmailBinding

class RecyclerAdapter(
    private val clickEdit: (Contact) -> Unit,
    private val clickDelete: (Contact) -> Unit
) : ListAdapter<Contact, RecyclerView.ViewHolder>(ContactDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == CONTACT_ITEM) {
            ItemViewHolder(
                RecyclerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            ItemWithNoteViewHolder(
                RecyclerItemWithEmailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == CONTACT_ITEM) {
            (holder as ItemViewHolder).bind(getItem(position), clickEdit, clickDelete)
        } else {
            (holder as ItemWithNoteViewHolder).bind(getItem(position), clickEdit, clickDelete)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).withEmail) CONTACT_WITH_EMAIL_ITEM else CONTACT_ITEM
    }

    inner class ItemWithNoteViewHolder(private val binding: RecyclerItemWithEmailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Contact, clickEdit: (Contact) -> Unit, clickDelete: (Contact) -> Unit) {
            with(binding) {
                tvName.text = model.name
                tvPhoneNumber.text = model.number
                model.email?.let {
                    tvEmail.text = it
                }
                btnEdit.setOnClickListener { clickEdit(model) }
                btnDelete.setOnClickListener { clickDelete(model) }
            }
        }
    }

    inner class ItemViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Contact, clickEdit: (Contact) -> Unit, clickDelete: (Contact) -> Unit) {
            with(binding) {
                tvName.text = model.name
                tvPhoneNumber.text = model.number
                btnEdit.setOnClickListener { clickEdit(model) }
                btnDelete.setOnClickListener { clickDelete(model) }
            }
        }
    }

    companion object {
        const val CONTACT_ITEM = 1
        const val CONTACT_WITH_EMAIL_ITEM = 2
    }
}