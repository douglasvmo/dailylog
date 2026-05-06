package com.example.diariodeobra.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diariodeobra.domain.model.Work
import java.util.UUID


@Entity(tableName = "works")
class WorkEntity(
    @PrimaryKey()
    var id: String,
    var description: String = "",
    var address: String = "",
    var clientName: String = ""
) {
    companion object {
        fun fromDomain(work: Work): WorkEntity = WorkEntity(
            id = work.id ?: UUID.randomUUID().toString(),
            description = work.description,
            address = work.address,
            clientName = work.clientName
        )
    }
}