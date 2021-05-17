package domain

import Scene

sealed class SceneEntity {
    abstract fun update(scene: Scene)
}

data class Drop(
    private var x: Float,
    private var y: Float,
) : SceneEntity() {

    companion object {
        var calls = 0
    }

    override fun update(scene: Scene) {

    }
}

