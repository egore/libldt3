using NUnit.Framework;

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            [TestFixture]
            public class F001Test
            {
                static readonly F001 f001 = new F001();

                [Test]
                public void testEmpty()
                {
                    Assert.That(f001.IsValid(""), Is.False);
                }

                [Test]
                public void testNull()
                {
                    Assert.That(f001.IsValid(null), Is.True);
                }

                [Test]
                public void testFiveZeros()
                {
                    Assert.That(f001.IsValid("00000"), Is.True);
                }

                [Test]
                public void testFiveNines()
                {
                    Assert.That(f001.IsValid("99999"), Is.True);
                }

                [Test]
                public void testOneTwoThreeFourFive()
                {
                    Assert.That(f001.IsValid("12345"), Is.True);
                }

                [Test]
                public void testOneTwoThreeFour()
                {
                    Assert.That(f001.IsValid("1234"), Is.False);
                }

                [Test]
                public void testOneTwoThreeFourFiveSix()
                {
                    Assert.That(f001.IsValid("123456"), Is.False);
                }

                [Test]
                public void testABCDE()
                {
                    Assert.That(f001.IsValid("ABCDE"), Is.False);
                }

                [Test]
                public void testFiveSymbols()
                {
                    Assert.That(f001.IsValid("/=()["), Is.False);
                }

                [Test]
                public void testOneTwoThreeFourA()
                {
                    Assert.That(f001.IsValid("1234A"), Is.False);
                }

                [Test]
                public void testOneTwoThreeFourLowerA()
                {
                    Assert.That(f001.IsValid("1234a"), Is.False);
                }
            }
        }
    }
}
