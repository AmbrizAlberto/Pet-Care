package com.example.spike.data.network.model.responses

import com.example.spike.data.network.model.Veterinary

data class GetVetsResponse(
    val veterinaries: List<Veterinary>
)