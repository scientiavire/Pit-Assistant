package com.scientiavitae.pitassistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scientiavitae.pitassistant.databinding.FragmentRouletteCalcBinding

class RouletteCalcFragment : Fragment() {

    private lateinit var binding: FragmentRouletteCalcBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRouletteCalcBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}