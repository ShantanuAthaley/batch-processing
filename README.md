# Simple batch processing using Spring-Boot-Starter-Batch

## For Spring-Boot-Start-Batch (v4.3.10) refer to https://docs.spring.io/spring-batch/docs/4.3.10/reference/html/index.html

### Project artifacts and dependencies:
- Java v11
- Spring-Boot v2.7.18
- Spring-Batch <a href="https://docs.spring.io/spring-batch/docs/4.3.10/reference/html/index.html">v4.3.10</a> (as packaged in Spring-Boot)
- Needs a running mysql instance on localhost:3306 (can be re-configured in spring's `application.properties` file)
- Needs existing schema/database name `batch_processing`
- The domain table will get created in the same schema - `batch_proessing.person`
- Following advertised properties in `application.properties` to initialized database does not seem to work:
  - `spring.batch.jdbc.initialize-schema=always` - should have created schema based on `schema-all.sql` file under `src/main/resources` but unless jdbc connection string has query param `createDatabaseIfNotExist=true` e.g. `spring.datasource.url=jdbc:mysql://localhost:3306/batch_process?createDatabaseIfNotExist=true` this won't be able to connect to non-existing database schema.
  - `spring.datasource.initialization-mode=always` in conjunction with (`spring.sql.init.schema-locations=classpath:create-tables.sql`)  - does not create `people` table from `create-tables.sql`
  - `spring.datasource.initialization-mode=always` - does not seem to work. 
