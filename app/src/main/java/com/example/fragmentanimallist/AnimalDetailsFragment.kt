package com.example.fragmentanimallist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


private const val ARG_POSITION = "positionList"

class AnimalDetailsFragment : Fragment() {

    private val TAG = "AnimalDetailsFragment"

    // Create an instance of our ViewModel
    lateinit var viewModel: PositionViewModel

    private val ANIMAL_NAME_LIST = listOf("Dog", "Cat", "Bear", "Rabbit")

    // Source wikipedia
    private val ANIMAL_DETAILS = listOf(

        "Dogs' typical lifespan varies widely among breeds, but for most the median longevity, " +
                "the age at which half the dogs in a population have died and half are still alive, " +
                "ranges from 10 to 13 years. Individual dogs may live well beyond the median of their breed.",

        "Cats' average lifespan of has risen in recent decades. In the early 1980s, " +
                "it was about seven years, rising to 9.4 years in 1995 15.1 years in 2018. " +
                "Some cats have been reported as surviving into their 30s, with the oldest known cat, " +
                "Creme Puff, dying at a verified age of 38",

        "Bears can live up to 25 years of age in the wild. The oldest wild brown bear on record was " +
                "nearly 37 years old.The oldest recorded female in captivity was nearly 40 years old, " +
                "while males in captivity have been verified to live up to 47 years, with one " +
                "captive male possibly attaining 50 years of age.",

        "Rabbits (Spayed or neutered) kept indoors with proper care may have a lifespan of 8 to 12 " +
                "years, with mixed-breed rabbits typically living longer than purebred specimens, " +
                "and dwarf breeds having longer average lifespans than larger breeds." +
                "The world record for longest-lived rabbit is 18 years."
    )

    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_animal_details, container, false)

        //ViewModelProvider returns an existing ViewModel if one exists,
        // or it creates a new one if it does not already exist.
        // Get an instance of our ViewModel using context
        // and then get the position
        viewModel = ViewModelProvider(requireActivity())[PositionViewModel::class.java]
        //val position = viewModel.position


        // With mutablelivedata version ?: elvis operator to assign 0 in case the value is null
        val position = viewModel.getPosition() ?: 0
        // The following can be used for observing values (may be helpful in the future (e.g., your class project))
        //viewModel.position.observe(requireActivity(), Observer {
        //    position = it
        //})


        // Based on the index of position selected, set the corresponding image
        val imageId = when(position){
            0 -> R.drawable.dog
            1 -> R.drawable.cat
            2 -> R.drawable.bear
            else -> R.drawable.rabbit
        }

        view.findViewById<ImageView>(R.id.animal_image).setImageResource(imageId)

        // Set the text to textviews after getting the selected position
        view.findViewById<TextView>(R.id.animal_name).text = ANIMAL_NAME_LIST[position]
        view.findViewById<TextView>(R.id.animal_details).text = ANIMAL_DETAILS[position]

        return view
    }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
        }
    }

  companion object {

        @JvmStatic
        fun newInstance(positionArgument: Int) =
            AnimalDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, positionArgument)
                }
            }
    }*/


}