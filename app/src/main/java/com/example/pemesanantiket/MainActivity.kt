package com.example.pemesanantiket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pemesanantiket.ui.theme.PemesananTiketTheme
import com.example.pemesanantiket.ui.theme.TicketBg
import com.example.pemesanantiket.ui.theme.TicketBlue
import com.example.pemesanantiket.ui.theme.TicketCardBg
import com.example.pemesanantiket.ui.theme.TicketGreen
import com.example.pemesanantiket.ui.theme.TicketRed
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PemesananTiketTheme {
                TicketBookingScreen()
            }
        }
    }
}

@Composable
fun TicketBookingScreen() {
    val hargaTiket = 25000
    var jumlahTiket by remember { mutableIntStateOf(1) }
    val totalBayar = hargaTiket * jumlahTiket

    val localeID = Locale("id", "ID")
    val formatter = NumberFormat.getCurrencyInstance(localeID)
    formatter.maximumFractionDigits = 0
    val formattedHarga = formatter.format(hargaTiket).replace("Rp", "Rp")
    val formattedTotal = formatter.format(totalBayar).replace("Rp", "Rp")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = TicketBg
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TicketBlue)
                    .padding(vertical = 48.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.ConfirmationNumber,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Pemesanan Tiket",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Pesan tiket dengan mudah!",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 16.sp
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                // Harga Tiket Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = TicketCardBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(text = "Harga Tiket", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 16.sp)
                        Text(
                            text = formattedHarga,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TicketBlue
                        )
                        Text(text = "per tiket", color = Color.Gray, fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Jumlah Tiket Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = TicketCardBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(text = "Jumlah Tiket", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = { if (jumlahTiket > 1) jumlahTiket-- },
                                modifier = Modifier
                                    .size(56.dp)
                                    .background(TicketBlue, CircleShape)
                            ) {
                                Icon(Icons.Default.Remove, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
                            }

                            Box(
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(56.dp)
                                    .background(Color(0xFFF1F3F5), RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = jumlahTiket.toString(),
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            IconButton(
                                onClick = { jumlahTiket++ },
                                modifier = Modifier
                                    .size(56.dp)
                                    .background(TicketBlue, CircleShape)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Total Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = TicketCardBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(text = "Total", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 16.sp)
                        Text(
                            text = formattedTotal,
                            fontSize = 36.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TicketGreen
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Reset Button
                Button(
                    onClick = { jumlahTiket = 1 },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = TicketRed),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Refresh, contentDescription = null)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "RESET", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicketBookingPreview() {
    PemesananTiketTheme {
        TicketBookingScreen()
    }
}
