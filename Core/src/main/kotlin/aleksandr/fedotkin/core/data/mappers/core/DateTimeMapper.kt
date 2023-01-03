package aleksandr.fedotkin.core.data.mappers.core

import aleksandr.fedotkin.core.core.mapper.CoreMapper
import java.time.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime

class DateTimeMapper: CoreMapper<LocalDateTime, kotlinx.datetime.LocalDateTime> {
    override fun map(value: LocalDateTime): kotlinx.datetime.LocalDateTime {
        return value.toKotlinLocalDateTime()
    }

    override fun reverseMap(value: kotlinx.datetime.LocalDateTime): LocalDateTime {
        return value.toJavaLocalDateTime()
    }
}
