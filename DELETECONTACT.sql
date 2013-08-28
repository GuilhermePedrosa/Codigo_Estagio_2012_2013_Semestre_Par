create or replace 
PROCEDURE DELETECONTACT 
(
  CID IN NUMBER  
) AS 
BEGIN
  Delete from contactos_por_empresa where contacto_id = cid;
  Delete from classificacao where contacto_id = cid;
  Delete from contactos where contacto_id = cid;
  commit;
END DELETECONTACT;
