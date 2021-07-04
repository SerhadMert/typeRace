package com.example.typerace.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.typerace.R

class FragmentFinishActivity: Fragment() {


    override fun onAttach(context: Context) {
        Log.d("FragmentTwo","onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("FragmentTwo","onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("FragmentTwo","onCreateView")
        return inflater.inflate(R.layout.fragment_finish_activity,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("FragmentTwo","onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d("FragmentTwo","onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("FragmentTwo","onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("FragmentTwo","onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("FragmentTwo","onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("FragmentTwo","onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("FragmentTwo","onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("FragmentTwo","onDetach")
        super.onDetach()
    }
}