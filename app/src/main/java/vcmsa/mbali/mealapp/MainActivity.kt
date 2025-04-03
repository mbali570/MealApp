package vcmsa.mbali.mealapp

import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // code start here
        //step 1 : access the elements
        // create all the value for elements

        val mealtime = findViewById<EditText>(R.id.mealtimetext)
        val buttongen = findViewById<Button>(R.id.buttongen)
        val buttonreset = findViewById<Button>(R.id.buttonreset)
        val advisedmeal = findViewById<EditText>(R.id.mealadvise)

        buttongen.setOnClickListener {
            val mealtimetext = advisedmeal.text.toString()
            val meal = (mealtime)
            advisedmeal.setText("advise : meal")

            //Determine meal advised based on time of day
            val mealTimetext = mealtime.text.toString()
            val mealadvised = when (mealTimetext) {
                "Morning" -> "Eggs,cheese,burger"
                "Mid-Morning" -> "Fruits"
                "Afternoon" -> "Sandwich,Snacks"
                "Mid-afternoon" -> "Cake"
                "Dinner" -> "Pasta and mince"
                "After Dinner" -> "Ice cream"
                else -> "Invalid time"
            }
            advisedmeal.setText(mealadvised)
        }

        buttonreset.setOnClickListener {
            mealtime.text.clear()
            advisedmeal.text.clear()
        }
    }

    fun CurrentTime(): String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        return when (hour) {
            in  6..10 -> "Morning"
            in 10..12 -> "Mid-Morning"
            in 12.. 15 -> "Afternoon"
            in 15..19 -> "Mid-afternoon"
            in 19..21 -> "Dinner"
            else -> "After Dinner"
        }
    }
}