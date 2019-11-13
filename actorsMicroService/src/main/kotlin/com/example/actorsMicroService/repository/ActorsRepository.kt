package com.example.actorsMicroService.repository

import com.example.actorsMicroService.model.ActorModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ActorsRepository : JpaRepository<ActorModel, Long>{

}
