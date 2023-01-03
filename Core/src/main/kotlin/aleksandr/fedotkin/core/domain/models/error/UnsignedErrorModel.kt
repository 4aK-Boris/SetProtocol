package aleksandr.fedotkin.core.domain.models.error

import aleksandr.fedotkin.core.core.Model

class UnsignedErrorModel<T: Model>(
    val errorTBS: ErrorTBSModel<T>
): Model {

    override fun equals(other: Any?): Boolean {
        if (other !is UnsignedErrorModel<*>) return false
        return other.errorTBS == this.errorTBS
    }

    override fun hashCode(): Int {
        return errorTBS.hashCode()
    }
}
