package com.example.diariodeobra.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.diariodeobra.domain.model.Photo
import java.util.UUID

@Entity(
    tableName = "photos",
    foreignKeys = [ForeignKey(
        entity = DailyReportEntity::class,
        parentColumns = ["id"],
        childColumns = ["reportId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("reportId")])
data class PhotoEntity(
    @PrimaryKey
    var id: String,
    var reportId: String?,
    var url: String,
    var description: String?
) {

    fun toDomain(): Photo = Photo(
        id = this.id,
        path = this.url,
        description = this.description
    )

    companion object {
        fun fromDomain(photo: Photo): PhotoEntity = PhotoEntity(
            id = photo.id ?: UUID.randomUUID().toString(),
            reportId = null,
            url = photo.path,
            description = photo.description
        )
    }

}