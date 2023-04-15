package com.doodlejump

import android.content.Context
import android.graphics.*
import android.util.Log

class Player(pos0: Vector): GameObject(Vector(311F, 272F), pos0, R.drawable.player) {


    var acceleration = Vector(0F,-10F)
    var speed = Vector(0F, 0F)
    override fun update() {
        speed += acceleration * GameManager.TIME_CONSTANT
        move(pos + speed * GameManager.TIME_CONSTANT)
        if(pos.y < 0) {
            speed.y = 125F
        }
    }

    /* Interface pour les objets when hit sachant que le joueur n'en a pas besoin
    car la méthode permet de gérer les collisions avec le joueur (et peut être les monstres)
    On pourrait donc peut être faire une interface living entity qui possède la méthode en question ?
     */
    override fun whenHit() {

    }
}