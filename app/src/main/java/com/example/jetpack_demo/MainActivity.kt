package com.example.jetpack_demo

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_demo.ui.theme.JetpackdemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            JetpackdemoTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
//                }
//            }
//    }

//        setContent {
//                    MessageCard(msg = Message("Android","Jetpack Compose"))
//        }

        // 使用在您的项目中创建的 Material 主题 JetpackdemoTheme 和 Surface 来封装 MessageCard 函数。
        // 在 @Preview 和 setContent 函数中都需要执行此操作。
        // 这样一来，可组合项即可沿用应用主题中定义的样式，从而在整个应用中确保一致性。
        setContent {
            JetpackdemoTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                   // MessageCard(msg = Message("Android","Jetpack Compose"))
                    Conversation(SampleData.conversationSample)
                }
            }
        }
    }
}

data class  Message(val author: String,val body:String)

//@Composable
//fun MessageCard_old1(msg: Message) {
////    Text(text = msg.author)
////    Text(text = msg.body)
//    Row {
//        Image(
//            painter = painterResource(R.drawable.profile_picture),
//            contentDescription = "Contact profile picture",
//        )
//            Column {
//                Text(text = message.author)
//                Text(text = message.body)
//            }
//    }
//    Row {
//        Column {
//            Text(text = message.author)
//            Text(text = message.body)
//        }
//    }
//}

@Composable
fun MessageCard(msg: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                // Add a border to the image
                .border( 1.5.dp, MaterialTheme.colorScheme.error, CircleShape )
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(10.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }

        // We toggle the isExpanded variable when we click on this Column
        Column (modifier = Modifier.clickable { isExpanded = !isExpanded }){
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = msg.body,
//                style = MaterialTheme.typography.bodyMedium
//                )
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 10.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = MaterialTheme.colorScheme.surface,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)

            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                   )
               }
           }
        }

    }
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackdemoTheme {
        Greeting("Android")
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewMessageCard_old1() {
//    JetpackdemoTheme {
//        MessageCard(Message("Android","Jetpack Compose"))
//    }
//}
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { it ->
            MessageCard(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConversation() {
    JetpackdemoTheme {

        Conversation(SampleData.conversationSample)
    }
}
//
////@Preview(showBackground = true)
//@Preview(name = "Light Mode")
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
//@Composable
//fun PreviewMessageCard() {
//    JetpackdemoTheme {
//        Surface {
//            MessageCard(Message("Lexi", "Take a look at Jetpack Compose, it's great!")
//            )
//        }
//    }
//}