using System;

namespace libldt3
{
	namespace attributes
	{

		/**
		 * List of rules to be applied using a boolean 'OR'
		 */
		[AttributeUsage(AttributeTargets.Field, Inherited = false, AllowMultiple = true)]
		public class Regelsatz : Attribute
		{
			public Type[] Value = { };

			public int Laenge = -1;

			public int MinLaenge = -1;

			public int MaxLaenge = -1;
		}
	}
}
