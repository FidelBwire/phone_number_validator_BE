CREATE VIEW IF NOT EXISTS phone_numbers_view AS 
SELECT phone_numbers.id, ('(' || trim(code, '+') || ') '  || number) AS phone_number,
       country_codes.country_id AS country_id,
       CASE
        WHEN ('(' || trim(code, '+') || ') '  || number) REGEXP countries.validation_pattern THEN 'Valid'
        ELSE 'Invalid'
      END AS status,
       customers.customer_id, customers.name AS customer, countries.name AS country
  FROM phone_numbers phone_numbers
LEFT JOIN (
    SELECT id,
       country_id,
       code
  FROM country_codes
) country_codes ON phone_numbers.code_id = country_codes.id
LEFT JOIN (
    SELECT customer_id,
       name
  FROM customers
) customers ON phone_numbers.customer_id = customers.customer_id
LEFT JOIN (
    SELECT country_id,
       name,
       validation_pattern
  FROM countries
) countries ON country_codes.country_id = countries.country_id;