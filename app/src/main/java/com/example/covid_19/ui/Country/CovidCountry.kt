package com.example.covid_19.ui.Country

class CovidCountry(var mCovidCountry: String, var mCases: String) {
    var mDeaths: String
    var mTodayDeaths: String
    var mRecoverd: String
    fun getmCovidCountry(): String {
        return mCovidCountry
    }

    fun getmCases(): String {
        return mCases
    }

    init {
        mDeaths = mDeaths
        mTodayDeaths = mTodayDeaths
        mRecoverd = mRecoverd
    }
}
