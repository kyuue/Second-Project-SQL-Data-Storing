package com.example.myfirstnativeandroidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.myfirstnativeandroidapp.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    companion object {
        lateinit var DatabaseHelper: MedeniyetDbHelper
    }


    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        DatabaseHelper = MedeniyetDbHelper( context )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        binding.button.setOnClickListener {
            onMyButtonClicked(view);
        }
    }

    private fun onMyButtonClicked(view: View) {

        try {

            val db = DatabaseHelper.readableDatabase

            // Filter results WHERE "title" = 'My Title'
            val selection = "key = ?"
            val selectionArgs = arrayOf(binding.queryStringTextBox.text.toString())


            val cursor = db.query(
                "Ders_odev",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
            )

            if (cursor.count <= 0)
            {

            }
            else
            {

            }

            if(cursor.moveToFirst())
            {
                val newFragment = InfoDialogFragment( cursor.getString(cursor.getColumnIndexOrThrow("value")))
                newFragment.show(parentFragmentManager, "info")
            }
            else
            {
                val newFragment = InfoDialogFragment("Ders bulunamadı")
                newFragment.show(parentFragmentManager, "info")
            }

        } catch (e: Exception) {
            val newFragment = InfoDialogFragment(e.toString())
            newFragment.show(parentFragmentManager, "info")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

