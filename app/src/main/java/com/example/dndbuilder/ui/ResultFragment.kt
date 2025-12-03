package com.example.dndbuilder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dndbuilder.R
import com.example.dndbuilder.databinding.FragmentQuestionBinding
import com.example.dndbuilder.viewmodel.DndViewModel
import com.example.dndbuilder.databinding.FragmentResultBinding
import com.example.dndbuilder.data.CharacterClass

class ResultFragment : Fragment() {

    private lateinit var viewModel: DndViewModel
    private var _b : FragmentResultBinding? = null
    private val b get() = _b!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentResultBinding.inflate(inflater, container,false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Shared ViewModel
        viewModel = ViewModelProvider(requireActivity())[DndViewModel::class.java]

        val clazz = viewModel.selectedClass.value



        viewModel.selectedClass.observe(viewLifecycleOwner) { clazz ->
            clazz?.let {

                val bgRes = when (clazz) {

                    CharacterClass.WARRIOR -> R.drawable.warrior
                    CharacterClass.WIZARD  -> R.drawable.wizard
                    CharacterClass.ROGUE   -> R.drawable.rogue
                    else                   -> R.drawable.warrior
                }

                b.ivClassCard.setImageResource(bgRes)


            }
        }

        viewModel.stats.observe(viewLifecycleOwner) { stats ->
            stats?.let {
                b.tvStr.text = "${it.str}"
                b.tvDex.text  = "${it.dex}"
                b.tvCon.text = "${it.con}"
                b.tvInt.text = "${it.intel}"
                b.tvWis.text = "${it.wis}"
                b.tvCha.text = "${it.cha}"
            }
        }
    }
}
