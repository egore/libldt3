using System;
using NUnit.Framework;

namespace libldt3
{
	namespace model
	{
		namespace regel
		{

			[TestFixture]
			public class F002Test
			{

				static readonly F002 f002 = new F002();

				[Test]
				public void testEmpty()
				{
					Assert.That(f002.IsValid(""), Is.False);
				}

				[Test]
				public void testNull()
				{
					Assert.That(f002.IsValid(null), Is.True);
				}

				[Test]
				public void testOnlyZeros()
				{
					Assert.That(f002.IsValid("00000000"), Is.False);
				}

				[Test]
				public void testFirstOfJanuaryEver()
				{
					Assert.That(f002.IsValid("00000101"), Is.True);
				}

				[Test]
				public void testLastOfDecemberEver()
				{
					Assert.That(f002.IsValid("99991231"), Is.True);
				}

				[Test]
				public void testDateOfTestCreation()
				{
					Assert.That(f002.IsValid("20161023"), Is.True);
				}

				[Test]
				public void testToLargeMonth()
				{
					Assert.That(f002.IsValid("20161323"), Is.False);
				}

				[Test]
				public void testMaxMonth()
				{
					Assert.That(f002.IsValid("20169923"), Is.False);
				}

				[Test]
				public void testToSmallMonth()
				{
					Assert.That(f002.IsValid("20160023"), Is.False);
				}

				[Test]
				public void testToLargeDay()
				{
					Assert.That(f002.IsValid("20161032"), Is.False);
				}

				[Test]
				public void testMaxDay()
				{
					Assert.That(f002.IsValid("20161099"), Is.False);
				}

				[Test]
				public void testToSmallDay()
				{
					Assert.That(f002.IsValid("20161000"), Is.False);
				}

				[Test]
				public void testToShort()
				{
					Assert.That(f002.IsValid("2016102"), Is.False);
				}

				[Test]
				public void testToLong()
				{
					Assert.That(f002.IsValid("201610234"), Is.False);
				}

				[Test]
				public void testAlphaOnly()
				{
					Assert.That(f002.IsValid("ABDCEFGH"), Is.False);
				}

				[Test]
				public void testAlphanumeric()
				{
					Assert.That(f002.IsValid("2O161O23"), Is.False);
				}

				[Test]
				public void testRandomString()
				{
					Assert.That(f002.IsValid(Guid.NewGuid().ToString()), Is.False);
				}
			}
		}
	}
}
