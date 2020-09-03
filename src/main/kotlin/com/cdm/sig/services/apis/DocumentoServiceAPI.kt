package com.cdm.sig.services.apis

import com.cdm.sig.models.Documento
import com.cdm.sig.utils.archive.GenericServiceAPI

interface DocumentoServiceAPI: GenericServiceAPI<Documento, Long> {
    fun findDocumentoByEmpleado(cedula: String): List<Documento>
}