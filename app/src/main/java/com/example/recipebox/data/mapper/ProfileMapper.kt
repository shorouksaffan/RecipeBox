package com.example.recipebox.data.mapper

import com.example.recipebox.data.local.entities.ProfileEntity
import com.example.recipebox.domain.model.Profile

fun ProfileEntity.toDomain() = Profile(
    onboardingCompleted = onboardingCompleted
)

fun Profile.toEntity() = ProfileEntity(
    onboardingCompleted = onboardingCompleted
)