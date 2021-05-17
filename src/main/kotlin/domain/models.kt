package domain

import Scene
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import mapRange

sealed class SceneEntity {
    abstract fun update(scene: Scene)
}

fun randomX(canvasWidth: Int) = (0..canvasWidth).random().toFloat()
fun randomY() = (-500..-50).random().toFloat()
fun randomZ() = (1..20).random().toFloat()
fun randomDropLength(z: Float) = z.mapRange(0f to 20f, 10f to 20f)
fun randomFallSpeed(z: Float) = z.mapRange(0f to 20f, 4f to 10f)
fun randomDropThickness(z: Float) = z.mapRange(0f to 20f, 1f to 5f)
fun randomGravityOnDrop(z: Float) = z.mapRange(0f to 20f, 0f to 0.2f)

data class Drop(
    var canvasWidth: Int = Window.WIDTH,
    var canvasHeight: Int = Window.HEIGHT,
    var x: Float = randomX(canvasWidth),
    var y: Float = randomY(),
    var z: Float = randomZ(),
    var length: Float = randomDropLength(z),
    var fallSpeed: Float = randomFallSpeed(z),
    var thickness: Float = randomDropThickness(z),
) : SceneEntity() {

    private var gravityEffect = randomGravityOnDrop(z)

    override fun update(scene: Scene) {
        y += fallSpeed
        fallSpeed += gravityEffect
        if (y > canvasHeight) {
            reset(this)
        }
    }

    private fun reset(drop: Drop) = with(drop) {
        x = randomX(canvasWidth)
        y = randomY()
        z = randomZ()
        length = randomDropLength(z)
        fallSpeed = randomFallSpeed(z)
        thickness = randomDropThickness(z)
        gravityEffect = randomGravityOnDrop(z)
    }

}

fun DrawScope.drawDrop(drop: Drop) {
    val canvasWidth = size.width
    val canvasHeight = size.height
    drop.canvasWidth = canvasWidth.toInt()
    drop.canvasHeight = canvasHeight.toInt()
    drawLine(
        color = Color.Black,
        start = Offset(drop.x, drop.y),
        end = Offset(drop.x, drop.y + drop.length),
        strokeWidth = drop.thickness,
    )

}
