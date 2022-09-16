using System;
using System.Collections.Generic;
using System.IO;
using de.egore911.fuzz;
using NUnit.Framework;

namespace libldt3
{
    namespace model
    {
        namespace saetze
        {
            public class FuzzerTest
            {

                readonly Fuzzer fuzzer = new Fuzzer(new LdtCustomHandler());

                [Test]
                public void auftragFuzzerTest()
                {
                    Auftrag auftrag = fuzzer.fuzz<Auftrag>(typeof(Auftrag), "libldt3.model");
                    testSatz(auftrag);
                }

                [Test]
                public void befundFuzzerTest()
                {
                    Befund befund = fuzzer.fuzz<Befund>(typeof(Befund), "libldt3.model");
                    testSatz(befund);
                }

                [Test]
                public void laborDatenpaketAbschlussFuzzerTest()
                {
                    LaborDatenpaketAbschluss laborDatenpaketAbschluss = fuzzer.fuzz<LaborDatenpaketAbschluss>(typeof(LaborDatenpaketAbschluss), "libldt3.model");
                    testSatz(laborDatenpaketAbschluss);
                }

                [Test]
                public void laborDatenpaketHeaderFuzzerTest()
                {
                    LaborDatenpaketHeader laborDatenpaketHeader = fuzzer.fuzz<LaborDatenpaketHeader>(typeof(LaborDatenpaketHeader), "libldt3.model");
                    testSatz(laborDatenpaketHeader);
                }

                [Test]
                public void praxisDatenpaketAbschlussFuzzerTest()
                {
                    PraxisDatenpaketAbschluss praxisDatenpaketAbschluss = fuzzer.fuzz<PraxisDatenpaketAbschluss>(typeof(PraxisDatenpaketAbschluss), "libldt3.model");
                    testSatz(praxisDatenpaketAbschluss);
                }

                [Test]
                public void PraxisDatenpaketHeaderFuzzerTest()
                {
                    PraxisDatenpaketHeader praxisDatenpaketHeader = fuzzer.fuzz<PraxisDatenpaketHeader>(typeof(PraxisDatenpaketHeader), "libldt3.model");
                    testSatz(praxisDatenpaketHeader);
                }

                void testSatz(Satz satz)
                {
                    string file = Path.GetTempPath() + "fuzzer_test" + Guid.NewGuid().ToString() + ".ldt";
                    new LdtWriter(LdtConstants.Mode.RELAXED).Write(new List<Satz> { satz }, file);
                    IList<Satz> saetze = new LdtReader(LdtConstants.Mode.RELAXED).Read(file);
                    Assert.That(saetze.Count, Is.EqualTo(1), file);
                }
            }
        }
    }
}