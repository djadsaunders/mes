SELECT t1.tag AS tag, t1.cnt AS inCount, t2.cnt AS outCount FROM (
    SELECT r.tag AS tag,SUM(c.value) AS cnt FROM resource r 
        JOIN counter c ON r.id = c.resource_id 
        LEFT JOIN production_run_period prp ON prp.id = r.current_production_run_period_id 
        WHERE c.counter_type = 'IN' 
        GROUP BY r.tag 
    ) as t1 JOIN (
    SELECT r.tag AS tag,SUM(c.value) AS cnt FROM resource r 
        JOIN counter c ON r.id = c.resource_id
        LEFT JOIN production_run_period prp ON prp.id = r.current_production_run_period_id 
        WHERE c.counter_type = 'OUT'
        GROUP BY r.tag
    ) as t2
    ON t1.tag = t2.tag
WHERE t1.tag = ?