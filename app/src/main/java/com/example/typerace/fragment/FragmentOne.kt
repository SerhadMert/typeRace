package com.example.typerace.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.typerace.R

class FragmentOne : Fragment() {


    override fun onAttach(context: Context) {
        Log.d("FragmentOne","onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("FragmentOne","onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("FragmentOne","onCreateView")
        return inflater.inflate(R.layout.fragment_one,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("FragmentOne","onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d("FragmentOne","onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("FragmentOne","onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("FragmentOne","onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("FragmentOne","onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("FragmentOne","onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("FragmentOne","onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("FragmentOne","onDetach")
        super.onDetach()
    }
}