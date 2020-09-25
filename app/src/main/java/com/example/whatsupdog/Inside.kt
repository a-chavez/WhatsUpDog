package com.example.whatsupdog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsupdog.model.ImagesAdapter
import com.example.whatsupdog.model.Repository
import com.example.whatsupdog.model.room.ImagesDAO
import com.example.whatsupdog.viewModel.MyViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_inside.*
import kotlinx.android.synthetic.main.fragment_inside.recyclerView
import java.util.logging.LogManager

class Inside : Fragment(){

    var mBreed : String? =null
    lateinit var mViewModel : MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mBreed = it.getString("breed","")
            Log.d("Arroz fragment inside =","Paso por oncreate y raza $mBreed")
            }
        mViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

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

        val mRecyclerView           = recyclerView
        val mAdapter                = ImagesAdapter()
        mRecyclerView.adapter       = mAdapter
        mRecyclerView.layoutManager = GridLayoutManager(context, 2)

        mBreed?.let {
            mViewModel.getImages(it)
            Log.d("Arroz fragment inside =","Ejecuto la funcion con raza $it")
        }

        tv_breed.text = mBreed.toString().capitalize()
        mViewModel.mAllImages.observe(viewLifecycleOwner, Observer {
          mAdapter.updateListBreedsImages(it)

        })

        view.findViewById<Button>(R.id.bt_home).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}