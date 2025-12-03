package com.example.dndbuilder.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dndbuilder.R
import com.example.dndbuilder.databinding.FragmentChooseClassBinding
import com.example.dndbuilder.ui.adapter.QuestionAdapter
import com.example.dndbuilder.viewmodel.DndViewModel
import com.example.dndbuilder.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    private lateinit var viewModel: DndViewModel

   private var _b : FragmentQuestionBinding? = null
    private val b get() = _b!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentQuestionBinding.inflate(inflater, container,false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[DndViewModel::class.java]

        val rvQuestions: RecyclerView = view.findViewById(R.id.rvQuestions)
        val btnNext: Button = view.findViewById(R.id.btnNext)

        // Observe questions and set up RecyclerView
        viewModel.questions.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                rvQuestions.layoutManager = LinearLayoutManager(requireContext())
                rvQuestions.adapter = QuestionAdapter(list) { question, answer ->
                    viewModel.applyAnswer(question, answer)
                }
            }
        }

        // Observe stats and update the top stats bar live
        viewModel.stats.observe(viewLifecycleOwner) { stats ->
            stats?.let {
                b.tvStrQuestion.text = "STR: ${it.str}"
                b.tvDexQuestion.text = "DEX: ${it.dex}"
                b.tvConQuestion.text = "CON: ${it.con}"
                b.tvIntQuestion.text = "INT: ${it.intel}"
                b.tvWisQuestion.text = "WIS: ${it.wis}"
                b.tvChaQuestion.text = "CHA: ${it.cha}"
            }
        }

        btnNext.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ResultFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
