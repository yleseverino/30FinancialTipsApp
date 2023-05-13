package com.example.financialtips

import android.os.Bundle
import android.os.ParcelFileDescriptor.OnCloseListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.CurrencyBitcoin
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Diamond
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.compose.FinancialTipsTheme
import com.example.financialtips.model.FinacialRepository.financialTips
import com.example.financialtips.model.FinancialTip

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
    var showAbout by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            FinancialAppBar(onClick = { showAbout = !showAbout })
        },
        content = { padding ->
            Box(modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)) {
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .clip(MaterialTheme.shapes.large)
                        .background(MaterialTheme.colorScheme.background)
                        .shadow(1.dp, shape = MaterialTheme.shapes.large)
                        .zIndex(1f)
                ) {
                    items(financialTips) { financialTip ->
                        Box(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)) {
                            FinancialTipItem(financialTip = financialTip)
                        }
                    }
                }
            }
        })
    if (showAbout) {
        AboutDialog {
            run { showAbout = !showAbout }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancialAppBar(
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.CurrencyExchange,
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .padding(end = 4.dp)
        )
        Text(
            text = "FINANCE TIPS",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = onClick,
            content = {
                Icon(imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp))
            }

        )
    }
}

@Composable
fun FinancialTipItem(modifier: Modifier = Modifier, financialTip: FinancialTip) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.medium,
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
                TipImage(financialTip.image)

                Text(
                    text = stringResource(id = financialTip.title),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = modifier.weight(1f)

                )
                PlusButton(expanded = expanded, onClick = { expanded = !expanded })

            }
            if (expanded) {
                Text(
                    text = stringResource(id = financialTip.description),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = modifier.padding(8.dp)
                )
                Text(
                    text = stringResource(id = financialTip.imageRef),
                    style = MaterialTheme.typography.bodySmall,
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

@Composable
private fun AboutDialog(
    onClick: () -> Unit
) {
    AlertDialog(
        title = { Text(text = "Created with ♡ by Yle S. Carvalho")},
        onDismissRequest = onClick,
        confirmButton = {
            TextButton(onClick = onClick) {
                Text(text = "Ok")
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun financialCard() {
    FinancialTipsTheme {
        FinancialTipItem(financialTip = financialTips[0])
    }
}

@Preview(showBackground = false)
@Composable
fun financialCardDarkMode() {
    FinancialTipsTheme(useDarkTheme = true) {
        FinancialTipItem(financialTip = financialTips[0])
    }
}

@Preview(showBackground = false)
@Composable
fun financialCardAppBar() {
    FinancialTipsTheme(useDarkTheme = true) {
        FinancialAppBar(onClick = {})
    }
}