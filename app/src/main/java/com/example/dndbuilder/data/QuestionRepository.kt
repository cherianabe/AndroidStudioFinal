package com.example.dndbuilder.data



object QuestionRepository {

    fun getBaseStats(clazz: CharacterClass): Stats {
        return when (clazz) {
            CharacterClass.WARRIOR -> Stats(
                str = 16,
                dex = 10,
                con = 14,
                intel = 8,
                wis = 10,
                cha = 10
            )
            CharacterClass.ROGUE -> Stats(
                str = 10,
                dex = 16,
                con = 12,
                intel = 12,
                wis = 10,
                cha = 10
            )
            CharacterClass.WIZARD -> Stats(
                str = 8,
                dex = 12,
                con = 10,
                intel = 16,
                wis = 14,
                cha = 10
            )
        }
    }

    fun getQuestionsFor(clazz: CharacterClass): List<Question> {
        return when (clazz) {
            CharacterClass.WARRIOR -> warriorQuestions()
            CharacterClass.ROGUE -> rogueQuestions()
            CharacterClass.WIZARD -> wizardQuestions()
        }
    }

    // WARRIOR QUESTIONS
    private fun warriorQuestions(): List<Question> = listOf(
        Question(
            id = "warrior_armor",
            text = "Does your warrior...",
            options = listOf(
                AnswerOption("heavy", "Wear the heaviest armor available? (+CON, -DEX)"),
                AnswerOption("light", "Go lighter so they can move faster? (+DEX, -CON)")
            )
        ),
        Question(
            id = "warrior_role",
            text = "In battle, would your warrior rather...",
            options = listOf(
                AnswerOption("tank", "Be the one who takes hits for the team? (+CON, -STR)"),
                AnswerOption("finisher", "Be the one who ends the fight quickly? (+STR, -CON)")
            )
        ),
        Question(
            id = "warrior_magic",
            text = "Does your warrior...",
            options = listOf(
                AnswerOption("cantrips", "Cast low level cantrips? (+INT, -STR)"),
                AnswerOption("rage", "Crush enemies with their rage? (+STR, -INT)")
            )
        ),
        Question(
            id = "warrior_personality",
            text = "How do they act in social situations?",
            options = listOf(
                AnswerOption("sweet", "Be a sweet talker? (+CHA, -STR)"),
                AnswerOption("intimidating", "Be intimidating? (+STR, -CHA)")
            )
        ),
        Question(
            id = "warrior_combat_style",
            text = "Do they prefer...",
            options = listOf(
                AnswerOption("close", "Close combat? (+STR, -DEX)"),
                AnswerOption("ranged", "Ranged weapons? (+DEX, -STR)")
            )
        ),
        Question(
            id = "warrior_background",
            text = "What best describes their background?",
            options = listOf(
                AnswerOption("scholar", "They know about the world. (+INT, +WIS, -STR)"),
                AnswerOption("brute", "They are a brute born for combat. (+STR)")
            )
        )
    )

    // WIZARD QUESTIONS
    private fun wizardQuestions(): List<Question> = listOf(
        Question(
            id = "wizard_study_style",
            text = "How do they spend most of their time?",
            options = listOf(
                AnswerOption("tomes", "Reading ancient tomes? (+INT, -WIS)"),
                AnswerOption("observing", "Observing the world around them? (+WIS, -INT)")
            )
        ),
        Question(
            id = "wizard_presence",
            text = "What describes their arcane presence?",
            options = listOf(
                AnswerOption("dazzle", "Dazzle others with magic and presence? (+CHA, -INT)"),
                AnswerOption("efficient", "Focus solely on efficient spellcasting? (+INT, -CHA)")
            )
        ),
        Question(
            id = "wizard_control",
            text = "In combat, does your wizard...",
            options = listOf(
                AnswerOption("crowd", "Excel at crowd control? (+WIS, -INT)"),
                AnswerOption("single", "Pick enemies off one by one? (+INT, -WIS)")
            )
        ),
        Question(
            id = "wizard_approach",
            text = "Physically and mentally, are they more...",
            options = listOf(
                AnswerOption("nimble", "Nimble? (+DEX, -INT)"),
                AnswerOption("insightful", "Insightful about the world? (+INT, -WIS)")
            )
        ),
        Question(
            id = "wizard_religion",
            text = "Regarding gods and cults...",
            options = listOf(
                AnswerOption("scholar", "Familiar with local religions? (+INT, -STR)"),
                AnswerOption("runner", "Good at running away from cultists? (+STR, -INT)")
            )
        ),
        Question(
            id = "wizard_weapon",
            text = "What do they carry?",
            options = listOf(
                AnswerOption("dagger", "A secret dagger? (+STR, -INT)"),
                AnswerOption("staff", "A favorite staff? (+INT, -STR)")
            )
        )
    )

    // ROGUE QUESTIONS
    private fun rogueQuestions(): List<Question> = listOf(
        Question(
            id = "rogue_approach",
            text = "How do they handle danger?",
            options = listOf(
                AnswerOption("shadows", "Strike from the shadows? (+DEX, -CHA)"),
                AnswerOption("talk", "Talk their way out of trouble? (+CHA, -DEX)")
            )
        ),
        Question(
            id = "rogue_skill_focus",
            text = "What do they focus on more?",
            options = listOf(
                AnswerOption("locks", "Lockpicking? (+DEX, -INT)"),
                AnswerOption("intel", "Gathering information? (+INT, -DEX)")
            )
        ),
        Question(
            id = "rogue_defense",
            text = "How do they avoid danger?",
            options = listOf(
                AnswerOption("agility", "Rely on agility? (+DEX, -CON)"),
                AnswerOption("toughness", "Rely on toughness? (+CON, -DEX)")
            )
        ),
        Question(
            id = "rogue_social",
            text = "Socially, do they...",
            options = listOf(
                AnswerOption("charm", "Charm people into helping them? (+CHA, -WIS)"),
                AnswerOption("observe", "Observe quietly to read intentions? (+WIS, -CHA)")
            )
        ),
        Question(
            id = "rogue_escape",
            text = "If things go bad, do they...",
            options = listOf(
                AnswerOption("sword", "Keep a sword just in case? (+STR, -DEX)"),
                AnswerOption("flee", "Escape if they get cornered? (+DEX, -STR)")
            )
        ),
        Question(
            id = "rogue_archetype",
            text = "What kind of rogue are they?",
            options = listOf(
                AnswerOption("arcane", "An arcane trickster? (+INT, -STR)"),
                AnswerOption("brawler", "Agile and nimble but can’t read well? (+STR, -INT)")
            )
        )
    )
}
