package com.shadowapps.gallerycrashdemo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shadowapps.gallerycrashdemo.R
import com.shadowapps.gallerycrashdemo.adapters.ViewImagesAdapter
import com.shadowapps.gallerycrashdemo.databinding.FragmentViewSelectedImagesBinding
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ViewSelectedImageFragment : Fragment() {

    private var _binding: FragmentViewSelectedImagesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args : ViewSelectedImageFragmentArgs by navArgs()
    var imageList:ArrayList<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentViewSelectedImagesBinding.inflate(inflater, container, false)

        val imageArg = args.images
        imageList = imageArg.toCollection(kotlin.collections.ArrayList())

        for(i in 0 until imageList!!.size){
            Log.e("Images", imageList!![i])
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRecyclerAdapter = ViewImagesAdapter(imageList!!,requireActivity())
        val mRecycler = view.findViewById<RecyclerView>(R.id.rvSelectedImages)
        val layoutManager = GridLayoutManager(requireActivity(),2)
        mRecycler.layoutManager = layoutManager
        mRecycler.adapter = mRecyclerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

