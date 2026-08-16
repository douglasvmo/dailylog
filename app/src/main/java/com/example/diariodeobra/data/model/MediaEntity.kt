package com.example.diariodeobra.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.diariodeobra.domain.model.MediaFile
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(
    tableName = "medias",
    foreignKeys = [ForeignKey(
        entity = DailyReportEntity::class,
        parentColumns = ["id"],
        childColumns = ["reportId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("reportId")])
data class MediaEntity(
    @PrimaryKey
    var id: String,
    var reportId: String,
    var url: String,
    var checksum: String,
    var description: String?
) {

    fun toDomain(): MediaFile = MediaFile(
        id = this.id,
        path = this.url,
        checksum = this.checksum,
        description = this.description,
    )

    companion object Companion {

        @OptIn(ExperimentalUuidApi::class)
        fun fromDomain(media: MediaFile, reportId: String): MediaEntity = MediaEntity(
            id = media.id ?: Uuid.generateV7().toString(),
            reportId,
            url = media.path,
            checksum = media.checksum,
            description = media.description
        )
    }

}