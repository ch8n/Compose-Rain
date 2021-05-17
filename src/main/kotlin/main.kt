import androidx.compose.desktop.Window
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import domain.SceneEntity

fun main() = Preview {
    var text by remember { mutableStateOf("Hello, World!") }

    Text(text)
}


class Scene {

    var sceneEntity = mutableStateListOf<SceneEntity>()


    fun setupScene() {
        sceneEntity.clear()

    }

    fun update() {
        for (entity in sceneEntity) {
            entity.update(this)
        }
    }

    @Composable
    fun render(frameState: State<Long>) {

    }
}


