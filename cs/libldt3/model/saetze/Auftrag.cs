using System.Collections.Generic;
using libldt3.attributes;
using libldt3.model.enums;
using libldt3.model.objekte;

namespace libldt3
{
	namespace model
	{
		namespace saetze
		{


			/**
			 * Satzart: Auftrag "8215"
			 */
			[Datenpaket(Value = Satzart.Auftrag)]
			public class Auftrag : Satz
			{

				[Feld(Value = "8122", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 23)]
				public Einsenderidentifikation Einsenderidentifikation;
				[Feld(Value = "8145", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 7)]
				public Patient Patient;
				[Feld(Value = "8169", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 19)]
				public Koerperkenngroessen Koerperkenngroessen;
				[Feld(Value = "8150", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 15)]
				public Schwangerschaft Schwangerschaft;
				[Feld(Value = "8140", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 12)]
				public Mutterschaft Mutterschaft;
				[Feld(Value = "8153", Name = "Tier_Sonstiges", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 14)]
				public Tier Tier;
				[Feld(Value = "8113", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 19)]
				public Auftragsinformation Auftragsinformation;
				[Feld(Value = "8127", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 18)]
				public IList<Veranlassungsgrund> Veranlassungsgrund;
				[Feld(Value = "8101", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 22)]
				public Abrechnungsinformation Abrechnungsinformationen;
				[Feld(Value = "8137", Feldart = Feldart.bedingt_kann)]
				public IList<Material> Material;
				[Feld(Value = "8159", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 24)]
				public IList<Untersuchungsanforderung> Untersuchungsanforderung;
				[Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 26)]
				public IList<Fliesstext> zusaeztlicheInformationen;
				[Feld(Value = "8110", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 6)]
				public IList<Anhang> Ahang;

			}
		}
	}
}
