package com.example.financialtips

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.Diamond
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financialtips.ui.theme.FinancialTipsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinancialTipsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FinancialApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancialApp() {
    Scaffold(
        topBar = {
            FinancialAppBar()
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .clip(MaterialTheme.shapes.large).background(MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                TipItem()
                TipItem()
                TipItem()
                TipItem()
            }
        })
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancialAppBar() {
//    Row() {
//        Icon(imageVector = Icons.Outlined.Diamond, contentDescription = null)
//        Text(text = "Minha App Bar")
//    }
    CenterAlignedTopAppBar(
        title = { Text(text = "Minha App Bar") },
    )
}

@Composable
fun TipItem(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(true) }
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = modifier.padding(8.dp)
    ) {
        Column(
            modifier = modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TipImage(R.drawable.credit_card)

                Text(
                    text = "Use the credit card with wisdom",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = modifier.weight(1f)

                )
                PlusButton(expanded = expanded, onClick = { expanded = !expanded })

            }
            if (expanded) {
                Text(
                    text = """The limit in your credit car is not your money is money that the bank lend to you. Sometimes it is crever to use that money and gain some benefits in your bank, but if you don`t have money to paid the bill you should not use the credit card""",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = modifier.padding(8.dp)
                )
            }
        }
    }

}

@Composable
fun TipImage(@DrawableRes image: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "A credit card",
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(100)),
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun PlusButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = stringResource(id = R.string.expand_button_content_description)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun financialCard() {
    FinancialTipsTheme {
        TipItem()
    }
}

@Preview(showBackground = false)
@Composable
fun financialCardDarkModew() {
    FinancialTipsTheme(useDarkTheme = true) {
        TipItem()
    }
}