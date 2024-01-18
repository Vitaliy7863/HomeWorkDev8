SELECT  project.id AS project_id,
SUM(worker.salary * (DATEDIFF('MONTH', START_DATE, FINISH_DATE) + DATEDIFF('YEAR', START_DATE, FINISH_DATE) * 12)) AS PRICE
FROM  project_worker
JOIN project ON project_worker.project_id = project.id
JOIN worker ON project_worker.worker_id = worker.id
GROUP BY   project.id
ORDER BY PRICE DESC;