package aleksandr.fedotkin.core.data.mappers.core

import aleksandr.fedotkin.core.core.mapper.BaseMapperTest
import java.time.LocalDate
import org.koin.core.component.inject

class DateMapperTest : BaseMapperTest<LocalDate, kotlinx.datetime.LocalDate>() {

    override val mapper by inject<DateMapper>()

    override suspend fun generateModel(): LocalDate {
        return LocalDate.now()
    }
}
