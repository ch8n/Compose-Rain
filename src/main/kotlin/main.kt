import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asDesktopBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import domain.*
import org.jetbrains.skija.IRect

fun main() {

    Preview(modifier = Modifier.fillMaxSize()) {
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
        val bitmap = imageResource("images/batman.jpeg").asDesktopBitmap()

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black),
            ) {

                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawBitmapRect(
                        bitmap,
                        IRect(0, 0, Window.WIDTH, Window.HEIGHT).toRect()
                    )
                }

                val stepFrame = frameState.value
                for (drop in drops) {
                    drawDrop(drop)
                }
            }
        }
    }
}


