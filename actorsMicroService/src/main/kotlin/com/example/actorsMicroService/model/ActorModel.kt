package com.example.actorsMicroService.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
class ActorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var actorId: Long? = null

    var actorName: String? = null
    var actorEmail: String? = null
    var actorPhone: String? = null
}
