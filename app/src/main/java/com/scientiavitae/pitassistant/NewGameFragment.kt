package com.scientiavitae.pitassistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scientiavitae.pitassistant.databinding.FragmentNewGameBinding

class NewGameFragment : Fragment() {
    private lateinit var binding: FragmentNewGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}