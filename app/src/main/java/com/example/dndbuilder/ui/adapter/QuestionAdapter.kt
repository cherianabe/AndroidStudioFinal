package com.example.dndbuilder.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dndbuilder.R
import com.example.dndbuilder.data.AnswerOption
import com.example.dndbuilder.data.Question

class QuestionAdapter(
    private val questions: List<Question>,
    private val onAnswerSelected: (Question, AnswerOption) -> Unit
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuestionText: TextView = itemView.findViewById(R.id.tvQuestionText)
        val rgAnswers: RadioGroup = itemView.findViewById(R.id.rgAnswers)
        val rbOption1: RadioButton = itemView.findViewById(R.id.rbOption1)
        val rbOption2: RadioButton = itemView.findViewById(R.id.rbOption2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.tvQuestionText.text = question.text

        val option1 = question.options[0]
        val option2 = question.options[1]

        holder.rbOption1.text = option1.text
        holder.rbOption2.text = option2.text

        holder.rgAnswers.setOnCheckedChangeListener(null)
        holder.rgAnswers.clearCheck()

        holder.rgAnswers.setOnCheckedChangeListener { _, checkedId ->
            val selected = when (checkedId) {
                holder.rbOption1.id -> option1
                holder.rbOption2.id -> option2
                else -> null
            }
            selected?.let { onAnswerSelected(question, it) }
        }
    }
}
