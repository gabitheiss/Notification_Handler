package com.example.notificationhandler.view

import android.app.NotificationManager
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notificationhandler.R
import com.example.notificationhandler.databinding.MainFragmentBinding
import com.example.notificationhandler.services.NotificationHandler
import com.example.notificationhandler.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    @Inject
    lateinit var notificationHandler: NotificationHandler

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding : MainFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.buttonNotification.setOnClickListener{
            showNotification()
        }
    }

    fun showNotification(){
        notificationHandler.createNotification("ALERTA", "Testando notificações, rodando ok!").run{
            val notificationManager = NotificationManagerCompat.from(requireContext())
            notificationManager.notify(1,this)
        }

    }

}