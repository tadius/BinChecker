package com.tadiuzzz.binchecker.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tadiuzzz.binchecker.domain.model.BinBank
import com.tadiuzzz.binchecker.domain.model.BinCountry
import com.tadiuzzz.binchecker.domain.model.BinInfo
import com.tadiuzzz.binchecker.domain.model.BinNumber

@Entity(tableName = "bininfo")
data class BinInfoEntity(
    val bin: String,
    val numberLength: Int?,
    val numberLuhn: Boolean?,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val countryNumeric: String?,
    val countryAlpha2: String?,
    val countryName: String?,
    val countryEmoji: String?,
    val countryCurrency: String?,
    val countryLatitude: Float?,
    val countryLongitude: Float?,
    val bankName: String?,
    val bankUrl: String?,
    val bankPhone: String?,
    val bankCity: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
) {
    companion object {
        fun mapFromBinInfo(binInfo: BinInfo, bin: String): BinInfoEntity {
            return BinInfoEntity(
                bin = bin,
                numberLength = binInfo.number?.length,
                numberLuhn = binInfo.number?.luhn,
                scheme = binInfo.scheme,
                type = binInfo.type,
                brand = binInfo.brand,
                prepaid = binInfo.prepaid,
                countryNumeric = binInfo.country?.numeric,
                countryAlpha2 = binInfo.country?.alpha2,
                countryName = binInfo.country?.name,
                countryEmoji = binInfo.country?.emoji,
                countryCurrency = binInfo.country?.currency,
                countryLatitude = binInfo.country?.latitude,
                countryLongitude = binInfo.country?.longitude,
                bankName = binInfo.bank?.name,
                bankUrl = binInfo.bank?.url,
                bankPhone = binInfo.bank?.phone,
                bankCity = binInfo.bank?.city,
            )
        }

        fun mapToBinInfo(binInfoEntity: BinInfoEntity): BinInfo {
            return BinInfo(
                id = binInfoEntity.id,
                bin = binInfoEntity.bin,
                number = BinNumber(
                    length = binInfoEntity.numberLength,
                    luhn = binInfoEntity.numberLuhn
                ),
                scheme = binInfoEntity.scheme,
                type = binInfoEntity.type,
                brand = binInfoEntity.brand,
                prepaid = binInfoEntity.prepaid,
                country = BinCountry(
                    numeric = binInfoEntity.countryNumeric,
                    alpha2 = binInfoEntity.countryAlpha2,
                    name = binInfoEntity.countryName,
                    emoji = binInfoEntity.countryEmoji,
                    currency = binInfoEntity.countryCurrency,
                    latitude = binInfoEntity.countryLatitude,
                    longitude = binInfoEntity.countryLongitude,
                ),
                bank = BinBank(
                    name = binInfoEntity.bankName,
                    url = binInfoEntity.bankUrl,
                    phone = binInfoEntity.bankPhone,
                    city = binInfoEntity.bankCity,
                )
            )
        }
    }
}