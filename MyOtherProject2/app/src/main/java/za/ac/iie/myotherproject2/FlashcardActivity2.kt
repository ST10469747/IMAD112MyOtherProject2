package za.ac.iie.myotherproject2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Toast
import android.widget.TextView
import android.widget.Button

class FlashcardActivity2 : AppCompatActivity() {

    private val questions = arrayOf(
        "There are 36 hours in one day",
        "The largest land animal is found in Africa",
        "Eating too many lettuce will can make you turn green",
        "The river nile is the longest river in the world",
        "The blue whale is the biggest animal to ever live"
    )

    private val answers = arrayOf(false, true, false, true, true)
    private var score = 0
    private var currentIndex = 0
    private val userAnswers = mutableListOf<Boolean>()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flashcard2)

        val questionTxt = findViewById<TextView>(R.id.questionTxt)
        val trueBtn = findViewById<Button>(R.id.trueBtn)
        val falseBtn = findViewById<Button>(R.id.falseBtn)



        fun loadQuestion() {
            if (currentIndex < questions.size) {
                questionTxt.text = questions[currentIndex]
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("questions", questions)
                intent.putExtra("answers", answers)
                intent.putExtra("userAnswers", userAnswers.toBooleanArray())
                startActivity(intent)
                finish()

            }
        }

        trueBtn.setOnClickListener {
            checkAnswer(true)
            loadQuestion()
        }
        falseBtn.setOnClickListener {
            checkAnswer(false)
            loadQuestion()
        }
        loadQuestion()
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correct = answers[currentIndex]
        userAnswers.add(userAnswer)

        if (userAnswer == correct) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
            score++
        } else {
            Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show()
        }
        currentIndex++
        if (currentIndex < questions.size) {
            val btnMove = findViewById<TextView>(R.id.questionTxt)
            btnMove.setOnClickListener {
                btnMove.text = questions[currentIndex]

                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", 0)
                intent.getStringArrayExtra("questions") ?: arrayOf()
                startActivity(intent)
                finish()

            }
        }

        
        {
        }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}