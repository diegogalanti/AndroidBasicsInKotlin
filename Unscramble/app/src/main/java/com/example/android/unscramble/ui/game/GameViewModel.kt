package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var _score = 0
    public val score get() = _score

    private var _currentWordCount = 0
    public val currentWordCount get() = _currentWordCount

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    fun getNextScrambledWord(): String {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        return if (wordsList.contains(currentWord)) {
            getNextScrambledWord()
        } else {
            ++_currentWordCount
            wordsList.add(currentWord)
            String(tempWord)
        }
    }

        fun hasMoreWords(): Boolean {
            return currentWordCount < MAX_NO_OF_WORDS
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    /*
    * Re-initializes the game data to restart the game.
    */
    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordsList.clear()
    }
}