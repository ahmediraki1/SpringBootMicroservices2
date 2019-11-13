package com.example.showsMicroService.repository

import com.example.showsMicroService.model.ShowModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShowsRepository : JpaRepository<ShowModel, Long>
