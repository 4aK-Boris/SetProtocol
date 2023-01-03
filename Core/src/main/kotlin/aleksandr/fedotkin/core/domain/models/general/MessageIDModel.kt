package aleksandr.fedotkin.core.domain.models.general

import aleksandr.fedotkin.core.core.Model
import java.math.BigInteger

data class MessageIDModel(
    val lIdC: BigInteger?,
    val lIdM: BigInteger?,
    val xId: BigInteger?
): Model
