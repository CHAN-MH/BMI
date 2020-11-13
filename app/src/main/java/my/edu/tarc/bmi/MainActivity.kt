package my.edu.tarc.bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

// variable : Type
// val = value, var = variable
// Eg. val pi : Double = 3.1427 OR val pi = 3.14159
// Eg. var number : Float

//MainActivity is an instance of AppCompatActivity
class MainActivity : AppCompatActivity() {
    //Global variable
    //val = value, var = variable
    //lateinit = late initialize -> declare the variable but i dun initialize it first
    //private = access modifier
    private lateinit var editTextWeight : EditText
    private lateinit var editTextHeight : EditText
    private lateinit var textViewBMI : TextView
    private lateinit var imageViewBMI : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Display the User Interface
        // R = resource class (res/ folder)
        setContentView(R.layout.activity_main)
        //Link UI to the program code
        editTextWeight = findViewById(R.id.editTextWeight)
        editTextHeight = findViewById(R.id.editTextHeight)
        textViewBMI = findViewById(R.id.textViewBMI)
        imageViewBMI = findViewById(R.id.imageViewBMI)
        val imageViewMoreInfo: ImageView = findViewById(R.id.imageViewMoreInfo)

        imageViewMoreInfo.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

        val buttonCalculate : Button = findViewById(R.id.buttonCalculate)
        buttonCalculate.setOnClickListener {
            //To retrieve value from a View
            val weight : Double = editTextWeight.text.toString().toDouble()
            val height : Double = editTextHeight.text.toString().toDouble()

            //BMI = kg/m pow 2
            var bmi = weight/(height/100).pow(2)
            //val bmi = weight / Math.pow(height/100, 2.0)


            //TODO : Determine the status of BMI and present the result to the user
            //Underweight = <18.5
            //Normal weight = 18.5 - 24.9
            //Overweight >= 25

            if(bmi <= 18.5)
            {
                imageViewBMI.setImageResource(R.drawable.under)
                textViewBMI.text = String.format("%1.2f, %s", bmi, getString(R.string.underweight))
            }
            else if (bmi > 18.5 && bmi < 24.9)
            {
                imageViewBMI.setImageResource(R.drawable.normal)
                textViewBMI.text = String.format("%1.2f, %s", bmi, getString(R.string.normal))
            }
            else if (bmi >= 25)
            {
                imageViewBMI.setImageResource(R.drawable.over)
                textViewBMI.text = String.format("%1.2f, %s", bmi, getString(R.string.overweight))
            }
            

        }//end of onCLickListener

        val buttonReset : Button = findViewById(R.id.buttonReset)
        buttonReset.setOnClickListener {
            textViewBMI.text = "BMI"
            editTextWeight.text = null
            editTextHeight.text = null
            //or we can put editTextHeight.text = clear()
            imageViewBMI.setImageResource(R.drawable.empty)
        }




    }//end of function
}//end of class