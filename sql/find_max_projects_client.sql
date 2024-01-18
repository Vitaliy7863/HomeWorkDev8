SELECT client.name, COUNT(*) AS PROJECT_COUNT
FROM project
JOIN client ON project.client_id = client.id
GROUP BY client.id, client.name
HAVING COUNT(*) = (
    SELECT MAX(PROJECT)
    FROM (
        SELECT COUNT(*) AS PROJECT
        FROM project
        GROUP BY client_id
    ) AS subquery
);