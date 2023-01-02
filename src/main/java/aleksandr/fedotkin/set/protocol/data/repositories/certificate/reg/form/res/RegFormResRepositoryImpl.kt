package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.core.exception.SetExternalException
import aleksandr.fedotkin.set.protocol.data.dto.RequestType
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormResMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResTBSModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResTBSRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.cert.X509Certificate

class RegFormResRepositoryImpl(
    override val mapper: RegFormResMapper,
    private val regFormResTBSRepository: RegFormResTBSRepository
) : RegFormResRepository {

    override suspend fun create(
        regFormReqDataModel: RegFormReqDataModel,
        privateKey: PrivateKey,
        certificate: X509Certificate
    ): RegFormResModel {
        val regFormResTBSModel = regFormResTBSRepository.create(model = regFormReqDataModel, certificate = certificate)
        val signature = regFormResTBSRepository.createSignature(model = regFormResTBSModel, privateKey = privateKey)
        return RegFormResModel(signature = signature, regFormResTBS = regFormResTBSModel)
    }

    override suspend fun verifySignature(model: RegFormResModel, certificate: X509Certificate) {
        if (regFormResTBSRepository.verifySignature(
                model = model.regFormResTBS,
                certificate = certificate,
                signature = model.signature
            )
        ) {
            throw SetExternalException(errorCode = ErrorCode.SignatureFailure)
        }
    }

    override suspend fun check(
        model: RegFormResModel,
        thumbs: List<ByteArray>,
        regFormReqDataModel: RegFormReqDataModel
    ) {
        checkRRPID(rrpid = regFormReqDataModel.rrpID, model = model.regFormResTBS)
        checkRequestType(requestType = regFormReqDataModel.requestType, model = model.regFormResTBS)
        checkLIDEE(lidEE = regFormReqDataModel.lidEE, model = model.regFormResTBS)
        checkChallEE2(challEE2 = regFormReqDataModel.challEE2, model = model.regFormResTBS)
    }

    private suspend fun checkRRPID(rrpid: BigInteger, model: RegFormResTBSModel) {
        if (!regFormResTBSRepository.checkRRPID(rrpid, model)) {
            throw SetExternalException(errorCode = ErrorCode.UnknownXID)
        }
    }

    private suspend fun checkRequestType(requestType: RequestType, model: RegFormResTBSModel) {
        if (!regFormResTBSRepository.checkRequestType(requestType, model)) {
            throw SetExternalException(errorCode = ErrorCode.ChallengeMismatch)
        }
    }

    private suspend fun checkLIDEE(lidEE: BigInteger, model: RegFormResTBSModel) {
        if (!regFormResTBSRepository.checkLIDEE(lidEE, model)) {
            throw SetExternalException(errorCode = ErrorCode.ChallengeMismatch)
        }
    }

    private suspend fun checkChallEE2(challEE2: BigInteger, model: RegFormResTBSModel) {
        if (!regFormResTBSRepository.checkChallEE2(challEE2, model)) {
            throw SetExternalException(errorCode = ErrorCode.ChallengeMismatch)
        }
    }

    private suspend fun checkThumbs(thumbs: List<ByteArray>, model: RegFormResTBSModel) {
        if (!regFormResTBSRepository.checkThumbs(thumbs, model)) {
            throw SetExternalException(errorCode = ErrorCode.ThumbsMismatch)
        }
    }
}
