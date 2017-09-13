#### Specification / Implementation Assumptions:
1. Java 1.8 is available and used in production
2. Instruction ingestion is via file load rather than a streaming, polling or queue feed
3. We are bulk loading a batch of instructions, which cover many days and may not be in order in the file
4. Instruction format is via simple comma separated file, one instruction per line
5. Date format follows the format in the sample data provided on the specification (dd MMM yyyy)
6. Assume there maybe duplicate lines in the input, which is expected rather than a problem, as there is no unique key per instruction mentioned
7. Some static analysis of the code was executed in the IDE, but not via maven plugin to avoid additional external dependencies (as per the spec)
8. The output report is ordered by adjusted settlement date, and it was assumed days with no settlements would not be included in the report
9. Ranking of entities is based on the sum total amount of the instructions across all days loaded in the batch, separately for incoming and outgoing, where "amount" = Price per unit * Units * Agreed FX
10. The output format would need to be reviewed to ensure it is as expected, as the spec is somewhat ambiguous
11. Currency is modelled as a String rather than Enum, with a restriction to 3 chars, as there was no master list of currencies supplied and it avoids having to continually make code changes to add currency types (or update database / static config)
12. When returning the ranking, if the total amount is the same between 2 entities, the order is between then is not enforced

#### Code Structure
1. As a small project, there was little need for extensive hierarchy, however, the exceptions and enums are in their own package to illustrate structure
2. For a data structure holding the Instructions, a LinkedList was used to allow for more efficient expansion,
but if refactoring it maybe worth choosing a sorted collection which offer efficiencies given the output requirements

## TODO
1. commenting, maybe some "comment templates"
4. Maybe have some ETL pattern? transformer, sender, loader?