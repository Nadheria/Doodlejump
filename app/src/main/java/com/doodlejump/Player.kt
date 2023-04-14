package com.doodlejump

import android.content.Context
import android.graphics.*

class Player(pos0: Vector): GameObject(50F, 100F, pos0) {

    override fun draw(canvas: Canvas, context: Context) {
        canvas.drawBitmap(BitmapFactory.decodeResource(context.resources,
            R.drawable.doodlejump), pos[0], pos[1], Paint())
    }

    override fun update() {

    }

    /* Interface pour les objets when hit sachant que le joueur n'en a pas besoin
    car la méthode permet de gérer les collisions avec le joueur (et peut être les monstres)
    On pourrait donc peut être faire une interface living entity qui possède la méthode en question ?
     */
    override fun whenHit() {

    }
}