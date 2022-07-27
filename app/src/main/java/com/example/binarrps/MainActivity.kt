package com.example.binarrps

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.binarrps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var playerChoice = 0
    private var computerChoice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            ivBatuP1.setOnClickListener {
                Log.d("MainActivity", "onClick: batu ")
                pickChoice(it, 0)
            }

            ivKertasP1.setOnClickListener {
                Log.d("MainActivity", "onClick: kertas ")
                pickChoice(it, 2)
            }

            ivGuntingP1.setOnClickListener {
                Log.d("MainActivity", "onClick: gunting ")
                pickChoice(it, 1)
            }

            cvRefresh.setOnClickListener {
                Log.d("MainActivity", "onClick: refresh ")
                refresh()

            }
        }
    }

    private fun refresh() {
        resetState()
        resetButton()
        resetBackground()

    }

    private fun resetButton() {
        binding.apply {
            ivBatuP1.isEnabled = true
            ivKertasP1.isEnabled = true
            ivGuntingP1.isEnabled = true
        }
    }

    private fun resetState() {
        playerChoice = 0
        computerChoice = 0
        binding.tvVersus.text = "Versus"
    }

    private fun pickChoice(view: View, choice: Int) {
        view.setBackgroundColor(Color.parseColor("#c2dbe9"))
        binding.cvRefresh.setBackgroundResource(R.color.green_flat)
        disabledPick()
        playerChoice = choice
        startGame()
    }

    private fun disabledPick() {
        binding.apply {
            ivBatuP1.isEnabled = false
            ivKertasP1.isEnabled = false
            ivGuntingP1.isEnabled = false
        }
    }

    private fun startGame() {
        computerChoice = (0..2).random()
        when (computerChoice) {
            0 -> binding.ivBatuCom.setBackgroundColor(Color.parseColor("#c2dbe9"))
            1 -> binding.ivGuntingCom.setBackgroundColor(Color.parseColor("#c2dbe9"))
            else -> binding.ivKertasCom.setBackgroundColor(Color.parseColor("#c2dbe9"))
        }

        calculateResult()
    }

    private fun calculateResult() {
        val result = when {
            playerChoice + 1 % 3 == computerChoice -> {
                "Pemain 1\n MENANG!"
            }
            playerChoice == computerChoice -> {
                "DRAW"
            }
            else -> {
                "Computer\n MENANG!"
            }
        }

        binding.tvVersus.text = result
    }

    private fun resetBackground() {
        binding.apply {
            ivBatuP1.setBackgroundColor(Color.parseColor("#ffffff"))
            ivKertasP1.setBackgroundColor(Color.parseColor("#ffffff"))
            ivGuntingP1.setBackgroundColor(Color.parseColor("#ffffff"))
            ivBatuCom.setBackgroundColor(Color.parseColor("#ffffff"))
            ivKertasCom.setBackgroundColor(Color.parseColor("#ffffff"))
            ivGuntingCom.setBackgroundColor(Color.parseColor("#ffffff"))
            cvRefresh.setBackgroundResource(R.color.red_flat)
        }
    }


}