package com.example.typerace.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.typerace.R

class FragmentCorrect : Fragment() {


    override fun onAttach(context: Context) {
        Log.d("FragmentCorrect","onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("FragmentCorrect","onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("FragmentCorrect","onCreateView")
        return inflater.inflate(R.layout.fragment_correct,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("FragmentCorrect","onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d("FragmentCorrect","onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("FragmentCorrect","onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("FragmentCorrect","onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("FragmentCorrect","onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("FragmentCorrect","onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("FragmentCorrect","onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("FragmentCorrect","onDetach")
        super.onDetach()
    }
}