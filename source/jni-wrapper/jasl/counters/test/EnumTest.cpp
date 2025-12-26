#include "../private/Descriptions_p.h"
#include "../private/InfantryTypes_p.h"
#include "../private/Nationalities_p.h"

#include "gtest/gtest.h"

using namespace jasl::counters;

TEST(EnumTest, Descriptions)
{
    EXPECT_EQ(toString(Descriptions::Crew).compare("Crew"),0);
    const auto crewObject = toObject(Descriptions::Crew);
    EXPECT_EQ(fromDescriptionsObject(crewObject),Descriptions::Crew);

    EXPECT_EQ(toString(Descriptions::HalfSquad).compare("Half Squad"),0);
    const auto halfSquadObject = toObject(Descriptions::HalfSquad);
    EXPECT_EQ(fromDescriptionsObject(halfSquadObject),Descriptions::HalfSquad);

    EXPECT_EQ(toString(Descriptions::Leader).compare("Leader"),0);
    const auto leaderObject = toObject(Descriptions::Leader);
    EXPECT_EQ(fromDescriptionsObject(leaderObject),Descriptions::Leader);

    EXPECT_EQ(toString(Descriptions::Squad).compare("Squad"),0);
    const auto squadObject = toObject(Descriptions::Squad);
    EXPECT_EQ(fromDescriptionsObject(squadObject),Descriptions::Squad);
}

TEST(EnumTest, InfantryTypes)
{
    EXPECT_EQ(toString(InfantryTypes::None).compare(""),0);
    const auto noneObject = toObject(InfantryTypes::None);
    EXPECT_EQ(fromInfantryTypesObject(noneObject),InfantryTypes::None);

    EXPECT_EQ(toString(InfantryTypes::Paratroops).compare("Paratroops"),0);
    const auto paratroopsObject = toObject(InfantryTypes::Paratroops);
    EXPECT_EQ(fromInfantryTypesObject(paratroopsObject),InfantryTypes::Paratroops);

    EXPECT_EQ(toString(InfantryTypes::Airborne).compare("Airborne"),0);
    const auto airborneObject = toObject(InfantryTypes::Airborne);
    EXPECT_EQ(fromInfantryTypesObject(airborneObject),InfantryTypes::Airborne);

    EXPECT_EQ(toString(InfantryTypes::Anzac).compare("ANZAC"),0);
    const auto anzacObject = toObject(InfantryTypes::Anzac);
    EXPECT_EQ(fromInfantryTypesObject(anzacObject),InfantryTypes::Anzac);

    EXPECT_EQ(toString(InfantryTypes::Canadian).compare("Canadian"),0);
    const auto canadianObject = toObject(InfantryTypes::Canadian);
    EXPECT_EQ(fromInfantryTypesObject(canadianObject),InfantryTypes::Canadian);

    EXPECT_EQ(toString(InfantryTypes::FreeFrench).compare("Free French"),0);
    const auto freeFrenchObject = toObject(InfantryTypes::FreeFrench);
    EXPECT_EQ(fromInfantryTypesObject(freeFrenchObject),InfantryTypes::FreeFrench);

    EXPECT_EQ(toString(InfantryTypes::FreePolish).compare("Free Polish"),0);
    const auto freePolishObject = toObject(InfantryTypes::FreePolish);
    EXPECT_EQ(fromInfantryTypesObject(freePolishObject),InfantryTypes::FreePolish);

    EXPECT_EQ(toString(InfantryTypes::Guardsmen).compare("Guardsmen"),0);
    const auto guardsmenObject = toObject(InfantryTypes::Guardsmen);
    EXPECT_EQ(fromInfantryTypesObject(guardsmenObject),InfantryTypes::Guardsmen);

    EXPECT_EQ(toString(InfantryTypes::Gurkha).compare("Gurkha"),0);
    const auto gurkhaObject = toObject(InfantryTypes::Gurkha);
    EXPECT_EQ(fromInfantryTypesObject(gurkhaObject),InfantryTypes::Gurkha);

    EXPECT_EQ(toString(InfantryTypes::Sissi).compare("Sissi"),0);
    const auto sissiObject = toObject(InfantryTypes::Sissi);
    EXPECT_EQ(fromInfantryTypesObject(sissiObject),InfantryTypes::Sissi);

    EXPECT_EQ(toString(InfantryTypes::Engineers).compare("Engineers"),0);
    const auto engineersObject = toObject(InfantryTypes::Engineers);
    EXPECT_EQ(fromInfantryTypesObject(engineersObject),InfantryTypes::Engineers);

    EXPECT_EQ(toString(InfantryTypes::Commissar).compare("Commissar"),0);
    const auto commissarObject = toObject(InfantryTypes::Commissar);
    EXPECT_EQ(fromInfantryTypesObject(commissarObject),InfantryTypes::Commissar);

    EXPECT_EQ(toString(InfantryTypes::Guards).compare("Guards"),0);
    const auto guardsObject = toObject(InfantryTypes::Guards);
    EXPECT_EQ(fromInfantryTypesObject(guardsObject),InfantryTypes::Guards);
}

TEST(EnumTest, Nationalities)
{
    EXPECT_EQ(toString(Nationalities::AlliedMinor).compare("Allied Minor"),0);
    const auto alliedMinorObject = toObject(Nationalities::AlliedMinor);
    EXPECT_EQ(fromNationalitiesObject(alliedMinorObject),Nationalities::AlliedMinor);

    EXPECT_EQ(toString(Nationalities::American).compare("American"),0);
    const auto americanObject = toObject(Nationalities::American);
    EXPECT_EQ(fromNationalitiesObject(americanObject),Nationalities::American);

    EXPECT_EQ(toString(Nationalities::AxisMinor).compare("Axis Minor"),0);
    const auto axisMinorObject = toObject(Nationalities::AxisMinor);
    EXPECT_EQ(fromNationalitiesObject(axisMinorObject),Nationalities::AxisMinor);

    EXPECT_EQ(toString(Nationalities::British).compare("British"),0);
    const auto britishObject = toObject(Nationalities::British);
    EXPECT_EQ(fromNationalitiesObject(britishObject),Nationalities::British);

    EXPECT_EQ(toString(Nationalities::Finnish).compare("Finnish"),0);
    const auto finnishObject = toObject(Nationalities::Finnish);
    EXPECT_EQ(fromNationalitiesObject(finnishObject),Nationalities::Finnish);

    EXPECT_EQ(toString(Nationalities::French).compare("French"),0);
    const auto frenchObject = toObject(Nationalities::French);
    EXPECT_EQ(fromNationalitiesObject(frenchObject),Nationalities::French);

    EXPECT_EQ(toString(Nationalities::German).compare("German"),0);
    const auto germanObject = toObject(Nationalities::German);
    EXPECT_EQ(fromNationalitiesObject(germanObject),Nationalities::German);

    EXPECT_EQ(toString(Nationalities::Italian).compare("Italian"),0);
    const auto italianObject = toObject(Nationalities::Italian);
    EXPECT_EQ(fromNationalitiesObject(italianObject),Nationalities::Italian);

    EXPECT_EQ(toString(Nationalities::Japanese).compare("Japanese"),0);
    const auto japaneseObject = toObject(Nationalities::Japanese);
    EXPECT_EQ(fromNationalitiesObject(japaneseObject),Nationalities::Japanese);

    EXPECT_EQ(toString(Nationalities::Partisan).compare("Partisan"),0);
    const auto partisanObject = toObject(Nationalities::Partisan);
    EXPECT_EQ(fromNationalitiesObject(partisanObject),Nationalities::Partisan);

    EXPECT_EQ(toString(Nationalities::Russian).compare("Russian"),0);
    const auto russianObject = toObject(Nationalities::Russian);
    EXPECT_EQ(fromNationalitiesObject(russianObject),Nationalities::Russian);
}

int main(int argc,char** argv)
{
    ::testing::InitGoogleTest(&argc,argv);
    return RUN_ALL_TESTS();
}
