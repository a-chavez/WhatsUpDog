package com.example.whatsupdog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.whatsupdog.model.Repository
import com.example.whatsupdog.model.room.ImagesDAO
import com.example.whatsupdog.viewModel.MyViewModel
import kotlinx.android.synthetic.main.fragment_inside.*
import java.util.logging.LogManager

class Inside : Fragment(){

    var mBreed : String? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mBreed = it.getString("breed","")

            Log.d(" Arroz fragment inside =","Paso por oncreate y raza $mBreed")

                   }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inside, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_breed.text = mBreed.toString().capitalize()
        val mMyViewModel:MyViewModel


        view.findViewById<Button>(R.id.bt_home).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}