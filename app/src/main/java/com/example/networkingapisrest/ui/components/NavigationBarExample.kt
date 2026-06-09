import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import com.example.networkingapisrest.routes.Home
import com.example.networkingapisrest.routes.Locales

@Composable
fun NavigationBarExample(
    onUserScreen:()-> Unit,
    onLocalesScreen:()-> Unit,
    currentRoute: String
) {

            NavigationBar {

                NavigationBarItem(
                    selected = currentRoute == Home.toString(),
                    onClick = {
                            onUserScreen()
                    },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = currentRoute == Locales.toString(),
                    onClick = {
                        onLocalesScreen()
                    },
                    icon = { Icon(Icons.Default.LocalLibrary, null) },
                    label = { Text("Locales") }
                )
            }
        }



