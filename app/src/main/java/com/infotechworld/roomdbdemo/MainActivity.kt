package com.infotechworld.roomdbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.infotechworld.roomdbdemo.adapter.ContactAdapter
import com.infotechworld.roomdbdemo.databinding.ActivityMainBinding
import com.infotechworld.roomdbdemo.model.ContactDetails
import com.infotechworld.roomdbdemo.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ContactViewModel
    lateinit var selectedContact: ContactDetails
    var isListItemClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        initVM()

        binding.buttonSaveUpdate.setOnClickListener({
            if (isListItemClicked) {
                updateContact()
            } else {
                viewModel.insertContact(ContactDetails(0,
                    binding.etName.text.toString(),
                    binding.etMobileNo.text.toString()))
            }
        })

        binding.buttonReset.setOnClickListener({
            if (isListItemClicked) {
                deletedContact()
            } else {
                resetField()
            }
        })
    }

    private fun updateContact() {
        viewModel.updateContact(ContactDetails(selectedContact.id,
            binding.etName.text.toString(),
            binding.etMobileNo.text.toString()))
        binding.buttonSaveUpdate.text = "Save"
        binding.buttonReset.text = "Reset"
        isListItemClicked = false
        resetField()
    }

    private fun deletedContact() {
        viewModel.deleteContact(ContactDetails(selectedContact.id,
            selectedContact.name,
            selectedContact.phone))

        binding.buttonSaveUpdate.text = "Save"
        binding.buttonReset.text = "Reset"
        isListItemClicked = false
        resetField()
    }

    private fun resetField(){
        binding.etName.setText("")
        binding.etMobileNo.setText("")
        binding.etName.clearFocus()
        binding.etMobileNo.clearFocus()
    }

    private fun initVM() {
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        viewModel.getContacts().observe(this, object : Observer<List<ContactDetails>> {
            override fun onChanged(value: List<ContactDetails>) {
                binding.recyclerView.adapter =
                    ContactAdapter(value) { s: ContactDetails -> listItemClick(s) }//lambda  param set outside of ()
                value.forEach {

                }
            }
        })
    }

    private fun listItemClick(contactDetails: ContactDetails) {
        //Toast.makeText(this, "${contactDetails.name}", Toast.LENGTH_LONG).show()
        selectedContact = contactDetails
        binding.buttonSaveUpdate.text = "Update"
        binding.buttonReset.text = "Delete"
        isListItemClicked = true

        binding.etName.setText(selectedContact.name)
        binding.etMobileNo.setText(selectedContact.phone)
    }
}