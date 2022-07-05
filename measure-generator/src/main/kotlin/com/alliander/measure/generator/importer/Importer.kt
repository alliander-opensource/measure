// SPDX-FileCopyrightText: 2021-2022 Alliander N.V.
//
// SPDX-License-Identifier: MPL-2.0

package com.alliander.measure.generator.importer

import com.alliander.measure.generator.System

interface Importer {
    fun import(): System
}
