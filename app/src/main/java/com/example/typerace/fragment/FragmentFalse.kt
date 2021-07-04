package com.example.typerace.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.typerace.R

class FragmentFalse: Fragment() {


    override fun onAttach(context: Context) {
        Log.d("FragmentFalse","onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("FragmentFalse","onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("FragmentFalse","onCreateView")
        return inflater.inflate(R.layout.fragment_false,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("FragmentFalse","onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d("FragmentFalse","onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("FragmentFalse","onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("FragmentFalse","onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("FragmentFalse","onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("FragmentFalse","onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("FragmentFalse","onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("FragmentFalse","onDetach")
        super.onDetach()
    }
}