// ************************************************************************** //
// Game.swig - This file is an interface description for <A HREF="http://www.swig.org/">SWIG</A>. It is used to  //
//             generate shared libraries that link to the library created for //
//             the <A HREF="../../jasl/ui/data/data.html">package</A> containing this class. These libraries, along with //
//             the other files that are generated, allow objects of this type //
//             to be accessed from the supported scripting languages (<A HREF="http://www.perl.org/">Perl</A>    //
//             and <A HREF="http://www.python.org/">Python</A>).                                                   //
//                                                                            //
//               NOTE: This file is intended for use in the <A HREF="UiData.swig.html">UiData</A> interface  //
//                     file, not to build standalone libraries.               //
//                                                                            //
// Written By: Craig R. Campbell  -  November 2014                            //
// ************************************************************************** //

%module Game;

// The public interface of the <A HREF="../../jasl/ui/data/Game.html">Game</A> class.

class Game
{
    public:

        static Game*          game();

        bool                  addPlayer(::jasl::ui::data::Side$Sides*,
                                        ::java::lang::String*,
                                        ::jasl::counters::Nationality$Nationalities*,
                                        int);

        Player*               player(::jasl::ui::data::Side$Sides*,
                                     ::java::lang::String*);

        ::java::lang::String* toText();

    private:

        Game();
};
