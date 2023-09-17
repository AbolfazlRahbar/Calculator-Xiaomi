package rahbar.abolfazl.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder
import rahbar.abolfazl.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // add viewBinding to project ->
    lateinit var binding: ActivityMainBinding
    private var operator: Char = '+'
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumberClicked()

        onOperatorClicked()
    }

    private fun onOperatorClicked() {

        /*
        When buttons with math operators are clicked, the appendText function is called, which gets
        A number in its input which is a string and that function sets the received number
        to txtExpression to display on the operation page. to be given.
         */

        binding.btnPlus.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()) {

                val myChar = binding.txtExpression.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("+")
                }
            }
        }

        binding.btnMinus.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()) {

                val myChar = binding.txtExpression.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("-")
                }
            }
        }

        binding.btnMultipliedBy.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()) {

                val myChar = binding.txtExpression.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("*")
                }
            }
        }

        binding.btnDivision.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()) {

                val myChar = binding.txtExpression.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("/")
                }
            }
        }

        binding.btnClosedParenthesis.setOnClickListener {
            appendText(")")
        }

        binding.btnOpenParenthesis.setOnClickListener {
            appendText("(")
        }

        /*
         * When the * button is clicked, all the performed calculations will be deleted, both in the
         * calculation details section and in the calculation answer section, so we put their values
         * as an empty string.
         **/
        binding.btnAC.setOnClickListener {
            binding.txtExpression.text = ""
            binding.txtAnswer.text = ""
        }

        /*
        By pressing this button, the text written by the user in (txtExpression) or the math
        operation detail page is received and poured into the (textOld) variable. In the next step,
        we need to check whether the text we received with the (textOld) variable was written by
        the user or not. Based on that, we can perform the deletion operation.
        In the next step, after checking the same previous text that was written in the (txtExpression)
        or math operation detail page, we delete one of its characters with the same old text
        or (textOld) and through the * function, which works like this. I will explain the
        (subString) function for better understanding.

        Now I will explain the function of the (subString) function to make it easier for
        you to understand the codes.
        The use of this function is when you want to remove a character from a string variable.
        In its input, it receives the first and second index values, which I give the text that I
        want to inject into this string from the beginning to the end.
        For example, if I have a string as Amir, it contains 3 indexes, so the strings are an
        array of characters starting from index 0.
        So, in this code, we have the text entered by the user in full, and we only
        remove one character from the end.
        If you don't understand, you can get more information by reading the document on Google.

         */
        binding.btnEraser.setOnClickListener {

            val textOld = binding.txtExpression.text.toString()

            if (textOld.isNotEmpty()) {
                binding.txtExpression.text = textOld.substring(0, textOld.length - 1)
            }
        }

        binding.btnEqual.setOnClickListener {

            try {

                val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()
                val result = expression.evaluate()

                val longResult = result.toLong()

                // 135.0 == 135
                if (result == longResult.toDouble()) {
                    binding.txtAnswer.text = longResult.toString()
                } else {
                    binding.txtAnswer.text = result.toString()
                }

            } catch (e: Exception) {
                binding.txtExpression.text = ""
                binding.txtAnswer.text = ""
                Toast.makeText(
                    this@MainActivity,
                    "Error! Please enter the numbers correctly.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun onNumberClicked() {

        /*
        When buttons with numbers on them are clicked, the appendText function is called, which receives
        a number in its input, which is a string, and that function sets the received number
        to txtExpression to display on the operation screen. to be given.
         */

        binding.btnZero.setOnClickListener {

            /*
            In order for zero to be placed at the beginning of the sentence, we check that if
            something was written in (txtExpression) or the math operation details screen, zero can be written,
            otherwise zero cannot be used at the beginning of the sentence.
             */

            if (binding.txtExpression.text.isNotEmpty()) {
                appendText("0")
            }
        }

        binding.btnOne.setOnClickListener {
            appendText("1")
        }

        binding.btnTwo.setOnClickListener {
            appendText("2")
        }

        binding.btnThree.setOnClickListener {
            appendText("3")
        }

        binding.btnFour.setOnClickListener {
            appendText("4")
        }

        binding.btnFive.setOnClickListener {
            appendText("5")
        }

        binding.btnSix.setOnClickListener {
            appendText("6")
        }

        binding.btnSeven.setOnClickListener {
            appendText("7")
        }

        binding.btnEight.setOnClickListener {
            appendText("8")
        }

        binding.btnNine.setOnClickListener {
            appendText("9")
        }

        binding.btnDot.setOnClickListener {

            /*
            When the dot button is clicked, we first check a condition that if no value was entered
            in (txtExpression), a zero is clicked on the dot button and the dot is displayed to the
            user through (txtExpression).
            And to avoid repeating multiple points, we check a condition using (contains)
            (which we will check the feature of this function completely below), which otherwise
            if there is no point (using a We have copied the exclamation mark of the sentence)
            to create a point.

            In this section, we will examine some features of the (contains) function.
            In short, this function is used when we want to know if there is something
            (number, string, etc.) in this string variable or not.

             */

            if (binding.txtExpression.text.isEmpty() || binding.txtAnswer.text.isNotEmpty()) {
                appendText("0.")
            } else if (!binding.txtExpression.text.contains(".")) {
                appendText(".")
            }
        }
    }

    private fun appendText(newText: String) {

        /*
        This function is called when one has received an input in functions
        onNumberClicked and onOperator and adds these inputs to txtExpression to be displayed
        on the math operations page.
         */
        binding.txtExpression.append(newText)

        /*
        This code is executed when if a value was written in (txtExpression), we set it to zero so that we can
        write and set a new value by clicking on a button.
         */
        if (binding.txtAnswer.text.isNotEmpty()) {
            binding.txtExpression.text = ""
        }
        binding.txtAnswer.text = ""

        // This code is executed when the scrollview is set to start scrolling automatically after entering any number ->
        val vtoExpression: ViewTreeObserver =
            binding.horizontalScrollViewTxtExpression.viewTreeObserver
        vtoExpression.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewTxtExpression.scrollTo(binding.txtExpression.width, 0)
            }
        })

        val vtoAnswer: ViewTreeObserver = binding.horizontalScrollViewTxtAnswer.viewTreeObserver
        vtoAnswer.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewTxtAnswer.viewTreeObserver
                binding.horizontalScrollViewTxtAnswer.scrollTo(
                    binding.horizontalScrollViewTxtAnswer.width,
                    0
                )
            }
        })
    }
}