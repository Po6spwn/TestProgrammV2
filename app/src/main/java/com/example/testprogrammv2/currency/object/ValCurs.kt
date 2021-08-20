package com.example.testprogrammv2.currency.`object`.xml

import android.icu.text.AlphabeticIndex.Record
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.util.*

@Root(strict = false)
data class ValCurs (
    @field:ElementList(name = "Record", inline = true)
    @param:ElementList(name = "Record", inline = true)
    var Record: List<RecordV>,

//    @field:Attribute(name = "ID")
//    @param:Attribute(name = "ID")
//    var ID: String,

    @field:Attribute(name = "DateRange1")
    @param:Attribute(name = "DateRange1")
    var DateRange1: String,

    @field:Attribute(name = "DateRange2")
    @param:Attribute(name = "DateRange2")
    var DateRange2: String,

//    @field:Attribute(name = "name")
//    @param:Attribute(name = "name")
//    var name: String
)

@Root(name = "Record",strict = false) // Strict - говорит нам о тмо что мы должны распарсить ВСЕ элементы без исключения
data class RecordV (

//    @field:Attribute(name = "Nominal")
//    @param:Attribute(name = "Nominal")
//    var Nominal: Int,
//
    @field:Attribute(name = "Date") //--> Если так <Record Date="06.03.2021" Id="R01235">, то Attribute
    @param:Attribute(name = "Date")
    var Date: String,

    @field:Element(name = "Value") // --> Если так <Value>74,4275</Value>, то Element
    @param:Element(name = "Value")
    var Value: String,

//    @field:Attribute(name = "Id")
//    @param:Attribute(name = "Id")
//    var Id: String,
)
