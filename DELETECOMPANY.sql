create or replace 
PROCEDURE DELETECOMPANY 
(
  EID IN NUMBER  
) AS 
BEGIN
  Delete from contactos_por_empresa where empresa_id = EID;
  Delete from instalacoes where empresa_id = EID;
  Delete from empresa where empresa_id = EID;
  commit;
END DELETECOMPANY;
