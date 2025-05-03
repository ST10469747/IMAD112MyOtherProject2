package za.ac.iie.myotherproject2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra("score", 0)
        val questions = intent.getStringArrayExtra("questions") ?: arrayOf()
        val scoreTxt = findViewById<TextView>(R.id.scoreTxt)
        val commentTxt = findViewById<TextView>(R.id.commentTxt)

        scoreTxt.text = "Your score: $score / ${questions.size}"
        commentTxt.text = if (score>=3) "Fantastic work!" else "Do better!"


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}