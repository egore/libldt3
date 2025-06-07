using NodaTime;

namespace libldt3
{

    namespace model
    {

        public readonly struct YearOnly
        {
            public int Year { get; }
            public YearOnly(int year) => Year = year;
        }

        public abstract record PartialDate;
        public record PartialLocalDate(LocalDate Date) : PartialDate;
        public record PartialYearMonth(YearMonth Date) : PartialDate;
        public record PartialYearOnly(YearOnly Date) : PartialDate;

    }

}