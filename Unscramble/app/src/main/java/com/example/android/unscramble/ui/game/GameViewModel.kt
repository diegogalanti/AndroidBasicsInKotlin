package com.example.android.unscramble.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private val _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

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
            _currentWordCount.value = (_currentWordCount.value)?.inc()
            wordsList.add(currentWord)
            String(tempWord)
        }
    }

        fun hasMoreWords(): Boolean {
            return currentWordCount.value!! < MAX_NO_OF_WORDS
    }

    private fun increaseScore() {
        _score.value = _score.value?.plus(SCORE_INCREASE)
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
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
    }
}