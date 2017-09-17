Feature: Adjust settlement date
  The adjusted settlement date is based on the settlement date and currency
  The adjusted date should be different from the settlement date if, given the currency, the
  settlement date falls on a weekend
  Each currency has different rules about when the week starts and finishes
  Other holidays are not taken into account, only work days and non-work days for the currency

Scenario Outline: Instruction Settlement Date Adjust

Given I use the <currency>
  When The settlement date is <settlement_date>
  Then The adjusted settlement date should be <adjusted_settlement_date>

  Examples:
    | currency  | settlement_date | adjusted_settlement_date  |
    | GBP       | "29 Dec 2016"   | "29 Dec 2016"             |
    | GBP       | "30 Dec 2016"   | "30 Dec 2016"             |
    | GBP       | "31 Dec 2016"   | "02 Jan 2017"             |
    | GBP       | "01 Jan 2017"   | "02 Jan 2017"             |
    | GBP       | "02 Jan 2017"   | "02 Jan 2017"             |
    | AED       | "29 Dec 2016"   | "29 Dec 2016"             |
    | AED       | "30 Dec 2016"   | "01 Jan 2017"             |
    | AED       | "31 Dec 2016"   | "01 Jan 2017"             |
    | AED       | "01 Jan 2017"   | "01 Jan 2017"             |
    | AED       | "02 Jan 2017"   | "02 Jan 2017"             |
    | SAR       | "29 Dec 2016"   | "29 Dec 2016"             |
    | SAR       | "30 Dec 2016"   | "01 Jan 2017"             |
    | SAR       | "31 Dec 2016"   | "01 Jan 2017"             |
    | SAR       | "01 Jan 2017"   | "01 Jan 2017"             |
    | SAR       | "02 Jan 2017"   | "02 Jan 2017"             |