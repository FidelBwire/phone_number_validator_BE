CREATE VIEW IF NOT EXISTS phone_numbers_view AS 
SELECT ROWID AS row_number,
       id AS customer_id,
       customer.name AS customer,
       phone,
       country_id,
       countries.name AS country,
       validation_pattern,
       CASE WHEN (/* Check for (237) pattern */phone LIKE '(237)%' AND 
                  substr(phone, 6, 1) IN (' ', NULL) AND 
                  substr(phone, 7, 1) IN ('2', '3', '6', '8') AND 
                  substr(phone, 8) GLOB '[0-9][0-9][0-9][0-9][0-9][0-9][0-9]' OR 
                  substr(phone, 8) GLOB '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') OR 
                 (/* Check for (251) pattern */phone LIKE '(251)%' AND 
                  substr(phone, 6, 1) IN (' ', NULL) AND 
                  substr(phone, 7, 1) BETWEEN '1' AND '9' AND 
                  substr(phone, 8) GLOB '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') OR 
                 (/* Check for (212) pattern */phone LIKE '(212)%' AND 
                  substr(phone, 6, 1) IN (' ', NULL) AND 
                  substr(phone, 7, 1) BETWEEN '5' AND '9' AND 
                  substr(phone, 8) GLOB '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') OR 
                 (/* Check for (258) pattern */phone LIKE '(258)%' AND 
                  substr(phone, 6, 1) IN (' ', NULL) AND 
                  substr(phone, 7, 1) IN ('2', '8') AND 
                  substr(phone, 8) GLOB '[0-9][0-9][0-9][0-9][0-9][0-9][0-9]' OR 
                  substr(phone, 8) GLOB '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') OR 
                 (/* Check for (256) pattern */phone LIKE '(256)%' AND 
                  substr(phone, 6, 1) IN (' ', NULL) AND 
                  substr(phone, 7) GLOB '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') THEN 'Valid' ELSE 'Invalid' END AS status
FROM customer customer
LEFT JOIN
(
    SELECT country_id,
           name,
           validation_pattern,
           code
       FROM countries
)
countries ON customer.phone LIKE '(' || code || '%'