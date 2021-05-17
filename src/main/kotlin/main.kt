import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import domain.*

fun main() {
    Preview {
        val scene = remember { Scene() }
        scene.setupScene()
        val frameState = StepFrame {
            scene.update()
        }
        scene.render(frameState)
    }
}


class Scene {

    private var sceneEntity = mutableStateListOf<SceneEntity>()
    private val drops = mutableListOf<Drop>()

    fun setupScene() {
        sceneEntity.clear()
        drops.clear()
        repeat(800 * 1) { drops.add(Drop()) }
        sceneEntity.addAll(drops)
    }


    fun update() {
        for (entity in sceneEntity) {
            entity.update(this)
        }
    }

    @Composable
    fun render(frameState: State<Long>) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor),
        ) {
            val stepFrame = frameState.value
            for (drop in drops) {
                drawDrop(drop)
            }
        }
    }
}


