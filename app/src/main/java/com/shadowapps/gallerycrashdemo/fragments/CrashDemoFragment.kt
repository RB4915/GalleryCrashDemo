package com.shadowapps.gallerycrashdemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shadowapps.gallerycrashdemo.databinding.FragmentCrashDemoBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CrashDemoFragment : Fragment() {

    private var _binding: FragmentCrashDemoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCrashDemoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonError.setOnClickListener {
            throw Error("Hello, Crash the App")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}