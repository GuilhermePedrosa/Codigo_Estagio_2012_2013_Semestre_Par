create or replace 
PROCEDURE DELETELINK 
(
  CID IN NUMBER  
, EID IN NUMBER  
) AS 
BEGIN
  Delete from contactos_por_empresa where contacto_id = CID and empresa_id = EID;
  commit;
END DELETELINK;
