using libldt3.attributes;
using libldt3.model.objekte;

namespace libldt3
{
    namespace model
    {
        namespace enums
        {
            [Objekt]
            public class GrenzwertindikatorErweitert
            {
                public Grenzwertindikator Value;
                [Feld(Value = "8126", Name = "Fehlermeldung_Aufmerksamkeit", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 28)]
                public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
            }
        }
    }
}