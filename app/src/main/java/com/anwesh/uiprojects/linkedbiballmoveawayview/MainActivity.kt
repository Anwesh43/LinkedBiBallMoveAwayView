package com.anwesh.uiprojects.linkedbiballmoveawayview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.biballmoveawayview.BiBallMoveAwayView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BiBallMoveAwayView.create(this)
    }
}
