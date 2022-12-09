package com.yusuftalhaklc.depremtakp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuftalhaklc.depremtakp.R
import com.yusuftalhaklc.depremtakp.adapter.QuakeAdapter
import com.yusuftalhaklc.depremtakp.viewmodel.QuakeViewModel
import kotlinx.android.synthetic.main.fragment_main_screen.*

class MainScreen : Fragment() {
    lateinit var adapter: QuakeAdapter
    lateinit var viewModel:QuakeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = QuakeAdapter(arrayListOf(),this,view)

        viewModel = ViewModelProvider(this).get(QuakeViewModel::class.java)
        viewModel.getDataFromApi()

        quakesRcyclerView.layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.VERTICAL,false)
        quakesRcyclerView.adapter = adapter

        observeData()
    }
    private fun observeData(){
        viewModel.quakesLiveData.observe(viewLifecycleOwner, Observer {
            it?.let{

                adapter.refreshList(it)

                quakesRcyclerView.visibility = View.VISIBLE
                //errorTextView.visibility = View.GONE

            }
        })
        viewModel.quakesLoading.observe(viewLifecycleOwner, Observer {
            it?.let{
                if(it){
                    loadingBar.visibility = View.VISIBLE
                    error.visibility = View.GONE
                    quakesRcyclerView.visibility = View.GONE
                }
                else {
                   loadingBar.visibility = View.GONE
                }
            }
        })
        viewModel.quakesError.observe(viewLifecycleOwner, Observer {
            it?.let{
                if(it){
                    quakesRcyclerView.visibility= View.GONE
                    loadingBar.visibility = View.GONE
                   error.visibility = View.VISIBLE
                }
                else {
                   error.visibility = View.GONE
                }
            }
        })



    }
}