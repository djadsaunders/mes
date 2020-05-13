package com.djad.mesquery.repository;

public abstract class SQLQueries {

	public static final String SINGLE_RESOURCE =
		"SELECT r.tag, r.name, psp.state, ap.availability, s.name as shift, c.name as crew, pr.name as run, p.description as product " +
		"FROM resource AS r " +
			"JOIN production_state_period psp on r.current_production_state_period_id = psp.id " +
			"JOIN availability_period ap on r.current_availability_period_id = ap.id " +
			"LEFT JOIN shift_period sp on r.current_shift_period_id = sp.id " +
			"LEFT JOIN shift s on sp.shift_id = s.id " +
			"LEFT JOIN crew_period cp on r.current_crew_period_id = cp.id " +
			"LEFT JOIN crew c on cp.crew_id = c.id " +
			"LEFT JOIN production_run_period prp on r.current_production_run_period_id = prp.id " +
			"LEFT JOIN production_run pr on prp.production_run_id = pr.id " +
			"LEFT JOIN product p on pr.product_id = p.id " +
		"WHERE tag = ?";

	public static final String ALL_RESOURCES =
		"SELECT r.tag, r.name, psp.state, ap.availability, s.name as shift, c.name as crew, pr.name as run, p.description as product " +
		"FROM resource AS r " +
			"JOIN production_state_period psp on r.current_production_state_period_id = psp.id " +
			"JOIN availability_period ap on r.current_availability_period_id = ap.id " +
			"LEFT JOIN shift_period sp on r.current_shift_period_id = sp.id " +
			"LEFT JOIN shift s on sp.shift_id = s.id " +
			"LEFT JOIN crew_period cp on r.current_crew_period_id = cp.id " +
			"LEFT JOIN crew c on cp.crew_id = c.id " +
			"LEFT JOIN production_run_period prp on r.current_production_run_period_id = prp.id " +
			"LEFT JOIN production_run pr on prp.production_run_id = pr.id " +
			"LEFT JOIN product p on pr.product_id = p.id ";

	public static final String PRODUCTION_RUN_INFO =
		"SELECT t1.tag, t1.cnt AS in_count, t2.cnt AS out_count FROM " +
			"(SELECT r.tag AS tag,SUM(c.value) AS cnt FROM resource r " +
			"JOIN counter c ON r.id = c.resource_id " +
			"LEFT JOIN production_run_period prp ON prp.id = r.current_production_run_period_id  " +
			"WHERE c.counter_type = 'IN' " +
			"GROUP BY r.tag " +
			") as t1 JOIN " +
			"(SELECT r.tag AS tag,SUM(c.value) AS cnt FROM resource r " +
			"JOIN counter c ON r.id = c.resource_id " +
			"LEFT JOIN production_run_period prp ON prp.id = r.current_production_run_period_id  " +
			"WHERE c.counter_type = 'OUT' " +
			"GROUP BY r.tag " +
			") as t2 " +
			"ON t1.tag = t2.tag " + 
			"WHERE t1.tag = ?";
}
