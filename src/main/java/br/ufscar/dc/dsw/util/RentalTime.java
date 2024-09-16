package br.ufscar.dc.dsw.util;

public enum RentalTime
{
    H09_0("09:00"),
    H09_1("09:30"),
    H10_0("10:00"),
    H10_1("10:30"),
    H11_0("11:00"),
    H11_1("11:30"),
    H12_0("12:00"),
    H12_1("12:30"),
    H13_0("13:00"),
    H13_1("13:30"),
    H14_0("14:00"),
    H14_1("14:30"),
    H15_0("15:00"),
    H15_1("15:30"),
    H16_0("16:00"),
    H16_1("16:30");

    private String hour;

    RentalTime(String hour)
    {
        this.hour = hour;
    }

    public String getHour()
    {
        return hour;
    }
}