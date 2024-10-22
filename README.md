<!--
SPDX-FileCopyrightText: 2021-2022 Alliander N.V.

SPDX-License-Identifier: MPL-2.0
-->

# Measure

`measure` is a Kotlin library for working with units of measurement that are - for example - used in the power system sector.

## Motivation
Projects often need a way to express different quantities. For example, building software for electric grids one encounters quantities like power, energy, amperage etcetera.

When one choses to represent this quantities as `BigDecimal`, one needs to be very vigilant when calculating with these quantities. It is easy to add power and energy when they both are represented as a `BigDecimal`, even though it does not make sense on physical grounds.

Rather, one would represents quantities as a multiple of a [*unit of measurement*][wikipedia:unit]. This project provides exactly that.

## Usage
In using *measure* library there are two parts. The generic `Measure` and `Units` provided by the measure library, and the specific units that your project are interested in.

```kotlin
val power: Measure<Power> = 10 * kiloWatt
val duration: Measure<Time> = 15 * minutes

val energy: Measure<Energy> = power * duration

assertThat(energy `as` megaJoule).equals(9 * megaJoule)
```

The code above demonstrates a fully annotated calculation with quantities. Often times the compiler can infer the corresponding types.

## Development
This project is written in [Kotlin][kotlin] and uses [Gradle][gradle] as a build tool. To see which tasks are available run

```
./gradlew tasks
```

### Model
The code found in the `measure` module is generated from a model. The model is a [`System`](https://github.com/alliander-opensource/measure/blob/master/measure-generator/src/main/kotlin/com/alliander/open/measure/generator/System.kt) which describes units and their multiplications. An [application](https://github.com/alliander-opensource/measure/blob/master/measure-generator/src/main/kotlin/com/alliander/open/measure/generator/App.kt) can read a representation of a system and output the source.

If one wants to add a specific unit one can add it to the [units](https://github.com/alliander-opensource/measure/blob/master/measure-generator/src/main/resources/units.json) as found in the resources of the `measure-generator` module.

If the functionality of `Measure` should change one should make the intended changes in the [`measure.vtl`](https://github.com/alliander-opensource/measure/blob/master/measure-generator/src/main/resources/templates/measure.vtl) template or its companion [`specific.vtl`](https://github.com/alliander-opensource/measure/tree/master/measure-generator/src/main/resources/templates) that describes how specific units like *power*, *energy* or *time*.

# License
This project is licensed under the Mozilla Public License, version 2.0 - see [LICENSE](LICENSE) for details.

# Licenses third-party libraries
This project includes third-party libraries, 
which are licensed under their own respective Open-Source licenses.
SPDX-License-Identifier headers are used to show which license is applicable. 
The concerning license files can be found in the LICENSES directory.

# Contributing
Please read [CODE_OF_CONDUCT](CODE_OF_CONDUCT.md) and [CONTRIBUTING](CONTRIBUTING.md) for details on the process 
for submitting pull requests to us.

# Contact
Please read [SUPPORT](SUPPORT.md) for how to connect and get into contact with the Measure project.

[wikipedia:unit]: https://en.wikipedia.org/wiki/Unit_of_measurement
[github:create-from-template]: https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-on-github/creating-a-repository-from-a-template
[kotlin]: https://kotlinlang.org/
[gradle]: https://gradle.org/

