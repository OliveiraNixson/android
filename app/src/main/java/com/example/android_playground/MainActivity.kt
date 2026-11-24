package com.example.android_playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_playground.ui.theme.AndroidplaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculdora(isVip = true, prices = listOf(1.0,2.0))
        }
    }
}

@Composable
fun Calculdora(isVip: Boolean, prices: List<Double>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ){
        val subtotal = calculateSubtotal(prices)
        val discount = calculateDiscount(subtotal = subtotal, isVip = isVip)
        val shipping = calculateShipping(subtotal)
        val finalPrice = calculateFinalPrice(subtotal = subtotal, shipping = shipping, discount = discount)
        Text(text = "Cliente Vip: $isVip")
        Text(text = "subtotal $subtotal")
        Text(text = "Frete: -$shipping")
        Text(text = "Desconto: $discount")
        Text(text = "Total: $finalPrice")
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    AndroidplaygroundTheme {
        Calculdora(true, listOf(1.0,2.0,3.0))
    }
}

fun calculateSubtotal(
    prices: List<Double>
): Double {
    var total = 0.0
    for(price in prices){
        total += price
    }
    return total
}

fun calculateShipping(
    subtotal: Double
): Double {
    if(subtotal > 500){
        return 0.0
    }else{
        return 50.0
    }
}

fun calculateDiscount(
    subtotal: Double,
    isVip: Boolean
): Double {
    if(isVip){
        return subtotal*0.15
    }else if( subtotal > 1000.0){
        return subtotal*0.1
    }else{
        return 0.0
    }
}

fun calculateFinalPrice(
    subtotal: Double,
    shipping: Double,
    discount: Double
): Double{
    return subtotal + shipping - discount
}



