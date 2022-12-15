package com.shadowapps.gallerycrashdemo.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shadowapps.gallerycrashdemo.databinding.FragmentGalleryDemoBinding
import com.shadowapps.gallerycrashdemo.helpers.PhotoPickerAvailabilityChecker

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GalleryDemoFragment : Fragment() {

    private lateinit var pickSingleMediaLauncherOld: ActivityResultLauncher<Intent>
    private lateinit var pickSingleMediaLauncherOldTwo: ActivityResultLauncher<Intent>
    private lateinit var pickSingleMediaLauncher: ActivityResultLauncher<Intent>
    private lateinit var pickMultipleMediaLauncher: ActivityResultLauncher<Intent>

    private var _binding: FragmentGalleryDemoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGalleryDemoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagesList = ArrayList<String>()
        // Initialize single media picker launcher Old Way
        pickSingleMediaLauncherOld =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode != Activity.RESULT_OK) {
                    Toast.makeText(requireActivity(), "Failed picking media.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val uri = it.data?.data
                    imagesList.clear()
                    imagesList.add(uri!!.toString())
                    val action =
                        GalleryDemoFragmentDirections.actionGalleryDemoFragmentToViewSelectedImageFragment(
                            imagesList.toTypedArray()
                        )
                    findNavController().navigate(
                        action
                    )
                }
            }

        // Initialize multiple media picker launcher Old Way
        pickSingleMediaLauncherOldTwo =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode != Activity.RESULT_OK) {
                    Toast.makeText(requireActivity(), "Failed picking media.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    imagesList.clear()
                    val uris = it.data?.clipData ?: return@registerForActivityResult
                    for (index in 0 until uris.itemCount) {
                        imagesList.add(uris.getItemAt(index).uri.toString())
                    }

                    val action =
                        GalleryDemoFragmentDirections.actionGalleryDemoFragmentToViewSelectedImageFragment(
                            imagesList.toTypedArray()
                        )
                    findNavController().navigate(
                        action
                    )
                }

            }

        // Initialize single media picker launcher
        pickSingleMediaLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode != Activity.RESULT_OK) {
                    Toast.makeText(requireActivity(), "Failed picking media.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    imagesList.clear()
                    val uri = it.data?.data
                    imagesList.add(uri!!.toString())

                    val action =
                        GalleryDemoFragmentDirections.actionGalleryDemoFragmentToViewSelectedImageFragment(
                            imagesList.toTypedArray()
                        )
                    findNavController().navigate(
                        action
                    )
                }
            }
        // Initialize multiple media picker launcher
        pickMultipleMediaLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode != Activity.RESULT_OK) {
                    Toast.makeText(requireActivity(), "Failed picking media.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    imagesList.clear()
                    val uris = it.data?.clipData ?: return@registerForActivityResult
                    var uriPaths = ""
                    for (index in 0 until uris.itemCount) {
                        uriPaths += uris.getItemAt(index).uri.path
                        uriPaths += "\n"
                        imagesList.add(uris.getItemAt(index).uri.toString())
                    }
                    val action =
                        GalleryDemoFragmentDirections.actionGalleryDemoFragmentToViewSelectedImageFragment(
                            imagesList.toTypedArray()
                        )
                    findNavController().navigate(
                        action
                    )

                }
            }
        if (PhotoPickerAvailabilityChecker.isPhotoPickerAvailable()) {
            // Setup pick 1 image/video
            binding.buttonFirst.setOnClickListener {
                pickSingleMediaLauncher.launch(
                    Intent(MediaStore.ACTION_PICK_IMAGES)
                )
            }
            // Setup pick 1 image
            binding.buttonSecond.setOnClickListener {
                pickSingleMediaLauncher.launch(
                    Intent(MediaStore.ACTION_PICK_IMAGES)
                        .apply {
                            type = "image/*"
                        }
                )
            }
            // Setup pick 1 video
            binding.buttonThird.setOnClickListener {
                pickSingleMediaLauncher.launch(
                    Intent(MediaStore.ACTION_PICK_IMAGES)
                        .apply {
                            type = "video/*"
                        }
                )
            }
            // Setup pick 3 images
            binding.buttonForth.setOnClickListener {
                pickMultipleMediaLauncher.launch(
                    Intent(MediaStore.ACTION_PICK_IMAGES)
                        .apply {
                            type = "image/*"
                            putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, 3)
                        }
                )
            }


            // Setup max pick medias
            val maxPickMedia = MediaStore.getPickImagesMaxLimit()
            binding.txtTotal.text = "Max Pick Media: $maxPickMedia"

            // Setup pick 100 images
            binding.buttonFifth.setOnClickListener {
                pickMultipleMediaLauncher.launch(
                    Intent(MediaStore.ACTION_PICK_IMAGES)
                        .apply {
                            type = "image/*"
                            putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, 25)
                        }
                )
            }
        } else {
            Toast.makeText(requireActivity(), "Please use Android 13 device.", Toast.LENGTH_SHORT)
                .show()
        }

        binding.buttonOldOne.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/* video/*"
            pickSingleMediaLauncherOld.launch(
                intent
            )

        }

        binding.buttonOldTwo.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)

            pickSingleMediaLauncherOldTwo.launch(
                intent
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}