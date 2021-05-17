import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.IntSize
import org.jetbrains.skija.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO


/**
 * To support instant preview (replacement for android's @Preview annotation)
 */
fun Preview(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Window(
        title = "Compose-Droplets-Debug",
        size = IntSize(domain.Window.WIDTH, domain.Window.HEIGHT),
        resizable = false,
        centered = true,
    ) {
        MaterialTheme() {
            Surface(modifier = modifier.fillMaxSize()) {
                content()
            }
        }
    }
}

fun Float.mapRange(fromRange: Pair<Float, Float>, toRange: Pair<Float, Float>): Float {
    val (minRange, maxRange) = fromRange
    val (minMappedRange, maxMappedRange) = toRange
    val rangePercentage = (this / maxRange) * 100
    val mappedValue = (rangePercentage / 100) * (minMappedRange + maxMappedRange)
    return mappedValue
}

fun getBatmanImage(): BufferedImage {
    var image: BufferedImage? = null
    try {
        image = ImageIO.read(File("images/batman.jpeg"))
    } catch (e: Exception) {
        // image file does not exist
    }
    if (image == null) {
        image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }
    return image
}

fun asImageAsset(image: BufferedImage): ImageBitmap {
    val baos = ByteArrayOutputStream()
    ImageIO.write(image, "jpeg", baos)

    return Image.makeFromEncoded(baos.toByteArray()).asImageBitmap()
}