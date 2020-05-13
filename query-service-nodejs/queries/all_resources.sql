SELECT r.name AS name, 
    r.tag AS tag, 
    psp.state AS productionState, 
    ap.availability AS availability, 
    s.name AS currentShift, 
    c.name AS currentCrew, 
    pr.name AS currentProductionRun, 
    p.description AS currentProduct
FROM resource AS r
    JOIN production_state_period psp on r.current_production_state_period_id = psp.id
    JOIN availability_period ap on r.current_availability_period_id = ap.id
    LEFT JOIN shift_period sp on r.current_shift_period_id = sp.id
    LEFT JOIN shift s on sp.shift_id = s.id
    LEFT JOIN crew_period cp on r.current_crew_period_id = cp.id
    LEFT JOIN crew c on cp.crew_id = c.id
    LEFT JOIN production_run_period prp on r.current_production_run_period_id = prp.id
    LEFT JOIN production_run pr on prp.production_run_id = pr.id
    LEFT JOIN product p on pr.product_id = p.id