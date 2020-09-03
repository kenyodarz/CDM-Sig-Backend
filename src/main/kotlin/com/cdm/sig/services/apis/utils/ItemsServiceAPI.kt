package com.cdm.sig.services.apis.utils

import com.cdm.sig.models.integrations.Item
import com.cdm.sig.utils.archive.GenericServiceAPI

interface ItemsServiceAPI : GenericServiceAPI<Item, Long> {
}