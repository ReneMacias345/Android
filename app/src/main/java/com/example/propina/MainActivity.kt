package com.example.propina

import android.health.connect.datatypes.units.Percentage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.propina.components.Inputfield
import com.example.propina.components.RounedIconButton
import com.example.propina.ui.theme.PropinaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PropinaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PropinaApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
//@Preview
fun TopHeader(totalPerPerson: Double = 0.0){

    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        color= Color(0xFFE9D7F7)

    ) {

        Column(modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Total Per Person", style = MaterialTheme.typography.headlineSmall)

            Text(text="$$totalPerPerson",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold)
        }

    }
}

@Preview
@Composable
fun  PropinaApp(modifier: Modifier = Modifier) {

    Column(modifier = Modifier.padding(top = 50.dp)) {

        val splitNumber = remember { mutableIntStateOf(1) }

        val totalBillState = remember { mutableStateOf("") }

        val totalPerPersonState = remember { mutableDoubleStateOf(0.0) }

        val tipAmountState = remember { mutableDoubleStateOf(0.0) }

        val sliderPositionState = remember { mutableFloatStateOf(0f) }

        val tipPercentage = sliderPositionState.floatValue.toInt()

        val validState = remember(totalBillState.value)
        { totalBillState.value.trim().isNotEmpty() }


        TopHeader(totalPerPerson = totalPerPersonState.doubleValue)

        Surface(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            border = BorderStroke(width = 1.dp, color = Color.Red)
        ) {

            Column(
                modifier = Modifier.padding(6.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            )
            {

                Inputfield(valueState = totalBillState, labelId = "Enter Bill")

                if (validState) {

                    Row(
                        modifier = Modifier.padding(3.dp),
                        horizontalArrangement = Arrangement.Start
                    )
                    {
                        Text(
                            text = "Split",
                            modifier = Modifier.align(alignment = Alignment.CenterVertically)
                        )

                        Spacer(modifier = Modifier.width(120.dp))

                        RounedIconButton(imageVector = Icons.Default.Remove,
                            onClick = {
                                if (splitNumber.intValue > 1) {
                                    splitNumber.intValue
                                }
                            })

                        Text(
                            text = splitNumber.intValue.toString(),
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically)
                                .padding(start = 9.dp, end = 9.dp)
                        )

                        RounedIconButton(
                            imageVector = Icons.Default.Add,
                            onClick = { splitNumber.intValue++ })

                    }

                    Row(modifier = Modifier.padding(horizontal = 3.dp, vertical = 12.dp)) {

                        Text(text = "Tip")
                        Spacer(modifier = Modifier.width(200.dp))
                        Text(text = "${tipAmountState.doubleValue}")

                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "$tipPercentage %")
                        Spacer(modifier = Modifier.height(14.dp))

                        Slider(
                            value = sliderPositionState.floatValue,
                            onValueChange = { sliderPositionState.floatValue = it },
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            valueRange = 0f..25f,
                        )
                    }

                } else {
                    Box() { }

                }


            }

        }
    }
}

fun calculateTotalTip(totalBill: Double, tipPercentage: Int): Double{
    return if (totalBill>1 && totalBill.toString().isNotEmpty())
        (totalBill * tipPercentage) / 100 else 0.0
}

fun calculateTotalTipPerPerson(totalBill: Double, splitBy: Int, tipPercentage: Int): Double{
    val bill = calculateTotalTip(totalBill, tipPercentage) + totalBill
    return (bill / splitBy)
}
