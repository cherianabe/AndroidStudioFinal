package com.example.dndbuilder.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dndbuilder.data.*

class DndViewModel : ViewModel() {

    private val MAX_TOTAL = 72

    private val _selectedClass = MutableLiveData<CharacterClass?>()
    val selectedClass: LiveData<CharacterClass?> = _selectedClass

    private val _stats = MutableLiveData<Stats?>()
    val stats: LiveData<Stats?> = _stats

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> = _questions

    private val answersMap = mutableMapOf<String, AnswerOption>()


    fun selectClass(clazz: CharacterClass) {
        _selectedClass.value = clazz
        _stats.value = QuestionRepository.getBaseStats(clazz)
        _questions.value = QuestionRepository.getQuestionsFor(clazz)
        answersMap.clear()
    }


    private fun totalOf(stats: Stats): Int {
        return stats.str + stats.dex + stats.con +
                stats.intel + stats.wis + stats.cha
    }


    private fun clampToMax(stats: Stats) {
        var total = totalOf(stats)
        if (total <= MAX_TOTAL) return

        var overflow = total - MAX_TOTAL

        val list = mutableListOf(
            "str" to stats.str,
            "dex" to stats.dex,
            "con" to stats.con,
            "intel" to stats.intel,
            "wis" to stats.wis,
            "cha" to stats.cha
        )

        list.sortByDescending { it.second }

        var i = 0
        while (overflow > 0 && i < list.size) {
            val (name, value) = list[i]
            if (value > 1) {
                list[i] = name to (value - 1)
                overflow--
            } else {
                i++
            }
        }

        for ((name, value) in list) {
            when (name) {
                "str" -> stats.str = value
                "dex" -> stats.dex = value
                "con" -> stats.con = value
                "intel" -> stats.intel = value
                "wis" -> stats.wis = value
                "cha" -> stats.cha = value
            }
        }
    }


    fun applyAnswer(question: Question, answer: AnswerOption) {
        answersMap[question.id] = answer

        val stats = _stats.value ?: return

        when (question.id) {

            // ===== WARRIOR =====
            "warrior_armor" -> {
                when (answer.id) {
                    "heavy" -> { stats.con += 1; stats.dex -= 1 }
                    "light" -> { stats.dex += 1; stats.con -= 1 }
                }
            }

            "warrior_role" -> {
                when (answer.id) {
                    "tank" -> { stats.con += 1; stats.str -= 1 }
                    "finisher" -> { stats.str += 1; stats.con -= 1 }
                }
            }

            "warrior_magic" -> {
                when (answer.id) {
                    "cantrips" -> { stats.intel += 1; stats.str -= 1 }
                    "rage" -> { stats.str += 1; stats.intel -= 1 }
                }
            }

            "warrior_personality" -> {
                when (answer.id) {
                    "sweet" -> { stats.cha += 1; stats.str -= 1 }
                    "intimidating" -> { stats.str += 1; stats.cha -= 1 }
                }
            }

            "warrior_combat_style" -> {
                when (answer.id) {
                    "close" -> { stats.str += 1; stats.dex -= 1 }
                    "ranged" -> { stats.dex += 1; stats.str -= 1 }
                }
            }

            "warrior_background" -> {
                when (answer.id) {
                    "scholar" -> { stats.intel += 1; stats.wis += 1; stats.str -= 1 }
                    "brute" -> { stats.str += 1 }
                }
            }

            // ===== WIZARD =====
            "wizard_study_style" -> {
                when (answer.id) {
                    "tomes" -> { stats.intel += 1; stats.wis -= 1 }
                    "observing" -> { stats.wis += 1; stats.intel -= 1 }
                }
            }

            "wizard_presence" -> {
                when (answer.id) {
                    "dazzle" -> { stats.cha += 1; stats.intel -= 1 }
                    "efficient" -> { stats.intel += 1; stats.cha -= 1 }
                }
            }

            "wizard_control" -> {
                when (answer.id) {
                    "crowd" -> { stats.wis += 1; stats.intel -= 1 }
                    "single" -> { stats.intel += 1; stats.wis -= 1 }
                }
            }

            "wizard_approach" -> {
                when (answer.id) {
                    "nimble" -> { stats.dex += 1; stats.intel -= 1 }
                    "insightful" -> { stats.intel += 1; stats.wis -= 1 }
                }
            }

            "wizard_religion" -> {
                when (answer.id) {
                    "scholar" -> { stats.intel += 1; stats.str -= 1 }
                    "runner" -> { stats.str += 1; stats.intel -= 1 }
                }
            }

            "wizard_weapon" -> {
                when (answer.id) {
                    "dagger" -> { stats.str += 1; stats.intel -= 1 }
                    "staff" -> { stats.intel += 1; stats.str -= 1 }
                }
            }

            // ===== ROGUE =====
            "rogue_approach" -> {
                when (answer.id) {
                    "shadows" -> { stats.dex += 1; stats.cha -= 1 }
                    "talk" -> { stats.cha += 1; stats.dex -= 1 }
                }
            }

            "rogue_skill_focus" -> {
                when (answer.id) {
                    "locks" -> { stats.dex += 1; stats.intel -= 1 }
                    "intel" -> { stats.intel += 1; stats.dex -= 1 }
                }
            }

            "rogue_defense" -> {
                when (answer.id) {
                    "agility" -> { stats.dex += 1; stats.con -= 1 }
                    "toughness" -> { stats.con += 1; stats.dex -= 1 }
                }
            }

            "rogue_social" -> {
                when (answer.id) {
                    "charm" -> { stats.cha += 1; stats.wis -= 1 }
                    "observe" -> { stats.wis += 1; stats.cha -= 1 }
                }
            }

            "rogue_escape" -> {
                when (answer.id) {
                    "sword" -> { stats.str += 1; stats.dex -= 1 }
                    "flee" -> { stats.dex += 1; stats.str -= 1 }
                }
            }

            "rogue_archetype" -> {
                when (answer.id) {
                    "arcane" -> { stats.intel += 1; stats.str -= 1 }
                    "brawler" -> { stats.str += 1; stats.intel -= 1 }
                }
            }
        }

        // Enforce the 72-point max total
        clampToMax(stats)

        _stats.value = stats.copy()
    }

}
