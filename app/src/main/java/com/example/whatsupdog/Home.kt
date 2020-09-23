package com.example.whatsupdog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsupdog.model.BreedsAdapter
import com.example.whatsupdog.viewModel.MyViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment(), BreedsAdapter.Breeds {

    lateinit var mViewModel : MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) //activar menu item
        mViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRecyclerView           = recyclerView
        val mAdapter                = BreedsAdapter(this)
        mRecyclerView.adapter       = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mViewModel.mAllBreeds.observe(viewLifecycleOwner, Observer {
          mAdapter.updateListBreeds(it)

        })
    }

    override fun passBreeds(mBreeds: String) {
        val mBundle = Bundle()
        mBundle.putString("breed", mBreeds)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,mBundle)
    }
}