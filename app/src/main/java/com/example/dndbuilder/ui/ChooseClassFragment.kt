package com.example.dndbuilder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dndbuilder.R
import com.example.dndbuilder.data.CharacterClass
import com.example.dndbuilder.viewmodel.DndViewModel
import com.example.dndbuilder.databinding.FragmentChooseClassBinding

class ChooseClassFragment : Fragment() {

    private var _b : FragmentChooseClassBinding? = null
    private val b get() = _b!!

    private lateinit var viewModel: DndViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentChooseClassBinding.inflate(inflater, container,false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Get the shared ViewModel from the Activity
        viewModel = ViewModelProvider(requireActivity())[DndViewModel::class.java]




        b.btnWarrior.setOnClickListener {

            viewModel.selectClass(CharacterClass.WARRIOR)
            goToQuestions()
        }

        b.btnRogue.setOnClickListener {
            viewModel.selectClass(CharacterClass.ROGUE)
            goToQuestions()
        }

        b.btnWizard.setOnClickListener {
            viewModel.selectClass(CharacterClass.WIZARD)
            goToQuestions()
        }
    }

    private fun goToQuestions() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, QuestionFragment())
            .addToBackStack(null)
            .commit()
    }
}
