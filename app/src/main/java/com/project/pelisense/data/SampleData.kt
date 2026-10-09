package com.project.pelisense.data

data class CastMember(
    val name: String,
    val role: String,
    val imageUrl: String? = null
)

data class Movie(
    val id: String,
    val title: String,
    val year: String,
    val duration: String,
    val rating: String,
    val mpaaRating: String,
    val genres: List<String>,
    val matchPercentage: Int,
    val synopsis: String,
    val director: String,
    val budget: String,
    val globalRevenue: String,
    val cast: List<CastMember>,
    val posterColor: Long,
    val isSaved: Boolean = false
)

data class UserProfile(
    val name: String = "Alex Cypher",
    val email: String = "alex.cypher@example.com",
    val uid: String = "abc123def456",
    val provider: String = "google.com",
    val isVerified: Boolean = true,
    val accountType: String = "Registered User",
    val isAnonymous: Boolean = false,
    val accountCreated: String = "Oct 12, 2023",
    val lastSignIn: String = "Today, 10:45 AM"
)

object SampleData {
    val sampleUser = UserProfile()

    val heroMovie = Movie(
        id = "neon_horizon",
        title = "NEON HORIZON",
        year = "2042",
        duration = "2H 15M",
        rating = "8.7",
        mpaaRating = "R",
        genres = listOf("SCI-FI", "THRILLER"),
        matchPercentage = 98,
        synopsis = "In a sprawling metropolis where synthetic memories are traded on the black market, a disillusioned memory extraction specialist discovers a hidden code within a high-profile client's mind. This code holds the key to a reality-altering network. Hunted by corporate mercenaries and digital specters, she must navigate the neon-lit underworld to decode the truth before her own memories are overwritten.",
        director = "Silas Vance",
        budget = "$120,000,000",
        globalRevenue = "$485,320,000",
        cast = listOf(
            CastMember("ELARA VANCE", "Protagonist"),
            CastMember("KADEN SYL", "Infiltrator"),
            CastMember("NOVA TRENT", "Archivist")
        ),
        posterColor = 0xFF0D282B,
        isSaved = true
    )

    val sampleMovies = listOf(
        heroMovie,
        Movie(
            id = "silent_echoes",
            title = "SILENT ECHOES",
            year = "2024",
            duration = "2H 14M",
            rating = "4.8",
            mpaaRating = "PG-13",
            genres = listOf("SCI-FI", "THRILLER"),
            matchPercentage = 85,
            synopsis = "A deep space mission encounters an unexpected signal from the abyss, unravelling cosmic mysteries.",
            director = "Elena Rostova",
            budget = "$85,000,000",
            globalRevenue = "$310,000,000",
            cast = listOf(CastMember("Kaelen Voss", "Captain")),
            posterColor = 0xFF0A2229,
            isSaved = true
        ),
        Movie(
            id = "optic_mirror",
            title = "OPTIC MIRROR",
            year = "2040",
            duration = "1H 58M",
            rating = "4.6",
            mpaaRating = "R",
            genres = listOf("THRILLER", "CYBERPUNK"),
            matchPercentage = 92,
            synopsis = "Visual hacking becomes the new frontier of corporate espionage, as perception itself is hacked.",
            director = "Marcus Vance",
            budget = "$65,000,000",
            globalRevenue = "$220,000,000",
            cast = listOf(CastMember("Aria Chen", "Hacker")),
            posterColor = 0xFF142B28,
            isSaved = false
        ),
        Movie(
            id = "ascension",
            title = "ASCENSION",
            year = "2024",
            duration = "2H 05M",
            rating = "4.5",
            mpaaRating = "PG-13",
            genres = listOf("ACTION", "CYBERPUNK"),
            matchPercentage = 78,
            synopsis = "Uncovering secrets at the bottom of the oceanic trench in a high-tech underwater facility.",
            director = "Dorian Gray",
            budget = "$110,000,000",
            globalRevenue = "$400,000,000",
            cast = listOf(CastMember("Jax Mercer", "Commander")),
            posterColor = 0xFF081C24,
            isSaved = true
        )
    )

    val moodOptions = listOf(
        "Happy" to "😊",
        "Sad" to "☹️",
        "Stressed" to "😵",
        "Bored" to "😐",
        "Anxious" to "😟",
        "Angry" to "😡",
        "Nostalgic" to "⌛",
        "Romantic" to "💙",
        "Excited" to "🎉",
        "Relaxed" to "🧘",
        "Lonely" to "👤",
        "Energetic" to "⚡"
    )

    val contextPills = listOf(
        "Solo", "Acompañado", "Mejorar ánimo", "Acompañarlo", "Pensar", "Desconectar"
    )
}
