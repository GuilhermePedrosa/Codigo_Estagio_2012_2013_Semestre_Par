create or replace 
PROCEDURE DELETEMORADA 
(
  MID IN NUMBER  
) AS 
BEGIN
  Delete from instalacoes where morada_id = MID;
  Delete from morada where morada_id = MID;
  commit;
END DELETEMORADA;
