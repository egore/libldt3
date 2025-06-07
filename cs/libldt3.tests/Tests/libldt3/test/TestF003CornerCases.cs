using System;
using System.IO;
using NUnit.Framework;
using NodaTime;
using libldt3.model;

namespace libldt3
{
    namespace test
    {
        class TestF003CornerCases
        {
            private static string getTempFile(String prefix)
            {
                return Path.Combine(Path.GetTempPath(), Path.GetRandomFileName(), ".ldt");
            }

            [Test]
            public void testWithYearOnly()
            {
                var reader = new LdtReader(LdtConstants.Mode.RELAXED);
                string inFile = Path.Combine(TestContext.CurrentContext.TestDirectory, "Resources/person-19900000.ldt");
                var read = reader.Read(inFile);

                Assert.That(read, Is.Not.Null);
                Assert.That(read.Count, Is.EqualTo(1));
                Assert.That(read[0], Is.Not.Null);
                Assert.That(read[0], Is.InstanceOf<libldt3.model.saetze.Auftrag>());
                Assert.That(((libldt3.model.saetze.Auftrag)read[0]).Patient, Is.Not.Null);
                Assert.That(((libldt3.model.saetze.Auftrag)read[0]).Patient.Person, Is.Not.Null);
                Assert.That(((libldt3.model.saetze.Auftrag)read[0]).Patient.Person.Geburtsdatum, Is.Not.Null);
                Assert.That(((libldt3.model.saetze.Auftrag)read[0]).Patient.Person.Geburtsdatum, Is.InstanceOf<PartialYearOnly>());
                Assert.That(((PartialYearOnly) ((libldt3.model.saetze.Auftrag)read[0]).Patient.Person.Geburtsdatum).Date, Is.EqualTo(new YearOnly(1990)));

                var writer = new LdtWriter(LdtConstants.Mode.RELAXED);
                string tempFile = Path.GetTempFileName();
                writer.Write(read, tempFile);

                Assert.That(File.ReadAllText(inFile).Trim(), Is.EqualTo(File.ReadAllText(tempFile).Trim()));
                File.Delete(tempFile);
            }

            [Test]
            public void testWithYearAnyMonth()
            {
                var reader = new LdtReader(LdtConstants.Mode.RELAXED);
                string inFile = Path.Combine(TestContext.CurrentContext.TestDirectory, "Resources/person-19900200.ldt");
                var read = reader.Read(inFile);

                Assert.That(read, Is.Not.Null);
                Assert.That(read.Count, Is.EqualTo(1));
                Assert.That(read[0], Is.Not.Null);
                Assert.That(read[0], Is.InstanceOf<libldt3.model.saetze.Auftrag>());
                Assert.That(((libldt3.model.saetze.Auftrag)read[0]).Patient, Is.Not.Null);
                Assert.That(((libldt3.model.saetze.Auftrag)read[0]).Patient.Person, Is.Not.Null);
                Assert.That(((libldt3.model.saetze.Auftrag)read[0]).Patient.Person.Geburtsdatum, Is.Not.Null);
                Assert.That(((libldt3.model.saetze.Auftrag)read[0]).Patient.Person.Geburtsdatum, Is.InstanceOf<PartialYearMonth>());
                Assert.That(((PartialYearMonth) ((libldt3.model.saetze.Auftrag)read[0]).Patient.Person.Geburtsdatum).Date, Is.EqualTo(new YearMonth(1990, 2)));

                var writer = new LdtWriter(LdtConstants.Mode.RELAXED);
                string tempFile = Path.GetTempFileName();
                writer.Write(read, tempFile);

                Assert.That(File.ReadAllText(inFile).Trim(), Is.EqualTo(File.ReadAllText(tempFile).Trim()));
                File.Delete(tempFile);
            }

            [Test]
            public void testWithFullDate()
            {
                var reader = new LdtReader(LdtConstants.Mode.RELAXED);
                string inFile = Path.Combine(TestContext.CurrentContext.TestDirectory, "Resources/person-19900204.ldt");
                var read = reader.Read(inFile);

                Assert.That(read, Is.Not.Null);
                Assert.That(read.Count, Is.EqualTo(1));
                Assert.That(read[0], Is.Not.Null);
                Assert.That(read[0], Is.InstanceOf<libldt3.model.saetze.Auftrag>());
                Assert.That(((libldt3.model.saetze.Auftrag)read[0]).Patient, Is.Not.Null);
                Assert.That(((libldt3.model.saetze.Auftrag)read[0]).Patient.Person, Is.Not.Null);
                Assert.That(((libldt3.model.saetze.Auftrag)read[0]).Patient.Person.Geburtsdatum, Is.Not.Null);
                Assert.That(((libldt3.model.saetze.Auftrag)read[0]).Patient.Person.Geburtsdatum, Is.InstanceOf<PartialLocalDate>());
                Assert.That(((PartialLocalDate) ((libldt3.model.saetze.Auftrag)read[0]).Patient.Person.Geburtsdatum).Date, Is.EqualTo(new LocalDate(1990, 2, 4)));

                var writer = new LdtWriter(LdtConstants.Mode.RELAXED);
                string tempFile = Path.GetTempFileName();
                writer.Write(read, tempFile);

                Assert.That(File.ReadAllText(inFile).Trim(), Is.EqualTo(File.ReadAllText(tempFile).Trim()));
                File.Delete(tempFile);
            }
        }
    }
}