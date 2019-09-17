package com.monash.sample

import androidx.lifecycle.ViewModel
import com.monash.sample.pojo.UserData

class DashboardViewModel : ViewModel() {
    var userData: UserData = getData()
}
