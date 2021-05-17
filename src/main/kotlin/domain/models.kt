package domain

import Scene
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope

sealed class SceneEntity {
    abstract fun update(scene: Scene)
}

fun randomX() = (0..Window.WIDTH).random().toFloat()
fun randomY() = (-500..-50).random().toFloat()
fun randomZ() = (1..20).random().toFloat()
fun randomDropLength() = (10..20).random().toFloat()
fun randomFallSpeed() = (4..10).random().toFloat()


data class Drop(
    var x: Float = randomX(),
    var y: Float = randomY(),
    var z: Float = randomZ(),
    var length: Float = randomDropLength(),
    var fallSpeed: Float = randomFallSpeed(),
) : SceneEntity() {

    override fun update(scene: Scene) {
        y += fallSpeed
        fallSpeed += 0.5f // gravity acceleration
        if (y > Window.HEIGHT) {
            reset(this)
        }
    }

    private fun reset(drop: Drop) {
        drop.x = randomX()
        drop.y = randomY()
        drop.fallSpeed = randomFallSpeed()
        drop.length = randomDropLength()
    }

}

fun DrawScope.drawDrop(drop: Drop) {
    val canvasWidth = size.width
    val canvasHeight = size.height

    drawLine(
        color = dropColor,
        start = Offset(drop.x, drop.y),
        end = Offset(drop.x, drop.y + drop.length),
        strokeWidth = 1f,
    )
}
