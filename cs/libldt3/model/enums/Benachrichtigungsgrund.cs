/*
 * Copyright 2016-2022  Christoph Brill <opensource@christophbrill.de>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
using System;

namespace libldt3
{
	namespace model
	{
		namespace enums
		{

			/**
			 * E049
			 */
			public enum Benachrichtigungsgrund
			{

				/** Pathologisch auffälliger Befund */
				PathologischAuffaelligerBefund,
				/** Lebensbedrohlicher Zustand */
				LebensbedrohlicherZustand,
				/** Wiedervorstellung empfohlen */
				WiedervorstellungEmpfohlen,
				/** Probenmaterial nicht verwendbar */
				ProbenmaterialNichtVerwendbar,
				/** Probenmaterial unvollständig */
				ProbenmaterialUnvollstaendig
			}

			public static class BenachrichtigungsgrundExtensions
			{
				public static string GetCode(this Benachrichtigungsgrund self)
				{
					switch (self)
					{
						case Benachrichtigungsgrund.PathologischAuffaelligerBefund: return "1";
						case Benachrichtigungsgrund.LebensbedrohlicherZustand: return "2";
						case Benachrichtigungsgrund.WiedervorstellungEmpfohlen: return "3";
						case Benachrichtigungsgrund.ProbenmaterialNichtVerwendbar: return "4";
						case Benachrichtigungsgrund.ProbenmaterialUnvollstaendig: return "5";
						default: throw new Exception();
					}
				}
			}
		}
	}
}