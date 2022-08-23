package com.tcoding.socialapp.model

import com.google.firebase.Timestamp
import java.io.Serializable

data class Share(val id: String, val email: String, val sharing: String): Serializable {
    val comment : Comment? = null
}
