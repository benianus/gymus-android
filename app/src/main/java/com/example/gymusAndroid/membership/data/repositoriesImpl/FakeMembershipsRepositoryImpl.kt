package com.example.gymusAndroid.membership.data.repositoriesImpl

import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberRegisterRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberUpdateRequestDto
import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.models.MemberCard
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository
import kotlin.time.Clock

class FakeMembershipsRepositoryImpl : MembershipRepository {
    private val members = mutableListOf(
        Member(
            id = 1,
            firstName = "Mohamed",
            lastName = "BENIANE",
            email = "beniane39@gmail.com",
            phoneNumber = "+213658854636",
            address = "city 36 apartment bloc b n08",
            birthdate = "1994-02-11",
            personalPhoto = "photo",
            endDate = "2026-10-12",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        ),
        Member(
            id = 2,
            firstName = "Sarah",
            lastName = "Mansouri",
            email = "sarah.m@email.com",
            phoneNumber = "+213555123456",
            address = "Avenue 1er Novembre, Algiers",
            birthdate = "1990-05-20",
            personalPhoto = "photo2",
            endDate = "2025-05-20",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = false
        ),
        Member(
            id = 3,
            firstName = "Ahmed",
            lastName = "Ziane",
            email = "ahmed.ziane@provider.dz",
            phoneNumber = "+213770987654",
            address = "Rue Didouche Mourad, 16000",
            birthdate = "1988-11-03",
            personalPhoto = "photo3",
            endDate = "2024-12-01",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        ),
        Member(
            id = 4,
            firstName = "Lina",
            lastName = "Belkacem",
            email = "lina.b@gmail.com",
            phoneNumber = "+213661223344",
            address = "Villa 45, Hydra",
            birthdate = "1995-08-15",
            personalPhoto = "photo4",
            endDate = "2025-08-15",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        ),
        Member(
            id = 5,
            firstName = "Karim",
            lastName = "Haddad",
            email = "k.haddad@outlook.com",
            phoneNumber = "+213550445566",
            address = "Cite des Asphodeles, El Biar",
            birthdate = "1982-03-25",
            personalPhoto = "photo5",
            endDate = "2023-12-31",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = false
        ),
        Member(
            id = 6,
            firstName = "Yasmine",
            lastName = "Hamidi",
            email = "yasmine.h@webmail.com",
            phoneNumber = "+213699887766",
            address = "Residence Sahraoui, Kouba",
            birthdate = "1998-12-12",
            personalPhoto = "photo6",
            endDate = "2026-01-10",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        ),
        Member(
            id = 7,
            firstName = "Omar",
            lastName = "Berrabah",
            email = "omar.ber@gmail.com",
            phoneNumber = "+213552001122",
            address = "Lotissement 12, Dely Ibrahim",
            birthdate = "1992-07-07",
            personalPhoto = "photo7",
            endDate = "2025-03-15",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        ),
        Member(
            id = 8,
            firstName = "Amel",
            lastName = "Saidi",
            email = "amel.saidi@gmail.com",
            phoneNumber = "+213778554433",
            address = "Cité 200 logts, Reghaia",
            birthdate = "1996-02-28",
            personalPhoto = "photo8",
            endDate = "2025-11-20",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        ),
        Member(
            id = 9,
            firstName = "Ryad",
            lastName = "Mahrez",
            email = "ryad.m7@football.com",
            phoneNumber = "+213660112233",
            address = "Belouizdad, 16000",
            birthdate = "1991-02-21",
            personalPhoto = "photo9",
            endDate = "2027-01-01",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        ),
        Member(
            id = 10,
            firstName = "Sofia",
            lastName = "Boudiaf",
            email = "sofia.b@mail.dz",
            phoneNumber = "+213550334455",
            address = "Boulevard Mohamed V, Oran",
            birthdate = "2000-09-10",
            personalPhoto = "photo10",
            endDate = "2025-06-30",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        ),
        Member(
            id = 11,
            firstName = "Malek",
            lastName = "Bennabi",
            email = "malek.philos@edu.dz",
            phoneNumber = "+213667788990",
            address = "Constantine, Center City",
            birthdate = "1985-04-14",
            personalPhoto = "photo11",
            endDate = "2024-05-01",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = false
        ),
        Member(
            id = 12,
            firstName = "Meriem",
            lastName = "Fekkaï",
            email = "meriem.f@art.dz",
            phoneNumber = "+213554332211",
            address = "Casbah, Algiers",
            birthdate = "1993-01-05",
            personalPhoto = "photo12",
            endDate = "2026-12-12",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        ),
        Member(
            id = 13,
            firstName = "Walid",
            lastName = "Mesloub",
            email = "walid.m@sports.dz",
            phoneNumber = "+213770001122",
            address = "Cite Olympic, Cheraga",
            birthdate = "1989-10-10",
            personalPhoto = "photo13",
            endDate = "2025-09-09",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        ),
        Member(
            id = 14,
            firstName = "Anis",
            lastName = "Rahmani",
            email = "anis.rahmani@news.dz",
            phoneNumber = "+213662556677",
            address = "Bir Mourad Rais",
            birthdate = "1980-06-20",
            personalPhoto = "photo14",
            endDate = "2025-02-14",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        ),
        Member(
            id = 15,
            firstName = "Selma",
            lastName = "Kacimi",
            email = "selma.k@gmail.com",
            phoneNumber = "+213559887755",
            address = "Annaba, Plage",
            birthdate = "1997-03-30",
            personalPhoto = "photo15",
            endDate = "2025-10-30",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        ),
        Member(
            id = 16,
            firstName = "Tarik",
            lastName = "Aziz",
            email = "t.aziz@it.dz",
            phoneNumber = "+213775441122",
            address = "Cyber Parc, Sidi Abdellah",
            birthdate = "1994-11-11",
            personalPhoto = "photo16",
            endDate = "2026-11-11",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        )
    )
    private val memberCards = mutableListOf(
        MemberCard(
            id = 1,
            firstName = "Mohamed",
            lastName = "BENIANE",
            birthDate = "11-02-1194",
            joinDate = "10-09-2026",
            endDate = "11-06-2026",
            personalPhoto = "photo"
        )
    )

    override suspend fun findAllMembers(
        page: Int,
        pageSize: Int
    ): List<Member> {
        return this.members
    }

    override suspend fun registerMember(memberRegisterRequestDto: MemberRegisterRequestDto) {
        TODO("Not yet implemented")
    }

    override suspend fun recordAttendance(memberId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun renewMembership(memberId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun findMemberCard(memberId: Int): MemberCard? {
        return this.memberCards.firstOrNull { it.id == memberId }
    }

    override suspend fun updateMember(
        memberId: Int,
        memberUpdateRequestDto: MemberUpdateRequestDto
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMember(memberId: Int) {
        TODO("Not yet implemented")
    }

}