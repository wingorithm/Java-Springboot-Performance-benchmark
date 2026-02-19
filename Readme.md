```sql
CREATE TABLE performance_records (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id VARCHAR(50) NOT NULL, -- J11 / J17 / J21 indicating which service write this data
    transaction_message VARCHAR(50) NOT NULL, -- random data brought by performance test
    processed BOOLEAN DEFAULT false,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_user_transaction ON performance_records(user_id, transaction_message);
```

1. create endpoint POST {service-name}/api/v1/benchmark/write-flow, this controller endpoint should call function benchmarkWriteFlow in service layer, has payload body bring 1 field "transaction_message" value of string.
2. service layer's function should do following tasks
    a. log input
    b. query to table performance_records, and log current total data
    c. hit 3rd endpoint GET `http://localhost:9090/api/data?delay=200ms`, wait -> if success go to c.1, else go to c.2
    c.1. write data to table performance_records:
        id (default), user_id (based on each service: J11 / J17 / J21), transaction_message (based on payload), processed (true)
    c.2.write data to table performance_records:
        id (default), user_id (based on each service: J11 / J17 / J21), transaction_message (based on payload), processed (false)
    d. construct dummy response
3. prepare the feign client and repository layer code
4. service should have following config:
    a. HikariCP pool size config
    b. feign config
    c. other common benchmark related config