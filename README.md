#### Specification / Implementation Assumptions:
1. Instruction ingestion is via file load rather than a streaming, polling or queue feed
2. We are bulk loading a batch of instructions, which cover many days and may not be in order in the file
3. Instruction format is via simple comma separated file, one instruction per line
4. Date format follows the format in the sample data provided on the specification (dd MMM yyyy)
5. Static analysis of the code was avoided to avoid additional external dependencies (as per the spec)
6. The output report is ordered by adjusted settlement date, and it was assumed days with no settlements would not be included in the report
7. Ranking of entities is based on the sum total amount of the instructions across all days loaded in the batch, separately for incoming and outgoing, where "amount" = Price per unit * Units * Agreed FX

#### Code Structure
* As a small project, there was little need for extensive hierarchy, however, the exceptions and enums are in their own package to illustrate structure
* Much of the report generation summarisation is within the main class, as when using the Java 8 features it seemed simple and clear even though it's a little verbose
* HOWEVER, I feel it's lacking testing coverage of the calculations themselves so this may need to be moved!