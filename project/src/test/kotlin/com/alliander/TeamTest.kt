package example

import com.alliander.team
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


class TeamTest : StringSpec({
    "tested with love" {
        val ingredient = "love"

        "Tested with $ingredient by $team" shouldBe "Tested with love by Dynamo"
    }
})
