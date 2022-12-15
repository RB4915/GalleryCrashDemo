package com.shadowapps.gallerycrashdemo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shadowapps.gallerycrashdemo.R
import com.shadowapps.gallerycrashdemo.databinding.FragmentSelectDemoBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SelectDemoFragment : Fragment() {

    private var _binding: FragmentSelectDemoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSelectDemoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_SelectDemoFragment_to_CrashDemoFragment)
        }
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SelectDemoFragment_to_GalleryDemoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}