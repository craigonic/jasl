#include "counters/Descriptions.h"

#include "../private/Descriptions_p.h"

#include "gtest/gtest.h"

using namespace jasl::counters;

TEST(EnumTest, Descriptions)
{
    const auto crewObject = toObject(Descriptions::Crew);
    EXPECT_EQ(fromDescriptionsObject(crewObject),Descriptions::Crew);
    const auto halfSquadObject = toObject(Descriptions::HalfSquad);
    EXPECT_EQ(fromDescriptionsObject(halfSquadObject),Descriptions::HalfSquad);
    const auto leaderObject = toObject(Descriptions::Leader);
    EXPECT_EQ(fromDescriptionsObject(leaderObject),Descriptions::Leader);
    const auto squadObject = toObject(Descriptions::Squad);
    EXPECT_EQ(fromDescriptionsObject(squadObject),Descriptions::Squad);
}

int main(int argc,char** argv)
{
    ::testing::InitGoogleTest(&argc,argv);
    return RUN_ALL_TESTS();
}
