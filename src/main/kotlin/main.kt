import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asDesktopBitmap
import androidx.compose.ui.res.imageResource
import domain.Drop
import domain.SceneEntity
import domain.StepFrame
import domain.drawDrop

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
        repeat(800 * 5) { drops.add(Drop()) }
        sceneEntity.addAll(drops)
    }


    fun update() {
        for (entity in sceneEntity) {
            entity.update(this)
        }
    }

    @Composable
    fun render(frameState: State<Long>) {
        val batmap = imageResource("images/batman.jpg").asDesktopBitmap()
        Box {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black),
            ) {

                drawBitmap(batmap)

                val stepFrame = frameState.value
                for (drop in drops) {
                    drawDrop(drop)
                }
            }
        }
    }
}


